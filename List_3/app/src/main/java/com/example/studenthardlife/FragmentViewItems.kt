package com.example.studenthardlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenthardlife.databinding.FragmentViewItemsBinding


class FragmentViewItems : Fragment() {

    private val dbHandler by lazy { DBHandler(requireContext()) }
    private var _binding: FragmentViewItemsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saveInstanceState: Bundle?
    ): View {
        _binding = FragmentViewItemsBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_view_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addTaskButton.setOnClickListener {
            val name = binding.editSubjectName.text.toString()
            val description = binding.editTasksDescription.text.toString()

            if (name.isNotEmpty() && description.isNotEmpty()){
                dbHandler.addTask(Task(name,description))
            }
        }

        binding.tasksRecyclerView.adapter?.notifyItemInserted(dbHandler.getTasks().size)
        binding.tasksRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TaskAdapter(dbHandler, context)
        }
    }

    override fun onDestroyView() {
        dbHandler.close()
        super.onDestroyView()
        _binding = null
    }
}
