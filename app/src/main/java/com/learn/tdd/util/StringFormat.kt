package com.learn.tdd.util

import java.text.NumberFormat
import java.util.*

class StringFormat {

    companion object {
        fun printThousand(number: Int): String {
            return NumberFormat.getNumberInstance(Locale.US).format(number)
        }
    }

}