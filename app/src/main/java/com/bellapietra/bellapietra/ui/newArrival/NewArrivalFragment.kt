package com.bellapietra.bellapietra.ui.newArrival

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bellapietra.bellapietra.R

class NewArrivalFragment : Fragment() {

    private lateinit var newArrivalViewModel: NewArrivalViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        newArrivalViewModel =
                ViewModelProvider(this).get(NewArrivalViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_newest_arrival, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
