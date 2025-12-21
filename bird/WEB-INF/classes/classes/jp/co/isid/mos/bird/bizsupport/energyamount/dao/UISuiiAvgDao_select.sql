SELECT DISTINCT CASE WHEN BD28AVG.METER_YM IS NOT NULL THEN BD28AVG.METER_YM ELSE BD27.METER_YM END METER_YM
,      BD28AVG.EP_GNT
,      BD28AVG.GAS_GNT
,      BD28AVG.WATER_GNT
,      BD28AVG.LAST_EP_GNT
,      BD28AVG.LAST_GAS_GNT
,      BD28AVG.LAST_WATER_GNT
,      BD27.TENPO_CNT
,      BD27.LAST_TENPO_CNT

FROM       (
        SELECT DISTINCT BD27.MISE_CD
        FROM BD27EUPD BD27
        ,    (
		    SELECT SUBBM01.COMPANY_CD
		    ,      SUBBM01.MISE_CD
		    ,      (CASE WHEN SUBBM01.HIKITUGI_DT_OPEN IS NULL OR RTRIM(SUBBM01.HIKITUGI_DT_OPEN)=''
		                 THEN SUBBM01.OPEN_DT 
		             ELSE SUBBM01.HIKITUGI_DT_OPEN 
		             END) OPEN_DT
		    ,      SUBBM01.CLOSE_DT
		    FROM BM01TENM SUBBM01
/*IF "01".equals(userTypeCd) */
		    ,    (
		      SELECT DISTINCT SIBU_CD
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
	        AND   SUBBM01.SIBU_CD = /*taishoCd*/'077'

	/*END*/
	/*IF "MISE".equals(taishoJoken) */  
	        AND   SUBBM01.MISE_CD = /*taishoCd*/'02001'
	/*END*/
	        AND   BM10.SIBU_CD     = SUBBM01.SIBU_CD
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
		) BM01
	    WHERE BD27.COMPANY_CD =/*companyCd*/'00'
	    AND   BD27.MISE_CD IN (
	            SELECT MISE_CD FROM BM01TENM 
	            WHERE COMPANY_CD = /*companyCd*/'00'
	/*IF "SIBU".equals(taishoJoken) && !taishoCd.equals("ALL")*/ 
	            AND   SIBU_CD = /*taishoCd*/'077'
	       
/*END*/
/*IF "MISE".equals(taishoJoken) */  
		        AND   MISE_CD = /*taishoCd*/'02001'
/*END*/
/*IF "02".equals(userTypeCd) */
		        AND   ONER_CD IN (
	                 SELECT DISTINCT ONER_CD 
	                 FROM BM06UONR 
	                 WHERE USER_ID = /*userId*/'99990002'
	                 AND   COMPANY_CD = /*companyCd*/'00')
/*END*/
/*IF "03".equals(userTypeCd) */
		        AND   MISE_CD IN (
		          SELECT DISTINCT MISE_CD 
		          FROM BM07UTEN 
		          WHERE USER_ID = /*userId*/'99990003'
		          AND   COMPANY_CD = /*companyCd*/'00')
/*END*/
	    )
        AND   BD27.METER_YM BETWEEN /*nendoFrom*/'200804' AND /*nendoTo*/'201003'
        AND   BD27.METER_KBN =/*meterKbn*/'0'
        AND   BD27.COMPANY_CD = BM01.COMPANY_CD 
        AND   BD27.MISE_CD    = BM01.MISE_CD 
        AND   BD27.METER_YM  >= SUBSTR(BM01.OPEN_DT, 1,6)
        AND   BD27.METER_YM  < SUBSTR(BM01.CLOSE_DT, 1,6)
     ) EXIST_DATA
,    (SELECT METER_YM
      ,     CASE WHEN METER_YM >= /*sysYm*/'200910' OR MAX(DATA_FLG)=-1 THEN null ELSE SUM(EP_GNT) END AS EP_GNT
      ,     CASE WHEN METER_YM >= /*sysYm*/'200910' OR MAX(DATA_FLG)=-1 THEN null ELSE SUM(GAS_GNT) END AS GAS_GNT
      ,     CASE WHEN METER_YM >= /*sysYm*/'200910' OR MAX(DATA_FLG)=-1 THEN null ELSE SUM(WATER_GNT) END AS WATER_GNT
      ,     CASE WHEN MIN(DATA_FLG) =0 THEN null ELSE SUM(LAST_EP_GNT) END AS LAST_EP_GNT
      ,     CASE WHEN MIN(DATA_FLG) =0 THEN null ELSE SUM(LAST_GAS_GNT) END AS LAST_GAS_GNT
      ,     CASE WHEN MIN(DATA_FLG) =0 THEN null ELSE SUM(LAST_WATER_GNT) END AS LAST_WATER_GNT
      
      FROM ((
          SELECT METER_YM
          ,      0 AS DATA_FLG
          ,      ELECTRICPOWER_GNT AS EP_GNT
          ,      GAS_GNT
          ,      WATER_GNT
          ,      0 AS LAST_EP_GNT
          ,      0 AS LAST_GAS_GNT
          ,      0 AS LAST_WATER_GNT
          FROM BD28EGNT
          WHERE COMPANY_CD =/*companyCd*/'00'
          AND   MISE_CD = '00000'
          AND   METER_YM BETWEEN /*nendoFrom*/'200904' AND /*nendoTo*/'201003'
          AND   METER_KBN =/*meterKbn*/'0'
          )
          UNION ALL
          (
          SELECT CONCAT(RTRIM(CHAR(INT(SUBSTR(METER_YM, 1, 4))+1)), SUBSTR(METER_YM, 5, 2)) METER_YM
          ,      -1 AS DATA_FLG
          ,      0 AS EP_GNT
          ,      0 AS GAS_GNT
          ,      0 AS WATER_GNT
          ,      ELECTRICPOWER_GNT AS LAST_EP_GNT
          ,      GAS_GNT AS LAST_GAS_GNT
          ,      WATER_GNT AS LAST_WATER_GNT
          FROM BD28EGNT
          WHERE COMPANY_CD =/*companyCd*/'00'
          AND   MISE_CD = '00000'
          AND   METER_YM BETWEEN /*lastNendoFrom*/'200804' AND /*lastNendoTo*/'200903'
          AND   METER_KBN =/*meterKbn*/'0'
          )
      ) SUB
      
      GROUP BY METER_YM
      ) BD28AVG
FULL OUTER JOIN (
    SELECT METER_YM
    ,      CASE WHEN METER_YM< /*sysYm*/'201002' THEN MAX(TENPO_CNT) ELSE null END TENPO_CNT
    ,      MAX(LAST_TENPO_CNT) AS LAST_TENPO_CNT
    FROM (
      (
        SELECT BD27.METER_YM
        ,      COUNT(DISTINCT BD27.MISE_CD) TENPO_CNT
        ,      0 AS LAST_TENPO_CNT
        FROM BD27EUPD BD27
        ,    (
		    SELECT SUBBM01.COMPANY_CD
		    ,      SUBBM01.MISE_CD
		    ,      (CASE WHEN SUBBM01.HIKITUGI_DT_OPEN IS NULL OR RTRIM(SUBBM01.HIKITUGI_DT_OPEN)=''
		                 THEN SUBBM01.OPEN_DT 
		             ELSE SUBBM01.HIKITUGI_DT_OPEN 
		             END) OPEN_DT
		    ,      SUBBM01.CLOSE_DT
		    FROM BM01TENM SUBBM01
/*IF "01".equals(userTypeCd) */
		    ,    (
		      SELECT DISTINCT SIBU_CD
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
	        AND   SUBBM01.SIBU_CD = /*taishoCd*/'077'
	/*END*/
	/*IF "MISE".equals(taishoJoken) */  
	        AND   SUBBM01.MISE_CD = /*taishoCd*/'02001'
	/*END*/
	        AND   BM10.SIBU_CD     = SUBBM01.SIBU_CD
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
		) BM01
	    WHERE BD27.COMPANY_CD =/*companyCd*/'00'
	    AND   BD27.MISE_CD IN (
	            SELECT MISE_CD FROM BM01TENM 
	            WHERE COMPANY_CD = /*companyCd*/'00'
	/*IF "SIBU".equals(taishoJoken) && !taishoCd.equals("ALL")*/ 
	            AND   SIBU_CD = /*taishoCd*/'077'
	         
/*END*/
/*IF "MISE".equals(taishoJoken) */  
		        AND   MISE_CD = /*taishoCd*/'02001'
/*END*/
/*IF "02".equals(userTypeCd) */
		        AND   ONER_CD IN (
	                 SELECT DISTINCT ONER_CD 
	                 FROM BM06UONR 
	                 WHERE USER_ID = /*userId*/'99990002'
	                 AND   COMPANY_CD = /*companyCd*/'00')
/*END*/
/*IF "03".equals(userTypeCd) */
		        AND   MISE_CD IN (
		          SELECT DISTINCT MISE_CD 
		          FROM BM07UTEN 
		          WHERE USER_ID = /*userId*/'99990003'
		          AND   COMPANY_CD = /*companyCd*/'00')
/*END*/
	    )
        AND   BD27.METER_YM BETWEEN /*nendoFrom*/'200904' AND /*nendoTo*/'201003'
        AND   BD27.METER_KBN =/*meterKbn*/'0'
        AND   BD27.COMPANY_CD = BM01.COMPANY_CD 
        AND   BD27.MISE_CD    = BM01.MISE_CD 
        AND   BD27.METER_YM  >=SUBSTR(BM01.OPEN_DT, 1,6)
        AND   BD27.METER_YM  < SUBSTR(BM01.CLOSE_DT, 1,6)
        GROUP BY BD27.METER_YM
     )
     UNION ALL
     (
        SELECT CONCAT(RTRIM(CHAR(INT(SUBSTR(BD27.METER_YM, 1, 4))+1)), SUBSTR(BD27.METER_YM, 5, 2)) METER_YM
        ,      0 AS TENPO_CNT
        ,      COUNT(DISTINCT BD27.MISE_CD) LAST_TENPO_CNT
        FROM BD27EUPD BD27
        ,    (
	       SELECT SUBBM01.COMPANY_CD
	       ,      SUBBM01.MISE_CD
	       ,      (CASE WHEN SUBBM01.HIKITUGI_DT_OPEN IS NULL OR RTRIM(SUBBM01.HIKITUGI_DT_OPEN)=''
	                         THEN SUBBM01.OPEN_DT 
	                    ELSE SUBBM01.HIKITUGI_DT_OPEN 
	               END) OPEN_DT
	       ,      SUBBM01.CLOSE_DT
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
	        AND   SUBBM01.SIBU_CD = /*taishoCd*/'077'
	      
	/*END*/
	/*IF "MISE".equals(taishoJoken) */  
	        AND   SUBBM01.MISE_CD = /*taishoCd*/'02001'
	/*END*/
	        AND   BM10.SIBU_CD     = SUBBM01.SIBU_CD
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
	    ) BM01
        WHERE BD27.COMPANY_CD =/*companyCd*/'00'
        AND   BD27.MISE_CD IN (
            SELECT MISE_CD FROM BM01TENM 
            WHERE COMPANY_CD = /*companyCd*/'00'
	/*IF "SIBU".equals(taishoJoken) && !taishoCd.equals("ALL")*/ 
            AND   SIBU_CD = /*taishoCd*/'077'
           
/*END*/
/*IF "MISE".equals(taishoJoken) */  
	        AND   MISE_CD = /*taishoCd*/'02001'
/*END*/
/*IF "02".equals(userTypeCd) */
	        AND   ONER_CD IN (
	                 SELECT DISTINCT ONER_CD 
	                 FROM BM06UONR 
	                 WHERE USER_ID = /*userId*/'99990002'
	                 AND   COMPANY_CD = /*companyCd*/'00')
/*END*/
/*IF "03".equals(userTypeCd) */
	        AND   MISE_CD IN (
	          SELECT DISTINCT MISE_CD 
	          FROM BM07UTEN 
	          WHERE USER_ID = /*userId*/'99990003'
	          AND   COMPANY_CD = /*companyCd*/'00')
/*END*/
        )
        AND   BD27.METER_YM BETWEEN /*lastNendoFrom*/'200804' AND /*lastNendoTo*/'200903'
        AND   BD27.METER_KBN =/*meterKbn*/'0'
        AND   BD27.COMPANY_CD = BM01.COMPANY_CD 
        AND   BD27.MISE_CD    = BM01.MISE_CD 
        AND   SUBSTR(BM01.OPEN_DT, 1, 6) <= /*nendoTo*/'201003'
	    AND   SUBSTR(BM01.CLOSE_DT, 1, 6) > /*nendoFrom*/'201003'
        AND   BD27.METER_YM  >= SUBSTR(BM01.OPEN_DT, 1,6)
        AND   BD27.METER_YM  < SUBSTR(BM01.CLOSE_DT, 1,6)
        GROUP BY CONCAT(RTRIM(CHAR(INT(SUBSTR(BD27.METER_YM, 1, 4))+1)), SUBSTR(BD27.METER_YM, 5, 2))
        ,        0
     )
    ) SUB
    GROUP BY METER_YM
) BD27
ON BD27.METER_YM = BD28AVG.METER_YM
ORDER BY METER_YM
