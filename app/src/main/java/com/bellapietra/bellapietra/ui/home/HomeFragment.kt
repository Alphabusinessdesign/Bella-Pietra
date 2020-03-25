package com.bellapietra.bellapietra.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bellapietra.bellapietra.databinding.FragmentHomeBinding
import com.bellapietra.bellapietra.network.SingleItems
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var sliderAdapter: HomeSliderAdapter
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Initializing ViewModel class
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val homeBinding = FragmentHomeBinding.inflate(inflater, container, false)

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
                homeViewModel.createCatIdList(it)
            } ?: let {
                Timber.e("Category list is empty")
            }
        })

        //Observe category id list
        homeViewModel.catIdList.observe(viewLifecycleOwner, Observer {
            it?.let {
                for (id in it) {
                    Timber.e("ids are $id")
                    homeViewModel.getItemsByCategory(id)
                }
            }
        })

        //Observe ItemList by category
        homeViewModel.itemList.observe(viewLifecycleOwner, Observer {
            Timber.e("Item list size ${it.size}")
            val singleItems = SingleItems(it)
            homeViewModel.addItemsToHomeList(singleItems)
        })

        //observe home item list value
        homeViewModel.homeItemListLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                homeAdapter = HomeAdapter(it)
                homeBinding.homeRecycler.adapter = homeAdapter
            }
        })

        //Observe when to navigate ShowAllFragment
        homeViewModel.navigateToShowAllFragment.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToShowAllFragment(
                    it.catid,null,"Category"
                ))
                homeViewModel.doneNavigating()
            }
        })
        return homeBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
