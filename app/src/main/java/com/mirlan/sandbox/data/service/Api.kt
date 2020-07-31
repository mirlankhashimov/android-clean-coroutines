package com.mirlan.sandbox.data.service

import com.mirlan.sandbox.domain.entity.Album
import com.mirlan.sandbox.domain.entity.Photo
import com.mirlan.sandbox.domain.entity.Salon
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("albums")
    suspend fun getAlbums(
        @Query("_start") pageIndex: Int,
        @Query("_limit") pageSize: Int = 20
    ): Response<List<Album>>

    @GET("photos")
    suspend fun getPhotos(@Query("albumId") albumId: Int): Response<List<Photo>>

    @GET("screen/home")
    suspend fun getSalons(): Flow<Response<List<Salon>>>
}