CREATE TABLE IF NOT EXISTS ds_agreement_templates (
  id                    BIGINT PRIMARY KEY AUTO_INCREMENT,
  name                  VARCHAR(255) NOT NULL,
  version               VARCHAR(20),
  content               LONGTEXT NOT NULL,
  template_type         VARCHAR(50),
  docusign_template_id  VARCHAR(100),
  is_active             BOOLEAN DEFAULT TRUE,
  created_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ds_template_fields (
  id              BIGINT PRIMARY KEY AUTO_INCREMENT,
  template_id     BIGINT NOT NULL REFERENCES ds_agreement_templates(id),
  field_key       VARCHAR(100) NOT NULL,
  field_label     VARCHAR(255) NOT NULL,
  field_type      ENUM('text','number','date','currency') NOT NULL,
  is_required     BOOLEAN DEFAULT TRUE,
  display_order   INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS ds_agreements (
  id              BIGINT PRIMARY KEY AUTO_INCREMENT,
  template_id     BIGINT REFERENCES ds_agreement_templates(id),
  envelope_id     VARCHAR(100),
  broker_name     VARCHAR(255),
  broker_email    VARCHAR(255),
  buyer_name      VARCHAR(255),
  buyer_email     VARCHAR(255),
  seller_name     VARCHAR(255),
  seller_email    VARCHAR(255),
  field_values    JSON,
  tenant_kyc_status ENUM('PENDING','UPLOADED','VERIFIED') DEFAULT 'PENDING',
  owner_kyc_status  ENUM('PENDING','UPLOADED','VERIFIED') DEFAULT 'PENDING',
  tenant_signed_at  TIMESTAMP NULL,
  owner_signed_at   TIMESTAMP NULL,
  tenant_ip_address VARCHAR(45),
  owner_ip_address  VARCHAR(45),
  final_pdf_path    VARCHAR(500),
  doc_hash          VARCHAR(64),
  status          ENUM('DRAFT','SENT','PARTIAL','COMPLETED','VOIDED') DEFAULT 'DRAFT',
  agreement_status  ENUM('DRAFT','TENANT_KYC_DONE','OWNER_KYC_DONE','BOTH_KYC_DONE','TENANT_SIGNED','OWNER_SIGNED','COMPLETED') DEFAULT 'DRAFT',
  sent_at         TIMESTAMP NULL,
  completed_at    TIMESTAMP NULL,
  created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ds_agreement_signatures (
  id                      BIGINT PRIMARY KEY AUTO_INCREMENT,
  agreement_id            BIGINT NOT NULL REFERENCES ds_agreements(id),
  signer_role             ENUM('BUYER','SELLER') NOT NULL,
  signer_name             VARCHAR(255),
  signer_email            VARCHAR(255),
  signed_at               TIMESTAMP NULL,
  ip_address              VARCHAR(45),
  docusign_recipient_id   VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS ds_kyc_documents (
  id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
  agreement_id        BIGINT NOT NULL,
  party_type          ENUM('TENANT','OWNER') NOT NULL,
  doc_type            ENUM('AADHAAR','PAN') NOT NULL,
  file_path           VARCHAR(500) NOT NULL,
  extracted_name      VARCHAR(255),
  extracted_dob       VARCHAR(20),
  extracted_gender    VARCHAR(10),
  extracted_address   TEXT,
  extracted_pincode   VARCHAR(10),
  aadhaar_number      VARCHAR(20),
  pan_number          VARCHAR(10),
  father_name         VARCHAR(255),
  confidence_score    DECIMAL(3,2),
  raw_ai_response     TEXT,
  extraction_status   ENUM('PENDING','SUCCESS','FAILED') DEFAULT 'PENDING',
  extracted_at        TIMESTAMP NULL,
  created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (agreement_id) REFERENCES ds_agreements(id)
);

CREATE TABLE IF NOT EXISTS ds_signature_otp (
  id              BIGINT PRIMARY KEY AUTO_INCREMENT,
  agreement_id    BIGINT NOT NULL,
  party_type      ENUM('TENANT','OWNER') NOT NULL,
  mobile_number   VARCHAR(15) NOT NULL,
  otp_hash        VARCHAR(255) NOT NULL,
  expires_at      TIMESTAMP NOT NULL,
  verified_at     TIMESTAMP NULL,
  is_used         BOOLEAN DEFAULT FALSE,
  created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (agreement_id) REFERENCES ds_agreements(id)
);
