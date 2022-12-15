package com.example.trabajonahufacu
import android.content.Intent
import android.icu.text.DecimalFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.trabajonahufacu.TicketRepositorio.tickets
import com.example.trabajonahufacu.databinding.ActivityLoginBinding
import entidades.Cliente
import entidades.Ticket
import entidades.Usuario
import java.time.LocalDate
import java.time.LocalTime
import java.util.*


open class loginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val objectIntent: Intent = intent
        val nombreUsuario = objectIntent.getStringExtra("nombreUsuario")
        val contrasena = objectIntent.getStringExtra("contraseña")
        val usuario = UsuarioRepositorio.iniciar(nombreUsuario.toString(), contrasena.toString())


        var saldoFormato= DecimalFormat("0.00")
        var saldoUsuario = saldoFormato.format(usuario!!.saldo)
        binding.tvUsuario.text = "Bienvenido $nombreUsuario"

        binding.tvSaldo.text = "Tu saldo es de: $saldoUsuario "


        binding.btPagar.setOnClickListener {
            val saldo:Double? = usuario!!.saldo
            if (saldo!! < 150.0){
                Toast.makeText(this, "Su saldo es insuficiente", Toast.LENGTH_SHORT).show()
            }else {
                val pagarActivity = Intent(this, PagarActivity::class.java)
                pagarActivity.putExtra("nombreUsuario", usuario.nombreUsuario)
                pagarActivity.putExtra("contraseña", usuario.password)

                startActivity(pagarActivity)
            }
        }
        binding.btRecargar.setOnClickListener {

            val recargarActivity = Intent(this, RecargarActivity::class.java)
                recargarActivity.putExtra("nombreUsuario", usuario.nombreUsuario)
                recargarActivity.putExtra("contraseña", usuario.password)
            startActivity(recargarActivity)
        }

        binding.btCerrarSesion.setOnClickListener {
            val volverLogin = Intent(this, MainActivity::class.java)
            startActivity(volverLogin)
        }

        binding.btTicket.setOnClickListener {
            if(TicketRepositorio.tickets.isNullOrEmpty()){
                Toast.makeText(this, "No hay ticket para mostrar", Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this, RecyclerviewTickets::class.java)
                startActivity(intent)
            }
        }

    }



}
