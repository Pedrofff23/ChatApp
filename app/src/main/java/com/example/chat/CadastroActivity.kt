package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CadastroActivity : AppCompatActivity() {

    private lateinit var editUsuario: EditText
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var btnCadastro: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var DbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.email)
        editUsuario = findViewById(R.id.usuario)
        editSenha = findViewById(R.id.senha)
        btnCadastro = findViewById(R.id.cadastrar)

        btnCadastro.setOnClickListener {
            val name = editUsuario.text.toString()
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()

            cadastrar(name,email, senha)
        }
    }

    private fun cadastrar(name: String ,email: String, senha: String) {
        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    addUserToDatabase(name,email,auth.currentUser?.uid!! )
                    val intent = Intent(this@CadastroActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@CadastroActivity, "Erro ao criar a conta", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun  addUserToDatabase(name: String, email: String, uid:String){
        DbRef = FirebaseDatabase.getInstance().getReference()

        DbRef.child("user").child(uid).setValue(User(name,email,uid))
    }
}