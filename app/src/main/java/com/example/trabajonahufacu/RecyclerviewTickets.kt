package com.example.trabajonahufacu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajonahufacu.adapter.TicketAdapter
import com.example.trabajonahufacu.databinding.ActivityRecyclerviewTicketsBinding
import entidades.Ticket
import java.security.AccessController.getContext

class RecyclerviewTickets : AppCompatActivity() {
        private lateinit var binding: ActivityRecyclerviewTicketsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerviewTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerview()
    }

     private  fun initRecyclerview(){

       binding.recyclerTickets.layoutManager = LinearLayoutManager(this)
        binding.recyclerTickets.adapter= TicketAdapter(TicketRepositorio.tickets)
    }
}