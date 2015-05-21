package com.example.fujisakikyo.kotlinsample

import android.content.Intent
import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.activeandroid.query.Select
import com.example.fujisakikyo.kotlinsample.model.Task

import java.util.Date
import java.util.Random


public class MainActivity : ActionBarActivity() {

    var todoAddButton: Button? = null
    var todoListView: ListView? = null
    var todoAddtask: EditText? = null
    var taskAdapter: TaskAdapter? = null;
    var results: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAddButton = findViewById(R.id.addbtn) as Button
        todoAddButton!!.setOnClickListener {
            var todoAddTask: EditText? = findViewById(R.id.addTask) as EditText

            if( todoAddtask!!.getText().toString() != "" ) {
                val current_date = Date()

                Log.d("debug", "insert_pre")
                var newTask: Task = Task()
                newTask.Content = todoAddTask!!.getText().toString()
                newTask.Created_at = current_date
                newTask.Lastupdated_at = current_date
                newTask.isChecked = false

                newTask.save()
                Log.d("debug", "insert_aft")
                todoAddTask!!.setText("")
            }
            loadTodoList()
        }

        todoListView = findViewById(R.id.listView) as ListView

        loadTodoList()
    }

    fun loadTodoList() {

        var listdata: List<Task>? = Select()?.from(javaClass<Task>())?.execute()
//        todoListView.setOnItemClickListener(TaskAdapter.onItemClickListener() {
//            fun onItemClick(task: Task) {
//                open
//            }
//        })

//        val taskadapter = TaskAdapter(this, TaskAdapter.OnItemClickListener() {
//            fun onItemClick(task: Task) {
//                openTodoEdit(task)
//            }
//        })

//        taskAdapter!!.setResult(listdata)
//        todoListView?.setAdapter(taskAdapter)


    }

    fun openTodoEdit(task: Task) {
        val intent = Intent(this, javaClass<TaskEditActivity>())
        intent.putExtra("task", task.Content)
        intent.putExtra("task_id", task.Id)
        startActivity(intent)
    }

//    fun addTaskList(view: View) {
//        var todoAddTask: EditText? = findViewById(R.id.addTask) as EditText
//
//        if( todoAddtask == null) {
//            return;
//        }
//
//        val current_date = Date()
//
//Log.d("debug", "insert_pre")
//        var newTask: Task = Task()
//        newTask.Content = todoAddTask as String
//        newTask.Created_at = current_date
//        newTask.Lastupdated_at = current_date
//        newTask.isChecked = false
//
//        newTask.save()
//Log.d("debug", "insert_aft")
//        todoAddTask!!.setText("")
//        loadTodoList()
//    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
