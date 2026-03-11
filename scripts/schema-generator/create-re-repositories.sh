#!/bin/bash
set -e

DEF_FILE="scripts/schema-generator/re-entities.def"
REPO_DIR="src/main/java/com/sthi/re/repo"
MODEL_PKG="com.sthi.re.model"
REPO_PKG="com.sthi.re.repo"

mkdir -p "$REPO_DIR"

echo "🚀 Starting RE Repository Generator..."

CURRENT_ENTITY=""
ID_TYPE=""

while IFS= read -r line || [ -n "$line" ]; do
  line="$(echo "$line" | xargs)"
  [[ -z "$line" || "$line" =~ ^# ]] && continue

  if [[ "$line" == ENTITY* ]]; then
    read _ CURRENT_ENTITY _ <<< "$line"
    ID_TYPE=""
  elif [[ "$line" == END ]]; then
    REPO_FILE="$REPO_DIR/${CURRENT_ENTITY}Repository.java"

    if [ -f "$REPO_FILE" ]; then
      echo "❌ Repository already exists: ${CURRENT_ENTITY}Repository"
      exit 1
    fi

    if [[ -z "$ID_TYPE" ]]; then
      echo "❌ No ID field found for entity $CURRENT_ENTITY"
      exit 1
    fi

    {
      echo "package $REPO_PKG;"
      echo
      echo "import org.springframework.data.jpa.repository.JpaRepository;"
      echo "import $MODEL_PKG.$CURRENT_ENTITY;"
      echo
      echo "public interface ${CURRENT_ENTITY}Repository extends JpaRepository<$CURRENT_ENTITY, $ID_TYPE> {"
      echo "}"
    } > "$REPO_FILE"

    echo "✅ Generated: ${CURRENT_ENTITY}Repository"

    CURRENT_ENTITY=""
    ID_TYPE=""
  else
    # Capture first *Id field as primary key
    if [[ -z "$ID_TYPE" ]]; then
      IFS=":" read name type _ <<< "$line"
      if [[ "$name" == *Id ]]; then
        ID_TYPE="$type"
      fi
    fi
  fi
done < "$DEF_FILE"

echo "🎉 RE Repository Generation Completed"
