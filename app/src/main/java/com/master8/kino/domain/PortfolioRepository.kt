package com.master8.kino.domain

import com.master8.kino.domain.entity.Instrument
import com.master8.kino.domain.entity.PortfolioPosition

interface PortfolioRepository {

    suspend fun getPortfolioPositions(vararg instruments: Instrument): List<PortfolioPosition>
}