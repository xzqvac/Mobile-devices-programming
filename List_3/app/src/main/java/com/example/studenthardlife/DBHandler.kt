package com.example.studenthardlife

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context) : SQLiteOpenHelper (
    context, DATABASE_NAME, null, DATABASE_VERSION){
private companion object{
    private const val DATABASE_VERSION = 1
    private const val DATABASE_NAME = "studentsDBKotlin.db"
    private const val TABLE_TASKS = "TaskTable"

    private const val COLUMN_ID = "_id"
    private const val COLUMN_NAME = "name"
    private const val COLUMN_INDEX = "indexNumber"
}

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TASKS_TABLE =
            "CREATE TABLE $TABLE_TASKS(" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "$COLUMN_NAME TEXT," +
                    "$COLUMN_INDEX INTEGER)"
        db?.execSQL(CREATE_TASKS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TASKS")
        onCreate(db)
    }

    fun addTask(task: Task)
    {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, task.name)
        contentValues.put(COLUMN_INDEX, task.index)

        db.insert(TABLE_TASKS, null, contentValues)
        db.close()
    }

    fun deleteTask(task: Task)
    {
        val db = this.writableDatabase
        db.delete(
            TABLE_TASKS,
            "$COLUMN_ID=${task.id}",
            null
        )
        db.close()
    }

    fun updateTask (id: Int, name: String, index: Int){
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, name)
        contentValues.put(COLUMN_INDEX, index)

        db.update(TABLE_TASKS,
            contentValues,
            "$COLUMN_ID=$id",
            null)

        db.close()

    }

    fun getTasks(): List<Task> {
        val tasks: MutableList<Task> = ArrayList()

        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM $TABLE_TASKS", null)
        if (cursor.moveToFirst()) {
            do{
                tasks.add(Task(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2)))
            } while (cursor.moveToNext())
        }
        db.close()
        cursor.close()
        return tasks
    }
}