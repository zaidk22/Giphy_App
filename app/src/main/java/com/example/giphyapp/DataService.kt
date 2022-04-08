package com.example.giphyapp

import retrofit2.Call
import retrofit2.http.GET

interface DataService {
    @GET("gifs/trending?api_key=k2n4AHf4XzF54oDJhyxnllieRDqGsOCq")
    fun getGif() : retrofit2.Call<DataResult>
}