# Tenant Management System

An Android application for landlords to record tenants, view property overview and total rent calculations, and call tenants directly. Built in Kotlin for **BBT 3.2 Mobile Application Development Class** at Strathmore University.

---

## Architecture

The project follows modern Android **MVVM (Model-View-ViewModel)** and **Repository** architectural patterns:

```
+-------------------------------------------------------------------------+
|                                UI LAYER                                 |
|   Activities (SplashActivity, LoginActivity, RegisterActivity, Main)    |
|   Fragments (DashboardFragment, AddTenantFragment, ViewTenantsFragment) |
+-------------------------------------------------------------------------+
                                    │
                                    ▼
+-------------------------------------------------------------------------+
|                            VIEWMODEL LAYER                              |
|   TenantViewModel (LiveData Transformations, Coroutines, State)        |
+-------------------------------------------------------------------------+
                                    │
                                    ▼
+-------------------------------------------------------------------------+
|                               DATA LAYER                                |
|   TenantRepository (Single Source of Truth)                            |
|   TenantDatabase & TenantDao (Room SQLite Database Engine)              |
|   Tenant Data Model Entity                                             |
+-------------------------------------------------------------------------+
```

---

## Features
- **Simple Input Validation & Onboarding:** Animated Splash Screen, User Registration, and Login flow.
- **Dashboard Overview:** Displays live total tenant counts and aggregated total rent paid.
- **Tenant Management:** Form for adding new tenants with data validation and phone/rent formatting.
- **Tenant Directory:** View all saved tenants with direct single-tap phone dialer integration.
- **Navigation Drawer:** Seamless navigation between Dashboard, Add Tenant, View Tenants, About screen, and Logout.
- **Room SQLite Persistence:** Asynchronous Room Database integration for offline data storage.
- **Automated Testing:** Instrumented Unit Tests & System/Integration Tests for Room Database verification.
- **As Instructed**

---

## Team Members & Contributions

| Stage | Name | Admission No | Branch | PR Link | Role / Responsibility |
|---|---|---|---|---|---|
| Stage 1 | Francis Wainaina | 190050 | `main` | [Direct Commit](https://github.com/0Francis/TenantManagementSystem) | Project Setup & Navigation Graph |
| Stage 2 | Shana Githu | 186888 | `feature/viewmodel` | [PR #2](https://github.com/0Francis/TenantManagementSystem/pull/1) | ViewModel & LiveData Architecture |
| Stage 3 | Steve Luyo | 190277 | `feature/room` | [PR #3](https://github.com/0Francis/TenantManagementSystem/pull/2) | Room Database Entity & DAO |
| Stage 4 | Shana Githu | 186888 | `feature/screens` | [PR #4](https://github.com/0Francis/TenantManagementSystem/pull/3) | UI Fragment Screens & Layouts |
| Stage 5 | Daniella Wasike | 179201 | `feature/data-integration` | [PR #5](https://github.com/0Francis/TenantManagementSystem/pull/4) | Data Integration & SafeArgs Passing |
| Stage 6 | Frank | 190050 | `release/v1.1` | [Direct Commit](https://github.com/0Francis/TenantManagementSystem) | Diagnostics, Optimization & Testing |

---

## Structure
```
app/src/main/java/com/example/tenantmanagementsystemgroup1/
  ├── data/
  │   ├── local/        TenantDao.kt, TenantDatabase.kt
  │   ├── model/        Tenant.kt
  │   └── repository/   TenantRepository.kt
  └── ui/
      ├── about/        AboutFragment.kt
      ├── auth/         LoginActivity.kt, RegisterActivity.kt
      ├── dashboard/    DashboardFragment.kt
      ├── main/         MainActivity.kt
      ├── splash/       SplashActivity.kt
      ├── tenants/      AddTenantFragment.kt, ViewTenantsFragment.kt
      └── viewmodel/    TenantViewModel.kt

app/src/androidTest/java/com/example/tenantmanagementsystemgroup1/
  ├── data/
  │   ├── local/        TenantDaoUnitTest.kt (Instrumented Unit Test)
  │   └──               TenantDatabaseSystemTest.kt (System / Integration Test)
  └── util/             LiveDataTestUtil.kt
```

---

## Tech Stack & Dependencies
| Area | Choice |
|---|---|
| Language | Kotlin |
| UI Architecture | Material Components + ConstraintLayout |
| Binding | View Binding + Data Binding |
| Navigation | Jetpack Navigation Component + Safe Args |
| Local Database | Room Database (Runtime + KSP Compiler) |
| Architecture | ViewModel, LiveData, Kotlin Coroutines |
| Min / Target SDK | 24 / 37 |

---

## How to Run
1. Open the project in **Android Studio**.
2. Sync Gradle files (`File` > `Sync Project with Gradle Files`).
3. Run on an Android Emulator or connected physical Android device.
