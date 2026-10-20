package com.example.tenantmanagementsystem190050

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tenantmanagementsystem190050.databinding.FragmentViewTenantsBinding

class ViewTenantsFragment : Fragment() {

    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!
    private val args: ViewTenantsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTenantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newTenant = args.newTenant

        if (newTenant == null) {
            // Opened from the Dashboard
            binding.newTenantGroup.visibility = View.GONE
        } else {
            // Opened after SAVE: show the tenant that was just added
            binding.tenant = newTenant
            binding.newTenantGroup.visibility = View.VISIBLE

            // Implicit Intent: dialing the tenant
            binding.callButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${newTenant.phone}"))
                try {
                    startActivity(intent)
                } catch (_: ActivityNotFoundException) {
                    Toast.makeText(requireContext(), "No dialer application found", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val tenants = TenantRepository.tenants
        if (tenants.isEmpty()) {
            binding.allTenantsTextView.text = "No tenants yet. Add one from the Dashboard."
        } else {
            binding.allTenantsTextView.text = tenants.joinToString("\n\n") { it.summary() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
