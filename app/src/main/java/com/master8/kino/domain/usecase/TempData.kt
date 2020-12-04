package com.master8.kino.domain.usecase

import com.master8.kino.domain.entity.Instrument
import com.master8.kino.domain.entity.PortfolioPosition
import com.master8.kino.domain.entity.usd

val tempPositionsData = mapOf(
    Instrument.FXCN to PortfolioPosition(
        Instrument.FXCN,
        50.7032649127352.usd,
        53.54878166543277.usd,
        7
    ),
    Instrument.FXUS to PortfolioPosition(
        Instrument.FXUS,
        59.5873603288523.usd,
        64.69887662149547.usd,
        2
    ),
    Instrument.FXIT to PortfolioPosition(
        Instrument.FXIT,
        105.99351487047174.usd,
        120.55235458847008.usd,
        1
    ),
    Instrument.FXIM to PortfolioPosition(
        Instrument.FXIM,
        .0.usd,
        .0.usd,
        0
    ),
    Instrument.FXGD to PortfolioPosition(
        Instrument.FXGD,
        12.353537112048155.usd,
        12.83419705797148.usd,
        10
    ),

    Instrument.BAC to PortfolioPosition(
        Instrument.BAC,
        25.74.usd,
        28.83.usd,
        1
    ),
    Instrument.PZZA to PortfolioPosition(
        Instrument.PZZA,
        87.32.usd,
        81.97.usd,
        3
    ),
    Instrument.AAPL to PortfolioPosition(
        Instrument.AAPL,
        121.32.usd,
        122.87.usd,
        2
    ),
    Instrument.ATVI to PortfolioPosition(
        Instrument.ATVI,
        77.05.usd,
        78.94.usd,
        2
    ),

    Instrument.TECH to PortfolioPosition(
        Instrument.TECH,
        0.0889.usd,
        0.0936.usd,
        346
    ),
    Instrument.TGLD to PortfolioPosition(
        Instrument.TGLD,
        0.0798.usd,
        0.0762.usd,
        4054
    ),
    Instrument.TUSD to PortfolioPosition(
        Instrument.TUSD,
        0.1038.usd,
        0.1064.usd,
        3609
    )
)