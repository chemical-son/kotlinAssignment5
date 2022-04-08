package com.example.quran

import android.content.res.AssetManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quran.adapter.DataModel
import org.json.JSONArray
import org.json.JSONObject


class ReadActivity : AppCompatActivity() {

    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        textView = findViewById(R.id.read_data)
        val data = intent.getSerializableExtra("data") as DataModel
        textView.text = ""


        var inputStreamAsString: String
        var fileAsJSONObject: JSONObject


        //اضافة بسم الله لباقي السور
        if(data.id != 1){
            if (data.id != 9){
                inputStreamAsString = application.assets.open("all_quran/0.json").bufferedReader().use{ it.readText() }
                fileAsJSONObject = JSONObject(inputStreamAsString)

                val strBesmEllah = fileAsJSONObject.getString("content")
                textView.append("${strBesmEllah.subSequence(0, strBesmEllah.length-1)}\n")
            }
        }

        inputStreamAsString = application.assets.open("all_quran/${data.id}.json").bufferedReader().use{ it.readText() }
        fileAsJSONObject = JSONObject(inputStreamAsString)

        textView.append(fileAsJSONObject.getString("content"))


    }
}