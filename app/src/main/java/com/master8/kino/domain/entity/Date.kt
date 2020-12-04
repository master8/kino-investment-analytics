package com.master8.kino.domain.entity

import java.text.SimpleDateFormat
import java.util.*

data class Date(
    private val value: String
) {
    constructor(dateInMillis: Long) : this("")//TODO

    override fun toString(): String {
        return value
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