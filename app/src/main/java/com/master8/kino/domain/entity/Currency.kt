package com.master8.kino.domain.entity

sealed class Currency(
    open val value: Double
)

data class Usd(override val value: Double) : Currency(value) {

    operator fun plus(b: Usd) = Usd(this.value + b.value)

    operator fun minus(b: Usd) = Usd(value - b.value)

    operator fun times(b: Usd) = Usd(this.value * b.value)

    operator fun div(b: Usd) = Usd(this.value / b.value)


    operator fun plus(b: Int) = Usd(this.value + b)

    operator fun minus(b: Int) = Usd(value - b)

    operator fun times(b: Int) = Usd(this.value * b)

    operator fun div(b: Int) = Usd(this.value / b)

    fun toFloat(): Float {
        return value.toFloat()
    }
}


data class Rub(override val value: Double) : Currency(value)

inline fun <T> Iterable<T>.sumByUsd(selector: (T) -> Usd): Usd {
    var sum = Usd(.0)
    for (element in this) {
        sum = sum + selector(element)
    }
    return sum
}


inline val Double.usd: Usd get() = Usd(value = this)

operator fun Int.plus(b: Usd) = Usd(this + b.value)

operator fun Int.minus(b: Usd) = Usd(this - b.value)

operator fun Int.times(b: Usd) = Usd(this * b.value)

operator fun Int.div(b: Usd) = Usd(this / b.value)