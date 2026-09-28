package com.example.ui.screens

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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

data class AchievementBadge(
    val title: String,
    val description: String,
    val iconEmoji: String,
    val isUnlocked: Boolean
)

@Composable
fun ProfileScreen(viewModel: ITMasterViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userStats by viewModel.userStats.collectAsStateWithLifecycle()
    val quizScores by viewModel.quizScores.collectAsStateWithLifecycle()
    val bookmarks by viewModel.bookmarks.collectAsStateWithLifecycle()

    var showResetDialog by remember { mutableStateOf(false) }

    val badges = listOf(
        AchievementBadge(
            title = "First Boot",
            description = "Completed first lesson in any track",
            iconEmoji = "🔌",
            isUnlocked = userStats.completedLessons >= 1
        ),
        AchievementBadge(
            title = "Cable Master",
            description = "Completed Networking & RJ45 lessons",
            iconEmoji = "🔗",
            isUnlocked = uiState.completedLessonIds.contains("net_rj45")
        ),
        AchievementBadge(
            title = "Hardware Guru",
            description = "Completed at least 5 PC Hardware lessons",
            iconEmoji = "💻",
            isUnlocked = userStats.completedLessons >= 5
        ),
        AchievementBadge(
            title = "Field Diagnostic Ace",
            description = "Solved 3 or more customer practice tickets",
            iconEmoji = "🛠️",
            isUnlocked = userStats.solvedScenarios >= 3
        ),
        AchievementBadge(
            title = "Certified IT Master",
            description = "Completed 80% of entire curriculum and 8 scenarios",
            iconEmoji = "👑",
            isUnlocked = userStats.progressPercentage >= 80 && userStats.solvedScenarios >= 8
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        ITTopBar(
            title = "Technician Profile",
            subtitle = "Career Progression & Achievements"
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Profile Rank Card
            item {
                CyberCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("profile_rank_card"),
                    glowEffect = true
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(76.dp)
                                .clip(CircleShape)
                                .background(CyanAccent.copy(alpha = 0.15f))
                                .border(2.dp, CyanAccent, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = userStats.rankBadge, fontSize = 34.sp)
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = userStats.technicianRank,
                            color = TextPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "IT Master Certified Trainee",
                            color = CyanAccent,
                            fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        LinearProgressIndicator(
                            progress = { userStats.progressPercentage / 100f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = CyanAccent,
                            trackColor = DarkSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${userStats.progressPercentage}% Curriculum Mastered",
                            color = TextSecondary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Stats Matrix
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatBox(
                        title = "Completed",
                        value = "${userStats.completedLessons}",
                        subtitle = "of ${userStats.totalLessons} Lessons",
                        color = CyanAccent,
                        modifier = Modifier.weight(1f)
                    )
                    StatBox(
                        title = "Tickets",
                        value = "${userStats.solvedScenarios}",
                        subtitle = "Cases Solved",
                        color = CyberGreen,
                        modifier = Modifier.weight(1f)
                    )
                    StatBox(
                        title = "Quizzes",
                        value = "${userStats.quizAttemptCount}",
                        subtitle = "Attempts",
                        color = BlueAccent,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Achievements & Badges
            item {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = WarningAmber,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "FIELD BADGES & HONORS",
                            color = WarningAmber,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    badges.forEach { badge ->
                        CyberCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            borderColor = if (badge.isUnlocked) WarningAmber.copy(alpha = 0.5f) else DarkCardBorder
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (badge.isUnlocked) badge.iconEmoji else "🔒",
                                    fontSize = 26.sp
                                )
                                Spacer(modifier = Modifier.width(14.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = badge.title,
                                        color = if (badge.isUnlocked) TextPrimary else TextMuted,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = badge.description,
                                        color = if (badge.isUnlocked) TextSecondary else TextMuted,
                                        fontSize = 12.sp
                                    )
                                }
                                if (badge.isUnlocked) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(WarningAmber.copy(alpha = 0.2f))
                                            .padding(horizontal = 8.dp, vertical = 3.dp)
                                    ) {
                                        Text(
                                            text = "UNLOCKED",
                                            color = WarningAmber,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Bookmarks Quick Access
            if (bookmarks.isNotEmpty()) {
                item {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = null,
                                tint = CyanAccent,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "SAVED BOOKMARKS (${bookmarks.size})",
                                color = CyanAccent,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        bookmarks.forEach { bm ->
                            CyberCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                onClick = {
                                    if (bm.itemType == "LESSON") {
                                        viewModel.navigateTo(Screen.LessonDetail(bm.itemId))
                                    } else if (bm.itemType == "TOOL") {
                                        viewModel.navigateTo(Screen.ToolDetail(bm.itemId))
                                    }
                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = bm.title,
                                            color = TextPrimary,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = bm.category,
                                            color = CyanAccent,
                                            fontSize = 11.sp
                                        )
                                    }
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null,
                                        tint = CyanAccent,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Reset Progress Section
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { showResetDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("reset_progress_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkSurfaceVariant
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = DangerRed,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Reset All Training Progress",
                        color = DangerRed,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            containerColor = DarkSurface,
            title = {
                Text(
                    text = "Reset Progress?",
                    color = DangerRed,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "This will erase your completed lessons, quiz scores, and solved practice scenarios from local storage. This action cannot be undone.",
                    color = TextPrimary,
                    fontSize = 13.sp
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.resetAllProgress()
                        showResetDialog = false
                    }
                ) {
                    Text("Reset Everything", color = DangerRed, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("Cancel", color = TextSecondary)
                }
            }
        )
    }
}

@Composable
fun StatBox(
    title: String,
    value: String,
    subtitle: String,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    CyberCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                color = color,
                fontSize = 22.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = title,
                color = TextPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                color = TextMuted,
                fontSize = 10.sp
            )
        }
    }
}
