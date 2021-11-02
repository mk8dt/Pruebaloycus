package com.mk8.domain.provider

import com.mk8.data.Either
import com.mk8.data.app.EventModel
import com.mk8.data.mapValue
import com.mk8.data.value
import com.mk8.domain.cache.EventListCache
import com.mk8.domain.repository.EventRepository

class EventProvider(
    private val eventRepository: EventRepository,
    private val eventListCache: EventListCache
) {

    suspend fun getEventList(): Either<String, List<EventModel>> {
        val cacheData = eventListCache.load()
        return if (cacheData.isNotEmpty()) value(cacheData)
        else {
            val dataRepository = eventRepository.getEventList()
            dataRepository.mapValue { eventListCache.save(it.toMutableList()) }
            dataRepository
        }
    }

    suspend fun getEventByIdentifier(identifier: Long): Either<String, EventModel> =
        eventRepository.getEventByIdentifier(identifier)
}