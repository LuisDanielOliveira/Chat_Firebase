package com.example.chat_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class StartingActivity : AppCompatActivity() {

    //initialize firebase user
    var firebaseUser: FirebaseUser? = null
    private val newWordActivityRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)
        //Botão registar
        val btn_registar_SA = findViewById<Button>(R.id.btn_registar_SA)
        btn_registar_SA.setOnClickListener {
            val intent = Intent(this@StartingActivity,CriarUtilizadorActivity ::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
        //Botão Login
        val btn_login_SA = findViewById<Button>(R.id.btn_login_SA)
        btn_login_SA.setOnClickListener {
            val intent = Intent(this@StartingActivity,LoginActivity ::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

    //ver se o user é null
         fun onStart() {
            super.onStart()

            firebaseUser = FirebaseAuth.getInstance().currentUser

            if (firebaseUser != null){
                val intent = Intent(this@StartingActivity,MainActivity ::class.java)
                startActivityForResult(intent, newWordActivityRequestCode)
            }
        }
    }





}