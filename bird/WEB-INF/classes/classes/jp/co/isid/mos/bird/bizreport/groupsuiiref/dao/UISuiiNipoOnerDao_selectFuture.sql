SELECT SUBSTR((CASE WHEN POS.YOSAN_DT IS NULL THEN ZENNEN.EIGYO_DT ELSE POS.YOSAN_DT END), 5, 4) AS SORT_NO
,      (CASE WHEN POS.YOSAN_DT IS NOT NULL THEN POS.YOSAN_DT ELSE '' END) AS EIGYO_DT

,      (CASE WHEN POS.YOSAN IS NULL THEN 0 ELSE POS.YOSAN END) AS YOSAN

,      (CASE WHEN ZENNEN.URIAGE IS NULL THEN 0 ELSE ZENNEN.URIAGE END) AS URIAGE_ZEN
,      (CASE WHEN ZENNEN.KYAKUSU IS NULL THEN 0 ELSE ZENNEN.KYAKUSU END) AS KYAKUSU_ZEN
/*IF taishoJoken.equals("MISE") */
,      (CASE WHEN ZENNEN.EIGYO_DAYS IS NULL THEN 0 ELSE ZENNEN.EIGYO_DAYS END) AS EIGYO_DAYS_ZEN
,      (CASE WHEN ZENNEN.TENKO_KBN IS NULL THEN 0 ELSE ZENNEN.TENKO_KBN END) AS TENKO_KBN_ZEN
,      CASE WHEN ZENNEN.TENKO_KBN = 1 THEN '°'
            WHEN ZENNEN.TENKO_KBN = 2 THEN '“Ü'
            WHEN ZENNEN.TENKO_KBN = 3 THEN '‰J'
            WHEN ZENNEN.TENKO_KBN = 4 THEN 'á'
            WHEN ZENNEN.TENKO_KBN = 5 THEN '—’'
            ELSE '' END  AS TENKO_KBN_ZEN_KJ
/*END*/

from  (SELECT BD14.EIGYO_DT AS YOSAN_DT
       ,      SUM(BD29.POS_YOSAN) AS YOSAN
       FROM BD14ACAL BD14
       LEFT JOIN (
               SELECT YOSAN_DT, POS_YOSAN
               FROM (
              SELECT MISE_CD FROM BM01TENM BM01
              WHERE COMPANY_CD = /*companyCd*/'00'
/*IF "MISE".equals(taishoJoken) */
            AND   BM01.MISE_CD = /*hyojiTaisho*/'04179'
--ELSE
/*IF "02".equals(userTypeCd) */
            AND   BM01.ONER_CD = /*onerCd*/'37006'
/*END*/
/*END*/
              AND   EXISTS (SELECT * FROM BN01DTEN BN01
               WHERE BN01.EIGYO_YM = /*taishoYm*/'200902'
               AND   BN01.COMPANY_CD = /*companyCd*/'00'
/*IF "MISE".equals(taishoJoken) */
               AND   BN01.MISE_CD = /*hyojiTaisho*/'00108'
/*END*/
               AND   BN01.MISE_CD = BM01.MISE_CD)
           ) BM01
       ,   BD29PSYN BD29
           WHERE BD29.COMPANY_CD = /*companyCd*/'00'
           AND BD29.MISE_CD = BM01.MISE_CD
/*IF "MISE".equals(taishoJoken) */
           AND BD29.MISE_CD = /*hyojiTaisho*/'04821'
/*END*/
            ) BD29
           ON (BD29.YOSAN_DT = BD14.EIGYO_DT)
           WHERE EIGYO_DT BETWEEN  /*futureFrom*/'20110111' AND /*futureTo*/'20110131'
           GROUP BY BD14.EIGYO_DT
      ) POS
FULL OUTER JOIN (
      SELECT BD55.EIGYO_DT
/*IF "DOGETU".equals(zennenDatashubetu) || "HOSEI".equals(zennenDatashubetu) */
      ,      SUM(BD55.URIAGE_ZEN_DOGETU) AS URIAGE
      ,      SUM(BD55.ONER_YOSAN) AS YOSAN
      ,      SUM(BD55.EIGYO_DAYS_ZEN_DOGETU) AS EIGYO_DAYS
      ,      MIN(BD55.TENKO_KBN_ZEN_DOGETU) AS TENKO_KBN
      ,      SUM(BD55.KYAKUSU_ZEN_DOGETU) AS KYAKUSU
/*END*/
/*IF "DOJITU".equals(zennenDatashubetu) */
      ,      SUM(BD55.URIAGE_ZEN_DOJITU) AS URIAGE
      ,      SUM(BD55.ONER_YOSAN) AS YOSAN
      ,      SUM(BD55.EIGYO_DAYS_ZEN_DOJITU) AS EIGYO_DAYS
      ,      MIN(BD55.TENKO_KBN_ZEN_DOJITU) AS TENKO_KBN
      ,      SUM(BD55.KYAKUSU_ZEN_DOJITU) AS KYAKUSU
/*END*/
/*IF "DOYO".equals(zennenDatashubetu) */
      ,      SUM(BD55.URIAGE_ZEN_DOYO) AS URIAGE
      ,      SUM(BD55.ONER_YOSAN) AS YOSAN
      ,      SUM(BD55.EIGYO_DAYS_ZEN_DOYO) AS EIGYO_DAYS
      ,      MIN(BD55.TENKO_KBN_ZEN_DOYO) AS TENKO_KBN
      ,      SUM(BD55.KYAKUSU_ZEN_DOYO) AS KYAKUSU
/*END*/
      FROM  (
               SELECT MISE_CD, CLOSE_DT FROM BM01TENM BM01
               WHERE COMPANY_CD = /*companyCd*/'00'
               AND   BM01.CLOSE_DT >= /*togetuGessho*/
/*IF "MISE".equals(taishoJoken) */
               AND   BM01.MISE_CD = /*hyojiTaisho*/'04179'
--ELSE
/*IF "02".equals(userTypeCd) */
               AND   BM01.ONER_CD = /*onerCd*/'37006'
/*END*/
/*END*/
               AND   EXISTS (SELECT * FROM BN01DTEN BN01
                             WHERE BN01.EIGYO_YM = /*taishoYm*/'200902'
                             AND   BN01.COMPANY_CD = /*companyCd*/'00'
/*IF "MISE".equals(taishoJoken) */
                             AND   BN01.MISE_CD = /*hyojiTaisho*/'00108'
/*END*/
                             AND   BN01.MISE_CD = BM01.MISE_CD
                            )
            )BM01
      ,    BD55ZNIP BD55
      WHERE BD55.EIGYO_DT BETWEEN   /*sysdate*/ and /*sysdateGetumatu*/
      AND   BD55.COMPANY_CD = /*companyCd*/'00'
      AND   BD55.MISE_CD = BM01.MISE_CD
      AND   BD55.EIGYO_DT <= BM01.CLOSE_DT
      AND   BD55.OLDM_FLG <> '1'
      GROUP BY BD55.EIGYO_DT
) ZENNEN
ON (ZENNEN.EIGYO_DT = POS.YOSAN_DT)
ORDER BY SORT_NO