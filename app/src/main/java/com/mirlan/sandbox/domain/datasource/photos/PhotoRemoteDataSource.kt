package com.mirlan.sandbox.domain.datasource.photos

import com.mirlan.sandbox.data.service.Api
import com.mirlan.sandbox.domain.BaseDataSource

class PhotoRemoteDataSource(private val api: Api) : BaseDataSource() {
    suspend fun fetchData(id: Int) = getResult {  api.getPhotos(id) }
}