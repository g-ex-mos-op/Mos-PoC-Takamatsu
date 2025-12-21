SELECT CASE WHEN METER_YM IS NOT NULL THEN METER_YM ELSE 'RUIKEI' END METER_YM
,      METER_KBN
,      MISE_CD AS TAISHO_CD
,      MISE_NAME_KJ AS TAISHO_NAME
,      CASE WHEN MAX(DATA_FLG) = 0 THEN SUM(URIAGE) ELSE null END AS URIAGE
,      CASE WHEN MAX(DATA_FLG) = 0 THEN SUM(EP_AMT) ELSE null END AS EP_AMT
,      CASE WHEN MAX(DATA_FLG) = 0 THEN SUM(EP_GNT) ELSE null END AS EP_GNT
,      CASE WHEN MAX(DATA_FLG) = 0 THEN SUM(GAS_AMT) ELSE null END AS GAS_AMT
,      CASE WHEN MAX(DATA_FLG) = 0 THEN SUM(GAS_GNT) ELSE null END AS GAS_GNT
,      CASE WHEN MAX(DATA_FLG) = 0 THEN SUM(WATER_AMT) ELSE null END AS WATER_AMT
,      CASE WHEN MAX(DATA_FLG) = 0 THEN SUM(WATER_GNT) ELSE null END AS WATER_GNT
,      CASE WHEN MIN(DATA_FLG) = -1 THEN SUM(LAST_URIAGE) ELSE null END AS LAST_URIAGE
,      CASE WHEN MIN(DATA_FLG) = -1 THEN SUM(LAST_EP_AMT) ELSE null END AS LAST_EP_AMT
,      CASE WHEN MIN(DATA_FLG) = -1 THEN SUM(LAST_EP_GNT) ELSE null END AS LAST_EP_GNT
,      CASE WHEN MIN(DATA_FLG) = -1 THEN SUM(LAST_GAS_AMT) ELSE null END AS LAST_GAS_AMT
,      CASE WHEN MIN(DATA_FLG) = -1 THEN SUM(LAST_GAS_GNT) ELSE null END AS LAST_GAS_GNT
,      CASE WHEN MIN(DATA_FLG) = -1 THEN SUM(LAST_WATER_AMT) ELSE null END AS LAST_WATER_AMT
,      CASE WHEN MIN(DATA_FLG) = -1 THEN SUM(LAST_WATER_GNT) ELSE null END AS LAST_WATER_GNT
,      MAX(EP_MT_FLG) AS EP_MT_FLG
,      MAX(GAS_MT_FLG) AS GAS_MT_FLG
,      MAX(WATER_MT_FLG) AS WATER_MT_FLG
,      MAX(DATA_MT_DT) AS DATA_MT_DT
,      MAX(LAST_EP_MT_FLG) AS LAST_EP_MT_FLG
,      MAX(LAST_GAS_MT_FLG) AS LAST_GAS_MT_FLG
,      MAX(LAST_WATER_MT_FLG) AS LAST_WATER_MT_FLG
,      MAX(LAST_DATA_MT_DT) AS LAST_DATA_MT_DT
FROM ((
    SELECT BD27.METER_YM
    ,      BD27.METER_KBN
    ,      BD27.COMPANY_CD
    ,      BD27.MISE_CD
    ,      BM01.MISE_NAME_KJ
    ,      BM01.SIBU_CD
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BT61.URIAGE ELSE null END AS URIAGE
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BD27.ELECTRICPOWER_AMT ELSE null END AS EP_AMT
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BD27.GAS_AMT ELSE null END AS GAS_AMT
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BD27.WATER_AMT ELSE null END AS WATER_AMT
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BD27.ELECTRICPOWER_FLG ELSE null END AS EP_MT_FLG
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BD27.GAS_MT_FLG ELSE null END AS GAS_MT_FLG
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BD27.WATER_MT_FLG ELSE null END AS WATER_MT_FLG
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' THEN BD27.DATA_MT_DT ELSE null END AS DATA_MT_DT
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' AND BT61.URIAGE>0 
                THEN DECIMAL(
                         CASE WHEN BD27.ELECTRICPOWER_AMT>0 AND BT61.URIAGE>0 
                              THEN (DOUBLE(BD27.ELECTRICPOWER_AMT)/DOUBLE(DOUBLE(BT61.URIAGE)/1000000))+0.0005 
                              ELSE 0 
                              END
                         ,  20, 3)
                ELSE 0 END EP_GNT
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' AND BT61.URIAGE>0
                THEN DECIMAL(
                         CASE WHEN BD27.GAS_AMT>0 AND BT61.URIAGE>0 
                              THEN (DOUBLE(BD27.GAS_AMT)/DOUBLE(DOUBLE(BT61.URIAGE)/1000000))+0.0005 
                              ELSE 0 
                              END
                         , 20, 3)
                ELSE 0 END GAS_GNT
    ,      CASE WHEN BD27.METER_YM < /*sysYm*/'200910' AND BT61.URIAGE>0
                THEN DECIMAL(
                          CASE WHEN BD27.WATER_AMT>0 AND BT61.URIAGE>0 
                               THEN (DOUBLE(BD27.WATER_AMT)/DOUBLE(DOUBLE(BT61.URIAGE)/1000000))+0.0005 
                               ELSE 0 END
                          , 20, 3)
                ELSE 0 END WATER_GNT
    ,      0 AS LAST_URIAGE
    ,      0 AS LAST_EP_AMT
    ,      0 AS LAST_GAS_AMT
    ,      0 AS LAST_WATER_AMT
    ,      '' AS LAST_EP_MT_FLG
    ,      '' AS LAST_GAS_MT_FLG
    ,      '' AS LAST_WATER_MT_FLG
    ,      '' AS LAST_DATA_MT_DT
    ,      0 AS LAST_EP_GNT
    ,      0 AS LAST_GAS_GNT
    ,      0 AS LAST_WATER_GNT
    ,      0 AS DATA_FLG
    FROM (SELECT * FROM BD27EUPD
	      WHERE COMPANY_CD =/*companyCd*/'00'
          AND   METER_YM BETWEEN /*nendoFrom*/'201004' AND /*nendoTo*/'201103'
          AND   METER_KBN =/*meterKbn*/'0'
	      AND   MISE_CD IN (
	            SELECT MISE_CD FROM BM01TENM 
	            WHERE COMPANY_CD = /*companyCd*/'00'
	/*IF "SIBU".equals(taishoJoken) && !taishoCd.equals("ALL")*/ 
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
	/*IF "SIBU".equals(taishoJoken) && !taishoCd.equals("ALL")*/ 
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
	    ,      SUBBM01.SIBU_CD
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
		,        SUBBM01.SIBU_CD
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
    ,      BM01.SIBU_CD
    ,      0 AS URIAGE
    ,      0 AS EP_AMT
    ,      0 AS GAS_AMT
    ,      0 AS WATER_AMT
    ,      '' AS EP_MT_FLG
    ,      '' AS GAS_MT_FLG
    ,      '' AS WATER_MT_FLG
    ,      '' AS DATA_MT_DT
    ,      0 AS EP_GNT
    ,      0 AS GAS_GNT
    ,      0 AS WATER_GNT
    ,      BT61.URIAGE AS LAST_URIAGE
    ,      BD27.ELECTRICPOWER_AMT AS LAST_EP_AMT
    ,      BD27.GAS_AMT AS LAST_GAS_AMT
    ,      BD27.WATER_AMT AS LAST_WATER_AMT
    ,      BD27.ELECTRICPOWER_FLG AS LAST_EP_MT_FLG
    ,      BD27.GAS_MT_FLG AS LAST_GAS_MT_FLG
    ,      BD27.WATER_MT_FLG AS LAST_WATER_MT_FLG
    ,      BD27.DATA_MT_DT AS LAST_DATA_MT_DT 
    ,      CASE WHEN BT61.URIAGE>0
                     THEN DECIMAL(CASE WHEN BD27.ELECTRICPOWER_AMT>0 AND BT61.URIAGE>0 THEN (DOUBLE(BD27.ELECTRICPOWER_AMT)/DOUBLE(DOUBLE(BT61.URIAGE)/1000000))+0.0005 ELSE 0 END,  20, 3)
                ELSE 0 END LAST_EP_GNT
    ,      CASE WHEN BT61.URIAGE>0
                     THEN DECIMAL(CASE WHEN BD27.GAS_AMT>0 AND BT61.URIAGE>0 THEN (DOUBLE(BD27.GAS_AMT)/DOUBLE(DOUBLE(BT61.URIAGE)/1000000))+0.0005 ELSE 0 END, 20, 3)
                ELSE 0 END LAST_GAS_GNT
    ,      CASE WHEN BT61.URIAGE>0
                     THEN DECIMAL(CASE WHEN BD27.WATER_AMT>0 AND BT61.URIAGE>0 THEN (DOUBLE(BD27.WATER_AMT)/DOUBLE(DOUBLE(BT61.URIAGE)/1000000))+0.0005 ELSE 0 END, 20, 3)
                ELSE 0 END LAST_WATER_GNT
    ,      -1 AS DATA_FLG
    FROM (
        SELECT * FROM BD27EUPD
        WHERE COMPANY_CD =/*companyCd*/'00'
        AND   METER_YM BETWEEN /*lastNendoFrom*/'200904' AND /*lastNendoTo*/'201003'
        AND   METER_KBN =/*meterKbn*/'0'
        AND   MISE_CD IN (
            SELECT MISE_CD FROM BM01TENM 
            WHERE COMPANY_CD = /*companyCd*/'00'
	/*IF "SIBU".equals(taishoJoken) && !taishoCd.equals("ALL")*/ 
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
	/*IF "SIBU".equals(taishoJoken) && !taishoCd.equals("ALL")*/ 
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
	,      SUBBM01.SIBU_CD
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
		,        SUBBM01.SIBU_CD
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
) SUB


GROUP BY ROLLUP ((MISE_CD
,        SIBU_CD 
,        MISE_NAME_KJ)
,(METER_YM
,        METER_KBN
,        MISE_CD
,        SIBU_CD 
,        MISE_NAME_KJ)
)
HAVING  MISE_CD IS NOT NULL


ORDER BY 
/*IF "SIBU".equals(taishoJoken) && taishoCd.equals("ALL")*/ 
SIBU_CD,
/*END*/
TAISHO_CD, METER_YM



