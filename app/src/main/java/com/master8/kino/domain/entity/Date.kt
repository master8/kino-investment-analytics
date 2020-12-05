package com.master8.kino.domain.entity

import java.text.SimpleDateFormat
import java.util.*

data class Date(
    private val value: String
) {

    override fun toString(): String {
        return value
    }

    private fun toMillis(): Long = dateFormatter.parse(value)!!.time

    operator fun compareTo(other: Date): Int {
        if (this.value == other.value) {
            return 0
        }

        return this.toMillis().compareTo(other.toMillis())
    }

    val nextMinute: Date
        get() {
            calendar.time = dateFormatter.parse(value)!!
            calendar.add(Calendar.MINUTE, 1)

            return Date(dateFormatter.format(calendar.time))
        }

    fun minus(numberOfDays: Int): Date {
        calendar.time = dateFormatter.parse(value)!!
        calendar.add(Calendar.DAY_OF_YEAR, -numberOfDays)

        return Date(dateFormatter.format(calendar.time))
    }

    fun copyDayFrom(other: Date): Date {
        calendar.time = dateFormatter.parse(other.value)!!
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        calendar.time = dateFormatter.parse(value)!!
        calendar.set(year, month, day)

        return Date(dateFormatter.format(calendar.time))
    }

    val isMonday: Boolean
        get() = isDayOfWeek(Calendar.MONDAY)

    val isSaturday: Boolean
        get() = isDayOfWeek(Calendar.SATURDAY)

    val isSunday: Boolean
        get() = isDayOfWeek(Calendar.SUNDAY)

    private fun isDayOfWeek(dayOfWeek: Int): Boolean {
        calendar.time = dateFormatter.parse(value)!!
        return calendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek
    }

    companion object {
        private val calendar = Calendar.getInstance()
        private val dateFormatter by lazy { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX") }

        val now: Date
            get() {
                return Date(dateFormatter.format(System.currentTimeMillis()))
            }
    }
}