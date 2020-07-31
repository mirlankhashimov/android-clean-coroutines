package com.mirlan.sandbox.domain.entity

data class Salon(
    val data: Data,
    val message: String
)

data class Data(
    val cities: List<City>,
    val isCategoriesHidden: Boolean,
    val masters: List<Any>,
    val recentlyAddedFirms: List<Any>,
    val recommendedFirms: List<RecommendedFirm>,
    val updates: List<Any>
)

data class City(
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val urlName: String
)

data class RecommendedFirm(
    val address: String,
    val avatarUrl: String,
    val averageRating: Int,
    val checkRating: Int,
    val id: Int,
    val isFavorite: Boolean,
    val isIndividualMaster: Boolean,
    val isPromoted: Boolean,
    val name: String,
    val pictureUrl: String,
    val prepaymentCashbackAmount: String,
    val todayReservationsCount: Any,
    val type: String,
    val urlKey: String,
    val workSchedule: String
)