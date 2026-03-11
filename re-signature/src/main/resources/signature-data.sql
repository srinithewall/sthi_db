INSERT INTO ds_agreement_templates (name, version, content, is_active) 
VALUES (
  'Standard Purchase Agreement', '1.0',
  'PURCHASE AND SALE AGREEMENT\n\nDate: {{agreement_date}}\n\nBUYER: {{buyer_name}}\nSELLER: {{seller_name}}\nBROKER: {{broker_name}}\n\nPROPERTY: {{property_address}}\nPURCHASE PRICE: USD ${{purchase_price}}\nCLOSING DATE: {{closing_date}}\n\n[Agreement terms...]\n\nBUYER SIGNATURE:\n\\s1\\\nDate: \\d1\\\nName: {{buyer_name}}\n\nSELLER SIGNATURE:\n\\s2\\\nDate: \\d2\\\nName: {{seller_name}}',
  TRUE
);

INSERT INTO ds_template_fields (template_id, field_key, field_label, field_type, is_required, display_order)
VALUES
(1, 'buyer_name', 'Buyer Full Name', 'text', true, 1),
(1, 'buyer_email', 'Buyer Email', 'text', true, 2),
(1, 'seller_name', 'Seller Full Name', 'text', true, 3),
(1, 'seller_email', 'Seller Email', 'text', true, 4),
(1, 'broker_name', 'Broker Name', 'text', true, 5),
(1, 'broker_email', 'Broker Email', 'text', true, 6),
(1, 'property_address', 'Property Address', 'text', true, 7),
(1, 'purchase_price', 'Purchase Price (USD)', 'currency', true, 8),
(1, 'closing_date', 'Closing Date', 'date', true, 9);
