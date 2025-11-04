package com.pasteleria1000sabores.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pasteleria1000sabores.R

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ocultar la barra de acción para una mejor experiencia de login
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.email_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)
        loginButton = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            performLogin()
        }

        // Opcional: Manejar el clic en el enlace de registro si implementas RegisterActivity
        // findViewById<TextView>(R.id.register_link).setOnClickListener {
        //     val intent = Intent(this, RegisterActivity::class.java)
        //     startActivity(intent)
        // }
    }

    private fun performLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa ambos campos.", Toast.LENGTH_SHORT).show()
            return
        }

        // --- Lógica de Autenticación Temporal/Simulada ---
        // En una aplicación real, aquí llamarías a tu servicio de Retrofit
        // para autenticar contra tu backend.

        if (email == "test@pasteleria.cl" && password == "123456") {
            // Autenticación exitosa simulada:
            Toast.makeText(this, "Bienvenido!", Toast.LENGTH_SHORT).show()

            // Navegar a la actividad principal (Home)
            val intent = Intent(this, MainActivity::class.java)
            // Esto asegura que el usuario no pueda volver al login con el botón de retroceso
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()

        } else {
            // Error de autenticación
            Toast.makeText(this, "Credenciales incorrectas.", Toast.LENGTH_LONG).show()
        }
    }
}