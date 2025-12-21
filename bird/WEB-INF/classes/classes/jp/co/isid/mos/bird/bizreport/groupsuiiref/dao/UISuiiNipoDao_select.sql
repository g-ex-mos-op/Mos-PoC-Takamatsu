select SUBSTR((CASE WHEN BT4YOSAN.YOSAN_DT IS NULL THEN BT60.EIGYO_DT ELSE BT4YOSAN.YOSAN_DT END), 1,6) AS EIGYO_YM
,      (CASE WHEN BT4YOSAN.YOSAN_DT IS NOT NULL THEN BT4YOSAN.YOSAN_DT ELSE BT60.EIGYO_DT END) AS EIGYO_DT 
,      (CASE WHEN BT60.URIAGE IS NULL THEN 0 ELSE BT60.URIAGE END) AS URIAGE
,      (CASE WHEN BT4YOSAN.YOSAN IS NULL THEN 0 ELSE BT4YOSAN.YOSAN END) AS YOSAN
,      (CASE WHEN BT60.URIAGE_ZEN IS NULL THEN 0 ELSE BT60.URIAGE_ZEN END)   AS URIAGE_ZEN
,      (CASE WHEN BT60.KYAKUSU IS NULL THEN 0 ELSE BT60.KYAKUSU END)  AS KYAKUSU
,      (CASE WHEN BT60.KYAKUSU_ZEN IS NULL THEN 0 ELSE BT60.KYAKUSU_ZEN END)  AS KYAKUSU_ZEN

/*IF taishoJoken.equals("MISE") */
,      (CASE WHEN BT60.EIGYO_DAYS IS NULL THEN 0 ELSE BT60.EIGYO_DAYS END) AS EIGYO_DAYS
,      (CASE WHEN BT60.TENKO_KBN IS NULL THEN 0 ELSE BT60.TENKO_KBN END) AS TENKO_KBN
,      (CASE WHEN BT60.EIGYO_DAYS_ZEN IS NULL THEN 0 ELSE BT60.EIGYO_DAYS_ZEN END)  AS EIGYO_DAYS_ZEN
,      (CASE WHEN BT60.TENKO_KBN_ZEN IS NULL THEN 0 ELSE BT60.TENKO_KBN_ZEN END)  AS TENKO_KBN_ZEN
,      CASE WHEN BT60.TENKO_KBN = 1 THEN 'ê∞'
            WHEN BT60.TENKO_KBN = 2 THEN 'ì‹'
            WHEN BT60.TENKO_KBN = 3 THEN 'âJ'
            WHEN BT60.TENKO_KBN = 4 THEN 'ê·'
            WHEN BT60.TENKO_KBN = 5 THEN 'óí'
            ELSE '' END  AS TENKO_KBN_KJ
,      CASE WHEN BT60.TENKO_KBN_ZEN = 1 THEN 'ê∞'
            WHEN BT60.TENKO_KBN_ZEN = 2 THEN 'ì‹'
            WHEN BT60.TENKO_KBN_ZEN = 3 THEN 'âJ'
            WHEN BT60.TENKO_KBN_ZEN = 4 THEN 'ê·'
            WHEN BT60.TENKO_KBN_ZEN = 5 THEN 'óí'
            ELSE '' END  AS TENKO_KBN_ZEN_KJ
/*END*/
/*IF "HOSEI".equals(zennenDatashubetu) */
,      (CASE WHEN BT60.NET_URIAGE IS NULL THEN 0 ELSE BT60.NET_URIAGE END) AS NET_URIAGE
,      (CASE WHEN BT60.NET_URIAGE_ZEN IS NULL THEN 0 ELSE BT60.NET_URIAGE_ZEN END) AS NET_URIAGE_ZEN
,      (CASE WHEN BT60.NET_KYAKUSU IS NULL THEN 0 ELSE BT60.NET_KYAKUSU END) AS NET_KYAKUSU
,      (CASE WHEN BT60.NET_KYAKUSU_ZEN IS NULL THEN 0 ELSE BT60.NET_KYAKUSU_ZEN END) AS NET_KYAKUSU_ZEN
/*END*/
from (SELECT YOSANTBL.YOSAN_DT
      ,      SUM(YOSANTBL.YOSAN) AS YOSAN
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
/*IF "JIGYOU".equals(taishoJoken) || "SLAREA".equals(taishoJoken) || "SIBU".equals(taishoJoken) */  
             AND   EXISTS (
                 SELECT * FROM BM10GSIB BM10
                 WHERE  BM10.COMPANY_CD = /*companyCd*/'00'
                 AND /*$taishoJoken*/'SIBU'_CD = /*hyojiTaisho*/'031'
                 AND BM10.SIBU_CD = BM01.SIBU_CD
                 ) 
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
		    GROUP BY BM01.SIBU_CD
/*IF ("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken) */  
            ,        BM01.MISE_CD 
/*END*/
     ) BM01
     ,      BT45DSJY YOSANTBL
      WHERE YOSAN_DT < /*sysDate*/'20110228'
      AND   YOSAN_DT BETWEEN /*kikanFromAddDay*/'20110101' AND /*kikanToAddDay*/'20110131'
      AND   YOSANTBL.COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
      AND   YOSANTBL.SIBU_CD = /*hyojiTaisho*/'031'
/*END*/
/*IF "MISE".equals(taishoJoken) */  
      AND   YOSANTBL.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
/*IF "1".equals(tenpoShubetu) || "3".equals(tenpoShubetu) */
      AND   YOSANTBL.TENPO_SHU = /*tenpoShubetu*/'1'
/*END*/
/*IF "2".equals(tenpoShubetu) */
      AND   YOSANTBL.TENPO_SHU IN ('1', '2')
/*END*/
      AND   YOSANTBL.SIBU_CD = BM01.SIBU_CD
/*IF ("SIBU".equals(taishoJoken) && blockCd != null) || "MISE".equals(taishoJoken) */
      AND   YOSANTBL.MISE_CD = BM01.MISE_CD
	/*END*/
      
/*END*/
      group by YOSANTBL.YOSAN_DT
      ) BT4YOSAN
FULL OUTER JOIN ( 
      SELECT BT60.EIGYO_DT
      ,      SUM(BT60.URIAGE) AS URIAGE
      ,      SUM(BT60.ONER_YOSAN) AS ONER_YOSAN
      ,      SUM(BT60.EIGYO_DAYS) AS EIGYO_DAYS
      ,      MAX(BT60.TENKO_KBN) AS TENKO_KBN
      ,      SUM(BT60.KYAKUSU) AS KYAKUSU
/*IF "HOSEI".equals(zennenDatashubetu)==false */
      ,      SUM(BT60.URIAGE_ZEN_/*$zennenDatashubetu*/'DOGETU') AS URIAGE_ZEN
      ,      SUM(BT60.EIGYO_DAYS_ZEN_/*$zennenDatashubetu*/'DOGETU') AS EIGYO_DAYS_ZEN
      ,      MAX(BT60.TENKO_KBN_ZEN_/*$zennenDatashubetu*/'DOGETU') AS TENKO_KBN_ZEN
      ,      SUM(BT60.KYAKUSU_ZEN_/*$zennenDatashubetu*/'DOGETU') AS KYAKUSU_ZEN
--ELSE
      ,      SUM(BT60.URIAGE_ZEN_DOGETU) AS URIAGE_ZEN
      ,      SUM(BT60.EIGYO_DAYS_ZEN_DOGETU) AS EIGYO_DAYS_ZEN
      ,      MAX(BT60.TENKO_KBN_ZEN_DOGETU) AS TENKO_KBN_ZEN
      ,      SUM(BT60.KYAKUSU_ZEN_DOGETU) AS KYAKUSU_ZEN
      ,      SUM(CASE WHEN BT60.URIAGE > 0 AND BT60.URIAGE_ZEN_DOGETU > 0 THEN BT60.URIAGE ELSE 0 END) AS NET_URIAGE
      ,      SUM(CASE WHEN BT60.URIAGE > 0 AND BT60.URIAGE_ZEN_DOGETU > 0 THEN BT60.EIGYO_DAYS ELSE 0 END) AS NET_EIGYO_DAYS
      ,      MAX(CASE WHEN BT60.URIAGE > 0 AND BT60.URIAGE_ZEN_DOGETU > 0 THEN BT60.TENKO_KBN ELSE 0 END) AS NET_TENKO_KBN
      ,      SUM(CASE WHEN BT60.URIAGE > 0 AND BT60.URIAGE_ZEN_DOGETU > 0 THEN BT60.KYAKUSU ELSE 0 END) AS NET_KYAKUSU
      ,      SUM(CASE WHEN BT60.URIAGE > 0 AND BT60.URIAGE_ZEN_DOGETU > 0 THEN BT60.URIAGE_ZEN_DOGETU ELSE 0 END) AS NET_URIAGE_ZEN
      ,      SUM(CASE WHEN BT60.URIAGE > 0 AND BT60.URIAGE_ZEN_DOGETU > 0 THEN BT60.EIGYO_DAYS_ZEN_DOGETU ELSE 0 END) AS NET_EIGYO_DAYS_ZEN
      ,      MAX(CASE WHEN BT60.URIAGE > 0 AND BT60.URIAGE_ZEN_DOGETU > 0 THEN BT60.TENKO_KBN_ZEN_DOGETU ELSE 0 END) AS NET_TENKO_KBN_ZEN
      ,      SUM(CASE WHEN BT60.URIAGE > 0 AND BT60.URIAGE_ZEN_DOGETU > 0 THEN BT60.KYAKUSU_ZEN_DOGETU ELSE 0 END) AS NET_KYAKUSU_ZEN
/*END*/ 
      FROM (SELECT MISE_CD FROM BM01TENM BM01
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
/*IF "JIGYOU".equals(taishoJoken) || "SLAREA".equals(taishoJoken) || "SIBU".equals(taishoJoken) */  
            AND   EXISTS (SELECT * FROM BM10GSIB BM10
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
            AND   EXISTS (SELECT * FROM BM51SVSB AS BM51
                           WHERE BM51.SV_CD = /*userId*/'00000921'
                           AND BM51.SIBU_CD = BM01.SIBU_CD)
/*END*/
                ) BM01 
       ,    BN01DTEN BN01
       ,    BT60ZNIP BT60
        WHERE BT60.COMPANY_CD = /*companyCd*/'00'
        AND   BT60.EIGYO_DT < /*sysDate*/'20110228'
        AND   BT60.EIGYO_DT BETWEEN /*kikanFromAddDay*/'20110101' AND /*kikanToAddDay*/'20110131'
        AND   BT60.OLDM_FLG <> '1'
/*IF taishoJoken.equals("MISE") */  
        AND   BT60.MISE_CD = /*hyojiTaisho*/'02001'
/*END*/
/*IF ("01".equals(userTypeCd) && "ALL".equals(tenpoShubetu))==false */
        AND   BT60.OPEN_KBN = 1 
--ELSE
        AND   (BT60.OPEN_KBN + BT60.OPEN_KBN_ZEN_/*$zennenDatashubetu*/'DOGETU') > 0
/*END*/
        AND   BT60.COMPANY_CD    = BN01.COMPANY_CD
        AND   BT60.EIGYO_DT = BN01.EIGYO_DT 
        AND   BT60.MISE_CD       = BN01.MISE_CD
/*IF "MISE".equals(taishoJoken) */  
        AND   BN01.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
        AND   BN01.KBN1 = /*tenpoShubetu*/'1'
/*END*/
/*IF tenpoShubetu == "2" */
        AND   BN01.KBN1 IN ('1', '2')
/*END*/
        AND   BT60.MISE_CD  = BM01.MISE_CD
        GROUP BY BT60.EIGYO_DT
) BT60 ON (BT60.EIGYO_DT = BT4YOSAN.YOSAN_DT)
ORDER BY EIGYO_DT
