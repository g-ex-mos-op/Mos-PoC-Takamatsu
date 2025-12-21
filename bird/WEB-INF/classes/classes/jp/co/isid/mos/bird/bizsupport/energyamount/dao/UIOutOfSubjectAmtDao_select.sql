SELECT BM68.COMPANY_CD
,      BM68.MISE_CD
,      CASE WHEN ELECTRIC_FLG = '0' OR POWER_FLG = '0' THEN '0' ELSE '1' END EP_FLG
,      GAS_FLG
,      WATER_FLG

FROM (
	SELECT SUBBM01.COMPANY_CD
	,      SUBBM01.MISE_CD
	FROM BM01TENM SUBBM01
/*IF "01".equals(userTypeCd) */
	,    (SELECT DISTINCT SIBU_CD
	      FROM BM10GSIB
	      WHERE COMPANY_CD = /*companyCd*/'00'
	/*IF limitFlg*/
		  AND SIBU_CD IN (
		      SELECT   BM51.SIBU_CD
		      FROM     BM51SVSB BM51
		      WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
		      AND    BM51.SV_CD      = /*userId*/'99990001'
		      GROUP BY BM51.SIBU_CD
		  )
	/*END*/
	) BM10
/*END*/
	            WHERE SUBBM01.COMPANY_CD = /*companyCd*/'00'
/*IF "01".equals(userTypeCd) */
	/*IF "SIBU".equals(taishoJoken) && !taishoCd.equals("ALL")*/ 
	            AND   SUBBM01.SIBU_CD = /*taishoCd*/''

	/*END*/
	/*IF "MISE".equals(taishoJoken) */  
	            AND   SUBBM01.MISE_CD = /*taishoCd*/
	/*END*/
	            AND    BM10.SIBU_CD     = SUBBM01.SIBU_CD
/*END*/
/*IF "02".equals(userTypeCd) */
	       AND   SUBBM01.ONER_CD IN (
	                 SELECT DISTINCT ONER_CD 
	                 FROM BM06UONR 
	                 WHERE USER_ID = /*userId*/'99990002'
	                 AND   COMPANY_CD = /*companyCd*/'00')
/*END*/
/*IF "03".equals(userTypeCd) */
	       AND   SUBBM01.MISE_CD IN (
	                 SELECT DISTINCT MISE_CD 
	                 FROM BM07UTEN 
	                 WHERE USER_ID = /*userId*/'99990003'
	                 AND   COMPANY_CD = /*companyCd*/'00')
/*END*/
	            GROUP BY SUBBM01.COMPANY_CD
	            ,        SUBBM01.SIBU_CD 
	            ,        SUBBM01.MISE_CD
	            ,        SUBBM01.ONER_CD
	) BM01
,   BM68MSET BM68
WHERE BM68.COMPANY_CD = /*companyCd*/'00'
AND   BM68.MISE_CD    = BM01.MISE_CD
AND   BM68.METER_KBN = /*meterKbn*/'0'