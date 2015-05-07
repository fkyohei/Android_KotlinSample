package com.example.fujisakikyo.kotlinsample

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.fujisakikyo.kotlinsample.model.Task
import java.text.SimpleDateFormat

/**
 * Created by fujisakikyo on 15/05/07.
 */
public class TaskAdapter(context: Context, val loader: ((TaskAdapter, List<Task>?) -> Unit)) : ArrayAdapter<Task>(context, -1) {

    companion object {
        var FORMAT = SimpleDateFormat("yyyy/MM/dd HH:mm")
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup?): View? {
        var view: View? = convertView
        if( view == null) {
            view = View.inflate(getContext()!!, R.layout.task_list, null)
            view?.setTag(ViewHolder(view!!))
        }
        var holder: ViewHolder? = view?.getTag() as ViewHolder
        val item = getItem(position)
        if( item?.created_at != null) {
            holder?.created_at?.setText(FORMAT.format(item?.created_at))
        }
        holder?.todoText?.setText(item?.content)
        holder?.checkBox?.setChecked(item.ischecked)

        return view
    }

    class ViewHolder(root: View) {

        var checkBox: CheckBox? = null
        var created_at: TextView? = null
        var todoText: TextView? = null

        {
            checkBox = root.findViewById(R.id.check_box) as CheckBox
            created_at = root.findViewById(R.id.created_at) as TextView
            todoText = root.findViewById(R.id.todo_text) as TextView
        }
    }
}