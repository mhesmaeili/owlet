INSERT INTO idm.role_permission
(
    id,
    role_id,
    permission_id,
    granted
)
SELECT
    gen_random_uuid(),
    r.id,
    p.id,
    true
FROM idm.role r
         CROSS JOIN idm.permission p
WHERE r.code = 'Super_Administrator'
  AND NOT EXISTS (
    SELECT 1
    FROM idm.role_permission rp
    WHERE rp.role_id = r.id
      AND rp.permission_id = p.id
);