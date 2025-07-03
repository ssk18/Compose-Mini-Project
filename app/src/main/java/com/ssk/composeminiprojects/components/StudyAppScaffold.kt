package com.ssk.composeminiprojects.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StudyAppScaffold(
    modifier: Modifier = Modifier,
    bottomBar: @Composable () -> Unit = {},
    topBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    snackBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        snackbarHost = snackBar,
        contentWindowInsets = WindowInsets.statusBars
    ) { padding ->
        content(padding)
    }
}