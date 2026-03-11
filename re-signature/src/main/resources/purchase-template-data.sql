INSERT INTO ds_agreement_templates 
  (name, version, content, template_type, is_active)
VALUES (
  'Standard Purchase Agreement - India', '1.0',
  'AGREEMENT TO SELL\n\nThis Agreement to Sell is made on {{agreement_date}} at {{city}}, {{state}}, India.\n\nBETWEEN\n\nSELLER (Party 2):\nName    : {{seller_name}}\nAadhaar : {{seller_aadhaar}}\nPAN     : {{seller_pan}}\nAddress : {{seller_address}}\n\nAND\n\nBUYER (Party 1):\nName    : {{buyer_name}}\nAadhaar : {{buyer_aadhaar}}\nPAN     : {{buyer_pan}}\nAddress : {{buyer_address}}\n\nPROPERTY DETAILS:\nProperty Type: {{property_type}}\nLocation: {{property_address}}, {{property_city}} - {{property_pincode}}\n\nCONSIDERATION:\n1. Total Purchase Price: INR {{total_price}}/-\n2. Advance/Token Amount: INR {{advance_amount}}/-\n3. Balance Payment to be paid on or before: {{payment_deadline}}\n\nTERMS:\n1. The Seller warrants that the property is free from all encumbrances.\n2. The Buyer agrees to complete the registration within the stipulated time.\n3. Possession shall be handed over upon full and final payment.\n\nThis agreement is legally binding under the laws of India.',
  'PURCHASE',
  TRUE
);
