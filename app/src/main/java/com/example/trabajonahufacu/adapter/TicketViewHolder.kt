package com.example.trabajonahufacu.adapter

import android.icu.text.DecimalFormat
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajonahufacu.R
import com.example.trabajonahufacu.databinding.ItemTicketBinding
import entidades.Ticket

class TicketViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val binding = ItemTicketBinding.bind(view)
    var saldoFormato= DecimalFormat("0.00")

    fun render(ticketModel: Ticket){
        binding.tvIDCliente.text ="Id Cliente:" + ticketModel.idCliente.toString()
        binding.tvCodidoTicket.text ="Codigo Ticket:" + ticketModel.codigoTicket.toString()
        binding.tvFechaIngreso.text ="Fecha Ingreso:" + ticketModel.fechaIngreso.toString()
        binding.tvHoraIngreso.text = "Hora Ingreso:" + ticketModel.horaIngreso.toString()
        binding.tvVehiculo.text ="Vehiculo:" + ticketModel.vehiculo
        binding.tvPatente.text ="Patente:" + ticketModel.patente
        binding.tvEstadia.text = "Estadia:" + ticketModel.estadia.toString()
        binding.tvMontoBruto.text ="Monto Bruto:" + saldoFormato.format(ticketModel.montoBruto.toString().toDouble())
        binding.tvMontoFinal.text ="Monto Final:" + saldoFormato.format(ticketModel.montoFinal.toString().toDouble())
    }
}