package com.example.visualisation

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    private lateinit var ppm: Sensor

    private lateinit var textView: TextView

    private var testInt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        ppm = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_PRESSURE) {
            val pressureValue = event.values[0]
            Log.i("SensorData", "Pressure value: $pressureValue")
            textView.text = "$testInt. Pressure value: $pressureValue"
            testInt++
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onResume() {
        super.onResume()
            sensorManager.registerListener(this, ppm, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }


}