package com.myt.lesson

import com.google.gson.reflect.TypeToken
import com.myt.base.core.http.EntityCallback
import com.myt.base.core.http.HttpClient
import com.myt.base.core.utils.Utils
import com.myt.lesson.entity.Lesson
import com.myt.lesson.entity.State
import java.lang.reflect.Type

class LessonPresenter(private val activity: LessonActivity) {

    private var lessons = listOf<Lesson>()

    private val type: Type = object :TypeToken<List<Lesson>>(){}.type

    fun fetchData(){
        HttpClient.get(LESSON_PATH, type, object: EntityCallback<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                lessons = entity
                activity.runOnUiThread {
                    activity.showResult(lessons)
                }
            }
            override fun onFailure(message: String?) {
                activity.runOnUiThread {
                    Utils.toast(message ?: "错误字段为空")
                }
            }
        })
    }

    fun showPlayback(){
        activity.showResult(lessons.filter { it.state == State.PLAYBACK })
    }

    companion object{
        const val LESSON_PATH = "lessons"
    }

}