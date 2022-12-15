package repositorios

import java.time.LocalDate
import java.time.Period

class Beneficio {

    private fun obtenerAntiguedad(fechaAlta: LocalDate): Long {
        val fechaActual = LocalDate.now()
        return Period.between(fechaAlta, fechaActual).toTotalMonths()
    }

    fun calcularBeneficio(fechaAlta: LocalDate, costo: Double): Double{
        var costoDescuento = costo
        when(obtenerAntiguedad(fechaAlta)){
            in 0..1 -> costoDescuento = costo * 0.50
            in 2..6 -> costoDescuento = costo * 0.25
        }
        return costoDescuento
    }
}