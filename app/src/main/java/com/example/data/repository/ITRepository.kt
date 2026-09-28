package com.example.data.repository

import com.example.data.datasource.CctvLessonsDataSource
import com.example.data.datasource.HardwareLessonsDataSource
import com.example.data.datasource.NetworkingLessonsDataSource
import com.example.data.datasource.PracticeScenariosDataSource
import com.example.data.datasource.PrinterLessonsDataSource
import com.example.data.datasource.QuizDataSource
import com.example.data.datasource.SoftwareLessonsDataSource
import com.example.data.datasource.ToolsDataSource
import com.example.data.datasource.WindowsLessonsDataSource
import com.example.data.local.ProgressDao
import com.example.data.local.entity.BookmarkedItemEntity
import com.example.data.local.entity.CompletedLessonEntity
import com.example.data.local.entity.QuizScoreEntity
import com.example.data.local.entity.SolvedScenarioEntity
import com.example.data.model.CategoryInfo
import com.example.data.model.DifficultyLevel
import com.example.data.model.ITTool
import com.example.data.model.Lesson
import com.example.data.model.PracticeScenario
import com.example.data.model.QuizQuestion
import kotlinx.coroutines.flow.Flow

class ITRepository(private val progressDao: ProgressDao) {

    private val cachedLessons: List<Lesson> by lazy {
        WindowsLessonsDataSource.getLessons() +
                HardwareLessonsDataSource.getLessons() +
                PrinterLessonsDataSource.getLessons() +
                CctvLessonsDataSource.getLessons() +
                NetworkingLessonsDataSource.getLessons() +
                SoftwareLessonsDataSource.getLessons()
    }

    private val cachedScenarios: List<PracticeScenario> by lazy {
        PracticeScenariosDataSource.getScenarios()
    }

    private val cachedTools: List<ITTool> by lazy {
        ToolsDataSource.getTools()
    }

    fun getCategories(): List<CategoryInfo> {
        return listOf(
            CategoryInfo(
                id = "windows",
                title = "Windows OS",
                description = "Installation, bootable media, UEFI, partitioning, drivers & diagnostics",
                iconKey = "windows",
                totalLessons = WindowsLessonsDataSource.getLessons().size
            ),
            CategoryInfo(
                id = "pc_hardware",
                title = "PC Hardware",
                description = "CPU, RAM, GPU, motherboard, PSU, assembly & POST troubleshooting",
                iconKey = "hardware",
                totalLessons = HardwareLessonsDataSource.getLessons().size
            ),
            CategoryInfo(
                id = "printers",
                title = "Printers & Scanners",
                description = "USB, network TCP/IP, spooler, paper jams, drums, toners & sharing",
                iconKey = "printer",
                totalLessons = PrinterLessonsDataSource.getLessons().size
            ),
            CategoryInfo(
                id = "cctv_dvr",
                title = "CCTV & DVR",
                description = "Surveillance HDDs, BNC, PoE IP cameras, P2P remote & error codes",
                iconKey = "cctv",
                totalLessons = CctvLessonsDataSource.getLessons().size
            ),
            CategoryInfo(
                id = "networking",
                title = "Networking",
                description = "LAN, Cat6, RJ45 crimping, IP, DHCP, DNS, routers & Wi-Fi diagnostics",
                iconKey = "network",
                totalLessons = NetworkingLessonsDataSource.getLessons().size
            ),
            CategoryInfo(
                id = "software_tools",
                title = "Software & Tools",
                description = "Device Manager, Rufus, Ventoy, CPU-Z, CrystalDiskInfo & Event Viewer",
                iconKey = "tools",
                totalLessons = SoftwareLessonsDataSource.getLessons().size
            )
        )
    }

    fun getAllLessons(): List<Lesson> = cachedLessons

    fun getLessonsByCategory(categoryId: String): List<Lesson> {
        return cachedLessons.filter { it.categoryId == categoryId }
    }

    fun getLessonById(lessonId: String): Lesson? {
        return cachedLessons.find { it.id == lessonId }
    }

    fun getAllScenarios(): List<PracticeScenario> = cachedScenarios

    fun getScenarioById(scenarioId: String): PracticeScenario? {
        return cachedScenarios.find { it.id == scenarioId }
    }

    fun getAllTools(): List<ITTool> = cachedTools

    fun getToolById(toolId: String): ITTool? {
        return cachedTools.find { it.id == toolId }
    }

    fun getQuizQuestions(difficulty: DifficultyLevel): List<QuizQuestion> {
        return QuizDataSource.getQuestions(difficulty)
    }

    fun searchLessons(query: String): List<Lesson> {
        if (query.isBlank()) return emptyList()
        val q = query.trim().lowercase()
        return cachedLessons.filter {
            it.title.lowercase().contains(q) ||
                    it.explanation.lowercase().contains(q) ||
                    it.categoryTitle.lowercase().contains(q) ||
                    it.requiredTools.any { tool -> tool.lowercase().contains(q) } ||
                    it.troubleshootingTips.any { tip -> tip.symptom.lowercase().contains(q) || tip.fix.lowercase().contains(q) }
        }
    }

    fun searchTools(query: String): List<ITTool> {
        if (query.isBlank()) return emptyList()
        val q = query.trim().lowercase()
        return cachedTools.filter {
            it.name.lowercase().contains(q) ||
                    it.whatItDoes.lowercase().contains(q) ||
                    it.whenToUseIt.lowercase().contains(q)
        }
    }

    fun searchScenarios(query: String): List<PracticeScenario> {
        if (query.isBlank()) return emptyList()
        val q = query.trim().lowercase()
        return cachedScenarios.filter {
            it.title.lowercase().contains(q) ||
                    it.customerComplaint.lowercase().contains(q) ||
                    it.finalSolution.lowercase().contains(q)
        }
    }

    // Room Database Progress Operations
    val completedLessonsFlow: Flow<List<CompletedLessonEntity>> = progressDao.getAllCompletedLessons()

    suspend fun markLessonCompleted(lessonId: String) {
        progressDao.insertCompletedLesson(CompletedLessonEntity(lessonId = lessonId))
    }

    suspend fun unmarkLessonCompleted(lessonId: String) {
        progressDao.deleteCompletedLesson(lessonId)
    }

    val solvedScenariosFlow: Flow<List<SolvedScenarioEntity>> = progressDao.getAllSolvedScenarios()

    suspend fun markScenarioSolved(scenarioId: String, solvedCorrectly: Boolean) {
        progressDao.insertSolvedScenario(
            SolvedScenarioEntity(scenarioId = scenarioId, solvedCorrectly = solvedCorrectly)
        )
    }

    val quizScoresFlow: Flow<List<QuizScoreEntity>> = progressDao.getAllQuizScores()

    suspend fun recordQuizScore(difficulty: String, score: Int, total: Int, percentage: Int) {
        progressDao.insertQuizScore(
            QuizScoreEntity(
                difficulty = difficulty,
                score = score,
                totalQuestions = total,
                percentage = percentage
            )
        )
    }

    val bookmarksFlow: Flow<List<BookmarkedItemEntity>> = progressDao.getAllBookmarks()

    suspend fun toggleBookmark(
        itemId: String,
        itemType: String,
        title: String,
        category: String,
        isCurrentlyBookmarked: Boolean
    ) {
        if (isCurrentlyBookmarked) {
            progressDao.deleteBookmark(itemId)
        } else {
            progressDao.insertBookmark(
                BookmarkedItemEntity(
                    itemId = itemId,
                    itemType = itemType,
                    title = title,
                    category = category
                )
            )
        }
    }

    suspend fun resetAllProgress() {
        progressDao.clearCompletedLessons()
        progressDao.clearSolvedScenarios()
        progressDao.clearQuizScores()
        progressDao.clearBookmarks()
    }
}
