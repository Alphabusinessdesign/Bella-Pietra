package com.bellapietra.bellapietra.ui.showAll

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

class ShowAllViewModel : ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    //Store items by category
    private var _itemLists = MutableLiveData<List<Item>>()
    val itemList: LiveData<List<Item>>
        get() = _itemLists

    //Store items to send to details fragment
    private var _navigateToItemDetailsFragment = MutableLiveData<Item>()
    val navigateToItemDetailsFragment:LiveData<Item>
    get() = _navigateToItemDetailsFragment

    init {
        _navigateToItemDetailsFragment.value = null
    }

    //Get Item by category
    fun getItemsByCategory(catId:Int){
        uiScope.launch {
            val getItemsByCategoryDeferred = BellaApi.retrofitService.getItemByCategoryAsync(catId)
            try {
                val result = getItemsByCategoryDeferred.await()
                _itemLists.value = result.singleItemList
            }catch (e:Exception){
                Timber.e("Error getting item by category ${e.message}")
            }
        }
    }

    //Send Item to ItemDetailsFragment
    fun sendItemToDetailsFrag(item: Item){
        _navigateToItemDetailsFragment.value = item
    }

    //Done navigate to details fragment
    fun doneNavigating(){
        _navigateToItemDetailsFragment.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
