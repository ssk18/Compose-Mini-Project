package com.ssk.composeminiprojects.study_app_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.composeminiprojects.ui.theme.ComposeMiniProjectsTheme
import com.ssk.composeminiprojects.ui.theme.StudyAppPrimary

@Composable
fun EdgeToEdgeProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,

) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(2.dp)
    ) {
        LinearProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            color = StudyAppPrimary,
            trackColor = Color.Transparent,
        )
    }
}

@Preview
@Composable
fun EdgeToEdgeProgressBarPreview() {
    ComposeMiniProjectsTheme {
        EdgeToEdgeProgressBar(progress = 0.5f)
    }
}