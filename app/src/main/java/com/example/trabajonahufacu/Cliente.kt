package entidades

import java.time.LocalDate

abstract class Cliente(
    val id: Long,
    val nombre: String,
    val apellido: String,
    var saldo: Double?,
    val fechaAlta: LocalDate)
