SELECT DISTINCT 
       MISE_CD
,      COMPANY_CD
,      MISE_NAME_KJ
,      SIBU_CD
,      GYOTAI_KBN
,      CLOSE_DT
,      CLOSE_MJ

FROM (
       (
        SELECT BM01.COMPANY_CD
        ,      BM01.MISE_CD
        ,      RTRIM(CHAR(REPLACE(BM01.MISE_NAME_KJ , '  ', '  '), 40)) AS MISE_NAME_KJ
        ,      BM01.SIBU_CD
        ,      BM01.GYOTAI_KBN
        ,      BM01.CLOSE_DT
		,      '' AS CLOSE_MJ
        FROM   BM01TENM as BM01

		WHERE BM01.COMPANY_CD = /*companyCd*/'00'
		AND   BM01.ONER_CD    = /*onerCd*/'36001'
		AND   BM01.CLOSE_DT   > /*sysDate*/'20060823'

        ORDER BY BM01.MISE_CD
       )
       UNION ALL
       (
        SELECT BM01.COMPANY_CD
        ,      BM01.MISE_CD
        ,      RTRIM(CHAR(REPLACE(BM01.MISE_NAME_KJ , '  ', '  '), 40)) AS MISE_NAME_KJ
        ,      BM01.SIBU_CD
        ,      BM01.GYOTAI_KBN
        ,      BM01.CLOSE_DT
        ,      '(CLOSE)' AS CLOSE_MJ
        FROM   BM01TENM as BM01

		WHERE BM01.COMPANY_CD = /*companyCd*/'00'
		AND   BM01.ONER_CD    = /*onerCd*/'36001'
        AND   BM01.CLOSE_DT   <= /*sysDate*/'20060823'
        ORDER BY BM01.MISE_CD
       )
    ) as TBL
ORDER BY CLOSE_MJ, MISE_CD

