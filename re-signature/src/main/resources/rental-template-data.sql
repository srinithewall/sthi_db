INSERT INTO ds_agreement_templates 
  (name, version, content, template_type, is_active)
VALUES (
  'Standard Rental Agreement - India', '1.0',
  'RENTAL AGREEMENT\n\nThis Rental Agreement is made on {{agreement_date}} at {{city}}, {{state}}, India.\n\nBETWEEN\n\nOWNER (Party 2):\nName    : {{owner_name}}\nAadhaar : {{owner_aadhaar}}\nPAN     : {{owner_pan}}\nAddress : {{owner_address}}\n\nAND\n\nTENANT (Party 1):\nName    : {{tenant_name}}\nAadhaar : {{tenant_aadhaar}}\nPAN     : {{tenant_pan}}\nAddress : {{tenant_address}}\n\nPROPERTY:\n{{property_address}}, {{property_city}} - {{property_pincode}}\n\nTERMS:\n1. Rent: INR {{monthly_rent}}/- per month\n2. Security Deposit: INR {{security_deposit}}/-\n3. Lease Period: {{lease_start}} to {{lease_end}}\n4. Lock-in Period: {{lockin_months}} months\n5. Notice Period: {{notice_days}} days\n\nThis agreement is governed by the laws of India.',
  'RENTAL',
  TRUE
);
