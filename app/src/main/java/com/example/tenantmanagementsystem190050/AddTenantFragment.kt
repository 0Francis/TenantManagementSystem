package com.example.tenantmanagementsystem190050

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tenantmanagementsystem190050.databinding.FragmentAddTenantBinding

class AddTenantFragment : Fragment() {

    private var _binding: FragmentAddTenantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTenantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            var valid = true
            if (name.isBlank()) {
                binding.tenantNameEditText.error = "Tenant name is required"
                valid = false
            }
            if (phone.isBlank()) {
                binding.phoneEditText.error = "Phone number is required"
                valid = false
            }
            if (rent.isBlank()) {
                binding.rentEditText.error = "Rent is required"
                valid = false
            }
            if (!valid) return@setOnClickListener

            val tenant = Tenant(name, phone, rent)

            // Store it in the shared list
            TenantRepository.addTenant(tenant)

            // Clear the form so it's fresh next time
            binding.tenantNameEditText.text.clear()
            binding.phoneEditText.text.clear()
            binding.rentEditText.text.clear()

            findNavController().navigate(
                AddTenantFragmentDirections.actionAddTenantFragmentToViewTenantsFragment(tenant)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
