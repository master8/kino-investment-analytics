package com.master8.kino.ui

import android.icu.text.NumberFormat
import java.util.*

val CURRENCY_FORMATTER = NumberFormat.getInstance(Locale.US, NumberFormat.CURRENCYSTYLE)
val PERCENT_FORMATTER = NumberFormat.getInstance(Locale.US, NumberFormat.PERCENTSTYLE)
    .apply {
        maximumFractionDigits = 2
    }