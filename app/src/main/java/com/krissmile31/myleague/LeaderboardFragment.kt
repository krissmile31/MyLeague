package com.krissmile31.myleague

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.krissmile31.myleague.adapter.LeagueAdapter
import com.krissmile31.myleague.adapter.RankAdapter
import com.krissmile31.myleague.databinding.FragmentLeaderboardBinding
import com.krissmile31.myleague.model.League
import com.krissmile31.myleague.model.Rank

class LeaderboardFragment : Fragment() {

    private lateinit var leagueAdapter: LeagueAdapter
    private lateinit var rankAdapter: RankAdapter
    private var leagueList: ArrayList<League> = ArrayList()
    private var rankList: ArrayList<Rank> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentLeaderboardBinding.inflate(layoutInflater, container, false)

        setRecyclerView(binding)
        setLeagueView(binding)
        setRankView(binding)

        return binding.root
    }

    private fun setRecyclerView(binding: FragmentLeaderboardBinding) {
        binding.rclLeague.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rclRankList.layoutManager = LinearLayoutManager(context)
        binding.rclRankList.isNestedScrollingEnabled = false

    }

    private fun setRankView(binding: FragmentLeaderboardBinding) {
        val reference = Firebase.database.getReference("user").orderByChild("point")

        reference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for  (i in snapshot.children) {
                        val value = i.getValue(Rank::class.java)
                        value?.let { rankList.add(it) }
//                        rankList.sortedWith(compareByDescending { it.point })

                        Log.d("LeaderboardFragment", "onDataChange: $value")
                        Log.d("LeaderboardFragment", "onDataChange: $rankList")
                    }
                    rankList.reverse()

                    setRankInTop(binding)

                    rankAdapter = RankAdapter(rankList)
                    binding.rclRankList.adapter = rankAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setRankInTop(binding: FragmentLeaderboardBinding) {
//        binding.tvUsernameGold.text = rankList[0].name
//        ImageUtil.showRoundUserImage(context, rankList[0].avatar, binding.imgRankGold)
//        binding.tvPointGold.text = rankList[0].point.toString()
//
//        binding.tvUsernameSilver.text = rankList[1].name
//        ImageUtil.showRoundUserImage(context, rankList[1].avatar, binding.imgRankSilver)
//        binding.tvPointSilver.text = rankList[1].point.toString()
//
//        binding.tvUsernameBronze.text = rankList[2].name
//        ImageUtil.showRoundUserImage(context, rankList[2].avatar, binding.imgRankBronze)
//        binding.tvPointBronze.text = rankList[2].point.toString()
    }

    private fun setLeagueView(binding: FragmentLeaderboardBinding) {
        val database = Firebase.database
        val reference = database.getReference("league")

        reference.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                leagueList.clear()
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val value = i.getValue(League::class.java)
                        value?.let { leagueList.add(it) }
                        Log.d("LeaderboardFragment", "onDataChange: $value")
                    }
                    leagueAdapter = LeagueAdapter(leagueList, context!!)
                    binding.rclLeague.adapter = leagueAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    companion object {
        const val TOP_RANK = 3
    }

}
