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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("debug", "MainActivity.onCreate")
        setContentView(R.layout.activity_main)
        todoAddButton = findViewById(R.id.addbtn) as Button
        todoAddButton!!.setOnClickListener { view ->
            var todoAddTask: EditText = findViewById(R.id.addTask) as EditText
            Log.d("debug", todoAddTask.getText().toString())
            if( todoAddTask.getText().toString() != "" ) {

                var newTask: Task = Task.create(todoAddTask!!.getText().toString())

                newTask.save()
                todoAddTask!!.setText("")
            }
            loadTodoList()
        }

        todoListView = findViewById(R.id.listView) as ListView

        loadTodoList()
    }

    fun loadTodoList() {
        Log.d("debug", "loadTodoList")
        var taskadapter = TaskAdapter(this, {a, remain ->
            var from = Select().from(javaClass<Task>())

            remain?.forEach {
                from = from?.where("${Task.ID}=?", it.getId())
            }
            from?.orderBy("${Task.CREATED_AT} desc")
                    ?.execute<Task>()
                    ?.forEach {
                        a.add(it)
                    }
        })

        todoListView?.setAdapter(taskadapter)

        todoListView?.setOnItemClickListener {
            parent, view, position, id ->
            val intent = Intent(this, javaClass<TaskEditActivity>())
            intent.putExtra("task", taskadapter.getItemId(position))
            intent.putExtra("task_id", taskadapter.getItemId(position))
            startActivity(intent)
        }

    }

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
        if (id == R.id.action_delete) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
