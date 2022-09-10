package com.krissmile31.myleague.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krissmile31.myleague.databinding.ItemLeagueBinding
import com.krissmile31.myleague.model.League

class LeagueAdapter(private var leagueList: ArrayList<League>, private var context: Context):
    RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemLeagueBinding) : RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of ItemLeagueBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val binding = ItemLeagueBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    // bind the items with each item of the list languageList which than will be
    // shown in recycler view
    // to keep it simple we are not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(leagueList[position]){
                Glide.with(context)
                    .load(this.icon)
                    .into(binding.imgLeague)
                binding.tvLeague.text = this.name
                // since this is inside "expandedView" its visibility will be gone initially
                // after click on the item we will make the visibility of the "expandedView" visible
                // which will also make the visibility of desc also visible
                // check if boolean property "extend" is true or false
                // if it is true make the "extendedView" Visible
                binding.tvLeague.visibility = if (this.expand) View.VISIBLE else View.GONE
                // on Click of the item take parent card view in our case
                // revert the boolean "expand"
                binding.imgLeague.setOnClickListener {
                    this.expand = !this.expand
                    notifyDataSetChanged()
                }
                this.expand = false
            }
        }
    }
    // return the size of languageList
    override fun getItemCount(): Int {
        return leagueList.size
    }
}
