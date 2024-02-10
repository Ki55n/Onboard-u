package com.example.wifimodule.presentation.first_frag

import com.example.wifimodule.base.common.Resource
import com.example.wifimodule.base.view.BaseFragment
import com.example.wifimodule.data.common.EmployeeDetails
import com.example.wifimodule.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding, FirstFragVM>() {

    override fun observeViewModel() {
        viewModel.apply {
            employeeListResponse.observe(this@FirstFragment){
                handleGetResponse(it)
            }
        }
    }

    override fun initViewBinding() {
        binding.apply {
            clickMe.setOnClickListener {
                viewModel.getEmployeeList()
//                Navigation.findNavController(binding.root).navigateSafe(R.id.action_to_secondFragment)
            }
        }
    }

    /**
     * Handles the response of adding a new user address.
     *
     * @param state The resource state containing the response data.
     */
    private fun handleGetResponse(state: Resource<List<EmployeeDetails>>) {
        when (state) {
            is Resource.Success -> {
                viewModel.employeeList.value = state.data
                binding.clickMe.text = "${viewModel.employeeList.value?.size}"
            }
            is Resource.Loading -> {

            }
            is Resource.Error -> {

            }
            is Resource.APIException -> {

            }
            is Resource.Idle -> {
            }
        }
    }

}