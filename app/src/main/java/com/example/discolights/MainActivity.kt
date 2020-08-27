package com.example.discolights


import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() , SensorEventListener{

    lateinit var sensorManager: SensorManager
    lateinit var proxSensor: Sensor

    val colors = arrayOf(Color.BLUE,Color.BLACK,Color.CYAN,Color.GREEN,Color.RED,Color.YELLOW,Color.MAGENTA)

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // no action required for now
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event!!.values[0]>0){
            flProxInd.setBackgroundColor(colors[Random.nextInt(7)])
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proxSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)


    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            this, proxSensor, 1000*1000
        )

    }

    override fun onPause() {
        sensorManager.unregisterListener(this)
        super.onPause()
    }
}


