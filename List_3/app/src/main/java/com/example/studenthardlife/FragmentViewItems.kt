package com.example.studenthardlife

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenthardlife.databinding.FragmentViewItemsBinding


class FragmentViewItems : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //private val binding by lazy { FragmentViewItemsBinding.inflate(layoutInflater)}
    private var _binding: FragmentViewItemsBinding? = null
    private val binding get() = _binding!!
    // error i think

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saveInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewItemsBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_view_items, container, false)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.tasksRecyclerView)
        val dbHandler by lazy { DBHandler(requireContext()) }
        binding.tasksRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = TaskAdapter(dbHandler, context)
        }
    }
}