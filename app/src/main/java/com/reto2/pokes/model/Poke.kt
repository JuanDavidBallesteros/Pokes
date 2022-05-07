package com.reto2.pokes.model

data class Poke(
    var id: String = "",
    var img: String = "",
    var name: String = "",
    var power: String = "",
    var attack: Int = 0,
    var defence: Int = 0,
    var speed: Int = 0,
    var health: Int = 0,
    var catchDate: Long = 0
)
