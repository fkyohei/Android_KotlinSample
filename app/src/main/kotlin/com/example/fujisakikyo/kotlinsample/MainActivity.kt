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
import com.activeandroid.query.Delete
import com.activeandroid.query.Select
import com.example.fujisakikyo.kotlinsample.model.Task
import java.util.*


public class MainActivity : ActionBarActivity() {

    var todoAddButton: Button? = null
    var todoListView: ListView? = null
    var taskadapter: TaskAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("debug", "MainActivity.onCreate")
        setContentView(R.layout.activity_main)

        // addボタンのクリックイベントを追加
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
Log.d("debug", "MainActivity_todoListView")
Log.d("debug", todoListView.toString())
        // リスト表示
        loadTodoList()
    }

    fun loadTodoList() {
        Log.d("debug", "loadTodoList")
        // データを取得して、リストに当て込む
        taskadapter = TaskAdapter(this, {a, remain ->
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

        // 各リストのクリックイベントを追加
        todoListView?.setOnItemClickListener {
            parent, view, position, id ->
            val intent = Intent(this, javaClass<TaskEditActivity>())
            intent.putExtra("task_id", taskadapter?.getItem(position)?.Id as Int)
            intent.putExtra("task", taskadapter?.getItem(position)?.Content)
            intent.putExtra("task_ischecked", taskadapter?.getItem(position)!!.isChecked)
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

        // チェックしたタスクを削除
        if (id == R.id.action_delete) {
            deleteCheckedTask()
        }

        return super.onOptionsItemSelected(item)
    }

    fun deleteCheckedTask() {
        // 複数削除用のチェックした項目を習得
        var checkedTaskList: ArrayList<Task>? = taskadapter?.getCheckedTaskList()
        checkedTaskList?.forEach {
            // 削除実行
             Delete().from(javaClass<Task>())
                    ?.where("${Task.ID} = ?", it.Id)
                    ?.execute<Task>()
        }

    }
}
