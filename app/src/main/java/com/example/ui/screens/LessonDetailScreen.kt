package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.LessonStep
import com.example.data.model.TroubleshootingItem
import com.example.ui.components.CategoryIcon
import com.example.ui.components.CodeCommandBox
import com.example.ui.components.CyberCard
import com.example.ui.components.DifficultyBadge
import com.example.ui.components.HazardWarningBanner
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
fun LessonDetailScreen(
    lessonId: String,
    viewModel: ITMasterViewModel
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val lesson = viewModel.repository.getLessonById(lessonId)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (lesson == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground),
            contentAlignment = Alignment.Center
        ) {
            Text("Lesson not found", color = TextSecondary)
        }
        return
    }

    val isCompleted = uiState.completedLessonIds.contains(lesson.id)
    val isBookmarked = uiState.bookmarkedItemIds.contains(lesson.id)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ITTopBar(
            title = lesson.title,
            subtitle = lesson.categoryTitle,
            canNavigateBack = true,
            onBackClick = { viewModel.navigateBack() },
            actions = {
                IconButton(
                    onClick = {
                        viewModel.toggleBookmark(
                            itemId = lesson.id,
                            itemType = "LESSON",
                            title = lesson.title,
                            category = lesson.categoryTitle
                        )
                    },
                    modifier = Modifier.testTag("bookmark_button")
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) CyanAccent else TextMuted
                    )
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Overview Card
            item {
                CyberCard(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DifficultyBadge(lesson.difficulty)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "⏱ Est. ${lesson.estimatedTimeMinutes} mins",
                                    color = CyanAccent,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = lesson.explanation,
                            color = TextPrimary,
                            fontSize = 14.sp,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            // Visual Technical Diagram / Schematic Banner Placeholder
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(DarkSurfaceVariant, DarkSurface)
                            )
                        )
                        .border(1.dp, BlueAccent.copy(alpha = 0.25f), RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CategoryIcon(lesson.iconKey, tint = CyanAccent, size = 32)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "TECHNICAL PROCEDURE SCHEMATIC",
                                color = CyanAccent,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = "Standard Operating Procedure (SOP) Reference",
                                color = TextSecondary,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Important Warnings (Highlighting data-hazard in RED)
            if (lesson.importantWarnings.isNotEmpty()) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        lesson.importantWarnings.forEach { warning ->
                            HazardWarningBanner(
                                text = warning.text,
                                isDataDestructive = warning.isDataDestructive
                            )
                        }
                    }
                }
            }

            // Required Tools Section
            if (lesson.requiredTools.isNotEmpty()) {
                item {
                    CyberCard(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Build,
                                    contentDescription = null,
                                    tint = CyanAccent,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "REQUIRED TOOLS & EQUIPMENT",
                                    color = CyanAccent,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            lesson.requiredTools.forEach { tool ->
                                Row(
                                    modifier = Modifier.padding(vertical = 3.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(6.dp)
                                            .clip(CircleShape)
                                            .background(CyanAccent)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = tool,
                                        color = TextPrimary,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Step-by-Step Instructions
            item {
                Text(
                    text = "STEP-BY-STEP INSTRUCTIONS",
                    color = CyanAccent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )
            }

            itemsIndexed(lesson.stepByStep) { index, step ->
                StepCard(step = step)
            }

            // Common Mistakes Section
            if (lesson.commonMistakes.isNotEmpty()) {
                item {
                    CyberCard(
                        modifier = Modifier.fillMaxWidth(),
                        borderColor = WarningAmber.copy(alpha = 0.4f)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null,
                                    tint = WarningAmber,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "COMMON TECHNICIAN MISTAKES",
                                    color = WarningAmber,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            lesson.commonMistakes.forEach { mistake ->
                                Row(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text("⚠️", fontSize = 12.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = mistake,
                                        color = TextPrimary,
                                        fontSize = 13.sp,
                                        lineHeight = 18.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Troubleshooting Section
            if (lesson.troubleshootingTips.isNotEmpty()) {
                item {
                    CyberCard(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = BlueAccent,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "FIELD TROUBLESHOOTING GUIDE",
                                    color = BlueAccent,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            lesson.troubleshootingTips.forEach { item ->
                                TroubleshootingItemCard(item = item)
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }

            // Mark as Complete Button
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { viewModel.toggleLessonCompleted(lesson.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("mark_complete_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isCompleted) CyberGreen else CyanAccent
                    )
                ) {
                    Icon(
                        imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.Check,
                        contentDescription = null,
                        tint = DarkBackground,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isCompleted) "Completed (Tap to Undo)" else "Mark as Complete",
                        color = DarkBackground,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun StepCard(step: LessonStep) {
    CyberCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(CyanAccent.copy(alpha = 0.2f))
                        .border(1.dp, CyanAccent, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${step.stepNumber}",
                        color = CyanAccent,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = step.title,
                    color = TextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = step.description,
                color = TextSecondary,
                fontSize = 13.sp,
                lineHeight = 19.sp
            )

            if (step.commandOrKey != null) {
                Spacer(modifier = Modifier.height(10.dp))
                CodeCommandBox(command = step.commandOrKey)
            }

            if (step.proTip != null) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(DarkSurfaceVariant)
                        .padding(10.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = "Pro Tip",
                        tint = CyanAccent,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "PRO TIP: ${step.proTip}",
                        color = CyanAccent,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun TroubleshootingItemCard(item: TroubleshootingItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(DarkSurfaceVariant)
            .padding(12.dp)
    ) {
        Text(
            text = "SYMPTOM: ${item.symptom}",
            color = WarningAmber,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "RESOLUTION: ${item.fix}",
            color = TextPrimary,
            fontSize = 13.sp,
            lineHeight = 18.sp
        )
    }
}
