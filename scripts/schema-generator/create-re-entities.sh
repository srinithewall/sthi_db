#!/bin/bash
set -e

DEF_FILE="scripts/schema-generator/re-entities.def"
MODEL_DIR="src/main/java/com/sthi/re/model"
DTO_DIR="src/main/java/com/sthi/re/dto/response"

MODEL_PKG="com.sthi.re.model"
DTO_PKG="com.sthi.re.dto.response"

mkdir -p "$MODEL_DIR" "$DTO_DIR"

echo "🚀 Starting RE Schema Generator..."

declare -A ENTITY_MAP

capitalize() {
  echo "$(tr '[:lower:]' '[:upper:]' <<< ${1:0:1})${1:1}"
}

generate_entity() {
  local entity="$1"
  local table="$2"
  shift 2
  local fields=("$@")

  local model_file="$MODEL_DIR/$entity.java"
  local dto_file="$DTO_DIR/${entity}Dto.java"

  if [ -f "$model_file" ]; then
    echo "❌ Entity already exists: $entity.java"
    exit 1
  fi

  declare -A FIELD_NAMES
  declare -A COLUMN_NAMES

  {
    echo "package $MODEL_PKG;"
    echo
    echo "import jakarta.persistence.*;"
    echo "import java.time.LocalDateTime;"
    echo
    echo "@Entity"
    echo "@Table(name = \"$table\")"
    echo "public class $entity {"
    echo

    local is_first_field=true

    for field in "${fields[@]}"; do
      IFS=":" read name type column <<< "$field"

      [[ -n "${FIELD_NAMES[$name]}" ]] && { echo "❌ Duplicate field $name"; exit 1; }
      [[ -n "${COLUMN_NAMES[$column]}" ]] && { echo "❌ Duplicate column $column"; exit 1; }

      FIELD_NAMES[$name]=1
      COLUMN_NAMES[$column]=1

      if [ "$is_first_field" = true ]; then
        echo "    @Id"
        echo "    @GeneratedValue(strategy = GenerationType.IDENTITY)"
        is_first_field=false
      fi

      echo "    @Column(name = \"$column\")"
      echo "    private $type $name;"
      echo
    done

    for field in "${fields[@]}"; do
      IFS=":" read name type column <<< "$field"
      capName=$(capitalize "$name")

      echo "    public $type get$capName() {"
      echo "        return $name;"
      echo "    }"
      echo
      echo "    public void set$capName($type $name) {"
      echo "        this.$name = $name;"
      echo "    }"
      echo
    done

    echo "}"
  } > "$model_file"

  {
    echo "package $DTO_PKG;"
    echo
    echo "import java.time.LocalDateTime;"
    echo
    echo "public class ${entity}Dto {"
    echo

    for field in "${fields[@]}"; do
      IFS=":" read name type column <<< "$field"
      echo "    private $type $name;"
    done
    echo

    for field in "${fields[@]}"; do
      IFS=":" read name type column <<< "$field"
      capName=$(capitalize "$name")

      echo "    public $type get$capName() {"
      echo "        return $name;"
      echo "    }"
      echo
    done

    echo "}"
  } > "$dto_file"

  echo "✅ Generated: $entity (Entity + DTO)"
}

CURRENT_ENTITY=""
TABLE_NAME=""
FIELDS=()

while IFS= read -r line || [ -n "$line" ]; do
  line="$(echo "$line" | xargs)"
  [[ -z "$line" || "$line" =~ ^# ]] && continue

  if [[ "$line" == ENTITY* ]]; then
    read _ CURRENT_ENTITY TABLE_NAME <<< "$line"
    [[ -n "${ENTITY_MAP[$CURRENT_ENTITY]}" ]] && { echo "❌ Duplicate entity $CURRENT_ENTITY"; exit 1; }
    ENTITY_MAP[$CURRENT_ENTITY]=1
    FIELDS=()
  elif [[ "$line" == END ]]; then
    generate_entity "$CURRENT_ENTITY" "$TABLE_NAME" "${FIELDS[@]}"
    CURRENT_ENTITY=""
    TABLE_NAME=""
    FIELDS=()
  else
    FIELDS+=("$line")
  fi
done < "$DEF_FILE"

echo "🎉 RE Schema Generation Completed Successfully"
