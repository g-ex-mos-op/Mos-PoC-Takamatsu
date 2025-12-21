SELECT BD32.BNFC_YEAR
,      BD32.STATUS_CD
,      BD32.COMPANY_CD
,      BD32.MISE_CD
,      BD32.STATUS_NM
,      BD32.EX_DATE
,      BD32.EX_JISSHI_KBN
,      BD32.EX_JISSHI_KBN_NM
,      BD32.PDF_FILENM
,      BD32.KAKU_DATE
,      BD32.TENSO_DATE

FROM BD32SETB BD32

WHERE  BD32.BNFC_YEAR  = /*nendo*/'2013'
AND    BD32.COMPANY_CD = /*companyCd*/'00'
AND    BD32.MISE_CD    = /*miseCd*/'00018'
/*IF statusCd != null && statusCd != "" */
AND    BD32.STATUS_CD  = /*statusCd*/'1'
/*END*/
/*IF limitFlg && "01".equals(userTypeCd) */
AND    EXISTS(SELECT BM01.SIBU_CD, BM01.MISE_CD
             FROM BM01TENM BM01
             ,    BM51SVSB BM51
             WHERE BM51.SV_CD      = /*userId*/'99990003'
             AND   BM01.COMPANY_CD = BM51.COMPANY_CD 
             AND   BD32.COMPANY_CD = BM01.COMPANY_CD 
             AND   BM01.SIBU_CD    = BM51.SIBU_CD
             AND   BD32.MISE_CD    = BM01.MISE_CD)
/*END*/
ORDER BY EX_DATE desc
,                   STATUS_CD desc
