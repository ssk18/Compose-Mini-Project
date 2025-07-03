package com.ssk.composeminiprojects.study_app_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.composeminiprojects.components.StudyAppScaffold
import com.ssk.composeminiprojects.study_app_details.components.StudyAppDetailsTopBar
import com.ssk.composeminiprojects.ui.theme.ComposeMiniProjectsTheme
import com.ssk.composeminiprojects.ui.theme.SetStatusBarIconsColor
import com.ssk.composeminiprojects.ui.theme.StudyAppPrimaryText
import com.ssk.composeminiprojects.ui.theme.TitleCardColor
import com.ssk.composeminiprojects.ui.theme.TitleTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyAppDetailsScreen(
    modifier: Modifier = Modifier,
    title: String,
    topic: String,
    onBackClick: () -> Unit
) {
    SetStatusBarIconsColor(darkIcons = false)

    StudyAppScaffold(
        modifier = modifier,
        topBar = {
            StudyAppDetailsTopBar(
                onBackClick = onBackClick
            )
        }
    ) {
        StudyAppDetailsContent(
            title = title,
            topic = topic,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun StudyAppDetailsContent(
    modifier: Modifier = Modifier,
    title: String,
    topic: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = StudyAppPrimaryText,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        TitleCard(
            title = topic
        )
    }
}

@Composable
fun TitleCard(
    modifier: Modifier = Modifier,
    title: String
) {
    Surface(
        modifier = modifier
            .wrapContentSize(),
        shape = RoundedCornerShape(16.dp),
        color = TitleCardColor
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = TitleTextColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
fun StudyAppDetailsScreenPreview() {
    ComposeMiniProjectsTheme {
        StudyAppDetailsScreen(
            onBackClick = {},
            title = "Android Development",
            topic = "Kotlin"
        )
    }
}