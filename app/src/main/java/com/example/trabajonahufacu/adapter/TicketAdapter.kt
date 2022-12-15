package com.example.trabajonahufacu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajonahufacu.R
import entidades.Ticket

class TicketAdapter(private val tickets:List<Ticket>): RecyclerView.Adapter<TicketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  TicketViewHolder(layoutInflater.inflate(R.layout.item_ticket, parent, false))
    }
    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val item = tickets[position]
        holder.render(item)
    }
    override fun getItemCount(): Int = tickets.size


}