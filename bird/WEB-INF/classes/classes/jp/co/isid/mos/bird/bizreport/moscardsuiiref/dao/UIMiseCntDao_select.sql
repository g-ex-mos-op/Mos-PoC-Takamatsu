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
            SELECT BT44.YOSAN_DT AS DT, BT44.COMPANY_CD, BT44.MISE_CD, 0 AS OPEN_KBN
            FROM   BT44MSJY BT44
            WHERE  BT44.YOSAN <> 0
        /*IF paramsDto.tenpoShubetu == "1" || paramsDto.tenpoShubetu == "3"*/
            AND    BT44.TENPO_SHU = /*paramsDto.tenpoShubetu*/'1'
        /*END*/
        /*IF paramsDto.tenpoShubetu == "2" */
            AND    BT44.TENPO_SHU IN ('1', '2')
        /*END*/
            GROUP BY BT44.YOSAN_DT, BT44.COMPANY_CD, BT44.MISE_CD, 0
         UNION ALL
/*END*/
            SELECT BD36.EIGYO_DT AS DT, BD36.COMPANY_CD, BD36.MISE_CD, BD36.OPEN_KBN
            FROM   BD36ZGMC BD36
            WHERE BD36.OPEN_KBN =1
            AND   BD36.OLDM_FLG = '0'
/*IF "ALL".equals(paramsDto.tenpoShubetu) ==false */
            AND   EXISTS (
                SELECT * FROM BN01DTEN BN01
                WHERE BN01.COMPANY_CD = /*paramsDto.companyCd*/'00'
		/*IF paramsDto.tenpoShubetu == "2" */
                AND   BN01.KBN1 IN ('1', '2')
		--ELSE
                AND   BN01.KBN1 = /*paramsDto.tenpoShubetu*/'1'
		/*END*/
                AND   BD36.EIGYO_DT   = BN01.EIGYO_YM
                AND   BD36.MISE_CD    = BN01.MISE_CD 
            )
/*END*/
            GROUP BY BD36.EIGYO_DT, BD36.COMPANY_CD, BD36.MISE_CD, BD36.OPEN_KBN
            
/*IF "01".equals(userTypeCd) && "ALL".equals(paramsDto.tenpoShubetu)  */
		 UNION ALL 
	          SELECT SUBSTR(BM45.EIGYO_DT, 1, 6) AS DT
	          ,      BM45.COMPANY_CD, BM45.MISE_CD, BM45.OPEN_KBN
	          FROM   BM45ZDAY BM45
        /*IF isCsv && "GEPO".equals(paramsDto.focusTab) ==false */
	          WHERE SUBSTR(BM45.EIGYO_DT, 1, 6) = /*paramsDto.focusTab*/'201303'
        --ELSE
	          WHERE SUBSTR(BM45.EIGYO_DT, 1, 6) BETWEEN /*paramsDto.kikanSiteiFrom*/'201203' AND /*paramsDto.kikanSiteiTo*/'201304'
        /*END*/
	          AND   BM45.COMPANY_CD = /*paramsDto.companyCd*/'00'
	          AND   BM45.OPEN_KBN =0 AND OP_KBN_ZEN_DOGETU =1
	          AND   BM45.OLDM_FLG = '0'
/*END*/
         ) SUB
/*IF isCsv && "GEPO".equals(paramsDto.focusTab) ==false */
      WHERE SUB.DT = /*paramsDto.focusTab*/'201303'
--ELSE
      WHERE SUB.DT BETWEEN /*paramsDto.kikanSiteiFrom*/'201203' AND /*paramsDto.kikanSiteiTo*/'201304'
	  AND   SUB.DT <= /*appYM*/'201305'
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
	/*IF "JIGYOU".equals(paramsDto.taishoJoken) || "SLAREA".equals(paramsDto.taishoJoken) || "SIBU".equals(paramsDto.taishoJoken) */  
                AND   EXISTS (SELECT * FROM BM10GSIB BM10
                              WHERE  BM10.COMPANY_CD = /*paramsDto.companyCd*/'00'
                              AND   BM10./*$paramsDto.taishoJoken*/'SIBU'_CD = /*paramsDto.hyojiTaisho*/'031'
		/*IF "SIBU".equals(paramsDto.taishoJoken) && paramsDto.existsBlockCd */
		                      AND   BM01.BLOCK_CD = /*paramsDto.blockCd*/'004'
		/*END*/
                              AND BM10.SIBU_CD = BM01.SIBU_CD) 
	/*END*/  
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
/*IF "01".equals(userTypeCd) && "ALL".equals(paramsDto.taishoJoken)*/
    UNION ALL
        SELECT BD36.EIGYO_DT AS DT, BD36.COMPANY_CD, BD36.MISE_CD, BD36.OPEN_KBN 
        FROM   BD36ZGMC BD36
	/*IF isCsv */
	    WHERE  BD36.EIGYO_DT = /*paramsDto.focusTab*/'201303'
	--ELSE
	    WHERE  BD36.EIGYO_DT BETWEEN /*paramsDto.kikanSiteiFrom*/'201205' AND /*paramsDto.kikanSiteiTo*/'201304'
	    AND    BD36.EIGYO_DT <= /*appYM*/'201305'
	/*END*/
        AND    BD36.COMPANY_CD = /*paramsDto.companyCd*/'00'
	    AND    BD36.HONBU_KBN = '1'
        GROUP BY BD36.EIGYO_DT, BD36.COMPANY_CD, BD36.MISE_CD, BD36.OPEN_KBN 
/*END*/
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
