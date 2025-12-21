SELECT
    RTRIM(bm03uscp.r_company_cd) AS COMPANY_CD,
    RTRIM(bc02comp.company_name) AS COMPANY_NAME
FROM
    bm03uscp JOIN bc02comp ON (bm03uscp.r_company_cd = bc02comp.r_company_cd)
WHERE
    bm03uscp.user_id = /*userId*/'99990003'
AND bc02comp.mise_flg = '1'
ORDER BY
    bc02comp.sort_seq