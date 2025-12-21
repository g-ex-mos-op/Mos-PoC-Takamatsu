SELECT BS04.NENDO
,      BS04.KAI
,      BM01.COMPANY_CD
,      BC02.COMPANY_NAME
,      BR01.USER_NAME_KJ
,      BM01.SV_CD
,      BR01.USER_NAME_KJ
,      BM01.ONER_CD
,      BM11.ONER_NAME_KJ
,      BS04.MISE_CD
,      BM01.MISE_NAME_KJ
,      0 RANK
,      BS04.SPC_FLG
,      BS04.ZEN_RANK
,      BS04.HYOUKA_DATA
 FROM BS04MSPS BS04     
 ,    ( 
  SELECT MISEMST.COMPANY_CD
  ,      BM50.SV_CD 
  ,      MISEMST.ONER_CD 
  ,      MISEMST.MISE_CD 
  ,      MISEMST.MISE_NAME_KJ 
  FROM BM01TENM MISEMST
     ,    BM50TANM BM50
/*IF searchType == 'MISE' */
     ,    (
         SELECT ONER_CD FROM BM01TENM 
         WHERE COMPANY_CD = /*companyCd*/'00'
         AND   MISE_CD = /*miseCd*/'01776'
         GROUP BY ONER_CD
  ) KOTEN
/*END*/
  WHERE MISEMST.COMPANY_CD = /*companyCd*/'00'
/*IF searchType == 'SV' */
     AND   BM50.SV_CD = /*svCd*/'00000921' 
/*END*/
/*IF searchType == 'ONER' */
     AND MISEMST.ONER_CD = /*onerCd*/'36478' 
/*END*/
/*IF limitFlg == true && userTypeCd == '01' */
  AND   MISEMST.SIBU_CD in (select distinct sibu_Cd 
  						    from bm51svsb 
  						    where company_cd = /*companyCd*/'00' 
  						    and   SV_CD = /*userId*/'00000921')
/*END*/
/*IF searchType == 'MISE' */
     AND MISEMST.ONER_CD = KOTEN.ONER_CD
/*END*/
  AND   BM50.COMPANY_CD = MISEMST.COMPANY_CD 
  AND   BM50.MISE_CD = MISEMST.MISE_CD 
  GROUP BY MISEMST.COMPANY_CD
  ,        BM50.SV_CD 
  ,        MISEMST.ONER_CD 
  ,        MISEMST.MISE_CD 
  ,        MISEMST.MISE_NAME_KJ 
 ) BM01 
 , BC02COMP BC02
 , BR01USER BR01
 , BM11ONER BM11
 WHERE BS04.NENDO = /*nendo*/'2006'     
 AND   BS04.KAI = /*kai*/'01'     
 AND   BS04.COMPANY_CD = /*companyCd*/'00' 
 AND   BS04.HYOUKA_KBN = '3'
 AND   BS04.LIMIT_U = 100
    AND   BM01.COMPANY_CD = BC02.R_COMPANY_CD
    AND   BS04.COMPANY_CD = BM01.COMPANY_CD
    AND   BM11.COMPANY_CD = BS04.COMPANY_CD
 AND   BR01.USER_ID = BM01.SV_CD     
 AND   BM11.ONER_CD = BS04.ONER_CD     
 AND   BS04.MISE_CD = BM01.MISE_CD     
ORDER BY
         BS04.SPC_FLG
,        BS04.HYOUKA_DATA DESC