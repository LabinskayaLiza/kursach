package com.example.kursach.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kursach.Cafe
import com.example.kursach.CafeAdapter
import com.example.kursach.R
class Fragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rcView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val cafeItems = listOf(
            Cafe("Cafe 1", "Description 1", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 2", "Description 2", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 3", "Description 3", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 1", "Description 1", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 2", "Description 2", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 3", "Description 3", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 1", "Description 1", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 2", "Description 2", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 3", "Description 3", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 1", "Description 1", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 2", "Description 2", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 3", "Description 3", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 1", "Description 1", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 2", "Description 2", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 3", "Description 3", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 1", "Description 1", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 2", "Description 2", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 3", "Description 3", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 1", "Description 1", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 2", "Description 2", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 3", "Description 3", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 1", "Description 1", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 2", "Description 2", "app/src/main/res/drawable/img.png"),
            Cafe("Cafe 3", "Description 3", "app/src/main/res/drawable/img.png"),
            // ... more cafe items
        )
        val adapter = CafeAdapter(cafeItems)
        recyclerView.adapter = adapter
        return view
    }
}