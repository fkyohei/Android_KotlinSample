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
        var strId: Int? = getIntent().getExtras().getInt("task_id")
        var strTask: String? = getIntent().getStringExtra("task")
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
        var taskId: Integer? = getIntent().getExtras().getInt("task_id") as Integer
        var task_ischecked: Boolean = getIntent().getBooleanExtra("task_ischecked", false)
        if( id == R.id.action_update) {
            var newText: String = taskEdit!!.getText().toString()
            // 更新処理
            updateTask(taskId, newText, task_ischecked)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateTask(id: Integer?, text: String, ischecked: Boolean) {
        if( TextUtils.isEmpty(text)) {
            return;
        }

        var date: Date = Date()

        var task: Task = Task.update(id, text, date, ischecked)
        task.save()

        var intent: Intent = Intent(this, javaClass<MainActivity>())
        startActivity(intent)
    }

}