-- Database in mind: PostgreSQL.
-- (The CASE expressions below are standard SQL and also run unchanged
-- on MySQL 8+, with no vendor-specific syntax used.)
--
-- Assumption: "leads" has columns id, name, email, score as described
-- in the assessment, and score is always a non-null integer from 0 to
-- 100, so no NULL-handling for score is needed.

-- 1. All HOT leads (score of 80 or higher)
SELECT id, name, email, score
FROM leads
WHERE score >= 80
ORDER BY score DESC;

-- 2. Count of leads in each priority group (HOT / WARM / COLD)
SELECT
    CASE
        WHEN score >= 80 THEN 'HOT'
        WHEN score >= 50 THEN 'WARM'
        ELSE 'COLD'
    END AS priority,
    COUNT(*) AS lead_count
FROM leads
GROUP BY
    CASE
        WHEN score >= 80 THEN 'HOT'
        WHEN score >= 50 THEN 'WARM'
        ELSE 'COLD'
    END
ORDER BY priority;
