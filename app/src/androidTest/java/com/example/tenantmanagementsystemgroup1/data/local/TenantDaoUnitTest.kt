package com.example.tenantmanagementsystemgroup1.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tenantmanagementsystemgroup1.data.model.Tenant
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * TEST TYPE: INSTRUMENTED UNIT TEST
 *  -------------------------------------------------------------------
 * Purpose: Tests the Room Database Access Object (TenantDao) CRUD operations
 * in isolation using an in-memory SQLite database instance on the Android device/emulator.
 */
@RunWith(AndroidJUnit4::class)
class TenantDaoUnitTest {

    private lateinit var tenantDao: TenantDao
    private lateinit var db: TenantDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // In-memory database so information stored here disappears when the process dies
        db = Room.inMemoryDatabaseBuilder(context, TenantDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        tenantDao = db.tenantDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndReadTenant() = runBlocking {
        // Given a new tenant entity
        val tenant = Tenant(name = "John Doe", phone = "0712345678", rent = "15000")

        // When inserting into the DAO
        val insertedId = tenantDao.insertTenant(tenant)

        // Then verify the auto-generated ID is valid and reading by ID returns matching data
        val fetchedTenant = tenantDao.getTenantById(insertedId.toInt())
        assertNotNull(fetchedTenant)
        assertEquals("John Doe", fetchedTenant?.name)
        assertEquals("0712345678", fetchedTenant?.phone)
        assertEquals("15000", fetchedTenant?.rent)
    }

    @Test
    @Throws(Exception::class)
    fun deleteTenantAndVerifyRemoval() = runBlocking {
        // Given two inserted tenants
        val tenant1 = Tenant(name = "Alice Smith", phone = "0722111222", rent = "20000")
        val tenant2 = Tenant(name = "Bob Johnson", phone = "0733444555", rent = "25000")

        val id1 = tenantDao.insertTenant(tenant1).toInt()
        val id2 = tenantDao.insertTenant(tenant2).toInt()

        // When deleting the first tenant
        val savedTenant1 = tenantDao.getTenantById(id1)
        assertNotNull(savedTenant1)
        tenantDao.deleteTenant(savedTenant1!!)

        // Then tenant 1 is removed while tenant 2 remains
        val deletedTenant = tenantDao.getTenantById(id1)
        val remainingTenant = tenantDao.getTenantById(id2)

        assertNull(deletedTenant)
        assertNotNull(remainingTenant)
        assertEquals("Bob Johnson", remainingTenant?.name)
    }

    @Test
    @Throws(Exception::class)
    fun clearAllTenantsUnitCheck() = runBlocking {
        // Given multiple inserted tenants
        tenantDao.insertTenant(Tenant(name = "Tenant A", phone = "0700000001", rent = "10000"))
        tenantDao.insertTenant(Tenant(name = "Tenant B", phone = "0700000002", rent = "12000"))

        // When clearing all tenants
        tenantDao.deleteAllTenants()

        // Then table is empty
        val list = tenantDao.getAllTenantsSync()
        assertEquals(0, list.size)
    }
}