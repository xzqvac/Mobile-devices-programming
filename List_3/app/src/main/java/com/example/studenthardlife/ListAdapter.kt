package com.example.studenthardlife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<ListAdapter.TasksViewHolder>() {
    private val list = ('A').rangeTo('Z').toList()

    class TasksViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val taskTitle: TextView = itemView.findViewById(R.id.taskTitleView)
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        return TasksViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_tasks, parent, false))
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val item = list[position]
        holder.taskTitle.text = item.toString()
        holder.itemView.setOnClickListener {
            val taskInfo = String.format(
                "Task",
            )
            val action = FragmentViewItemsDirections.actionFragmentViewItemsToFragmentDetailedView2()
            holder.itemView.findNavController().navigate(action)


        }
    }
}