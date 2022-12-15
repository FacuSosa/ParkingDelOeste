package com.example.trabajonahufacu

import entidades.Cliente

object ClienteRepositorio {
    private val clientes = mutableListOf<Cliente>()



    fun agregar(cliente: Cliente) {
        clientes.add(cliente)
    }

    fun eliminar(cliente: Cliente?) {
        clientes.remove(cliente)
    }

    fun obtenerPorId(id: Long): Cliente? {
        var guardar : Cliente? = null
        for(cliente in clientes){
            if(id == cliente.id){
                guardar = cliente
            }
        }
        return guardar
    }

    fun buscar(apellido: String, nombre: String): Cliente? {
       var guardar : Cliente? = null
        for(cliente in clientes){
            if(apellido == cliente.apellido && nombre == cliente.nombre){
                guardar = cliente
            }
        }
        return guardar
    }

}