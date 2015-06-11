package com.example.fujisakikyo.kotlinsample

import android.content.Context
import android.support.v7.internal.widget.AdapterViewCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.activeandroid.query.Select
import com.example.fujisakikyo.kotlinsample.model.Task
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by fujisakikyo on 15/05/07.
 */
public class TaskAdapter(context: Context) : ArrayAdapter<Task>(context, -1) {

    companion object {
        val FORMAT = SimpleDateFormat("yyyy/MM/dd HH:mm")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var view: View? = convertView
        var holder: ViewHolder

        if( view == null) {
            view = View.inflate(getContext(), R.layout.task_list, null);
            holder = ViewHolder(view?.findViewById(R.id.check_box) as CheckBox,
                                view?.findViewById(R.id.created_at) as TextView,
                                view?.findViewById(R.id.todo_text) as TextView)
            view?.setTag(holder)
        } else {
            holder = view?.getTag() as ViewHolder
        }

        // 表示する要素をリストにセット
        val item = getItem(position)
        holder?.todoText?.setText(item?.Content)
        holder?.created_at?.setText(FORMAT.format(item?.Lastupdated_at))
        holder?.checkBox?.setChecked(item.isChecked)

        // リスト各要素のチェックボックスのクリックイベントを追加
        holder?.checkBox?.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton, ischecked: Boolean) {
                val date: Date = Date()
                val taskId: Integer = Integer(item.getId().toString())
                val task: kotlin.List<Task>? = Select().from(javaClass<Task>())
                        ?.where("${Task.ID} = ?", taskId)
                        ?.limit(1)
                        ?.execute<Task>()

                // 更新
                task?.first()?.isChecked = ischecked
                task?.first()?.Lastupdated_at = date
                task?.first()?.save()
            }
        })
        return view
    }

    fun getCheckedTaskList(): ArrayList<Task> {
       val checkedTaskList = ArrayList<Task>()
        for(i in 0 .. getCount()-1) {
            val task: Task = getItem(i)
            if( task.isChecked) {
                checkedTaskList.add(task)
            }
        }
        return checkedTaskList
    }

    class ViewHolder ( val checkBox: CheckBox,
                       val created_at: TextView,
                       val todoText: TextView
    ){}
}