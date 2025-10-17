# JainKoshModern

This repository builds a debug APK using GitHub Actions automatically when you push to the `main` branch.

## Quick steps
1. Create a GitHub repo and upload these files (or push from local).
2. Go to Actions → you'll see the "Build Android APK" workflow run.
3. After success, download artifact named `app-debug-apk` from the workflow run (contains `app-debug.apk`).

Alternatively, you can build locally using `gradle assembleDebug` if you have Android SDK & Gradle installed.
