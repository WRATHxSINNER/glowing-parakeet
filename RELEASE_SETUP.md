# Glowing Parakeet - APK Release Setup Guide

This guide will help you set up automated APK signing and GitHub releases for direct distribution.

## Step 1: Generate Your Signing Keystore

Run the keystore generation script:

```bash
bash generate-keystore.sh
```

This will create a keystore file at `~/.android/glowing-parakeet.keystore` and prompt you for:
- Your full name
- Organization details
- Country code
- Strong passwords for the keystore and key

**Important:** Keep this keystore file safe and never commit it to version control!

## Step 2: Encode the Keystore for GitHub Secrets

Encode your keystore as base64:

```bash
base64 ~/.android/glowing-parakeet.keystore | tr -d '\n' > keystore-encoded.txt
cat keystore-encoded.txt
```

Copy the output.

## Step 3: Add GitHub Secrets

1. Go to your repository: **Settings → Secrets and variables → Actions**
2. Create the following secrets:

| Secret Name | Value |
|---|---|
| `KEYSTORE_ENCODED` | Paste the base64-encoded keystore from Step 2 |
| `KEYSTORE_PASSWORD` | The password you set for the keystore |
| `KEY_ALIAS` | `glowing-parakeet-key` |
| `KEY_PASSWORD` | The password you set for the key |

**Steps to add secrets:**
1. Click "New repository secret"
2. Enter the name (e.g., `KEYSTORE_ENCODED`)
3. Paste the value
4. Click "Add secret"

## Step 4: Create a Release

### Using Git Tags:

```bash
# Increment your version in build.gradle first (versionCode and versionName)
# Then create a tag:
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

### Manual Trigger:

1. Go to **Actions** tab in your repository
2. Select "Build and Release APK"
3. Click "Run workflow"
4. Choose your branch
5. Click "Run workflow"

## Step 5: Download and Install

Once the workflow completes:

1. Go to **Releases** page in your repository
2. Find the latest release
3. Download the APK file
4. On your Android device:
   - Enable "Install from Unknown Sources" (Settings → Security)
   - Open the APK file
   - Tap "Install"

## Automated Workflow

The GitHub Actions workflow will automatically:
- Build a signed release APK
- Build a signed AAB (Android App Bundle)
- Create a GitHub Release
- Upload the files as release assets

## Incrementing Versions

For each new release, update:

```gradle
// In build.gradle
versionCode X+1  // Increment by 1
versionName "X.Y.Z"  // Update semantic version
```

Then:
```bash
git tag -a vX.Y.Z -m "Release version X.Y.Z"
git push origin vX.Y.Z
```

## Security Best Practices

1. **Never commit the keystore file** to your repository
2. **Back up your keystore** safely - losing it means you cannot update your app with new signing keys
3. **Keep passwords secure** - use a password manager
4. **Use GitHub Secrets** for all sensitive information
5. **Rotate your keystore** every few years as security best practice

## Troubleshooting

### Build fails with "Keystore not found"
- Verify `KEYSTORE_ENCODED` secret is set correctly
- Ensure all keystore secrets are defined

### APK fails to install
- Check device settings allow "Unknown Sources"
- Ensure you're replacing the same app (same package name)
- Clear app cache before reinstalling

### Version conflict when updating
- Increment `versionCode` in build.gradle
- The new APK must have a higher version code than the previous one

## Distribution Options

### Direct GitHub Distribution (Recommended for Development)
- Users download APK from GitHub Releases
- Fast updates
- Full control over distribution
- **Current setup**

### App Store Distribution (Production)
- Submit to Google Play Store
- Requires app signing
- Wider audience
- Store review process (2-24 hours)

### Third-Party App Stores
- F-Droid
- Amazon Appstore
- Samsung Galaxy Store
- Requires store-specific submission

## Additional Resources

- [Android App Signing Documentation](https://developer.android.com/studio/publish/app-signing)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [GitHub Secrets Documentation](https://docs.github.com/en/actions/security-guides/encrypted-secrets)
