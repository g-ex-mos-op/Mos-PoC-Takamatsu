/*IF "MISE".equals(paramsDto.taishoJoken) */
SELECT CODE
,      MISE_CNT_ALL
,      MISE_CNT
,      BM01.MISE_CD AS HT_CD
,      RTRIM(BM01.MISE_NAME_KJ) AS HT_NAME
FROM (
/*END*/
/*IF isCsv */
	/*IF paramsDto.suiiTypeGepo */
SELECT 'GEPO' AS CODE
	--ELSE
SELECT DT AS CODE
    /*END*/
--ELSE 
SELECT CASE WHEN DT IS NULL THEN 'GEPO' ELSE DT END AS CODE
/*END*/
,      COUNT(DISTINCT MISE_CD) MISE_CNT_ALL
,      COUNT(DISTINCT (CASE WHEN OPEN_KBN>0 THEN MISE_CD ELSE null END)) AS MISE_CNT
FROM (
      SELECT DT, COMPANY_CD, MISE_CD , MAX(OPEN_KBN) AS OPEN_KBN 
      FROM (
/*IF "1".equals(siteKbn)==false */
            SELECT BT44.YOSAN_DT AS DT, BT44.COMPANY_CD, BT44.MISE_CD
            ,      BT44.TENPO_SHU , 0 AS OPEN_KBN
            FROM BT44MSJY BT44
            WHERE BT44.YOSAN <> 0
            GROUP BY BT44.YOSAN_DT, BT44.COMPANY_CD, BT44.MISE_CD
            ,        BT44.TENPO_SHU , 0
         UNION ALL
/*END*/
            SELECT SUIIDATA.EIGYO_DT AS DT, SUIIDATA.COMPANY_CD, SUIIDATA.MISE_CD
            ,      BN01.TENPO_SHU, SUIIDATA.OPEN_KBN
            FROM   BD53NGEP SUIIDATA
            ,      (SELECT EIGYO_YM , COMPANY_CD, MISE_CD, KBN1 AS TENPO_SHU 
                    FROM BN01DTEN
                    GROUP BY EIGYO_YM , COMPANY_CD, MISE_CD, KBN1 ) BN01
            WHERE (SUIIDATA.OPEN_KBN = 1 
            /*IF "01".equals(userTypeCd) && "ALL".equals(paramsDto.tenpoShubetu) */
            	OR SUIIDATA.OPEN_KBN_ZEN_DOGETU = 1
            /*END*/
            )
            AND   SUIIDATA.OLDM_FLG <> '1'
            AND   SUIIDATA.EIGYO_DT   = BN01.EIGYO_YM
            AND   SUIIDATA.COMPANY_CD = BN01.COMPANY_CD
            AND   SUIIDATA.MISE_CD    = BN01.MISE_CD 
            GROUP BY SUIIDATA.EIGYO_DT, SUIIDATA.COMPANY_CD, SUIIDATA.MISE_CD, BN01.TENPO_SHU, SUIIDATA.OPEN_KBN
         ) SUB
	/*IF isCsv && "GEPO".equals(paramsDto.focusTab) ==false */
	  WHERE SUB.DT = /*paramsDto.focusTab*/'201001'
	--ELSE
	  WHERE SUB.DT BETWEEN /*paramsDto.kikanSiteiFrom*/'201001' AND /*paramsDto.kikanSiteiTo*/'201012'
	  AND   SUB.DT <= /*appYm*/'201305'
	/*END*/
	/*IF "1".equals(paramsDto.tenpoShubetu) || "3".equals(paramsDto.tenpoShubetu) */
	  AND   SUB.TENPO_SHU = /*paramsDto.tenpoShubetu*/'1'
	/*END*/
	/*IF "2".equals(paramsDto.tenpoShubetu) */
	  AND   SUB.TENPO_SHU IN ('1', '2')
	/*END*/
     AND   EXISTS (SELECT * FROM BM01TENM BM01
                WHERE BM01.COMPANY_CD = /*paramsDto.companyCd*/'00'
/*IF "MISE".equals(paramsDto.taishoJoken) */  
                AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
/*END*/
/*IF "02".equals(userTypeCd) && "ALL".equals(paramsDto.taishoJoken) */
                AND   BM01.ONER_CD = /*paramsDto.hyojiTaisho*/'37006'
/*END*/
/*IF "01".equals(userTypeCd) */
                AND   EXISTS (SELECT * FROM BM10GSIB BM10
                              WHERE  BM10.COMPANY_CD = BM01.COMPANY_CD 
	/*IF "JIGYOU".equals(paramsDto.taishoJoken) || "SLAREA".equals(paramsDto.taishoJoken) || "SIBU".equals(paramsDto.taishoJoken) */  
                              AND   BM10./*$paramsDto.taishoJoken*/'SIBU'_CD = /*paramsDto.hyojiTaisho*/'031'
		/*IF "SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd */
		                      AND   BM01.BLOCK_CD = /*paramsDto.blockCd*/'004'
		/*END*/
	/*END*/  
                              AND BM10.SIBU_CD = BM01.SIBU_CD) 
	/*IF limitFlg */
                AND   EXISTS (SELECT * FROM BM51SVSB AS BM51
                              WHERE BM51.SV_CD = /*userId*/'00000921'
                              AND BM51.COMPANY_CD = BM01.COMPANY_CD
                              AND BM51.SIBU_CD = BM01.SIBU_CD)
	/*END*/
	/*IF "SEGMENT".equals(paramsDto.taishoJoken) */  
                AND   EXISTS (SELECT * FROM BM09GTSG BM09
                             WHERE SEGMENT_TYPE = /*paramsDto.hyojiTaisho*/'M' 
                             AND BM09.GYOTAI_KBN = BM01.GYOTAI_KBN)
	/*END*/
/*END*/
                AND   SUB.COMPANY_CD = BM01.COMPANY_CD
                AND   SUB.MISE_CD = BM01.MISE_CD)              
      GROUP BY DT, COMPANY_CD, MISE_CD
      ) DATAMISE
/*IF isCsv */
	/*IF paramsDto.suiiTypeGepo */
	GROUP BY 'GEPO'
	--ELSE
	GROUP BY DT
	/*END*/
--ELSE
    GROUP BY ROLLUP (DT)
/*END*/


    HAVING COUNT(DISTINCT MISE_CD) > 0
/*IF "MISE".equals(paramsDto.taishoJoken) */
) HIT_DATA
, BM01TENM BM01
WHERE BM01.COMPANY_CD = /*paramsDto.companyCd*/'00'
AND   BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'01776'
/*END*/
