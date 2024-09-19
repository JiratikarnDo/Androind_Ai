package com.example.ai_mobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val age = intent.getStringExtra("age")
        val bloodPressure = intent.getStringExtra("bloodPressure")
        val gender = intent.getStringExtra("gender")
        val cp = intent.getStringExtra("cp")

        val Cholesterol = findViewById<EditText>(R.id.Cholesterol)
        val HeartRate = findViewById<EditText>(R.id.HeartRate)
        val OldPeak = findViewById<EditText>(R.id.OldPeak)

        val nextButton = findViewById<Button>(R.id.button2)

        nextButton.setOnClickListener {
            val url = "http://192.168.1.164:3000/api/heart"

            val okHttpClient = OkHttpClient()
            val fromBody : RequestBody = FormBody.Builder()
                .add("age", age.toString())
                .add("sex", gender.toString())
                .add("cp", cp.toString())
                .add("trestbps", bloodPressure.toString())
                .add("chol", Cholesterol.text.toString())
                .add("thalach", HeartRate.text.toString())
                .add("oldpeak", OldPeak.text.toString())
                .build()
            val request : Request = Request.Builder()
                .url(url)
                .post(fromBody)
                .build()
            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                val obj = JSONObject(response.body!!.string())
                val result = obj["prediction"].toString()
                val intent = Intent(this, MainActivity3::class.java)
                intent.putExtra("result", result)
                startActivity(intent)

            }else{
                Toast.makeText(this, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
}