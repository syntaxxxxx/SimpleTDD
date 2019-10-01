package com.learn.tdd.util

import org.junit.Assert.assertEquals
import org.junit.Test

class StringFormatTest {

    @Test
    fun printThousandTest() {
        val number = 9000
        val text = StringFormat.printThousand(number)
        assertEquals("9,000", text)
    }

}