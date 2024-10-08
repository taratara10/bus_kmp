# bus_kmp

This is a Kotlin Multiplatform project targeting Android, iOS, Web.
It is an app that displays bus schedules at the nearest bus stop in a rich format.

Deployed page → https://taratara10.github.io/bus_kmp/

![screnshot](https://github.com/user-attachments/assets/7540be2a-1121-4909-a099-debce4f6224d)

## Architecture

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - `wasmJsMain` is web projects. You can open the web application by running
      the `:composeApp:wasmJsBrowserDevelopmentRun`
      Gradle task.
      https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      `iosMain` would be the right folder for such calls.
* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for
  your project.

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

