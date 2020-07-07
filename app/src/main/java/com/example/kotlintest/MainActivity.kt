package com.example.kotlintest

import android.content.res.Configuration
import android.os.Bundle
import android.view.Surface
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintest.entities.ChangeCoinItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup.check(R.id.radioButton)
        callServiceGetEUR()

        radioGroup.setOnCheckedChangeListener { group, checkedID ->
            if (checkedID == R.id.radioButton)
                callServiceGetEUR()

            if (checkedID == R.id.radioButton2)
                callServiceGetUSD()
        }

    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
        when (windowManager.defaultDisplay.orientation) {
            Surface.ROTATION_0 -> {
                arrow_right.visibility = GONE
                arrow_left.visibility = GONE
            }
            Surface.ROTATION_90 -> {
                arrow_right.visibility = GONE
                arrow_left.visibility = View.VISIBLE
            }
            Surface.ROTATION_180 -> {
                arrow_right.visibility = GONE
                arrow_left.visibility = GONE
            }
            else -> {
                arrow_right.visibility = View.VISIBLE
                arrow_left.visibility = GONE
            }
        }
    }


    private fun callServiceGetUSD() {
        val usdService: ApiService = ApiClass.getApiClass().create(ApiService::class.java)
        val result: Call<ChangeCoinItem> = usdService.getUSD()
        var listUSD: List<ChangeCoinItem>?=null

        result.enqueue(object : Callback<ChangeCoinItem> {
            override fun onFailure(call: Call<ChangeCoinItem>, t: Throwable) {
                Toast.makeText(this@MainActivity,"error",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ChangeCoinItem>,
                response: Response<ChangeCoinItem>
            ) {
                valueCad.text= response.body()?.rates?.CAD.toString()
                valueGbp.text= response.body()?.rates?.GBP.toString()
                valueMxn.text= response.body()?.rates?.MXN.toString()
            }
        })
    }

    private fun callServiceGetEUR() {
        val eurService: ApiService = ApiClass.getApiClass().create(ApiService::class.java)
        val result: Call<ChangeCoinItem> = eurService.getEUR()
        var listEUR: List<ChangeCoinItem>?=null
        result.enqueue(object : Callback<ChangeCoinItem> {
            override fun onFailure(call: Call<ChangeCoinItem>, t: Throwable) {
                Toast.makeText(this@MainActivity,"error",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ChangeCoinItem>,
                response: Response<ChangeCoinItem>
            ) {
                valueCad.text= response.body()?.rates?.CAD.toString()
                valueGbp.text= response.body()?.rates?.GBP.toString()
                valueMxn.text= response.body()?.rates?.MXN.toString()
            }
        })
    }
}