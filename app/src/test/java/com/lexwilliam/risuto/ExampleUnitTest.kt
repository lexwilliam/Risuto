package com.lexwilliam.risuto

import junit.framework.TestCase
import org.joda.time.DateTime
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun correctDay() {
        val date = "2022-04-02T00:00:00+00:00"
        val onlyDate = date.removeRange(date.indexOf("T"), date.length)
        val dateTime = DateTime.parse(onlyDate).dayOfWeek().getAsText(Locale.ROOT)
        val currentDay = DateTime.now().dayOfWeek().getAsText(Locale.ROOT)

        TestCase.assertEquals(dateTime, currentDay)
    }
}