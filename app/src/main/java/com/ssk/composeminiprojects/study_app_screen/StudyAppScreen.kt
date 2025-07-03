@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.ssk.composeminiprojects.study_app_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ssk.composeminiprojects.R
import com.ssk.composeminiprojects.components.StudyAppScaffold
import com.ssk.composeminiprojects.components.StudySnackBar
import com.ssk.composeminiprojects.study_app_screen.components.EdgeToEdgeProgressBar
import com.ssk.composeminiprojects.study_app_screen.components.LessonItem
import com.ssk.composeminiprojects.study_app_screen.components.TopicsRow
import com.ssk.composeminiprojects.ui.theme.ComposeMiniProjectsTheme
import com.ssk.composeminiprojects.ui.theme.SetStatusBarIconsColor
import com.ssk.composeminiprojects.ui.theme.StudyAppOnSurface
import com.ssk.composeminiprojects.ui.theme.StudyAppPrimary
import com.ssk.composeminiprojects.ui.theme.StudyAppTertiaryText
import com.ssk.composeminiprojects.utils.ObserveAsEvents
import com.ssk.composeminiprojects.utils.Topic
import kotlinx.coroutines.launch

@Composable
fun StudyAppScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: StudyAppViewModel,
    navigateToDetails: (String, String) -> Unit
) {
    SetStatusBarIconsColor(darkIcons = true)
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val canScrollToTop by viewModel.canScrollToTop.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is StudyAppEvent.ShowSnackBar -> {
                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }

            is StudyAppEvent.NavigateToStudyDetails -> {
                navigateToDetails(event.title, event.category)
            }
        }
    }

    StudyAppScaffold(
        modifier = modifier,
        floatingActionButton = {
            if (canScrollToTop) {
                IconButton(
                    onClick = {
                        viewModel.scrollToTop(scope)
                    },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .size(80.dp)
                        .background(
                            shape = CircleShape,
                            color = StudyAppOnSurface
                        )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_up),
                        contentDescription = null,
                        tint = StudyAppPrimary
                    )
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                StudySnackBar(
                    hostState = snackBarHostState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .imePadding()
                        .navigationBarsPadding()
                )
            }
        },
    ) {
        StudyAppScreen(
            studyAppState = state,
            onAction = {
                viewModel.onAction(it, scope)
            },
            lazyListState = viewModel.lazyListState
        )
    }
}

@Composable
fun StudyAppScreen(
    modifier: Modifier = Modifier,
    studyAppState: StudyAppState,
    onAction: (StudyAppActions) -> Unit,
    lazyListState: LazyListState = rememberLazyListState()
) {
    LaunchedEffect(studyAppState.pinnedLessons.size) {
        if (studyAppState.pinnedLessons.isNotEmpty()) {
            lazyListState.scrollToItem(0)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = StudyAppOnSurface
            )
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
    ) {
        Spacer(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing))
        TopicsRow(
            topics = Topic.entries,
            onClick = { topic ->
                onAction(StudyAppActions.ScrollToCategory(topic))
            }
        )
        Spacer(Modifier.height(12.dp))
        EdgeToEdgeProgressBar(
            progress = studyAppState.scrollProgress
        )
        Spacer(Modifier.height(40.dp))
        
        // Fixed pinned items section (non-scrollable)
        if (studyAppState.pinnedLessons.isNotEmpty()) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                studyAppState.pinnedLessons.forEach { lesson ->
                    LessonItem(
                        isPinned = true,
                        lessonTopic = lesson,
                        onClick = {
                            onAction(StudyAppActions.OnLessonUnpinned(lesson))
                        },
                        onLessonClick = { title, category ->
                            onAction(StudyAppActions.OnLessonClicked(title, category))
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        
        // Scrollable lessons section
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            val groupedLessons = studyAppState.lessons.groupBy { it.category }
            val categoryIndexMap = mutableMapOf<String, Int>()
            var currentIndex = 0

            groupedLessons.entries.forEachIndexed { index, entry ->
                val category = entry.key
                val categoryLessons = entry.value

                if (index > 0) {
                    item(key = "spacer_$category") {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    currentIndex++
                }

                categoryIndexMap[category] = currentIndex
                stickyHeader(key = category) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(StudyAppOnSurface),
                    ) {
                        Text(
                            text = category,
                            style = MaterialTheme.typography.bodySmall,
                            color = StudyAppTertiaryText
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                currentIndex++

                items(
                    items = categoryLessons,
                ) { lesson ->
                    LessonItem(
                        isPinned = lesson.isPinned,
                        lessonTopic = lesson,
                        onClick = {
                            onAction(StudyAppActions.OnLessonPinned(lesson))
                        },
                        onLessonClick = { title, category ->
                            onAction(StudyAppActions.OnLessonClicked(title, category))
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                currentIndex += categoryLessons.size
            }
            onAction(StudyAppActions.UpdateCategoryIndexMap(categoryIndexMap))
        }
    }
}

@Preview
@Composable
fun StudyAppScreenPreview() {
    ComposeMiniProjectsTheme {
        StudyAppScreen(
            studyAppState = StudyAppState(),
            onAction = {}
        )
    }
}
