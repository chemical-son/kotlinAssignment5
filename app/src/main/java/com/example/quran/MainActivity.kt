package com.example.quran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quran.adapter.DataModel
import com.example.quran.adapter.RecyclerAdapter
import org.json.JSONArray
import org.json.JSONObject
import java.math.RoundingMode.valueOf

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        var list = ArrayList<DataModel>()

        val inputStreamAsString = application.assets.open("fehris.json").bufferedReader().use{ it.readText() }
        val fileAsJSONArray = JSONArray(inputStreamAsString)

        var temp: JSONObject
        for (i in 0 until fileAsJSONArray.length()){
            temp = fileAsJSONArray.getJSONObject(i)

            list.add(DataModel(temp.getString("name"), temp.getInt("id")))
        }

        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        recyclerView.adapter = RecyclerAdapter(list)
    }
}