package com.mirlan.sandbox.data.service

import com.mirlan.sandbox.domain.entity.Data
import com.mirlan.sandbox.domain.entity.Salon
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("screen/home")
    suspend fun getSalons(): BaseResponse<Data>

    @GET("firms/{firmId}")
    suspend fun getSalon(@Path("firmId") firmId: Int): BaseResponse<Salon>

}