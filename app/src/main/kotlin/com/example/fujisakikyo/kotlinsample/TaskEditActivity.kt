package com.example.fujisakikyo.kotlinsample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.activeandroid.query.Select
import com.example.fujisakikyo.kotlinsample.model.Task
import java.util
import java.util.ArrayList
import java.util.Date

/**
 * Created by fujisakikyo on 15/05/07.
 */
public class TaskEditActivity : ActionBarActivity() {
    var task: kotlin.List<Task>? = null
    var taskEdit: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_edit)
        taskEdit = findViewById(R.id.task_edit) as EditText
        setupUi();
    }

    fun setupUi() {
        var strTask: String? = getIntent().getStringExtra("task")
        var strId: Int? = getIntent().getExtras().getInt("task_id")
        taskEdit!!.setText(strTask)
        task = Select().from(javaClass<Task>())
                ?.where("${Task.ID} = ?", strId)
                ?.limit(1)
                ?.execute<Task>()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.task_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id: Int? = item.getItemId()
        if( id == R.id.action_update) {
            var newText: String = taskEdit!!.getText().toString()
//            updateTask(task!!.Id, newText, task!!.isChecked)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateTask(id: Int?, text: String, ischecked: Boolean) {
        if( TextUtils.isEmpty(text)) {
            return;
        }

        var date: Date = Date()

//        var newTask = Select().from(javaClass<Task>())
//                                ?.where("${Task.ID} = ?", id)
//                                ?.limit(1)
//                                ?.execute()!!
//
//        newTask!!.content = text
//        newTask!!.lastupdated_at = date
//        newTask!!.ischecked = ischecked
//        newTask!!.save()

        var intent: Intent = Intent(this, javaClass<MainActivity>())
        startActivity(intent)

    }

}