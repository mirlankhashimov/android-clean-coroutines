package com.mirlan.sandbox.domain.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Data(
    val cities: List<City>,
    val isCategoriesHidden: Boolean,
    //val masters: List<Any>,
    //val recentlyAddedFirms: List<Any>,
    val recommendedFirms: List<RecommendedFirm>
    //val updates: List<Any>
) : Parcelable

@Keep
@Parcelize
data class City(
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val urlName: String
) : Parcelable

@Keep
@Parcelize
data class RecommendedFirm(
    val address: String,
    val avatarUrl: String,
    val averageRating: Double,
    val checkRating: Int,
    val id: Int,
    val isFavorite: Boolean,
    val isIndividualMaster: Boolean,
    val isPromoted: Boolean,
    val name: String,
    val pictureUrl: String,
    val prepaymentCashbackAmount: String,
    //val todayReservationsCount: Any,
    val type: String,
    val urlKey: String,
    val workSchedule: String
) : Parcelable

@Keep
data class Salon(
    val firm: Firm,
    val masters: List<Master>,
    val services: List<Service>
)

@Keep
data class Firm(
    val address: String,
    val avatarUrl: String,
    val averageRating: Double,
    val category: String,
    val checkRating: Int,
    val cityName: String,
    val description: String,
    val id: Int,
    val instagramProfile: String,
    val isClientSurnameRequired: Boolean,
    val isExpress: Boolean,
    val isMastersHidden: Boolean,
    val isPromoted: Boolean,
    val name: String,
    val phoneNumbers: List<String>,
    val pictures: List<String>,
    val reviewCount: Int,
    val todayReservationsCount: Any,
    val type: String,
    val urlKey: String,
    val videoUrl: String,
    val workEndTime: String,
    val workStartTime: String
)

@Keep
data class Master(
    val avatarUrl: String,
    val capacity: Any,
    val experience: String,
    val id: Int,
    val isRoom: Boolean,
    val name: String,
    val profession: String,
    val rating: Double,
    val serviceIds: List<Int>,
    val surname: String
)

@Keep
data class Service(
    val categoryId: Int,
    val description: String,
    val duration: Int,
    val express: Any,
    val id: Int,
    val name: String,
    val prepaymentAmount: Int,
    val price: Int,
    val priceMax: Int,
    val priceStr: String
)