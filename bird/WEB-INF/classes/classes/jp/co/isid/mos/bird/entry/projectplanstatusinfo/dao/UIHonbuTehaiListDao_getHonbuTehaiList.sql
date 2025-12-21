SELECT BM11.SIBU_CD
,      BM10.SIBU_NAME
,      BM11.ONER_CD
,      BM11.ONER_NAME_KJ
,      BM11.KEIYAKU_STA
,      BM11.KEIYAKU_END
,      sum(case when BT46.SHUKUHAKU_FLG = '1' then 1 else 0 end) as SINGLE_CNT
,      sum(case when BT46.SHUKUHAKU_FLG = '2' then 1 else 0 end) as TWIN_CNT
FROM BT46KENS BT46
,    BT21ENOJ BT21
,    (SELECT MISE.SIBU_CD
      ,      MISE.ONER_CD 
      ,      ONER.ONER_NAME_KJ
      ,      ONER.KEIYAKU_STA
      ,      ONER.KEIYAKU_END
      FROM BM11ONER ONER
      ,    BM01TENM MISE
      WHERE ONER.COMPANY_CD   = /*companyCd*/'00'
      AND   ONER.ONER_CD     != '39999' 
      AND    MISE.CLOSE_DT     >= (select DISTINCT FROM_DT from BR18ENTD where ENTRY_CD = /*entryCd*/'25' and ENTRY_YEAR = /*entryYear*/'2005'  AND ENTRY_KAI = /*entryKai*/'001' AND USERTYPE_CD = '01' AND DAY_KBN = '04')
      AND   ONER.KEIYAKU_STA <= /*sysDate*/'20061213'
      AND   ONER.KEIYAKU_END >= /*sysDate*/'20061213'
      AND   MISE.COMPANY_CD   = ONER.COMPANY_CD
      AND   MISE.ONER_CD   = ONER.ONER_CD
      GROUP BY MISE.SIBU_CD
      ,        MISE.ONER_CD 
      ,        ONER.ONER_NAME_KJ
      ,        ONER.KEIYAKU_STA
      ,        ONER.KEIYAKU_END
     ) BM11
,    BM10GSIB BM10
,    BM01TENM BM01
/*IF taishouJokenChoice == "2"*/     
,    (
      SELECT DISTINCT ONER_CD 
      FROM   BM01TENM 
      WHERE  MISE_CD in (SELECT MISE_CD 
                         FROM   BM50TANM 
                         WHERE COMPANY_CD = /*companyCd*/'00'
                         AND   SV_CD = /*svCd*/'00000085'
                        )
      AND    COMPANY_CD = /*companyCd*/'00'
     ) BM50
/*END*/

WHERE BM01.COMPANY_CD   = /*companyCd*/'00'
/*IF taishouJokenChoice == "1"*/
AND   BM11.SIBU_CD = /*sibuListChoice*/'011'
/*END*/ 
/*IF taishouJokenChoice == "2"*/
AND   BM50.ONER_CD = BM11.ONER_CD
/*END*/
AND   BT46.ENTRY_CD     = /*entryCd*/'25'
AND   BT46.ENTRY_YEAR   = /*entryYear*/'2006'
AND   BT46.ENTRY_KAI    = /*entryKai*/'002'
AND   BT21.ENTRY_CD     = BT46.ENTRY_CD 
AND   BT21.ENTRY_YEAR   = BT46.ENTRY_YEAR
AND   BT21.ENTRY_KAI    = BT46.ENTRY_KAI
AND   BT46.COMPANY_CD   = BM01.COMPANY_CD 
AND   BT21.COMPANY_CD   = BT46.COMPANY_CD 
AND   BT46.ONER_CD      = BM11.ONER_CD
AND   BT21.ONER_CD      = BT46.ONER_CD
AND   BT21.ATESAKI_KBN  = '00'	  
AND   BM10.SIBU_CD      = BM11.SIBU_CD
AND   BM01.SIBU_CD      = BM10.SIBU_CD

/*IF limit*/
AND   BM10.SIBU_CD IN (select distinct
			                   sibu_cd
				        from   bm51svsb
				        where  company_cd = /*companyCd*/'00'
					    and    sv_cd      = /*userId*/'00000085')
/*END*/

AND   BT46.MISE_CD      = BM01.MISE_CD

GROUP BY BM11.SIBU_CD
,      BM10.SIBU_NAME
,      BM11.ONER_CD
,      BM11.ONER_NAME_KJ
,      BM11.KEIYAKU_STA
,      BM11.KEIYAKU_END

ORDER BY SIBU_CD
,        ONER_CD