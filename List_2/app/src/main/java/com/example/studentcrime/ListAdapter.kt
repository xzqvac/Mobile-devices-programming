package com.example.studentcrime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ListAdapter : RecyclerView.Adapter<ListAdapter.CrimeViewHolder>() {

    private val crimeList: List<Crime> = Crimes.crimes

    class CrimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val crimeTitle: TextView = itemView.findViewById(R.id.crimeTitleView)
        val crimeIsSolved: ImageView = itemView.findViewById(R.id.crimeIsSolvedView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeViewHolder {
        return CrimeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun getItemCount() = crimeList.size

    override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
        val crime = crimeList[position]
        holder.crimeTitle.text = crime.title
        holder.crimeIsSolved.visibility = if (crime.is_solved) View.VISIBLE else View.INVISIBLE
        holder.itemView.setOnClickListener {
            val crimeInfo = String.format(
                "%s\n%s\nCommitted an offence by student %d \nThe crime is currently %s solved",
                crime.title,
                crime.description,
                crime.student_id,
                if (crime.is_solved) "" else "not"
            )
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(crimeInfo)
            holder.itemView.findNavController().navigate(action)
        }
    }

}