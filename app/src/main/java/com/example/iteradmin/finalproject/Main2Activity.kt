package com.example.iteradmin.finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.textclassifier.TextLinks
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class Main2Activity : AppCompatActivity() {

    private val user = "123abcd"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val e = findViewById<EditText>(R.id.edit)
        val b = findViewById<Button>(R.id.button)
        val t = findViewById<TextView>(R.id.report)

        b.setOnClickListener {
            val str = e.text.toString()
            val url = "https://api.openweathermap.org/data/2.5/weather?q=jamshedpur"+str+"&appid=8c4573fb706135d588b53bcf23c37480"
            val queue = Volley.newRequestQueue(this)
            val jsonObjectRequest:JsonObjectRequest=
                    JsonObjectRequest(Request.Method.GET,url,null,Response.Listener {
                        response ->
                        val coord:JSONObject = response.getJSONObject("coord")
                        val weather:JSONArray = response.getJSONArray("weather")
                        val code:String = response.getString("name")

                        val report:String = "latitude: "+coord.get("lat")+" longitude: "+
                                coord.get("lon")+"\nDescription: "+weather.getJSONObject(0)
                                .get("description")+"\nName: "+code
                        t.text = report
                    },
                            Response.ErrorListener {

                            })
        }




    }
}
