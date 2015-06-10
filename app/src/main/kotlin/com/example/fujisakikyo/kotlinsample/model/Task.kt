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
    companion object {
        val TABLE_NAME = "Tasks"
        val ID = "Id"
        val CONTENT = "content"
        val CREATED_AT = "created_at"
        val LASTUPDETED_AT = "lastupdated_at"
        val ISCHECKED = " ischecked"

        fun create(content: String): Task {
            val date = Date()
            val task = Task()
            task.Content = content
            task.Created_at = date
            task.Lastupdated_at = date
            task.isChecked = false
            return task
        }

        fun update(id: Int, content: String, created_at: Date, ischecked: Boolean): Task {
            val date = Date()
            val task = Task()
            task.Id = id
            task.Content = content
            task.Created_at = created_at
            task.Lastupdated_at = date
            task.isChecked = ischecked
            return task
        }
    }

    var Id: Int = 0

    Column(name = CONTENT)
    var Content: String? = null

    Column(name = CREATED_AT)
    var Created_at: Date? = null

    Column(name = LASTUPDETED_AT)
    var Lastupdated_at: Date? = null

    Column(name = ISCHECKED)
    var isChecked: Boolean = false
}