SELECT DISTINCT 
       BM01.COMPANY_CD
,      BM01.MISE_CD
,      RTRIM(CHAR(REPLACE(BM01.MISE_NAME_KJ , '  ', '  '), 40)) AS MISE_NAME_KJ
,      BM01.SIBU_CD
,      BM01.GYOTAI_KBN
,      BM01.OPEN_DT
,      BM01.CLOSE_DT
,      CASE WHEN CLOSE_DT <= /*sysDate*/'20070228' THEN '(CLOSE)'
            ELSE ''
       END  AS CLOSE_MJ

FROM   BM01TENM as BM01

WHERE BM01.COMPANY_CD = /*companyCd*/'00'
AND   BM01.MISE_CD    = /*miseCd*/'36478'
/*IF userTypeCd.equals("01") && limitFlg */
AND   EXISTS  (
      SELECT * FROM BM51SVSB BM51
      WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
      AND    BM51.SV_CD      = /*userId*/'99990005'
      AND    BM51.SIBU_CD    = BM01.SIBU_CD
  )
/*END*/
ORDER BY MISE_CD

