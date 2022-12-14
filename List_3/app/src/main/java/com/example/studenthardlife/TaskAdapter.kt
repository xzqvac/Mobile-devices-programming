package com.example.studenthardlife

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.studenthardlife.databinding.DialogBinding
import com.example.studenthardlife.databinding.ViewTasksBinding

class TaskAdapter(private val dbHandler: DBHandler, private val context: Context) :
    RecyclerView.Adapter<TaskAdapter.TasksViewHolder>()
{
    inner class TasksViewHolder(private val itemBinding: ViewTasksBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Task) {
            itemBinding.textViewName.text = item.name
            itemBinding.textViewIndex.text = item.description.toString()
            itemBinding.textViewId.text = item.id.toString()
        }

//        private fun setupDialog(item: Task) {
//            val dialog = Dialog(context)
//            val dialogBinding = DialogBinding.inflate(LayoutInflater.from(context))
//            dialog.apply {
//                setCancelable(false)
//                setContentView(dialogBinding.root)
//            }
//
//            dialogBinding.apply {
//                editTextIndexUpdate.setText(item.description.toString())
//                editTextNameUpdate.setText(item.name)
//                buttonUpdate.setOnClickListener {
//                    updateDialog(dialogBinding, item, dialog)
//                }
//
//                buttonCancel.setOnClickListener { dialog.dismiss() }
//            }
//            dialog.show()
//        }

//        private fun updateDialog(
//            dialogBinding: DialogBinding,
//            item: Task,
//            dialog: Dialog
//        ){
//            val updateName = dialogBinding.editTextNameUpdate.text.toString()
//            val updateIndex = dialogBinding.editTextIndexUpdate.text.toString()
//
//            if (updateName.isNotEmpty() && updateIndex.isNotEmpty()) {
//                dbHandler.updateTask(Task(item.id, item.name, item.description))
//                notifyItemChanged(item.id - 1)
//                dialog.dismiss()
//            }
//        }
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
        val task = dbHandler.getTasks()[position]
        holder.bind(task)
        holder.itemView.setOnClickListener{
            val taskInfo = String.format(
                "%s\n%s",
                task.name,
                task.description,
            )
        val bundle = bundleOf()
            bundle.putInt("ID", task.id)
            bundle.putString("Name", task.name)
            bundle.putString("Description", task.description)
            holder.itemView.findNavController().navigate(R.id.action_fragmentViewItems_to_fragmentDetailedView7, bundle)
        }
    }
}

