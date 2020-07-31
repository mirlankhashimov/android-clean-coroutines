package com.mirlan.sandbox.domain.entity

data class Album(
    var id: Int? = 0,
    val userId: Int? = 0,
    val title: String? = "",
    val indexInResponse: Int = -1
)