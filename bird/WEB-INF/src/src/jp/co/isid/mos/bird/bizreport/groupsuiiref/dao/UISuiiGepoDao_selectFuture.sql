select BT4YOSAN.YOSAN_DT AS EIGYO_DT
,      0 AS URIAGE
,      BT4YOSAN.YOSAN AS YOSAN
,      0.00 AS YOSAN_TASSEI_RITU

,      0 AS EIGYO_DAYS
,      DECIMAL(CASE WHEN LAST_BT64.URIAGE IS NULL THEN 0 ELSE LAST_BT64.URIAGE END) AS URIAGE_ZEN
,      0.00 AS URIAGE_ZENNENHI

,      DECIMAL(CASE WHEN LAST_BT64.EIGYO_DAYS IS NULL THEN 0 ELSE LAST_BT64.EIGYO_DAYS END) AS EIGYO_DAYS_ZEN

,      0 AS KYAKUSU
,      DECIMAL(CASE WHEN LAST_BT64.KYAKUSU IS NULL THEN 0 ELSE LAST_BT64.KYAKUSU END) AS KYAKUSU_ZEN
,      0.00 AS KYAKUSU_ZENNENHI
,      0 AS KYAKUTANKA

,      0.00 AS KYAKUTANKA_ZENNENHI

/*IF "SIBU".equals(taishoJoken) && ("090".equals(hyojiTaisho) || "091".equals(hyojiTaisho)) || "MISE".equals(taishoJoken) */
,      0 AS NEBIKI
,      DECIMAL(CASE WHEN LAST_BT59.URIAGE_ZEN_AFTER_NEBIKI IS NULL THEN 0 ELSE LAST_BT59.URIAGE_ZEN_AFTER_NEBIKI END) AS URIAGE_ZEN_AFTER_NEBIKI
/*END*/
/*IF "MISE".equals(taishoJoken) */
,      (
         SELECT SIBU_CD
          FROM BM01TENM BM01
         WHERE BM01.COMPANY_CD = /*companyCd*/'00'
           AND BM01.MISE_CD = /*hyojiTaisho*/'01776'
        ) AS SYOZOKU_SIBU
/*END*/
/*IF "HOSEI".equals(zennenDatashubetu) */
,      DECIMAL(CASE WHEN LAST_BT64.URIAGE IS NULL THEN 0 ELSE LAST_BT64.URIAGE END) AS NET_URIAGE_ZEN
,      DECIMAL(CASE WHEN LAST_BT64.EIGYO_DAYS IS NULL THEN 0 ELSE LAST_BT64.EIGYO_DAYS END) AS NET_EIGYO_DAYS_ZEN
,      DECIMAL(CASE WHEN LAST_BT64.KYAKUSU IS NULL THEN 0 ELSE LAST_BT64.KYAKUSU END) AS NET_KYAKUSU_ZEN
/*END*/
from (
      SELECT BT44.YOSAN_DT
      ,      SUM(BT44.YOSAN) AS YOSAN
      FROM (SELECT BM01.SIBU_CD
/*IF ("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken) */
            ,      BM01.MISE_CD 
/*END*/
            FROM BM01TENM BM01
            WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
            AND   BM01.SIBU_CD = /*hyojiTaisho*/'031'
 /*IF blockCd != null */
            AND   BM01.BLOCK_CD = /*blockCd*/'004'
 /*END*/
/*END*/
/*IF taishoJoken.equals("MISE") */  
        AND   BM01.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
/*IF "JIGYOU".equals(taishoJoken) || "SLAREA".equals(taishoJoken) || "SIBU".equals(taishoJoken) */  
            AND   EXISTS (
                 SELECT * FROM BM10GSIB BM10
                 WHERE  BM10.COMPANY_CD = /*companyCd*/'00'
                 AND /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
                 AND BM10.SIBU_CD = BM01.SIBU_CD) 
/*END*/         
/*IF "SEGMENT".equals(taishoJoken) */  
            AND   EXISTS (SELECT * FROM BM09GTSG BM09
                      WHERE SEGMENT_TYPE = /*hyojiTaisho*/'M' 
                      AND BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
/*END*/
/*IF "01".equals(userTypeCd) && limitFlg*/
           AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.SV_CD = /*userId*/'00000921'
                      AND BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
/*IF (("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken))==false */
        GROUP BY BM01.SIBU_CD
/*END*/
     ) BM01
     ,     BT44MSJY BT44
      WHERE BT44.COMPANY_CD = /*companyCd*/'00'
      AND   BT44.YOSAN_DT BETWEEN /*futureFrom*/'' AND /*futureTo*/
/*IF "SIBU".equals(taishoJoken) */  
      AND   BT44.SIBU_CD = /*hyojiTaisho*/'031'
/*END*/
/*IF "MISE".equals(taishoJoken) */  
      AND   BT44.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
/*IF "1".equals(tenpoShubetu) || "3".equals(tenpoShubetu) */
      AND   BT44.TENPO_SHU = /*tenpoShubetu*/'1'
/*END*/
/*IF "2".equals(tenpoShubetu) */
      AND   BT44.TENPO_SHU IN ('1', '2')
/*END*/
      AND   BT44.SIBU_CD = BM01.SIBU_CD
/*IF ("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken) */
      AND   BT44.MISE_CD = BM01.MISE_CD
/*END*/
      group by BT44.YOSAN_DT
      ) BT4YOSAN
LEFT JOIN    ( 
      SELECT SUBSTR(CHAR(DECIMAL(DECIMAL(SUBSTR(SUB.EIGYO_DT, 1,6))+100, 6,0)),1,6) AS EIGYO_DT
      ,      SUM(SUB.URIAGE) AS URIAGE
      ,      SUM(SUB.ONER_YOSAN) AS ONER_YOSAN
      ,      SUM(SUB.EIGYO_DAYS) AS EIGYO_DAYS
      ,      SUM(SUB.KYAKUSU) AS KYAKUSU

      FROM (
            SELECT SUBBT64.EIGYO_DT
            ,      SUBBT64.MISE_CD
            ,      SUBBT64.URIAGE_DOGETU AS URIAGE
            ,      SUBBT64.ONER_YOSAN_DOGETU AS ONER_YOSAN
            ,      SUBBT64.EIGYO_DAYS_DOGETU AS EIGYO_DAYS
            ,      SUBBT64.KYAKUSU_DOGETU AS KYAKUSU
            FROM (
               SELECT EIGYO_YM
               ,      MISE_CD
               FROM BN01DTEN 
               WHERE  COMPANY_CD = /*companyCd*/'00'
               AND    EIGYO_YM  BETWEEN /*kikanFrom*/'' AND /*kikanFrom*/''
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
               AND   KBN1 = /*tenpoShubetu*/''
/*END*/
/*IF tenpoShubetu == "2" */
               AND   KBN1 IN ('1', '2')
/*END*/
/*IF taishoJoken.equals("MISE") */  
               AND   MISE_CD = /*hyojiTaisho*/
/*END*/
               GROUP BY EIGYO_YM
               ,        MISE_CD
            ) BN01
            ,   (
               SELECT *
               FROM BT64ZGEP 
               WHERE COMPANY_CD = /*companyCd*/'00'
               AND   EIGYO_DT BETWEEN /*zennenFrom*/'' AND /*zennenTo*/''
               AND   OLDM_FLG <> '1'
/*IF taishoJoken.equals("MISE") */  
               AND   MISE_CD = /*hyojiTaisho*/
/*END*/
/*IF tenpoShubetu != "ALL" */
               AND   OPEN_KBN = 1
/*END*/
            ) SUBBT64
            WHERE SUBBT64.MISE_CD  = BN01.MISE_CD
            GROUP BY SUBBT64.EIGYO_DT
            ,        SUBBT64.MISE_CD
            ,        SUBBT64.URIAGE_DOGETU
            ,        SUBBT64.ONER_YOSAN_DOGETU
            ,        SUBBT64.EIGYO_DAYS_DOGETU
            ,        SUBBT64.KYAKUSU_DOGETU
      ) SUB 
      WHERE EXISTS (SELECT * FROM BM01TENM BM01
            WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
            AND   BM01.SIBU_CD = /*hyojiTaisho*/'031'
 /*IF blockCd != null */
            AND   BM01.BLOCK_CD = /*blockCd*/'004'
 /*END*/
/*END*/
/*IF taishoJoken.equals("MISE") */  
            AND   BM01.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
/*IF "JIGYOU".equals(taishoJoken) || "SLAREA".equals(taishoJoken) || "SIBU".equals(taishoJoken) */  
            AND   EXISTS (
                 SELECT * FROM BM10GSIB BM10
                 WHERE  BM10.COMPANY_CD = /*companyCd*/'00'
                 AND /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
                 AND BM10.SIBU_CD = BM01.SIBU_CD) 
/*END*/         
/*IF "SEGMENT".equals(taishoJoken) */  
            AND   EXISTS (SELECT * FROM BM09GTSG BM09
                      WHERE SEGMENT_TYPE = /*hyojiTaisho*/'M' 
                      AND BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
/*END*/
/*IF "01".equals(userTypeCd) && limitFlg*/
           AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.SV_CD = /*userId*/'00000921'
                      AND BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
        AND   SUB.MISE_CD  = BM01.MISE_CD
     )
      GROUP BY SUBSTR(CHAR(DECIMAL(DECIMAL(SUBSTR(SUB.EIGYO_DT, 1,6))+100, 6,0)),1,6)
) LAST_BT64
ON (LAST_BT64.EIGYO_DT    = BT4YOSAN.YOSAN_DT )
/*IF "SIBU".equals(taishoJoken) && ("090".equals(hyojiTaisho) || "091".equals(hyojiTaisho)) || "MISE".equals(taishoJoken) */
LEFT JOIN    (
    select
         SUBSTR(SUB.EIGYO_DT, 1,6) AS EIGYO_DT
         ,SUM(COALESCE(SUB.URI_ZEN_AFT_NBK_/*$zennenDatashubetu*/'DOGETU',0)) AS URIAGE_ZEN_AFTER_NEBIKI
    from(
        select
             SUBBT59.EIGYO_DT AS EIGYO_DT
            ,SUBBT59.MISE_CD
            ,SUBBT59.URI_ZEN_AFT_NBK_/*$zennenDatashubetu*/'DOGETU' AS URI_ZEN_AFT_NBK_/*$zennenDatashubetu*/'DOGETU'
        from    (
               SELECT EIGYO_YM
                      ,MISE_CD
               FROM BN01DTEN
               WHERE  COMPANY_CD = /*companyCd*/'00'
               AND    EIGYO_YM  BETWEEN /*kikanFrom*/'' AND /*kikanFrom*/''
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
               AND   KBN1 = /*tenpoShubetu*/''
/*END*/
/*IF tenpoShubetu == "2" */
               AND   KBN1 IN ('1', '2')
/*END*/
               GROUP BY EIGYO_YM
               ,        MISE_CD
            ) BN01
            ,   (
            select
                 LEFT(BT59.EIGYO_DT, 6) as EIGYO_DT,
                 BT59.MISE_CD,
                 COALESCE(BT59.URI_ZEN_AFT_NBK_/*$zennenDatashubetu*/'DOGETU',0) AS URI_ZEN_AFT_NBK_/*$zennenDatashubetu*/'DOGETU'
            from
                BT59ANZJ BT59
            where
                   COMPANY_CD = /*companyCd*/'00'
               AND LEFT(BT59.EIGYO_DT, 6) BETWEEN /*futureFrom*/'' AND /*futureTo*/
            ) SUBBT59
         where
             BN01.MISE_CD = SUBBT59.MISE_CD
         )SUB
      WHERE EXISTS (SELECT * FROM BM01TENM BM01
            WHERE BM01.COMPANY_CD = /*companyCd*/'00'
            AND   BM01./*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
/*IF "SIBU".equals(taishoJoken) && blockCd != null */
            AND   BM01.BLOCK_CD = /*blockCd*/'004'
/*END*/
            AND   EXISTS (
                 SELECT * FROM BM10GSIB BM10
                 WHERE  BM10.COMPANY_CD = /*companyCd*/'00'
                 AND /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
                 AND BM10.SIBU_CD = BM01.SIBU_CD)
/*IF "01".equals(userTypeCd) && limitFlg*/
           AND EXISTS (
                      SELECT * FROM BM51SVSB AS BM51
                      WHERE BM51.SV_CD = /*userId*/'00000921'
                      AND BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
        AND   SUB.MISE_CD  = BM01.MISE_CD
     )
      GROUP BY SUBSTR(SUB.EIGYO_DT, 1,6)
) LAST_BT59
on (LAST_BT59.EIGYO_DT    = BT4YOSAN.YOSAN_DT) OR (LAST_BT59.EIGYO_DT = LAST_BT64.EIGYO_DT)
/*END*/
order by BT4YOSAN.YOSAN_DT
