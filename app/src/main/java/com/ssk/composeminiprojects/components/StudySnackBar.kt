package com.ssk.composeminiprojects.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ssk.composeminiprojects.ui.theme.StudyAppPrimary
import com.ssk.composeminiprojects.ui.theme.StudyAppSurface

@Composable
fun StudySnackBar(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState,
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier,
    ) { snackBarData ->
        StudyAppEventBanner(
            title = snackBarData.visuals.message
        )
    }
}

@Composable
fun StudyAppEventBanner(
    modifier: Modifier = Modifier,
    title: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(StudyAppPrimary)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = StudyAppSurface
        )
    }
}