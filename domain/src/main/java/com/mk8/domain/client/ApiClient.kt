package com.mk8.domain.client

import com.mk8.data.Either
import com.mk8.data.api.EventListResponse

interface ApiClient {

    suspend fun getEventList(): Either<String, EventListResponse>

    suspend fun getEventListByIdentifier(identifier :Long): Either<String, EventListResponse>
}