package com.example.breakingpet.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.breakingpet.domain.model.characters.Character
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val idPrimary: Int? = null,
    @SerializedName( "char_id")
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
    fun toCharacter(): Character {
        return Character (
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

}

class ConverterStringList {

    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}

class ConverterIntList {

    @TypeConverter
    fun listToJson(value: List<Int>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()
}
