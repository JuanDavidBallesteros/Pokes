package com.reto2.pokes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.reto2.pokes.databinding.ActivitySignUpBinding
import com.reto2.pokes.model.User


class SignUpActivity : AppCompatActivity() {

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener {
            val email = binding.signupEmailET.text.toString()
            val pass = binding.signupPassET.text.toString()
            Firebase.auth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener {
                Toast.makeText(this,"Success",Toast.LENGTH_LONG).show()
                registerUserData()
            }.addOnFailureListener {
                Toast.makeText(this,"Something go wrong: ${it.message}",Toast.LENGTH_LONG).show()
            }
        }
    }


    fun registerUserData(){
        val uid =  Firebase.auth.currentUser?.uid

        uid?.let {
            doc ->
            val user = User(
                doc,
                binding.signupUsernameET.text.toString()
            )

            Firebase.firestore.collection("users").document(doc).set(user).addOnSuccessListener {
                startActivity(Intent(this,MainActivity::class.java))
            }
            // Puedo añadir
            // los listeners anteriore para saber si se pudo guardar la información
        }


    }
}