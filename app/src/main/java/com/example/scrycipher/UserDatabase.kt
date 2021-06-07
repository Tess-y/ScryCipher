package com.example.scrycipher

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [User::class], version = 1)
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase(){

    abstract fun userDAO(): UserDAO

    companion object{
        var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): UserDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(ctx.applicationContext, UserDatabase::class.java, "users").fallbackToDestructiveMigration().build()
                populateDatabase(instance!!)
            }
            return instance!!

        }
        @Synchronized
        private fun populateDatabase(db: UserDatabase) {
            val userDao = db.userDAO()
            if(userDao.getAllUsers().isNotEmpty()) return
            userDao.insert(User("Madigan Mason", "BU", "19 - Rochester, NY", "Formats: EDH, Legacy, Modern \n Arena username: exaegrum \n Always looking for a game! My favourite EDH decks are Varina, Lich Queen and Kethis, the Hidden Hand. I really like utilizing the graveyard, so the reanimator and aristocrats archetypes are really fun."))
            userDao.insert(User("Liliana Vess", "B", "185 - Strixhaven, Arcavios", "Formats: Vintage, Legacy, EDH \n Arena username: nightqueen \n Looking for competitive players only. I'm a regular at Grand Prixs and I need quality practice. "))
            userDao.insert(User("Jace Beleren", "U", "24 - Singing City, Zendikar", "Formats: All of them \n Arena username: cloakboy \n Trying to get into new formats and archtypes - send me your lists to try them out! I'm down for a game anytime, as long as I've had my coffee first."))
            userDao.insert(User("Teysa Karlov", "WB", "120 - Orzhova, Ravnica", "Formats: Modern, Historic, Standard \n Arena username: advokist \n Getting back into the game after a long hiatus, trying to learn how to play with all these new formats and cards. Don't go easy on me, though!"))
            userDao.insert(User("Geralf Cecani", "U", "21 - Nephalia, Innistrad", "Formats: Limited \n Arena username: skaaberen \n My sister plays this game, so I've got to beat her, of course. I'll take any practice I can get."))
            userDao.insert(User("Davriel Cane", "B", "34 - Innistrad", "Formats: Legacy, Modern \n Arena username: N/A \n "))
            userDao.insert(User("Gisa Cecani", "B", "21 - Nephalia, Innistrad", "Formats: Limited \n Arena username: ghoulcaller \n My brother thinks he can beat me at Magic, and that simply cannot be allowed to happen. I've got to practice, so I can stay one step ahead of him."))

        }
    }
}