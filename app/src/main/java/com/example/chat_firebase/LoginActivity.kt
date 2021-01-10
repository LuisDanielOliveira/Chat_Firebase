package com.example.chat_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private val newWordActivityRequestCode = 1

    private lateinit var email_texto_login: EditText
    private lateinit var password_texto_login: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        email_texto_login = findViewById(R.id.email_login)
        password_texto_login = findViewById(R.id.password_login)

        val btn_login = findViewById<Button>(R.id.btn_login)
        btn_login.setOnClickListener {

            loginUser()
        }


    }

    private fun loginUser() {

        val email: String = email_texto_login.text.toString()
        val password: String = password_texto_login.text.toString()

        if (email == "")
        {
            Toast.makeText(this@LoginActivity, "O campo email está vazio ou errado", Toast.LENGTH_LONG).show()
        }
        else if (password == "")
        {
            Toast.makeText(this@LoginActivity, "O campo password está vazio ou errado", Toast.LENGTH_LONG).show()
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{

                task ->

                if(task.isSuccessful)
                {
                    val intent = Intent(this@LoginActivity,MainActivity ::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this@LoginActivity, "Erro:" + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }


    }







}