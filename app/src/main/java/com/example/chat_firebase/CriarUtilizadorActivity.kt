package com.example.chat_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CriarUtilizadorActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserID: String = ""

    private lateinit var username_texto: EditText
    private lateinit var email_texto: EditText
    private lateinit var password_texto: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_utilizador)

        mAuth = FirebaseAuth.getInstance()

        username_texto = findViewById(R.id.username_registar)
        email_texto = findViewById(R.id.email_registar)
        password_texto = findViewById(R.id.password_registar)


        val btn_registar = findViewById<Button>(R.id.btn_registar)
        btn_registar.setOnClickListener {

            registerUser()
        }


    }


    private fun registerUser() {

        val username: String = username_texto.text.toString()
        val email: String = email_texto.text.toString()
        val password: String = password_texto.text.toString()

        if (username == "")
        {
            Toast.makeText(this@CriarUtilizadorActivity, "O campo username não pode estar vazio", Toast.LENGTH_LONG).show()
        }
        else if (email == "")
        {
            Toast.makeText(this@CriarUtilizadorActivity, "O campo email não pode estar vazio", Toast.LENGTH_LONG).show()
        }
        else if (password == "")
        {
            Toast.makeText(this@CriarUtilizadorActivity, "O campo password não pode estar vazio", Toast.LENGTH_LONG).show()
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{

                task ->
                if (task.isSuccessful)
                {
                    firebaseUserID = mAuth.currentUser!!.uid
                     refUsers = FirebaseDatabase.getInstance("https://chatfirebase-a01de-default-rtdb.firebaseio.com/").reference.child("Users").child(firebaseUserID)

                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserID
                    userHashMap["profile"] = "https://firebasestorage.googleapis.com/v0/b/chatfirebase-a01de.appspot.com/o/profile_image.png?alt=media&token=e39efd5a-4bdf-4dd8-a5ef-b7ee334a4848"
                    userHashMap["status"] = "offline"
                    userHashMap["search"] = username.toLowerCase()
                    userHashMap["username"] = username

                    refUsers.updateChildren(userHashMap).addOnCompleteListener{
                            task ->
                            if (task.isSuccessful){

                                val intent = Intent(this@CriarUtilizadorActivity,MainActivity ::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                finish()

                            }


                    }

                }
                else
                {
                    Toast.makeText(this@CriarUtilizadorActivity, "Erro:" + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }


    }


}