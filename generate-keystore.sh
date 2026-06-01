#!/bin/bash

# Glowing Parakeet - APK Signing Key Generation Script
# This script generates a keystore for signing your Android app

set -e

# Colors for output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}APK Signing Keystore Generator${NC}"
echo -e "${GREEN}========================================${NC}"
echo ""

# Configuration
KEYSTORE_NAME="glowing-parakeet.keystore"
KEYSTORE_PATH="$HOME/.android/$KEYSTORE_NAME"
KEY_ALIAS="glowing-parakeet-key"
VALIDITY_DAYS=36500  # 100 years
KEY_SIZE=2048

echo -e "${YELLOW}This will create a signing key for your Android app.${NC}"
echo ""
echo "Keystore will be saved to: $KEYSTORE_PATH"
echo ""

# Prompt for user input
read -p "Enter your name (full name): " full_name
read -p "Enter your organizational unit: " org_unit
read -p "Enter your organization name: " org_name
read -p "Enter your city/locality: " city
read -p "Enter your state/province: " state
read -p "Enter your country code (e.g., US, GB, DE): " country
read -sp "Enter a strong password for the keystore (will not be displayed): " keystore_password
echo ""
read -sp "Confirm the password: " keystore_password_confirm
echo ""

if [ "$keystore_password" != "$keystore_password_confirm" ]; then
    echo -e "${RED}Error: Passwords do not match!${NC}"
    exit 1
fi

read -sp "Enter a password for the key (press enter for same as keystore): " key_password
echo ""

if [ -z "$key_password" ]; then
    key_password="$keystore_password"
fi

# Create ~/.android directory if it doesn't exist
mkdir -p "$HOME/.android"

# Generate the keystore
echo ""
echo -e "${YELLOW}Generating keystore...${NC}"

keytool -genkey -v -keystore "$KEYSTORE_PATH" \
    -keyalg RSA \
    -keysize $KEY_SIZE \
    -validity $VALIDITY_DAYS \
    -alias "$KEY_ALIAS" \
    -storepass "$keystore_password" \
    -keypass "$key_password" \
    -dname "CN=$full_name, OU=$org_unit, O=$org_name, L=$city, ST=$state, C=$country"

echo ""
echo -e "${GREEN}✓ Keystore created successfully!${NC}"
echo ""
echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}Next Steps:${NC}"
echo -e "${GREEN}========================================${NC}"
echo ""
echo "1. Keep your keystore file safe:"
echo "   Location: $KEYSTORE_PATH"
echo ""
echo "2. Add to your GitHub repository (encrypted):"
echo "   - Store keystore password in GitHub Secrets as: KEYSTORE_PASSWORD"
echo "   - Store key password in GitHub Secrets as: KEY_PASSWORD"
echo ""
echo "3. Update your build.gradle with signing config"
echo ""
echo -e "${YELLOW}WARNING: Never commit the keystore file to version control!${NC}"
echo "Keep it safe and backed up."
echo ""

# Generate a summary file
SUMMARY_FILE="$HOME/.android/keystore-info.txt"
cat > "$SUMMARY_FILE" << EOF
Glowing Parakeet - Keystore Information
Generated: $(date)

Keystore Details:
  Path: $KEYSTORE_PATH
  Alias: $KEY_ALIAS
  Validity: $VALIDITY_DAYS days
  Key Size: $KEY_SIZE bits

Owner Information:
  Name: $full_name
  Organization: $org_name
  Organization Unit: $org_unit
  Location: $city, $state, $country

IMPORTANT: Store the keystore password and key password securely!
EOF

echo "Keystore info saved to: $SUMMARY_FILE"
echo ""
