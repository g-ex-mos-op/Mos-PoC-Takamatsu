SELECT (CASE WHEN BD17.MENU_BUNRUI IS NULL  THEN 1
        ELSE 2 END) AS ROW_RANK
,      (CASE WHEN BD17.MENU_BUNRUI IS NULL  THEN 'TOTAL' 
        ELSE '' END) AS ROW_TYPE
,      BD17.COMPANY_CD
,      TAISHO.TARGET_NAME_KJ
,      TAISHO.TENPO_CNT
,      BD17.MENU_CD
,      (CASE WHEN BD17.MENU_BUNRUI IS NULL  THEN '合計' 
			 WHEN BD17.MENU_BUNRUI IS NOT NULL AND BD17.MENU_CD IS NULL THEN CONCAT(RTRIM(BD17.MBUNRUI_NAME_KJ) ,'計')
        ELSE RTRIM(BD17.MENU_NAME_KJ) END) AS MENU_NAME_KJ
,      BD17.KOSU,      BD17.KINGAKU
,      DECIMAL(CASE WHEN BD17.KINGAKU IS NULL OR SUM(CASE WHEN BD17.MENU_CD IS NOT NULL THEN BD17.KINGAKU ELSE 0 END) OVER(partition by BD17.COMPANY_CD)=0 THEN 0.00
                    ELSE (DOUBLE(BD17.KINGAKU)/DOUBLE(SUM(CASE WHEN BD17.MENU_CD IS NOT NULL THEN BD17.KINGAKU ELSE 0 END) OVER(partition by BD17.COMPANY_CD)))*100+0.005
                    END,11,2)  AS KINGAKU_KOUSEI_HI
,      DECIMAL(CASE WHEN BD17.KOSU IS NULL OR SUM(CASE WHEN BD17.MENU_CD IS NOT NULL THEN BD17.KOSU ELSE 0 END) OVER(partition by BD17.COMPANY_CD)=0 THEN 0.00
                    ELSE (DOUBLE(BD17.KOSU)/DOUBLE(SUM(CASE WHEN BD17.MENU_CD IS NOT NULL THEN BD17.KOSU ELSE 0 END) OVER(partition by BD17.COMPANY_CD)))*100+0.005
                    END,11,2)  AS KOSU_KOUSEI_HI
,      BD17.KOSU_00
, BD17.KIN_00
,      BD17.KOSU_01
, BD17.KIN_01
,      BD17.KOSU_02
, BD17.KIN_02
,      BD17.KOSU_03
, BD17.KIN_03
,      BD17.KOSU_04
, BD17.KIN_04
,      BD17.KOSU_05
, BD17.KIN_05
,      BD17.KOSU_06
, BD17.KIN_06
,      BD17.KOSU_07
, BD17.KIN_07
,      BD17.KOSU_08
, BD17.KIN_08
,      BD17.KOSU_09
, BD17.KIN_09
,      BD17.KOSU_10
, BD17.KIN_10
,      BD17.KOSU_11
, BD17.KIN_11
,      BD17.KOSU_12
, BD17.KIN_12
,      BD17.KOSU_13
, BD17.KIN_13
,      BD17.KOSU_14
, BD17.KIN_14
,      BD17.KOSU_15
, BD17.KIN_15
,      BD17.KOSU_16
, BD17.KIN_16
,      BD17.KOSU_17
, BD17.KIN_17
,      BD17.KOSU_18
, BD17.KIN_18
,      BD17.KOSU_19
, BD17.KIN_19
,      BD17.KOSU_20
, BD17.KIN_20
,      BD17.KOSU_21
, BD17.KIN_21
,      BD17.KOSU_22
, BD17.KIN_22
,      BD17.KOSU_23
, BD17.KIN_23
,      BD17.KOSU_ETC
, BD17.KIN_ETC
FROM (
        SELECT BD17.COMPANY_CD
        ,      PC10.MENU_BUNRUI 
        ,      PC11.MBUNRUI_NAME_KJ 
        , BD17.MENU_CD 
        ,      PC10.MENU_NAME_KJ
		,      SUM(BD17.URI_KEN_KEI*BD17.TANKA) AS KINGAKU
		,      SUM(BD17.URI_KEN_KEI) AS KOSU
		,      SUM(BD17.URI_KEN_0000*BD17.TANKA) AS KIN_00
		,      SUM(BD17.URI_KEN_0000) AS KOSU_00
		,      SUM(BD17.URI_KEN_0100*BD17.TANKA) AS KIN_01
		,      SUM(BD17.URI_KEN_0100) AS KOSU_01
		,      SUM(BD17.URI_KEN_0200*BD17.TANKA) AS KIN_02
		,      SUM(BD17.URI_KEN_0200) AS KOSU_02
		,      SUM(BD17.URI_KEN_0300*BD17.TANKA) AS KIN_03
		,      SUM(BD17.URI_KEN_0300) AS KOSU_03
		,      SUM(BD17.URI_KEN_0400*BD17.TANKA) AS KIN_04
		,      SUM(BD17.URI_KEN_0400) AS KOSU_04
		,      SUM(BD17.URI_KEN_0500*BD17.TANKA) AS KIN_05
		,      SUM(BD17.URI_KEN_0500) AS KOSU_05
		,      SUM(BD17.URI_KEN_0600*BD17.TANKA) AS KIN_06
		,      SUM(BD17.URI_KEN_0600) AS KOSU_06
		,      SUM(BD17.URI_KEN_0700*BD17.TANKA) AS KIN_07
		,      SUM(BD17.URI_KEN_0700) AS KOSU_07
		,      SUM(BD17.URI_KEN_0800*BD17.TANKA) AS KIN_08
		,      SUM(BD17.URI_KEN_0800) AS KOSU_08
		,      SUM(BD17.URI_KEN_0900*BD17.TANKA) AS KIN_09
		,      SUM(BD17.URI_KEN_0900) AS KOSU_09
		,      SUM(BD17.URI_KEN_1000*BD17.TANKA) AS KIN_10
		,      SUM(BD17.URI_KEN_1000) AS KOSU_10
		,      SUM(BD17.URI_KEN_1100*BD17.TANKA) AS KIN_11
		,      SUM(BD17.URI_KEN_1100) AS KOSU_11
		,      SUM(BD17.URI_KEN_1200*BD17.TANKA) AS KIN_12
		,      SUM(BD17.URI_KEN_1200) AS KOSU_12
		,      SUM(BD17.URI_KEN_1300*BD17.TANKA) AS KIN_13
		,      SUM(BD17.URI_KEN_1300) AS KOSU_13
		,      SUM(BD17.URI_KEN_1400*BD17.TANKA) AS KIN_14
		,      SUM(BD17.URI_KEN_1400) AS KOSU_14
		,      SUM(BD17.URI_KEN_1500*BD17.TANKA) AS KIN_15
		,      SUM(BD17.URI_KEN_1500) AS KOSU_15
		,      SUM(BD17.URI_KEN_1600*BD17.TANKA) AS KIN_16
		,      SUM(BD17.URI_KEN_1600) AS KOSU_16
		,      SUM(BD17.URI_KEN_1700*BD17.TANKA) AS KIN_17
		,      SUM(BD17.URI_KEN_1700) AS KOSU_17
		,      SUM(BD17.URI_KEN_1800*BD17.TANKA) AS KIN_18
		,      SUM(BD17.URI_KEN_1800) AS KOSU_18
		,      SUM(BD17.URI_KEN_1900*BD17.TANKA) AS KIN_19
		,      SUM(BD17.URI_KEN_1900) AS KOSU_19
		,      SUM(BD17.URI_KEN_2000*BD17.TANKA) AS KIN_20
		,      SUM(BD17.URI_KEN_2000) AS KOSU_20
		,      SUM(BD17.URI_KEN_2100*BD17.TANKA) AS KIN_21
		,      SUM(BD17.URI_KEN_2100) AS KOSU_21
		,      SUM(BD17.URI_KEN_2200*BD17.TANKA) AS KIN_22
		,      SUM(BD17.URI_KEN_2200) AS KOSU_22
		,      SUM(BD17.URI_KEN_2300*BD17.TANKA) AS KIN_23
		,      SUM(BD17.URI_KEN_2300) AS KOSU_23
		,      SUM(BD17.URI_KEN_OTHER*BD17.TANKA) AS KIN_ETC
		,      SUM(BD17.URI_KEN_OTHER) AS KOSU_ETC
        FROM (
        	SELECT BM01.MISE_CD
            FROM BM01TENM BM01
            ,    BM10GSIB BM10
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
		             AND   EIGYO_YM = /*kikanSitei*/'' 
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
            AND   BM01.MISE_CD = /*hyojiTaisho*/
/*END*/
            AND   BM10.COMPANY_CD  = BM01.COMPANY_CD
            AND   BM10.SIBU_CD     = BM01.SIBU_CD
/*IF userTypeCd == "01" && limitFlg == true */
            AND    BM10.SIBU_CD IN (
                SELECT   BM51.SIBU_CD
                FROM     BM51SVSB BM51
                WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
                AND    BM51.SV_CD      = /*userId*/'9999000a'
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
        	GROUP BY BM01.MISE_CD
	    ) BM01
	    ,   BD17JMRI BD17
        LEFT JOIN PC10SMNU PC10 ON (BD17.MENU_CD = PC10.MENU_CD)
        LEFT JOIN PC11MBUN PC11 ON (PC11.MENU_BUNRUI = PC10.MENU_BUNRUI)
        WHERE BD17.COMPANY_CD = /*companyCd*/'00'
        AND   BD17.SYS_DT = /*kikanSitei*/'200805'
        AND   BD17.MISE_CD    = BM01.MISE_CD
        GROUP BY ROLLUP (
               (BD17.COMPANY_CD)
        ,      (PC10.MENU_BUNRUI , PC11.MBUNRUI_NAME_KJ)
        ,      (PC10.MENU_BUNRUI , PC11.MBUNRUI_NAME_KJ
              , BD17.MENU_CD, PC10.MENU_NAME_KJ)
        )
	HAVING BD17.COMPANY_CD IS NOT NULL
) BD17
,    (
        SELECT BD17.COMPANY_CD
        ,      COUNT(DISTINCT BD17.MISE_CD) AS TENPO_CNT 
/*IF taishoJoken.equals("MISE") */  
        ,      BM01.MISE_NAME_KJ AS TARGET_NAME_KJ
--ELSE
	/*IF userTypeCd == "01" */
	,     '全社' AS TARGET_NAME_KJ
	--ELSE
	,     '全店' AS TARGET_NAME_KJ
	/*END*/
/*END*/
        FROM (
        	SELECT BM01.MISE_CD
        	,      BM01.MISE_NAME_KJ
        	FROM BM01TENM BM01
            ,    BM10GSIB BM10
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
		             AND   EIGYO_YM = /*kikanSitei*/'' 
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
            AND   BM10.COMPANY_CD  = BM01.COMPANY_CD
            AND   BM10.SIBU_CD     = BM01.SIBU_CD
/*IF userTypeCd == "01" && limitFlg == true */
            AND    BM10.SIBU_CD IN (
                SELECT   BM51.SIBU_CD
                FROM     BM51SVSB BM51
                WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
                AND    BM51.SV_CD      = /*userId*/'9999000a'
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
        	GROUP BY BM01.MISE_CD
        	,        BM01.MISE_NAME_KJ
        	
	    ) BM01
	    ,   BD17JMRI BD17
    WHERE BD17.COMPANY_CD = /*companyCd*/'00'
    AND   BD17.SYS_DT = /*kikanSitei*/'200805'
    AND   BD17.MISE_CD    = BM01.MISE_CD
    GROUP BY  BD17.COMPANY_CD
/*IF taishoJoken.equals("MISE") */  
    ,      BM01.MISE_NAME_KJ 
--ELSE
	/*IF userTypeCd == "01" */
	,     '全社' 
	--ELSE
	,     '全店' 
	/*END*/
/*END*/
) TAISHO
WHERE BD17.COMPANY_CD = TAISHO.COMPANY_CD
ORDER BY COMPANY_CD
,        ROW_RANK
,        MENU_BUNRUI
,        MENU_CD