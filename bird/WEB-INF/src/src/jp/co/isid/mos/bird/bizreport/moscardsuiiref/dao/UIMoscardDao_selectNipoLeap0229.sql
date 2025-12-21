select '' AS EIGYO_DT 
,      (CASE WHEN BD35.EIGYO_DT IS NULL THEN 0 ELSE BD35.URIAGE_ZEN END)   AS URIAGE_ZEN
,      (CASE WHEN BD35.EIGYO_DT IS NULL THEN 0 ELSE BD35.KYAKUSU_ZEN END)  AS KYAKUSU_ZEN
,      (CASE WHEN BD35.EIGYO_DT IS NULL THEN 0 ELSE BD35.EIGYO_DAYS_ZEN END) AS EIGYO_DAYS_ZEN
,      (CASE WHEN BD35.EIGYO_DT IS NULL THEN 0 ELSE BD35.TENKO_KBN_ZEN END)  AS TENKO_KBN_ZEN
,      (CASE WHEN BD35.EIGYO_DT IS NULL THEN 0 ELSE BD35.ZEN_ISSUE_CNT END)  AS ZEN_ISSUE_CNT
,      (CASE WHEN BD35.EIGYO_DT IS NULL THEN 0 ELSE BD35.ZEN_CHARGE_KIN END) AS ZEN_CHARGE_KIN
,      (CASE WHEN BD35.EIGYO_DT IS NULL THEN 0 ELSE BD35.ZEN_CHARGE_KEN END) AS ZEN_CHARGE_KEN
,      (CASE WHEN BD35.EIGYO_DT IS NULL THEN 0 ELSE BD35.ZEN_KESSAI_KIN END) AS ZEN_KESSAI_KIN
,      (CASE WHEN BD35.EIGYO_DT IS NULL THEN 0 ELSE BD35.ZEN_KESSAI_KEN END) AS ZEN_KESSAI_KEN
FROM ( 
      SELECT SUII0229.EIGYO_DT
      ,      SUM(SUII0229.DOGETU_ZEN_URIAGE)     AS URIAGE_ZEN
      ,      SUM(SUII0229.DOGETU_ZEN_EIGYO_DAYS) AS EIGYO_DAYS_ZEN
      ,      MAX(SUII0229.DOGETU_ZEN_TENKO_KBN)  AS TENKO_KBN_ZEN
      ,      SUM(SUII0229.DOGETU_ZEN_KYAKUSU)    AS KYAKUSU_ZEN
      ,      SUM(SUII0229.DOGETU_ZEN_ISSUE_CNT)    AS ZEN_ISSUE_CNT
      ,      SUM(SUII0229.DOGETU_ZEN_CHARGE_KIN)    AS ZEN_CHARGE_KIN
      ,      SUM(SUII0229.DOGETU_ZEN_CHARGE_KEN)    AS ZEN_CHARGE_KEN			
      ,      SUM(SUII0229.DOGETU_ZEN_KESSAI_KIN)    AS ZEN_KESSAI_KIN
      ,      SUM(SUII0229.DOGETU_ZEN_KESSAI_KEN)    AS ZEN_KESSAI_KEN
      FROM (SELECT MISE_CD FROM BM01TENM BM01
            WHERE BM01.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "MISE".equals(paramsDto.taishoJoken) */  
            AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
--ELSE
	/*IF "02".equals(birdUserInfo.mstUser.userTypeCd) */
            AND   BM01.ONER_CD = /*paramsDto.hyojiTaisho*/'01776'
	/*END*/
/*END*/
/*IF "01".equals(birdUserInfo.mstUser.userTypeCd) */
    /*IF "JIGYOU".equals(paramsDto.taishoJoken) || "SLAREA".equals(paramsDto.taishoJoken) || "SIBU".equals(paramsDto.taishoJoken) */  
            AND   EXISTS (
                     SELECT * FROM BM10GSIB BM10
                     WHERE BM10.COMPANY_CD = /*paramsDto.companyCd*/'00'
                     AND   BM10./*$paramsDto.taishoJoken*/'SIBU'_CD = /*paramsDto.hyojiTaisho*/'031'
        /*IF "SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd */  
                     AND   BM01.BLOCK_CD = /*paramsDto.blockCd*/'004'
        /*END*/         
                     AND   BM10.SIBU_CD    = BM01.SIBU_CD) 
    /*END*/
    /*IF "SEGMENT".equals(paramsDto.taishoJoken) */  
            AND   EXISTS (
                     SELECT * FROM BM09GTSG BM09
                     WHERE BM09.SEGMENT_TYPE = /*paramsDto.hyojiTaisho*/'M' 
                     AND   BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
    /*END*/
    /*IF birdUserInfo.limit*/
            AND   EXISTS (
                     SELECT * FROM BM51SVSB AS BM51
                     WHERE BM51.COMPANY_CD = /*paramsDto.companyCd*/'00'
                     AND   BM51.SV_CD = /*birdUserInfo.userID*/'00000921'
                     AND   BM51.SIBU_CD    = BM01.SIBU_CD)
    /*END*/
    /*IF "ALL".equals(paramsDto.taishoJoken)*/
            UNION ALL 
            SELECT CAST('00000' AS CHAR(5)) AS MISE_CD FROM BM01TENM
    /*END*/
/*END*/
            ) BM01 
      ,   (SELECT CASE WHEN SUBSTR(BD35.EIGYO_DT, 7,2)='99'
                           THEN SUBSTR(BD35.EIGYO_DT, 1,6) || '28'
                       WHEN SUBSTR(BD35.EIGYO_DT, 7,2)='28'
                           THEN SUBSTR(BD35.EIGYO_DT, 1,6) || '00'
                       ELSE BD35.EIGYO_DT END AS BN01_EIGYO_DT
           ,      BD35.EIGYO_DT
           ,      BD35.COMPANY_CD
           ,      BD35.MISE_CD
           ,      BD35.ZENNEN_DT
/*IF "01".equals(birdUserInfo.mstUser.userTypeCd) && "ALL"== paramsDto.tenpoShubetu */
           ,      MAX(CASE WHEN BD35.OPEN_KBN=1 THEN BD35.OPEN_KBN
                           ELSE BD35.DOGETU_ZEN_OPEN_KBN END) 
--ELSE
           ,      MAX(BD35.OPEN_KBN)
/*END*/
	                  OVER(partition by SUBSTR(BD35.EIGYO_DT, 1,6),  BD35.MISE_CD) OPEN_KBN
           ,      BD35.DOGETU_ZEN_URIAGE
           ,      BD35.DOGETU_ZEN_KYAKUSU
           ,      BD35.DOGETU_ZEN_EIGYO_DAYS
           ,      BD35.DOGETU_ZEN_TENKO_KBN
           ,      BD35.DOGETU_ZEN_OPEN_KBN
           ,      BD35.DOGETU_ZEN_ISSUE_CNT
           ,      BD35.DOGETU_ZEN_CHARGE_KIN
           ,      BD35.DOGETU_ZEN_CHARGE_KEN		
           ,      BD35.DOGETU_ZEN_KESSAI_KIN
           ,      BD35.DOGETU_ZEN_KESSAI_KEN
           FROM BD35ZNMC BD35
           WHERE SUBSTR(BD35.EIGYO_DT, 1,6) = /*paramsDto.focusTab*/
           AND   BD35.EIGYO_DT < /*birdDateInfo.sysDate*/'20090302'
           AND   SUBSTR(EIGYO_DT, 5,4) IN ('0228','0299')
           AND   BD35.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "MISE".equals(paramsDto.taishoJoken) */  
           AND   BD35.MISE_CD = /*paramsDto.hyojiTaisho*/'02001'
/*END*/
           AND   BD35.OLDM_FLG = '0'
           ) SUII0229
      WHERE SUBSTR(SUII0229.BN01_EIGYO_DT, 5,4) = '0228'
      AND   SUII0229.OPEN_KBN = 1
/*IF "ALL".equals(paramsDto.tenpoShubetu) ==false */
      AND   (EXISTS (
         SELECT * FROM BN01DTEN BN01
         WHERE BN01.COMPANY_CD = /*paramsDto.companyCd*/'00'
		/*IF paramsDto.tenpoShubetu == "2" */
         AND   BN01.KBN1 IN ('1', '2')
		--ELSE
         AND   BN01.KBN1 = /*paramsDto.tenpoShubetu*/'1'
		/*END*/
         AND   SUII0229.EIGYO_DT   = BN01.EIGYO_DT
         AND   SUII0229.MISE_CD    = BN01.MISE_CD 
	  )
	/*IF "ALL".equals(paramsDto.taishoJoken) */
		OR SUII0229.MISE_CD = '00000'
	/*END*/
	)
/*END*/
      AND   SUII0229.MISE_CD    = BM01.MISE_CD
      GROUP BY SUII0229.EIGYO_DT
) BD35
