insert into restaurant.restaurants(id, name, active)
    values ('c2e1f531-1518-4281-83e5-3b0124f77e1a', 'restaurant_1', true),
           ('c2e1f531-1518-4281-83e5-3b0124f77e1b', 'restaurant_2', false);

insert into restaurant.products(id, name, price, available)
    values ('7430b42a-f04b-4c48-807d-dc2643f283c1', 'product_1', 25.00, false),
           ('7430b42a-f04b-4c48-807d-dc2643f283c2', 'product_2', 50.00, true),
           ('7430b42a-f04b-4c48-807d-dc2643f283c3', 'product_3', 20.00, false),
           ('7430b42a-f04b-4c48-807d-dc2643f283c4', 'product_4', 40.00, true);

insert into restaurant.restaurant_products(id, restaurant_id, product_id)
    values ('7430b42a-f04b-4c48-807d-dc2643f283c5',  'c2e1f531-1518-4281-83e5-3b0124f77e1a', '7430b42a-f04b-4c48-807d-dc2643f283c1'),
           ('7430b42a-f04b-4c48-807d-dc2643f283c6',  'c2e1f531-1518-4281-83e5-3b0124f77e1a', '7430b42a-f04b-4c48-807d-dc2643f283c2'),
           ('7430b42a-f04b-4c48-807d-dc2643f283c7',  'c2e1f531-1518-4281-83e5-3b0124f77e1b', '7430b42a-f04b-4c48-807d-dc2643f283c3'),
           ('7430b42a-f04b-4c48-807d-dc2643f283c8',  'c2e1f531-1518-4281-83e5-3b0124f77e1b', '7430b42a-f04b-4c48-807d-dc2643f283c4');
