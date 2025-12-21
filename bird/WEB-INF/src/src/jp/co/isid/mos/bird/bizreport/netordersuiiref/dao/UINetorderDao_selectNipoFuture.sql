SELECT SUBSTR((CASE WHEN BT4YOSAN.YOSAN_DT IS NULL
                             THEN ZENNEN54.EIGYO_DT
                             ELSE BT4YOSAN.YOSAN_DT END), 5, 4) AS SORT_NO
,      (CASE WHEN BT4YOSAN.YOSAN_DT IS NOT NULL THEN BT4YOSAN.YOSAN_DT ELSE '' END) AS EIGYO_DT
,      (CASE WHEN BT4YOSAN.YOSAN IS NULL THEN 0 ELSE BT4YOSAN.YOSAN END) AS YOSAN
,      (CASE WHEN ZENNEN54.URIAGE IS NULL THEN 0 ELSE ZENNEN54.URIAGE END) AS URIAGE_ZEN
,      (CASE WHEN ZENNEN54.KYAKUSU IS NULL THEN 0 ELSE ZENNEN54.KYAKUSU END) AS KYAKUSU_ZEN
,      (CASE WHEN ZENNEN54.TENKO_KBN IS NULL THEN 0 ELSE ZENNEN54.TENKO_KBN END) AS TENKO_KBN_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.URIAGE_NSUM END)  AS URIAGE_NSUM_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.KYAKUSU_NSUM END) AS KYAKUSU_NSUM_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.URIAGE_NTAKE END) AS URIAGE_NTAKE_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.KYAKUSU_NTAKE END) AS KYAKUSU_NTAKE_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.URIAGE_NTAKU END) AS URIAGE_NTAKU_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.KYAKUSU_NTAKU END) AS KYAKUSU_NTAKU_ZEN
/*IF paramsDto.selectHosei */
,      (CASE WHEN ZENNEN54.URIAGE IS NULL THEN 0 ELSE ZENNEN54.URIAGE END) AS NET_URIAGE_ZEN
,      (CASE WHEN ZENNEN54.KYAKUSU IS NULL THEN 0 ELSE ZENNEN54.KYAKUSU END) AS NET_KYAKUSU_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.URIAGE_NSUM END)  AS NET_URIAGE_NSUM_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.KYAKUSU_NSUM END) AS NET_KYAKUSU_NSUM_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.URIAGE_NTAKE END) AS NET_URIAGE_NTAKE_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.KYAKUSU_NTAKE END) AS NET_KYAKUSU_NTAKE_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.URIAGE_NTAKU END) AS NET_URIAGE_NTAKU_ZEN
,      (CASE WHEN ZENNEN54.EIGYO_DT IS NULL THEN 0 ELSE ZENNEN54.KYAKUSU_NTAKU END) AS NET_KYAKUSU_NTAKU_ZEN
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
      SELECT BD57.EIGYO_DT
/*IF "HOSEI".equals(paramsDto.zennenDataShubetu)*/
      ,      SUM(BD57.URIAGE_ZEN_DOGETU) AS URIAGE
      ,      SUM(BD57.ONER_YOSAN) AS YOSAN
      ,      SUM(BD57.KYAKUSU_ZEN_DOGETU) AS KYAKUSU
      ,      MAX(BD57.TENKO_KBN_ZEN_DOGETU) AS TENKO_KBN
      ,      SUM(BD57.URIAGE_ZEN_DOGETU_NSUM) AS URIAGE_NSUM
      ,      SUM(BD57.KYAKUSU_ZEN_DOGETU_NSUM) AS KYAKUSU_NSUM
      ,      SUM(BD57.URIAGE_ZEN_DOGETU_NTAKE) AS URIAGE_NTAKE
      ,      SUM(BD57.KYAKUSU_ZEN_DOGETU_NTAKE) AS KYAKUSU_NTAKE
      ,      SUM(BD57.URIAGE_ZEN_DOGETU_NTAKU) AS URIAGE_NTAKU
      ,      SUM(BD57.KYAKUSU_ZEN_DOGETU_NTAKU) AS KYAKUSU_NTAKU
--ELSE
      ,      SUM(BD57.URIAGE_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU') AS URIAGE
      ,      SUM(BD57.ONER_YOSAN) AS YOSAN
      ,      SUM(BD57.KYAKUSU_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU') AS KYAKUSU
      ,      MAX(BD57.TENKO_KBN_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU') AS TENKO_KBN
      ,      SUM(BD57.URIAGE_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NSUM) AS URIAGE_NSUM
      ,      SUM(BD57.KYAKUSU_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NSUM) AS KYAKUSU_NSUM
      ,      SUM(BD57.URIAGE_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NTAKE) AS URIAGE_NTAKE
      ,      SUM(BD57.KYAKUSU_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NTAKE) AS KYAKUSU_NTAKE
      ,      SUM(BD57.URIAGE_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NTAKU) AS URIAGE_NTAKU
      ,      SUM(BD57.KYAKUSU_ZEN_/*$paramsDto.zennenDataShubetu*/'DOGETU'_NTAKU) AS KYAKUSU_NTAKU
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
    /*IF paramsDto.tenpoShubetu == "1" || paramsDto.tenpoShubetu == "2"*/
                   UNION ALL
                   (
                      SELECT BM01.MISE_CD , BM01.CLOSE_DT, BM01.HIKITUGI_DT_OPEN
                      FROM BM01TENM BM01
                      WHERE BM01.HIKITUGI_DT_OPEN BETWEEN  /*togetuGessho*/ and /*sysdateGetumatu*/
/*IF "MISE".equals(paramsDto.taishoJoken) */
                      AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
/*END*/
/*IF "JIGYOU".equals(paramsDto.taishoJoken) || "SLAREA".equals(paramsDto.taishoJoken) || "SIBU".equals(paramsDto.taishoJoken) */
                      AND   EXISTS (SELECT * FROM BM10GSIB BM10
                                    WHERE BM10.COMPANY_CD = /*paramsDto.companyCd*/'00'
                                    AND   BM10./*$paramsDto.taishoJoken*/'SIBU'_CD = /*paramsDto.hyojiTaisho*/'031'
    /*IF "SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd */
                                    AND   BM01.BLOCK_CD = /*paramsDto.blockCd*/'004'
    /*END*/
                                    AND BM10.SIBU_CD = BM01.SIBU_CD)
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
      , BD57NNIP BD57
      WHERE BD57.COMPANY_CD = /*paramsDto.companyCd*/'00'
      AND   BD57.EIGYO_DT BETWEEN   /*sysdate*/ and /*sysdateGetumatu*/
      AND   BD57.OLDM_FLG = '0'
      AND   BD57.MISE_CD = BM01SUB.MISE_CD
/*IF paramsDto.tenpoShubetu == "1" || paramsDto.tenpoShubetu == "2" || paramsDto.tenpoShubetu == "3"*/
      AND   BD57.EIGYO_DT <= BM01SUB.CLOSE_DT
/*END*/
/*IF paramsDto.tenpoShubetu == "3"*/
      AND   BM01SUB.HIKITUGI_DT_OPEN < /*togetuGessho*/
/*END*/
      GROUP BY BD57.EIGYO_DT
) ZENNEN54
ON (ZENNEN54.EIGYO_DT = BT4YOSAN.YOSAN_DT)
ORDER BY SORT_NO