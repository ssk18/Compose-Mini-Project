package com.ssk.composeminiprojects.study_app_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ssk.composeminiprojects.R
import com.ssk.composeminiprojects.ui.theme.ComposeMiniProjectsTheme
import com.ssk.composeminiprojects.ui.theme.StudyAppPrimary
import com.ssk.composeminiprojects.ui.theme.StudyAppSurface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyAppDetailsTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(StudyAppPrimary)
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.course_details),
                    style = MaterialTheme.typography.titleSmall,
                    color = StudyAppSurface,
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.navigate_back),
                        tint = StudyAppSurface
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = StudyAppPrimary
            )
        )
    }
}

@Preview
@Composable
fun StudyAppDetailsTopBarPreview() {
    ComposeMiniProjectsTheme {
        StudyAppDetailsTopBar(
            onBackClick = {}
        )
    }
}