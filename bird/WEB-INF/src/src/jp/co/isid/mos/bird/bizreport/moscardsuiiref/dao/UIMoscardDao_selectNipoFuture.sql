SELECT SUBSTR((CASE WHEN BT4YOSAN.YOSAN_DT IS NULL
                             THEN ZENNEN35.EIGYO_DT
                             ELSE BT4YOSAN.YOSAN_DT END), 5, 4) AS SORT_NO
,      (CASE WHEN BT4YOSAN.YOSAN_DT IS NOT NULL THEN BT4YOSAN.YOSAN_DT ELSE '' END) AS EIGYO_DT
,      (CASE WHEN BT4YOSAN.YOSAN IS NULL THEN 0 ELSE BT4YOSAN.YOSAN END) AS YOSAN
,      (CASE WHEN ZENNEN35.URIAGE IS NULL THEN 0 ELSE ZENNEN35.URIAGE END) AS URIAGE_ZEN
,      (CASE WHEN ZENNEN35.KYAKUSU IS NULL THEN 0 ELSE ZENNEN35.KYAKUSU END) AS KYAKUSU_ZEN
,      (CASE WHEN ZENNEN35.EIGYO_DAYS IS NULL THEN 0 ELSE ZENNEN35.EIGYO_DAYS END) AS EIGYO_DAYS_ZEN
,      (CASE WHEN ZENNEN35.TENKO_KBN IS NULL THEN 0 ELSE ZENNEN35.TENKO_KBN END) AS TENKO_KBN_ZEN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.ISSUE_CNT END)  AS ZEN_ISSUE_CNT
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.CHARGE_KIN END) AS ZEN_CHARGE_KIN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.CHARGE_KEN END) AS ZEN_CHARGE_KEN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.KESSAI_KIN END) AS ZEN_KESSAI_KIN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.KESSAI_KEN END) AS ZEN_KESSAI_KEN
/*IF paramsDto.selectHosei */
,      (CASE WHEN ZENNEN35.URIAGE IS NULL THEN 0 ELSE ZENNEN35.URIAGE END) AS NET_URIAGE_ZEN
,      (CASE WHEN ZENNEN35.KYAKUSU IS NULL THEN 0 ELSE ZENNEN35.KYAKUSU END) AS NET_KYAKUSU_ZEN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.ISSUE_CNT END)  AS NET_ZEN_ISSUE_CNT
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.CHARGE_KIN END) AS NET_ZEN_CHARGE_KIN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.CHARGE_KEN END) AS NET_ZEN_CHARGE_KEN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.KESSAI_KIN END) AS NET_ZEN_KESSAI_KIN
,      (CASE WHEN ZENNEN35.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN35.KESSAI_KEN END) AS NET_ZEN_KESSAI_KEN
/*END*/
from (

  SELECT BD14.EIGYO_DT AS YOSAN_DT
  ,      BT45.YOSAN
  FROM BD14ACAL BD14
  LEFT JOIN (
       SELECT YOSAN_DT, SUM(YOSAN) AS YOSAN
       FROM (SELECT BM01.COMPANY_CD, BM01.SIBU_CD
/*IF ("SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd) || "MISE".equals(paramsDto.taishoJoken) */
             , BM01.MISE_CD
/*END*/
             FROM BM01TENM BM01
             WHERE BM01.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "MISE".equals(paramsDto.taishoJoken) */
             AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
/*END*/
/*IF "01".equals(birdUserInfo.mstUser.userTypeCd)*/
    /*IF "JIGYOU".equals(paramsDto.taishoJoken) || "SLAREA".equals(paramsDto.taishoJoken) || "SIBU".equals(paramsDto.taishoJoken) */
             AND   EXISTS (SELECT * FROM BM10GSIB BM10
                           WHERE BM10.COMPANY_CD = /*paramsDto.companyCd*/'00'
                           AND   BM10./*$paramsDto.taishoJoken*/'SIBU'_CD = /*paramsDto.hyojiTaisho*/'031'
        /*IF "SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd */
                           AND   BM01.BLOCK_CD = /*paramsDto.blockCd*/'004'
        /*END*/
                           AND   BM10.SIBU_CD    = BM01.SIBU_CD
                           )
    /*END*/
    /*IF "SEGMENT".equals(paramsDto.taishoJoken) */
             AND   EXISTS (SELECT * FROM BM09GTSG BM09
                      WHERE BM09.SEGMENT_TYPE = /*paramsDto.hyojiTaisho*/'M'
                      AND   BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
    /*END*/
    /*IF birdUserInfo.limit*/
             AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.COMPANY_CD = /*paramsDto.companyCd*/'00'
                      AND   BM51.SV_CD = /*birdUserInfo.userID*/'00000921'
                      AND   BM51.SIBU_CD = BM01.SIBU_CD)
    /*END*/
/*END*/
             GROUP BY BM01.COMPANY_CD, BM01.SIBU_CD
/*IF ("SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd) || "MISE".equals(paramsDto.taishoJoken) */
             ,        BM01.MISE_CD
/*END*/
     ) BM01
     ,    BT45DSJY BT45
      WHERE BT45.YOSAN_DT BETWEEN  /*futureFrom*/'20120411' AND /*futureTo*/'20120431'
/*IF "MISE".equals(paramsDto.taishoJoken) */
      AND   BT45.MISE_CD = /*paramsDto.hyojiTaisho*/
/*END*/
/*IF "SIBU".equals(paramsDto.taishoJoken) */
      AND   BT45.SIBU_CD = /*paramsDto.hyojiTaisho*/'031'
/*END*/
/*IF "1".equals(paramsDto.tenpoShubetu) || "3".equals(paramsDto.tenpoShubetu) */
      AND   BT45.TENPO_SHU = /*paramsDto.tenpoShubetu*/'1'
/*END*/
/*IF "2".equals(paramsDto.tenpoShubetu) */
      AND   BT45.TENPO_SHU IN ('1', '2')
/*END*/
      AND   BT45.COMPANY_CD = BM01.COMPANY_CD
      AND   BT45.SIBU_CD = BM01.SIBU_CD
/*IF ("SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd) || "MISE".equals(paramsDto.taishoJoken) */
      AND   BT45.MISE_CD = BM01.MISE_CD
/*END*/
           GROUP BY YOSAN_DT
      )  BT45
      ON (BT45.YOSAN_DT = BD14.EIGYO_DT)
           WHERE BD14.EIGYO_DT BETWEEN  /*futureFrom*/'20120411' AND /*futureTo*/'20120431'

      ) BT4YOSAN
FULL OUTER JOIN (
      SELECT BD56.EIGYO_DT
/*IF "HOSEI".equals(paramsDto.zennenDataShubetu)*/
      ,      SUM(BD56.DOGETU_ZEN_URIAGE) AS URIAGE
      ,      SUM(BD56.ONER_YOSAN) AS YOSAN
      ,      SUM(BD56.DOGETU_ZEN_EIGYO_DAYS) AS EIGYO_DAYS
      ,      MIN(BD56.DOGETU_ZEN_TENKO_KBN) AS TENKO_KBN
      ,      SUM(BD56.DOGETU_ZEN_KYAKUSU) AS KYAKUSU
      ,      SUM(BD56.DOGETU_ZEN_ISSUE_CNT) AS ISSUE_CNT
      ,      SUM(BD56.DOGETU_ZEN_CHARGE_KIN) AS CHARGE_KIN
      ,      SUM(BD56.DOGETU_ZEN_CHARGE_KEN) AS CHARGE_KEN
      ,      SUM(BD56.DOGETU_ZEN_KESSAI_KIN) AS KESSAI_KIN
      ,      SUM(BD56.DOGETU_ZEN_KESSAI_KEN) AS KESSAI_KEN
--ELSE
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_URIAGE) AS URIAGE
      ,      SUM(BD56.ONER_YOSAN) AS YOSAN
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_EIGYO_DAYS) AS EIGYO_DAYS
      ,      MIN(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_TENKO_KBN) AS TENKO_KBN
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_KYAKUSU) AS KYAKUSU
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_ISSUE_CNT) AS ISSUE_CNT
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_CHARGE_KIN) AS CHARGE_KIN
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_CHARGE_KEN) AS CHARGE_KEN
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_KESSAI_KIN) AS KESSAI_KIN
      ,      SUM(BD56./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_KESSAI_KEN) AS KESSAI_KEN
/*END*/
      FROM (
              SELECT distinct SUB.MISE_CD, SUB.CLOSE_DT, SUB.HIKITUGI_DT_OPEN
              FROM
                (
                   SELECT BM01.MISE_CD , BM01.CLOSE_DT, BM01.HIKITUGI_DT_OPEN
                   FROM BM01TENM BM01
                   WHERE BM01.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "ALL".equals(paramsDto.tenpoShubetu) && "DOGETU".equals(paramsDto.zennenDataShubetu)*/
                   AND   BM01.CLOSE_DT >= /*zennenGessho*/
--ELSE
                   AND   BM01.CLOSE_DT >= /*togetuGessho*/
/*END*/
/*IF "MISE".equals(paramsDto.taishoJoken) */
                   AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
/*END*/
/*IF "SIBU".equals(paramsDto.taishoJoken) */
                   AND   BM01.SIBU_CD = /*paramsDto.hyojiTaisho*/'031'
    /*IF "SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd */  
                   AND   BM01.BLOCK_CD = /*paramsDto.blockCd*/'004'
    /*END*/
/*END*/
                   AND   EXISTS (SELECT * FROM BM10GSIB BM10
                                 WHERE COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "JIGYOU".equals(paramsDto.taishoJoken) || "SLAREA".equals(paramsDto.taishoJoken) || "SIBU".equals(paramsDto.taishoJoken) */
                                 AND   /*$paramsDto.taishoJoken*/'SIBU'_CD = /*paramsDto.hyojiTaisho*/'031'
/*END*/
                                 AND   BM10.SIBU_CD = BM01.SIBU_CD
                                )
/*IF "01".equals(birdUserInfo.mstUser.userTypeCd) && birdUserInfo.limit */
                   AND   EXISTS (SELECT * FROM BM51SVSB AS BM51
                                 WHERE BM51.SV_CD = /*birdUserInfo.userId*/'00000921'
                                 AND   BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
/*IF paramsDto.taishoJoken.equals("SEGMENT") */
                   AND   EXISTS (SELECT * FROM BM09GTSG BM09
                                 WHERE SEGMENT_TYPE = /*paramsDto.hyojiTaisho*/'M'
                                 AND   BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
/*END*/
                   AND   EXISTS (SELECT * FROM BN01DTEN BN01
                                 WHERE BN01.COMPANY_CD = /*paramsDto.companyCd*/'00'
                                 AND   BN01.EIGYO_YM = /*paramsDto.focusTab*/'201304'
/*IF "MISE".equals(paramsDto.taishoJoken) */
                                 AND   BN01.MISE_CD = /*paramsDto.hyojiTaisho*/'02001'
/*END*/
/*IF paramsDto.tenpoShubetu == "1" || paramsDto.tenpoShubetu == "3"*/
                                 AND   BN01.KBN1 = /*paramsDto.tenpoShubetu*/''
/*END*/
/*IF paramsDto.tenpoShubetu == "2" */
                                 AND   BN01.KBN1 IN ('1', '2')
/*END*/
                                 AND   BN01.MISE_CD = BM01.MISE_CD
                                 )
    /*IF "ALL".equals(paramsDto.taishoJoken)*/
                   UNION ALL
                   (
                              SELECT CAST('00000' AS CHAR(5)) AS MISE_CD, '99991231' AS CLOSE_DT, '00000000' AS HIKITUGI_DT_OPEN
                              FROM BM01TENM BM01
                              WHERE BM01.COMPANY_CD = '00'
                              GROUP BY CAST('00000' AS CHAR(5)) , '99991231' ,'00000000'
                    )
    /*END*/
    /*END*/
    /*IF paramsDto.tenpoShubetu == "1" || paramsDto.tenpoShubetu == "2"*/
                   UNION ALL
                   (
                      SELECT BM01.MISE_CD, BM01.CLOSE_DT, BM01.HIKITUGI_DT_OPEN
                      FROM BM01TENM BM01
                      WHERE BM01.HIKITUGI_DT_OPEN BETWEEN  /*togetuGessho*/ and /*sysdateGetumatu*/
/*IF "MISE".equals(paramsDto.taishoJoken) */
                      AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'02001'
/*END*/
/*IF "JIGYOU".equals(paramsDto.taishoJoken) || "SLAREA".equals(paramsDto.taishoJoken) || "SIBU".equals(paramsDto.taishoJoken) */
                      AND   EXISTS (SELECT * FROM BM10GSIB BM10
                                    WHERE BM10.COMPANY_CD = /*paramsDto.companyCd*/'00'
                                    AND   BM10./*$paramsDto.taishoJoken*/'SIBU'_CD = /*paramsDto.hyojiTaisho*/'031'
    /*IF "SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd */
                                    AND   BM01.BLOCK_CD = /*paramsDto.blockCd*/'004'
    /*END*/
                                    AND   BM10.SIBU_CD = BM01.SIBU_CD)
/*END*/
/*IF "01".equals(birdUserInfo.mstUser.userTypeCd) && birdUserInfo.limit*/
                      AND   EXISTS (SELECT * FROM BM51SVSB AS BM51
                                    WHERE BM51.COMPANY_CD = /*paramsDto.companyCd*/'00'
                                    AND   BM51.SV_CD = /*birdUserInfo.userID*/'00000921'
                                    AND   BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
/*IF paramsDto.taishoJoken.equals("SEGMENT") */
                      AND   EXISTS (SELECT * FROM BM09GTSG BM09
                                    WHERE SEGMENT_TYPE = /*paramsDto.hyojiTaisho*/'M'
                                    AND   BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
/*END*/
                   )
    /*END*/
/*END*/
                ) SUB
           ) BM01SUB
      ,    BD56ZNMC BD56
      WHERE BD56.COMPANY_CD = /*paramsDto.companyCd*/'00'
      AND   BD56.EIGYO_DT BETWEEN  /*sysdate*/ and /*sysdateGetumatu*/
      AND   BD56.OLDM_FLG = '0'
      AND   BD56.MISE_CD = BM01SUB.MISE_CD
/*IF paramsDto.tenpoShubetu == "1" || paramsDto.tenpoShubetu == "2" || paramsDto.tenpoShubetu == "3"*/
      AND   BD56.EIGYO_DT <= BM01SUB.CLOSE_DT
/*END*/
/*IF paramsDto.tenpoShubetu == "3"*/
      AND   BM01SUB.HIKITUGI_DT_OPEN < /*togetuGessho*/
/*END*/
      GROUP BY BD56.EIGYO_DT
) ZENNEN35
ON (ZENNEN35.EIGYO_DT = BT4YOSAN.YOSAN_DT)
ORDER BY SORT_NO