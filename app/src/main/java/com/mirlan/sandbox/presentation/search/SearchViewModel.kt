package com.mirlan.sandbox.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.mirlan.sandbox.core.BaseViewModel
import com.mirlan.sandbox.domain.repo.SalonsRepository
import com.mirlan.sandbox.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

class SearchViewModel(private val repository: SalonsRepository) : BaseViewModel() {
    private val query = MutableStateFlow("")

    @FlowPreview
    @ExperimentalCoroutinesApi
    val images: LiveData<Result<Any>> = query
        .debounce(DEBOUNCE_MILLIS)
        .filter { text -> text.isNotEmpty() }
        .flatMapLatest { text ->
            repository.getImages(text)//text
        }
        .asLiveData()

    /**
     * Set the new [text] to search
     * */
    fun setQuery(text: String) {
        query.value = text
    }
}

private const val DEBOUNCE_MILLIS = 700L