select '' AS EIGYO_DT 
,      (CASE WHEN BD54.EIGYO_DT IS NULL THEN 0 ELSE BD54.URIAGE_ZEN END)   AS URIAGE_ZEN
,      (CASE WHEN BD54.EIGYO_DT IS NULL THEN 0 ELSE BD54.KYAKUSU_ZEN END)  AS KYAKUSU_ZEN
,      (CASE WHEN BD54.EIGYO_DT IS NULL THEN 0 ELSE BD54.TENKO_KBN_ZEN END) AS TENKO_KBN_ZEN
,      (CASE WHEN BD54.EIGYO_DT IS NULL THEN 0 ELSE BD54.URIAGE_NSUM_ZEN END)  AS URIAGE_NSUM_ZEN
,      (CASE WHEN BD54.EIGYO_DT IS NULL THEN 0 ELSE BD54.KYAKUSU_NSUM_ZEN END)  AS KYAKUSU_NSUM_ZEN
,      (CASE WHEN BD54.EIGYO_DT IS NULL THEN 0 ELSE BD54.URIAGE_NTAKE_ZEN END) AS URIAGE_NTAKE_ZEN
,      (CASE WHEN BD54.EIGYO_DT IS NULL THEN 0 ELSE BD54.KYAKUSU_NTAKE_ZEN END) AS KYAKUSU_NTAKE_ZEN
,      (CASE WHEN BD54.EIGYO_DT IS NULL THEN 0 ELSE BD54.URIAGE_NTAKU_ZEN END) AS URIAGE_NTAKU_ZEN
,      (CASE WHEN BD54.EIGYO_DT IS NULL THEN 0 ELSE BD54.KYAKUSU_NTAKU_ZEN END) AS KYAKUSU_NTAKU_ZEN
FROM ( 
      SELECT SUII0229.EIGYO_DT
      ,      SUM(SUII0229.URIAGE_ZEN_DOGETU)  AS URIAGE_ZEN
      ,      SUM(SUII0229.KYAKUSU_ZEN_DOGETU) AS KYAKUSU_ZEN
      ,      MAX(SUII0229.TENKO_KBN_ZEN_DOGETU) AS TENKO_KBN_ZEN
      ,      SUM(SUII0229.URIAGE_ZEN_DOGETU_NSUM) AS URIAGE_NSUM_ZEN
      ,      SUM(SUII0229.KYAKUSU_ZEN_DOGETU_NSUM) AS KYAKUSU_NSUM_ZEN
      ,      SUM(SUII0229.URIAGE_ZEN_DOGETU_NTAKE) AS URIAGE_NTAKE_ZEN
      ,      SUM(SUII0229.KYAKUSU_ZEN_DOGETU_NTAKE) AS KYAKUSU_NTAKE_ZEN			
      ,      SUM(SUII0229.URIAGE_ZEN_DOGETU_NTAKU)  AS URIAGE_NTAKU_ZEN
      ,      SUM(SUII0229.KYAKUSU_ZEN_DOGETU_NTAKU) AS KYAKUSU_NTAKU_ZEN
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
      ,   (SELECT CASE WHEN SUBSTR(BD54.EIGYO_DT, 7,2)='99'
                           THEN SUBSTR(BD54.EIGYO_DT, 1,6) || '28'
                       WHEN SUBSTR(BD54.EIGYO_DT, 7,2)='28'
                           THEN SUBSTR(BD54.EIGYO_DT, 1,6) || '00'
                       ELSE BD54.EIGYO_DT END AS BN01_EIGYO_DT
           ,      BD54.EIGYO_DT
           ,      BD54.COMPANY_CD
           ,      BD54.MISE_CD
           ,      BD54.ZENNEN_DT
/*IF "01".equals(birdUserInfo.mstUser.userTypeCd) && "ALL"== paramsDto.tenpoShubetu */
           ,      MAX(CASE WHEN BD54.OPEN_KBN=1 THEN BD54.OPEN_KBN
                           ELSE BD54.OPEN_KBN_ZEN_DOGETU END) 
--ELSE
           ,      MAX(BD54.OPEN_KBN)
/*END*/
	                  OVER(partition by SUBSTR(BD54.EIGYO_DT, 1,6),  BD54.MISE_CD) OPEN_KBN
           ,      BD54.URIAGE_ZEN_DOGETU
           ,      BD54.KYAKUSU_ZEN_DOGETU
           ,      BD54.TENKO_KBN_ZEN_DOGETU
           ,      BD54.URIAGE_ZEN_DOGETU_NSUM
           ,      BD54.KYAKUSU_ZEN_DOGETU_NSUM
           ,      BD54.URIAGE_ZEN_DOGETU_NTAKE
           ,      BD54.KYAKUSU_ZEN_DOGETU_NTAKE
           ,      BD54.URIAGE_ZEN_DOGETU_NTAKU
           ,      BD54.KYAKUSU_ZEN_DOGETU_NTAKU		
           FROM BD54NNIP BD54
           WHERE SUBSTR(BD54.EIGYO_DT, 1,6) = /*paramsDto.focusTab*/
           AND   BD54.EIGYO_DT < /*birdDateInfo.sysDate*/'20090302'
           AND   SUBSTR(EIGYO_DT, 5,4) IN ('0228','0299')
           AND   BD54.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "MISE".equals(paramsDto.taishoJoken) */  
           AND   BD54.MISE_CD = /*paramsDto.hyojiTaisho*/'02001'
/*END*/
           AND   BD54.OLDM_FLG = '0'
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
) BD54
