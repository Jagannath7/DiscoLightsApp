package com.example.discolights


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        if(sensorManager == null){
            Toast.makeText(this, "Could not get sensors", Toast.LENGTH_SHORT).show()
            finish()
        }
        else{
            val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
            sensors.forEach{
                Log.d("Sensors","""
                    ${it.name} | ${it.stringType} | ${it.vendor}
                """.trimIndent())
            }
        }
    }
}