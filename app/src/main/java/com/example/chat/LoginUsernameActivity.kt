package com.example.chat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class LoginUsernameActivity : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnCadastro: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_username)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.email)
        editSenha = findViewById(R.id.senha)
        btnLogin = findViewById(R.id.login)
        btnCadastro = findViewById(R.id.cadastrar)

        btnCadastro.setOnClickListener {

            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()

            login(email, senha)
        }
    }

    private fun login(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LoginUsernameActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginUsernameActivity, "Esta conta não existe", Toast.LENGTH_SHORT).show()
                }
            }
    }
}