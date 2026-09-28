package com.example.data.model

data class QuizQuestion(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val difficulty: DifficultyLevel
)

data class QuizResult(
    val score: Int,
    val total: Int,
    val percentage: Int,
    val passed: Boolean
)
