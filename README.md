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
ui/    Activities, Fragments and (from Stage 2) the ViewModel â€” the UI layer
data/  Tenant and TenantRepository â€” the data layer
```
Tenants are currently stored in memory by `TenantRepository`.

## Structure
```
app/src/main/java/com/example/tenantmanagementsystemgroup1/
â”œâ”€â”€ ui/
â”‚   â”œâ”€â”€ splash/     SplashActivity
â”‚   â”œâ”€â”€ auth/       LoginActivity, RegisterActivity
â”‚   â”œâ”€â”€ main/       MainActivity
â”‚   â”œâ”€â”€ dashboard/  DashboardFragment
â”‚   â”œâ”€â”€ tenants/    AddTenantFragment, ViewTenantsFragment
â”‚   â”œâ”€â”€ about/      AboutFragment
â”‚   â””â”€â”€ viewmodel/  (added in Stage 2)
â””â”€â”€ data/
    â”œâ”€â”€ model/      Tenant
    â”œâ”€â”€ local/      (added in Stage 3)
    â””â”€â”€ repository/ TenantRepository
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