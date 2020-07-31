package com.mirlan.sandbox.domain.repo

import androidx.lifecycle.LiveData
import com.mirlan.sandbox.domain.datasource.album.AlbumRemoteDataSource
import com.mirlan.sandbox.domain.entity.Album
import kotlinx.coroutines.CoroutineScope

class SalonsRepository(
    private val remoteDataSource: AlbumRemoteDataSource
) {

   // fun observeAlbums(connectivityAvailable: Boolean, coroutineScope: CoroutineScope) =
       // if (connectivityAvailable)
            //observeRemoteAlbums(coroutineScope)

}