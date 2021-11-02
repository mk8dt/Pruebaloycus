package com.mk8.pruebaloycus.network

import com.mk8.data.Either
import com.mk8.data.api.EventListResponse
import com.mk8.domain.client.ApiClient

class ApiClientImpl(
    private val eventService: EventService,
    private val networkController: NetworkController
) : ApiClient {

    override suspend fun getEventList(): Either<String, EventListResponse> =
        try {
            val response = eventService.getEventList()
            networkController.checkResponse(response)
        } catch (e: Exception) {
            networkController.checkException(e)
        }

    override suspend fun getEventListByIdentifier(identifier: Long): Either<String, EventListResponse> =
        try {
            val response = eventService.getEventByIdentifier(identifier)
            networkController.checkResponse(response)
        } catch (e: Exception) {
            networkController.checkException(e)
        }
}