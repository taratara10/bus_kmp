# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

A Kotlin Multiplatform (KMP) app displaying bus schedules at the nearest bus stop. Targets Android (Wear OS), iOS, and Web (Kotlin/Wasm + Compose). Deployed at https://taratara10.github.io/bus_kmp/.

## Build & Development Commands

```bash
# Build web (Wasm) distribution
./gradlew :app-wasm:wasmJsBrowserDistribution

# Start web development server
./gradlew :app-wasm:wasmJsBrowserDevelopmentRun

# Run all tests
./gradlew test

# Run tests for a specific module
./gradlew :core:domain:commonTest
./gradlew :detekt:test

# Run a single test class
./gradlew :core:domain:commonTest --tests "*GetBusDepartureTimeUseCaseTest*"

# Run Detekt (static analysis)
./gradlew detekt
```

## Module Architecture

```
:core:model     →  Data models, enums, value classes (no dependencies on other modules)
:core:domain    →  Use cases, repository interfaces, MVI pattern (depends on :core:model)
:feature:clock  →  UI screens, ViewModels, MVI contracts (depends on :core:domain, :core:model)
:app-wearos     →  WearOS entry point (depends on :feature:clock)
:app-wasm       →  Web/Wasm entry point (depends on :feature:clock)
:detekt         →  Custom Detekt rule: RepositorySuspendRule
:build-logic    →  Gradle convention plugins (buildlogic.kmp, buildlogic.kmp.android, buildlogic.kmp.wasm)
```

## MVI Pattern

The app uses MVI (Model-View-Intent). The contract is defined in `:core:domain`:

- `MVI<UiState, UiAction, SideEffect>` interface — implemented by ViewModels
- `MVIDelegate` — state management delegate created via `mviDelegate()`
- State is `StateFlow<UiState>`, side effects are `Flow<SideEffect>`

Contracts (UiState, UiAction, SideEffect) live alongside their ViewModel in `:feature:clock/ClockContract.kt`.

## Repository Pattern & Custom Detekt Rule

All public functions on `Repository` interfaces **must be `suspend` functions**. This is enforced by the custom `RepositorySuspendRule` in the `:detekt` module. Violations are caught at build time via `./gradlew detekt`.

## Error Handling

Uses `com.github.michaelbull.kotlin-result` (Result monad). Key operations: `.getOr()`, `.andThen()`, `.onFailure()`. Prefer this over exceptions in domain/use-case code.

## Detekt Configuration

Config file: `detekt/src/main/resources/config/detekt.yml`
- Max line length: 120
- Forbidden comments: TODO, FIXME, STOPSHIP
- Max issues before failure: 10
- Custom rule set id: `bus-rule`

## Key Libraries (from `gradle/libs.versions.toml`)

- Kotlin: 2.0.0, Compose Multiplatform: 1.6.10
- `kotlinx-datetime` — date/time handling
- `kotlinx-coroutines-core` — async
- `kotlin-result` — Result monad error handling
- `androidx.lifecycle.viewmodel-compose` — ViewModel in Compose
- `androidx.wear.compose` — WearOS UI

## CI/CD

`.github/workflows/Deploy.yml` — on push to `main`, builds `:app-wasm:wasmJsBrowserDistribution`, copies output to `docs/`, and force-pushes to the `deploy` branch for GitHub Pages hosting.
