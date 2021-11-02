package com.mk8.data.api

import com.google.gson.annotations.SerializedName
import com.mk8.data.app.EventModel

data class EventListResponse(
    @SerializedName("@graph")
    val eventList: List<EventModel>
)