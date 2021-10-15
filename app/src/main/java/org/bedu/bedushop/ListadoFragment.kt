package org.bedu.bedushop

import RecyclerAdapter


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.PointerIcon
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread


class ListadoFragment : Fragment() {
    private lateinit var mAdapter : RecyclerAdapter
    private var listener : (Product) ->Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout de fragment
            return inflater.inflate(R.layout.fragment_recycler, container,false) //Apunta al fragmento que tiene el recyclerView
    }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
                super.onActivityCreated(savedInstanceState)
                setUpRecyclerView()//Aquí abriría otro thread?
    }

    fun setListener(l: (Product) ->Unit){
        listener = l
    }

    //configuramos lo necesario para desplegar el RecyclerView
    private fun setUpRecyclerView() {
            var list: MutableList<Product> = mutableListOf()
            val job = CoroutineScope(Main).launch{
                list = getApi()
                Log.d("setUpRecycler", list.toString())
                recyclerProducts.setHasFixedSize(true)
                recyclerProducts.layoutManager = LinearLayoutManager(activity)
                //seteando el Adapter
                mAdapter = RecyclerAdapter(requireActivity(), list, listener)
                //asignando el Adapter al RecyclerView
                recyclerProducts.adapter = mAdapter
        }
        CoroutineScope(Main).launch {
            job.join()
        }
    }

   fun getApi():MutableList<Product> {
        var products: MutableList<Product> = mutableListOf()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            val service = retrofit.create<WebServices>(WebServices::class.java)
            service.getAllProducts().enqueue(object : Callback<MutableList<Product>> {
                override fun onResponse(
                    call: Call<MutableList<Product>>,
                    response: Response<MutableList<Product>>
                ) {
                    products = response.body()!!
                    Log.d("json?", products.toString())
                    Log.i("GsonConverter", Gson().toJson(products))
                }

                override fun onFailure(call: Call<MutableList<Product>>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        return products
    }
}
/*
suspend fun getApi():String{
        var productosJson: String? = ""
        //instanciando al cliente
        val okHttpClient = OkHttpClient()

        //obteniendo la url completa
        val url = "https://fakestoreapi.com/products"

        //El objeto Request contiene todos los parámetros de la petición (headers, url, body etc)
        val request = Request.Builder()
            .url(url)
            .build()

        //enviando y recibiendo las llamadas de forma asíncrona
        okHttpClient.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("Error",e.toString())
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                productosJson = response.body?.string()
                Log.d("Response: ", productosJson.toString())
                Gson().fromJson(productosJson, object : TypeToken<MutableList<Product>>(){}.type)
            }

        })
        return productosJson.toString()
    }


    suspend fun getProducts(): MutableList<Product>{
        var jsonString : MutableList<Product> = mutableListOf()
        withContext(Main){
            val jsonApi = getApi()

            Log.d("jsonString", jsonString.toString())
        }
        Log.d("jsonString", jsonString.toString())
        return jsonString
    }
---------------------------------------------
var products: MutableList<Product> = mutableListOf()
val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://fakestoreapi.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val service = retrofit.create<WebServices>(WebServices::class.java)
service.getAllProducts().enqueue(object :Callback<MutableList<Product>>{
    override fun onResponse(call: Call<MutableList<Product>>, response: Response<MutableList<Product>>) {
        products = response.body()!!
        Log.d("json?", products.toString())
        Log.i("GsonConverter", Gson().toJson(products))
    }

    override fun onFailure(call: Call<MutableList<Product>>, t: Throwable) {
        t?.printStackTrace()
    }return products
})*/

