package com.example.tenantmanagementsystemgroup1.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tenantmanagementsystemgroup1.data.model.Tenant

@Dao
interface TenantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTenant(tenant: Tenant): Long

    @Query("SELECT * FROM tenants ORDER BY id DESC")
    fun getAllTenants(): LiveData<List<Tenant>>

    @Query("SELECT * FROM tenants ORDER BY id DESC")
    suspend fun getAllTenantsSync(): List<Tenant>

    @Query("SELECT * FROM tenants WHERE id = :id")
    suspend fun getTenantById(id: Int): Tenant?

    @Delete
    suspend fun deleteTenant(tenant: Tenant)

    @Query("DELETE FROM tenants")
    suspend fun deleteAllTenants()
}