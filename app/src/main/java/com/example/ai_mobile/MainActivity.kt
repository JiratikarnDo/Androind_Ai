package com.example.ai_mobile

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val optionGender = arrayOf("ชาย", "หญิง")
        val adaterGender = ArrayAdapter(this, android.R.layout.simple_spinner_item, optionGender)
        adaterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val optionCp = arrayOf("ไม่เจ็บ", "เริ่มเจ็บ", "เจ็บ", "เจ็บมาก")
        val adaterCp = ArrayAdapter(this, android.R.layout.simple_spinner_item, optionCp)
        adaterCp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // หาปุ่ม Button
        val nextButton: Button = findViewById(R.id.NextButton)

        val Age = findViewById<EditText>(R.id.Age)
        val spinner_Gender = findViewById<Spinner>(R.id.spinner_Gender)
        val spinner_Cp = findViewById<Spinner>(R.id.spinner_Cp)
        val BloodPressure = findViewById<EditText>(R.id.BloodPressure)

        spinner_Gender.adapter = adaterGender
        spinner_Cp.adapter = adaterCp

        nextButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("age", Age.text.toString())
            intent.putExtra("bloodPressure", BloodPressure.text.toString())
            intent.putExtra("gender", spinner_Gender.selectedItemPosition.toString())
            intent.putExtra("cp", spinner_Cp.selectedItemPosition.toString())
            startActivity(intent)
        }
    }
}
