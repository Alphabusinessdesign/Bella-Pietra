package com.bellapietra.bellapietra.ui.newArrival

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bellapietra.bellapietra.MainActivity
import com.bellapietra.bellapietra.R
import com.bellapietra.bellapietra.databinding.FragmentNewestArrivalBinding

class NewArrivalFragment : Fragment() {

    private lateinit var newArrivalViewModel: NewArrivalViewModel
    private lateinit var binding:FragmentNewestArrivalBinding
    private lateinit var newArrivalAdapter: NewArrivalAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //Initializing ViewModel class
        newArrivalViewModel =
                ViewModelProvider(this).get(NewArrivalViewModel::class.java)

        binding = FragmentNewestArrivalBinding.inflate(inflater,container,false)

        val activity = activity as MainActivity
        activity.setToolbarTv(getString(R.string.newest_arrival))
        //Setting up new arrival recyclerView with adapter
        newArrivalAdapter = NewArrivalAdapter(NewArrivalClickListener {

        })
        binding.newArrivalRecycler.adapter = newArrivalAdapter

        //Observe new arrival items to populate the recyclerview
        newArrivalViewModel.itemList.observe(viewLifecycleOwner, Observer {
            it?.let {
                newArrivalAdapter.submitList(it)
            }
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
