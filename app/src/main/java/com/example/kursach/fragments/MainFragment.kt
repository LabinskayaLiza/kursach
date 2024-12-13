package com.example.kursach.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.kursach.R

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bF1= view.findViewById<Button>(R.id.login_button)
        val bF2= view.findViewById<TextView>(R.id.signup)
        val loginEditText = view.findViewById<EditText>(R.id.login_user)
        val passwordEditText = view.findViewById<EditText>(R.id.login_password)
        val controller = findNavController()
        loginEditText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && loginEditText.text.toString() == "Login") {
                loginEditText.setText("")
            }
        }
        passwordEditText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && passwordEditText.text.toString() == "Password") {
                passwordEditText.setText("")
            }
        }
        bF1.setOnClickListener{
            val login = loginEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            } else {
                controller.navigate(R.id.fragment2)
            }
        }
        bF2.setOnClickListener{controller.navigate(R.id.fragment1) }
    }
}