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
import kotlin.math.roundToInt
import kotlin.random.Random


class MainActivity : AppCompatActivity() , SensorEventListener{

    lateinit var sensorManager: SensorManager
    lateinit var proxSensor: Sensor
    lateinit var accelSensor: Sensor

    val colors = arrayOf(Color.BLUE,Color.BLACK,Color.CYAN,Color.GREEN,Color.RED,Color.YELLOW,Color.MAGENTA)

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // no action required for now
    }

    override fun onSensorChanged(event: SensorEvent?) {
//        if(event!!.values[0]>0){
//            flProxInd.setBackgroundColor(colors[Random.nextInt(7)])
//        }
        val bgColor = Color.rgb(
            accel2color(event!!.values[0]),
            accel2color(event!!.values[1]),
            accel2color(event!!.values[2])

        )
        flAccInd.setBackgroundColor(bgColor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proxSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            this, accelSensor, 1000*1000
        )

    }

    override fun onPause() {
        sensorManager.unregisterListener(this)
        super.onPause()
    }

    private fun accel2color(accel:Float): Int = (((accel + 12)/24)*255).roundToInt()
}


