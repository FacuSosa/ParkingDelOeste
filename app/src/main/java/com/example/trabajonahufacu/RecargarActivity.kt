package com.example.trabajonahufacu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.trabajonahufacu.databinding.ActivityRecargarBinding

class RecargarActivity : loginActivity() {

    private lateinit var binding: ActivityRecargarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecargarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val objectIntent: Intent = intent
        val nombreUsuario = objectIntent.getStringExtra("nombreUsuario")
        val contrasena = objectIntent.getStringExtra("contraseña")
        val usuario = UsuarioRepositorio.iniciar(nombreUsuario.toString(), contrasena.toString())

        binding.btRecargar.setOnClickListener {

                val saldo = usuario!!.saldo
                val saldoRecargar: String? = binding.etSaldo.text.toString()

            if (saldoRecargar.isNullOrEmpty()) {
                Toast.makeText(this, "Debe ingresar el saldo", Toast.LENGTH_SHORT).show()
            }

            else {
               usuario.saldo = saldo?.let { it1 -> UsuarioRepositorio.recargarSaldo(it1, saldoRecargar.toDouble()) }

                val volverActivityIntent = Intent(this, loginActivity::class.java)

                volverActivityIntent.putExtra("nombreUsuario", usuario.nombreUsuario)
                volverActivityIntent.putExtra("contraseña", usuario.password)

                Toast.makeText(this, "Se cargo el saldo correctamente", Toast.LENGTH_SHORT).show()
                startActivity(volverActivityIntent)
            }
        }
     binding.btnCancelar2.setOnClickListener {
         finish()
     }
    }
}