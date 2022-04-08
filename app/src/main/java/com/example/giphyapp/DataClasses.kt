package com.example.giphyapp

import com.google.gson.annotations.SerializedName

data class DataResult(
     @SerializedName("data") val res : List<DataObject>
 )

data class DataObject(
    @SerializedName("images") val images:DataImage
)

class DataImage (

    @SerializedName("original") val ogImage: ogImage
)
data class ogImage(
    val url : String
)

