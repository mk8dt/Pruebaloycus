package com.mk8.domain.usecase

import com.mk8.data.Either
import com.mk8.data.app.EventModel
import com.mk8.domain.provider.EventProvider
import com.mk8.domain.usecase.base.UseCase

class GetEventListUseCase(
    private val eventProvider: EventProvider
) : UseCase<Either<String, List<EventModel>>> {

    override suspend fun execute(): Either<String, List<EventModel>> = eventProvider.getEventList()
}