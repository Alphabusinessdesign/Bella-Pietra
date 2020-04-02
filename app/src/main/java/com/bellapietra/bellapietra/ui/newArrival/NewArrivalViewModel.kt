package com.bellapietra.bellapietra.ui.newArrival

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bellapietra.bellapietra.network.BellaApi
import com.bellapietra.bellapietra.network.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class NewArrivalViewModel : ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _itemList = MutableLiveData<List<Item>>()
    val itemList: LiveData<List<Item>>
        get() = _itemList

    init {
        getAllItems()
    }

    private fun getAllItems() {

        uiScope.launch {
            val getAllItemsDeferred = BellaApi.retrofitService.getAllItemsAsync()
            try {
                val items = getAllItemsDeferred.await()
                val allItems = items.itemList

                allItems?.let {
                    if (allItems.size > 10) {
                        _itemList.value = allItems.takeLast(10).reversed()
                    } else {
                        _itemList.value = allItems
                    }
                }
            } catch (e: Exception) {
                Timber.e("Failed to get newest arrival items")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}