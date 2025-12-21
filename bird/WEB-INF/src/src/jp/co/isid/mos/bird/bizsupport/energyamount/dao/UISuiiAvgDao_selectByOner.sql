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
SELECT METER_YM
,      SUM(URIAGE) AS URIAGE
,      CASE WHEN METER_YM < /*sysYm*/'200910' AND SUM(URIAGE)>0 
            THEN DECIMAL(
                     CASE WHEN SUM(EP_AMT)>0
                          THEN (DOUBLE(SUM(EP_AMT))/DOUBLE(DOUBLE(SUM(URIAGE))/1000000))+0.0005 
                          ELSE 0 END
                     ,  20, 3)
            ELSE 0 END EP_GNT
,      CASE WHEN METER_YM < /*sysYm*/'200910' AND SUM(URIAGE)>0
            THEN DECIMAL(
                     CASE WHEN SUM(GAS_AMT)>0 
                          THEN (DOUBLE(SUM(GAS_AMT))/DOUBLE(DOUBLE(SUM(URIAGE))/1000000))+0.0005 
                          ELSE 0 END
                     , 20, 3)
            ELSE 0 END GAS_GNT
,      CASE WHEN METER_YM < /*sysYm*/'200910' AND SUM(URIAGE)>0
            THEN DECIMAL(
                      CASE WHEN SUM(WATER_AMT)>0 
                           THEN (DOUBLE(SUM(WATER_AMT))/DOUBLE(DOUBLE(SUM(URIAGE))/1000000))+0.0005 
                           ELSE 0 END
                      , 20, 3)
            ELSE 0 END WATER_GNT
,      SUM(LAST_URIAGE) AS LAST_URIAGE
,      CASE WHEN MIN(DATA_FLG) = -1 AND SUM(LAST_URIAGE)>0 
            THEN DECIMAL(
                     CASE WHEN SUM(LAST_EP_AMT)>0
                          THEN (DOUBLE(SUM(LAST_EP_AMT))/DOUBLE(DOUBLE(SUM(LAST_URIAGE))/1000000))+0.0005 
                          ELSE 0 END
                     ,  20, 3)
            ELSE 0 END LAST_EP_GNT
,      CASE WHEN MIN(DATA_FLG) = -1 AND METER_YM < /*sysYm*/'200910' AND SUM(LAST_URIAGE)>0
            THEN DECIMAL(
                     CASE WHEN SUM(LAST_GAS_AMT)>0 
                          THEN (DOUBLE(SUM(LAST_GAS_AMT))/DOUBLE(DOUBLE(SUM(LAST_URIAGE))/1000000))+0.0005 
                          ELSE 0 END
                     , 20, 3)
            ELSE 0 END LAST_GAS_GNT
,      CASE WHEN MIN(DATA_FLG) = -1 AND METER_YM < /*sysYm*/'200910' AND SUM(LAST_URIAGE)>0
            THEN DECIMAL(
                      CASE WHEN SUM(LAST_WATER_AMT)>0 
                           THEN (DOUBLE(SUM(LAST_WATER_AMT))/DOUBLE(DOUBLE(SUM(LAST_URIAGE))/1000000))+0.0005 
                           ELSE 0 END
                      , 20, 3)
            ELSE 0 END LAST_WATER_GNT
FROM (
  (
    SELECT BD27.METER_YM
    ,      BD27.METER_KBN
    ,      BD27.COMPANY_CD
    ,      BD27.MISE_CD
    ,      BM01.MISE_NAME_KJ
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BT61.URIAGE ELSE null END AS URIAGE
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BD27.ELECTRICPOWER_AMT ELSE null END AS EP_AMT
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BD27.GAS_AMT ELSE null END AS GAS_AMT
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BD27.WATER_AMT ELSE null END AS WATER_AMT
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BD27.DATA_MT_DT ELSE null END AS DATA_MT_DT
    ,      0 AS LAST_URIAGE
    ,      0 AS LAST_EP_AMT
    ,      0 AS LAST_GAS_AMT
    ,      0 AS LAST_WATER_AMT
    ,      ''  AS LAST_DATA_MT_DT 
    ,      0 AS DATA_FLG
    FROM (SELECT * FROM BD27EUPD
	      WHERE COMPANY_CD =/*companyCd*/'00'
          AND   METER_YM BETWEEN /*nendoFrom*/'201004' AND /*nendoTo*/'201103'
          AND   METER_KBN =/*meterKbn*/'0'
	      AND   MISE_CD IN (
	            SELECT MISE_CD FROM BM01TENM 
	            WHERE COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
	            AND   SIBU_CD = /*taishoCd*/'011'
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
    ) BD27
    LEFT JOIN (SELECT * FROM BT61ZNDM
	      WHERE COMPANY_CD =/*companyCd*/'00'
          AND   EIGYO_DT BETWEEN /*nendoFrom*/'201004' AND /*nendoTo*/'201103'
	      AND   MISE_CD IN (
	            SELECT MISE_CD FROM BM01TENM 
	            WHERE COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
	            AND   SIBU_CD = /*taishoCd*/'011'
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
    ) BT61
    ON ( BT61.COMPANY_CD   = BD27.COMPANY_CD 
      AND   BT61.EIGYO_DT  = BD27.METER_YM
      AND   BT61.MISE_CD   = BD27.MISE_CD 
    )
    ,    (
		SELECT SUBBM01.COMPANY_CD
		,      SUBBM01.MISE_CD
	    ,      SUBBM01.MISE_NAME_KJ
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
	/*IF "SIBU".equals(taishoJoken) */  
	            AND   SUBBM01.SIBU_CD = /*taishoCd*/'011'
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
		GROUP BY SUBBM01.COMPANY_CD
		,        SUBBM01.MISE_CD
		,        SUBBM01.MISE_NAME_KJ
		,        (CASE WHEN SUBBM01.HIKITUGI_DT_OPEN IS NULL OR RTRIM(SUBBM01.HIKITUGI_DT_OPEN)=''
		                 THEN SUBBM01.OPEN_DT 
		             ELSE SUBBM01.HIKITUGI_DT_OPEN 
		             END) 
		,      SUBBM01.CLOSE_DT
		) BM01
	  WHERE BD27.COMPANY_CD = BM01.COMPANY_CD 
      AND   BD27.MISE_CD    = BM01.MISE_CD 
      AND   BD27.METER_YM  >= SUBSTR(BM01.OPEN_DT, 1, 6)
      AND   BD27.METER_YM  < SUBSTR(BM01.CLOSE_DT, 1, 6)
     ) 
UNION ALL

(
    SELECT CONCAT(RTRIM(CHAR(INT(SUBSTR(BD27.METER_YM, 1, 4))+1)), SUBSTR(BD27.METER_YM, 5, 2)) METER_YM
    ,      BD27.METER_KBN
    ,      BD27.COMPANY_CD
    ,      BD27.MISE_CD
    ,      BM01.MISE_NAME_KJ
    ,      0 AS URIAGE
    ,      0 AS EP_AMT
    ,      0 AS GAS_AMT
    ,      0 AS WATER_AMT
    ,      ''  AS DATA_MT_DT 
    ,      BT61.URIAGE AS LAST_URIAGE
    ,      BD27.ELECTRICPOWER_AMT AS LAST_EP_AMT
    ,      BD27.GAS_AMT AS LAST_GAS_AMT
    ,      BD27.WATER_AMT AS LAST_WATER_AMT
    ,      BD27.DATA_MT_DT AS LAST_DATA_MT_DT 
    ,      -1 AS DATA_FLG
    FROM (
        SELECT * FROM BD27EUPD
        WHERE COMPANY_CD =/*companyCd*/'00'
        AND   METER_YM BETWEEN /*lastNendoFrom*/'200904' AND /*lastNendoTo*/'201003'
        AND   METER_KBN =/*meterKbn*/'0'
        AND   MISE_CD IN (
            SELECT MISE_CD FROM BM01TENM 
            WHERE COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
            AND   SIBU_CD = /*taishoCd*/'011'
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
      ) BD27
    LEFT JOIN (SELECT * FROM BT61ZNDM
	      WHERE COMPANY_CD =/*companyCd*/'00'
          AND   EIGYO_DT BETWEEN /*lastNendoFrom*/'200904' AND /*lastNendoTo*/'201003'
	      AND   MISE_CD IN (
	            SELECT MISE_CD FROM BM01TENM 
	            WHERE COMPANY_CD = /*companyCd*/'00'
/*IF "SIBU".equals(taishoJoken) */  
	            AND   SIBU_CD = /*taishoCd*/'011'
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
    ) BT61
    ON ( BT61.COMPANY_CD   = BD27.COMPANY_CD 
      AND   BT61.EIGYO_DT  = BD27.METER_YM
      AND   BT61.MISE_CD   = BD27.MISE_CD 
    )
    ,    (
	SELECT SUBBM01.COMPANY_CD
	,      SUBBM01.MISE_CD
	,      SUBBM01.MISE_NAME_KJ
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
	/*IF "SIBU".equals(taishoJoken) */  
	            AND   SUBBM01.SIBU_CD = /*taishoCd*/'011'
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
		GROUP BY SUBBM01.COMPANY_CD
		,        SUBBM01.MISE_CD
		,        SUBBM01.MISE_NAME_KJ
		,        (CASE WHEN SUBBM01.HIKITUGI_DT_OPEN IS NULL OR RTRIM(SUBBM01.HIKITUGI_DT_OPEN)=''
		                 THEN SUBBM01.OPEN_DT 
		             ELSE SUBBM01.HIKITUGI_DT_OPEN 
		             END) 
		,      SUBBM01.CLOSE_DT
	) BM01
	  WHERE BD27.COMPANY_CD = BM01.COMPANY_CD 
      AND   BD27.MISE_CD    = BM01.MISE_CD 
      AND   SUBSTR(BM01.OPEN_DT, 1, 6) <= /*nendoTo*/'201003'
      AND   SUBSTR(BM01.CLOSE_DT, 1, 6) > /*nendoFrom*/'201003'
      AND   BD27.METER_YM  >= SUBSTR(BM01.OPEN_DT, 1, 6)
      AND   BD27.METER_YM  < SUBSTR(BM01.CLOSE_DT, 1, 6)
     ) 
)SUB
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
	/*IF "SIBU".equals(taishoJoken) */  
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
/*IF "SIBU".equals(taishoJoken) */  
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
	/*IF "SIBU".equals(taishoJoken) */  
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
/*IF "SIBU".equals(taishoJoken) */  
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