package com.bellapietra.bellapietra.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bellapietra.bellapietra.R

class ShowAllFragment : Fragment() {

    companion object {
        fun newInstance() = ShowAllFragment()
    }

    private lateinit var viewModel: ShowAllViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.show_all_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ShowAllViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
