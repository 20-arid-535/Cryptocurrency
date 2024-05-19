package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.adapter.CustomAdapter
import com.example.myapplication.model.CryptoModel
import java.lang.reflect.Method

class MainActivity : AppCompatActivity() {
    val data = ArrayList<CryptoModel>()
    lateinit var adapter:CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        get()
        var rcview=findViewById<RecyclerView>(R.id.view)
        rcview.layoutManager=GridLayoutManager(this,2)

        // This will pass the ArrayList to our Adapter
        adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        rcview.adapter = adapter
    }
    fun get() :Unit{

        val url="https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest"
        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val request:JsonObjectRequest = object:JsonObjectRequest(Request.Method.GET,url,null, Response.Listener {
            response->
                       try {
                             val dataArray=response.getJSONArray("data")
                           for (i in 0 until 20){
                               val dataObject=dataArray.getJSONObject(i)
                               val name=dataObject.getString("name")

                               val symbol=dataObject.getString("symbol")

                               val quoteObject=dataObject.getJSONObject("quote")
                               val usdObject=quoteObject.getJSONObject("USD")
                               val price=String.format("$"+"%.2f",usdObject.getDouble("price"))
                               data.add(CryptoModel(name, price.toString(),symbol))
                               adapter.notifyDataSetChanged()
                           }

                       }
                           catch(e:Exception){
                               Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
                           }
        },Response.ErrorListener {
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
        })
        {
            override fun getHeaders(): Map<String, String> {
                val headers=HashMap<String,String>()
                headers["X-CMC_PRO_API_KEY"]="d588a598-b976-4688-8180-d43afeedb980"
                return headers
            }
        }
        queue.add(request)
    }
}