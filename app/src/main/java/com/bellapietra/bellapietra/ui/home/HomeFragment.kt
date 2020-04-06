package com.bellapietra.bellapietra.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bellapietra.bellapietra.R
import com.bellapietra.bellapietra.databinding.FragmentHomeBinding
import com.bellapietra.bellapietra.network.CategoryItem
import com.bellapietra.bellapietra.network.SingleItems
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var sliderAdapter: HomeSliderAdapter
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeBinding:FragmentHomeBinding
    private var homeItemList:MutableSet<SingleItems>? = mutableSetOf()
    private var idList: MutableList<Int> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Initializing ViewModel class
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)


        //Setting up category recyclerView
        categoryAdapter = CategoryAdapter(CategoryClickListener {
            homeViewModel.navigateToShowAllFrag(it)
        })

        homeBinding.categoryRecycler.adapter = categoryAdapter

        //Setting up slider
        homeViewModel.allItemList.observe(viewLifecycleOwner, Observer {
            it?.let {
                sliderAdapter = HomeSliderAdapter(it)
                homeBinding.homeSlider.setSliderAdapter(sliderAdapter)
            }
        })

        //Observe category list live data
        homeViewModel.categoryList.observe(viewLifecycleOwner, Observer {
            it?.let {
                categoryAdapter.submitList(it)
                idList = createCatIdList(it)
                for (id in idList) {
                    Timber.e("ids are $id")
                    homeViewModel.getItemsByCategory(id)
                }
            } ?: let {
                Timber.e("Category list is empty")
            }
        })

        //Observe ItemList by category
        homeViewModel.itemList.observe(viewLifecycleOwner, Observer {
            val singleItems = SingleItems(it)
            homeItemList?.add(singleItems).let {
                Timber.e("Items in home item list ${homeItemList?.size}")
                homeAdapter = HomeAdapter()
                homeBinding.homeRecycler.adapter = homeAdapter
                homeAdapter.setHomeItemList(homeItemList!!.toMutableList())
            }
        })

        //Observe when to navigate ShowAllFragment
        homeViewModel.navigateToShowAllFragment.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToShowAllFragment(
                    it,null,getString(R.string.category)
                ))
                homeViewModel.doneNavigating()
            }
        })
    }

    //Create Id list
    private fun createCatIdList(catList:List<CategoryItem>):MutableList<Int>{
        val idList = mutableListOf<Int>()
        for (item in catList){
            idList.add(item.catid!!.toInt())
        }
        return idList
    }

    override fun onPause() {
        super.onPause()
        Timber.e("on pause")
        homeItemList?.clear()
        idList.clear()
        Timber.e("Item in home item list ${homeItemList?.size}")
    }
}
