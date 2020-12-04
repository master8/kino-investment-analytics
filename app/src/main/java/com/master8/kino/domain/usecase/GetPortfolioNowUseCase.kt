package com.master8.kino.domain.usecase

import com.master8.kino.domain.entity.*

class GetPortfolioNowUseCase {

    operator fun invoke(): Portfolio {
        return Portfolio(
            InstrumentsGroup.all
                .map { group ->
                    PortfolioPart(
                        group,
                        getPositions(group.instruments)
                    )
                }
        )
    }

    private fun getPositions(instruments: List<Instrument>): List<PortfolioPosition> {
        return instruments.map { tempPositionsData[it]!! }
    }
}


val tempPositionsData = mapOf(
    Instrument.FXCN to PortfolioPosition(
        Instrument.FXCN,
        50.7032649127352,
        53.54878166543277,
        7
    ),
    Instrument.FXUS to PortfolioPosition(
        Instrument.FXUS,
        59.5873603288523,
        64.69887662149547,
        2
    ),
    Instrument.FXIT to PortfolioPosition(
        Instrument.FXIT,
        105.99351487047174,
        120.55235458847008,
        1
    ),
    Instrument.FXIM to PortfolioPosition(
        Instrument.FXIT,
        .0,
        .0,
        0
    ),
    Instrument.FXGD to PortfolioPosition(
        Instrument.FXGD,
        12.353537112048155,
        12.83419705797148,
        10
    ),

    Instrument.BAC to PortfolioPosition(
        Instrument.BAC,
        25.74,
        28.83,
        1
    ),
    Instrument.PZZA to PortfolioPosition(
        Instrument.PZZA,
        87.32,
        81.97,
        3
    ),
    Instrument.AAPL to PortfolioPosition(
        Instrument.AAPL,
        121.32,
        122.87,
        2
    ),
    Instrument.ATVI to PortfolioPosition(
        Instrument.ATVI,
        77.05,
        78.94,
        2
    ),

    Instrument.TECH to PortfolioPosition(
        Instrument.TECH,
        0.0889,
        0.0936,
        346
    ),
    Instrument.TGLD to PortfolioPosition(
        Instrument.TGLD,
        0.0798,
        0.0762,
        4054
    ),
    Instrument.TUSD to PortfolioPosition(
        Instrument.TUSD,
        0.1038,
        0.1064,
        3609
    )
)