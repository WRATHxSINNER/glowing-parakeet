# 📥 Glowing Parakeet - Download & Build Information

## ⚡ Quick Download Links

### Latest Release

| Build Type | Status | Download | Size | Updated |
|-----------|--------|----------|------|---------|
| **Debug APK** | ✅ Ready | [Download](https://github.com/WRATHxSINNER/glowing-parakeet/releases/latest) | ~45MB | Latest |
| **Release APK** | ✅ Ready | [Download](https://github.com/WRATHxSINNER/glowing-parakeet/releases/latest) | ~42MB | Latest |
| **Source Code** | ✅ Available | [Download](https://github.com/WRATHxSINNER/glowing-parakeet/archive/main.zip) | ~3MB | Latest |

---

## 🔗 Direct APK Links

### Debug Version (Recommended for Testing)
```
https://github.com/WRATHxSINNER/glowing-parakeet/releases/latest/download/app-debug.apk
```
**Features:**
- Full debugging capabilities
- No optimization
- Good for testing all features
- FPS counter visible
- Larger file size

### Release Version (Production)
```
https://github.com/WRATHxSINNER/glowing-parakeet/releases/latest/download/app-release-signed.apk
```
**Features:**
- Optimized performance
- Code obfuscation
- Smaller file size
- Better battery efficiency
- Production-ready

---

## 🏗️ Build Status

### GitHub Actions CI/CD Pipeline

**Automated Builds:** Every push to `main` branch

**Build Status:** [![Build Status](https://github.com/WRATHxSINNER/glowing-parakeet/workflows/Auto%20Build%20and%20Test/badge.svg)](https://github.com/WRATHxSINNER/glowing-parakeet/actions)

**View all builds:** [GitHub Actions](https://github.com/WRATHxSINNER/glowing-parakeet/actions)

### Latest Build Details

- **Repository:** WRATHxSINNER/glowing-parakeet
- **Branch:** main
- **Workflow:** Auto Build and Test
- **Last Run:** [View Here](https://github.com/WRATHxSINNER/glowing-parakeet/actions?query=workflow%3A%22Auto+Build+and+Test%22)

---

## 📱 Installation Methods

### Method 1: Direct APK Install (Easiest)
1. Download APK from releases
2. Enable "Unknown sources" in Settings
3. Open APK file
4. Tap Install

**⏱️ Time:** 2-5 minutes

### Method 2: ADB Install
```bash
adb install app-debug.apk
```

**⏱️ Time:** 3-5 minutes  
**Requirements:** Android SDK Platform Tools

### Method 3: Local Build
```bash
git clone https://github.com/WRATHxSINNER/glowing-parakeet.git
cd glowing-parakeet
./build.sh
./build.sh deploy
```

**⏱️ Time:** 10-30 minutes (first build)  
**Requirements:** Android Studio, NDK, JDK 11+

### Method 4: Android Studio
1. Open project in Android Studio
2. Click Build → Build APK(s)
3. Wait for completion
4. Run on device

**⏱️ Time:** 15-45 minutes (first build)

---

## 📊 Build Information

### APK Specifications

**Debug Build:**
- Size: ~45MB
- Optimization: None
- Debugging: Enabled
- Code Obfuscation: Disabled
- Signing: Debug Key
- Installation Time: 2-5 min

**Release Build:**
- Size: ~42MB
- Optimization: Full (R8)
- Debugging: Disabled
- Code Obfuscation: Enabled
- Signing: Production Key
- Installation Time: 2-5 min

### Supported Architectures
- arm64-v8a (Primary - Galaxy S10)
- armeabi-v7a (Fallback)

### Device Compatibility
- **Min SDK:** 26 (Android 8.0)
- **Target SDK:** 34 (Android 14)
- **Tested on:** Samsung Galaxy S10
- **RAM Required:** 2GB minimum, 4GB+ recommended
- **Storage Required:** 500MB minimum, 2GB+ recommended

---

## 🚀 Build Commands

### Using Build Script
```bash
# Make executable
chmod +x build.sh

# Full build
./build.sh

# Build and deploy
./build.sh deploy

# Clean and rebuild
./gradlew clean && ./build.sh
```

### Using Gradle Directly
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Clean
./gradlew clean

# Build and run tests
./gradlew build

# Lint check
./gradlew lint
```

### Using Android Studio
```
Build Menu → Build Bundle(s) / APK(s) → Build APK(s)
```

---

## 📍 Output Locations

### After Building

```
glowing-parakeet/
├── app/build/outputs/
│   ├── apk/
│   │   ├── debug/
│   │   │   └── app-debug.apk
│   │   └── release/
│   │       └── app-release-unsigned.apk
│   ├── bundle/
│   │   └── release/
│   │       └── app-release.aab
│   └── lint-results/
│       └── lint-results.html
├── build/
│   ├── outputs/
│   └── reports/
└── build.log
```

---

## ⚙️ Build Configuration

### Key Build Settings

**Gradle Properties:**
- Parallel builds: Enabled
- Build caching: Enabled
- Incremental compilation: Enabled
- JVM args: 4096MB heap

**Android Configuration:**
- Min SDK: 26
- Target SDK: 34
- Build Tools: 34.0.0
- NDK: 26.1.10909125

**Optimization:**
- R8 Code Shrinking: Enabled
- Resource Shrinking: Enabled
- ProGuard Rules: Applied

---

## 🔐 Security

**Release Build Signing:**
- Keystore: Configured via GitHub Secrets
- Algorithm: SHA-256
- Key Type: RSA 2048-bit
- Validity: 10+ years

**Code Protection:**
- Obfuscation: ProGuard
- Resource Minification: Enabled
- Native Stack Trace Obfuscation: Enabled

---

## 📈 Build Statistics

**Current Release:**
- Version: 1.0.0
- Release Date: June 1, 2026
- Total Downloads: Tracking enabled
- Active Installations: Tracking enabled

**Code Statistics:**
- Lines of Kotlin: ~2,000
- Lines of Java: ~500
- Lines of C++: ~5,000 (native libraries)
- Test Coverage: 60%+

---

## 🛠️ Troubleshooting Builds

### Build Fails with "Out of Memory"
```bash
export GRADLE_OPTS="-Xmx4096m -XX:MaxPermSize=1024m"
```

### NDK Not Found
```bash
# Install via Android Studio SDK Manager
# Or manually set NDK path
export NDK_HOME=/path/to/ndk/26.1.10909125
```

### Gradle Sync Issues
```bash
./gradlew --stop
./gradlew clean
./gradlew sync
```

### Build Cache Issues
```bash
./gradlew build --no-build-cache
```

---

## 📚 Resources

- **Repository:** https://github.com/WRATHxSINNER/glowing-parakeet
- **Issues:** https://github.com/WRATHxSINNER/glowing-parakeet/issues
- **Releases:** https://github.com/WRATHxSINNER/glowing-parakeet/releases
- **Discussions:** https://github.com/WRATHxSINNER/glowing-parakeet/discussions
- **Android Docs:** https://developer.android.com
- **Gradle Docs:** https://gradle.org/docs

---

**Last Updated:** June 1, 2026  
**Version:** 1.0.0  
**License:** GNU General Public License v3.0
