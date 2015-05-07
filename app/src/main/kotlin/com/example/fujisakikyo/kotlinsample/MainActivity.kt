package com.example.fujisakikyo.kotlinsample

import android.content.Intent
import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.activeandroid.query.Select
import com.example.fujisakikyo.kotlinsample.model.Task


public class MainActivity : ActionBarActivity() {

    var todoAddButton: Button? = null
    var todoListView: ListView? = null
    var todoAddtask: EditText? = null
//    var taskAdapter: TaskAdapter? = null;
    var results: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAddButton = findViewById(R.id.addbtn) as Button
        todoListView = findViewById(R.id.listView) as ListView

        loadTodoList()
    }

    fun loadTodoList() {
        val taskadapter = TaskAdapter(this!!, { a, remain ->
            var from = Select().from(javaClass<Task>())

            from?.execute<Task>()
                ?.forEach {
                    a.add(it)
                }
        })

        todoListView?.setAdapter(taskadapter)


    }

    fun openTodoEdit(Task task) {
        val intent = Intent(this, javaClass<TaskEditActivity>())
        intent.putExtra("task", task.getTask())
        intent.putExtra("task_id", task.getId())
        startActivity(intent)
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
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
