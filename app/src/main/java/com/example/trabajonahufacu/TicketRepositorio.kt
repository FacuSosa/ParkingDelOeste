package com.example.trabajonahufacu

import entidades.Ticket
import java.time.LocalDate
import java.time.LocalTime

object TicketRepositorio {
    var codigoTicket:Long = 1
    val tickets = mutableListOf<Ticket>()


init {
}

    fun agregar(ticket: Ticket) {
        tickets.add(ticket)
    }

    fun eliminar(ticket: Ticket) {
        tickets.remove(ticket)
    }

    fun obtenerPorId(id: Long): Ticket? {
        var guardar : Ticket? = null
        for(ticket in tickets){
            if(id == ticket.codigoTicket){
                guardar = ticket
            }
        }
        return guardar
    }

    fun buscar(idCliente: Long): List<Ticket> {
        return tickets.filter { ticket:Ticket -> idCliente == ticket.idCliente}
    }

    fun imprimir(tickets: List<Ticket>){
        for(ticket in tickets){
            println("--------------------------------------------------------------")
            print("Codigo de ticket: ${ticket.codigoTicket}")
            println("\t\tId de cliente: ${ticket.idCliente}")
            print("Fecha: ${ticket.fechaIngreso}")
            println("\t\tHora: ${ticket.horaIngreso}")
            print("Vehiculo: ${ticket.vehiculo}")
            println("\t\t\tEstadia: ${ticket.estadia}")
            print("Monto bruto: $${ticket.montoBruto}")
            println("\t\tMonto final: $${ticket.montoFinal}")
            println("--------------------------------------------------------------")
        }
    }

}