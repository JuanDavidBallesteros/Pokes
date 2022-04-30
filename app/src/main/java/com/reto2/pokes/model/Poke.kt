package com.reto2.pokes.model

data class Poke(
    var img: String = "",
    var name: String = "",
    var power: String = "",
    var attack: Number = 0,
    var defence: Number = 0,
    var speed: Number = 0,
    var health: Number = 0,
    var catchDate: String = ""
)
