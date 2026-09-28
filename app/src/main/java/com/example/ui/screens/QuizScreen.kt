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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DifficultyLevel
import com.example.ui.components.CyberCard
import com.example.ui.components.DifficultyBadge
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

@Composable
fun QuizScreen(
    initialDifficulty: DifficultyLevel,
    viewModel: ITMasterViewModel
) {
    BackHandler {
        viewModel.navigateBack()
    }

    var selectedDifficulty by remember { mutableStateOf(initialDifficulty) }
    var questions by remember(selectedDifficulty) {
        mutableStateOf(viewModel.repository.getQuizQuestions(selectedDifficulty))
    }

    var currentQuestionIndex by remember(selectedDifficulty) { mutableIntStateOf(0) }
    var selectedOptionIndex by remember(selectedDifficulty, currentQuestionIndex) { mutableStateOf<Int?>(null) }
    var hasAnsweredCurrent by remember(selectedDifficulty, currentQuestionIndex) { mutableStateOf(false) }
    var score by remember(selectedDifficulty) { mutableIntStateOf(0) }
    var isQuizFinished by remember(selectedDifficulty) { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ITTopBar(
            title = "IT Skills Assessment",
            subtitle = "${selectedDifficulty.label} Certification Test",
            canNavigateBack = true,
            onBackClick = { viewModel.navigateBack() }
        )

        // Difficulty Tab Selector
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DifficultyLevel.entries.forEach { diff ->
                val isSelected = selectedDifficulty == diff
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isSelected) CyanAccent else DarkSurfaceVariant)
                        .clickable {
                            selectedDifficulty = diff
                        }
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = diff.label,
                        color = if (isSelected) DarkBackground else TextSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        if (!isQuizFinished && questions.isNotEmpty()) {
            val currentQ = questions[currentQuestionIndex]

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Progress Bar & Counter
                item {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Question ${currentQuestionIndex + 1} of ${questions.size}",
                            color = CyanAccent,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Score: $score",
                            color = CyberGreen,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { (currentQuestionIndex + 1).toFloat() / questions.size },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = CyanAccent,
                        trackColor = DarkSurfaceVariant
                    )
                }

                // Question Card
                item {
                    CyberCard(
                        modifier = Modifier.fillMaxWidth(),
                        glowEffect = true
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            DifficultyBadge(currentQ.difficulty)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = currentQ.question,
                                color = TextPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 22.sp
                            )
                        }
                    }
                }

                // Option Cards
                items(currentQ.options.size) { idx ->
                    val optionText = currentQ.options[idx]
                    val isSelected = selectedOptionIndex == idx
                    val isCorrect = idx == currentQ.correctIndex

                    val borderColor = if (hasAnsweredCurrent) {
                        when {
                            isCorrect -> CyberGreen
                            isSelected -> DangerRed
                            else -> DarkCardBorder
                        }
                    } else {
                        if (isSelected) CyanAccent else DarkCardBorder
                    }

                    val bgColor = if (hasAnsweredCurrent) {
                        when {
                            isCorrect -> CyberGreen.copy(alpha = 0.15f)
                            isSelected -> DangerRed.copy(alpha = 0.15f)
                            else -> DarkSurface
                        }
                    } else {
                        if (isSelected) CyanAccent.copy(alpha = 0.08f) else DarkSurface
                    }

                    CyberCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("quiz_option_$idx"),
                        borderColor = borderColor,
                        onClick = {
                            if (!hasAnsweredCurrent) {
                                selectedOptionIndex = idx
                                hasAnsweredCurrent = true
                                if (isCorrect) {
                                    score += 1
                                }
                            }
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(bgColor)
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(DarkSurfaceVariant)
                                    .border(1.dp, borderColor, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${('A'.code + idx).toChar()}",
                                    color = if (hasAnsweredCurrent && isCorrect) CyberGreen else TextPrimary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = optionText,
                                color = TextPrimary,
                                fontSize = 14.sp,
                                lineHeight = 19.sp,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                // Explanation Banner after answering
                if (hasAnsweredCurrent) {
                    item {
                        CyberCard(
                            modifier = Modifier.fillMaxWidth(),
                            borderColor = if (selectedOptionIndex == currentQ.correctIndex) CyberGreen else WarningAmber
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = if (selectedOptionIndex == currentQ.correctIndex) "CORRECT!" else "INCORRECT",
                                    color = if (selectedOptionIndex == currentQ.correctIndex) CyberGreen else DangerRed,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Black
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = currentQ.explanation,
                                    color = TextPrimary,
                                    fontSize = 13.sp,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    }

                    item {
                        Button(
                            onClick = {
                                if (currentQuestionIndex + 1 < questions.size) {
                                    currentQuestionIndex += 1
                                    selectedOptionIndex = null
                                    hasAnsweredCurrent = false
                                } else {
                                    isQuizFinished = true
                                    viewModel.recordQuizScore(selectedDifficulty, score, questions.size)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("quiz_next_button"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = CyanAccent)
                        ) {
                            Text(
                                text = if (currentQuestionIndex + 1 < questions.size) "Next Question" else "Complete Quiz",
                                color = DarkBackground,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }
            }
        } else if (isQuizFinished) {
            // Results Screen
            val percentage = if (questions.isNotEmpty()) ((score.toFloat() / questions.size) * 100).toInt() else 0
            val passed = percentage >= 80

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(if (passed) CyberGreen.copy(alpha = 0.2f) else DangerRed.copy(alpha = 0.2f))
                            .border(2.dp, if (passed) CyberGreen else DangerRed, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (passed) "🏆" else "📚",
                            fontSize = 44.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = if (passed) "CERTIFICATION PASSED!" else "KEEP PRACTICING!",
                        color = if (passed) CyberGreen else WarningAmber,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Score: $score out of ${questions.size} ($percentage%)",
                        color = TextPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (passed) "Great job! Your technical competency has been recorded in your local profile."
                        else "You need 80% to pass this module. Review the course material and try again.",
                        color = TextSecondary,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = {
                            currentQuestionIndex = 0
                            selectedOptionIndex = null
                            hasAnsweredCurrent = false
                            score = 0
                            isQuizFinished = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("retake_quiz_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = CyanAccent)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            tint = DarkBackground,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Retake Assessment",
                            color = DarkBackground,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { viewModel.navigateBack() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DarkSurfaceVariant)
                    ) {
                        Text(
                            text = "Return to Practice Hub",
                            color = TextPrimary,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
