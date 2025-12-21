SELECT CKANRI_NO
   ,   COMPANY_CD
   ,   MISE_CD
   ,   SEQ_NO
   ,   RESERVE_DT
   ,   ACCEPT_DT
   ,   RESERVE_HH
   ,   RESERVE_MM
   ,   rtrim(REMARK) as REMARK
   ,   PAYMENT_FLG
   ,   rtrim(MEMO) as MEMO
   ,   CANCEL_FLG
   ,   CANCEL_DT
   ,   LAST_TMSP
   ,   PREMIUM_FLG 
FROM   BT70CRSV
WHERE  CKANRI_NO = /*ckanriNo*/'200601'
AND    MISE_CD   = /*miseCd*/'2001'
AND    COMPANY_CD = /*companyCd*/'00'   
AND    RESERVE_DT = /*reserveDt*/'20060901'   
ORDER BY RESERVE_HH
,        RESERVE_MM
,		 SEQ_NO