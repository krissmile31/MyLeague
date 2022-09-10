package com.krissmile31.myleague.model

data class Rank (
    val uid: String = "",
    val avatar: String = "",
    val name: String = "",
    val gmail: String = "",
    val role: String = "",
    val streak: Int = 0,
    val point: Long = 0L,
    var premium: Boolean = false
)
