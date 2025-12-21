UPDATE BT43KTKT
   SET MISE_CD = /*tempStoreInfo.miseCd*/'00000',
       KAKUTEI_DT = /*tempStoreInfo.kakuteiDt*/'20060101',
       SET_FLG = /*tempStoreInfo.setFlg*/'0',
       LAST_USER = /*tempStoreInfo.lastUser*/'',
       LAST_PGM  = /*tempStoreInfo.lastPgm*/'BBS025',
       LAST_TMSP = /*tempStoreInfo.lastTmsp*/''
 WHERE NENDO = /*tempStoreInfo.nendo*/'200611'
   AND KARI_CD = /*tempStoreInfo.kariCd*/'00'
   AND COMPANY_CD = /*tempStoreInfo.companyCd*/'0000'