SELECT CASE WHEN TM_ELEM IS NULL THEN 'TOTAL'
/*IF isAnHour */
            WHEN TIME_KBN IS NOT NULL THEN ''
/*END*/
            ELSE 'TIMESLOT'  END AS ROW_TYPE
,      TAISHO.COMPANY_CD
,      TAISHO.TARGET_NAME_KJ
,      TAISHO.TENPO_CNT
/*IF isAnHour */
,      CASE WHEN WEEK_TYPE = '1' THEN (CASE WHEN TIME_KBN IS NOT NULL THEN '3' ELSE WEEK_TYPE END)
            WHEN WEEK_TYPE = '2' THEN (CASE WHEN TIME_KBN IS NOT NULL THEN '4' ELSE WEEK_TYPE END)
            ELSE WEEK_TYPE END AS ROW_RANK
--ELSE
,      WEEK_TYPE AS ROW_RANK
/*END*/
,      WEEK_TYPE
,      WEEK_KBN
,      RTRIM(WEEK_NAME) AS WEEK_NAME
,      CASE WHEN TM_ELEM IS NULL THEN 'ZZ' ELSE TM_ELEM  END AS TM_ELEM
,      SUBSTR(TM_KBN,1,2) AS TM_KBN
,      KYAKUSU
,      MAX(KYAKUSU) OVER(partition by WEEK_TYPE, WEEK_KBN) AS KOUSEIHI_KYAKUSU
,      DECIMAL(CASE WHEN MAX(KYAKUSU) OVER(partition by WEEK_TYPE, WEEK_KBN) = 0 THEN 0.00 
                    ELSE (DOUBLE(KYAKUSU)/DOUBLE(MAX(KYAKUSU) OVER(partition by WEEK_TYPE, WEEK_KBN)))*100+0.005 END, 7,2) AS KYAKUSU_KOUSEI_HI
,      KINGAKU
,      MAX(KINGAKU) OVER(partition by WEEK_TYPE, WEEK_KBN) AS KOUSEIHI_KINGAKU
,      DECIMAL(CASE WHEN MAX(KINGAKU) OVER(partition by WEEK_TYPE, WEEK_KBN) = 0 THEN 0.00
                    ELSE (DOUBLE(KINGAKU)/DOUBLE(MAX(KINGAKU) OVER(partition by WEEK_TYPE, WEEK_KBN)))*100+0.005 END, 7,2) AS KINGAKU_KOUSEI_HI

,      KYAKUSU_ZEN
,      KINGAKU_ZEN
FROM (
SELECT CASE WHEN BD23.COMPANY_CD IS NULL THEN '00' ELSE BD23.COMPANY_CD END AS COMPANY_CD
,      B1470.WEEK_TYPE
,      B1470.WEEK_KBN
,      B1470.WEEK_NAME
,      B1470.TM_ELEM
/*IF isAnHour */
,      B1470.TM_KBN AS TIME_KBN
/*END*/
,      MIN(B1470.TM_KBN) AS TM_KBN
,      SUM(CASE WHEN BD23.COMPANY_CD IS NULL THEN 0 ELSE BD23.KYAKUSU END) AS KYAKUSU
,      SUM(CASE WHEN BD23.COMPANY_CD IS NULL THEN 0 ELSE BD23.KINGAKU END) AS KINGAKU
,      SUM(CASE WHEN ZEN23.COMPANY_CD IS NULL THEN 0 ELSE ZEN23.KYAKUSU END) AS KYAKUSU_ZEN
,      SUM(CASE WHEN ZEN23.COMPANY_CD IS NULL THEN 0 ELSE ZEN23.KINGAKU END) AS KINGAKU_ZEN
FROM (
	SELECT BD14.WEEK_TYPE
	,      BD14.WEEK_KBN
	,      BD14.WEEK_NAME
	,      BM70.TM_ELEM
	,      BM70.TM_KBN
	FROM   (SELECT '1' AS WEEK_TYPE, THREE_KBN AS WEEK_KBN, THREE_NAME AS WEEK_NAME
              FROM BD14ACAL WHERE EIGYO_YM = /*kikanSitei*/'200805'
              GROUP BY '1', THREE_KBN , THREE_NAME 
              UNION ALL 
              SELECT '2' AS WEEK_TYPE,  SEVEN_KBN AS WEEK_KBN, SEVEN_NAME  AS WEEK_NAME
              FROM BD14ACAL 
              WHERE EIGYO_YM = /*kikanSitei*/'200805'
              AND   SEVEN_KBN NOT IN ('6', '7')
              GROUP BY '2' ,  SEVEN_KBN , SEVEN_NAME 
             ) BD14
	,      BM70JTNM BM70
	GROUP BY BD14.WEEK_TYPE
	,      BD14.WEEK_KBN
	,      BD14.WEEK_NAME
	,      BM70.TM_ELEM
	,      BM70.TM_KBN
	) B1470
LEFT JOIN (
	SELECT BD23.COMPANY_CD
	,      BD23.WEEK_TYPE
	,      BD23.WEEK_KBN
	,      BD23.TM_ELEM
	,      BD23.TM_KBN
	,      DECIMAL(SUM(BD23.AVG_KEN)+0.5, 8,0) AS KYAKUSU
	,      DECIMAL(SUM(BD23.AVG_KIN)+0.5, 14,0) AS KINGAKU
	FROM BD23JAVG/*$targetMonth*/'05' BD23
	,    (
		SELECT BM01.COMPANY_CD
		,      BM01.MISE_CD
		,      BM01.MISE_NAME_KJ
		FROM BM01TENM BM01
		/*IF userTypeCd == "02" */
		        ,    BM06UONR BM06
		/*END*/
		/*IF userTypeCd == "03" */
		        ,    BM07UTEN BM07
		/*END*/
		,   (
			SELECT MISE_CD
			FROM BN01DTEN
			WHERE COMPANY_CD = /*companyCd*/'00'
			AND   EIGYO_YM = /*kikanSitei*/'200805' 
		/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
			AND   KBN1 = /*tenpoShubetu*/'1'
		/*END*/
		/*IF tenpoShubetu == "2" */
			AND   KBN1 IN ('1', '2')
		/*END*/
			GROUP BY MISE_CD
		) BN01
		WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
        AND   BM01.SIBU_CD = /*hyojiTaisho*/'031'
 /*IF blockCd != null */
        AND   BM01.BLOCK_CD = /*blockCd*/'004'
 /*END*/
/*END*/
/*IF taishoJoken.equals("MISE") */  
		AND   BM01.MISE_CD = /*hyojiTaisho*/'02001'
/*END*/
/*IF userTypeCd == "01" && limitFlg == true */
		AND    BM01.SIBU_CD IN (
		   SELECT   BM51.SIBU_CD
		   FROM     BM51SVSB BM51
		   WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
		   AND    BM51.SV_CD      = /*userId*/'99990001'
		   GROUP BY BM51.SIBU_CD
		)
/*END*/
		AND   BN01.MISE_CD    = BM01.MISE_CD
	/*IF userTypeCd == "02" */
	    AND   BM06.USER_ID    = /*userId*/'99990002'
	    AND   BM06.COMPANY_CD = BM01.COMPANY_CD
	    AND   BM06.ONER_CD    = BM01.ONER_CD
	/*END*/
	/*IF userTypeCd == "03" */
	    AND   BM07.USER_ID    = /*userId*/'99990003'
	    AND   BM07.COMPANY_CD = BM01.COMPANY_CD
	    AND   BM07.MISE_CD    = BM01.MISE_CD
	/*END*/
		GROUP BY BM01.COMPANY_CD
		,        BM01.MISE_CD
		,        BM01.MISE_NAME_KJ
	) BM01
	WHERE BD23.COMPANY_CD = /*companyCd*/'00'
	AND   BD23.EIGYO_YM = /*kikanSitei*/'200805' 
/*IF taishoJoken.equals("MISE") */  
	AND   BD23.MISE_CD = /*hyojiTaisho*/'02001'
/*END*/
	AND   BD23.COMPANY_CD = BM01.COMPANY_CD
	AND   BD23.MISE_CD    = BM01.MISE_CD
	GROUP BY BD23.COMPANY_CD
	,        BD23.WEEK_TYPE
	,        BD23.WEEK_KBN
	,        BD23.TM_ELEM
	,        BD23.TM_KBN
 	
) BD23 ON ( BD23.WEEK_TYPE= B1470.WEEK_TYPE 
               AND BD23.WEEK_KBN= B1470.WEEK_KBN
               AND BD23.TM_ELEM= B1470.TM_ELEM
               AND BD23.TM_KBN= B1470.TM_KBN
          )
LEFT JOIN (
	SELECT BD23.COMPANY_CD
	,      BD23.WEEK_TYPE
	,      BD23.WEEK_KBN
	,      BD23.TM_ELEM
	,      BD23.TM_KBN
	,      DECIMAL(SUM(BD23.AVG_KEN)+0.5, 8,0) AS KYAKUSU
	,      DECIMAL(SUM(BD23.AVG_KIN)+0.5, 14,0) AS KINGAKU
	FROM BD23JAVG/*$targetMonth*/'05' BD23
	,    (
		SELECT BM01.COMPANY_CD
		,      BM01.MISE_CD
		,      BM01.MISE_NAME_KJ
		FROM BM01TENM BM01
		/*IF userTypeCd == "02" */
		        ,    BM06UONR BM06
		/*END*/
		/*IF userTypeCd == "03" */
		        ,    BM07UTEN BM07
		/*END*/
		,   (
			SELECT MISE_CD
			FROM BN01DTEN
			WHERE COMPANY_CD = /*companyCd*/'00'
			AND   EIGYO_YM = /*kikanSitei*/'200805' 
		/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
			AND   KBN1 = /*tenpoShubetu*/'1'
		/*END*/
		/*IF tenpoShubetu == "2" */
			AND   KBN1 IN ('1', '2')
		/*END*/
			GROUP BY MISE_CD
		) BN01
		WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
        AND   BM01.SIBU_CD = /*hyojiTaisho*/'031'
 /*IF blockCd != null */
        AND   BM01.BLOCK_CD = /*blockCd*/'004'
 /*END*/
/*END*/
/*IF taishoJoken.equals("MISE") */  
		AND   BM01.MISE_CD = /*hyojiTaisho*/'02001'
/*END*/
/*IF userTypeCd == "01" && limitFlg == true */
		AND    BM01.SIBU_CD IN (
		   SELECT   BM51.SIBU_CD
		   FROM     BM51SVSB BM51
		   WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
		   AND    BM51.SV_CD      = /*userId*/'99990001'
		   GROUP BY BM51.SIBU_CD
		)
/*END*/
		AND   BN01.MISE_CD    = BM01.MISE_CD
	/*IF userTypeCd == "02" */
	    AND   BM06.USER_ID    = /*userId*/'99990002'
	    AND   BM06.COMPANY_CD = BM01.COMPANY_CD
	    AND   BM06.ONER_CD    = BM01.ONER_CD
	/*END*/
	/*IF userTypeCd == "03" */
	    AND   BM07.USER_ID    = /*userId*/'99990003'
	    AND   BM07.COMPANY_CD = BM01.COMPANY_CD
	    AND   BM07.MISE_CD    = BM01.MISE_CD
	/*END*/
		GROUP BY BM01.COMPANY_CD
		,        BM01.MISE_CD
		,        BM01.MISE_NAME_KJ
	) BM01
	WHERE BD23.COMPANY_CD = /*companyCd*/'00'
	AND   BD23.EIGYO_YM = /*zennenYM*/'200805' 
/*IF taishoJoken.equals("MISE") */  
	AND   BD23.MISE_CD = /*hyojiTaisho*/'02001'
/*END*/
	AND   BD23.COMPANY_CD = BM01.COMPANY_CD
	AND   BD23.MISE_CD    = BM01.MISE_CD
	GROUP BY BD23.COMPANY_CD
	,        BD23.WEEK_TYPE
	,        BD23.WEEK_KBN
	,        BD23.TM_ELEM
	,        BD23.TM_KBN
 	
) ZEN23 ON ( ZEN23.WEEK_TYPE = B1470.WEEK_TYPE 
         AND ZEN23.WEEK_KBN  = B1470.WEEK_KBN
         AND ZEN23.TM_ELEM   = B1470.TM_ELEM
         AND ZEN23.TM_KBN    = B1470.TM_KBN
          )

GROUP BY ROLLUP((CASE WHEN BD23.COMPANY_CD IS NULL THEN '00' ELSE BD23.COMPANY_CD END)
	,    (B1470.WEEK_TYPE, B1470.WEEK_KBN, B1470.WEEK_NAME)
    ,    (B1470.WEEK_TYPE, B1470.WEEK_KBN, B1470.WEEK_NAME, B1470.TM_ELEM)
/*IF isAnHour */
    ,    (B1470.WEEK_TYPE, B1470.WEEK_KBN, B1470.WEEK_NAME, B1470.TM_KBN)
/*END*/

)
HAVING B1470.WEEK_TYPE IS NOT NULL
)SUB
,(
	SELECT BD23.COMPANY_CD
/*IF taishoJoken.equals("MISE") */  
    ,      BM01.MISE_NAME_KJ AS TARGET_NAME_KJ
--ELSE
	/*IF userTypeCd == "01" */
	,     '‘SŽÐ' AS TARGET_NAME_KJ
	--ELSE
	,     '‘S“X' AS TARGET_NAME_KJ
	/*END*/
/*END*/

	,      COUNT(DISTINCT BD23.MISE_CD) AS TENPO_CNT
	FROM BD23JAVG/*$targetMonth*/ BD23
	,    (
		SELECT BM01.COMPANY_CD
		,      BM01.MISE_CD
		,      BM01.MISE_NAME_KJ
		FROM BM01TENM BM01
		/*IF userTypeCd == "02" */
		        ,    BM06UONR BM06
		/*END*/
		/*IF userTypeCd == "03" */
		        ,    BM07UTEN BM07
		/*END*/
		,   (
			SELECT MISE_CD
			FROM BN01DTEN
			WHERE COMPANY_CD = /*companyCd*/'00'
			AND   EIGYO_YM = /*kikanSitei*/'200805' 
		/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
			AND   KBN1 = /*tenpoShubetu*/'1'
		/*END*/
		/*IF tenpoShubetu == "2" */
			AND   KBN1 IN ('1', '2')
		/*END*/
			GROUP BY MISE_CD
		) BN01
		WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
        AND   BM01.SIBU_CD = /*hyojiTaisho*/'031'
 /*IF blockCd != null */
        AND   BM01.BLOCK_CD = /*blockCd*/'004'
 /*END*/
/*END*/
/*IF taishoJoken.equals("MISE") */  
		AND   BM01.MISE_CD = /*hyojiTaisho*/'02001'
/*END*/
/*IF userTypeCd == "01" && limitFlg == true */
		AND    BM01.SIBU_CD IN (
		   SELECT   BM51.SIBU_CD
		   FROM     BM51SVSB BM51
		   WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
		   AND    BM51.SV_CD      = /*userId*/'99990001'
		   GROUP BY BM51.SIBU_CD
		)
/*END*/
		AND   BN01.MISE_CD    = BM01.MISE_CD
	/*IF userTypeCd == "02" */
	    AND   BM06.USER_ID    = /*userId*/'99990002'
	    AND   BM06.COMPANY_CD = BM01.COMPANY_CD
	    AND   BM06.ONER_CD    = BM01.ONER_CD
	/*END*/
	/*IF userTypeCd == "03" */
	    AND   BM07.USER_ID    = /*userId*/'99990003'
	    AND   BM07.COMPANY_CD = BM01.COMPANY_CD
	    AND   BM07.MISE_CD    = BM01.MISE_CD
	/*END*/
		GROUP BY BM01.COMPANY_CD
		,        BM01.MISE_CD
		,        BM01.MISE_NAME_KJ
	) BM01
	WHERE BD23.COMPANY_CD = '00'
	AND   BD23.EIGYO_YM = /*kikanSitei*/'200805' 
	AND   BD23.COMPANY_CD = BM01.COMPANY_CD
	AND   BD23.MISE_CD    = BM01.MISE_CD
	GROUP BY BD23.COMPANY_CD
/*IF taishoJoken.equals("MISE") */  
    ,      BM01.MISE_NAME_KJ
--ELSE
	/*IF userTypeCd == "01" */
	,     '‘SŽÐ' 
	--ELSE
	,     '‘S“X' 
	/*END*/
/*END*/
) TAISHO

ORDER BY COMPANY_CD
,        ROW_RANK
,        WEEK_KBN
,        TM_ELEM
,        TM_KBN