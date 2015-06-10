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
        val Id: Int = getIntent().getExtras().getInt("task_id")
        val strTask: String? = getIntent().getStringExtra("task")
        taskEdit!!.setText(strTask)
        task = Select().from(javaClass<Task>())
                ?.where("${Task.ID} = ?", Id)
                ?.limit(1)
                ?.execute<Task>()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.task_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int? = item.getItemId()
        val taskId: Int = getIntent().getExtras().getInt("task_id")
        val task_ischecked: Boolean = getIntent().getBooleanExtra("task_ischecked", false)
        if( id == R.id.action_update) {
            val newText: String = taskEdit!!.getText().toString()
            // 更新処理
            updateTask(taskId, newText, task_ischecked)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateTask(id: Int, text: String, ischecked: Boolean) {
        if( TextUtils.isEmpty(text)) {
            return;
        }

        val date: Date = Date()

        // 更新
        val task: Task = Task.update(id, text, date, ischecked)
        task.save()

        // リスト画面に遷移
        val intent: Intent = Intent(this, javaClass<MainActivity>())
        startActivity(intent)
    }

}