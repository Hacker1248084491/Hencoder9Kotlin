package com.myt.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myt.base.core.BaseViewHolder
import com.myt.lesson.entity.Lesson
import com.myt.lesson.entity.State

class LessonAdapter: RecyclerView.Adapter<LessonAdapter.LessonViewHolder>(){

    private var list = listOf<Lesson>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = LessonViewHolder.onCreate(parent)

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    fun updateAndNotify(list: List<Lesson>){
        this.list = list
        notifyDataSetChanged()
    }


    class LessonViewHolder(itemView: View): BaseViewHolder(itemView) {
        companion object{
            fun onCreate(parent: ViewGroup) =
                LessonViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_lesson, parent, false)
                )

        }

        fun onBind(lesson: Lesson){
            setText(R.id.tv_date, lesson.date ?: "日期待定")
            setText(R.id.tv_content, lesson.content)
            lesson.state?.let {
                setText(R.id.tv_state, it.stateName())
                val colorRes = when(it){
                    State.PLAYBACK -> R.color.playback
                    State.LIVE -> R.color.live
                    State.WAIT -> R.color.wait
                }
                getView<TextView>(R.id.tv_state).setBackgroundColor(itemView.context.getColor(colorRes))
            }
        }
    }
}