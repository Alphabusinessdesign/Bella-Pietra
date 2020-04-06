package com.bellapietra.bellapietra.ui.itemDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bellapietra.bellapietra.MainActivity
import com.bellapietra.bellapietra.databinding.FragmentDetailsBinding
import com.bellapietra.bellapietra.network.Item
import com.bellapietra.bellapietra.ui.submitdetails.SubmitDetailsFragment

class ItemDetailsFragment : Fragment(), View.OnClickListener {

    private lateinit var itemDetailsViewModel: ItemDetailsViewModel
    private lateinit var detailsBinding:FragmentDetailsBinding
    private lateinit var item:Item
    private lateinit var catId:String
    private lateinit var similarAdapter:SimilarItemAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        //Initializing ViewModel class
        itemDetailsViewModel =
                ViewModelProvider(this).get(ItemDetailsViewModel::class.java)

        //Initializing DataBinding
        detailsBinding = FragmentDetailsBinding.inflate(inflater,container,false)

        //Getting arguments from sender fragments
        val arguments = ItemDetailsFragmentArgs.fromBundle(getArguments()!!)
        item = arguments.item
        catId = item.catid!!
        detailsBinding.item = item

        //Setting up the recyclerView with Adapter
        similarAdapter = SimilarItemAdapter(SimilarItemClickListener {
            detailsBinding.item = it
        })
        
        detailsBinding.similarRecycler.adapter = similarAdapter

        //Getting similar items by category
        itemDetailsViewModel.getItemsByCategory(catId.toInt())

        //Observe similar item lists and populate the recyclerView
        itemDetailsViewModel.itemList.observe(viewLifecycleOwner, Observer {
            it?.let {
                similarAdapter.submitList(it)
            }
        })

        //Setting the toolbar title
        val activity = activity as MainActivity
        activity.setToolbarTv(item.catname)

        //Set on click listener to interested button
        detailsBinding.interestedButton.setOnClickListener(this)
        detailsBinding.callNowFab.setOnClickListener(this)
        return detailsBinding.root
    }

    override fun onClick(v: View?) {
        when (v) {
            detailsBinding.interestedButton -> {
                val submitDetailBottomSheet = SubmitDetailsFragment()
                submitDetailBottomSheet.show(activity!!.supportFragmentManager,submitDetailBottomSheet.tag)
            }
            detailsBinding.callNowFab->{
                val intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel:+14508126885"))
                startActivity(intent)
            }
        }
    }
}
