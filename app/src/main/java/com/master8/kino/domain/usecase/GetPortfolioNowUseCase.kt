package com.master8.kino.domain.usecase

import com.master8.kino.domain.PortfolioRepository
import com.master8.kino.domain.entity.*

class GetPortfolioNowUseCase(
    private val repository: PortfolioRepository
) {

    suspend operator fun invoke(): Portfolio {
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

    private suspend fun getPositions(instruments: List<Instrument>): List<PortfolioPosition> {
        return instruments.map {
            buildPosition(it)
        }
    }

    private suspend fun buildPosition(instrument: Instrument): PortfolioPosition {
        val operations = repository.getBuyOperations(
            instrument, repository.getStartDate(instrument), repository.getLastAvailableDate()
        )

        val lots = operations.lots
        val startAveragePrice = if (lots > 0) operations.totalPrice() / lots else Usd(.0)
        val endAveragePrice = getNowAveragePrice(instrument)

        return PortfolioPosition(
            instrument = instrument,
            startAveragePrice,
            endAveragePrice,
            lots
        )
    }

    private suspend fun getNowAveragePrice(instrument: Instrument): Usd {
        val lastAvailableDate = repository.getLastAvailableDate()
        return repository.getPriceAt(lastAvailableDate, instrument)
            .toUsd(lastAvailableDate)
    }

    private val List<BuyOperation>.lots get() = this.sumBy { it.quantityExecuted }

    private suspend fun List<BuyOperation>.totalPrice(): Usd {
        return this.sumByUsd {
            it.price.toUsd(it.date) * it.quantityExecuted
        }
    }

    private suspend fun Currency.toUsd(date: Date): Usd {
        return when (this) {
            is Usd -> return this
            is Rub -> repository.convertToUsdAt(date, this)
        }
    }
}