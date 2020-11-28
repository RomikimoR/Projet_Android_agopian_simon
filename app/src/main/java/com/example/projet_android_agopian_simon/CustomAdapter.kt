package com.example.projet_android_agopian_simon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView







class CustomAdapter(private var dataSet: ArrayList<Launch>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView;
        val date: TextView;

        init {
            name = view.findViewById(R.id.rocketName)
            date = view.findViewById(R.id.rocketDate)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(holder: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val v = LayoutInflater.from(holder.context).inflate(R.layout.rocket_row, holder, false)
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.name.text = dataSet[position].name
            holder.date.text = dataSet[position].date
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}