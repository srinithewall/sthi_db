#!/bin/bash

# ===============================
# Vertical Scaffolding Generator
# ===============================

SCRIPT_VERSION="1.0"
BASE_PACKAGE="com.sthi"
SRC_DIR="src/main/java"

# -------- Helpers --------
error() {
  echo "❌ $1"
  exit 1
}

info() {
  echo "✅ $1"
}

# -------- Input Validation --------
if [ -z "$1" ]; then
  error "Vertical name is required. Usage: ./create-vertical.sh <vertical>"
fi

VERTICAL="$1"

# Allow only lowercase letters
if [[ ! "$VERTICAL" =~ ^[a-z]+$ ]]; then
  error "Vertical name must contain only lowercase letters (a-z)"
fi

# Capitalize first letter for class names
VERTICAL_CAP="$(tr '[:lower:]' '[:upper:]' <<< ${VERTICAL:0:1})${VERTICAL:1}"

VERTICAL_PATH="$SRC_DIR/$(echo $BASE_PACKAGE | tr '.' '/')/$VERTICAL"

# -------- Safety Check --------
if [ -d "$VERTICAL_PATH" ]; then
  error "Vertical '$VERTICAL' already exists at $VERTICAL_PATH"
fi

info "Creating vertical '$VERTICAL'..."

# -------- Directory Structure --------
mkdir -p \
"$VERTICAL_PATH/controller" \
"$VERTICAL_PATH/service/impl" \
"$VERTICAL_PATH/repo" \
"$VERTICAL_PATH/model" \
"$VERTICAL_PATH/dto/request" \
"$VERTICAL_PATH/dto/response" \
"$VERTICAL_PATH/exception" \
"$VERTICAL_PATH/constants"

# -------- Files --------

# Base Controller
cat <<EOF > "$VERTICAL_PATH/controller/${VERTICAL_CAP}BaseController.java"
package $BASE_PACKAGE.$VERTICAL.controller;

/**
 * Base controller for $VERTICAL vertical.
 * Common controller-level logic can go here.
 */
public abstract class ${VERTICAL_CAP}BaseController {
}
EOF

# Service Interface
cat <<EOF > "$VERTICAL_PATH/service/${VERTICAL_CAP}Service.java"
package $BASE_PACKAGE.$VERTICAL.service;

/**
 * Base service interface for $VERTICAL vertical.
 */
public interface ${VERTICAL_CAP}Service {
}
EOF

# Service Implementation
cat <<EOF > "$VERTICAL_PATH/service/impl/${VERTICAL_CAP}ServiceImpl.java"
package $BASE_PACKAGE.$VERTICAL.service.impl;

import $BASE_PACKAGE.$VERTICAL.service.${VERTICAL_CAP}Service;
import org.springframework.stereotype.Service;

/**
 * Base service implementation for $VERTICAL vertical.
 */
@Service
public class ${VERTICAL_CAP}ServiceImpl implements ${VERTICAL_CAP}Service {
}
EOF

# Vertical Exception
cat <<EOF > "$VERTICAL_PATH/exception/${VERTICAL_CAP}Exception.java"
package $BASE_PACKAGE.$VERTICAL.exception;

/**
 * Base exception for $VERTICAL vertical.
 */
public class ${VERTICAL_CAP}Exception extends RuntimeException {

    public ${VERTICAL_CAP}Exception(String message) {
        super(message);
    }

    public ${VERTICAL_CAP}Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
EOF

# Constants
cat <<EOF > "$VERTICAL_PATH/constants/${VERTICAL_CAP}Constants.java"
package $BASE_PACKAGE.$VERTICAL.constants;

/**
 * Constants for $VERTICAL vertical.
 */
public final class ${VERTICAL_CAP}Constants {

    private ${VERTICAL_CAP}Constants() {
        // prevent instantiation
    }
}
EOF

# README
cat <<EOF > "$VERTICAL_PATH/README.md"
# $VERTICAL_CAP Vertical

This module contains all code related to the **$VERTICAL_CAP** vertical.

## Structure
- controller
- service
- repo
- model
- dto
- exception
- constants

## Notes
- This vertical was generated using create-vertical.sh
- Do not place cross-vertical logic here
EOF

# Vertical version marker
cat <<EOF > "$VERTICAL_PATH/.vertical-version"
vertical=$VERTICAL
architecture=v1
generatedBy=create-vertical.sh
scriptVersion=$SCRIPT_VERSION
EOF

info "Vertical '$VERTICAL' created successfully."
info "Architecture version: v1"
