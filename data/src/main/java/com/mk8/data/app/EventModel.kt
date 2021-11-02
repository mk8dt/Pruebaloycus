package com.mk8.data.app

import com.google.gson.annotations.SerializedName

data class EventModel(
    val id: String,
    val title: String,
    val price: String,
    val description: String,
    @SerializedName("event-location")
    val eventLocation: String,
    @SerializedName("dtstart")
    val dateStart: String,
    @SerializedName("dtend")
    val dateEnd: String,
    val link: String,
    val location : LocationEvent
)