SELECT DISTINCT BM11.SIBU_CD
,      BM10.SIBU_NAME
,      BM11.ONER_CD
,      BM11.ONER_NAME_KJ
,      ENTRYDATA.ENTRY_FLG
,      DECIMAL((CASE WHEN ENTRYDATA.ATTENDCNT IS NULL THEN 0 ELSE ENTRYDATA.ATTENDCNT END), 6,0) as ATTEND_CNT
FROM BM10GSIB BM10
,    (SELECT MISE.SIBU_CD
      ,      MISE.ONER_CD 
      ,      ONER.ONER_NAME_KJ
      FROM BM11ONER ONER
      ,    BM01TENM MISE
      WHERE ONER.COMPANY_CD   = /*companyCd*/'00'
      AND   ONER.ONER_CD     != '39999' 
      AND   ONER.KEIYAKU_STA <= /*sysDate*/'20061213'
      AND   ONER.KEIYAKU_END >= /*sysDate*/'20061213'
      AND   MISE.COMPANY_CD   = ONER.COMPANY_CD
      AND   MISE.ONER_CD   = ONER.ONER_CD
      AND   MISE.CLOSE_DT  >= /*fromDt*/'20061213'
      GROUP BY MISE.SIBU_CD
      ,        MISE.ONER_CD 
      ,        ONER.ONER_NAME_KJ
     ) BM11
LEFT JOIN (
    SELECT ONER.SIBU_CD
    ,      ONER.ONER_CD
    ,      CHAR(MAX(case when BT20.ENTRY_FLG = '1' then 1 else 0 end)) as ENTRY_FLG
    ,      SUM(case when BT48.SEQ_NO IS NOT NULL then 1 else 0 end) as ATTENDCNT
    FROM BT20ENON BT20
    ,    (SELECT MISE.COMPANY_CD
          ,      MISE.SIBU_CD
          ,      MISE.ONER_CD 
          FROM BM11ONER ONER
          ,    BM01TENM MISE
          WHERE ONER.COMPANY_CD   = /*companyCd*/'00'
          AND   ONER.ONER_CD     != '39999' 
          AND   ONER.KEIYAKU_STA <= /*sysDate*/'20061213'
          AND   ONER.KEIYAKU_END >= /*sysDate*/'20061213'
          AND   MISE.COMPANY_CD   = ONER.COMPANY_CD
          AND   MISE.ONER_CD   = ONER.ONER_CD
          AND   MISE.CLOSE_DT  >= /*fromDt*/'20061213'
          GROUP BY MISE.COMPANY_CD
          ,        MISE.SIBU_CD
          ,        MISE.ONER_CD 
         ) ONER
    LEFT JOIN   (
         SELECT DISTINCT 
                MISE.COMPANY_CD
         ,      MISE.SIBU_CD 
         ,      SUB48.ONER_CD
         ,      SUB48.SEQ_NO
         FROM BM01TENM MISE
         ,    BT48NATI SUB48
         WHERE SUB48.COMPANY_CD   = MISE.COMPANY_CD
         AND   SUB48.MISE_CD = MISE.MISE_CD
         AND   ENTRY_CD     = /*entryCd*/'15'
         AND   ENTRY_YEAR   = /*entryYear*/'2007'
         AND   ENTRY_KAI    = /*entryKai*/'011'
         
    ) BT48
         ON (
          BT48.COMPANY_CD   = ONER.COMPANY_CD
    AND   BT48.ONER_CD      = ONER.ONER_CD
    AND   BT48.SIBU_CD      = ONER.SIBU_CD
    )

    WHERE BT20.COMPANY_CD   = /*companyCd*/'00'
    AND   BT20.ENTRY_CD     = /*entryCd*/'15'
    AND   BT20.ENTRY_YEAR   = /*entryYear*/'2006'
    AND   BT20.ENTRY_KAI    = /*entryKai*/'003'
    AND   BT20.ONER_CD      = ONER.ONER_CD
    GROUP BY ONER.SIBU_CD
    ,        ONER.ONER_CD
) ENTRYDATA
ON (ENTRYDATA.SIBU_CD = BM11.SIBU_CD AND ENTRYDATA.ONER_CD = BM11.ONER_CD)
        
/*IF limit*/
,    BM51SVSB as BM51 
/*END*/
/*IF taishoJoken.equals("2")*/     
,    (
     SELECT DISTINCT ONER_CD 
     FROM   BM01TENM 
     WHERE  MISE_CD in (SELECT MISE_CD 
                        FROM   BM50TANM 
                        WHERE COMPANY_CD = /*companyCd*/'00'
                        AND   SV_CD = /*svCd*/'00000085'
                        AND   CLOSE_DT  >= /*fromDt*/'20061213'
                       )
     AND    COMPANY_CD = /*companyCd*/'00'
     ) BM50
/*END*/

WHERE BM10.COMPANY_CD   =  /*companyCd*/'00'
AND   BM10.SIBU_CD      = BM11.SIBU_CD 
/*IF limit*/
AND   BM51.COMPANY_CD = BM10.COMPANY_CD 
AND   BM51.SIBU_CD    = BM10.SIBU_CD
AND   BM51.SV_CD = /*userId*/'99990003'
/*END*/
/*IF taishoJoken.equals("1") */
AND   BM10.SIBU_CD = /*sibuCd*/'011'
/*END*/
/*IF taishoJoken.equals("2") */     
AND   BM50.ONER_CD = BM11.ONER_CD
/*END*/

order by SIBU_CD
       , ONER_CD
