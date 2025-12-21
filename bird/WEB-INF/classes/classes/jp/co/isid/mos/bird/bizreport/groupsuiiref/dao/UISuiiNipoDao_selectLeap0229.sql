select '' AS EIGYO_DT 
,      (CASE WHEN BT60.URIAGE_ZEN IS NULL THEN 0 ELSE BT60.URIAGE_ZEN END)   AS URIAGE_ZEN
,      (CASE WHEN BT60.KYAKUSU_ZEN IS NULL THEN 0 ELSE BT60.KYAKUSU_ZEN END)  AS KYAKUSU_ZEN
,      DECIMAL(
           CASE WHEN (CASE WHEN BT60.KYAKUSU_ZEN IS NULL THEN 0 ELSE BT60.KYAKUSU_ZEN END)  <= 0 THEN 0.00 
                ELSE ((DOUBLE(CASE WHEN BT60.URIAGE_ZEN IS NULL THEN 0 ELSE BT60.URIAGE_ZEN END)/DOUBLE(BT60.KYAKUSU_ZEN))+0.5)
           END, 11, 0) AS KYAKUTANKA_ZEN
/*IF taishoJoken.equals("MISE") */
,      (CASE WHEN BT60.EIGYO_DAYS_ZEN IS NULL THEN 0 ELSE BT60.EIGYO_DAYS_ZEN END)  AS EIGYO_DAYS_ZEN
,      (CASE WHEN BT60.TENKO_KBN_ZEN IS NULL THEN 0 ELSE BT60.TENKO_KBN_ZEN END)  AS TENKO_KBN_ZEN
,      CASE WHEN BT60.TENKO_KBN_ZEN = 1 THEN '°'
            WHEN BT60.TENKO_KBN_ZEN = 2 THEN '“Ü'
            WHEN BT60.TENKO_KBN_ZEN = 3 THEN '‰J'
            WHEN BT60.TENKO_KBN_ZEN = 4 THEN 'á'
            WHEN BT60.TENKO_KBN_ZEN = 5 THEN '—’'
            ELSE '' END  AS TENKO_KBN_ZEN_KJ
/*END*/
from ( 
      SELECT SUB.EIGYO_DT
      ,      SUM(SUB.URIAGE) AS URIAGE
      ,      SUM(SUB.ONER_YOSAN) AS ONER_YOSAN
      ,      SUM(SUB.EIGYO_DAYS) AS EIGYO_DAYS
      ,      MAX(SUB.TENKO_KBN) AS TENKO_KBN
      ,      SUM(SUB.URIAGE_ZEN) AS URIAGE_ZEN
      ,      SUM(SUB.EIGYO_DAYS_ZEN) AS EIGYO_DAYS_ZEN
      ,      MAX(SUB.TENKO_KBN_ZEN) AS TENKO_KBN_ZEN
      ,      SUM(SUB.KYAKUSU) AS KYAKUSU
      ,      SUM(SUB.KYAKUSU_ZEN) AS KYAKUSU_ZEN
      FROM (
            SELECT SUBBT60.EIGYO_DT
            ,      SUBBT60.MISE_CD
            ,      SUBBT60.URIAGE
            ,      SUBBT60.ONER_YOSAN
            ,      SUBBT60.EIGYO_DAYS
            ,      SUBBT60.TENKO_KBN
            ,      SUBBT60.KYAKUSU
            ,      SUBBT60.URIAGE_ZEN_DOGETU     AS URIAGE_ZEN
            ,      SUBBT60.EIGYO_DAYS_ZEN_DOGETU AS EIGYO_DAYS_ZEN
            ,      SUBBT60.TENKO_KBN_ZEN_DOGETU  AS TENKO_KBN_ZEN
            ,      SUBBT60.KYAKUSU_ZEN_DOGETU    AS KYAKUSU_ZEN
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
            ,   (SELECT CASE WHEN SUBSTR(BT60.EIGYO_DT, 7,2)='99'
                                THEN SUBSTR(BT60.EIGYO_DT, 1,6) || '28'
                               WHEN SUBSTR(BT60.EIGYO_DT, 7,2)='28'
                                THEN SUBSTR(BT60.EIGYO_DT, 1,6) || '00'
                           ELSE BT60.EIGYO_DT END AS BN01_EIGYO_DT
                   ,      BT60.EIGYO_DT
	               ,      BT60.COMPANY_CD
	               ,      BT60.MISE_CD
	               ,      BT60.ZENNEN_DT
/*IF "ALL"== tenpoShubetu &&  "01".equals(userTypeCd) */
	               ,      MAX(CASE WHEN BT60.OPEN_KBN=1 THEN BT60.OPEN_KBN
                                     ELSE BT60.OPEN_KBN_ZEN_DOGETU END) 
--ELSE
	               ,      MAX(BT60.OPEN_KBN)
/*END*/
	                          OVER(partition by SUBSTR(BT60.EIGYO_DT, 1,6),  BT60.MISE_CD) OPEN_KBN
	               ,      BT60.URIAGE
	               ,      BT60.URI_YOSAN
	               ,      BT60.ONER_YOSAN
	               ,      BT60.KYAKUSU
	               ,      BT60.EIGYO_DAYS
	               ,      BT60.TENKO_KBN
	               ,      BT60.URIAGE_ZEN_DOGETU
	               ,      BT60.KYAKUSU_ZEN_DOGETU
	               ,      BT60.EIGYO_DAYS_ZEN_DOGETU
	               ,      BT60.TENKO_KBN_ZEN_DOGETU
	               ,      BT60.OPEN_KBN_ZEN_DOGETU
	               ,      BT60.MISE_CD_ZEN
	               ,      BT60.OLDM_FLG
	               FROM BT60ZNIP BT60
	               WHERE BT60.COMPANY_CD = /*companyCd*/'00'
	               AND   BT60.EIGYO_DT < /*sysDate*/'20090302'
	               AND   SUBSTR(EIGYO_DT, 5,4) IN ('0228','0299')
	               AND   SUBSTR(BT60.EIGYO_DT, 1, 6) = /*taishoYm*/'20110101'
/*IF "MISE".equals(taishoJoken) */  
                   AND   BT60.MISE_CD = /*hyojiTaisho*/'02001'
/*END*/
	               AND   BT60.OLDM_FLG <> '1'
               ) SUBBT60
            WHERE SUBBT60.OPEN_KBN = 1
            AND   SUBSTR(SUBBT60.BN01_EIGYO_DT, 5,4) = '0228'
            AND   SUBBT60.COMPANY_CD    = BN01.COMPANY_CD
            AND   SUBBT60.BN01_EIGYO_DT = BN01.EIGYO_DT 
            AND   SUBBT60.MISE_CD       = BN01.MISE_CD
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
            AND   BN01.KBN1 = /*tenpoShubetu*/''
/*END*/
/*IF tenpoShubetu == "2" */
            AND   BN01.KBN1 IN ('1', '2')
/*END*/
/*IF "MISE".equals(taishoJoken) */  
            AND   BN01.MISE_CD = /*hyojiTaisho*/'01776'
/*END*/
            AND   SUBBT60.MISE_CD  = BM01.MISE_CD
            GROUP BY SUBBT60.EIGYO_DT
            ,        SUBBT60.MISE_CD
            ,        SUBBT60.URIAGE
            ,        SUBBT60.ONER_YOSAN
            ,        SUBBT60.EIGYO_DAYS
            ,        SUBBT60.TENKO_KBN
            ,        SUBBT60.KYAKUSU
            ,        SUBBT60.URIAGE_ZEN_DOGETU
            ,        SUBBT60.EIGYO_DAYS_ZEN_DOGETU
            ,        SUBBT60.TENKO_KBN_ZEN_DOGETU
            ,        SUBBT60.KYAKUSU_ZEN_DOGETU
      ) SUB 
      GROUP BY SUB.EIGYO_DT
) BT60
