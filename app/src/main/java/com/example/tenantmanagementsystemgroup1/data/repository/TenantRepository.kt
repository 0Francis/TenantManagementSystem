package com.example.tenantmanagementsystemgroup1.data.repository
import com.example.tenantmanagementsystemgroup1.data.model.Tenant

// An object, shared by the whole app. Will have one instance.
object TenantRepository {

    val tenants = mutableListOf<Tenant>()

    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    fun totalRent(): Double {
        return tenants.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
    }
}