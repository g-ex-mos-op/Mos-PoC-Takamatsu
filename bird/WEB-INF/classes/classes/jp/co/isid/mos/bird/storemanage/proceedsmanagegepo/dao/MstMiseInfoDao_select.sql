SELECT
    RTRIM(bm01tenm.mise_cd) AS MISE_CD,
    (CASE WHEN bm01tenm.close_dt >= /*appDate*/'20060823' 
          THEN RTRIM(CHAR(REPLACE(bm01tenm.mise_name_kj, '  ', '  '), 40)) 
          ELSE RTRIM(CHAR(REPLACE(bm01tenm.mise_name_kj, '  ', '  '), 40)) CONCAT '(CLOSE)'
     END) AS MISE_NAME_KJ,
    (CASE WHEN bm01tenm.close_dt >= /*appDate*/'20060823' THEN 0 ELSE 1 END) AS CLOSE_FLG
FROM
    bm01tenm
WHERE
    bm01tenm.company_cd = /*companyCd*/'00'
AND bm01tenm.oner_cd = /*onerCd*/'36378'
ORDER BY
    close_flg,
    bm01tenm.mise_cd