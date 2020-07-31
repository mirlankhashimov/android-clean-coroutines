package com.mirlan.sandbox.domain.repo

import com.mirlan.sandbox.domain.datasource.photos.PhotoRemoteDataSource

class PhotoRepository(
    private val photoRemoteDataSource: PhotoRemoteDataSource
) {

}