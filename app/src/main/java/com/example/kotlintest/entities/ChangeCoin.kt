package com.example.kotlintest.entities

class ChangeCoin: ArrayList<ChangeCoinItem>()

data class ChangeCoinItem(
    val rates: Rates
)

data class Rates(
    val CAD: Double,
    val GBP: Double,
    val MXN: Double
)
