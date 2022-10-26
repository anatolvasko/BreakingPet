package com.example.breakingpet.domain.models

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("char_id")
    val charId: Long,
    val name: String,
    val birthday: String,
    val occupation: List<String>,
    val img: String,
    val status: String,
    val nickname: String,
    val appearance: List<Long>,
    val portrayed: String,
    val category: String,
    @SerializedName("better_call_saul_appearance")
    val betterCallSaulAppearance: List<Long>,
)


/*@SerializedName("char_id")
val charID: Int,
val name: String,
val nickname: String,
val img: String,
val portrayed: String,
val occupation: List<String>,
val status: String,

val birthday: String,
val category: String,
val appearance: List<Int>*/

