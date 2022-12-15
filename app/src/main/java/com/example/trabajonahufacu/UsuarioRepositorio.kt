package com.example.trabajonahufacu

import entidades.Ticket
import entidades.Usuario
import java.time.LocalDate

object UsuarioRepositorio {
    val usuarios = mutableListOf<Usuario>()

    init {
        usuarios.add(Usuario(1, "facu", "t", 200.0, LocalDate.now(), "123", "facu123"))
        usuarios.add(Usuario(1, "f", "t", 220.0, LocalDate.now(), "f", "f"))
    }

    fun agregar(usuario: Usuario) {
        usuarios.add(usuario)
    }

    fun recargarSaldo(saldoActual : Double, agregar: Double) : Double{
        return (saldoActual + agregar)
    }

    fun restarSaldo(saldoActual: Double,saldoARestar:Double):Double{
        return (saldoActual-saldoARestar)
    }

    fun eliminar(usuario: Usuario) {
        usuarios.remove(usuario)
    }

   fun existe(nombreUsuario: String): Boolean {
       var existe = false

       for (usuario in usuarios){
           if(nombreUsuario.equals(usuario.nombreUsuario)){
               existe = true
           }
       }
       return existe
    }


    fun iniciar(nombreUsuario: String, password: String): Usuario? {
        var guardar : Usuario? = null
        for(usuario in usuarios){
            if(nombreUsuario == usuario.nombreUsuario && password == usuario.password){
                guardar = usuario
            }
        }
        return guardar
    }


}