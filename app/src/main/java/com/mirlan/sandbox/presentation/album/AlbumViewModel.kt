package com.mirlan.sandbox.presentation.album

import androidx.lifecycle.viewModelScope
import com.mirlan.sandbox.core.BaseViewModel
import com.mirlan.sandbox.domain.repo.SalonsRepository

class AlbumViewModel(
    private val salonsRepository: SalonsRepository
) : BaseViewModel() {

    var connectivityAvailable: Boolean = true


}