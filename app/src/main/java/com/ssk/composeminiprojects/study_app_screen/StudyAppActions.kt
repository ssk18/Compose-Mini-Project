package com.ssk.composeminiprojects.study_app_screen

import com.ssk.composeminiprojects.utils.LessonTopic
import com.ssk.composeminiprojects.utils.Topic

sealed interface StudyAppActions {
    data class OnTopicSelected(val topic: Topic): StudyAppActions
    data class OnLessonPinned(val lesson: LessonTopic): StudyAppActions
    data class OnLessonUnpinned(val lesson: LessonTopic): StudyAppActions
    data class ScrollToCategory(val topic: Topic): StudyAppActions
    data class UpdateCategoryIndexMap(val map: Map<String, Int>): StudyAppActions
    data class OnLessonClicked(val lesson: String, val topic: String): StudyAppActions
}