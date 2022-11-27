package com.example.studentcrime

import kotlin.random.Random

object Crimes {
    val crimes: List<Crime> = List(20)
    {
        val number = "$it".toInt() + 1
        Crime(
            "Crime $number",
            "Description of crime #$number",
            Random.nextInt(0, 1000),
            Random.nextBoolean()
        )
    }
}