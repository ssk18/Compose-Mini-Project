package com.ssk.composeminiprojects.study_app_screen

import com.ssk.composeminiprojects.utils.LessonTopic
import com.ssk.composeminiprojects.utils.Topic

data class StudyAppState(
    val pinnedLessons: List<LessonTopic> = emptyList(),
    val selectedTopic: Topic? = null,
    val allLessons: List<LessonTopic> = emptyList(), // Original list, never modified
    val isPinned: Boolean = false,
    val categoryIndexMap: Map<String, Int> = emptyMap(),
    val scrollProgress: Float = 0f,
    val hasShownEndWarning: Boolean = false,
    val isAutoScrolling: Boolean = false
) {
    val lessons: List<LessonTopic>
        get() = allLessons.filter { lesson ->
            pinnedLessons.none { it.title == lesson.title }
        }
}