package com.example.breakingpet.domain.model.characters

import androidx.room.TypeConverter
import com.example.breakingpet.data.database.entities.CharacterEntity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("char_id")
    val characterID: Int,
    val name: String,
    val nickname: String,
    val img: String,
    val portrayed: String,
    val occupation: List<String>,
    val status: String,
    val birthday: String,
    val category: String,
    val appearance: List<Int>
) {

    fun toCharacterEntity() = CharacterEntity(
        characterID = characterID,
        name = name,
        nickname = nickname,
        img = img,
        portrayed = portrayed,
        occupation = occupation,
        status = status,
        birthday = birthday,
        category = category,
        appearance = appearance
    )

}


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

