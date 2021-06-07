package com.example.scrycipher.carddata

class Legalities(
    standard: Legality,
    future: Legality,
    historic: Legality,
    gladiator: Legality,
    pioneer: Legality,
    modern: Legality,
    legacy: Legality,
    pauper: Legality,
    vintage: Legality,
    penny: Legality,
    commander: Legality,
    brawl: Legality,
    duel: Legality,
    oldschool: Legality,
    premodern: Legality,
) {
}

enum class Legality(legality: String){
    LEGAL("legal"),
    NOT_LEGAL("not_legal"),
    RESTRICTED("restricted"),
    BANNED("banned")
}