package com.example.scrycipher

import androidx.room.Dao
import androidx.room.*

@Dao
@TypeConverters(Converters::class)
interface DeckDAO {
    @Insert
    fun insert(deck: Deck)

    @Update
    fun update(deck: Deck)

    @Delete
    fun delete(deck: Deck)

    @Query("DELETE FROM decks")
    fun deleteAllDecks()

    @Query("SELECT * FROM decks")
    fun getAllDecks(): List<Deck>

    @Query("SELECT * FROM decks WHERE owner=:owner")
    fun getUserDecks(owner:String): List<Deck>

    @Query("SELECT * FROM decks WHERE owner=:owner AND name=:name")
    fun getUserDeck(owner:String, name:String): Deck
}