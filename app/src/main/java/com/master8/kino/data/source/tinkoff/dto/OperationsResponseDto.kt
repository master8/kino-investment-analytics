package com.master8.kino.data.source.tinkoff.dto

data class OperationsResponseDto(
    val payload: OperationsDto
)

data class OperationsDto(
    val operations: List<OperationDto>
)

data class OperationDto(
    val operationType: String,
    val date: String,
    val quantityExecuted: Int,
    val price: Double,
    val currency: String
)