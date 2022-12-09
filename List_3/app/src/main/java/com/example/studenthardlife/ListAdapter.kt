package com.example.studenthardlife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<ListAdapter.TasksViewHolder>() {
    private val list = ('A').rangeTo('Z').toList()

    class TasksViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.button_item)
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        return TasksViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_tasks, parent, false))
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val item = list[position]
        holder.button.text = item.toString()
    }
}