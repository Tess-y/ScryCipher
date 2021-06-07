package com.example.scrycipher.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.scrycipher.carddata.Legalities
import com.example.scrycipher.carddata.MagicColor
import java.util.*

@Entity(tableName = "cards")
//Discard arena_id, mtgo_id, mtgo_foil_id, multiverse_ids, tcgplayer_id, cardmarket_id, object, prints_search_uri, scryfall_uri, uri
//card_faces and keywords are separate tables
data class Card(val id: UUID,
                val lang: String?,
                @PrimaryKey val oracle_id: UUID,
                val all_parts: Array<UUID>?, //Just save the UUIDs of the parts
                val cmc: Float,
                val color_identity: Array<MagicColor>,
                val color_indicator: Array<MagicColor>?,
                val colors: Array<MagicColor>?,
                val foil: Boolean,
                val hand_modifier:  String?,
                val layout: String,
                val legalities: Legalities,
                val life_modifier: String?,
                val lotalty: String?,
                val mana_cost: String?,
                val name: String,
                val nonfoil: Boolean,
                val oracle_text: String?,
                val oversized: Boolean,
                val power: String?,
                val produced_mana: Array<MagicColor>?,
                val reserved: Boolean,
                val toughness: String?,
                val type_line: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        if (id != other.id) return false
        if (lang != other.lang) return false
        if (oracle_id != other.oracle_id) return false
        if (all_parts != null) {
            if (other.all_parts == null) return false
            if (!all_parts.contentEquals(other.all_parts)) return false
        } else if (other.all_parts != null) return false
        if (cmc != other.cmc) return false
        if (!color_identity.contentEquals(other.color_identity)) return false
        if (color_indicator != null) {
            if (other.color_indicator == null) return false
            if (!color_indicator.contentEquals(other.color_indicator)) return false
        } else if (other.color_indicator != null) return false
        if (colors != null) {
            if (other.colors == null) return false
            if (!colors.contentEquals(other.colors)) return false
        } else if (other.colors != null) return false
        if (foil != other.foil) return false
        if (hand_modifier != other.hand_modifier) return false
        if (layout != other.layout) return false
        if (legalities != other.legalities) return false
        if (life_modifier != other.life_modifier) return false
        if (lotalty != other.lotalty) return false
        if (mana_cost != other.mana_cost) return false
        if (name != other.name) return false
        if (nonfoil != other.nonfoil) return false
        if (oracle_text != other.oracle_text) return false
        if (oversized != other.oversized) return false
        if (power != other.power) return false
        if (produced_mana != null) {
            if (other.produced_mana == null) return false
            if (!produced_mana.contentEquals(other.produced_mana)) return false
        } else if (other.produced_mana != null) return false
        if (reserved != other.reserved) return false
        if (toughness != other.toughness) return false
        if (type_line != other.type_line) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (lang?.hashCode() ?: 0)
        result = 31 * result + oracle_id.hashCode()
        result = 31 * result + (all_parts?.contentHashCode() ?: 0)
        result = 31 * result + cmc.hashCode()
        result = 31 * result + color_identity.contentHashCode()
        result = 31 * result + (color_indicator?.contentHashCode() ?: 0)
        result = 31 * result + (colors?.contentHashCode() ?: 0)
        result = 31 * result + foil.hashCode()
        result = 31 * result + (hand_modifier?.hashCode() ?: 0)
        result = 31 * result + layout.hashCode()
        result = 31 * result + legalities.hashCode()
        result = 31 * result + (life_modifier?.hashCode() ?: 0)
        result = 31 * result + (lotalty?.hashCode() ?: 0)
        result = 31 * result + (mana_cost?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + nonfoil.hashCode()
        result = 31 * result + (oracle_text?.hashCode() ?: 0)
        result = 31 * result + oversized.hashCode()
        result = 31 * result + (power?.hashCode() ?: 0)
        result = 31 * result + (produced_mana?.contentHashCode() ?: 0)
        result = 31 * result + reserved.hashCode()
        result = 31 * result + (toughness?.hashCode() ?: 0)
        result = 31 * result + type_line.hashCode()
        return result
    }
}