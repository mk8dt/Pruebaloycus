package com.mk8.domain.usecase

import com.mk8.data.Either
import com.mk8.data.app.EventModel
import com.mk8.domain.provider.EventProvider
import com.mk8.domain.usecase.base.UseCaseWithParams

class GetEventByIdentifierUseCase(
    private val eventProvider: EventProvider
) : UseCaseWithParams<Either<String,EventModel>,Long> {

    override suspend fun execute(params: Long): Either<String, EventModel> =
        eventProvider.getEventByIdentifier(params)
}