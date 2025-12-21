UPDATE   BT70CRSV
SET    CANCEL_FLG = /*entity.cancelFlg*/'1'
  ,    CANCEL_DT  =  /*entity.cancelDt*/'20061003'
  ,    LAST_TMSP  = /*entity.lastTmsp*/'2006-10-25 11:11:11.111111'   
  ,    LAST_USER = /*entity.lastUser*/'99990003'   
  ,     LAST_PGM = /*entity.lastPgm*/'BSS016'   
WHERE  CKANRI_NO  = /*entity.ckanriNo*/'200601'
AND    MISE_CD    = /*entity.miseCd*/'2001'
AND    COMPANY_CD = /*entity.companyCd*/'00'   
AND    RESERVE_DT = /*entity.reserveDt*/'20060901'   
AND    SEQ_NO = /*entity.seqNo*/2   
