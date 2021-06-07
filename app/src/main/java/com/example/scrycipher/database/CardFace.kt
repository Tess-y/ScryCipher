package com.example.scrycipher.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.scrycipher.carddata.Image_Uris
import com.example.scrycipher.carddata.MagicColor
import java.util.*

@Entity(tableName = "cardfaces")
data class CardFace(@PrimaryKey val faceid: String,//UUID of associated card plus the face's index
                    val cardid: UUID,
                    val artist: String?,
                    val color_indicator: Array<MagicColor>?,
                    val colors: Array<MagicColor>?,
                    val flavor_text: String?,
                    val illustraion_id: UUID?,
                    val image_uris: Image_Uris?,
                    val loyalty: String?,
                    val mana_cost: String,
                    val name: String,
                    val oracle_text: String?,
                    val power: String?,
                    val printed_name: String?,
                    val printed_text: String?,
                    val printed_type_line: String?,
                    val toughness: String?,
                    val type_line: String,
                    val watermark: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardFace

        if (faceid != other.faceid) return false
        if (cardid != other.cardid) return false
        if (artist != other.artist) return false
        if (color_indicator != null) {
            if (other.color_indicator == null) return false
            if (!color_indicator.contentEquals(other.color_indicator)) return false
        } else if (other.color_indicator != null) return false
        if (colors != null) {
            if (other.colors == null) return false
            if (!colors.contentEquals(other.colors)) return false
        } else if (other.colors != null) return false
        if (flavor_text != other.flavor_text) return false
        if (illustraion_id != other.illustraion_id) return false
        if (image_uris != other.image_uris) return false
        if (loyalty != other.loyalty) return false
        if (mana_cost != other.mana_cost) return false
        if (name != other.name) return false
        if (oracle_text != other.oracle_text) return false
        if (power != other.power) return false
        if (printed_name != other.printed_name) return false
        if (printed_text != other.printed_text) return false
        if (printed_type_line != other.printed_type_line) return false
        if (toughness != other.toughness) return false
        if (type_line != other.type_line) return false
        if (watermark != other.watermark) return false

        return true
    }

    override fun hashCode(): Int {
        var result = faceid.hashCode()
        result = 31 * result + cardid.hashCode()
        result = 31 * result + (artist?.hashCode() ?: 0)
        result = 31 * result + (color_indicator?.contentHashCode() ?: 0)
        result = 31 * result + (colors?.contentHashCode() ?: 0)
        result = 31 * result + (flavor_text?.hashCode() ?: 0)
        result = 31 * result + (illustraion_id?.hashCode() ?: 0)
        result = 31 * result + (image_uris?.hashCode() ?: 0)
        result = 31 * result + (loyalty?.hashCode() ?: 0)
        result = 31 * result + mana_cost.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (oracle_text?.hashCode() ?: 0)
        result = 31 * result + (power?.hashCode() ?: 0)
        result = 31 * result + (printed_name?.hashCode() ?: 0)
        result = 31 * result + (printed_text?.hashCode() ?: 0)
        result = 31 * result + (printed_type_line?.hashCode() ?: 0)
        result = 31 * result + (toughness?.hashCode() ?: 0)
        result = 31 * result + type_line.hashCode()
        result = 31 * result + (watermark?.hashCode() ?: 0)
        return result
    }
}