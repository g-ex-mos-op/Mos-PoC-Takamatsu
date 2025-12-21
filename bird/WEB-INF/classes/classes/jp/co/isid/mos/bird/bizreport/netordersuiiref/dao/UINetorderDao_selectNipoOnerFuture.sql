SELECT SUBSTR((CASE WHEN POS.YOSAN_DT IS NULL THEN ZENNEN54.EIGYO_DT ELSE POS.YOSAN_DT END), 5, 4) AS SORT_NO
,      (CASE WHEN POS.YOSAN_DT IS NOT NULL THEN POS.YOSAN_DT ELSE '' END) AS EIGYO_DT
,      (CASE WHEN POS.YOSAN IS NULL THEN 0 ELSE POS.YOSAN END) AS YOSAN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.URIAGE END) AS URIAGE_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.KYAKUSU END) AS KYAKUSU_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.TENKO_KBN END) AS TENKO_KBN_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.URIAGE_NSUM END)  AS URIAGE_NSUM_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.KYAKUSU_NSUM END) AS KYAKUSU_NSUM_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.URIAGE_NTAKE END) AS URIAGE_NTAKE_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.KYAKUSU_NTAKE END) AS KYAKUSU_NTAKE_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.URIAGE_NTAKU END) AS URIAGE_NTAKU_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.KYAKUSU_NTAKU END) AS KYAKUSU_NTAKU_ZEN

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
      SELECT BD57.EIGYO_DT
      ,      SUM(BD57.URIAGE_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU') AS URIAGE
      ,      SUM(BD57.ONER_YOSAN) AS YOSAN
      ,      SUM(BD57.KYAKUSU_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU') AS KYAKUSU
      ,      SUM(BD57.EIGYO_DAYS_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU') AS EIGYO_DAYS
      ,      MAX(BD57.TENKO_KBN_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU') AS TENKO_KBN
      ,      SUM(BD57.EIGYO_DAYS_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NSUM) AS EIGYO_DAYS_NSUM
      ,      SUM(BD57.URIAGE_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NSUM) AS URIAGE_NSUM
      ,      SUM(BD57.KYAKUSU_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NSUM) AS KYAKUSU_NSUM
      ,      SUM(BD57.EIGYO_DAYS_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NTAKE) AS EIGYO_DAYS_NTAKE
      ,      SUM(BD57.URIAGE_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NTAKE) AS URIAGE_NTAKE
      ,      SUM(BD57.KYAKUSU_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NTAKE) AS KYAKUSU_NTAKE
      ,      SUM(BD57.EIGYO_DAYS_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NTAKU) AS EIGYO_DAYS_NTAKU
      ,      SUM(BD57.URIAGE_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NTAKU) AS URIAGE_NTAKU
      ,      SUM(BD57.KYAKUSU_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NTAKU) AS KYAKUSU_NTAKU
      FROM  (
              SELECT BM01.MISE_CD, BM01.CLOSE_DT FROM BM01TENM BM01
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
           )BM01, BD57NNIP BD57
      WHERE BD57.EIGYO_DT BETWEEN   /*sysdate*/ and /*sysdateGetumatu*/
      AND   BD57.COMPANY_CD = /*paramsDto.companyCd*/'00'
      AND   BD57.MISE_CD = BM01.MISE_CD
      AND   BD57.EIGYO_DT <= BM01.CLOSE_DT
      AND   BD57.OLDM_FLG = '0'
      GROUP BY BD57.EIGYO_DT
) ZENNEN54
ON (ZENNEN54.EIGYO_DT = POS.YOSAN_DT)
ORDER BY SORT_NO