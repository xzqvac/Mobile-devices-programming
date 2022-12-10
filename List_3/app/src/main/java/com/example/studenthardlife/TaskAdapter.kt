package com.example.studenthardlife

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studenthardlife.databinding.DialogBinding
import com.example.studenthardlife.databinding.ViewTasksBinding

class TaskAdapter(private val dbHandler: DBHandler, private val context: Context) :
    RecyclerView.Adapter<TaskAdapter.TasksViewHolder>()
{
    inner class TasksViewHolder(private val itemBinding: ViewTasksBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        //val taskTitle: TextView = itemView.findViewById(R.id.taskTitleView)
        fun bind(item: Task) {
            itemBinding.textViewName.text = item.name
            itemBinding.textViewIndex.text = item.index.toString()
            itemBinding.textViewId.text = item.id.toString()

            itemBinding.imageViewDelete.setOnClickListener {
                dbHandler.deleteTask(item)
                notifyItemRemoved(adapterPosition)
            }
            itemBinding.imageViewEdit.setOnClickListener { setupDialog(item) }
        }

        private fun setupDialog(item: Task) {
            val dialog = Dialog(context)
            val dialogBinding = DialogBinding.inflate(LayoutInflater.from(context))
            dialog.apply {
                setCancelable(false)
                setContentView(dialogBinding.root)
            }

            dialogBinding.apply {
                editTextIndexUpdate.setText(item.index.toString())
                editTextNameUpdate.setText(item.name)
                buttonUpdate.setOnClickListener {
                    updateDialog(dialogBinding, item, dialog)
                }

                buttonCancel.setOnClickListener { dialog.dismiss() }
            }
            dialog.show()
        }

        private fun updateDialog(
            dialogBinding: DialogBinding,
            item: Task,
            dialog: Dialog
        ){
            val updateName = dialogBinding.editTextNameUpdate.text.toString()
            val updateIndex = dialogBinding.editTextIndexUpdate.text.toString()

            if (updateName.isNotEmpty() && updateIndex.isNotEmpty()) {
                dbHandler.updateTask(item.id, updateName, updateIndex.toInt())
                notifyItemChanged(item.id - 1)
                dialog.dismiss()
            }
        }
    }

    override fun getItemCount() = dbHandler.getTasks().size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TasksViewHolder {
        val itemBinding = ViewTasksBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: TasksViewHolder, position: Int){
        val item = dbHandler.getTasks()[position]
        holder.bind(item)
    }
}

/*
package com.example.studenthardlife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TasksViewHolder>() {
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
}*/
