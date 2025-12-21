SELECT BD34.BNFC_YEAR
,      BD34.STATUS_CD
,      BD34.COMPANY_CD
,      BD34.MISE_CD
,      BD34.STATUS_NM
,      BD34.APPEND_DATE
,      BD34.PDF_FILENM
,      BD34.KAKU_DATE
,      BD34.TENSO_DATE

FROM BD34VSTB BD34

WHERE  BD34.BNFC_YEAR  = /*nendo*/'2013'
AND    BD34.COMPANY_CD = /*companyCd*/'00'
AND    BD34.MISE_CD    = /*miseCd*/'00018'
/*IF statusCd != null && statusCd != "" */
AND    BD34.STATUS_CD  = /*statusCd*/'1'
/*END*/
/*IF limitFlg && "01".equals(userTypeCd) */
AND    EXISTS(SELECT BM01.SIBU_CD, BM01.MISE_CD
             FROM BM01TENM BM01
             ,    BM51SVSB BM51
             WHERE BM51.SV_CD      = /*userId*/'99990003'
             AND   BM01.COMPANY_CD = BM51.COMPANY_CD 
             AND   BD34.COMPANY_CD = BM01.COMPANY_CD 
             AND   BM01.SIBU_CD    = BM51.SIBU_CD
             AND   BD34.MISE_CD    = BM01.MISE_CD)
/*END*/
ORDER BY APPEND_DATE desc
