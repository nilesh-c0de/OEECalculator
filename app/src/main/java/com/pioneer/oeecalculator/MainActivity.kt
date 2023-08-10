package com.pioneer.oeecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtPtt = findViewById<EditText>(R.id.edt_ppt)
        val edtBreaks = findViewById<EditText>(R.id.edt_breaks)
        val edtDowntime = findViewById<EditText>(R.id.edt_downtime)
        val edtRate = findViewById<EditText>(R.id.edt_rate)
        val edtOutput = findViewById<EditText>(R.id.edt_output)
        val edtScrap = findViewById<EditText>(R.id.edt_scrap)
        val submit = findViewById<Button>(R.id.calculate)
        val result = findViewById<TextView>(R.id.tv_result)


        submit.setOnClickListener {
            val production_total_time = edtPtt.text.toString().toInt()
            val total_breaks = edtBreaks.text.toString().toInt()
            val down_time = edtDowntime.text.toString().toInt()
            val rate_per_min = edtRate.text.toString().toInt()
            val total_products = edtOutput.text.toString().toInt()
            val scrap_products = edtScrap.text.toString().toInt()

            val waste_time = total_breaks + down_time
            val run_time = production_total_time - waste_time
            val avaibility = (run_time.toDouble() / production_total_time.toDouble()) * 100

            val c = (rate_per_min.toDouble() * run_time.toDouble())
            val performance = (total_products.toDouble() / c) * 100

            val speed_loss = c - total_products
            val good_products = total_products - scrap_products
            val quality = (good_products.toDouble() / total_products.toDouble()) * 100

            val oee = ((avaibility/100) * (performance/100) * (quality/100)) * 100
            result.text = "Potential Production Time(A) = $production_total_time\n" +
                    "Break(bt) = $total_breaks\n" +
                    "Downtime(dt) = $down_time\n" +
                    "Time Loss(w = bt+dt) = $waste_time\n" +
                    "Actual Production Time(B = A-w) = $run_time\n\n" +
                    "Theoretical Output(C = $rate_per_min * B) = $c\n" +
                    "Actual Output(D,E) = $total_products\n" +
                    "Speed Loss(C-D) = $speed_loss\n" +
                    "Good Product Output(F = D-S) = $good_products\n" +
                    "Bad Product Output(S)= $scrap_products\n\n" +
                    "Availability(B/A * 100) = $avaibility\n" +
                    "Performance(D/C * 100) = $performance\n" +
                    "Quality(F/E * 100) = $quality\n\n" +
                    "OEE (A*P*Q*100) \n= $oee"
        }
    }
}