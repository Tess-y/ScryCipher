package com.example.scrycipher

import androidx.room.Dao
import androidx.room.*

@Dao
interface UserDAO {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM users")
    fun deleteAllUsers()

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE name=:name")
    fun getUser(name: String): User
}