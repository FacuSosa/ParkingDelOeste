package entidades

import java.time.LocalDate

class Usuario(
    id: Long,
    nombre: String,
    apellido: String,
    saldo: Double,
    fechaAlta:LocalDate,
    val password: String,
    val nombreUsuario: String
) : Cliente(id, nombre, apellido,saldo,fechaAlta)


