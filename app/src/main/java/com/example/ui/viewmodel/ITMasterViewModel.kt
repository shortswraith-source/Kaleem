package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.entity.BookmarkedItemEntity
import com.example.data.local.entity.CompletedLessonEntity
import com.example.data.local.entity.QuizScoreEntity
import com.example.data.local.entity.SolvedScenarioEntity
import com.example.data.model.CategoryInfo
import com.example.data.model.DifficultyLevel
import com.example.data.model.ITTool
import com.example.data.model.Lesson
import com.example.data.model.PracticeScenario
import com.example.data.repository.ITRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class Screen {
    data object Home : Screen()
    data object Learn : Screen()
    data class CategoryLessons(val categoryId: String) : Screen()
    data class LessonDetail(val lessonId: String) : Screen()
    data object Practice : Screen()
    data class ScenarioDetail(val scenarioId: String) : Screen()
    data class Quiz(val difficulty: DifficultyLevel) : Screen()
    data object Tools : Screen()
    data class ToolDetail(val toolId: String) : Screen()
    data object Profile : Screen()
    data object GlobalSearch : Screen()
}

data class ITMasterUiState(
    val currentScreen: Screen = Screen.Home,
    val screenStack: List<Screen> = listOf(Screen.Home),
    val searchQuery: String = "",
    val completedLessonIds: Set<String> = emptySet(),
    val solvedScenarioIds: Set<String> = emptySet(),
    val quizScores: List<QuizScoreEntity> = emptyList(),
    val bookmarkedItemIds: Set<String> = emptySet(),
    val currentTipIndex: Int = 0,
    val selectedToolCategory: String = "ALL"
)

data class UserStats(
    val totalLessons: Int,
    val completedLessons: Int,
    val progressPercentage: Int,
    val solvedScenarios: Int,
    val totalScenarios: Int,
    val quizAttemptCount: Int,
    val technicianRank: String,
    val rankBadge: String
)

class ITMasterViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    val repository = ITRepository(db.progressDao())

    private val _uiState = MutableStateFlow(ITMasterUiState())
    val uiState: StateFlow<ITMasterUiState> = _uiState.asStateFlow()

    val completedLessons: StateFlow<List<CompletedLessonEntity>> = repository.completedLessonsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val solvedScenarios: StateFlow<List<SolvedScenarioEntity>> = repository.solvedScenariosFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val quizScores: StateFlow<List<QuizScoreEntity>> = repository.quizScoresFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarks: StateFlow<List<BookmarkedItemEntity>> = repository.bookmarksFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val userStats: StateFlow<UserStats> = combine(
        completedLessons,
        solvedScenarios,
        quizScores
    ) { completed, solved, quizzes ->
        val totalLessons = repository.getAllLessons().size
        val completedCount = completed.size
        val progressPct = if (totalLessons > 0) ((completedCount.toFloat() / totalLessons) * 100).toInt() else 0
        val solvedCount = solved.size
        val totalScenarios = repository.getAllScenarios().size

        val (rank, badge) = when {
            progressPct >= 90 && solvedCount >= 8 -> "Certified IT Master" to "👑"
            progressPct >= 65 && solvedCount >= 5 -> "Senior Systems Specialist" to "⚡"
            progressPct >= 35 && solvedCount >= 3 -> "Junior Field Technician" to "🔧"
            completedCount >= 5 -> "Hardware Apprentice" to "💻"
            else -> "Novice Tech Trainee" to "🌱"
        }

        UserStats(
            totalLessons = totalLessons,
            completedLessons = completedCount,
            progressPercentage = progressPct,
            solvedScenarios = solvedCount,
            totalScenarios = totalScenarios,
            quizAttemptCount = quizzes.size,
            technicianRank = rank,
            rankBadge = badge
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        UserStats(
            totalLessons = repository.getAllLessons().size,
            completedLessons = 0,
            progressPercentage = 0,
            solvedScenarios = 0,
            totalScenarios = repository.getAllScenarios().size,
            quizAttemptCount = 0,
            technicianRank = "Novice Tech Trainee",
            rankBadge = "🌱"
        )
    )

    init {
        viewModelScope.launch {
            completedLessons.collect { list ->
                _uiState.value = _uiState.value.copy(
                    completedLessonIds = list.map { it.lessonId }.toSet()
                )
            }
        }
        viewModelScope.launch {
            solvedScenarios.collect { list ->
                _uiState.value = _uiState.value.copy(
                    solvedScenarioIds = list.map { it.scenarioId }.toSet()
                )
            }
        }
        viewModelScope.launch {
            bookmarks.collect { list ->
                _uiState.value = _uiState.value.copy(
                    bookmarkedItemIds = list.map { it.itemId }.toSet()
                )
            }
        }
    }

    val dailyTips = listOf(
        "Peel Heatsink Plastic: Always inspect the bottom copper plate of brand new CPU coolers before mounting—leaving the clear film on causes instant 100°C thermal throttling!",
        "RAM Dual Channel Rule: On motherboards with 4 RAM slots, always populate Slot 2 and Slot 4 (A2 and B2) first for optimal signal trace integrity.",
        "Print Spooler Quick Fix: If printer queue is locked, open elevated CMD and type: 'net stop spooler && del /Q /F /S %systemroot%\\System32\\Spool\\Printers\\*.* && net start spooler'.",
        "DVR Hard Drive Spindown: If an installed CCTV HDD is not detected and clicks repeatedly, measure the 12V power adapter—an aging adapter dropping to 10.5V cannot supply the motor spin-up surge!",
        "APIPA Alert: Any IP address starting with 169.254.x.x means the PC failed to reach a DHCP server. Check the physical Ethernet cable, RJ45 clip, or router DHCP service.",
        "Static Safety: Never handle bare motherboards or RAM on carpets. Touch an unpainted metal chassis screw or wear an anti-static wrist strap with a 1MΩ resistor.",
        "The Ping Diagnostic Ladder: 1) ping 127.0.0.1 (TCP/IP stack), 2) ping local IP (NIC driver), 3) ping gateway IP (LAN router), 4) ping 8.8.8.8 (Internet WAN).",
        "Disk Management Secret: Brand new retail HDDs and SSDs will not show up in File Explorer until you open diskmgmt.msc, initialize as GPT, and create a Simple Volume."
    )

    fun nextTip() {
        _uiState.value = _uiState.value.copy(
            currentTipIndex = (_uiState.value.currentTipIndex + 1) % dailyTips.size
        )
    }

    fun navigateTo(screen: Screen) {
        if (_uiState.value.currentScreen == screen) return
        val currentStack = _uiState.value.screenStack
        // If navigating to a bottom nav root screen, reset stack to that screen
        val newStack = when (screen) {
            Screen.Home, Screen.Learn, Screen.Practice, Screen.Tools, Screen.Profile -> listOf(screen)
            else -> currentStack + screen
        }
        _uiState.value = _uiState.value.copy(
            currentScreen = screen,
            screenStack = newStack
        )
    }

    fun navigateBack(): Boolean {
        val currentStack = _uiState.value.screenStack
        return if (currentStack.size > 1) {
            val updatedStack = currentStack.dropLast(1)
            val previousScreen = updatedStack.last()
            _uiState.value = _uiState.value.copy(
                currentScreen = previousScreen,
                screenStack = updatedStack
            )
            true
        } else {
            false
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun setToolCategoryFilter(category: String) {
        _uiState.value = _uiState.value.copy(selectedToolCategory = category)
    }

    fun toggleLessonCompleted(lessonId: String) {
        viewModelScope.launch {
            val isCompleted = _uiState.value.completedLessonIds.contains(lessonId)
            if (isCompleted) {
                repository.unmarkLessonCompleted(lessonId)
            } else {
                repository.markLessonCompleted(lessonId)
            }
        }
    }

    fun markScenarioSolved(scenarioId: String, correct: Boolean) {
        viewModelScope.launch {
            repository.markScenarioSolved(scenarioId, correct)
        }
    }

    fun recordQuizScore(difficulty: DifficultyLevel, score: Int, total: Int) {
        viewModelScope.launch {
            val pct = if (total > 0) ((score.toFloat() / total) * 100).toInt() else 0
            repository.recordQuizScore(difficulty.label, score, total, pct)
        }
    }

    fun toggleBookmark(itemId: String, itemType: String, title: String, category: String) {
        viewModelScope.launch {
            val isBookmarked = _uiState.value.bookmarkedItemIds.contains(itemId)
            repository.toggleBookmark(itemId, itemType, title, category, isBookmarked)
        }
    }

    fun resetAllProgress() {
        viewModelScope.launch {
            repository.resetAllProgress()
        }
    }
}
