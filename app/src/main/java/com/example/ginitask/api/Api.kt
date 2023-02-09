package com.example.ginitask.api

import com.example.ginitask.model.Gini
import com.example.ginitask.model.GiniNumber
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("/raw/8wJzytQX")
    suspend fun getGiniNumbers(): Response<Gini>
}