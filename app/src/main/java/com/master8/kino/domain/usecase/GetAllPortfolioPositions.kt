package com.master8.kino.domain.usecase

import com.master8.kino.domain.PortfolioRepository
import com.master8.kino.domain.entity.Instrument
import com.master8.kino.domain.entity.PortfolioPosition

class GetAllPortfolioPositions(
    private val repository: PortfolioRepository
) {

    suspend operator fun invoke(): List<PortfolioPosition> {
        return repository.getPortfolioPositions(
            Instrument.FXCN,
            Instrument.FXUS,
            Instrument.FXIT,
            Instrument.FXGD
        )
    }
}