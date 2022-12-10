package com.example.studenthardlife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentDetailedView : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saveInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_view, container, false)

//        view.findViewById<FloatingActionButton>(R.id.DetailedView).setOnClickListener {
//            val action = FragmentDetailedViewDirections.actionFragmentDetailedViewToFragmentViewItems2()
//            Navigation.findNavController(view).navigate(action)
//        }
//        return view
    }
}