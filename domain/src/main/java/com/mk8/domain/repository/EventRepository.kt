package com.mk8.domain.repository

import com.mk8.data.Either
import com.mk8.data.app.EventModel
import com.mk8.data.mapValue
import com.mk8.domain.client.ApiClient

class EventRepository(private val apiClient: ApiClient) {

    suspend fun getEventList(): Either<String, List<EventModel>> =
        apiClient.getEventList().mapValue { it.eventList }

    suspend fun getEventByIdentifier(identifier: Long): Either<String, EventModel> =
        apiClient.getEventListByIdentifier(identifier).mapValue { it.eventList.first() }
}