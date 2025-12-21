SELECT
    MISE_CD,
    RTRIM(MISE_NAME_KJ) AS MISE_NAME_KJ,
    OPEN_DT,
    CLOSE_DT,
    (case when CLOSE_DT > /*targetYM*/'200605' then '0' else '1' end) as CLOSE_FLG
FROM 
    BM01TENM
WHERE
    COMPANY_CD = '00' and
    ONER_CD IN /*onerCd*/('36961') 
/*IF !closeMiseFlg */
and CLOSE_DT > /*targetYM*/'200605'
/*END*/
ORDER BY MISE_CD