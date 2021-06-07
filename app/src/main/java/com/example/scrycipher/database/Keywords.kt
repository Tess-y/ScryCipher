package com.example.scrycipher.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "keywords")
data class Keywords(@PrimaryKey val id: UUID,
                    val keywords: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Keywords

        if (id != other.id) return false
        if (!keywords.contentEquals(other.keywords)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + keywords.contentHashCode()
        return result
    }
}