package com.reto2.pokes.model

data class User(
    var id: String = "",
    var username: String = "",
    var pokes:ArrayList<Poke> = ArrayList<Poke>()
)
