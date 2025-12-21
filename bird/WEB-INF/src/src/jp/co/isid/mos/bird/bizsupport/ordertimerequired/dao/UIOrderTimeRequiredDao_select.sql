SELECT BD26.MISE_CD
,      BM01.MISE_NAME_KJ
,      EIGYO_DT
,      TM_KBN
,      TEIKYO_TM_KBN
,      EAT_KYAKUSU
,      EAT_URI_SU
,      TAKE_KYAKUSU
,      TAKE_URI_SU
,      TEL_KYAKUSU
,      TEL_URI_SU
,      DRIVE_KYAKUSU
,      DRIVE_URI_SU
,      OTHER_KYAKUSU
,      OTHER_URI_SU
FROM BD26ODTM BD26
,    (SELECT COMPANY_CD, MISE_CD, MISE_NAME_KJ
      FROM BM01TENM
      WHERE COMPANY_CD = /*companyCd*/'00'
      /*IF limitFlg */
      AND   SIBU_CD IN (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD      = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
      )
	  /*END*/
      
     ) BM01
WHERE BD26.COMPANY_CD = /*companyCd*/'00'
AND   BD26.MISE_CD = /*miseCd*/'01809'
/*IF taishoKikan.equals("DAYS") */
AND   EIGYO_DT BETWEEN /*kikanFrom*/'20090903' AND /*kikanTo*/'20091020'
--ELSE
AND   EIGYO_DT = /*kikanFrom*/'20090903'
/*END*/
AND   BM01.COMPANY_CD = BD26.COMPANY_CD 
AND   BM01.MISE_CD    = BD26.MISE_CD

ORDER BY EIGYO_DT
,        TM_KBN
,        TEIKYO_TM_KBN
