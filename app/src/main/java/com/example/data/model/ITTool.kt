package com.example.data.model

enum class ToolCategory(val label: String) {
    SOFTWARE("Software Diagnostic"),
    HARDWARE("Hardware & Testing"),
    NETWORKING("Networking & Cabling")
}

data class ITTool(
    val id: String,
    val name: String,
    val category: ToolCategory,
    val whatItDoes: String,
    val whenToUseIt: String,
    val basicInstructions: List<String>,
    val safetyNotes: String,
    val isDataHazard: Boolean = false,
    val officialLinkHint: String = "Industry Standard Utility"
)
