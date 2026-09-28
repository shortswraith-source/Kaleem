package com.example.data.model

data class DiagnosticChoice(
    val id: String,
    val text: String,
    val isCorrectFirstStep: Boolean,
    val outcomeFeedback: String
)

data class PracticeScenario(
    val id: String,
    val title: String,
    val category: String,
    val customerComplaint: String,
    val deviceSpecs: String,
    val availableSymptoms: List<String>,
    val possibleCauses: List<String>,
    val diagnosticChoices: List<DiagnosticChoice>,
    val correctChoiceId: String,
    val systematicDiagnosticPath: String,
    val finalSolution: String,
    val proTechnicianRule: String,
    val isDataHazard: Boolean = false
)
