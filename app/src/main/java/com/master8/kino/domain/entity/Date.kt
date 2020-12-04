package com.master8.kino.domain.entity

data class Date(
    private val value: String
) {
    constructor(dateInMillis: Long) : this("")//TODO

    override fun toString(): String {
        return value
    }
}