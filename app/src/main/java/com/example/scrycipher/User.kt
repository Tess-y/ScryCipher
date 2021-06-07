package com.example.scrycipher

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(@PrimaryKey(autoGenerate = true) val id: Int, val name: String?, val mana: String?, val type: String?, val text: String?) : Parcelable {
    constructor(name: String?, mana: String?, type: String?, text: String?) :
            this(0, name, mana,type,text) {}

    constructor(parcel: Parcel) : this(
        0,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(mana)
        parcel.writeString(type)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}