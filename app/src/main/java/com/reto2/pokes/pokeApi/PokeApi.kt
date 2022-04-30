package com.reto2.pokes.pokeApi

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.google.gson.Gson
import com.reto2.pokes.fragments.PokeDetail
import com.reto2.pokes.model.Poke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.net.ssl.HttpsURLConnection
import kotlin.collections.ArrayList

class PokeApi: ViewModel() {

    private var urlT = "https://pokeapi.co/api/v2/pokemon/"
    private lateinit var connection: HttpsURLConnection // el as HttpsURLConnection para poder conectarse a una pagina con https
    private var _poke:MutableLiveData<Poke> = MutableLiveData()

    val poke:LiveData<Poke>
        get(){
            return _poke
        }

    fun getByName(name: String) {
        var json = ""


        viewModelScope.launch (Dispatchers.IO){ // IO es un nuevo hilo, main mantiene el mismo hilo
            val url = URL("$urlT$name")
            connection = url.openConnection() as HttpsURLConnection
            connection.requestMethod = "GET"
            json = connection.inputStream.bufferedReader().readText()
            //Log.e("-->",json)

            val obj = Gson().fromJson(json, PokeJson::class.java)

            val date = Calendar.getInstance().time.toString().split(' ')
            val displayDate = "${date[1]} ${date[2]} de ${date[5]}"

            val temp = Poke(
                UUID.randomUUID().toString(),
                obj.sprites.other.home.front_default,
                obj.name,
                obj.types[0].type.name,
                obj.stats[1].base_stat,
                obj.stats[2].base_stat,
                obj.stats[5].base_stat,
                obj.stats[0].base_stat,
                displayDate
            )

            withContext(Dispatchers.Main){
                _poke.value = temp
            }
        }

    }

    companion object {
        fun createApi():PokeApi{
            return PokeApi()
        }
    }


    // Deserialización
    data class PokeJson(
        var types:ArrayList<TypeItem>,
        var name: String,
        var stats:ArrayList<SatatItem>,
        var sprites: Sprite
    )

    //parts abilities
    data class TypeItem (
        var type: Type,
    )

    data class  Type(
        var name: String
    )

    //parts stats
    data class SatatItem(
        var base_stat:Int,
        var stat:Stat
    )

    data class Stat(
        var name:String
    )

    // img parts
    data class Sprite(
        var other: Other,
    )

    data class Other(
        var home : Home
    )

    data class Home(
        var front_default:String
    )

}