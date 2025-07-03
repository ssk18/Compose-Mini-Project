package com.ssk.composeminiprojects.study_app_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.composeminiprojects.R
import com.ssk.composeminiprojects.ui.theme.ComposeMiniProjectsTheme
import com.ssk.composeminiprojects.ui.theme.StudyAppIcon
import com.ssk.composeminiprojects.ui.theme.StudyAppPrimary
import com.ssk.composeminiprojects.ui.theme.StudyAppPrimaryText
import com.ssk.composeminiprojects.ui.theme.StudyAppSurface
import com.ssk.composeminiprojects.utils.LessonTopic

@Composable
fun LessonItem(
    modifier: Modifier = Modifier,
    isPinned: Boolean,
    lessonTopic: LessonTopic,
    onClick: () -> Unit,
    onLessonClick: (String, String) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = StudyAppSurface,
        border = BorderStroke(
            width = 1.dp,
            color = if (isPinned) StudyAppPrimary else StudyAppSurface
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    onLessonClick(lessonTopic.title, lessonTopic.category)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = lessonTopic.title,
                style = MaterialTheme.typography.titleLarge,
                color = StudyAppPrimaryText,
                modifier = Modifier
                    .weight(1f)
            )
            IconButton(
                onClick = onClick
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = if (isPinned) R.drawable.filled_pin else R.drawable.hollow_pin),
                        contentDescription = null,
                        tint = if (isPinned) StudyAppPrimary else StudyAppIcon,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LessonItemPreview() {
    ComposeMiniProjectsTheme {
        LessonItem(
            isPinned = true,
            lessonTopic = LessonTopic(
                title = "Photosynthesis Basics",
                category = "Science"
            ),
            onClick = {},
            onLessonClick = { _, _ -> }
        )
    }
}