package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Router
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DifficultyLevel
import com.example.ui.theme.BlueAccent
import com.example.ui.theme.BluePrimary
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
import com.example.ui.viewmodel.Screen

@Composable
fun CyberCard(
    modifier: Modifier = Modifier,
    borderColor: Color = DarkCardBorder,
    glowEffect: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val borderBrush = if (glowEffect) {
        Brush.horizontalGradient(listOf(CyanAccent.copy(alpha = 0.6f), BlueAccent.copy(alpha = 0.3f)))
    } else {
        Brush.linearGradient(listOf(borderColor, borderColor.copy(alpha = 0.5f)))
    }

    Card(
        modifier = modifier
            .border(
                BorderStroke(1.dp, borderBrush),
                RoundedCornerShape(16.dp)
            )
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkSurface
        )
    ) {
        content()
    }
}

@Composable
fun ITTopBar(
    title: String,
    subtitle: String? = null,
    canNavigateBack: Boolean = false,
    onBackClick: () -> Unit = {},
    actions: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBackground)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (canNavigateBack) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(DarkSurfaceVariant)
                    .testTag("top_bar_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = CyanAccent
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    color = CyanAccent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        actions?.invoke()
    }
}

@Composable
fun ITBottomNavBar(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    NavigationBar(
        containerColor = DarkSurface,
        tonalElevation = 8.dp
    ) {
        val navItems = listOf(
            Triple(Screen.Home, "Home", Icons.Default.Home),
            Triple(Screen.Learn, "Learn", Icons.Default.MenuBook),
            Triple(Screen.Practice, "Practice", Icons.Default.Computer),
            Triple(Screen.Tools, "Tools", Icons.Default.Build),
            Triple(Screen.Profile, "Profile", Icons.Default.Person)
        )

        navItems.forEach { (screen, label, icon) ->
            val isSelected = when (currentScreen) {
                screen -> true
                is Screen.CategoryLessons -> screen == Screen.Learn
                is Screen.LessonDetail -> screen == Screen.Learn
                is Screen.ScenarioDetail -> screen == Screen.Practice
                is Screen.Quiz -> screen == Screen.Practice
                is Screen.ToolDetail -> screen == Screen.Tools
                else -> false
            }

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(screen) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = CyanAccent,
                    selectedTextColor = CyanAccent,
                    unselectedIconColor = TextMuted,
                    unselectedTextColor = TextMuted,
                    indicatorColor = CyanAccent.copy(alpha = 0.15f)
                ),
                modifier = Modifier.testTag("nav_${label.lowercase()}")
            )
        }
    }
}

@Composable
fun DifficultyBadge(difficulty: DifficultyLevel) {
    val (bgColor, textColor) = when (difficulty) {
        DifficultyLevel.BEGINNER -> CyberGreen.copy(alpha = 0.2f) to CyberGreen
        DifficultyLevel.INTERMEDIATE -> WarningAmber.copy(alpha = 0.2f) to WarningAmber
        DifficultyLevel.ADVANCED -> DangerRed.copy(alpha = 0.2f) to DangerRed
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = difficulty.label,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun HazardWarningBanner(
    text: String,
    isDataDestructive: Boolean
) {
    val borderCol = if (isDataDestructive) DangerRed else WarningAmber
    val bgCol = if (isDataDestructive) DangerRed.copy(alpha = 0.12f) else WarningAmber.copy(alpha = 0.10f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(bgCol)
            .border(BorderStroke(1.dp, borderCol.copy(alpha = 0.5f)), RoundedCornerShape(12.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = if (isDataDestructive) "Data Hazard" else "Warning",
            tint = borderCol,
            modifier = Modifier.size(26.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = if (isDataDestructive) "CRITICAL DATA HAZARD" else "IMPORTANT TECHNICIAN WARNING",
                color = borderCol,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = text,
                color = TextPrimary,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun CodeCommandBox(
    command: String,
    label: String = "COMMAND / SHORTCUT"
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF030712))
            .border(BorderStroke(1.dp, BluePrimary.copy(alpha = 0.4f)), RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Column {
            Text(
                text = label,
                color = CyanAccent,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = command,
                color = Color(0xFF67E8F9),
                fontFamily = FontFamily.Monospace,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun CategoryIcon(key: String, tint: Color = CyanAccent, size: Int = 24) {
    val icon: ImageVector = when (key) {
        "windows" -> Icons.Default.Computer
        "hardware" -> Icons.Default.Build
        "printer" -> Icons.Default.Print
        "cctv" -> Icons.Default.Security
        "network" -> Icons.Default.Router
        "tools" -> Icons.Default.Build
        else -> Icons.Default.Build
    }
    Icon(
        imageVector = icon,
        contentDescription = key,
        tint = tint,
        modifier = Modifier.size(size.dp)
    )
}
