package repositorios

import java.time.LocalDate

class Pesado{
    fun calcularRecarga(valorHora: Double, estadia: Int, fechaIngreso: LocalDate): Double {
        val costoTotal: Double
        var nuevoValorHora: Double

        return if(fechaIngreso.dayOfWeek.name == "SATURDAY" || fechaIngreso.dayOfWeek.name == "SUNDAY"){
            nuevoValorHora = valorHora
            nuevoValorHora += valorHora * 0.1
            costoTotal = nuevoValorHora * estadia
            costoTotal
        }else {
            nuevoValorHora = valorHora
            nuevoValorHora += valorHora * 0.05
            costoTotal = nuevoValorHora * estadia
            costoTotal
        }

    }

}