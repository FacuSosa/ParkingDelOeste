package com.example.trabajonahufacu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.trabajonahufacu.databinding.ActivityRegistrarBinding
import entidades.Usuario
import java.time.LocalDate

class RegistrarActivity : MainActivity() {

    private lateinit var binding: ActivityRegistrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btRegistrarse.setOnClickListener {
            val repositorioUsuario = UsuarioRepositorio
            val contraseña = binding.etContrasena.text.toString()
            val nombreUsuario = binding.etNombreUsuarioRegistro.text.toString()
            val apellido = binding.etApellido.text.toString()
            val nombre = binding.etNombre.text.toString()

            if(validarEntrada()){
                if ( !repositorioUsuario.existe(nombreUsuario) ) {
                    val nuevoUsuario = Usuario(1, nombre, apellido, 0.00, LocalDate.now(), contraseña, nombreUsuario)
                    repositorioUsuario.agregar(nuevoUsuario)

                    Toast.makeText(this, "Se Registro Correctamente", Toast.LENGTH_LONG).show()
                    val volverActivityIntent = Intent(this, MainActivity::class.java)
                    startActivity(volverActivityIntent)
                } else {
                    Toast.makeText(this, "Ya existe un usuario con ese nombre", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Por favor ingrese los datos solicitados", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btIngresar.setOnClickListener {
            val volverActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(volverActivityIntent)
        }
    }

    private fun validarEntrada(): Boolean {
        return binding.etNombre.text.isNotEmpty() && binding.etApellido.text.isNotEmpty() && binding.etNombreUsuarioRegistro.text.isNotEmpty() &&
        binding.etContrasena.text.isNotEmpty()
    }
}