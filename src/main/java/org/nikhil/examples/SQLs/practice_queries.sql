--Write a query to find the top 5 customers who have spent the most money in total across all their orders.
SELECT TOP 5 c.customer_id, c.first_name, c.last_name, SUM(o.total_amount) AS total_spent
FROM customers c, orders o
WHERE c.customer_id = o.customer_id
GROUP BY c.customer_id, c.first_name, c.last_name
ORDER BY total_spent DESC;

-- Write a query to find the top 5 products that have been ordered the most times.
SELECT TOP 5 p.product_id, p.product_name, COUNT(oi.order_item_id) AS total_orders
FROM products p
JOIN order_items oi ON p.product_id = oi.product_id
GROUP BY p.product_id, p.product_name
ORDER BY total_orders DESC;

-- Write a query to get top 3 products by revenue for each category.
SELECT c.category_id, c.category_name, p.product_id, p.product_name, SUM(oi.quantity * oi.price) AS total_revenue
FROM categories c
JOIN products p ON c.category_id = p.category_id
JOIN order_items oi ON p.product_id = oi.product_id
GROUP BY c.category_id, c.category_name, p.product_id, p.product_name
ORDER BY c.category_id, total_revenue DESC
LIMIT 3;

-- Write a query to find the average order value for each customer.
SELECT c.customer_id, c.first_name, c.last_name, AVG(o.total_amount) AS average_order_value
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.first_name, c.last_name
ORDER BY average_order_value DESC;

-- Write a query to find products where the total revenue is more than the average revenue of all products.
SELECT p.product_id, p.product_name, SUM(oi.quantity * oi.price) AS total_revenue
FROM products p
JOIN order_items oi ON p.product_id = oi.product_id
GROUP BY p.product_id, p.product_name
HAVING SUM(oi.quantity * oi.price) > (
    SELECT AVG(total_revenue)
    FROM (
        SELECT SUM(oi.quantity * oi.price) AS total_revenue
        FROM products p
        JOIN order_items oi ON p.product_id = oi.product_id
        GROUP BY p.product_id
    ) AS subquery
)
ORDER BY total_revenue DESC;

-- Write a query to find customer whose spending increased month-over-month using LAG and CASE.
WITH monthly_spending AS (
    SELECT c.customer_id,
           DATE_TRUNC('month', o.order_date) AS month,
           SUM(o.total_amount) AS total_spent
    FROM customers c
    JOIN orders o ON c.customer_id = o.customer_id
    GROUP BY c.customer_id, DATE_TRUNC('month', o.order_date)
),
monthly_comparison AS (
    SELECT customer_id,
           month,
           total_spent,
           LAG(total_spent) OVER (PARTITION BY customer_id ORDER BY month) AS previous_month_spending
    FROM monthly_spending
)
SELECT customer_id, month, total_spent, previous_month_spending
FROM monthly_comparison
WHERE total_spent > COALESCE(previous_month_spending, 0)
ORDER BY customer_id, month;

-- Write a query to find customer whose spending increased month-over-month in last 3 months.
WITH monthly_spending AS (
    SELECT c.customer_id,
           DATE_TRUNC('month', o.order_date) AS month,
           SUM(o.total_amount) AS total_spent
    FROM customers c
    JOIN orders o ON c.customer_id = o.customer_id
    WHERE o.order_date >= DATEADD(month, -3, CURRENT_DATE)
    GROUP BY c.customer_id, DATE_TRUNC('month', o.order_date)
),
monthly_comparison AS (
    SELECT customer_id,
           month,
           total_spent,
           LAG(total_spent) OVER (PARTITION BY customer_id ORDER BY month) AS previous_month_spending
    FROM monthly_spending
)
SELECT customer_id, month, total_spent, previous_month_spending
FROM monthly_comparison
WHERE total_spent > COALESCE(previous_month_spending, 0)
ORDER BY customer_id, month;

-- Write a query to find the top 5 customers who have placed the most orders in the last 6 months.
WITH recent_orders AS (
    SELECT customer_id, COUNT(order_id) AS order_count
    FROM orders
    WHERE order_date >= DATEADD(month, -6, CURRENT_DATE)
    GROUP BY customer_id
)
SELECT TOP 5 c.customer_id, c.first_name, c.last_name, ro.order_count
FROM customers c
JOIN recent_orders ro ON c.customer_id = ro.customer_id
ORDER BY ro.order_count DESC;

-- Write a query to find the top 5 products that have been ordered the most times in the last 6 months.
WITH recent_order_items AS (
    SELECT oi.product_id, COUNT(oi.order_item_id) AS total_orders
    FROM order_items oi
    JOIN orders o ON oi.order_id = o.order_id
    WHERE o.order_date >= DATEADD(month, -6, CURRENT_DATE)
    GROUP BY oi.product_id
)
SELECT TOP 5 p.product_id, p.product_name, roi.total_orders
FROM products p
JOIN recent_order_items roi ON p.product_id = roi.product_id
ORDER BY roi.total_orders DESC;

-- Write a query to find the top 3 products by revenue for each category in the last 6 months.
WITH recent_order_items AS (
    SELECT oi.product_id, oi.order_id, oi.quantity, oi.price
    FROM order_items oi
    JOIN orders o ON oi.order_id = o.order_id
    WHERE o.order_date >= DATEADD(month, -6, CURRENT_DATE)
),
category_revenue AS (
    SELECT c.category_id, c.category_name, p.product_id, p.product_name,
           SUM(roi.quantity * roi.price) AS total_revenue
    FROM categories c
    JOIN products p ON c.category_id = p.category_id
    JOIN recent_order_items roi ON p.product_id = roi.product_id
    GROUP BY c.category_id, c.category_name, p.product_id, p.product_name
)
SELECT category_id, category_name, product_id, product_name, total_revenue
FROM (
    SELECT cr.category_id, cr.category_name, cr.product_id, cr.product_name, cr.total_revenue,
           ROW_NUMBER() OVER (PARTITION BY cr.category_id ORDER BY cr.total_revenue DESC) AS rn
    FROM category_revenue cr
) AS ranked_products
WHERE rn <= 3
ORDER BY category_id, total_revenue DESC;

-- Write a query to find the average order value for each customer in the last 6 months.
WITH recent_orders AS (
    SELECT customer_id, order_id, total_amount
    FROM orders
    WHERE order_date >= DATEADD(month, -6, CURRENT_DATE)
)
SELECT c.customer_id, c.first_name, c.last_name, AVG(ro.total_amount) AS average_order_value
FROM customers c
JOIN recent_orders ro ON c.customer_id = ro.customer_id
GROUP BY c.customer_id, c.first_name, c.last_name
ORDER BY average_order_value DESC;

-- Write a query to find products where the total revenue is more than the average revenue of all products in the last 6 months.
WITH recent_order_items AS (
    SELECT oi.product_id, oi.quantity, oi.price
    FROM order_items oi
    JOIN orders o ON oi.order_id = o.order_id
    WHERE o.order_date >= DATEADD(month, -6, CURRENT_DATE)
),
product_revenue AS (
    SELECT p.product_id, p.product_name, SUM(roi.quantity * roi.price) AS total_revenue
    FROM products p
    JOIN recent_order_items roi ON p.product_id = roi.product_id
    GROUP BY p.product_id, p.product_name
)
SELECT pr.product_id, pr.product_name, pr.total_revenue
FROM product_revenue pr
WHERE pr.total_revenue > (
    SELECT AVG(total_revenue)
    FROM product_revenue
)
ORDER BY pr.total_revenue DESC;

-- Write a query to find the top 5 customers who have spent the most money in total across all their orders in the last 6 months.
WITH recent_orders AS (
    SELECT customer_id, total_amount
    FROM orders
    WHERE order_date >= DATEADD(month, -6, CURRENT_DATE)
)
SELECT TOP 5 c.customer_id, c.first_name, c.last_name, SUM(ro.total_amount) AS total_spent
FROM customers c
JOIN recent_orders ro ON c.customer_id = ro.customer_id
GROUP BY c.customer_id, c.first_name, c.last_name
ORDER BY total_spent DESC;








