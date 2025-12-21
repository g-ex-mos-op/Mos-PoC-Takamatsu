SELECT
    RTRIM(bm01tenm.mise_cd)
    CONCAT ' '
    CONCAT RTRIM(CHAR(REPLACE(bm01tenm.mise_name_kj, '  ', '  '), 40))
FROM
    bm01tenm
WHERE
    bm01tenm.company_cd = /*companyCd*/'00'
AND bm01tenm.mise_cd = /*miseCd*/'01372'