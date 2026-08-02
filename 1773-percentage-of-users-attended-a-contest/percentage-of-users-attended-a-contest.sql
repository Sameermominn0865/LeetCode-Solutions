# Write your MySQL query statement below
SELECT contest_id, ROUND(count(*) * 100.0 / (select count(*) from Users), 2) as percentage
FROM Register r
GROUP BY r.contest_id
ORDER BY percentage DESC, contest_id ASC;
