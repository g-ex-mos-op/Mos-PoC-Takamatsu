SELECT
    segment_type
,   segment_name
FROM
    bm08sgtp
WHERE
    company_cd = /*companyCd*/'00'
ORDER BY
    sort_seq
