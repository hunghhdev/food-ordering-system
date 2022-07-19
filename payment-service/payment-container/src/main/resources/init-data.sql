insert into payment.credit_entry(id, customer_id, total_credit_amount)
    values ('4502b002-ea72-456e-8664-d7dea4d8891e', '44494d1f-6451-4073-9345-1a5ec34b4941', 10500.00);
insert into payment.credit_history(id, customer_id, amount, type)
    values ('e59d4551-9a79-4d05-b473-89a80c8c6ce7', '44494d1f-6451-4073-9345-1a5ec34b4941', 10100.00, 'CREDIT'),
           ('299a6716-4e7d-45a3-9cae-032562d17402', '44494d1f-6451-4073-9345-1a5ec34b4941', 600.00, 'CREDIT'),
           ('f8e5a432-1ee3-47c6-9b31-2eae47d02ec8', '44494d1f-6451-4073-9345-1a5ec34b4941', 200.00, 'DEBIT');

insert into payment.credit_entry(id, customer_id, total_credit_amount)
    values ('95aac5d0-74cf-44f0-a10f-b4712a5bb209', '44494d1f-6451-4073-9345-1a5ec34b4942', 100.00);
insert into payment.credit_history(id, customer_id, amount, type)
    values ('09099c04-5685-4591-9ef8-fa68dea222cf', '44494d1f-6451-4073-9345-1a5ec34b4942', 100.00, 'CREDIT');
