SELECT SUBSTR((CASE WHEN POS.YOSAN_DT IS NULL THEN ZENNEN35.EIGYO_DT ELSE POS.YOSAN_DT END), 5, 4) AS SORT_NO
,      (CASE WHEN POS.YOSAN_DT IS NOT NULL THEN POS.YOSAN_DT ELSE '' END) AS EIGYO_DT
,      (CASE WHEN POS.YOSAN IS NULL THEN 0 ELSE POS.YOSAN END) AS YOSAN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.URIAGE END) AS URIAGE_ZEN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.KYAKUSU END) AS KYAKUSU_ZEN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.EIGYO_DAYS END) AS EIGYO_DAYS_ZEN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.TENKO_KBN END) AS TENKO_KBN_ZEN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.ISSUE_CNT END)  AS ZEN_ISSUE_CNT
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.CHARGE_KIN END) AS ZEN_CHARGE_KIN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.CHARGE_KEN END) AS ZEN_CHARGE_KEN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.KESSAI_KIN END) AS ZEN_KESSAI_KIN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.KESSAI_KEN END) AS ZEN_KESSAI_KEN

from  (SELECT BD14.EIGYO_DT AS YOSAN_DT
       ,      SUM(BD29.POS_YOSAN) AS YOSAN
       FROM BD14ACAL BD14
       LEFT JOIN (
               SELECT YOSAN_DT, POS_YOSAN
               FROM (
              SELECT MISE_CD FROM BM01TENM BM01
              WHERE COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "MISE".equals(paramsDto.taishoJoken) */
            AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'04179'
--ELSE
            AND   BM01.ONER_CD = /*paramsDto.hyojiTaisho*/'37006'
/*END*/
              AND   EXISTS (SELECT * FROM BN01DTEN BN01
               WHERE BN01.EIGYO_YM = /*paramsDto.focusTab*/'200902'
               AND   BN01.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "MISE".equals(paramsDto.taishoJoken) */
               AND   BN01.MISE_CD = /*paramsDto.hyojiTaisho*/'00108'
/*END*/
               AND   BN01.MISE_CD = BM01.MISE_CD)
           ) BM01
       ,   BD29PSYN BD29
           WHERE BD29.COMPANY_CD = /*paramsDto.companyCd*/'00'
           AND BD29.MISE_CD = BM01.MISE_CD
/*IF "MISE".equals(paramsDto.taishoJoken) */
           AND BD29.MISE_CD = /*paramsDto.hyojiTaisho*/'04821'
/*END*/
            ) BD29
           ON (BD29.YOSAN_DT = BD14.EIGYO_DT)
           WHERE EIGYO_DT BETWEEN  /*futureFrom*/'20110111' AND /*futureTo*/'20110131'
           GROUP BY BD14.EIGYO_DT
      ) POS
FULL OUTER JOIN (
      SELECT BD56.EIGYO_DT
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_URIAGE) AS URIAGE
      ,      SUM(BD56.ONER_YOSAN) AS YOSAN
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_EIGYO_DAYS) AS EIGYO_DAYS
      ,      MAX(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_TENKO_KBN) AS TENKO_KBN
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_KYAKUSU) AS KYAKUSU
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_ISSUE_CNT)  AS ISSUE_CNT
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_CHARGE_KIN) AS CHARGE_KIN
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_CHARGE_KEN) AS CHARGE_KEN
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_KESSAI_KIN) AS KESSAI_KIN
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_KESSAI_KEN) AS KESSAI_KEN
      FROM  (
                  SELECT BM01.MISE_CD , BM01.CLOSE_DT FROM BM01TENM BM01
                  WHERE BM01.COMPANY_CD = /*paramsDto.companyCd*/'00'
                  AND   BM01.CLOSE_DT >= /*togetuGessho*/
/*IF "MISE".equals(paramsDto.taishoJoken) */
                  AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'04179'
--ELSE
                  AND   BM01.ONER_CD = /*paramsDto.hyojiTaisho*/'37006'
/*END*/
                  AND   EXISTS (SELECT * FROM BN01DTEN BN01
                                WHERE BN01.EIGYO_YM = /*paramsDto.focusTab*/'200902'
                                AND   BN01.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "MISE".equals(paramsDto.taishoJoken) */
                                AND   BN01.MISE_CD = /*paramsDto.hyojiTaisho*/'00108'
/*END*/
                                AND   BN01.MISE_CD = BM01.MISE_CD
            )
           )BM01,    BD56ZNMC BD56
      WHERE BD56.EIGYO_DT BETWEEN   /*sysdate*/ and /*sysdateGetumatu*/
      AND   BD56.COMPANY_CD = /*paramsDto.companyCd*/'00'
      AND   BD56.MISE_CD = BM01.MISE_CD
      AND   BD56.EIGYO_DT <= BM01.CLOSE_DT
      AND   BD56.OLDM_FLG = '0'
      GROUP BY BD56.EIGYO_DT
) ZENNEN35
ON (ZENNEN35.EIGYO_DT = POS.YOSAN_DT)
ORDER BY SORT_NO