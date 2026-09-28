package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.ITBottomNavBar
import com.example.ui.screens.CategoryLessonsScreen
import com.example.ui.screens.GlobalSearchScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LearnScreen
import com.example.ui.screens.LessonDetailScreen
import com.example.ui.screens.PracticeScenarioDetailScreen
import com.example.ui.screens.PracticeScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.ToolDetailScreen
import com.example.ui.screens.ToolsScreen
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.ITMasterTheme
import com.example.ui.viewmodel.ITMasterViewModel
import com.example.ui.viewmodel.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ITMasterTheme {
                val viewModel: ITMasterViewModel = viewModel()
                ITMasterApp(viewModel)
            }
        }
    }
}

@Composable
fun ITMasterApp(viewModel: ITMasterViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentScreen = uiState.currentScreen

    val isRootScreen = when (currentScreen) {
        Screen.Home, Screen.Learn, Screen.Practice, Screen.Tools, Screen.Profile -> true
        else -> false
    }

    if (!isRootScreen) {
        BackHandler {
            viewModel.navigateBack()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = {
            ITBottomNavBar(
                currentScreen = currentScreen,
                onNavigate = { screen -> viewModel.navigateTo(screen) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground)
                .padding(innerPadding)
        ) {
            when (val screen = currentScreen) {
                Screen.Home -> HomeScreen(viewModel = viewModel)
                Screen.Learn -> LearnScreen(viewModel = viewModel)
                is Screen.CategoryLessons -> CategoryLessonsScreen(
                    categoryId = screen.categoryId,
                    viewModel = viewModel
                )
                is Screen.LessonDetail -> LessonDetailScreen(
                    lessonId = screen.lessonId,
                    viewModel = viewModel
                )
                Screen.Practice -> PracticeScreen(viewModel = viewModel)
                is Screen.ScenarioDetail -> PracticeScenarioDetailScreen(
                    scenarioId = screen.scenarioId,
                    viewModel = viewModel
                )
                is Screen.Quiz -> QuizScreen(
                    initialDifficulty = screen.difficulty,
                    viewModel = viewModel
                )
                Screen.Tools -> ToolsScreen(viewModel = viewModel)
                is Screen.ToolDetail -> ToolDetailScreen(
                    toolId = screen.toolId,
                    viewModel = viewModel
                )
                Screen.Profile -> ProfileScreen(viewModel = viewModel)
                Screen.GlobalSearch -> GlobalSearchScreen(viewModel = viewModel)
            }
        }
    }
}
