package com.example.breakingpet.domain.model.characters

import com.example.breakingpet.data.database.entities.CharacterEntity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
) : Serializable{

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




