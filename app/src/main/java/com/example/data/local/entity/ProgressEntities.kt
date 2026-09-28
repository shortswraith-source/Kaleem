package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_lessons")
data class CompletedLessonEntity(
    @PrimaryKey val lessonId: String,
    val completedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "solved_scenarios")
data class SolvedScenarioEntity(
    @PrimaryKey val scenarioId: String,
    val solvedCorrectly: Boolean,
    val completedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "quiz_scores")
data class QuizScoreEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val difficulty: String,
    val score: Int,
    val totalQuestions: Int,
    val percentage: Int,
    val takenAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "bookmarked_items")
data class BookmarkedItemEntity(
    @PrimaryKey val itemId: String,
    val itemType: String, // LESSON, TOOL, SCENARIO
    val title: String,
    val category: String,
    val savedAt: Long = System.currentTimeMillis()
)
