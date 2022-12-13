package com.example.studenthardlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.studenthardlife.databinding.FragmentViewItemsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.properties.Delegates

class FragmentDetailedView : Fragment() {
    private val dbHandler by lazy { DBHandler(requireContext()) }
    private var _binding: FragmentViewItemsBinding? = null
    private val binding get() = _binding!!

    private lateinit var name: String
    private lateinit var description: String
    private var _id by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getString("Name").toString()
        description = arguments?.getString("Description").toString()
        _id = arguments?.getInt("ID")!!

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saveInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.text_view_index).text = description
        view.findViewById<TextView>(R.id.text_view_name).text = name

        val deleteButton: Button = view.findViewById(R.id.delete_task_button)
        deleteButton.setOnClickListener{

            dbHandler.deleteTask(Task(_id, name, description))
            val action = FragmentDetailedViewDirections.actionFragmentDetailedViewToFragmentViewItems()
            findNavController().navigate(action)
        }
    }
}