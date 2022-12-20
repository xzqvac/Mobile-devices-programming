package com.example.studenthardlife

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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

        binding.tasksRecyclerView.adapter?.notifyItemInserted(dbHandler.getTasks().size)
        binding.tasksRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TaskAdapter(dbHandler, context)
        }

        binding.addTaskButton.setOnClickListener {
            val name = binding.editSubjectName.text.toString()
            val description = binding.editTasksDescription.text.toString()
            if (name.isNotEmpty() && description.isNotEmpty()){
                it.hideKeyboard()
                dbHandler.addTask(Task(name,description))
            }
        }
    }

    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onDestroyView() {
        dbHandler.close()
        super.onDestroyView()
        _binding = null
    }
}
