package com.example.trabajonahufacu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.trabajonahufacu.databinding.ActivityMainBinding


open class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btRegistrarse.setOnClickListener {
            val registerActivityIntent = Intent(this, RegistrarActivity::class.java)
            startActivity(registerActivityIntent)
        }

        binding.btIngresar.setOnClickListener {
            val loginActivity = Intent(this, loginActivity::class.java)
                if(validateInput()) {
                    val usuario = UsuarioRepositorio.iniciar(binding.etNombre.text.toString(), binding.etContrasena.text.toString())

                if(usuario != null) {
                    loginActivity.putExtra("nombreUsuario", binding.etNombre.text.toString())
                    loginActivity.putExtra("contraseña", binding.etContrasena.text.toString())
                    startActivity(loginActivity)
                } else {
                    Toast.makeText(this, "Nombre de usuario o contraseña incorrectas", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor ingrese los datos solicitados", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun validateInput(): Boolean {
        return binding.etNombre.text.isNotEmpty() && binding.etContrasena.text.isNotEmpty()
    }
}