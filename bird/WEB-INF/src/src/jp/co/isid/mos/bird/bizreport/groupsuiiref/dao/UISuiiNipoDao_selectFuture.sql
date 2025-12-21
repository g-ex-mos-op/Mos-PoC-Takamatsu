SELECT SUBSTR((CASE WHEN BT4YOSAN.YOSAN_DT IS NULL
                             THEN ZENNEN.EIGYO_DT
                             ELSE BT4YOSAN.YOSAN_DT END), 5, 4) AS SORT_NO
,      (CASE WHEN BT4YOSAN.YOSAN_DT IS NOT NULL THEN BT4YOSAN.YOSAN_DT ELSE '' END) AS EIGYO_DT

,      (CASE WHEN BT4YOSAN.YOSAN IS NULL THEN 0 ELSE BT4YOSAN.YOSAN END) AS YOSAN

,      (CASE WHEN ZENNEN.URIAGE IS NULL THEN 0 ELSE ZENNEN.URIAGE END) AS URIAGE_ZEN

,      (CASE WHEN ZENNEN.KYAKUSU IS NULL THEN 0 ELSE ZENNEN.KYAKUSU END) AS KYAKUSU_ZEN

/*IF "SIBU".equals(taishoJoken) && ("090".equals(hyojiTaisho) || "091".equals(hyojiTaisho)) || "MISE".equals(taishoJoken) */
,      (CASE WHEN BT59.URIAGE_ZEN_AFTER_NEBIKI IS NULL THEN 0 ELSE BT59.URIAGE_ZEN_AFTER_NEBIKI END)  AS URIAGE_ZEN_AFTER_NEBIKI
/*END*/

/*IF "MISE".equals(taishoJoken) */
,      (CASE WHEN ZENNEN.EIGYO_DAYS IS NULL THEN 0 ELSE ZENNEN.EIGYO_DAYS END) AS EIGYO_DAYS_ZEN
,      (CASE WHEN ZENNEN.TENKO_KBN IS NULL THEN 0 ELSE ZENNEN.TENKO_KBN END) AS TENKO_KBN_ZEN
,      CASE WHEN ZENNEN.TENKO_KBN = 1 THEN '°'
            WHEN ZENNEN.TENKO_KBN = 2 THEN '“Ü'
            WHEN ZENNEN.TENKO_KBN = 3 THEN '‰J'
            WHEN ZENNEN.TENKO_KBN = 4 THEN 'á'
            WHEN ZENNEN.TENKO_KBN = 5 THEN '—’'
            ELSE '' END  AS TENKO_KBN_ZEN_KJ
,      (
         SELECT SIBU_CD
          FROM BM01TENM BM01
         WHERE BM01.COMPANY_CD = /*companyCd*/'00'
           AND BM01.MISE_CD = /*hyojiTaisho*/'01776'
        ) AS SYOZOKU_SIBU
/*END*/
/*IF "HOSEI".equals(zennenDatashubetu) */
,      (CASE WHEN ZENNEN.URIAGE IS NULL THEN 0 ELSE ZENNEN.URIAGE END) AS NET_URIAGE_ZEN
,      (CASE WHEN ZENNEN.KYAKUSU IS NULL THEN 0 ELSE ZENNEN.KYAKUSU END) AS NET_KYAKUSU_ZEN
/*END*/
from (

  SELECT BD14.EIGYO_DT AS YOSAN_DT
  ,      BT45.YOSAN
  FROM BD14ACAL BD14
  LEFT JOIN (
       SELECT YOSAN_DT, SUM(YOSAN) AS YOSAN
       FROM (SELECT BM01.SIBU_CD
/*IF ("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken) */
             , BM01.MISE_CD
/*END*/
             FROM BM01TENM BM01
             WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF "MISE".equals(taishoJoken) */
             AND   BM01.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
/*IF "SIBU".equals(taishoJoken) */
             AND   BM01.SIBU_CD = /*hyojiTaisho*/'031'
 /*IF blockCd != null */
             AND   BM01.BLOCK_CD = /*blockCd*/'004'
 /*END*/
/*END*/
/*IF "01".equals(userTypeCd) && limitFlg*/
             AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.SV_CD = /*userId*/'00000921'
                      AND BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
             AND   EXISTS (
                 SELECT * FROM BM10GSIB BM10
                 WHERE  BM10.COMPANY_CD = /*companyCd*/'00'
/*IF "JIGYOU".equals(taishoJoken) || "SLAREA".equals(taishoJoken) || "SIBU".equals(taishoJoken) */
                 AND /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
/*END*/
                 AND BM10.SIBU_CD = BM01.SIBU_CD
                 )
/*IF "SEGMENT".equals(taishoJoken) */
             AND   EXISTS (SELECT * FROM BM09GTSG BM09
                      WHERE SEGMENT_TYPE = /*hyojiTaisho*/'M'
                      AND BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
/*END*/
             GROUP BY BM01.SIBU_CD
/*IF ("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken) */
             ,        BM01.MISE_CD
/*END*/
     ) BM01
     ,    BT45DSJY BT45
      WHERE BT45.YOSAN_DT BETWEEN  /*futureFrom*/'20110111' AND /*futureTo*/'20110131'
      AND   BT45.COMPANY_CD = /*companyCd*/'00'
/*IF "MISE".equals(taishoJoken) */
      AND   BT45.MISE_CD = /*hyojiTaisho*/
/*END*/
/*IF "SIBU".equals(taishoJoken) */
      AND   BT45.SIBU_CD = /*hyojiTaisho*/'031'
/*END*/
/*IF "1".equals(tenpoShubetu) || "3".equals(tenpoShubetu) */
      AND   BT45.TENPO_SHU = /*tenpoShubetu*/'1'
/*END*/
/*IF "2".equals(tenpoShubetu) */
      AND   BT45.TENPO_SHU IN ('1', '2')
/*END*/
      AND   BT45.SIBU_CD = BM01.SIBU_CD
/*IF ("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken) */
      AND   BT45.MISE_CD = BM01.MISE_CD
/*END*/
           GROUP BY YOSAN_DT
      )  BT45
      ON (BT45.YOSAN_DT = BD14.EIGYO_DT)
           WHERE BD14.EIGYO_DT BETWEEN  /*futureFrom*/'20110111' AND /*futureTo*/'20110131'

      ) BT4YOSAN
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
      FROM (
              SELECT distinct SUB.MISE_CD, SUB.CLOSE_DT, SUB.HIKITUGI_DT_OPEN
              FROM
                (
                   SELECT BM01.MISE_CD , BM01.CLOSE_DT, BM01.HIKITUGI_DT_OPEN
                   FROM BM01TENM BM01
                   WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF "ALL".equals(tenpoShubetu) && "DOGETU".equals(zennenDataShubetu)*/
                   AND   BM01.CLOSE_DT >= /*zennenGessho*/
--ELSE
                   AND   BM01.CLOSE_DT >= /*togetuGessho*/
/*END*/
/*IF "MISE".equals(taishoJoken) */
                   AND   BM01.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
/*IF "SIBU".equals(taishoJoken) */
                   AND   BM01.SIBU_CD = /*hyojiTaisho*/'031'
 /*IF blockCd != null */
                   AND   BM01.BLOCK_CD = /*blockCd*/'004'
 /*END*/
/*END*/
                   AND   EXISTS (SELECT * FROM BM10GSIB BM10
                                 WHERE COMPANY_CD = /*companyCd*/'00'
/*IF "JIGYOU".equals(taishoJoken) || "SLAREA".equals(taishoJoken) || "SIBU".equals(taishoJoken) */
                                 AND   /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
/*END*/
                                 AND   BM10.SIBU_CD = BM01.SIBU_CD
                                )
/*IF "01".equals(userTypeCd) && limitFlg*/
                   AND   EXISTS (SELECT * FROM BM51SVSB AS BM51
                                 WHERE BM51.SV_CD = /*userId*/'00000921'
                                 AND   BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
/*IF taishoJoken.equals("SEGMENT") */
                   AND   EXISTS (SELECT * FROM BM09GTSG BM09
                                 WHERE SEGMENT_TYPE = /*hyojiTaisho*/'M'
                                 AND   BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
/*END*/
                   AND   EXISTS (SELECT * FROM BN01DTEN BN01
                                 WHERE BN01.COMPANY_CD = /*companyCd*/'00'
                                 AND   BN01.EIGYO_YM = /*taishoYm*/'200902'
/*IF "MISE".equals(taishoJoken) */
                                 AND   BN01.MISE_CD = /*hyojiTaisho*/'02001'
/*END*/
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
                                 AND   BN01.KBN1 = /*tenpoShubetu*/''
/*END*/
/*IF tenpoShubetu == "2" */
                                 AND   BN01.KBN1 IN ('1', '2')
/*END*/
                                 AND   BN01.MISE_CD = BM01.MISE_CD
                                 )
/*IF tenpoShubetu == "1" || tenpoShubetu == "2"*/
                   UNION ALL
                   (
                      SELECT BM01.MISE_CD , BM01.CLOSE_DT, BM01.HIKITUGI_DT_OPEN
                      FROM BM01TENM BM01
                      WHERE BM01.HIKITUGI_DT_OPEN BETWEEN  /*togetuGessho*/ and /*sysdateGetumatu*/
/*IF "MISE".equals(taishoJoken) */
                      AND   BM01.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
/*IF "SIBU".equals(taishoJoken) */
                      AND   BM01.SIBU_CD = /*hyojiTaisho*/'031'
 /*IF blockCd != null */
                      AND   BM01.BLOCK_CD = /*blockCd*/'004'
 /*END*/
/*END*/
                      AND   EXISTS (SELECT * FROM BM10GSIB BM10
                                    WHERE COMPANY_CD = /*companyCd*/'00'
/*IF "JIGYOU".equals(taishoJoken) || "SLAREA".equals(taishoJoken) || "SIBU".equals(taishoJoken) */
                                    AND   /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
/*END*/
                                    AND   BM10.SIBU_CD = BM01.SIBU_CD
                                   )
/*IF "01".equals(userTypeCd) && limitFlg*/
                      AND   EXISTS (SELECT * FROM BM51SVSB AS BM51
                                    WHERE BM51.SV_CD = /*userId*/'00000921'
                                    AND   BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
/*IF taishoJoken.equals("SEGMENT") */
                      AND   EXISTS (SELECT * FROM BM09GTSG BM09
                                    WHERE SEGMENT_TYPE = /*hyojiTaisho*/'M'
                                    AND   BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
/*END*/
                   )
/*END*/
                ) SUB
           ) BM01SUB
      ,    BD55ZNIP BD55
      WHERE BD55.COMPANY_CD = /*companyCd*/'00'
      AND   BD55.EIGYO_DT BETWEEN  /*sysdate*/ and /*sysdateGetumatu*/
      AND   BD55.OLDM_FLG <> '1'
      AND   BD55.MISE_CD = BM01SUB.MISE_CD
/*IF tenpoShubetu == "1" || tenpoShubetu == "2" || tenpoShubetu == "3"*/
      AND   BD55.EIGYO_DT <= BM01SUB.CLOSE_DT 
/*END*/
/*IF tenpoShubetu == "3"*/
      AND   BM01SUB.HIKITUGI_DT_OPEN < /*togetuGessho*/
/*END*/
      GROUP BY BD55.EIGYO_DT
) ZENNEN
ON (ZENNEN.EIGYO_DT = BT4YOSAN.YOSAN_DT)
/*IF "SIBU".equals(taishoJoken) && ("090".equals(hyojiTaisho) || "091".equals(hyojiTaisho)) || "MISE".equals(taishoJoken) */
left outer join (
		SELECT
		  BT59ANZJ.EIGYO_DT,
		  SUM(COALESCE(BT59ANZJ.URI_ZEN_AFT_NBK_/*$zennenDatashubetu*/'DOGETU',0)) AS URIAGE_ZEN_AFTER_NEBIKI
		FROM
           (
              SELECT distinct SUB.MISE_CD, SUB.CLOSE_DT, SUB.HIKITUGI_DT_OPEN
              FROM
                (
                   SELECT BM01.MISE_CD , BM01.CLOSE_DT, BM01.HIKITUGI_DT_OPEN
                   FROM BM01TENM BM01
                   WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF "ALL".equals(tenpoShubetu) && "DOGETU".equals(zennenDataShubetu)*/
                   AND   BM01.CLOSE_DT >= /*zennenGessho*/
--ELSE
                   AND   BM01.CLOSE_DT >= /*togetuGessho*/
/*END*/
                   AND   BM01./*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
/*IF "SIBU".equals(taishoJoken) && blockCd != null */
                   AND   BM01.BLOCK_CD = /*blockCd*/'004'
/*END*/
                   AND   EXISTS (SELECT * FROM BM10GSIB BM10
                                 WHERE COMPANY_CD = /*companyCd*/'00'
                                 AND   /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
                                 AND   BM10.SIBU_CD = BM01.SIBU_CD
                                )
/*IF "01".equals(userTypeCd) && limitFlg*/
                   AND   EXISTS (SELECT * FROM BM51SVSB AS BM51
                                 WHERE BM51.SV_CD = /*userId*/'00000921'
                                 AND   BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
                   AND   EXISTS (SELECT * FROM BN01DTEN BN01
                                 WHERE BN01.COMPANY_CD = /*companyCd*/'00'
                                 AND   BN01.EIGYO_YM = /*taishoYm*/'200902'
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
                                 AND   BN01.KBN1 = /*tenpoShubetu*/''
/*END*/
/*IF tenpoShubetu == "2" */
                                 AND   BN01.KBN1 IN ('1', '2')
/*END*/
                                 AND   BN01.MISE_CD = BM01.MISE_CD
                                 )
/*IF tenpoShubetu == "1" || tenpoShubetu == "2"*/
                   UNION ALL
                   (
                      SELECT BM01.MISE_CD , BM01.CLOSE_DT, BM01.HIKITUGI_DT_OPEN
                      FROM BM01TENM BM01
                      WHERE BM01.HIKITUGI_DT_OPEN BETWEEN  /*togetuGessho*/ and /*sysdateGetumatu*/
                      AND   BM01./*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
/*IF "SIBU".equals(taishoJoken) && blockCd != null */
                      AND   BM01.BLOCK_CD = /*blockCd*/'004'
/*END*/
                      AND   EXISTS (SELECT * FROM BM10GSIB BM10
                                    WHERE COMPANY_CD = /*companyCd*/'00'
                                    AND   /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
                                    AND   BM10.SIBU_CD = BM01.SIBU_CD
                                   )
/*IF "01".equals(userTypeCd) && limitFlg*/
                      AND   EXISTS (SELECT * FROM BM51SVSB AS BM51
                                    WHERE BM51.SV_CD = /*userId*/'00000921'
                                    AND   BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
                   )
/*END*/
                ) SUB
           ) BM01SUB
		  ,BT59ANZJ
		WHERE
		  BT59ANZJ.COMPANY_CD = /*companyCd*/'00'
        AND   BT59ANZJ.EIGYO_DT BETWEEN /*togetuGessho*/'20110101' AND /*sysdateGetumatu*/'20110131'
        AND   BT59ANZJ.MISE_CD = BM01SUB.MISE_CD
		GROUP BY
		  BT59ANZJ.EIGYO_DT
) AS BT59 ON (BT59.EIGYO_DT = BT4YOSAN.YOSAN_DT) OR (BT59.EIGYO_DT = ZENNEN.EIGYO_DT)
/*END*/
ORDER BY SORT_NO