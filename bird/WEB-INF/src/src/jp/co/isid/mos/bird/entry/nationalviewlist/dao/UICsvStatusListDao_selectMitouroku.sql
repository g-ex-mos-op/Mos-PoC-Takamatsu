SELECT DISTINCT 
       ONERDATA.SIBU_CD
,      ONERDATA.SIBU_NAME
,      ONERDATA.ONER_CD
,      ONERDATA.ONER_NAME_KJ
,      '' AS SEQ_NO
,      '' AS MISE_CD
,      '' AS MISE_NAME_KJ
,      '' AS NAME
,      '' AS tel
,      '' AS L_NAME_KJ
,      '' AS F_NAME_KJ
,      '' AS L_NAME_KNA
,      '' AS F_NAME_KNA
,      '' AS SEX
,      '' AS BIRTHDAY
,      '' AS AGE
,      '' AS JOB_TYPE
,      '' AS PROPOSE_KBN
,      '' AS PROPOSE_NAME
,      '' AS OPTIONAL1
,      '' AS OPTIONAL2
,      '' AS OPTIONAL3
,      '' AS OPTIONAL4
,      '' AS OPTIONAL5
,      '' AS OPTIONAL6
,      '' AS OPTIONAL7
,      '' AS OPTIONAL8
,      '' AS OPTIONAL9
,      '' AS OPTIONAL10
,      '' AS NOTE1
,      '' AS NOTE
,      '' AS LAST_USER
,      '' AS USER_NAME_KJ
,      ONERDATA.KEIYAKU_END

from (SELECT ONER.SIBU_CD
      ,      SIBU.SIBU_NAME
      ,      ONER.ONER_CD 
      ,      ONER.ONER_NAME_KJ
      ,      ONER.KEIYAKU_STA
      ,      ONER.KEIYAKU_END
      FROM BM11ONER ONER
      ,    BM10GSIB SIBU
      ,    BM01TENM MISE
/*IF limit*/
      ,    BM51SVSB as BM51 
/*END*/
      WHERE ONER.COMPANY_CD   = /*companyCd*/'00'
      AND   ONER.ONER_CD     != '39999' 
      AND   ONER.KEIYAKU_STA <= /*sysDate*/'20090818'
      AND   ONER.KEIYAKU_END >= /*sysDate*/'20090818'
      AND   SIBU.COMPANY_CD   = ONER.COMPANY_CD
/*IF limit*/
      AND   BM51.COMPANY_CD = SIBU.COMPANY_CD
      AND   BM51.SIBU_CD    = SIBU.SIBU_CD
      AND   BM51.SV_CD      = /*userId*/'99990003'
/*END*/
      AND   SIBU.SIBU_CD    = ONER.SIBU_CD
      AND   MISE.COMPANY_CD = ONER.COMPANY_CD
      AND   MISE.ONER_CD    = ONER.ONER_CD
      GROUP BY ONER.SIBU_CD
      ,        SIBU.SIBU_NAME
      ,        ONER.ONER_CD 
      ,        ONER.ONER_NAME_KJ
      ,        ONER.KEIYAKU_STA
      ,        ONER.KEIYAKU_END

     ) ONERDATA
WHERE NOT EXISTS (
    SELECT DISTINCT REGDATA.ONER_CD
    FROM (
        SELECT BM11.ONER_CD
        ,      BM11.ONER_NAME_KJ
        FROM BM10GSIB BM10
        ,   (
              SELECT MISE.SIBU_CD
              ,      MISE.ONER_CD 
              ,      ONER.ONER_NAME_KJ
              ,      ONER.KEIYAKU_STA
              ,      ONER.KEIYAKU_END
              FROM BM11ONER ONER
              ,    BM01TENM MISE
              WHERE ONER.COMPANY_CD   = /*companyCd*/'00'
              AND   ONER.ONER_CD     != '39999' 
              AND   ONER.KEIYAKU_STA <= /*sysDate*/'20090818'
              AND   ONER.KEIYAKU_END >= /*sysDate*/'20090818'
              AND   MISE.COMPANY_CD   = ONER.COMPANY_CD
              AND   MISE.ONER_CD   = ONER.ONER_CD
              GROUP BY MISE.SIBU_CD
              ,        MISE.ONER_CD 
              ,        ONER.ONER_NAME_KJ
              ,        ONER.KEIYAKU_STA
              ,        ONER.KEIYAKU_END
             ) BM11
        ,    BM01TENM BM01
        ,    BT21ENOJ BT21
        ,    BR01USER BR01
        ,    BT20ENON BT20

        WHERE BM10.COMPANY_CD   = /*companyCd*/'00'
        AND   BT20.ENTRY_CD     = /*entryCd*/'15'
        AND   BT20.ENTRY_YEAR   = /*entryYear*/'2009'
        AND   BT20.ENTRY_KAI    = /*entryKai*/'001'
        AND   BT21.ENTRY_CD     = BT20.ENTRY_CD
        AND   BT21.ENTRY_YEAR   = BT20.ENTRY_YEAR
        AND   BT21.ENTRY_KAI    = BT20.ENTRY_KAI
        AND   BM01.COMPANY_CD   = BM10.COMPANY_CD 
        AND   BT21.COMPANY_CD   = BM01.COMPANY_CD 
        AND   BM10.SIBU_CD      = BM11.SIBU_CD
        AND   BM01.SIBU_CD      = BM11.SIBU_CD
        AND   BT21.ONER_CD      = BM11.ONER_CD
        AND   BT21.ATESAKI_KBN  = '00'
    ) REGDATA

    WHERE REGDATA.ONER_CD   = ONERDATA.ONER_CD 
)
ORDER BY ONER_CD
,        SIBU_CD
