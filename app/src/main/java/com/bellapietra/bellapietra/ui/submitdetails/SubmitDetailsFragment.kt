package com.bellapietra.bellapietra.ui.submitdetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.bellapietra.bellapietra.R
import com.bellapietra.bellapietra.databinding.SubmitDetailsFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.regex.Pattern

class SubmitDetailsFragment : BottomSheetDialogFragment(), View.OnClickListener {

    companion object {
        fun newInstance() = SubmitDetailsFragment()
    }

    private lateinit var viewModel: SubmitDetailsViewModel
    private lateinit var submitDetailsBinding:SubmitDetailsFragmentBinding
    private lateinit var cName:String
    private lateinit var phone:String
    private lateinit var email:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        submitDetailsBinding = SubmitDetailsFragmentBinding.inflate(inflater,container,false)

        //Add TextWatcher to name text input
        submitDetailsBinding.nameTextInput.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                submitDetailsBinding.nameTextInputLayout.isErrorEnabled = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                submitDetailsBinding.nameTextInputLayout.isErrorEnabled = false
            }
        })

        //Add Text Watcher to phone number text input
        submitDetailsBinding.phoneTextInput.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                submitDetailsBinding.phoneTextInputLayout.isErrorEnabled = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                submitDetailsBinding.phoneTextInputLayout.isErrorEnabled = false
            }
        })

        //Add TextWatcher to Email text input
        submitDetailsBinding.emailTextInput.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                submitDetailsBinding.emailTextInputLayout.isErrorEnabled = true
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                submitDetailsBinding.emailTextInputLayout.isErrorEnabled = false
            }
        })

        //Set on Click listener to submit button
        submitDetailsBinding.submitDetailsButton.setOnClickListener(this)
        return submitDetailsBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Initializing ViewModel class
        viewModel = ViewModelProvider(this).get(SubmitDetailsViewModel::class.java)

        //Observe the viewModel class
        viewModel.detailsSubmitted.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
                viewModel.disMissDialog()
                dismiss()
            }
        })
    }

    override fun onClick(v: View?) {
        when(v){
            submitDetailsBinding.submitDetailsButton ->{
                cName = submitDetailsBinding.nameTextInput.text.toString()
                phone = submitDetailsBinding.phoneTextInput.text.toString()
                email = submitDetailsBinding.emailTextInput.text.toString()
                checkInputs(cName,phone,email)
            }
        }
    }

    //check inputs
    private fun checkInputs(name:String, phone:String,email:String){
        when{
            name.isEmpty()-> submitDetailsBinding.nameTextInputLayout.error = getString(R.string.enter_your_name)
            phone.isEmpty()->submitDetailsBinding.phoneTextInputLayout.error = getString(R.string.enter_phone_number)
            email.isEmpty()->submitDetailsBinding.emailTextInputLayout.error = getString(R.string.enter_email_address)
            !validateEmail(email)->submitDetailsBinding.emailTextInputLayout.error = getString(R.string.enter_valid_email)
            else->{
                submitDetails(name, email, phone)
            }
        }
    }

    //Validate email
    private fun validateEmail(emailId: String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(emailId).matches()
    }

    //Submit details
    private fun submitDetails(name: String,email:String, phone: String){
        viewModel.submitDetails(name, email, phone)
    }
}
