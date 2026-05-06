package com.delivery.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delivery.app.data.model.Store
import com.delivery.app.domain.repository.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storeRepository: StoreRepository
) : ViewModel() {

    private val _allStores = MutableStateFlow<List<Store>>(emptyList())
    val allStores: StateFlow<List<Store>> = _allStores

    private val _topRatedStores = MutableStateFlow<List<Store>>(emptyList())
    val topRatedStores: StateFlow<List<Store>> = _topRatedStores

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadStores()
    }

    private fun loadStores() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                storeRepository.getAllStores().collect { stores ->
                    _allStores.value = stores
                    _topRatedStores.value = stores.sortedByDescending { it.rating }.take(10)
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchStores(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                storeRepository.searchStores(query).collect { results ->
                    _allStores.value = results
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun filterByCategory(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                storeRepository.getStoresByCategory(category).collect { results ->
                    _allStores.value = results
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}