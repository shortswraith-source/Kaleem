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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.CategoryInfo
import com.example.data.model.Lesson
import com.example.ui.components.CategoryIcon
import com.example.ui.components.CyberCard
import com.example.ui.components.DifficultyBadge
import com.example.ui.components.ITTopBar
import com.example.ui.theme.BlueAccent
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.CyberGreen
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkCardBorder
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.ITMasterViewModel
import com.example.ui.viewmodel.Screen

@Composable
fun LearnScreen(viewModel: ITMasterViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val categories = viewModel.repository.getCategories()
    val completedIds = uiState.completedLessonIds

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ITTopBar(
            title = "Learning Hub",
            subtitle = "6 Comprehensive IT Specializations"
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Select a track to master hardware diagnostics, operating systems, network engineering, and system administration.",
                    color = TextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            }

            items(categories) { category ->
                val categoryLessons = viewModel.repository.getLessonsByCategory(category.id)
                val completedCount = categoryLessons.count { completedIds.contains(it.id) }
                val progressPct = if (categoryLessons.isNotEmpty()) {
                    ((completedCount.toFloat() / categoryLessons.size) * 100).toInt()
                } else 0

                CategoryHubCard(
                    category = category,
                    completedLessons = completedCount,
                    totalLessons = categoryLessons.size,
                    progressPercentage = progressPct,
                    onClick = { viewModel.navigateTo(Screen.CategoryLessons(category.id)) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun CategoryHubCard(
    category: CategoryInfo,
    completedLessons: Int,
    totalLessons: Int,
    progressPercentage: Int,
    onClick: () -> Unit
) {
    CyberCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("category_card_${category.id}"),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(DarkSurfaceVariant)
                        .border(1.dp, CyanAccent.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    CategoryIcon(category.iconKey, tint = CyanAccent, size = 26)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = category.title,
                        color = TextPrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = category.description,
                        color = TextSecondary,
                        fontSize = 12.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Open Track",
                    tint = CyanAccent,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Progress bar
            LinearProgressIndicator(
                progress = { progressPercentage / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = if (progressPercentage == 100) CyberGreen else CyanAccent,
                trackColor = DarkSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$completedLessons of $totalLessons Lessons Completed",
                    color = TextMuted,
                    fontSize = 11.sp
                )
                Text(
                    text = "$progressPercentage%",
                    color = if (progressPercentage == 100) CyberGreen else CyanAccent,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CategoryLessonsScreen(
    categoryId: String,
    viewModel: ITMasterViewModel
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val category = viewModel.repository.getCategories().find { it.id == categoryId }
    val lessons = viewModel.repository.getLessonsByCategory(categoryId)
    val completedIds = uiState.completedLessonIds

    var filterText by remember { mutableStateOf("") }
    val displayedLessons = if (filterText.isBlank()) {
        lessons
    } else {
        lessons.filter {
            it.title.contains(filterText, ignoreCase = true) ||
                    it.explanation.contains(filterText, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ITTopBar(
            title = category?.title ?: "Lessons",
            subtitle = "${lessons.size} Professional Lessons",
            canNavigateBack = true,
            onBackClick = { viewModel.navigateBack() }
        )

        // Filter search inside category
        OutlinedTextField(
            value = filterText,
            onValueChange = { filterText = it },
            placeholder = { Text("Filter lessons in this track...", color = TextMuted, fontSize = 13.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = CyanAccent,
                    modifier = Modifier.size(18.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .testTag("category_filter_input"),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CyanAccent,
                unfocusedBorderColor = DarkCardBorder,
                focusedContainerColor = DarkSurface,
                unfocusedContainerColor = DarkSurface,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            singleLine = true
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "LESSON SYLLABUS",
                        color = TextMuted,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "${lessons.count { completedIds.contains(it.id) }} / ${lessons.size} Completed",
                        color = CyanAccent,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            items(displayedLessons) { lesson ->
                val isCompleted = completedIds.contains(lesson.id)
                LessonItemCard(
                    lesson = lesson,
                    isCompleted = isCompleted,
                    onClick = { viewModel.navigateTo(Screen.LessonDetail(lesson.id)) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun LessonItemCard(
    lesson: Lesson,
    isCompleted: Boolean,
    onClick: () -> Unit
) {
    CyberCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("lesson_card_${lesson.id}"),
        borderColor = if (isCompleted) CyberGreen.copy(alpha = 0.5f) else DarkCardBorder,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(if (isCompleted) CyberGreen.copy(alpha = 0.15f) else DarkSurfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Completed",
                        tint = CyberGreen,
                        modifier = Modifier.size(22.dp)
                    )
                } else {
                    CategoryIcon(lesson.iconKey, tint = CyanAccent, size = 18)
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = lesson.title,
                    color = TextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    DifficultyBadge(lesson.difficulty)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "⏱ ${lesson.estimatedTimeMinutes} min",
                        color = TextMuted,
                        fontSize = 11.sp
                    )
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = if (isCompleted) CyberGreen else CyanAccent,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
