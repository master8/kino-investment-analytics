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

    private companion object {
        val calendar = Calendar.getInstance()
        val dateFormatter by lazy { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX") }
    }
}