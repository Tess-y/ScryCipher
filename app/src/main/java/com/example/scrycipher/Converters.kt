package com.example.scrycipher

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): ArrayList<String> =
        ArrayList(Gson().fromJson(value, Array<String>::class.java).toList())


    @TypeConverter
    fun listDeckToJson(value: java.util.ArrayList<Deck>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToListDeck(value: String): java.util.ArrayList<Deck> =
        ArrayList(Gson().fromJson(value, Array<Deck>::class.java).toList())

}