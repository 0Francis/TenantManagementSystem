# Tenant Management System

An Android app for landlords to record tenants, see the total rent, and call a tenant. Built in Kotlin for **BBT 3.2 Mobile Application Development** (Strathmore University).

## Features
- Splash screen, registration and login
- Dashboard with the number of tenants and the total rent
- Add a tenant (with validation) and view all tenants
- Call a tenant from the app
- Navigation drawer with logout

## Architecture
```
ui/    Activities, Fragments and (from Stage 2) the ViewModel - the UI layer
data/  Tenant and TenantRepository - the data layer
```
Tenants are currently stored in memory by `TenantRepository`.

## Structure
```
app/src/main/java/com/example/tenantmanagementsystemgroup1/
  ui/
    splash/      SplashActivity
    auth/        LoginActivity, RegisterActivity
    main/        MainActivity
    dashboard/   DashboardFragment
    tenants/     AddTenantFragment, ViewTenantsFragment
    about/       AboutFragment
    viewmodel/   (added in Stage 2)
  data/
    model/       Tenant
    local/       (added in Stage 3)
    repository/  TenantRepository
```

## Tech
| Area | Choice |
|---|---|
| Language | Kotlin |
| UI | XML with ConstraintLayout |
| Binding | View Binding + Data Binding |
| Navigation | Navigation Component + Safe Args |
| Build | Gradle (Kotlin DSL) |
| Min / Target SDK | 24 / 37 |

## Run
1. Open the project in Android Studio and sync Gradle.
2. Run on an emulator or a connected phone.