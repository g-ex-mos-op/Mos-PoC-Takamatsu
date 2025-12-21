SELECT BD15.COMPANY_CD
,      BD15.TIME_DT
/*IF taishoJoken.equals("MISE") */  
,      TAISHO.TARGET_NAME_KJ
/*END*/
,      TAISHO.TENPO_CNT
,      SUM(BD15.KAZU_00) AS KAZU_00
,      SUM(BD15.URIAGE_00) AS URIAGE_00
,      SUM(BD15.KAZU_01) AS KAZU_01
,      SUM(BD15.URIAGE_01) AS URIAGE_01
,      SUM(BD15.KAZU_02) AS KAZU_02
,      SUM(BD15.URIAGE_02) AS URIAGE_02
,      SUM(BD15.KAZU_03) AS KAZU_03
,      SUM(BD15.URIAGE_03) AS URIAGE_03
,      SUM(BD15.KAZU_04) AS KAZU_04
,      SUM(BD15.URIAGE_04) AS URIAGE_04
,      SUM(BD15.KAZU_05) AS KAZU_05
,      SUM(BD15.URIAGE_05) AS URIAGE_05
,      SUM(BD15.KAZU_06) AS KAZU_06
,      SUM(BD15.URIAGE_06) AS URIAGE_06
,      SUM(BD15.KAZU_07) AS KAZU_07
,      SUM(BD15.URIAGE_07) AS URIAGE_07
,      SUM(BD15.KAZU_08) AS KAZU_08
,      SUM(BD15.URIAGE_08) AS URIAGE_08
,      SUM(BD15.KAZU_09) AS KAZU_09
,      SUM(BD15.URIAGE_09) AS URIAGE_09
,      SUM(BD15.KAZU_10) AS KAZU_10
,      SUM(BD15.URIAGE_10) AS URIAGE_10
,      SUM(BD15.KAZU_11) AS KAZU_11
,      SUM(BD15.URIAGE_11) AS URIAGE_11
,      SUM(BD15.KAZU_12) AS KAZU_12
,      SUM(BD15.URIAGE_12) AS URIAGE_12
,      SUM(BD15.KAZU_13) AS KAZU_13
,      SUM(BD15.URIAGE_13) AS URIAGE_13
,      SUM(BD15.KAZU_14) AS KAZU_14
,      SUM(BD15.URIAGE_14) AS URIAGE_14
,      SUM(BD15.KAZU_15) AS KAZU_15
,      SUM(BD15.URIAGE_15) AS URIAGE_15
,      SUM(BD15.KAZU_16) AS KAZU_16
,      SUM(BD15.URIAGE_16) AS URIAGE_16
,      SUM(BD15.KAZU_17) AS KAZU_17
,      SUM(BD15.URIAGE_17) AS URIAGE_17
,      SUM(BD15.KAZU_18) AS KAZU_18
,      SUM(BD15.URIAGE_18) AS URIAGE_18
,      SUM(BD15.KAZU_19) AS KAZU_19
,      SUM(BD15.URIAGE_19) AS URIAGE_19
,      SUM(BD15.KAZU_20) AS KAZU_20
,      SUM(BD15.URIAGE_20) AS URIAGE_20
,      SUM(BD15.KAZU_21) AS KAZU_21
,      SUM(BD15.URIAGE_21) AS URIAGE_21
,      SUM(BD15.KAZU_22) AS KAZU_22
,      SUM(BD15.URIAGE_22) AS URIAGE_22
,      SUM(BD15.KAZU_23) AS KAZU_23
,      SUM(BD15.URIAGE_23) AS URIAGE_23
,      SUM(BD15.KAZU_OTHER) AS KAZU_OTHER
,      SUM(BD15.URIAGE_OTHER) AS URIAGE_OTHER
,      SUM(BD15.KAZU_KEI) AS KAZU_KEI
,      SUM(BD15.URIAGE_KEI) AS URIAGE_KEI
FROM (
		SELECT BM01.MISE_CD
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
			AND   EIGYO_YM = /*kikanSitei*/'200809' 
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
		GROUP BY BM01.MISE_CD
		,      BM01.MISE_NAME_KJ
	) BM01
,   BD15NJSL/*$targetMonth*/'07' BD15
,   (
	SELECT BD15.COMPANY_CD
/*IF taishoJoken.equals("MISE") */  
	,      BM01.MISE_NAME_KJ AS TARGET_NAME_KJ
--ELSE
	/*IF userTypeCd == "01" */
	,     '‘SŽÐ'   AS TARGET_NAME_KJ
	--ELSE
	,     '‘S“X'   AS TARGET_NAME_KJ
	/*END*/
/*END*/
	,      COUNT(DISTINCT BD15.MISE_CD) AS TENPO_CNT
	FROM (
			SELECT BM01.MISE_CD
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
				AND   EIGYO_YM = /*kikanSitei*/'200809' 
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
			GROUP BY BM01.MISE_CD
			,      BM01.MISE_NAME_KJ
		) BM01
	,   BD15NJSL/*$targetMonth*/'07' BD15
	
	WHERE BD15.COMPANY_CD = /*companyCd*/'00'
	AND   SUBSTR(BD15.TIME_DT, 1,6) =/*kikanSitei*/'200809'
	AND   BD15.MISE_CD = BM01.MISE_CD
	
	GROUP BY BD15.COMPANY_CD
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
WHERE BD15.COMPANY_CD = /*companyCd*/'00'
AND   SUBSTR(BD15.TIME_DT, 1,6) =/*kikanSitei*/'200809'
AND   BD15.MISE_CD = BM01.MISE_CD

GROUP BY BD15.COMPANY_CD
,      TAISHO.TARGET_NAME_KJ
,        TAISHO.TENPO_CNT
,        BD15.TIME_DT
ORDER BY TIME_DT
