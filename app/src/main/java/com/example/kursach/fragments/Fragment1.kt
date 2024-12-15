package com.example.kursach.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.kursach.Network
import com.example.kursach.R
import com.example.kursach.User
import com.google.gson.JsonElement
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody

class Fragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bF1= view.findViewById<Button>(R.id.button_signin)
        val bF2= view.findViewById<TextView>(R.id.LogIn)
        val loginEditText = view.findViewById<EditText>(R.id.user_login)
        val nameEditText = view.findViewById<EditText>(R.id.user_name)
        val passwordEditText = view.findViewById<EditText>(R.id.user_password)
        val controller = findNavController()

        nameEditText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus && nameEditText.text.toString() == "Name") {
                nameEditText.setText("")
            }
        }
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
            val name = nameEditText.text.toString().trim()

            if (login.isEmpty() || password.isEmpty() || name.isEmpty() || login == "Login" || name == "Name" || password == "Password") {
                Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val response: HttpResponse = Network.httpClient.post("http://109.172.94.7:8080/registration") {
                            contentType(ContentType.Application.Json)
                            setBody(User(name, login, password))
                        }
                        val data: JsonElement = response.body()
                        Log.i("NETWORK_ANS",data.toString())
                        if (data.asJsonObject["status"].asBoolean) {
                            withContext(Dispatchers.Main) {
                                loginEditText.setText("Login")
                                passwordEditText.setText("Password")
                                nameEditText.setText("Name")
                                controller.navigate(R.id.mainFragment)
                            }
                        }
                    }catch (err: NoTransformationFoundException){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                requireContext(),
                                "Такой логин уже зарегестрирован",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (err: ServerResponseException){
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Ошибка серверва", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
        bF2.setOnClickListener{controller.navigate(R.id.mainFragment) }
    }
}