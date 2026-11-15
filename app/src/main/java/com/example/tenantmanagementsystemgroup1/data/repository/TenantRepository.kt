package com.example.tenantmanagementsystemgroup1.data.repository

import androidx.lifecycle.LiveData
import com.example.tenantmanagementsystemgroup1.data.local.TenantDao
import com.example.tenantmanagementsystemgroup1.data.model.Tenant

class TenantRepository(private val tenantDao: TenantDao) {
    val tenants: LiveData<List<Tenant>> = tenantDao.getAllTenants()

    suspend fun addTenant(tenant: Tenant): Long {
        return tenantDao.insertTenant(tenant)
    }

    suspend fun deleteTenant(tenant: Tenant) {
        tenantDao.deleteTenant(tenant)
    }

    suspend fun deleteAllTenants() {
        tenantDao.deleteAllTenants()
    }

    suspend fun getTenantById(id: Int): Tenant? {
        return tenantDao.getTenantById(id)
    }

    suspend fun getAllTenantsSync(): List<Tenant> {
        return tenantDao.getAllTenantsSync()
    }
}
