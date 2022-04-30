package com.reto2.pokes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.reto2.pokes.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener (::login)
        binding.singUp.setOnClickListener(::signup)

    }

    private fun login(view: View){

        Firebase.auth.signInWithEmailAndPassword(
            binding.userTF.text.toString(),
            binding.passwordTF.text.toString()
        ).addOnSuccessListener {
            startActivity(Intent(this, MainActivity::class.java))
        }.addOnFailureListener {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show() //Mensaje
        }
    }

    private fun signup(view: View){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}