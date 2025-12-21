SELECT
    BM01.COMPANY_CD
   ,BM01.MISE_CD
   ,BM01.MISE_NAME_KJ
   ,(case when CLOSE_DT < /*date*/'20061201' then 1 else 0 end) as CLOSE_FLG
FROM 
    BM01TENM BM01 
WHERE 
      BM01.COMPANY_CD = /*companyCd*/'00'
  AND BM01.ONER_CD    = /*onerCd*/'38001'

  /*IF limitFlg==true */
    AND BM01.MISE_CD IN ( 
        SELECT 
            BM50.MISE_CD
        FROM
            BM50TANM BM50 
        WHERE
            BM50.COMPANY_CD = /*companyCd*/'00' AND
            BM50.SV_CD = /*userId*/'00000121'
        GROUP BY
            BM50.MISE_CD 
        )
  /*END*/


ORDER BY 
    MISE_CD
  