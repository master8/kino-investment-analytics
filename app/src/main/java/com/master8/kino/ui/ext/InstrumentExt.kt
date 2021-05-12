package com.master8.kino.ui.ext

import com.master8.kino.R
import com.master8.kino.domain.entity.Instrument

val Instrument.image: Int
    get() {
        return when(this) {
            Instrument.FXGD -> R.drawable.im_fxgd
            Instrument.FXUS -> R.drawable.im_fxus
            Instrument.FXCN -> R.drawable.im_fxcn
            Instrument.FXIT -> R.drawable.im_fxit
            Instrument.FXIM -> R.drawable.im_fxit
            Instrument.BAC -> R.drawable.im_bac
            Instrument.PZZA -> R.drawable.im_pzza
            Instrument.AAPL -> R.drawable.im_aapl
            Instrument.ATVI -> R.drawable.im_atvi
            Instrument.TECH -> R.drawable.im_tech
            Instrument.TGLD -> R.drawable.im_tgld
            Instrument.TUSD -> R.drawable.im_tusd
            Instrument.TSPX -> R.drawable.im_tspx
            Instrument.USD -> throw RuntimeException("Unsupported image for this instrument")
            Instrument.FXWO -> R.drawable.im_fxwo
            Instrument.FXDM -> R.drawable.im_fxwo
        }
    }