package com.bellapietra.bellapietra.ui.showAll

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.bellapietra.bellapietra.R
import com.bellapietra.bellapietra.databinding.ShowAllFragmentBinding
import com.bellapietra.bellapietra.network.SingleItems

class ShowAllFragment : Fragment() {

    companion object {
        fun newInstance() =
            ShowAllFragment()
    }

    private lateinit var viewModel: ShowAllViewModel
    private lateinit var showAllBinding:ShowAllFragmentBinding
    private lateinit var sender:String
    private lateinit var catid:String
    private lateinit var singleItems: SingleItems
    private lateinit var showAllAdapter: ShowAllAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showAllBinding = ShowAllFragmentBinding.inflate(inflater,container,false)

        return showAllBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Initializing ViewModel
        viewModel = ViewModelProvider(this).get(ShowAllViewModel::class.java)

        //Getting arguments from the sender
        val arguments = ShowAllFragmentArgs.fromBundle(getArguments()!!)
        sender = arguments.sender
        if (sender == getString(R.string.category)){
            catid = arguments.catid!!
            Toast.makeText(context,catid,Toast.LENGTH_SHORT).show()
        }else{
            singleItems = arguments.singleItem!!

            //Setting up recyclerView
            showAllAdapter = ShowAllAdapter()
            showAllBinding.showAllRecycler.adapter = showAllAdapter
            showAllAdapter.submitList(singleItems.singleItemList)

            //Setting up the Heading
            showAllBinding.showAllTv.text = singleItems.singleItemList?.get(0)?.catname
        }

    }

}
