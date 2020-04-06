package com.bellapietra.bellapietra.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bellapietra.bellapietra.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

enum class apiStatus{LOADING, ERROR, DONE}
class HomeViewModel : ViewModel() {

    private var _apiStatus = MutableLiveData<apiStatus>()
    val apiStatus:LiveData<apiStatus>
    get() = _apiStatus

    //Store Category list
    private var _categoryList = MutableLiveData<List<CategoryItem>>()
    val categoryList:LiveData<List<CategoryItem>>
    get() = _categoryList

    //Store all items
    private var _allItemList = MutableLiveData<List<Item>>()
    val allItemList:LiveData<List<Item>>
    get() = _allItemList

    //Store items by category
    private var _itemLists = MutableLiveData<List<Item>>()
    val itemList:LiveData<List<Item>>
    get() = _itemLists

    //Store category ids
    private var _catIdList = MutableLiveData<List<Int>>()
    val catIdList:LiveData<List<Int>>
    get() = _catIdList

    //Store home item list value
    private var _homeItemListLiveData = MutableLiveData<MutableList<SingleItems>>()
    val homeItemListLiveData:LiveData<MutableList<SingleItems>>
    get() = _homeItemListLiveData

    private var _navigateToShowAllFragment = MutableLiveData<CategoryItem>()
    val navigateToShowAllFragment:LiveData<CategoryItem>
    get() = _navigateToShowAllFragment

    //Initialing Coroutines job
    private val viewModelJob = Job()

    //Creating a coroutines scope which will run on the main thread
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCategoryList()
        getAllItems()
        _navigateToShowAllFragment.value = null
    }

    //get Category list from network
    private fun getCategoryList(){
        uiScope.launch {

            val getCategoryDeferred = BellaApi.retrofitService.getCategoriesAsync()
            try {
                val result = getCategoryDeferred.await()
                _categoryList.value = result.categoryList
            }catch (e:Exception){
                Timber.e("Error getting category")
            }
        }
    }

    //Get All items to show in slider
    private fun getAllItems(){
        uiScope.launch {
            val getAllItemDeferred = BellaApi.retrofitService.getAllItemsAsync()

            try {
                val result = getAllItemDeferred.await()
                _allItemList.value = result.itemList
            }catch (e:Exception){
                Timber.e("Error getting slider items ${e.message}")
            }
        }
    }

    //Get Item by category
    fun getItemsByCategory(catId:Int){
        uiScope.launch {
            val getItemsByCategoryDeferred = BellaApi.retrofitService.getItemByCategoryAsync(catId)
            try {
                _apiStatus.value = com.bellapietra.bellapietra.ui.home.apiStatus.LOADING
                val result = getItemsByCategoryDeferred.await()
                _itemLists.value = result.singleItemList
                _apiStatus.value = com.bellapietra.bellapietra.ui.home.apiStatus.DONE
            }catch (e:Exception){
                Timber.e("Error getting item by category ${e.message}")
                _apiStatus.value = com.bellapietra.bellapietra.ui.home.apiStatus.ERROR
            }
        }
    }

    //navigate to Show all fragment
    fun navigateToShowAllFrag(categoryItem: CategoryItem){
        _navigateToShowAllFragment.value = categoryItem
    }

    fun doneNavigating(){
        _navigateToShowAllFragment.value = null
    }

    fun addItemsToHomeList(homeItemList:MutableList<SingleItems>){
        Timber.e("Home item list size is ${homeItemList.size}")
        _homeItemListLiveData.value = homeItemList
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}