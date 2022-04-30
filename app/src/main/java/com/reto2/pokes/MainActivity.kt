package com.reto2.pokes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.reto2.pokes.databinding.ActivityMainBinding
import com.reto2.pokes.fragments.PokeDetail
import com.reto2.pokes.fragments.PokeInfo
import com.reto2.pokes.model.Poke
import com.reto2.pokes.model.User
import com.reto2.pokes.pokeList.Adapter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var container: ConstraintLayout
    var user:User? = null

    // Fragments
    lateinit var pokeInfo: PokeInfo
    lateinit var pokeDetail: PokeDetail

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // On not logged User
        if(Firebase.auth.currentUser == null){
            startActivity((Intent(this,LoginActivity::class.java)))
            finish() // Termina la actividad
            return
        }

        // Set actual user
        val uid = Firebase.auth.currentUser?.uid

        Firebase.firestore.collection("users").
            document(uid!!).get().addOnSuccessListener {
                    docSnap ->
                user = docSnap.toObject(User::class.java)
                Toast.makeText(this,user?.username, Toast.LENGTH_LONG).show()

                //Crear fragmentos
                pokeInfo = PokeInfo.newInstance()
                pokeDetail = PokeDetail.newInstance()
                showFragment(pokeInfo)
            }
    }

    fun catchedPokeList(adapter: Adapter){
        user?.let {
                user ->
            Firebase.firestore.collection("users")
                .document(user.id).collection("pokes").get()
                .addOnSuccessListener{
                        docSnap ->
                        val pokesList = docSnap.toObjects(Poke::class.java)
                    adapter.setpokeList(pokesList as ArrayList<Poke>)
                }
        }
    }

    fun showFragment(fragment: Fragment?) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(binding.fragmentHolder.id, fragment!!)
        transaction.commit()
    }

    fun singout(view: View) {
        Log.e(">>>","BYE")
        Firebase.auth.signOut()
        startActivity((Intent(this,LoginActivity::class.java)))
        finish()
    }

    fun freePoke( poke: Poke) {
        user?.let {
                user ->
            Firebase.firestore.collection("users")
                .document(user.id).collection("pokes")
                .document(poke.id).delete()
                .addOnSuccessListener{
                    Toast.makeText(this,"Poke liberado", Toast.LENGTH_LONG).show()
                    catchedPokeList(pokeInfo.adapter)

                }.addOnFailureListener {
                    Log.e("deleteFail->",it.toString())
                }
        }
    }

    fun catchPoke(poke: Poke) {

        user?.let {
                user ->
            Firebase.firestore.collection("users")
                .document(user.id).collection("pokes")
                .document(poke.id).set(poke)
                .addOnSuccessListener{
                    Toast.makeText(this,"Poke atrapado", Toast.LENGTH_LONG).show()
                    catchedPokeList(pokeInfo.adapter)
                }.addOnFailureListener {
                    Log.e("deleteFail->",it.toString())
                }
        }
    }


}