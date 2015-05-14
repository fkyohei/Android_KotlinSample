package com.example.fujisakikyo.kotlinsample.model

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import java.util.Date

/**
 * Created by fujisakikyo on 15/04/30.
 */

Table(name = Task.TABLE_NAME)
public class Task : Model() {
    companion  object {
        val TABLE_NAME = "Tasks"
        val ID = "Id"
        val CONTENT = "Content"
        val CREATED_AT = "Created_at"
        val LASTUPDETED_AT = "LastUpdated_at"
        val ISCHECKED = " isChecked"

        fun create(id: Int, content: String): Task {
            val date = Date()
            val task = Task()
            task.id = id
            task.content = content
            task.created_at = date
            task.lastupdated_at = date
            task.ischecked = false
            return task
        }
    }

    Column(name = ID)
    var id: Int? = 0

    Column(name = CONTENT)
    var content: String? = null

    Column(name = CREATED_AT)
    var created_at: Date? = null

    Column(name = LASTUPDETED_AT)
    var lastupdated_at: Date? = null

    Column(name = ISCHECKED)
    var ischecked: Boolean = false
}