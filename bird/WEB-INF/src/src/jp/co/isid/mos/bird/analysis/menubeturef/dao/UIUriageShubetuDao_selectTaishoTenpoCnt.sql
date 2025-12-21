SELECT COUNT(DISTINCT BD11.MISE_CD) AS TENPO_CNT 
FROM   
/*IF "DAY".equals(taishoKikan) */
		BD10NMSL/*$targetMonth*/'05' BD11
--ELSE
		BD11NMRI BD11
/*END*/		
		,    PC10SMNU PC10
WHERE 
/*IF "DAY".equals(taishoKikan) */
      BD11.MENU_DT = /*kikanSitei*/'20080501'
        AND   BD11.KAZU_KEI > 0
--ELSE
      BD11.MENU_YM = /*kikanSitei*/'200805'
		AND   BD11.URIAGE_FLG = '1'
/*END*/      
		AND   BD11.COMPANY_CD = /*companyCd*/'00'
		AND   BD11.MISE_CD IN (
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
/*IF "DAY".equals(taishoKikan) */
				AND   EIGYO_DT = /*kikanSitei*/'20080501' 
--ELSE
				AND   EIGYO_YM = /*kikanSitei*/'200805' 
/*END*/				
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
/*IF taishoJoken.equals("MISE") */  
            AND   BM01.MISE_CD = /*hyojiTaisho*/'02001'
/*END*//*IF userTypeCd == "01" && limitFlg == true */
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
	    )
		AND   BD11.MENU_CD = PC10.MENU_CD
