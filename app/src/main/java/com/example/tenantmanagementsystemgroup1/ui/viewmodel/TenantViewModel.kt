package com.example.tenantmanagementsystemgroup1.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tenantmanagementsystemgroup1.data.local.TenantDatabase
import com.example.tenantmanagementsystemgroup1.data.model.Tenant
import com.example.tenantmanagementsystemgroup1.data.repository.TenantRepository
import kotlinx.coroutines.launch

class TenantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        TenantRepository(TenantDatabase.getDatabase(application).tenantDao())

    val tenants: LiveData<List<Tenant>> = repository.tenants

    val tenantCountText: LiveData<String> = tenants.map { list ->
        "Tenants\n${list.size}"
    }

    val totalRentText: LiveData<String> = tenants.map { list ->
        val total = list.sumOf { it.rent.replace(",", "").toDoubleOrNull() ?: 0.0 }
        "Total rent\nKSh %,.0f".format(total)
    }

    val allTenantsText: LiveData<String> = tenants.map { list ->
        if (list.isEmpty()) "No tenants recorded yet.\nTap 'ADD TENANT' to get started."
        else list.joinToString("\n\n────────────────────\n\n") { it.summary() }
    }

    fun addTenant(name: String, phone: String, rent: String): Tenant? {
        val cleanName = name.trim()
        val cleanPhone = phone.trim()
        val cleanRent = rent.trim().replace(",", "")

        if (cleanName.isEmpty() || cleanPhone.isEmpty() || cleanRent.isEmpty()) {
            return null
        }

        val rentAmount = cleanRent.toDoubleOrNull()
        if (rentAmount == null || rentAmount <= 0) {
            return null
        }

        val tenant = Tenant(cleanName, cleanPhone, cleanRent)
        viewModelScope.launch {
            repository.addTenant(tenant)
        }
        return tenant
    }

    fun deleteTenant(tenant: Tenant) {
        viewModelScope.launch {
            repository.deleteTenant(tenant)
        }
    }

    fun clearAllTenants() {
        viewModelScope.launch {
            repository.deleteAllTenants()
        }
    }

    init {
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: onCleared")
    }
}
