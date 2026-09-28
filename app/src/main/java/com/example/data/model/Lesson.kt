package com.example.data.model

enum class DifficultyLevel(val label: String) {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced")
}

data class LessonStep(
    val stepNumber: Int,
    val title: String,
    val description: String,
    val commandOrKey: String? = null,
    val proTip: String? = null
)

data class LessonWarning(
    val text: String,
    val isDataDestructive: Boolean = false
)

data class TroubleshootingItem(
    val symptom: String,
    val fix: String
)

data class Lesson(
    val id: String,
    val categoryId: String,
    val categoryTitle: String,
    val title: String,
    val difficulty: DifficultyLevel,
    val estimatedTimeMinutes: Int,
    val explanation: String,
    val stepByStep: List<LessonStep>,
    val requiredTools: List<String>,
    val importantWarnings: List<LessonWarning>,
    val commonMistakes: List<String>,
    val troubleshootingTips: List<TroubleshootingItem>,
    val iconKey: String = "tool"
)

data class CategoryInfo(
    val id: String,
    val title: String,
    val description: String,
    val iconKey: String,
    val totalLessons: Int = 0
)
