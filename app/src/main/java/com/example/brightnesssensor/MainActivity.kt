package com.example.brightnesssensor

import android.annotation.SuppressLint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import android.content.Context


class MainActivity : AppCompatActivity(), SensorEventListener {

    // Initialised sensorManager & two variables
    // for storing brightness value
    private lateinit var sensorManager: SensorManager
    private var brightness: Sensor? = null
    private lateinit var text: TextView
    private var temperature: Sensor? = null
    private lateinit var text2: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set default nightmode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // searched our textview id and stored it
        text = findViewById(R.id.tv_text2)
        text2 = findViewById(R.id.tv_text3)

        // setupSensor Called
        setUpSensor()

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
    }

    // Declared setupSensor function
    private fun setUpSensor() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        brightness = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
    }

    // These are two methods from sensorEventListner Interface
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val light1 = event.values[0]
            val degreesofTemperature = event.values[0]

            text.text = "Light Sensor: $light1\n${brightness(light1)}"
            text2.text = "Temperature: $degreesofTemperature\n${temperature(degreesofTemperature)}"
        }
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }


    // Created a function to show messages according to the brightness
    private fun brightness(brightness: Float): String {

        return when (brightness.toInt()) {
            0 -> "Pitch black: Don't water your plants"
            in 1..10 -> "Dark: Water your plants a lot less often"
            in 11..50 -> "Grey: Water your plants a little less often"
            in 51..5000 -> "Normal: Water your plants as normal"
            in 5001..25000 -> "Incredibly bright: Water your plants very often"
            else -> "This light will blind you!"
        }
    }

    private fun temperature(temperature: Float): String{

        return when (temperature.toInt()) {
            0 -> "0 °C"
            1 -> "1 °C"
            2 -> "2 °C"
            3 -> "3 °C"
            4 -> "4 °C"
            5 -> "5 °C"
            6 -> "6 °C"
            7 -> "7 °C"
            8 -> "8 °C"
            9 -> "9 °C"
            10 -> "10 °C"
            11 -> "11 °C"
            12 -> "12 °C"
            13 -> "13 °C"
            14 -> "14 °C"
            15 -> "15 °C"
            16 -> "16 °C"
            17 -> "17 °C"
            18 -> "18 °C"
            19 -> "19 °C"
            20 -> "20 °C"
            21 -> "21 °C"
            22 -> "22 °C"
            23 -> "23 °C"
            24 -> "24 °C"
            25 -> "25 °C"
            26 -> "26 °C"
            27 -> "27 °C"
            28 -> "28 °C"
            29 -> "29 °C"
            30 -> "30 °C"
            31 -> "31 °C"
            32 -> "32 °C"
            33 -> "33 °C"
            34 -> "34 °C"
            35 -> "35 °C"
            36 -> "36 °C"
            37 -> "37 °C"
            38 -> "38 °C"
            39 -> "39 °C"
            40 -> "40 °C"
            else -> "It's too hot!"

        }
    }

    // This is onResume function of our app
    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, brightness, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_NORMAL)
    }

    // This is onPause function of our app
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}
