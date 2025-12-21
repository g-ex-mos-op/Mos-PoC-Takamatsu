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
   ,   PREMIUM_FLG
   ,   rtrim(MEMO) as MEMO
   ,   CANCEL_FLG
   ,   CANCEL_DT
FROM   BT70CRSV
WHERE  CKANRI_NO = /*ckanriNo*/'200601'
AND    MISE_CD   = /*miseCd*/'2001'
AND    COMPANY_CD = /*companyCd*/'00'   
AND    RESERVE_DT = /*reserveDt*/'20061224'   
AND    CANCEL_FLG = '0'   
AND    RESERVE_HH between /*reserveFrom*/'14' and /*reserveTo*/'17'
/*IF premiumFlg != null && premiumFlg != ""*/
AND    PREMIUM_FLG = /*premiumFlg*/'0'
/*END*/
/*IF paymentFlg != null && paymentFlg != ""*/
AND    PAYMENT_FLG = /*paymentFlg*/'0'
/*END*/

ORDER BY RESERVE_HH
,        RESERVE_MM
,		 SEQ_NO
