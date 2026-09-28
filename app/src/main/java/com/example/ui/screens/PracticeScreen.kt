package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.PracticeScenario
import com.example.ui.components.CyberCard
import com.example.ui.components.ITTopBar
import com.example.ui.theme.BlueAccent
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.CyberGreen
import com.example.ui.theme.DangerRed
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkCardBorder
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.WarningAmber
import com.example.ui.viewmodel.ITMasterViewModel
import com.example.ui.viewmodel.Screen

@Composable
fun PracticeScreen(viewModel: ITMasterViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scenarios = viewModel.repository.getAllScenarios()
    val solvedIds = uiState.solvedScenarioIds

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ITTopBar(
            title = "Practice Lab",
            subtitle = "Realistic IT Helpdesk Customer Tickets"
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
                CyberCard(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.SupportAgent,
                            contentDescription = null,
                            tint = CyanAccent,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "HOW PRACTICE MODE WORKS",
                                color = CyanAccent,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Review the customer complaint, analyze symptoms, and choose the correct first diagnostic step to resolve the trouble ticket.",
                                color = TextSecondary,
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "CUSTOMER SERVICE TICKETS",
                        color = TextMuted,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "${solvedIds.size} / ${scenarios.size} Solved",
                        color = CyberGreen,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            items(scenarios) { scenario ->
                val isSolved = solvedIds.contains(scenario.id)
                ScenarioItemCard(
                    scenario = scenario,
                    isSolved = isSolved,
                    onClick = { viewModel.navigateTo(Screen.ScenarioDetail(scenario.id)) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ScenarioItemCard(
    scenario: PracticeScenario,
    isSolved: Boolean,
    onClick: () -> Unit
) {
    CyberCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("scenario_card_${scenario.id}"),
        borderColor = if (isSolved) CyberGreen.copy(alpha = 0.5f) else DarkCardBorder,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(CyanAccent.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = scenario.category.uppercase(),
                        color = CyanAccent,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }

                if (isSolved) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Solved",
                            tint = CyberGreen,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "SOLVED",
                            color = CyberGreen,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = scenario.title,
                color = TextPrimary,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = scenario.customerComplaint,
                color = TextSecondary,
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Start Diagnosis",
                    color = CyanAccent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = CyanAccent,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Composable
fun PracticeScenarioDetailScreen(
    scenarioId: String,
    viewModel: ITMasterViewModel
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val scenario = viewModel.repository.getScenarioById(scenarioId)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (scenario == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground),
            contentAlignment = Alignment.Center
        ) {
            Text("Scenario not found", color = TextSecondary)
        }
        return
    }

    var selectedChoiceId by remember { mutableStateOf<String?>(null) }
    var hasEvaluated by remember { mutableStateOf(false) }
    val isAlreadySolved = uiState.solvedScenarioIds.contains(scenario.id)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ITTopBar(
            title = scenario.title,
            subtitle = scenario.category,
            canNavigateBack = true,
            onBackClick = { viewModel.navigateBack() }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Customer Complaint Box
            item {
                CyberCard(
                    modifier = Modifier.fillMaxWidth(),
                    borderColor = CyanAccent.copy(alpha = 0.4f)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.SupportAgent,
                                contentDescription = null,
                                tint = CyanAccent,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "CUSTOMER COMPLAINT",
                                color = CyanAccent,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = scenario.customerComplaint,
                            color = TextPrimary,
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic,
                            lineHeight = 20.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Hardware Profile: ${scenario.deviceSpecs}",
                            color = TextMuted,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            // Available Symptoms
            item {
                CyberCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "OBSERVED SYMPTOMS",
                            color = WarningAmber,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        scenario.availableSymptoms.forEach { symptom ->
                            Row(
                                modifier = Modifier.padding(vertical = 3.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(WarningAmber)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = symptom,
                                    color = TextPrimary,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }

            // Possible Causes
            item {
                CyberCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "POTENTIAL ROOT CAUSES",
                            color = BlueAccent,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        scenario.possibleCauses.forEach { cause ->
                            Row(
                                modifier = Modifier.padding(vertical = 3.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(BlueAccent)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = cause,
                                    color = TextSecondary,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }

            // Diagnostic Decision Prompt
            item {
                Text(
                    text = "WHAT SHOULD YOU CHECK OR DO FIRST?",
                    color = CyanAccent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )
            }

            // Diagnostic Choices
            items(scenario.diagnosticChoices) { choice ->
                val isSelected = selectedChoiceId == choice.id
                val choiceBorder = if (hasEvaluated) {
                    if (choice.isCorrectFirstStep) CyberGreen else if (isSelected) DangerRed else DarkCardBorder
                } else {
                    if (isSelected) CyanAccent else DarkCardBorder
                }

                CyberCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("choice_${choice.id}"),
                    borderColor = choiceBorder,
                    onClick = {
                        if (!hasEvaluated) {
                            selectedChoiceId = choice.id
                        }
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = {
                                if (!hasEvaluated) {
                                    selectedChoiceId = choice.id
                                }
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = CyanAccent,
                                unselectedColor = TextMuted
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = choice.text,
                            color = TextPrimary,
                            fontSize = 14.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            // Evaluate Decision Button
            item {
                if (!hasEvaluated) {
                    Button(
                        onClick = {
                            if (selectedChoiceId != null) {
                                hasEvaluated = true
                                val chosen = scenario.diagnosticChoices.find { it.id == selectedChoiceId }
                                if (chosen?.isCorrectFirstStep == true) {
                                    viewModel.markScenarioSolved(scenario.id, true)
                                }
                            }
                        },
                        enabled = selectedChoiceId != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("evaluate_decision_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CyanAccent,
                            disabledContainerColor = DarkSurfaceVariant
                        )
                    ) {
                        Text(
                            text = "Submit Diagnostic Step",
                            color = DarkBackground,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Evaluation Outcome & Systematic Diagnostic Path
            if (hasEvaluated) {
                val chosen = scenario.diagnosticChoices.find { it.id == selectedChoiceId }
                val isCorrect = chosen?.isCorrectFirstStep == true

                item {
                    CyberCard(
                        modifier = Modifier.fillMaxWidth(),
                        borderColor = if (isCorrect) CyberGreen else DangerRed,
                        glowEffect = true
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Help,
                                    contentDescription = null,
                                    tint = if (isCorrect) CyberGreen else DangerRed,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isCorrect) "EXCELLENT DIAGNOSIS!" else "DIAGNOSTIC PATH FEEDBACK",
                                    color = if (isCorrect) CyberGreen else DangerRed,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = chosen?.outcomeFeedback ?: "",
                                color = TextPrimary,
                                fontSize = 13.sp,
                                lineHeight = 19.sp
                            )
                        }
                    }
                }

                // Full Systematic Diagnostic Path
                item {
                    CyberCard(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "SYSTEMATIC TECHNICIAN ELIMINATION PATH",
                                color = CyanAccent,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = scenario.systematicDiagnosticPath,
                                color = TextPrimary,
                                fontSize = 13.sp,
                                lineHeight = 19.sp
                            )
                        }
                    }
                }

                // Final Solution
                item {
                    CyberCard(
                        modifier = Modifier.fillMaxWidth(),
                        borderColor = CyberGreen.copy(alpha = 0.5f)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "FINAL REPAIR SOLUTION",
                                color = CyberGreen,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = scenario.finalSolution,
                                color = TextPrimary,
                                fontSize = 13.sp,
                                lineHeight = 19.sp
                            )
                        }
                    }
                }

                // Pro Technician Rule
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(DarkSurfaceVariant)
                            .padding(14.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = "Pro Rule",
                            tint = WarningAmber,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "PRO TECHNICIAN RULE",
                                color = WarningAmber,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = scenario.proTechnicianRule,
                                color = TextPrimary,
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
