		SELECT (CASE WHEN PC10.MENU_BUNRUI IS NULL THEN 1 ELSE 2 END) AS ROW_RANK
		,      (CASE WHEN BD11.MENU_CD IS NULL THEN 1 ELSE 2 END) AS SMENU_ROW_NO
		,      BD11.COMPANY_CD
/*IF taishoJoken.equals("ALL") */
		,     '‘SŽÐ' AS TARGET_NAME_KJ
/*END*/
/*IF taishoJoken.equals("SIBU") */  
		,      'Žx•”' AS TARGET_NAME_KJ
/*END*/
		,      PC10.MENU_BUNRUI
		,      CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD11.MENU_CD ELSE BM62.SUM_MENU_CD END AS SUM_MENU_CD
		,      BD11.MENU_CD
		,      COUNT(DISTINCT BD11.MISE_CD) AS HANBAI_TENPO_CNT 
		FROM   BD11NMRI BD11
		LEFT JOIN BM62SYMM BM62 ON (BD11.MENU_CD = BM62.MENU_CD)
		,    PC10SMNU PC10
		,    (
			SELECT BM01.MISE_CD
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
				AND   KBN1 = /*tenpoShubetu*/''
		/*END*/
		/*IF tenpoShubetu == "2" */
				AND   KBN1 IN ('1', '2')
		/*END*/
				GROUP BY MISE_CD
			) BN01
			WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF taishoJoken.equals("SIBU") */  
			AND   BM01.SIBU_CD = /*hyojiTaisho*/'011'
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
	        AND   BN01.MISE_CD    = BM01.MISE_CD
	    ) BM01
		WHERE BD11.MENU_YM = /*kikanSitei*/'20080509'
		AND   BD11.URIAGE_FLG = '1'
		AND   BD11.COMPANY_CD = /*companyCd*/'00'
		AND   BD11.MENU_CD = PC10.MENU_CD
		AND   BD11.MISE_CD = BM01.MISE_CD
 	   	GROUP BY ROLLUP( (BD11.COMPANY_CD
 /*IF taishoJoken.equals("ALL") */
		,     '‘SŽÐ'
/*END*/
/*IF taishoJoken.equals("SIBU") */  
		,      'Žx•”'
/*END*/
 	   	 )
         ,      (PC10.MENU_BUNRUI)
         ,      (CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD11.MENU_CD ELSE BM62.SUM_MENU_CD END)
         ,      (BD11.MENU_CD)
         )
		HAVING BD11.COMPANY_CD IS NOT NULL

ORDER BY COMPANY_CD
,        ROW_RANK
,        MENU_BUNRUI
/*IF !isCsv */
,        SUM_MENU_CD
,        SMENU_ROW_NO
,        MENU_CD
,        HANBAI_TENPO_CNT DESC
--ELSE
	/*IF isSmenu */
,        SUM_MENU_CD
,        SMENU_ROW_NO
	--ELSE
,        MENU_CD
	/*END*/
/*END*/
