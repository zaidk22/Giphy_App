package com.example.giphyapp

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

const val BASE_URL = "http://api.giphy.com/v1/"
class MainActivity : AppCompatActivity() {
private lateinit var tempArrayList : MutableList<DataObject>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val gifsArrayList = mutableListOf<DataObject>()
        tempArrayList = mutableListOf<DataObject>()
        val mAdapter =GifAdapter(this,tempArrayList)
        recycler.adapter = mAdapter
            recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retroService = retrofit.create(DataService::class.java)
        retroService.getGif().enqueue(object : Callback<DataResult?>{
            override fun onResponse(call: Call<DataResult?>, response: Response<DataResult?>) {
                val body = response.body()
                if (body == null){
                    Log.d("msg","Failed")
                }

                gifsArrayList.addAll(body!!.res)

              tempArrayList.addAll(gifsArrayList)
            recycler!!.adapter!!.notifyDataSetChanged()

                searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {


                    return false
                    }


                    override fun onQueryTextChange(newText: String?): Boolean {
                        tempArrayList.clear()
                        val searchText = newText!!.lowercase()
                        if (searchText.isNotEmpty()) {
                            gifsArrayList.forEach {
                                if (it.title.lowercase()
                                        .contains(searchText)
                                ) {
                                    tempArrayList.add(it)
                                }
                            }
                            recycler!!.adapter!!.notifyDataSetChanged()

                        }
                        else{
                            tempArrayList.clear()
                            tempArrayList.addAll(gifsArrayList)
                            recycler!!.adapter!!.notifyDataSetChanged()
                        }
                        return false

                    }

                })
            }






            override fun onFailure(call: Call<DataResult?>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Failed",Toast.LENGTH_LONG).show()
            }

        })





    }

}




