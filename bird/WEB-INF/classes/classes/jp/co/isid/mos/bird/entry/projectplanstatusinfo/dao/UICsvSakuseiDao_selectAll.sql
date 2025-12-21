SELECT DISTINCT 
       ONERDATA.SIBU_CD
,      ONERDATA.SIBU_NAME
,      ONERDATA.ONER_CD
,      ONERDATA.ONER_NAME_KJ
,      REGDATA.TAB_NO
,      REGDATA.MISE_CD
,      REGDATA.MISE_NAME_KJ
,      REGDATA.NAME
,      REGDATA.TEL
,      REGDATA.L_NAME_KJ
,      REGDATA.F_NAME_KJ
,      REGDATA.L_NAME_kna
,      REGDATA.F_NAME_kna
,      CASE WHEN REGDATA.SEX = '0' THEN 'íj' WHEN REGDATA.SEX = '1' THEN 'èó' ELSE '' end AS SEX
,      REGDATA.AGE
/*IF taishouListChoice == "0" || taishouListChoice == "4"*/
,      CASE WHEN REGDATA.JIGYO_FLG = '0' THEN 'Åõ' WHEN REGDATA.JIGYO_FLG = '1' THEN 'Å~' ELSE '' END AS JIGYO_FLG
/*END*/
		
/*IF taishouListChoice == "1" || taishouListChoice == "4"*/
,      CASE WHEN REGDATA.KONSHIN_FLG = '0' THEN 'Åõ' WHEN REGDATA.KONSHIN_FLG = '1' THEN 'Å~' ELSE '' END AS KONSHIN_FLG
/*END*/
		
/*IF taishouListChoice == "2" || taishouListChoice == "4"*/
,      CASE WHEN REGDATA.KYOEI_FLG = '0' THEN 'Åõ' WHEN REGDATA.KYOEI_FLG = '1' THEN 'Å~' ELSE '' END AS KYOEI_FLG
/*END*/
,      CASE WHEN REGDATA.SHUKUHAKU_FLG IS NULL THEN '' WHEN REGDATA.SHUKUHAKU_FLG = '0' THEN 'ñ≥' WHEN REGDATA.SHUKUHAKU_FLG = '1' THEN 'ÉVÉìÉOÉã' ELSE 'ÉcÉCÉì' END AS SHUKUHAKU_FLG
,      REGDATA.NOTE
,      REGDATA.ABSENCE_REASON
,      CASE WHEN REGDATA.TAB_NO IS NULL THEN '' 
            WHEN REGDATA.ININ_MISE_CD is null THEN 'Ç»Çµ' 
            ELSE 'Ç†ÇË' 
       END AS ININ_MISE_CD
,      REGDATA.LAST_USER
,      REGDATA.USER_NAME_KJ
,      ONERDATA.KEIYAKU_END
FROM (SELECT ONER.SIBU_CD
      ,      SIBU.SIBU_NAME
      ,      ONER.ONER_CD 
      ,      ONER.ONER_NAME_KJ
      ,      MISE.MISE_CD
      ,      MISE.MISE_NAME_KJ
      ,      ONER.KEIYAKU_STA
      ,      ONER.KEIYAKU_END
      FROM BM11ONER ONER
      ,    BM10GSIB SIBU
      ,    BM01TENM MISE
/*IF taishouJokenChoice == "2" && taishouListChoice != "4"*/     
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
      WHERE ONER.COMPANY_CD   = '00'
      AND   ONER.ONER_CD     != '39999' 
      AND   ONER.KEIYAKU_STA <= /*sysDate*/'20061215'
      AND   ONER.KEIYAKU_END >= /*sysDate*/'20061215'
      AND   MISE.COMPANY_CD   = ONER.COMPANY_CD
      AND   SIBU.COMPANY_CD   = MISE.COMPANY_CD
      AND   MISE.ONER_CD      = ONER.ONER_CD      
      AND   SIBU.SIBU_CD      = ONER.SIBU_CD
/*IF taishouJokenChoice == "1" && taishouListChoice != "4"*/
AND   SIBU.SIBU_CD = /*sibuListChoice*/'011'
/*END*/ 
      
/*IF limit*/
AND   SIBU.SIBU_CD IN (select distinct
			                   sibu_cd
				        from   bm51svsb
				        where  company_cd = /*companyCd*/'00'
					    and    sv_cd      = /*userId*/'00000085')
/*END*/

/*IF taishouJokenChoice == "2" && taishouListChoice != "4"*/
AND   BM50.ONER_CD = MISE.ONER_CD
/*END*/
      GROUP BY ONER.SIBU_CD
      ,        SIBU.SIBU_NAME
      ,        ONER.ONER_CD 
      ,        ONER.ONER_NAME_KJ
      ,        MISE.MISE_CD
      ,        MISE.MISE_NAME_KJ
      ,        ONER.KEIYAKU_STA
      ,        ONER.KEIYAKU_END

     ) ONERDATA
LEFT JOIN (
    SELECT BM11.SIBU_CD
    ,      BM11.ONER_CD
    ,      BT46.TAB_NO
    ,      BT46.MISE_CD
    ,      BM01.MISE_NAME_KJ
    ,      BT21.NAME
    ,      BT21.TEL
    ,      BT46.L_NAME_KJ
    ,      BT46.F_NAME_KJ
    ,      BT46.L_NAME_kna
    ,      BT46.F_NAME_kna
    ,      BT46.SEX AS SEX
    ,      BT46.AGE
    ,      BT46.JIGYO_FLG AS JIGYO_FLG 
    ,      BT46.KONSHIN_FLG AS KONSHIN_FLG
    ,      BT46.KYOEI_FLG AS KYOEI_FLG
    ,      BT46.SHUKUHAKU_FLG AS SHUKUHAKU_FLG
    ,      BT46.NOTE
    ,      BT46.ABSENCE_REASON
    ,      bt47.ININ_MISE_CD AS ININ_MISE_CD
    ,      BT46.LAST_USER
    ,      br01.USER_NAME_KJ
    FROM BT46KENS BT46
    LEFT JOIN BT47ININ BT47
    ON (    BT46.ENTRY_CD   = BT47.ENTRY_CD 
        AND BT46.ENTRY_YEAR = BT47.ENTRY_YEAR
        AND BT46.ENTRY_KAI  = BT47.ENTRY_KAI
        AND BT46.COMPANY_CD = BT47.COMPANY_CD 
        AND BT46.ONER_CD    = BT47.ONER_CD)
    ,    BM10GSIB BM10
    ,   (
      SELECT MISE.SIBU_CD
      ,      MISE.ONER_CD 
      ,      ONER.ONER_NAME_KJ
      ,      ONER.KEIYAKU_STA
      ,      ONER.KEIYAKU_END
      ,      MISE.MISE_CD 
      ,      MISE.MISE_NAME_KJ 
      FROM BM11ONER ONER
      ,    BM01TENM MISE
      WHERE ONER.COMPANY_CD   = /*companyCd*/'00'
      AND   ONER.ONER_CD     != '39999' 
      AND   ONER.KEIYAKU_STA <= /*sysDate*/'20061213'
      AND   ONER.KEIYAKU_END >= /*sysDate*/'20061213'
      AND   MISE.COMPANY_CD   = ONER.COMPANY_CD
      AND   MISE.ONER_CD   = ONER.ONER_CD
      GROUP BY MISE.SIBU_CD
      ,        MISE.ONER_CD 
      ,        ONER.ONER_NAME_KJ
      ,        ONER.KEIYAKU_STA
      ,        ONER.KEIYAKU_END
      ,        MISE.MISE_CD 
      ,        MISE.MISE_NAME_KJ 
     ) BM11
     ,    BM01TENM BM01
     ,    BT21ENOJ BT21
     ,    BR01USER BR01

WHERE BM10.COMPANY_CD   = /*companyCd*/'00'
AND   BT46.ENTRY_CD     = /*entryCd*/'25'
AND   BT46.ENTRY_YEAR   = /*entryYear*/'2006'
AND   BT46.ENTRY_KAI    = /*entryKai*/'002'
AND   BT46.ENTRY_CD     = BT21.ENTRY_CD 
AND   BT46.ENTRY_YEAR   = BT21.ENTRY_YEAR
AND   BT46.ENTRY_KAI    = BT21.ENTRY_KAI
AND   BM01.COMPANY_CD   = BM10.COMPANY_CD 
AND   BT46.COMPANY_CD   = BM01.COMPANY_CD 
AND   BT21.COMPANY_CD   = BT46.COMPANY_CD 
AND   BM10.SIBU_CD      = BM11.SIBU_CD
AND   BM01.SIBU_CD      = BM11.SIBU_CD
AND   BT46.ONER_CD      = BM11.ONER_CD
AND   BT46.MISE_CD      = BM01.MISE_CD
AND   BT46.ONER_CD      = BT21.ONER_CD
AND   BT21.ATESAKI_KBN  = '00'
AND   BR01.USER_ID      = BT46.LAST_USER
) REGDATA
ON (    REGDATA.ONER_CD = ONERDATA.ONER_CD 
    AND REGDATA.MISE_CD = ONERDATA.MISE_CD)
ORDER BY SIBU_CD
,        ONER_CD
,        TAB_NO
