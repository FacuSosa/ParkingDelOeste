package entidades

import com.example.trabajonahufacu.TicketRepositorio
import com.example.trabajonahufacu.UsuarioRepositorio
import java.time.LocalDate
import java.time.LocalTime

data class Ticket(
    val idCliente: Long,
    var codigoTicket: Long,
    val fechaIngreso: LocalDate,
    val horaIngreso: LocalTime,
    val vehiculo: String,
    val patente: String,
    val estadia: Int,
    val montoBruto: Double,
    val montoFinal: Double

)


