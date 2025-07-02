package com.ssk.composeminiprojects.study_app_screen

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssk.composeminiprojects.study_app_screen.StudyAppEvent.*
import com.ssk.composeminiprojects.utils.lessonTopics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudyAppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(StudyAppState())
    val uiState: StateFlow<StudyAppState> = _uiState
        .onStart { loadLessons() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = StudyAppState()
        )

    val lazyListState = LazyListState()

    val canScrollToTop = snapshotFlow { lazyListState.firstVisibleItemIndex }
        .map { index ->
            index >= 10
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    private val _eventChannel = Channel<StudyAppEvent>()
    val events = _eventChannel.receiveAsFlow()

    private fun loadLessons() {
        _uiState.update {
            it.copy(
                lessons = lessonTopics
            )
        }
        startScrollProgressTracking()
    }

    private fun startScrollProgressTracking() {
        viewModelScope.launch {
            snapshotFlow { calculateScrollProgress() }
                .distinctUntilChanged()
                .collect { progress ->
                    _uiState.update {
                        it.copy(scrollProgress = progress)
                    }
                }
        }
    }

    private fun calculateScrollProgress(): Float {
        val layoutInfo = lazyListState.layoutInfo
        val totalItems = layoutInfo.totalItemsCount
        val visibleItems = layoutInfo.visibleItemsInfo

        return when {
            totalItems <= 1 -> 0f
            visibleItems.isEmpty() -> 0f
            else -> {
                val firstVisibleIndex = visibleItems.first().index
                val lastVisibleIndex = visibleItems.last().index
                if (lastVisibleIndex == totalItems - 1) {
                    1f
                } else {
                    firstVisibleIndex.toFloat() / (totalItems - 1).toFloat()
                }
            }
        }
    }

    fun onAction(action: StudyAppActions, uiScope: CoroutineScope) {
        when (action) {
            is StudyAppActions.OnLessonPinned -> {
                val pinnedItems = _uiState.value.pinnedLessons
                if (pinnedItems.size < 5) {
                    _uiState.update {
                        it.copy(
                            pinnedLessons = it.pinnedLessons.plus(action.lesson),
                            lessons = it.lessons.minus(action.lesson),
                            isPinned = true
                        )
                    }
                } else {
                    _eventChannel.trySend(ShowSnackBar("You can only pin 5 lessons"))
                }
            }

            is StudyAppActions.OnTopicSelected -> {
                _uiState.update {
                    it.copy(
                        selectedTopic = action.topic
                    )
                }
            }

            is StudyAppActions.OnLessonUnpinned -> {
                _uiState.update {
                    it.copy(
                        pinnedLessons = it.pinnedLessons.minus(action.lesson),
                        lessons = it.lessons.plus(action.lesson),
                        isPinned = false
                    )
                }
            }

            is StudyAppActions.ScrollToCategory -> {
                val targetCategory = action.topic.toCategoryString()
                val targetIndex = _uiState.value.categoryIndexMap[targetCategory]

                if (targetIndex != null) {
                    viewModelScope.launch {
                        lazyListState.scrollToItem(targetIndex)
                    }
                }

                _uiState.update {
                    it.copy(selectedTopic = action.topic)
                }
            }

            is StudyAppActions.UpdateCategoryIndexMap -> {
                _uiState.update {
                    it.copy(categoryIndexMap = action.map)
                }
            }
        }
    }

    fun scrollToTop(uiScope: CoroutineScope) {
        viewModelScope.launch {
            withContext(uiScope.coroutineContext) {
                lazyListState.animateScrollToItem(0)
            }
        }
    }

}