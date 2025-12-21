select BT4YOSAN.YOSAN_DT AS EIGYO_DT
,      BT4YOSAN.YOSAN AS YOSAN
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.URIAGE END) AS URIAGE_ZEN
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.EIGYO_DAYS END) AS EIGYO_DAYS_ZEN
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.KYAKUSU END) AS KYAKUSU_ZEN
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.ISSUE_CNT END) AS ZEN_ISSUE_CNT
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.CHARGE_KIN END) AS ZEN_CHARGE_KIN
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.CHARGE_KEN END) AS ZEN_CHARGE_KEN
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.KESSAI_KIN END) AS ZEN_KESSAI_KIN
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.KESSAI_KEN END) AS ZEN_KESSAI_KEN
/*IF paramsDto.selectHosei */
,      (CASE WHEN LAST_BD36.URIAGE IS NULL THEN 0 ELSE LAST_BD36.URIAGE END) AS NET_URIAGE_ZEN
,      (CASE WHEN LAST_BD36.EIGYO_DAYS IS NULL THEN 0 ELSE LAST_BD36.EIGYO_DAYS END) AS NET_EIGYO_DAYS_ZEN
,      (CASE WHEN LAST_BD36.KYAKUSU IS NULL THEN 0 ELSE LAST_BD36.KYAKUSU END) AS NET_KYAKUSU_ZEN
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.ISSUE_CNT END) AS NET_ZEN_ISSUE_CNT
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.CHARGE_KIN END) AS NET_ZEN_CHARGE_KIN
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.CHARGE_KEN END) AS NET_ZEN_CHARGE_KEN
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.KESSAI_KIN END) AS NET_ZEN_KESSAI_KIN
,      (CASE WHEN LAST_BD36.EIGYO_DT IS NULL THEN 0 ELSE LAST_BD36.KESSAI_KEN END) AS NET_ZEN_KESSAI_KEN
/*END*/
from (
      SELECT BT44.YOSAN_DT
      ,      SUM(BT44.YOSAN) AS YOSAN
      FROM (SELECT BM01.SIBU_CD
/*IF ("SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd) || "MISE".equals(paramsDto.taishoJoken) */
            ,      BM01.MISE_CD 
/*END*/
            FROM BM01TENM BM01
            WHERE BM01.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF paramsDto.taishoJoken.equals("MISE") */  
            AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
/*END*/
/*IF "01".equals(birdUserInfo.mstUser.userTypeCd) */
    /*IF "JIGYOU".equals(paramsDto.taishoJoken) || "SLAREA".equals(paramsDto.taishoJoken) || "SIBU".equals(paramsDto.taishoJoken) */  
            AND   EXISTS (SELECT * FROM BM10GSIB BM10
                          WHERE BM10.COMPANY_CD = /*paramsDto.companyCd*/'00'
                          AND   BM10./*$paramsDto.taishoJoken*/'SIBU'_CD = /*paramsDto.hyojiTaisho*/'031'
        /*IF "SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd */  
                          AND   BM01.BLOCK_CD   = /*paramsDto.blockCd*/'004'
        /*END*/         
                          AND   BM10.SIBU_CD    = BM01.SIBU_CD) 
    /*END*/
    /*IF "SEGMENT".equals(paramsDto.taishoJoken) */  
            AND   EXISTS (SELECT * FROM BM09GTSG BM09
                          WHERE BM09.SEGMENT_TYPE = /*paramsDto.hyojiTaisho*/'M' 
                          AND   BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
    /*END*/
    /*IF birdUserInfo.limit*/
            AND   EXISTS (SELECT * FROM BM51SVSB AS BM51
                          WHERE BM51.COMPANY_CD = /*paramsDto.companyCd*/'00'
                          AND   BM51.SV_CD = /*birdUserInfo.userID*/'00000921'
                          AND   BM51.SIBU_CD = BM01.SIBU_CD)
    /*END*/
/*END*/
/*IF (("SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd) || "MISE".equals(paramsDto.taishoJoken))==false */
        GROUP BY BM01.SIBU_CD
/*END*/
     ) BM01
     ,     BT44MSJY BT44
      WHERE BT44.YOSAN_DT BETWEEN /*futureFrom*/'' AND /*futureTo*/
      AND   BT44.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "SIBU".equals(paramsDto.taishoJoken) */  
      AND   BT44.SIBU_CD = /*paramsDto.hyojiTaisho*/'031'
/*END*/
/*IF "MISE".equals(paramsDto.taishoJoken) */  
      AND   BT44.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
/*END*/
/*IF "1".equals(paramsDto.tenpoShubetu) || "3".equals(paramsDto.tenpoShubetu) */
      AND   BT44.TENPO_SHU = /*paramsDto.tenpoShubetu*/'1'
/*END*/
/*IF "2".equals(paramsDto.tenpoShubetu) */
      AND   BT44.TENPO_SHU IN ('1', '2')
/*END*/
      AND   BT44.SIBU_CD = BM01.SIBU_CD
/*IF ("SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd) || "MISE".equals(paramsDto.taishoJoken) */
      AND   BT44.MISE_CD = BM01.MISE_CD
/*END*/
      group by BT44.YOSAN_DT
      ) BT4YOSAN
LEFT JOIN    ( 
      SELECT SUBSTR(CHAR(DECIMAL(DECIMAL(SUBSTR(SUB.EIGYO_DT, 1,6))+100, 6,0)),1,6) AS EIGYO_DT
      ,      SUM(SUB.DOGETU_URIAGE) AS URIAGE
      ,      SUM(SUB.DOGETU_ONER_YOSAN) AS ONER_YOSAN
      ,      SUM(SUB.DOGETU_EIGYO_DAYS) AS EIGYO_DAYS
      ,      SUM(SUB.DOGETU_KYAKUSU) AS KYAKUSU
      ,      SUM(SUB.DOGETU_CHARGE_KIN_CANCEL) AS CHARGE_KIN_CANCEL
      ,      SUM(SUB.DOGETU_CHARGE_KEN_CANCEL) AS CHARGE_KEN_CANCEL
      ,      SUM(SUB.DOGETU_USE_KIN_CANCEL) AS USE_KIN_CANCEL
      ,      SUM(SUB.DOGETU_USE_KEN_CANCEL) AS USE_KEN_CANCEL
      ,      SUM(SUB.DOGETU_BONUS_V_ISSUE) AS BONUS_V_ISSUE
      ,      SUM(SUB.DOGETU_BONUS_V_USE) AS BONUS_V_USE
      ,      SUM(SUB.DOGETU_COUPON_V_ISSUE) AS COUPON_V_ISSUE
      ,      SUM(SUB.DOGETU_COUPON_V_USE) AS COUPON_V_USE
      ,      SUM(SUB.DOGETU_ISSUE_CNT) AS ISSUE_CNT
      ,      SUM(SUB.DOGETU_CHARGE_KIN) AS CHARGE_KIN
      ,      SUM(SUB.DOGETU_CHARGE_KEN) AS CHARGE_KEN
      ,      SUM(SUB.DOGETU_KESSAI_KIN) AS KESSAI_KIN
      ,      SUM(SUB.DOGETU_KESSAI_KEN) AS KESSAI_KEN

      FROM (
            SELECT SUBBD36.*
            FROM (
               SELECT EIGYO_YM, MISE_CD
               FROM BN01DTEN 
               WHERE  COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF paramsDto.tenpoShubetu == "1" || paramsDto.tenpoShubetu == "3"*/
               AND   KBN1 = /*paramsDto.tenpoShubetu*/''
/*END*/
/*IF paramsDto.tenpoShubetu == "2" */
               AND   KBN1 IN ('1', '2')
/*END*/
               AND    EIGYO_YM  BETWEEN /*kijunYm*/'' AND /*kijunYm*/''
               GROUP BY EIGYO_YM, MISE_CD
            ) BN01
            ,   BD36ZGMC SUBBD36
            WHERE SUBBD36.EIGYO_DT BETWEEN /*zennenFrom*/'' AND /*zennenTo*/''
            AND   SUBBD36.COMPANY_CD = /*paramsDto.companyCd*/'00'
            AND   SUBBD36.OLDM_FLG = '0'
/*IF paramsDto.tenpoShubetu != "ALL" */
            AND   SUBBD36.OPEN_KBN = 1
/*END*/
            AND   SUBBD36.MISE_CD  = BN01.MISE_CD
            AND   EXISTS (
                  SELECT * FROM BM01TENM BM01
                  WHERE BM01.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF paramsDto.taishoJoken.equals("MISE") */  
                  AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
/*END*/
/*IF "01".equals(birdUserInfo.mstUser.userTypeCd) */
	/*IF "JIGYOU".equals(paramsDto.taishoJoken) || "SLAREA".equals(paramsDto.taishoJoken) || "SIBU".equals(paramsDto.taishoJoken) */  
                  AND   EXISTS (SELECT * FROM BM10GSIB BM10
		                WHERE BM10.COMPANY_CD = /*paramsDto.companyCd*/'00'
		                AND   BM10./*$paramsDto.taishoJoken*/'SIBU'_CD = /*paramsDto.hyojiTaisho*/'031'
		/*IF "SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd */  
		                AND   BM01.BLOCK_CD = /*paramsDto.blockCd*/'004'
		/*END*/
		                AND   BM10.SIBU_CD    = BM01.SIBU_CD) 
	/*END*/         
	/*IF "SEGMENT".equals(paramsDto.taishoJoken) */  
                  AND   EXISTS (SELECT * FROM BM09GTSG BM09
		                WHERE SEGMENT_TYPE = /*paramsDto.hyojiTaisho*/'M' 
		                AND   BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
	/*END*/
	/*IF birdUserInfo.limit */
                  AND   EXISTS (SELECT * FROM BM51SVSB AS BM51
		                WHERE BM51.COMPANY_CD = /*paramsDto.companyCd*/'00'
                        AND   BM51.SV_CD      = /*birdUserInfo.userID*/'00000921'
		                AND   BM51.SIBU_CD    = BM01.SIBU_CD)
	/*END*/
                  AND   SUBBD36.MISE_CD  = BM01.MISE_CD) 
	/*IF "ALL".equals(paramsDto.taishoJoken)*/
        UNION ALL
             SELECT * FROM BD36ZGMC
             WHERE EIGYO_DT BETWEEN /*zennenFrom*/'' AND /*zennenTo*/''
             AND   COMPANY_CD = /*paramsDto.companyCd*/'00'
             AND   HONBU_KBN = '1'
	/*END*/
/*END*/
      ) SUB 
      GROUP BY SUBSTR(CHAR(DECIMAL(DECIMAL(SUBSTR(SUB.EIGYO_DT, 1,6))+100, 6,0)),1,6)
) LAST_BD36
ON (LAST_BD36.EIGYO_DT    = BT4YOSAN.YOSAN_DT )
order by BT4YOSAN.YOSAN_DT
