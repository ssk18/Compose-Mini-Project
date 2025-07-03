package com.ssk.composeminiprojects.study_app_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.composeminiprojects.ui.theme.ComposeMiniProjectsTheme
import com.ssk.composeminiprojects.ui.theme.StudyAppSurface
import com.ssk.composeminiprojects.utils.Topic

@Composable
fun TopicsRow(
    modifier: Modifier = Modifier,
    topics: List<Topic>,
    onClick: (Topic) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        userScrollEnabled = true,
    ) {
        items(
            items = topics,
            key = { topic -> topic.ordinal }
        ) {
            TopicCard(
                topic = it,
                onClick = { onClick(it) }
            )
        }
    }
}

@Composable
fun TopicCard(
    modifier: Modifier = Modifier,
    topic: Topic,
    onClick: (Topic) -> Unit
) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .height(40.dp)
            .background(
                color = StudyAppSurface,
                shape = RoundedCornerShape(100.dp)
            )
            .clickable {
                onClick.invoke(topic)
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = topic.name,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF090808)
@Composable
fun TopicsRowPreview() {
    ComposeMiniProjectsTheme {
        TopicsRow(
            topics = Topic.entries,
            onClick = {}
        )
    }
}

@Preview(showBackground = false)
@Composable
fun TopicCardPreview() {
    ComposeMiniProjectsTheme {
        TopicCard(topic = Topic.Science, onClick = {})
    }
}
