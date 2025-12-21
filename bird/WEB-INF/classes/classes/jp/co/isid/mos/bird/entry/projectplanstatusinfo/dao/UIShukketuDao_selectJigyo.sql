SELECT ONERTOTAL.CNT AS ONER_TOTAL_CNT
,      ONERCNT.ATTEND AS ONER_ATTEND_CNT
,      ONERCNT.ABSENT AS ONER_ABSENT_CNT
,      PERSONCNT.ATTEND AS ATTEND_CNT
,      PERSONCNT.ABSENT AS ABSENT_CNT

FROM BR17ENTL BR17
,    (SELECT COUNT(DISTINCT BM11.ONER_CD) AS CNT
     FROM     BM11ONER BM11
     ,        BM01TENM BM01
     WHERE BM11.COMPANY_CD = /*companyCd*/'00'
     AND   BM11.COMPANY_CD = BM01.COMPANY_CD
     AND   BM11.ONER_CD != '39999' 
     AND   BM11.ONER_CD = BM01.ONER_CD
     AND   BM11.KEIYAKU_STA <= /*sysDate*/'20061213'
     AND   BM11.KEIYAKU_END >= /*sysDate*/'20061213'
) ONERTOTAL
,  (
	SELECT ENTRY_CD
    ,      ENTRY_YEAR
    ,      ENTRY_KAI
    ,      SUM(ATTEND) AS ATTEND
    ,      SUM(CASE WHEN ATTEND > 0 THEN 0 ELSE ABSENT END) AS ABSENT
    FROM (SELECT BT46.ENTRY_CD
          ,      BT46.ENTRY_YEAR
          ,      BT46.ENTRY_KAI
          ,      BT46.COMPANY_CD
          ,      BT46.ONER_CD
          ,      MAX(CASE WHEN BT46.JIGYO_FLG = '0' THEN 1 ELSE 0 END) AS ATTEND
          ,      MAX(CASE WHEN BT46.JIGYO_FLG = '1' THEN 1 ELSE 0 END) AS ABSENT
          FROM (SELECT MISE.SIBU_CD
                ,      MISE.ONER_CD 
                ,      ONER.ONER_NAME_KJ
                ,      ONER.KEIYAKU_STA
                ,      ONER.KEIYAKU_END
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
               ) BM11
          ,    BM01TENM MISE
          ,    BT46KENS BT46
          ,    BT21ENOJ BT21
          WHERE MISE.COMPANY_CD = /*companyCd*/'00'
          AND   BT46.COMPANY_CD = MISE.COMPANY_CD
          AND   BT21.ENTRY_CD     = BT46.ENTRY_CD 
          AND   BT21.ENTRY_YEAR   = BT46.ENTRY_YEAR
          AND   BT21.ENTRY_KAI    = BT46.ENTRY_KAI
          AND   BT21.COMPANY_CD   = BT46.COMPANY_CD 
          AND   MISE.SIBU_CD    = BM11.SIBU_CD
          AND   BT46.ONER_CD    = BM11.ONER_CD
          AND   BT21.ONER_CD      = BT46.ONER_CD
          AND   BT21.ATESAKI_KBN  = '00'
          AND   BT46.MISE_CD    = MISE.MISE_CD
          GROUP BY BT46.ENTRY_CD
          ,        BT46.ENTRY_YEAR
          ,        BT46.ENTRY_KAI
          ,        BT46.COMPANY_CD
          ,        BT46.ONER_CD
          ) KENS
    GROUP BY ENTRY_CD
    ,     ENTRY_YEAR
    ,     ENTRY_KAI
) ONERCNT
, (
    SELECT BT46.ENTRY_CD
    ,      BT46.ENTRY_YEAR
    ,      BT46.ENTRY_KAI
    ,      SUM(CASE WHEN BT46.JIGYO_FLG = '0' THEN 1 ELSE 0 END) AS ATTEND
    ,      SUM(CASE WHEN BT46.JIGYO_FLG = '1' THEN 1 ELSE 0 END) AS ABSENT
    FROM (SELECT MISE.SIBU_CD
          ,      MISE.ONER_CD 
          ,      ONER.ONER_NAME_KJ
          ,      ONER.KEIYAKU_STA
          ,      ONER.KEIYAKU_END
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
         ) BM11
    ,    BM01TENM MISE
    ,    BT46KENS BT46
    ,    BT21ENOJ BT21
    WHERE MISE.COMPANY_CD   = /*companyCd*/'00'
    AND   BT46.COMPANY_CD   = MISE.COMPANY_CD
    AND   BT21.ENTRY_CD     = BT46.ENTRY_CD 
    AND   BT21.ENTRY_YEAR   = BT46.ENTRY_YEAR
    AND   BT21.ENTRY_KAI    = BT46.ENTRY_KAI
    AND   BT21.COMPANY_CD   = BT46.COMPANY_CD 
    AND   MISE.SIBU_CD      = BM11.SIBU_CD
    AND   BT46.ONER_CD      = BM11.ONER_CD
    AND   BT21.ONER_CD      = BT46.ONER_CD
    AND   BT21.ATESAKI_KBN  = '00'
    AND   BT46.MISE_CD      = MISE.MISE_CD
    GROUP BY BT46.ENTRY_CD
    ,        BT46.ENTRY_YEAR
    ,        BT46.ENTRY_KAI
) PERSONCNT

WHERE SAKUJO_FLG <> '1'
AND   BR17.ENTRY_CD = /*entryCd*/'25'
AND   BR17.ENTRY_YEAR = /*entryYear*/'2006'
AND   BR17.ENTRY_KAI = /*entryKai*/'012'
AND   ONERCNT.ENTRY_CD = BR17.ENTRY_CD 
AND   ONERCNT.ENTRY_YEAR = BR17.ENTRY_YEAR 
AND   ONERCNT.ENTRY_KAI = BR17.ENTRY_KAI
AND   PERSONCNT.ENTRY_CD = BR17.ENTRY_CD 
AND   PERSONCNT.ENTRY_YEAR = BR17.ENTRY_YEAR 
AND   PERSONCNT.ENTRY_KAI = BR17.ENTRY_KAI
  
