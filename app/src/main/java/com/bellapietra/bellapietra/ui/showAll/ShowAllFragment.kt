package com.bellapietra.bellapietra.ui.showAll

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bellapietra.bellapietra.MainActivity

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

        //Setting up recyclerView
        showAllAdapter = ShowAllAdapter()
        showAllBinding.showAllRecycler.adapter = showAllAdapter

        return showAllBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Initializing ViewModel
        viewModel = ViewModelProvider(this).get(ShowAllViewModel::class.java)

        //Getting arguments from the sender
        val arguments = ShowAllFragmentArgs.fromBundle(getArguments()!!)
        val activity = activity as MainActivity
        sender = arguments.sender
        if (sender == getString(R.string.category)){
            catid = arguments.category?.catid!!
            viewModel.getItemsByCategory(catid.toInt())
            activity.setToolbarTv(arguments.category?.catname)
        }else{
            singleItems = arguments.singleItem!!
            showAllAdapter.submitList(singleItems.singleItemList)
            //Setting up the Heading
            activity.setToolbarTv(singleItems.singleItemList?.get(0)?.catname)
        }

        //Observe items by category
        viewModel.itemList.observe(viewLifecycleOwner, Observer {
            it?.let {
                showAllAdapter.submitList(it)
            }
        })
    }

}
