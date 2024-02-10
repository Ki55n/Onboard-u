package com.example.wifimodule.presentation.profile

import android.text.InputType
import android.view.MotionEvent
import android.view.View
import com.example.wifimodule.R
import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.view.BaseFragment
import com.example.wifimodule.data.response.ProfileResponse
import com.example.wifimodule.data.response.UpdateProfileResponse
import com.example.wifimodule.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileFragVM>() {
    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    override fun initViewBinding() {
        binding.apply {
            setupViews()
            loadServerData()
        }
    }

    fun loadServerData() {
        viewModel.getProfile {
            handleProfile(it)
        }
    }
    private fun handleProfile(it: Resource<ProfileResponse>?) {
        when (it) {
            is Resource.Loading -> {
                //Show loading
            }
            is Resource.Success -> {
                binding.textViewName.text = it.data?.data?.name
                binding.textviewBusinessName.text = it.data?.data?.store_name
                binding.textviewAddress.text = it.data?.data?.address
                binding.textviewEmail.text = it.data?.data?.email_address
                binding.textviewPhoneNumber.text = it.data?.data?.phone_number
                binding.textviewBankAccount.text = it.data?.data?.bank_details.toString()
                binding.textviewShippingMethod.text = it.data?.data?.shipping_method

                binding.edittextName.setText(it.data?.data?.name)
                binding.edittextBusinessName.setText(it.data?.data?.store_name)
                binding.edittextEnterAddress.setText(it.data?.data?.address)
                binding.edittextEnterEmailAddress.setText(it.data?.data?.email_address)
                binding.edittextPhoneNumber.setText(it.data?.data?.phone_number)
                binding.edittextBankAccountDetails.setText(it.data?.data?.bank_details.toString())
                binding.edittextEnterShippingMethod.setText(it.data?.data?.shipping_method)

            }
            is Resource.Error -> {
                //Handle the error
            }

            else -> {

            }
        }
    }

    fun setupViews() {
        //Set the update container visible Gone
        binding.cardview1.visibility = View.GONE

        //Set update material button Click Listener
        binding.materialButton1.setOnClickListener {
            updateOrEditProfile()
        }

        //Set the view all button click listener
        binding.materialbuttonViewAll.setOnClickListener {
            viewAllDetails()
        }
    }

    fun setFirstViewContainerVisibilityVisible() {

    }

    fun viewAllDetails() {
        binding.cardview1.visibility = View.VISIBLE
        binding.firstViewContainer.visibility = View.GONE
        
        //Set the editTexts disabled so it can only be viewed only
        binding.edittextBusinessName.isEnabled = false
        binding.edittextEnterAddress.isEnabled = false
        binding.edittextEnterEmailAddress.isEnabled = false
        binding.edittextPhoneNumber.isEnabled = false
        
        //Set the Bank Accout Details and enter Shipping Method EditText to InputType.TYPE_NULL so it can reject text input and recevie click event in the drawable in the right of their editTexts
        binding.edittextBankAccountDetails.inputType = InputType.TYPE_NULL
        binding.edittextEnterShippingMethod.inputType = InputType.TYPE_NULL

        
        //Get their database data and fill it accordingly to see their details
        binding.edittextBusinessName.setText("Ashram Enterprises Limited")
        binding.edittextEnterAddress.setText("Ashram Building, Suite 3, Ghandi Avenue, Chennai")
        binding.edittextEnterEmailAddress.setText("hello@ashram.com")
        binding.edittextPhoneNumber.setText("+912786756745")
        binding.edittextBankAccountDetails.setText("View Bank Account Details")
        binding.edittextEnterShippingMethod.setText("Self-Shipping")
        
        val drawable = resources.getDrawable(R.drawable.default_image) // Replace with the verified image for edittextEnterEmailAddress and edittextPhoneNumber EditTexts
        val drawable2 = resources.getDrawable(R.drawable.default_image) // Replace with the dropDown image for edittextEnterShippingMethod and edittextBankAccountDetails EditTexts

        // Set the bounds of the drawable (adjust as needed)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable2.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)


        // Set the compound drawables for left, top, right, and bottom
        binding.edittextEnterEmailAddress.setCompoundDrawables(null, null, drawable, null)
        binding.edittextPhoneNumber.setCompoundDrawables(null, null, drawable, null)
        binding.edittextEnterShippingMethod.setCompoundDrawables(null, null, drawable2, null)
        binding.edittextBankAccountDetails.setCompoundDrawables(null, null, drawable2, null)

        //Set a click listener for the drawable in the right of an edittextBankAccountDetails and edittextEnterShippingMethod EditTexts
        binding.edittextBankAccountDetails.setOnTouchListener { v, event ->
    // Check if the touch event is within the bounds of the right drawable
    if (event.action == MotionEvent.ACTION_UP &&
        event.rawX >= (binding.edittextBankAccountDetails.right - binding.edittextBankAccountDetails.compoundDrawables[2].bounds.width())) {
        // Handle the click event on the right drawable
        // The drawable in the right is clicked handle the desired action here
        
        return@setOnTouchListener true // Consume the touch event
    }
    false // Allow other touch events to be handled
}

         binding.edittextEnterShippingMethod.setOnTouchListener { v, event ->
    // Check if the touch event is within the bounds of the right drawable
    if (event.action == MotionEvent.ACTION_UP &&
        event.rawX >= (binding.edittextEnterShippingMethod.right - binding.edittextEnterShippingMethod.compoundDrawables[2].bounds.width())) {
        // Handle the click event on the right drawable
        // Drawable in tge roght is clicked handle the desired action here
        
        return@setOnTouchListener true // Consume the touch event
    }
    false // Allow other touch events to be handled
}

        
        //Set the button to edit profile
        binding.materialButton1.text = "Edit Profile"
        
        //Set the search icon container Gone
        binding.locateShopContainer.visibility = View.GONE
        
        //Set the view all button Gone
        binding.materialbuttonViewAll.visibility = View.GONE
        
        //Set a click listener when the button is clicked
        binding.materialButton1.setOnClickListener{
        //Update or edit profile
        updateOrEditProfile()
        }
    }
    private fun handleUpdate(it: Resource<UpdateProfileResponse>?) {

    }
    fun updateOrEditProfile() {
        binding.firstViewContainer.visibility = View.GONE
        binding.cardview1.visibility = View.VISIBLE
        
        //Set the editTexts enabled so it can be editted
        binding.edittextBusinessName.isEnabled = true
        binding.edittextEnterAddress.isEnabled = true
        binding.edittextEnterEmailAddress.isEnabled = true
        binding.edittextPhoneNumber.isEnabled = true
        
        //Set the Bank Accout Details and enter Shipping Method EditText to InputType.TYPE_CLASS_TEXT so it will accept Text input
        binding.edittextBankAccountDetails.inputType = InputType.TYPE_CLASS_TEXT
        binding.edittextEnterShippingMethod.inputType = InputType.TYPE_CLASS_TEXT
        
        //Set the edittext hints
        binding.edittextBusinessName.hint = "Enter Business Name"
        binding.edittextEnterAddress.hint = "Enter Address"
        binding.edittextEnterEmailAddress.hint = "Enter Email Address"
        binding.edittextPhoneNumber.hint = "Enter Phone No"
        binding.edittextBankAccountDetails.hint = "Enter Bank Account Details"
        binding.edittextEnterShippingMethod.hint = "Select Shipping Method"
        
        val drawable2 = resources.getDrawable(R.drawable.default_image) // Replace with the dropDown image for edittextEnterShippingMethod EditTexts

        // Set the bounds of the drawable (adjust as needed)
//        drawable2.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)


        // Set the compound drawables for left, top, right, and bottom
        binding.edittextEnterEmailAddress.setCompoundDrawables(null, null, null, null)
        binding.edittextPhoneNumber.setCompoundDrawables(null, null, null, null)
        binding.edittextEnterShippingMethod.setCompoundDrawables(null, null, drawable2, null)
        binding.edittextBankAccountDetails.setCompoundDrawables(null, null, null, null)

        //Renoves click listener for the drawable in the right of an edittextBankAccountDetails EditText 
        binding.edittextBankAccountDetails.setOnTouchListener(null)
        
        //Set a click listener for the drawable in the right of an edittextEnterShippingMethod EditTexts
         binding.edittextEnterShippingMethod.setOnTouchListener { v, event ->
    // Check if the touch event is within the bounds of the right drawable
    if (event.action == MotionEvent.ACTION_UP &&
        event.rawX >= (binding.edittextEnterShippingMethod.right - binding.edittextEnterShippingMethod.compoundDrawables[2].bounds.width())) {
        // Handle the click event on the right drawable
        // Drawable in the right is clicked handle the desired action here
        
        return@setOnTouchListener true // Consume the touch event
    }
    false // Allow other touch events to be handled
}
        
        //Set the button to edit profile
        binding.materialButton1.text = "Update Profile"
        
        //Set the search icon container Gone
        binding.locateShopContainer.visibility = View.VISIBLE
        
        //Set the view all button Gone
        binding.materialbuttonViewAll.visibility = View.GONE

       //When update profile button clicked it should check the details input and submit it to database
        binding.materialButton1.setOnClickListener {
            //Get all details and update it to the database
            val nameText = binding.edittextName.text.toString()
            val businessNameText = binding.edittextBusinessName.text.toString()
            val addressEditTextText = binding.edittextEnterAddress.text.toString()
            val emailAddressEditTextText = binding.edittextEnterEmailAddress.text.toString()
            val enterPhoneNoEditTextText = binding.edittextPhoneNumber.text.toString()
            val bankAccountDetailsEditTextText = binding.edittextBankAccountDetails.text.toString()
            val enterShippingMethodEditTextText =  binding.edittextEnterShippingMethod.text.toString()

            viewModel.updateProfile(
                nameText,
                businessNameText,
                emailAddressEditTextText,
                addressEditTextText,
                enterPhoneNoEditTextText,
                enterShippingMethodEditTextText,
                2
            ) {
                handleUpdate(it)
            }


            if (!businessNameText.isNullOrEmpty() && !addressEditTextText.isNullOrEmpty()
                && !emailAddressEditTextText.isNullOrEmpty() && !enterPhoneNoEditTextText.isNullOrEmpty()
                && !bankAccountDetailsEditTextText.isNullOrEmpty() && !enterShippingMethodEditTextText.isNullOrEmpty()
            ) {
                //No details are null or empty, so update their profile accordingly
            } else {
                //Some of the details are null or empty so notify the user or handle accordingly
            }
        }
    }
}
