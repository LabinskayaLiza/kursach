package com.example.kursach

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

object Network {
    val BASE_URL = "http://109.172.94.7:8080"
    val CAFE_URL = "$BASE_URL/cafe"
    val LOGIN_URL = "$BASE_URL/login"
    val LOGOUT_URL = "$BASE_URL/logout"
    val REGISTRATION_URL = "$BASE_URL/registration"

    val httpClient = HttpClient(OkHttp) {
        install(ContentNegotiation){
            gson ()
        }
    }
}