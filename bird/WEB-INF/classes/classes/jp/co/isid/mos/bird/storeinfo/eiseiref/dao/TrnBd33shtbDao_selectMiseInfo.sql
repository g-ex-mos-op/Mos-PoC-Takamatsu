SELECT /*$nendo*/'' AS BNFC_YEAR
,      BM01.COMPANY_CD
,      BM01.MISE_CD
,      BM01.MISE_NAME_KJ
,      BM10.SIBU_CD
,      BM10.SIBU_NAME
,      '' AS MACHINE1_NAME
,      '' AS MACHINE2_NAME
,      '' AS GRIDOLE1_NAME
,      '' AS GRIDOLE2_NAME
,      '' AS FRYER1_NAME
,      '' AS FRYER2_NAME
,      '' AS FRYER3_NAME
,      '' AS WATER_KIND
,      '' AS TYPE_START_DATE
,      '' AS TYPE_NM
,      '' AS PDF_FILENM
,      '' AS KAKU_DATE
,      '' AS TENSO_DATE

FROM BM01TENM BM01
,    BM10GSIB BM10

WHERE  BM01.COMPANY_CD = /*companyCd*/'00'
AND    BM01.MISE_CD    = /*miseCd*/'00018'
AND    BM10.COMPANY_CD = BM01.COMPANY_CD 
AND    BM10.SIBU_CD    = BM01.SIBU_CD

/*IF limitFlg && "01".equals(userTypeCd) */
AND    EXISTS(SELECT BM01.SIBU_CD, BM01.MISE_CD
             FROM BM01TENM BM01
             ,    BM51SVSB BM51
             WHERE BM51.SV_CD      = /*userId*/'99990003'
             AND   BM01.COMPANY_CD = BM51.COMPANY_CD 
             AND   BM01.SIBU_CD    = BM51.SIBU_CD)
/*END*/