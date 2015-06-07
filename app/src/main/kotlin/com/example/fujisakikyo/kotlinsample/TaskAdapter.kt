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
            view = LayoutInflater.from(getContext()).inflate(R.layout.task_list, null)
            holder = ViewHolder(view.findViewById(R.id.check_box) as CheckBox,
                                view.findViewById(R.id.created_at) as TextView,
                                view.findViewById(R.id.todo_text) as TextView)
            view?.setTag(holder)
        }
        holder = view?.getTag() as ViewHolder

        val item = getItem(position)
        holder?.created_at?.setText(FORMAT.format(item?.Created_at))
        holder?.todoText?.setText(item?.Content)
        holder?.checkBox?.setChecked(item.isChecked)

        return view
    }

    class ViewHolder ( val checkBox: CheckBox,
                       val created_at: TextView,
                       val todoText: TextView
    ){}
}