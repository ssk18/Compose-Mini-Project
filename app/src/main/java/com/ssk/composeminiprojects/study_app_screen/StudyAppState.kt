package com.ssk.composeminiprojects.study_app_screen

import com.ssk.composeminiprojects.utils.LessonTopic
import com.ssk.composeminiprojects.utils.Topic

data class StudyAppState(
    val pinnedLessons: List<LessonTopic> = emptyList(),
    val selectedTopic: Topic? = null,
    val lessons: List<LessonTopic> = emptyList(),
    val isPinned: Boolean = false,
    val categoryIndexMap: Map<String, Int> = emptyMap(),
    val scrollProgress: Float = 0f
)