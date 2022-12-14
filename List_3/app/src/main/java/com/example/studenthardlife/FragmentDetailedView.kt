package com.example.studenthardlife

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.studenthardlife.databinding.DialogBinding

import kotlin.properties.Delegates

class FragmentDetailedView : Fragment() {
    private val dbHandler by lazy { DBHandler(requireContext()) }
    private var _binding: DialogBinding? = null
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
        view.findViewById<TextView>(R.id.text_view_name).text = name
        view.findViewById<TextView>(R.id.text_view_description).text = description

        val deleteButton: Button = view.findViewById(R.id.delete_task_button)
        deleteButton.setOnClickListener{

            dbHandler.deleteTask(Task(_id, name, description))
            val action = FragmentDetailedViewDirections.actionFragmentDetailedViewToFragmentViewItems()
            findNavController().navigate(action)
        }

        val editButton: Button = view.findViewById(R.id.edit_task_button)


        editButton.setOnClickListener{

            val dialogBinding = layoutInflater.inflate(R.layout.dialog, null)
            val myDialog = Dialog(requireContext())
            val buttonCancel: Button = dialogBinding.findViewById(R.id.button_cancel)
            val buttonEdit: Button = dialogBinding.findViewById(R.id.button_update)
            val nameUpdate = dialogBinding.findViewById<EditText>(R.id.edit_text_name_update)
            val descriptionUpdate = dialogBinding.findViewById<EditText>(R.id.edit_text_description_update)

            myDialog.setContentView(dialogBinding)
            myDialog.setCancelable(true)

            nameUpdate.setText(name)
            descriptionUpdate.setText(description)

            buttonEdit.setOnClickListener {
                dbHandler.updateTask(Task(_id, nameUpdate.toString(), descriptionUpdate.toString()))
                myDialog.dismiss()
            }
            buttonCancel.setOnClickListener { myDialog.dismiss()}

            myDialog.show()
        }
    }
}