SELECT
     BC18_SRC.MISE_LEASE_SHU   AS MISE_LEASE_SHU
    ,BC18_SRC.MISE_LEASE_NAME  AS MISE_LEASE_NAME
    ,(CASE WHEN BC18_MAX.MAX_LEASE_SHU_COUNT IS NULL THEN 1 ELSE BC18_MAX.MAX_LEASE_SHU_COUNT END) AS MAX_LEASE_SHU_COUNT
FROM
    BC18CHIT BC18_SRC
LEFT JOIN
    (
        SELECT
            BC18_COUNT.MISE_LEASE_SHU         AS MISE_LEASE_SHU
            ,MAX(BC18_COUNT.LEASE_SHU_COUNT)   AS MAX_LEASE_SHU_COUNT
        FROM
            (
                SELECT
                   MM24_1.MISE_CD                AS MISE_CD
                  ,MM24_1.MISE_LEASE_SHU         AS MISE_LEASE_SHU
                  ,COUNT(MM24_1.MISE_LEASE_SHU)  AS LEASE_SHU_COUNT
                FROM
                  MM24CHIT MM24_1,
                  (
                    SELECT
                      MM24.MISE_CD    AS MISE_CD
                      ,MM24.MISE_LEASE_SHU  AS MISE_LEASE_SHU
                      ,MAX(MM24.MISE_M_DATE)  AS MISE_M_DATE
                    FROM
                      MM24CHIT MM24
                    INNER JOIN
                      BM73MMLT BM73
                        ON(MM24.COMPANY_CD = BM73.COMPANY_CD AND MM24.MISE_CD = BM73.MISE_CD AND MM24.MISE_M_DATE = BM73.MISE_M_DATE)
                    INNER JOIN
                      BM01TENM BM01
                        ON(BM73.COMPANY_CD = BM01.COMPANY_CD AND BM73.MISE_CD = BM01.MISE_CD)
                    WHERE
                      BM73.COMPANY_CD='00'
                       /*IF inClose == null */
                      AND EXISTS (SELECT 1 FROM BR33CDAY BR33 WHERE BR33.DAY_KBN = '02' AND BM73.CLOSE_DT >= BR33.CNT_DATE)
                      /*END*/
                      /*IF shukeKubu.equals("AREA_DAI_CD")*/
                      AND EXISTS (SELECT 1 FROM BM10GSIB BM10 WHERE BM01.AREA_DAI= BM10.SIBU_CD AND BM10.AREA_DAI_FLG = '1')
                      /*END*/
                      /*IF shukeKubu.equals("SV_CD")*/
                      AND EXISTS (SELECT 1 FROM BM50TANM AS BM50 WHERE BM50.COMPANY_CD = '00'
                        AND BM50.SV_CD = /*svCd*/'00000921' AND BM50.MISE_CD = BM01.MISE_CD)
                      /*END*/
                      /*IF siBuCd != null*/
                      AND BM73.SIBU_CD = /*siBuCd*/'00'
                      /*END*/
                    GROUP BY
                       MM24.COMPANY_CD
                      ,MM24.MISE_CD
                      ,MM24.MISE_LEASE_SHU
                  ) MM24_2,
                  BC18CHIT BC18
                WHERE
                  MM24_1.COMPANY_CD = '00'
                  AND MM24_1.MISE_CD = MM24_2.MISE_CD
                  AND MM24_1.MISE_LEASE_SHU = MM24_2.MISE_LEASE_SHU
                  AND MM24_1.MISE_M_DATE = MM24_2.MISE_M_DATE
                  AND MM24_1.MISE_LEASE_SHU = BC18.MISE_LEASE_SHU
                  AND MM24_1.COMPANY_CD = BC18.COMPANY_CD
                GROUP BY
                   MM24_1.MISE_CD
                  ,MM24_1.MISE_LEASE_SHU
            )  BC18_COUNT
        GROUP BY
            BC18_COUNT.MISE_LEASE_SHU
    )  BC18_MAX ON (BC18_SRC.MISE_LEASE_SHU = BC18_MAX.MISE_LEASE_SHU)
WHERE
    BC18_SRC.COMPANY_CD='00'
ORDER BY
  BC18_SRC.MISE_LEASE_SHU