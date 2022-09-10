package com.krissmile31.myleague.adapter

import com.krissmile31.myleague.databinding.ItemRankBinding
import com.krissmile31.myleague.model.Rank

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RankAdapter(private var rankList: List<Rank>):
    RecyclerView.Adapter<RankAdapter.RankViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankViewHolder {
        val context = parent.context
        val itemBinding = ItemRankBinding.inflate(LayoutInflater.from(context), parent, false)
        return RankViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: RankViewHolder, position: Int) {
        // get rank list from top 4, the fourth position in list
        holder.bind(rankList[position], position+1)
    }

    override fun getItemCount(): Int {
        return rankList.size   // get rank list from top 4 (sublist (3, list.size -1))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newRankList: List<Rank>) {
        rankList = newRankList
        notifyDataSetChanged()
    }

    inner class RankViewHolder(private val context: Context, private val binding: ItemRankBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(rank: Rank, position: Int) {
            Glide.with(context)
                .load(rank.avatar)
                .circleCrop()
                .into(binding.imgAvatarRank)
            binding.tvUsernameRank.text = rank.name
            binding.tvPoint.text = rank.point.toString()
            binding.tvNumberRank.text = position.toString()
        }
    }
}
