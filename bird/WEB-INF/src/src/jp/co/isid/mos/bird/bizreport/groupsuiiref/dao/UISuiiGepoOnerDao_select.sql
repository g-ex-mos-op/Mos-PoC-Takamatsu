select BT64.EIGYO_DT AS EIGYO_DT 
,      BT64.URIAGE AS URIAGE
,      BT64.ONER_YOSAN AS YOSAN
,      BT64.EIGYO_DAYS AS EIGYO_DAYS
,      BT64.URIAGE_ZEN AS URIAGE_ZEN
,      BT64.EIGYO_DAYS_ZEN AS EIGYO_DAYS_ZEN
,      BT64.KYAKUSU AS KYAKUSU
,      BT64.KYAKUSU_ZEN AS KYAKUSU_ZEN

from (
      SELECT BT64.EIGYO_DT
      ,      SUM(BT64.URIAGE_DOGETU) AS URIAGE
      ,      SUM(BT64.ONER_YOSAN_/*$zennenDatashubetu*/'DOGETU') AS ONER_YOSAN
      ,      SUM(BT64.EIGYO_DAYS_/*$zennenDatashubetu*/'DOGETU') AS EIGYO_DAYS
      ,      SUM(BT64.URIAGE_ZEN_/*$zennenDatashubetu*/'DOGETU') AS URIAGE_ZEN
      ,      SUM(BT64.EIGYO_DAYS_ZEN_/*$zennenDatashubetu*/'DOGETU') AS EIGYO_DAYS_ZEN
      ,      SUM(BT64.KYAKUSU_/*$zennenDatashubetu*/'DOGETU') AS KYAKUSU
      ,      SUM(BT64.KYAKUSU_ZEN_/*$zennenDatashubetu*/'DOGETU') AS KYAKUSU_ZEN
      FROM BM01TENM BM01
      ,    BT64ZGEP BT64
      WHERE BT64.COMPANY_CD = /*companyCd*/'00'
      AND   BT64.EIGYO_DT BETWEEN /*kikanFrom*/'200705' AND /*kikanTo*/'200705'
/*IF "MISE".equals(taishoJoken) */  
      AND   BT64.MISE_CD = /*hyojiTaisho*/'04179'
--ELSE
      AND   BM01.ONER_CD = /*onerCd*/'37006'
/*END*/
      AND   OLDM_FLG <> '1'
      AND   OPEN_KBN = 1
      AND   BM01.COMPANY_CD = BT64.COMPANY_CD
      AND   BM01.MISE_CD = BT64.MISE_CD
      GROUP BY BT64.EIGYO_DT
) BT64 
order by BT64.EIGYO_DT
