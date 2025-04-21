-- Insert Categories
INSERT INTO categories (name, description) VALUES 
('Antibiotics', 'Medications that fight bacterial infections'),
('Painkillers', 'Medications that relieve pain'),
('Vitamins', 'Nutritional supplements'),
('Antidepressants', 'Medications for treating depression');

-- Insert Suppliers
INSERT INTO suppliers (name, contact_number, email, address) 
VALUES 
('MediPharm Inc', '555-0101', 'contact@medipharm.com', '123 Pharma Street, Medical City'),
('HealthCare Supplies', '555-0102', 'info@healthcaresupplies.com', '456 Health Avenue, Care Town'),
('Global Pharma', '555-0103', 'sales@globalpharma.com', '789 Global Road, Pharma City');

-- Insert Medicines
-- Note: Using CURRENT_DATE for expiry dates, in real application these would be specific dates
INSERT INTO medicines (name, manufacturer, expiry_date, price, quantity_in_stock, category_id, supplier_id)
VALUES 
('Amoxicillin', 'MediPharm Labs', CURRENT_DATE + 365, 15.99, 100, 1, 1),
('Ibuprofen', 'HealthCare Pharma', CURRENT_DATE + 730, 8.99, 200, 2, 2),
('Vitamin C', 'Global Vitamins', CURRENT_DATE + 548, 12.99, 150, 3, 3),
('Sertraline', 'MediPharm Labs', CURRENT_DATE + 730, 45.99, 50, 4, 1),
('Paracetamol', 'HealthCare Pharma', CURRENT_DATE + 365, 5.99, 300, 2, 2),
('Vitamin D3', 'Global Vitamins', CURRENT_DATE + 548, 18.99, 100, 3, 3); 