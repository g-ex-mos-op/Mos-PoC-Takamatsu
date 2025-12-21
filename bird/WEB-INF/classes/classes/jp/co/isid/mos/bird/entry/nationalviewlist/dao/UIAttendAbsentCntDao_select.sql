SELECT ONERTOTAL.CNT AS ONER_TOTAL_CNT
,      CASE WHEN ONERCNT.ATTEND IS NULL THEN 0 ELSE ONERCNT.ATTEND END AS ONER_ATTEND_CNT
,      CASE WHEN ONERCNT.ABSENT IS NULL THEN 0 ELSE ONERCNT.ABSENT END AS ONER_ABSENT_CNT
,      CASE WHEN PERSONCNT.ATTEND IS NULL THEN 0 ELSE PERSONCNT.ATTEND END AS ATTEND_CNT
,      CASE WHEN PERSONCNT.ABSENT IS NULL THEN 0 ELSE PERSONCNT.ABSENT END AS ABSENT_CNT

FROM (SELECT COUNT(DISTINCT ONER.ONER_CD) AS CNT
      FROM BM11ONER ONER
      ,    BM01TENM MISE
      WHERE ONER.COMPANY_CD   = /*companyCd*/'00'
      AND   ONER.ONER_CD     != '39999' 
      AND   ONER.KEIYAKU_STA <= /*sysDate*/'20061213'
      AND   ONER.KEIYAKU_END >= /*sysDate*/'20061213'
      AND   MISE.COMPANY_CD   = ONER.COMPANY_CD
      AND   MISE.ONER_CD   = ONER.ONER_CD
      AND   MISE.CLOSE_DT  >= /*fromDt*/'20061213'
) ONERTOTAL
,    BR17ENTL BR17
LEFT JOIN  (
	SELECT ENTRY_CD
    ,      ENTRY_YEAR
    ,      ENTRY_KAI
    ,      SUM(ATTEND) AS ATTEND
    ,      SUM(CASE WHEN ATTEND > 0 THEN 0 ELSE ABSENT END) AS ABSENT
    FROM (SELECT BT20.ENTRY_CD
          ,      BT20.ENTRY_YEAR
          ,      BT20.ENTRY_KAI
          ,      BT20.COMPANY_CD
          ,      BT20.ONER_CD
          ,      MAX(CASE WHEN BT20.ENTRY_FLG = '1' THEN 1 ELSE 0 END) AS ATTEND
          ,      MAX(CASE WHEN BT20.ENTRY_FLG = '0' THEN 1 ELSE 0 END) AS ABSENT
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
                AND   MISE.CLOSE_DT  >= /*fromDt*/'20061213'
                GROUP BY MISE.SIBU_CD
                ,        MISE.ONER_CD 
                ,        ONER.ONER_NAME_KJ
                ,        ONER.KEIYAKU_STA
                ,        ONER.KEIYAKU_END
               ) BM11
          ,    BT20ENON BT20
          WHERE BT20.COMPANY_CD = /*companyCd*/'00'
          AND   BT20.ONER_CD    = BM11.ONER_CD
          GROUP BY BT20.ENTRY_CD
          ,        BT20.ENTRY_YEAR
          ,        BT20.ENTRY_KAI
          ,        BT20.COMPANY_CD
          ,        BT20.ONER_CD
          ) KENS
    GROUP BY ENTRY_CD
    ,     ENTRY_YEAR
    ,     ENTRY_KAI
) ONERCNT
ON (
          ONERCNT.ENTRY_CD   = BR17.ENTRY_CD 
    AND   ONERCNT.ENTRY_YEAR = BR17.ENTRY_YEAR 
    AND   ONERCNT.ENTRY_KAI  = BR17.ENTRY_KAI
)
LEFT JOIN (
    SELECT ENTRY_CD
    ,      ENTRY_YEAR
    ,      ENTRY_KAI
    ,      SUM(ATTENDCNT) AS ATTEND
    ,      SUM(ABSENTCNT) AS ABSENT
    FROM (
        SELECT BT20.ENTRY_CD
        ,      BT20.ENTRY_YEAR
        ,      BT20.ENTRY_KAI
        ,      ONER.SIBU_CD
        ,      ONER.ONER_CD
        ,      SUM(case when BT20.ENTRY_FLG = '1' then 1 else 0 end) as ATTENDCNT
        ,      SUM(case when BT20.ENTRY_FLG = '0' then 1 else 0 end) as ABSENTCNT
        FROM BT20ENON BT20
        ,    BT48NATI BT48
        ,    (SELECT MISE.SIBU_CD
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
              GROUP BY MISE.SIBU_CD
              ,        MISE.ONER_CD 
             ) ONER
        ,    BM01TENM BM01
    
        WHERE BM01.COMPANY_CD   = /*companyCd*/'00'
        AND   BT20.ENTRY_CD     = /*entryCd*/'15'
        AND   BT20.ENTRY_YEAR   = /*entryYear*/'2006'
        AND   BT20.ENTRY_KAI    = /*entryKai*/'003'
        AND   BT48.ENTRY_CD     = BT20.ENTRY_CD
        AND   BT48.ENTRY_YEAR   = BT20.ENTRY_YEAR
        AND   BT48.ENTRY_KAI    = BT20.ENTRY_KAI
        AND   BT20.COMPANY_CD   = BM01.COMPANY_CD 
        AND   BT48.COMPANY_CD   = BT20.COMPANY_CD 
        AND   BT20.ONER_CD      = ONER.ONER_CD
        AND   BT48.ONER_CD      = BT20.ONER_CD
        AND   BM01.SIBU_CD      = ONER.SIBU_CD
        AND   BT48.MISE_CD      = BM01.MISE_CD
        GROUP BY BT20.ENTRY_CD
        ,        BT20.ENTRY_YEAR
        ,        BT20.ENTRY_KAI
        ,        ONER.SIBU_CD
        ,        ONER.ONER_CD
    ) SUBCNT
    GROUP BY ENTRY_CD
    ,        ENTRY_YEAR
    ,        ENTRY_KAI
) PERSONCNT
ON (
      PERSONCNT.ENTRY_CD   = ONERCNT.ENTRY_CD 
AND   PERSONCNT.ENTRY_YEAR = ONERCNT.ENTRY_YEAR 
AND   PERSONCNT.ENTRY_KAI  = ONERCNT.ENTRY_KAI
)

WHERE SAKUJO_FLG <> '1'
AND   BR17.ENTRY_CD      = /*entryCd*/'15'
AND   BR17.ENTRY_YEAR    = /*entryYear*/'2006'
AND   BR17.ENTRY_KAI     = /*entryKai*/'003'

  
