package com.example.scrycipher.database

import androidx.room.Dao
import androidx.room.*

@Dao
interface CardDAO {

    @Insert
    fun insert(card: Card)

    @Update
    fun update(card: Card)

    @Insert
    fun insert(printData: PrintData)

    @Update
    fun update(printData: PrintData)

    @Insert
    fun insert(cardFace: CardFace)

    @Update
    fun update(cardFace: CardFace)

    @Insert
    fun insert(keywords: Keywords)

    @Update
    fun update(keywords: Keywords)


}