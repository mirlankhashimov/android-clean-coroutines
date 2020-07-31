package com.mirlan.sandbox.domain.entity


data class Photo(
    val id: Int = 0,
    val albumId: Int = 0,
    val title: String? = "",
    val url: String? = "",
    val thumbnailUrl: String? = "",
    val indexInResponse: Int = -1
)