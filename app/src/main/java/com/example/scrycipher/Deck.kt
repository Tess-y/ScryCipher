package com.example.scrycipher

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "decks")
data class Deck(@PrimaryKey(autoGenerate = true) val id: Int,var name: String?, var format: String?, var cards: ArrayList<String>, var owner: String?) : Parcelable {
    constructor(name: String?,format: String?,cards: ArrayList<String>,owner: String?) :
            this(0,name, format, cards, owner){}

    constructor(parcel: Parcel) : this(
        0,
        parcel.readString(),
        parcel.readString(),
        parcel.readArrayList(null) as ArrayList<String>,
        parcel.readString()
        ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(format)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<Deck> {
        override fun createFromParcel(parcel: Parcel): Deck {
            return Deck(parcel)
        }

        override fun newArray(size: Int): Array<Deck?> {
            return arrayOfNulls(size)
        }
    }
}