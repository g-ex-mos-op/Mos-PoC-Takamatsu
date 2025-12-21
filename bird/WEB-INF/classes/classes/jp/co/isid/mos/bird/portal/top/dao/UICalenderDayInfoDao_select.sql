SELECT BR23.EIGYO_DT
,      BR23.ZENNEN_DT AS DOGETU_DT
,      CASE WHEN SUBSTR(BR23.EIGYO_DT, 5,4)='0229' THEN ''
       ELSE REPLACE(CHAR(DATE(SUBSTR(BR23.EIGYO_DT, 1,4)
                       ||'-'|| SUBSTR(BR23.EIGYO_DT, 5,2)
                       ||'-'|| SUBSTR(BR23.EIGYO_DT, 7,2))-1 YEAR)
                       ,'-','')
       END AS DOJITU_DT
,      REPLACE(CHAR(DATE(SUBSTR(BR23.EIGYO_DT, 1,4)
                       ||'-'|| SUBSTR(BR23.EIGYO_DT, 5,2)
                       ||'-'|| SUBSTR(BR23.EIGYO_DT, 7,2))-364 DAYS),'-','') AS DOYO_DT
FROM BR23DDSE BR23

WHERE BR23.COMPANY_CD = /*companyCd*/'00'
AND   SUBSTR(BR23.EIGYO_DT, 1,6) = /*targetYM*/'200802'
ORDER BY BR23.EIGYO_DT