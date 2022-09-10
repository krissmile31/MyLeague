package com.krissmile31.myleague.model

data class League(
    val id: String = "",
    val name: String = "",
    val icon: String = "",
    val minPoint: Long = 0L,
    val maxPoint: Long = 0L,
    var expand: Boolean = false
)
