package com.example.scrycipher

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Deck::class], version = 1)
@TypeConverters(Converters::class)
abstract class DeckDatabase : RoomDatabase(){

    abstract fun deckDAO(): DeckDAO

    companion object{
        var instance: DeckDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): DeckDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(ctx.applicationContext, DeckDatabase::class.java, "decks").fallbackToDestructiveMigration().build()
                populateDatabase(instance!!)
            }
            return instance!!

        }
        @Synchronized
        private fun populateDatabase(db: DeckDatabase) {
            val deckDao = db.deckDAO()
            if(deckDao.getAllDecks().isNotEmpty()) return
            deckDao.insert(Deck("Test", "Legacy", arrayListOf("Liliana, Dreadhorde General", "Village Rites", "Dark Confidant", "Bitterblossom", "Dreadhorde Invasion"), "Madigan Mason"))
            deckDao.insert(Deck("Chain Combo", "Legacy", arrayListOf("Professor Onyx", "Witherbloom Apprentice", "Chain of Smog", "Swamp", "Bayou"), "Liliana Vess"))
            deckDao.insert(Deck("Swarm", "Legacy", arrayListOf("Thrumming Stone", "Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats","Relentless Rats"), "Liliana Vess"))
            deckDao.insert(Deck("Gravecrawler", "Legacy", arrayListOf("Gravecrawler", "Phyrexian Altar", "Diregraf Captain", "Diregraf Colossus"), "Liliana Vess"))
            deckDao.insert(Deck("Horde", "Modern", arrayListOf("Endless Ranks of the Dead", "From Under the Floorboards", "Field of the Dead", "Necromancer's Stockpile", "Diregraf Colossus"), "Gisa Cecani"))
            deckDao.insert(Deck("Horde 2", "Modern", arrayListOf("Endless Ranks of the Dead", "From Under the Floorboards", "Field of the Dead", "Necromancer's Stockpile", "Diregraf Colossus"), "Gisa Cecani"))
            deckDao.insert(Deck("Orzhov Aristocrats", "Historic", arrayListOf("Cruel Celebrant", "Woe Strider", "Luminous Broodmoth", "Dreadhorde Invasion", "Village Rites"), "Teysa Karlov"))
            deckDao.insert(Deck("Orzhov Aristocrats", "Historic", arrayListOf("Cruel Celebrant", "Woe Strider", "Luminous Broodmoth", "Dreadhorde Invasion", "Village Rites"), "Jace Beleren"))
            deckDao.insert(Deck("Orzhov Aristocrats 2", "Historic", arrayListOf("Cruel Celebrant", "Woe Strider", "Luminous Broodmoth", "Dreadhorde Invasion", "Village Rites"), "Jace Beleren"))
            deckDao.insert(Deck("Orzhov Aristocrats 3", "Historic", arrayListOf("Cruel Celebrant", "Woe Strider", "Luminous Broodmoth", "Dreadhorde Invasion", "Village Rites"), "Jace Beleren"))
            deckDao.insert(Deck("Treasure Hunt", "Historic", arrayListOf("Treasure Hunt", "Thassa's Oracle", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island", "Island"), "Geralf Cecani"))
        }
    }
}