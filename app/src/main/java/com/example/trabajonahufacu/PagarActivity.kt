package com.example.trabajonahufacu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.trabajonahufacu.TicketRepositorio.codigoTicket
import com.example.trabajonahufacu.databinding.ActivityPagarBinding
import entidades.Ticket
import entidades.Usuario
import repositorios.Ciclomotor
import repositorios.Pesado
import java.time.LocalDate
import java.time.LocalTime

open class PagarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagarBinding

    val valorHora = 150.0
    val automovil = Automovil()
    val pesado = Pesado()
    val ciclomotor = Ciclomotor()
    var id:Long = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val objectIntent: Intent = intent
        val nombreUsuario = objectIntent.getStringExtra("nombreUsuario")
        val contrasena = objectIntent.getStringExtra("contraseña")
        val usuario = UsuarioRepositorio.iniciar(nombreUsuario.toString(), contrasena.toString())



        binding.btCiclomotor.setOnClickListener {
            val saldo = usuario!!.saldo
            if (validarEntrada()){
            val repositorioTicket = TicketRepositorio
            val hora: Int = binding.etHora.text.toString().toInt()
            val minutos: Int = binding.etMinutos.text.toString().toInt()
            val estadia: Int = binding.etEstadia.text.toString().toInt()
            val patente: String = binding.etPatente.text.toString()
            val montoBruto: Double = valorHora * estadia
            var costo: Double?= ciclomotor.calcularRecarga(valorHora, estadia)


                val nuevoTicket = Ticket(id,codigoTicket, LocalDate.now(),LocalTime.of(hora,
                    minutos
                ),"Ciclomotor", patente,
                    estadia, montoBruto,costo!!)
                if (saldo!! >= costo){
                    codigoTicket++
                    repositorioTicket.agregar(nuevoTicket)
                    usuario.saldo = saldo?.let { it1 -> UsuarioRepositorio.restarSaldo(it1, costo.toDouble()) }
                    val volverActivityIntent = Intent(this, loginActivity::class.java)

                    volverActivityIntent.putExtra("nombreUsuario", usuario.nombreUsuario)
                    volverActivityIntent.putExtra("contraseña", usuario.password)

                    Toast.makeText(this,"Se Registro Exitosamente el ticket",Toast.LENGTH_SHORT).show()
                    startActivity(volverActivityIntent)
                }

                if (saldo < costo){
                    Toast.makeText(this,"Saldo insuficiente,no puede realizar el pago",Toast.LENGTH_SHORT).show()
                    finish()
                }

            }
            else{
                Toast.makeText(this,"Debe completar todos los campos",Toast.LENGTH_SHORT).show()
            }

        }

       binding.btAutomovil.setOnClickListener {
            val saldo = usuario!!.saldo
           if (validarEntrada()){
            val repositorioTicket = TicketRepositorio
            val hora: Int = binding.etHora.text.toString().toInt()
            val minutos: Int = binding.etMinutos.text.toString().toInt()
            val estadia: Int = binding.etEstadia.text.toString().toInt()
            val patente: String = binding.etPatente.text.toString()
            val montoBruto: Double = valorHora * estadia
            var costo: Double?= automovil.calcularRecarga(valorHora, estadia, LocalTime.of(hora,minutos))

                val nuevoTicket = Ticket(id++, codigoTicket, LocalDate.now(),LocalTime.of(hora,
                    minutos
                ),"Automovil",patente,
                    estadia,montoBruto,costo!!)

                       if (saldo!! >= costo){
                    codigoTicket++
                    repositorioTicket.agregar(nuevoTicket)
                    usuario.saldo = saldo?.let { it1 -> UsuarioRepositorio.restarSaldo(it1, costo.toDouble()) }
                    val volverActivityIntent = Intent(this, loginActivity::class.java)

                    volverActivityIntent.putExtra("nombreUsuario", usuario.nombreUsuario)
                    volverActivityIntent.putExtra("contraseña", usuario.password)

                    Toast.makeText(this,"Se Registro Exitosamente el ticket",Toast.LENGTH_SHORT).show()
                    startActivity(volverActivityIntent)
                }

                if (saldo < costo){
                    Toast.makeText(this,"Saldo insuficiente,no puede realizar el pago",Toast.LENGTH_SHORT).show()
                    finish()
                }

            }
            else{
                Toast.makeText(this,"Debe completar todos los campos",Toast.LENGTH_SHORT).show()
            }


        }

        binding.btPesado.setOnClickListener {
            val saldo = usuario!!.saldo
            if (validarEntrada()){
            val repositorioTicket = TicketRepositorio
            val hora: Int = binding.etHora.text.toString().toInt()
            val minutos: Int = binding.etMinutos.text.toString().toInt()
            val estadia: Int = binding.etEstadia.text.toString().toInt()
            val patente: String = binding.etPatente.text.toString()
            val montoBruto: Double = valorHora * estadia
            var costo: Double?= pesado.calcularRecarga(valorHora, estadia, LocalDate.now())

                val nuevoTicket = Ticket(id++, codigoTicket, LocalDate.now(),LocalTime.of(hora,
                    minutos
                ),"Pesado", patente,
                    estadia, montoBruto,costo!!)

                          if (saldo!! >= costo){
                    codigoTicket++
                    repositorioTicket.agregar(nuevoTicket)
                    usuario.saldo = saldo?.let { it1 -> UsuarioRepositorio.restarSaldo(it1, costo.toDouble()) }
                    val volverActivityIntent = Intent(this, loginActivity::class.java)

                    volverActivityIntent.putExtra("nombreUsuario", usuario.nombreUsuario)
                    volverActivityIntent.putExtra("contraseña", usuario.password)

                    Toast.makeText(this,"Se Registro Exitosamente el ticket",Toast.LENGTH_SHORT).show()
                    startActivity(volverActivityIntent)
                }

                if (saldo < costo){
                    Toast.makeText(this,"Saldo insuficiente,no puede realizar el pago",Toast.LENGTH_SHORT).show()
                    finish()
                }

            }
            else{
                Toast.makeText(this,"Debe completar todos los campos",Toast.LENGTH_SHORT).show()
            }


        }

    }
    private fun validarEntrada():Boolean{
        return binding.etHora.text.isNotEmpty() && binding.etMinutos.text.isNotEmpty() && binding.etEstadia.text.isNotEmpty() &&
                binding.etPatente.text.isNotEmpty()
    }

}



