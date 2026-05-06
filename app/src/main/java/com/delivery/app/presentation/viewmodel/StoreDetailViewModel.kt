package com.delivery.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delivery.app.data.model.MenuItem
import com.delivery.app.data.model.Store
import com.delivery.app.domain.repository.MenuRepository
import com.delivery.app.domain.repository.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreDetailViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _store = MutableStateFlow<Store?>(null)
    val store: StateFlow<Store?> = _store

    private val _menuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuItems: StateFlow<List<MenuItem>> = _menuItems

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadStoreDetails(storeId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                storeRepository.getStoreById(storeId).collect { store ->
                    _store.value = store
                    loadMenuItems(storeId)
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun loadMenuItems(storeId: String) {
        viewModelScope.launch {
            try {
                menuRepository.getMenuItems(storeId).collect { items ->
                    _menuItems.value = items
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            }
        }
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
        val storeId = _store.value?.id ?: return
        viewModelScope.launch {
            try {
                menuRepository.getMenuItemsByCategory(storeId, category).collect { items ->
                    _menuItems.value = items
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            }
        }
    }
}