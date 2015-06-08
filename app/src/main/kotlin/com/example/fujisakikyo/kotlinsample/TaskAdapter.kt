package com.example.fujisakikyo.kotlinsample

import android.content.Context
import android.support.v7.internal.widget.AdapterViewCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.fujisakikyo.kotlinsample.model.Task
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by fujisakikyo on 15/05/07.
 */
public class TaskAdapter(context: Context, val loader: ((TaskAdapter, List<Task>?) -> Unit)) : ArrayAdapter<Task>(context, -1) {

    companion object {
        var FORMAT = SimpleDateFormat("yyyy/MM/dd HH:mm")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        Log.d("debug", "TaskAdapter.getView")
        var view: View? = convertView
        var holder: ViewHolder
        if( view == null) {
            Log.d("debug", "view=null")
//            view = LayoutInflater.from(getContext()).inflate(R.layout.task_list, null)
            view = View.inflate(getContext(), R.layout.task_list, null);
            holder = ViewHolder(view?.findViewById(R.id.check_box) as CheckBox,
                                view?.findViewById(R.id.created_at) as TextView,
                                view?.findViewById(R.id.todo_text) as TextView)
            view?.setTag(holder)
        }
        holder = view?.getTag() as ViewHolder

        val item = getItem(position)
        Log.d("debug", "TaskAdapter_getView")
        Log.d("debug", item.Content)
        Log.d("debug", FORMAT.format(item?.Created_at))
        holder?.todoText?.setText(item?.Content)
        holder?.created_at?.setText(FORMAT.format(item?.Created_at))
        holder?.checkBox?.setChecked(item.isChecked)

        return view
    }

    fun getCheckedTaskList(): ArrayList<Task> {
       val checkedTaskList = ArrayList<Task>()
        for(i in 0 .. getCount()) {
            var task: Task = getItem(i)
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