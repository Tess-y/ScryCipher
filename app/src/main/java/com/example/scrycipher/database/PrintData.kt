package com.example.scrycipher.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.scrycipher.carddata.Image_Uris
import java.sql.Date
import java.util.*

@Entity(tableName = "printData")
//Discard content_warning, games, highres_image, prices, purchase_uris
data class PrintData (@PrimaryKey val cardID: UUID,
                      val artist: String?,
                      val booster: Boolean,
                      val border_color: String,
                      val card_back_id: UUID,
                      val digital: Boolean,
                      val flavor_name: String?,
                      val flavor_text: String?,
                      val frame_effects: Array<String>?,
                      val frame: String,
                      val full_art: Boolean,
                      val illustration_id: UUID?,
                      val image_status: String,
                      val image_uris: Image_Uris,
                      val printed_name: String?,
                      val printed_text: String?,
                      val printed_type_line: String?,
                      val promo: Boolean,
                      val promo_types: Array<String>?,
                      val rarity: String,
                      val released_at: Date,
                      val reprint: Boolean,
                      val set: String,
                      val story_spotlight: Boolean,
                      val textless: Boolean,
                      val variation: Boolean,
                      val variation_of: UUID?,
                      val watermark: String?
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PrintData

        if (cardID != other.cardID) return false
        if (artist != other.artist) return false
        if (booster != other.booster) return false
        if (border_color != other.border_color) return false
        if (card_back_id != other.card_back_id) return false
        if (digital != other.digital) return false
        if (flavor_name != other.flavor_name) return false
        if (flavor_text != other.flavor_text) return false
        if (frame_effects != null) {
            if (other.frame_effects == null) return false
            if (!frame_effects.contentEquals(other.frame_effects)) return false
        } else if (other.frame_effects != null) return false
        if (frame != other.frame) return false
        if (full_art != other.full_art) return false
        if (illustration_id != other.illustration_id) return false
        if (image_status != other.image_status) return false
        if (image_uris != other.image_uris) return false
        if (printed_name != other.printed_name) return false
        if (printed_text != other.printed_text) return false
        if (printed_type_line != other.printed_type_line) return false
        if (promo != other.promo) return false
        if (promo_types != null) {
            if (other.promo_types == null) return false
            if (!promo_types.contentEquals(other.promo_types)) return false
        } else if (other.promo_types != null) return false
        if (rarity != other.rarity) return false
        if (released_at != other.released_at) return false
        if (reprint != other.reprint) return false
        if (set != other.set) return false
        if (story_spotlight != other.story_spotlight) return false
        if (textless != other.textless) return false
        if (variation != other.variation) return false
        if (variation_of != other.variation_of) return false
        if (watermark != other.watermark) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cardID.hashCode()
        result = 31 * result + (artist?.hashCode() ?: 0)
        result = 31 * result + booster.hashCode()
        result = 31 * result + border_color.hashCode()
        result = 31 * result + card_back_id.hashCode()
        result = 31 * result + digital.hashCode()
        result = 31 * result + (flavor_name?.hashCode() ?: 0)
        result = 31 * result + (flavor_text?.hashCode() ?: 0)
        result = 31 * result + (frame_effects?.contentHashCode() ?: 0)
        result = 31 * result + frame.hashCode()
        result = 31 * result + full_art.hashCode()
        result = 31 * result + (illustration_id?.hashCode() ?: 0)
        result = 31 * result + image_status.hashCode()
        result = 31 * result + image_uris.hashCode()
        result = 31 * result + (printed_name?.hashCode() ?: 0)
        result = 31 * result + (printed_text?.hashCode() ?: 0)
        result = 31 * result + (printed_type_line?.hashCode() ?: 0)
        result = 31 * result + promo.hashCode()
        result = 31 * result + (promo_types?.contentHashCode() ?: 0)
        result = 31 * result + rarity.hashCode()
        result = 31 * result + released_at.hashCode()
        result = 31 * result + reprint.hashCode()
        result = 31 * result + set.hashCode()
        result = 31 * result + story_spotlight.hashCode()
        result = 31 * result + textless.hashCode()
        result = 31 * result + variation.hashCode()
        result = 31 * result + (variation_of?.hashCode() ?: 0)
        result = 31 * result + (watermark?.hashCode() ?: 0)
        return result
    }

}