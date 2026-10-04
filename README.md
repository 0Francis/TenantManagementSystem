# Tenant Management System

An Android app for capturing tenant details and displaying them on screen. Built in **Kotlin** as the Lesson 6 practical for **BBT 3.2: Mobile Application Development** (Strathmore University, Bachelor in Business Information Technology).

The app starts as a plain form wired with **View Binding**, then moves display logic out of the Activity and into the data model using **Data Binding**.

---

## Features

- Add a tenant with **name**, **phone number** and **rent paid**
- Numeric keyboards for phone and rent fields (`phone`, `numberDecimal` input types)
- Validation: an empty tenant name shows an inline error and nothing is saved
- Saved tenant appears instantly in the result area, rendered by the layout itself
- Big bold tenant name plus a formatted summary (`Tenant`, `Phone`, `Rent paid: KSh ...`)
- Input fields clear after a successful save
- Placeholder hint shown until a tenant is saved

## Tech stack

| Area | Choice |
|---|---|
| Language | Kotlin |
| UI | XML with `ConstraintLayout` |
| Kotlin to views | View Binding |
| Data to views | Data Binding (`<layout>`, `<variable>`, `@{}` expressions) |
| Build | Gradle (Kotlin DSL) |
| Min SDK | 24 |
| Target SDK | 37 |
| IDE | Android Studio |

## How it works (we ain't done yet by the way)

```
View Binding (Step 1):   Kotlin  --builds the text-->  TextView
Data Binding (Step 2):   Kotlin  --gives a Tenant-->   XML  --displays-->  TextView
```

1. The user fills in the form and taps **SAVE**.
2. `MainActivity` validates the input and creates a `Tenant` object.
3. `binding.tenant = tenant` hands the object to the layout.
4. The layout displays it with `@{tenant.name}` and `@{tenant.summary()}`.

`MainActivity` decides *what* to save. The `Tenant` class and the XML decide *how it looks*. Changing the display text only touches `Tenant.kt`.

## Project structure

```
TenantManagementSystem/
├── app/
│   ├── build.gradle.kts                  # viewBinding + dataBinding enabled
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/example/tenantmanagementsystem190050/
│       │   ├── MainActivity.kt           # input handling, validation, binding setup
│       │   └── Tenant.kt                 # data class + summary() display text
│       └── res/layout/
│           └── activity_main.xml         # data binding layout (ConstraintLayout)
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## Getting started

### Prerequisites

- Windows 10/11 (64-bit), macOS or Linux
- [Android Studio](https://developer.android.com/studio) (latest stable)
- An Android emulator (virtualization enabled) **or** a physical Android phone with USB debugging on
- [Git](https://git-scm.com)

### Run the project

```bash
git clone https://github.com/0Francis/TenantManagementSystem.git
```

1. Open the cloned folder in Android Studio (**File > Open**).
2. Wait for the Gradle sync to finish.
3. Select an emulator or connected phone.
4. Click **Run**.

### If the build misbehaves

- `ActivityMainBinding` or `binding.tenant` shows red: **Build > Rebuild Project**
- Sync issues: **File > Sync Project with Gradle Files**
- Make sure the `type=` in `activity_main.xml`'s `<variable>` matches the package declared in `Tenant.kt`

## Usage

| Input | Example |
|---|---|
| Tenant name | John Kamau |
| Phone number | 0712345678 |
| Rent paid | 25000 |

Tap **SAVE** and the result area shows:

```
John Kamau

Tenant: John Kamau
Phone: 0712345678
Rent paid: KSh 25000
```

## Concepts covered

- Building screens with `ConstraintLayout` (including `0dp` match-constraints)
- Enabling and using View Binding (`ActivityMainBinding`, `binding.root`)
- Click listeners and string templates in Kotlin
- Kotlin data classes
- Data Binding layouts, `<variable>` declarations and `@{}` expressions
- Null-safe binding with hint text as the empty state

## Author

**Francis** ([@0Francis](https://github.com/0Francis))
BBIT, Strathmore University, Software Product Engineer, Data Scientist

## Acknowledgements
Lecturer: Madam Salome and Strathmore University
