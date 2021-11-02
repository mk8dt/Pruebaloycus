package com.mk8.pruebaloycus.network

import com.mk8.data.api.EventListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EventService {

    @GET("300107-0-agenda-actividades-eventos.json")
    suspend fun getEventList(): Response<EventListResponse>

    @GET("tipo/evento/{id}.json")
    suspend fun getEventByIdentifier(@Path("id") identifier: Long): Response<EventListResponse>
}