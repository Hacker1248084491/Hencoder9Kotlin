package com.myt.lesson.entity

data class Lesson(val date: String?, val content: String?, val state: State?)
enum class State{
    PLAYBACK{
        override fun stateName() ="有回放"
    },
    LIVE{
        override fun stateName() ="正在直播"
    },
    WAIT{
        override fun stateName() ="等待中"
    };
    abstract fun stateName(): String
}