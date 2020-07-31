package com.mirlan.sandbox.domain.datasource.album

import com.mirlan.sandbox.data.service.Api
import com.mirlan.sandbox.domain.BaseDataSource

class AlbumRemoteDataSource(private val api: Api) : BaseDataSource() {
    suspend fun fetchAlbums(page: Int, pageSize: Int) = getResult { api.getAlbums(page, pageSize) }
}