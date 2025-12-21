SELECT BD03.COMPANY_CD
/*IF companyCd.equals("00") && shukeiKbn.equals("SIBU")*/
,      BD03.HONBU_CD 
,      BD03.HONBU_NAME
,      BD03.JIGYOU_CD 
,      BD03.JIGYOU_NAME
,      BD03.SLAREA_CD 
,      BD03.SLAREA_NAME
/*END*/
/*IF companyCd.equals("00") && shukeiKbn.equals("SIBU") && menuTotaledKbn == "TOTAL"*/
,      1 AS GROUP_ROW_NO
,      CASE WHEN BD03.HONBU_CD IS NULL 
            THEN '8_TOTAL' 
            WHEN BD03.JIGYOU_CD IS NULL THEN '5_HONBU_TOTAL' 
            WHEN BD03.SLAREA_CD IS NULL THEN '4_JIGYOU_TOTAL' 
            WHEN BD03.SIBU_CD IS NULL THEN '3_SLAREA_TOTAL' 
            ELSE '0' END AS ROW_TYPE
,      RTRIM(CASE WHEN BD03.HONBU_CD IS NULL
            THEN 'ëççáåv' 
            WHEN BD03.JIGYOU_CD IS NULL THEN BD03.HONBU_NAME 
            WHEN BD03.SLAREA_CD IS NULL THEN BD03.JIGYOU_NAME 
            WHEN BD03.SIBU_CD IS NULL THEN BD03.SLAREA_NAME 
            ELSE BD03.SIBU_NAME END) AS SIBU_NAME
--ELSE
	/*IF menuTotaledKbn == "TOTAL" */
,      1 AS GROUP_ROW_NO
	--ELSE
,      ROW_NUMBER() OVER(
            partition by BD03.COMPANY_CD
            ,      (CASE WHEN BD03.SIBU_CD IS NULL
		/*IF menuTotaledKbn != "TOTAL" */
                              AND BD03.MENU_CD IS NOT NULL THEN '7_MENU_TOTAL' 
                        WHEN BD03.SIBU_CD IS NULL AND BD03.MENU_CD IS NULL      
		/*END*/
                        THEN '8_TOTAL' ELSE '0' END)
             ,     BD03.SIBU_CD order by BD03.MENU_CD asc) AS GROUP_ROW_NO
	/*END*/
,      CASE WHEN BD03.SIBU_CD IS NULL
	/*IF menuTotaledKbn != "TOTAL" */
                 AND BD03.MENU_CD IS NOT NULL THEN '7_MENU_TOTAL' 
            WHEN BD03.SIBU_CD IS NULL AND BD03.MENU_CD IS NULL
	/*END*/
            THEN '8_TOTAL' ELSE '0' END AS ROW_TYPE
,      RTRIM(CASE WHEN BD03.SIBU_CD IS NULL 
	/*IF menuTotaledKbn != "TOTAL" */
                      AND BD03.MENU_CD IS NOT NULL THEN ''
                  WHEN BD03.SIBU_CD IS NULL AND BD03.MENU_CD IS NULL
	/*END*/
                  THEN 'ëççáåv' ELSE BD03.SIBU_NAME END) AS SIBU_NAME
/*END*/
,      BD03.SIBU_CD 
/*IF menuTotaledKbn != "TOTAL" */
,      BD03.MENU_CD
,      RTRIM(PC10.MENU_NAME_KJ) AS MENU_NAME_KJ
/*END*/
,      (BD03.URIAGE) AS MENU_URIAGE
,      (BD03.KAZU_KEI) AS KAZU_KEI
,      DECIMAL(
           CASE WHEN BD13.URIAGE <= 0 THEN 0.00 
                WHEN BD03.URIAGE < 0
                THEN ((DOUBLE(BD03.URIAGE)/DOUBLE(BD13.URIAGE)*100)-0.005)
                ELSE ((DOUBLE(BD03.URIAGE)/DOUBLE(BD13.URIAGE)*100)+0.005)
           END, 20, 2) AS KINGAKU_KOUSEI_HI
,      BD13.TAISHO_TENPO_CNT
,      BD13.TENPO_CNT
,      (BD13.TENKO_KBN) AS TENKO_KBN
,      (BD13.URIAGE) AS URIAGE
,      (BD13.KYAKUSU) AS KYAKUSU
,      (BD13.TENKO_KBN_ZEN) AS TENKO_KBN_ZEN
,      (BD13.URIAGE_ZEN) AS URIAGE_ZEN
,      (BD13.KYAKUSU_ZEN) AS KYAKUSU_ZEN
,      BD13.KYAKUTANKA
,      BD13.KYAKUTANKA_ZEN
,      DECIMAL(
           CASE WHEN BD13.URIAGE_ZEN <= 0 THEN 0.00 
                ELSE ((DOUBLE(BD13.URIAGE)/DOUBLE(BD13.URIAGE_ZEN)*100)+0.005)
           END, 20, 2) AS URIAGE_ZEN_HI
,      DECIMAL(
           CASE WHEN BD13.KYAKUSU_ZEN <= 0 THEN 0.00 
                ELSE ((DOUBLE(BD13.KYAKUSU)/DOUBLE(BD13.KYAKUSU_ZEN)*100)+0.005)
           END, 20, 2) AS KYAKUSU_ZEN_HI
,      DECIMAL(
           CASE WHEN BD13.KYAKUTANKA_ZEN <= 0 THEN 0.00 
                ELSE ((DOUBLE(BD13.KYAKUTANKA)/DOUBLE(BD13.KYAKUTANKA_ZEN)*100)+0.005)
           END, 20, 2) AS KYAKUTANKA_ZEN_HI
/*IF zennenDataShubetu.equals("DOGETUHOSEI") */
,      BD13.NET_URIAGE
,      BD13.NET_KYAKUSU
,      BD13.NET_URIAGE_ZEN
,      BD13.NET_KYAKUSU_ZEN
,      BD13.NET_KYAKUTANKA
,      BD13.NET_KYAKUTANKA_ZEN
,      DECIMAL(
           CASE WHEN BD13.NET_URIAGE_ZEN <= 0 THEN 0.00 
                ELSE ((DOUBLE(BD13.NET_URIAGE)/DOUBLE(BD13.NET_URIAGE_ZEN)*100)+0.005)
           END, 20, 2) AS NET_URIAGE_ZEN_HI
,      DECIMAL(
           CASE WHEN BD13.NET_KYAKUSU_ZEN <= 0 THEN 0.00 
                ELSE ((DOUBLE(BD13.NET_KYAKUSU)/DOUBLE(BD13.NET_KYAKUSU_ZEN)*100)+0.005)
           END, 20, 2) AS NET_KYAKUSU_ZEN_HI
,      DECIMAL(
           CASE WHEN BD13.NET_KYAKUTANKA_ZEN <= 0 THEN 0.00 
                ELSE ((DOUBLE(BD13.NET_KYAKUTANKA)/DOUBLE(BD13.NET_KYAKUTANKA_ZEN)*100)+0.005)
           END, 20, 2) AS NET_KYAKUTANKA_ZEN_HI
/*END*/
FROM (
      SELECT BM01.COMPANY_CD
/*IF companyCd.equals("00") && shukeiKbn.equals("SIBU") && menuTotaledKbn == "TOTAL"*/
      ,      BM01.HONBU_CD 
      ,      BM01.JIGYOU_CD 
      ,      BM01.SLAREA_CD 
/*END*/
      ,      BM01.SIBU_CD
      ,      COUNT(DISTINCT (CASE WHEN BD13.OPEN_KBN >= 1 THEN BM01.MISE_CD ELSE null END)) AS TAISHO_TENPO_CNT
      ,      COUNT(DISTINCT BM01.MISE_CD) AS TENPO_CNT
      ,      SUM(URIAGE) AS URIAGE
      ,      SUM(KYAKUSU) AS KYAKUSU
      ,      SUM(EIGYO_DAYS) AS EIGYO_DAYS
      ,      MAX(TENKO_KBN) AS TENKO_KBN
      ,      SUM(URIAGE_ZEN)     AS URIAGE_ZEN
      ,      SUM(EIGYO_DAYS_ZEN) AS EIGYO_DAYS_ZEN
      ,      MAX(TENKO_KBN_ZEN)  AS TENKO_KBN_ZEN
      ,      SUM(KYAKUSU_ZEN)    AS KYAKUSU_ZEN
	  ,      DECIMAL(
		           CASE WHEN SUM(KYAKUSU) <= 0 THEN 0.00 
		                ELSE ((DOUBLE(SUM(URIAGE) )/DOUBLE(SUM(KYAKUSU)))+0.5)
		           END, 11, 0) AS KYAKUTANKA
	  ,      DECIMAL(
		           CASE WHEN SUM(KYAKUSU_ZEN) <= 0 THEN 0.00 
		                ELSE ((DOUBLE(SUM(URIAGE_ZEN) )/DOUBLE(SUM(KYAKUSU_ZEN)))+0.5)
		           END, 11, 0) AS KYAKUTANKA_ZEN
/*IF zennenDataShubetu.equals("DOGETUHOSEI") */
      ,      SUM(NET_URIAGE) AS NET_URIAGE
      ,      SUM(NET_EIGYO_DAYS) AS NET_EIGYO_DAYS
      ,      SUM(NET_KYAKUSU) AS NET_KYAKUSU
      ,      SUM(NET_URIAGE_ZEN)  AS NET_URIAGE_ZEN
      ,      SUM(NET_EIGYO_DAYS_ZEN) AS NET_EIGYO_DAYS_ZEN
      ,      SUM(NET_KYAKUSU_ZEN) AS NET_KYAKUSU_ZEN
	  ,      DECIMAL(
		           CASE WHEN SUM(NET_KYAKUSU) <= 0 THEN 0.00 
		                ELSE ((DOUBLE(SUM(NET_URIAGE) )/DOUBLE(SUM(NET_KYAKUSU)))+0.5)
		           END, 11, 0) AS NET_KYAKUTANKA
	  ,      DECIMAL(
		           CASE WHEN SUM(NET_KYAKUSU_ZEN) <= 0 THEN 0.00 
		                ELSE ((DOUBLE(SUM(NET_URIAGE_ZEN) )/DOUBLE(SUM(NET_KYAKUSU_ZEN)))+0.5)
		           END, 11, 0) AS NET_KYAKUTANKA_ZEN
/*END*/
      FROM BM65CPMS BM65
      ,    (SELECT COMPANY_CD, MISE_CD 
	        ,      MAX(OPEN_KBN_/*$tenpoShubetu*/) AS OPEN_KBN
	        ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' 
	                        THEN URIAGE_/*$tenpoShubetu*/ *-1 ELSE URIAGE_/*$tenpoShubetu*/ END) AS URIAGE
	        ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' 
                            THEN KYAKUSU_/*$tenpoShubetu*/ *-1 ELSE KYAKUSU_/*$tenpoShubetu*/ END) AS KYAKUSU
	        ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' 
                            THEN DAYS_/*$tenpoShubetu*/ *-1 ELSE DAYS_/*$tenpoShubetu*/ END) AS EIGYO_DAYS
	        ,      MAX(TENKO_KBN) AS TENKO_KBN
/*IF zennenDataShubetu.equals("DOGETUHOSEI")==false */
            ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601'
                            THEN URIAGE_ZEN_/*$targetZenneClm*/ *-1 
                            ELSE URIAGE_ZEN_/*$targetZenneClm*/
                       END)     AS URIAGE_ZEN
            ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601'
                            THEN DAYS_ZEN_/*$targetZenneClm*/ *-1 
                            ELSE DAYS_ZEN_/*$targetZenneClm*/
                       END) AS EIGYO_DAYS_ZEN
            ,      MAX(TENKO_KBN_ZEN_/*$zennenDataShubetu*/)  AS TENKO_KBN_ZEN
            ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' 
                            THEN KYAKUSU_ZEN_/*$targetZenneClm*/ *-1 
                            ELSE KYAKUSU_ZEN_/*$targetZenneClm*/
                       END) AS KYAKUSU_ZEN
--ELSE
            ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' THEN URIAGE_ZEN_DOGETU_/*$tenpoShubetu*/ *-1 
                       ELSE URIAGE_ZEN_DOGETU_/*$tenpoShubetu*/ END)     AS URIAGE_ZEN
            ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' THEN DAYS_ZEN_DOGETU_/*$tenpoShubetu*/ *-1 
                       ELSE DAYS_ZEN_DOGETU_/*$tenpoShubetu*/ END) AS EIGYO_DAYS_ZEN
            ,      MAX(TENKO_KBN_ZEN_DOGETU)  AS TENKO_KBN_ZEN
            ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' THEN KYAKUSU_ZEN_DOGETU_/*$tenpoShubetu*/ *-1 
                       ELSE KYAKUSU_ZEN_DOGETU_/*$tenpoShubetu*/ END)    AS KYAKUSU_ZEN
            ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' THEN URIAGE_HOSEI_1*-1 
                       ELSE URIAGE_HOSEI_1  END) AS NET_URIAGE
            ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' THEN DAYS_HOSEI_1*-1 
                       ELSE DAYS_HOSEI_1 END)    AS NET_EIGYO_DAYS
            ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' THEN KYAKUSU_HOSEI_1*-1 
                       ELSE KYAKUSU_HOSEI_1 END) AS NET_KYAKUSU
            ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' THEN URIAGE_ZEN_HOSEI_1*-1 
                       ELSE URIAGE_ZEN_HOSEI_1 END) AS NET_URIAGE_ZEN
            ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' THEN DAYS_ZEN_HOSEI_1*-1 
                       ELSE DAYS_ZEN_HOSEI_1 END)    AS NET_EIGYO_DAYS_ZEN
            ,      SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' THEN KYAKUSU_ZEN_HOSEI_1*-1 
                       ELSE KYAKUSU_ZEN_HOSEI_1 END) AS NET_KYAKUSU_ZEN
/*END*/
	        FROM BD13CRNP
            WHERE COMPANY_CD = /*companyCd*/'00'
            AND   (EIGYO_DT = /*taishoDtFromLastDay*/'20080601' OR EIGYO_DT = /*taishoDt*/'20080701')
/*IF taishoJoken.equals("MISE") */  
            AND   MISE_CD = /*hyojiTaisho*/
/*END*/
	        GROUP BY COMPANY_CD, MISE_CD 
	        HAVING SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' 
	                        THEN REAL_DATA_CNT_/*$tenpoShubetu*/ *-1 ELSE REAL_DATA_CNT_/*$tenpoShubetu*/ END) > 0
	        AND    (SUM(CASE WHEN EIGYO_DT = /*taishoDtFromLastDay*/'20080601' 
	                        THEN OPEN_KBN_/*$tenpoShubetu*/ *-1 ELSE OPEN_KBN_/*$tenpoShubetu*/ END) > 0
					)     
           ) BD13
      ,    ( 
      SELECT BM01.COMPANY_CD
      ,      BM10.HONBU_CD 
      ,      BM10.HONBU_NAME
      ,      BM10.JIGYOU_CD 
      ,      BM10.JIGYOU_NAME
      ,      BM10.SLAREA_CD 
      ,      BM10.SLAREA_NAME
      ,      BM10.SIBU_CD 
      ,      BM10.SIBU_NAME
      ,      BM01.MISE_CD
      ,      BM01.ONER_CD
      FROM BM01TENM BM01
      ,    BM10GSIB BM10
/*IF userTypeCd == "02" */
      ,    BM06UONR BM06
/*END*/
/*IF userTypeCd == "03" */
      ,    BM07UTEN BM07
/*END*/
/*IF taishoJoken.equals("SEGMENT") */
       ,    (
      SELECT GYOTAI_KBN 
      FROM BM09GTSG BM09
      WHERE SEGMENT_TYPE = /*hyojiTaisho*/'M'
      GROUP BY GYOTAI_KBN
       ) BM09
/*END*/
      WHERE BM01.COMPANY_CD = /*companyCd*/'00'
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
/*IF taishoJoken.equals("SEGMENT") */
      AND   BM01.GYOTAI_KBN = BM09.GYOTAI_KBN
/*END*/
/*IF taishoJoken.equals("JIGYOU") */  
      AND   BM10.JIGYOU_CD = /*hyojiTaisho*/''
/*END*/
/*IF taishoJoken.equals("SLAREA") */  
      AND   BM10.SLAREA_CD = /*hyojiTaisho*/
/*END*/
/*IF taishoJoken.equals("SIBU") || taishoJoken.equals("AREADAI") */  
      AND   BM10.SIBU_CD = /*hyojiTaisho*/''
/*END*/
/*IF taishoJoken.equals("SIBU") && blockCd != null */
      AND   BM01.BLOCK_CD = /*blockCd*/''
/*END*/
/*IF shukeiKbn.equals("AREADAI") || taishoJoken.equals("AREADAI")*/
      AND   BM10.AREA_DAI_FLG = '1'
      AND   BM10.SIBU_CD     = BM01.AREA_DAI
--ELSE
      AND   BM10.SIBU_CD     = BM01.SIBU_CD
/*END*/

/*IF taishoJoken.equals("MISE") */  
      AND   BM01.MISE_CD = /*hyojiTaisho*/
/*END*/
      AND    BM10.COMPANY_CD  = BM01.COMPANY_CD
/*IF userTypeCd == "01" && limitFlg == true */
      AND    BM01.SIBU_CD IN (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD      = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
      )
/*END*/
      GROUP BY BM01.COMPANY_CD
      ,        BM10.HONBU_CD 
      ,        BM10.HONBU_NAME
      ,        BM10.JIGYOU_CD 
      ,        BM10.JIGYOU_NAME
      ,        BM10.SLAREA_CD 
      ,        BM10.SLAREA_NAME
      ,        BM10.SIBU_CD 
      ,        BM10.SIBU_NAME
      ,        BM01.MISE_CD
      ,        BM01.ONER_CD
     ) BM01
      WHERE BM65.CAMP_ID    = /*campId*/'200806'
      AND   BM65.COMPANY_CD    = /*companyCd*/'00'
      AND   BM01.COMPANY_CD = BM65.COMPANY_CD
      AND   BD13.COMPANY_CD = BM01.COMPANY_CD
      AND   BM01.MISE_CD = BM65.MISE_CD
      AND   BD13.MISE_CD = BM01.MISE_CD
      GROUP BY ROLLUP ((BM01.COMPANY_CD)
/*IF companyCd.equals("00") && shukeiKbn.equals("SIBU") && menuTotaledKbn == "TOTAL"*/
      ,      (BM01.COMPANY_CD, BM01.HONBU_CD)
      ,      (BM01.COMPANY_CD, BM01.JIGYOU_CD)
      ,      (BM01.COMPANY_CD, BM01.SLAREA_CD)
/*END*/
      ,      (BM01.COMPANY_CD, BM01.SIBU_CD)
      )
     ) BD13
,    (SELECT CAMPMST65.COMPANY_CD
/*IF companyCd.equals("00") && shukeiKbn.equals("SIBU")*/
      ,      CAMPMST65.HONBU_CD 
      ,      CAMPMST65.HONBU_NAME
      ,      CAMPMST65.JIGYOU_CD 
      ,      CAMPMST65.JIGYOU_NAME
      ,      CAMPMST65.SLAREA_CD 
      ,      CAMPMST65.SLAREA_NAME
/*END*/
      ,      CAMPMST65.SIBU_CD 
      ,      CAMPMST65.SIBU_NAME
/*IF menuTotaledKbn == "TANPIN" || menuTotaledKbn == "SHUYAKU"*/
      ,      CAMPMST65.MENU_CD
/*END*/
      ,      SUM(CASE WHEN CAMP03.URIAGE IS NULL THEN 0 ELSE CAMP03.URIAGE END) AS URIAGE
      ,      SUM(CASE WHEN CAMP03.KAZU_KEI IS NULL THEN 0 ELSE CAMP03.KAZU_KEI END) AS KAZU_KEI
      ,      COUNT(DISTINCT CAMPMST65.MISE_CD) AS TAISHO_TENPO_CNT
    FROM (
      SELECT BM01.COMPANY_CD
      ,      BM01.HONBU_CD 
      ,      BM01.HONBU_NAME
      ,      BM01.JIGYOU_CD 
      ,      BM01.JIGYOU_NAME
      ,      BM01.SLAREA_CD 
      ,      BM01.SLAREA_NAME
      ,      BM01.SIBU_CD 
      ,      BM01.SIBU_NAME
      ,      BM01.MISE_CD 
/*IF menuTotaledKbn == "TOTAL" ||  menuTotaledKbn == "SHUYAKU" */
      ,      (CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BM61.MENU_CD ELSE BM62.SUM_MENU_CD END) AS MENU_CD
/*END*/
/*IF menuTotaledKbn == "TANPIN" */
      ,      BM61.MENU_CD AS MENU_CD
/*END*/
      FROM (
            SELECT BM01.COMPANY_CD
            ,      BM10.HONBU_CD 
            ,      BM10.HONBU_NAME
            ,      BM10.JIGYOU_CD 
            ,      BM10.JIGYOU_NAME
            ,      BM10.SLAREA_CD 
            ,      BM10.SLAREA_NAME
            ,      BM10.SIBU_CD 
            ,      BM10.SIBU_NAME
            ,      BM01.MISE_CD
            ,      BM01.ONER_CD
            FROM BM01TENM BM01
            ,    BM10GSIB BM10
/*IF taishoJoken.equals("SEGMENT") */
             ,    (
                  SELECT GYOTAI_KBN 
                  FROM BM09GTSG BM09
                  WHERE SEGMENT_TYPE = /*hyojiTaisho*/'M'
                  GROUP BY GYOTAI_KBN
             ) BM09
/*END*/
            WHERE BM01.COMPANY_CD = /*companyCd*/'00'
/*IF taishoJoken.equals("SEGMENT") */
            AND   BM01.GYOTAI_KBN = BM09.GYOTAI_KBN
/*END*/
/*IF taishoJoken.equals("JIGYOU") */  
            AND   BM10.JIGYOU_CD = /*hyojiTaisho*/''
/*END*/
/*IF taishoJoken.equals("SLAREA") */  
            AND   BM10.SLAREA_CD = /*hyojiTaisho*/
/*END*/
/*IF taishoJoken.equals("SIBU") || taishoJoken.equals("AREADAI") */  
            AND   BM10.SIBU_CD = /*hyojiTaisho*/''
/*END*/
/*IF taishoJoken.equals("SIBU") && blockCd != null */
            AND   BM01.BLOCK_CD = /*blockCd*/''
/*END*/
/*IF shukeiKbn.equals("AREADAI") || taishoJoken.equals("AREADAI")*/
            AND   BM10.AREA_DAI_FLG = '1'
            AND   BM10.SIBU_CD     = BM01.AREA_DAI
--ELSE
            AND   BM10.SIBU_CD     = BM01.SIBU_CD
/*END*/

/*IF taishoJoken.equals("MISE") */  
            AND   BM01.MISE_CD = /*hyojiTaisho*/
/*END*/
            AND    BM10.COMPANY_CD  = BM01.COMPANY_CD
/*IF userTypeCd == "01" && limitFlg == true */
            AND    BM01.SIBU_CD IN (
                SELECT   BM51.SIBU_CD
                FROM     BM51SVSB BM51
                WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
                AND    BM51.SV_CD      = /*userId*/'9999000a'
                GROUP BY BM51.SIBU_CD
                )
/*END*/
            GROUP BY BM01.COMPANY_CD
            ,        BM10.HONBU_CD 
            ,        BM10.HONBU_NAME
            ,        BM10.JIGYOU_CD 
            ,        BM10.JIGYOU_NAME
            ,        BM10.SLAREA_CD 
            ,        BM10.SLAREA_NAME
            ,        BM10.SIBU_CD 
            ,        BM10.SIBU_NAME
            ,        BM01.MISE_CD
            ,        BM01.ONER_CD
           ) BM01
      ,    BM65CPMS BM65
      ,    BM61CPMN BM61
/*IF menuTotaledKbn == "TOTAL" ||  menuTotaledKbn == "SHUYAKU" */
        LEFT JOIN BM62SYMM BM62
        ON (BM62.MENU_CD = BM61.MENU_CD)
/*END*/
        WHERE BM65.CAMP_ID    = /*campId*/'200806'
        AND   BM65.COMPANY_CD    = /*companyCd*/'00'
        AND   BM61.CAMP_ID    = BM65.CAMP_ID    
        AND   BM65.COMPANY_CD = BM01.COMPANY_CD
        AND   BM65.MISE_CD    = BM01.MISE_CD
        GROUP BY BM01.COMPANY_CD
        ,      BM01.HONBU_CD 
        ,      BM01.HONBU_NAME
        ,      BM01.JIGYOU_CD 
        ,      BM01.JIGYOU_NAME
        ,      BM01.SLAREA_CD 
        ,      BM01.SLAREA_NAME
        ,      BM01.SIBU_CD 
        ,      BM01.SIBU_NAME
        ,      BM01.MISE_CD 
/*IF menuTotaledKbn == "TOTAL" ||  menuTotaledKbn == "SHUYAKU" */
        ,      (CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BM61.MENU_CD ELSE BM62.SUM_MENU_CD END)
/*END*/
/*IF menuTotaledKbn == "TANPIN" */
        ,      BM61.MENU_CD 
/*END*/
    ) CAMPMST65
    LEFT JOIN (
        SELECT BD03.COMPANY_CD
        ,      BD03.MISE_CD 
/*IF "BD12CPRI".equals(targetTable) */
		,      SUM(BD03.URIAGE) AS URIAGE
--ELSE
		/*IF tenpoShubetu == "1"*/
       	,      SUM(CASE WHEN MENU_DT = /*taishoDt*/'20080515' THEN BD03.URIAGE_RUI_ZEN 
                        WHEN BD03.URIAGE_RUI_ZEN IS NULL THEN 0
                        ELSE (BD03.URIAGE_RUI_ZEN*-1) 
                   END) AS URIAGE
        --ELSE
       	,      SUM(CASE WHEN MENU_DT = /*taishoDt*/'20080515' THEN BD03.URIAGE_RUI 
                        WHEN BD03.URIAGE_RUI IS NULL THEN 0
                        ELSE (BD03.URIAGE_RUI*-1) 
                   END) AS URIAGE
	    /*END*/
/*END*/
                   
/*IF menuTotaledKbn == "TANPIN" */
        ,      BD03.MENU_CD
	/*IF "BD12CPRI".equals(targetTable) */
        ,      SUM(CASE WHEN BD03.KAZU_KEI IS NULL THEN 0
                        ELSE BD03.KAZU_KEI
                   END) AS KAZU_KEI
	--ELSE
		/*IF tenpoShubetu == "1"*/
   	,      SUM(CASE WHEN MENU_DT = /*taishoDt*/'20080515' THEN BD03.KAZU_KEI_RUI_ZEN 
                    WHEN BD03.KAZU_KEI_RUI_ZEN IS NULL THEN 0
                    ELSE (BD03.KAZU_KEI_RUI_ZEN*-1) 
               END) AS KAZU_KEI
        --ELSE
   	,      SUM(CASE WHEN MENU_DT = /*taishoDt*/'20080515' THEN BD03.KAZU_KEI_RUI 
                    WHEN BD03.KAZU_KEI_RUI IS NULL THEN 0
                    ELSE (BD03.KAZU_KEI_RUI*-1) 
               END) AS KAZU_KEI
        /*END*/
	/*END*/
--ELSE
        ,      (CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.MENU_CD ELSE BM62.SUM_MENU_CD END) AS MENU_CD
	/*IF "BD12CPRI".equals(targetTable) */
        ,      SUM((CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.KAZU_KEI ELSE BD03.KAZU_KEI*BM62.CONV_VALUE END)) AS KAZU_KEI
	--ELSE
		/*IF tenpoShubetu == "1"*/
   	,      SUM(CASE WHEN MENU_DT = /*taishoDt*/'20080515' THEN (CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.KAZU_KEI_RUI_ZEN ELSE BD03.KAZU_KEI_RUI_ZEN*BM62.CONV_VALUE END)
                    ELSE ((CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.KAZU_KEI_RUI_ZEN ELSE BD03.KAZU_KEI_RUI_ZEN*BM62.CONV_VALUE END)*-1) 
               END) AS KAZU_KEI
        --ELSE
   	,      SUM(CASE WHEN MENU_DT = /*taishoDt*/'20080515' THEN (CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.KAZU_KEI_RUI ELSE BD03.KAZU_KEI_RUI*BM62.CONV_VALUE END)
                    ELSE ((CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.KAZU_KEI_RUI ELSE BD03.KAZU_KEI_RUI*BM62.CONV_VALUE END)*-1) 
               END) AS KAZU_KEI
        /*END*/
	/*END*/
/*END*/
        FROM BM61CPMN BM61
        ,    BM65CPMS BM65
        ,    /*$targetTable*/ BD03
/*IF menuTotaledKbn == "TOTAL" ||  menuTotaledKbn == "SHUYAKU" */
        LEFT JOIN BM62SYMM BM62
        ON (BM62.MENU_CD = BD03.MENU_CD)
/*END*/
        WHERE BM65.CAMP_ID    = /*campId*/'200806'
        AND   BD03.COMPANY_CD = /*companyCd*/'00'
/*IF taishoKikan == "DAYS" */
        AND  (BD03.MENU_DT =/*taishoDtFromLastDay*/'20080327' OR BD03.MENU_DT =/*taishoDt*/'20080515')
/*END*/
        AND   BM61.CAMP_ID    = BM65.CAMP_ID
/*IF "BD12CPRI".equals(targetTable) */
	/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
	    AND   BD03.TENPO_SHU = /*tenpoShubetu*/''
	/*END*/
	/*IF tenpoShubetu == "2" */
	    AND   BD03.TENPO_SHU IN ('1', '2')
	/*END*/
        AND   BD03.CAMP_ID    = BM65.CAMP_ID
/*END*/
        AND   BD03.COMPANY_CD = BM65.COMPANY_CD
        AND   BD03.MISE_CD    = BM65.MISE_CD
        AND   BD03.MENU_CD    = BM61.MENU_CD
        GROUP BY BD03.COMPANY_CD
        ,        BD03.MISE_CD 
/*IF menuTotaledKbn == "TANPIN" */
        ,      BD03.MENU_CD
--ELSE
        ,      (CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.MENU_CD ELSE BM62.SUM_MENU_CD END)
/*END*/
    ) CAMP03
       ON (      CAMP03.COMPANY_CD = CAMPMST65.COMPANY_CD 
           AND   CAMP03.MISE_CD = CAMPMST65.MISE_CD
           AND   CAMP03.MENU_CD = CAMPMST65.MENU_CD
    )
    GROUP BY ROLLUP ( (CAMPMST65.COMPANY_CD)
/*IF companyCd.equals("00") && shukeiKbn.equals("SIBU") && menuTotaledKbn == "TOTAL"*/
      ,      (CAMPMST65.COMPANY_CD, CAMPMST65.HONBU_CD, CAMPMST65.HONBU_NAME)
      ,      (CAMPMST65.COMPANY_CD, CAMPMST65.JIGYOU_CD, CAMPMST65.JIGYOU_NAME)
      ,      (CAMPMST65.COMPANY_CD, CAMPMST65.SLAREA_CD, CAMPMST65.SLAREA_NAME)
      ,      (CAMPMST65.COMPANY_CD, CAMPMST65.SIBU_CD, CAMPMST65.SIBU_NAME)
--ELSE
      /*IF menuTotaledKbn == "TANPIN" || menuTotaledKbn == "SHUYAKU" */
      ,     (CAMPMST65.COMPANY_CD, CAMPMST65.MENU_CD)
      /*END*/
      ,     (CAMPMST65.COMPANY_CD
/*IF companyCd.equals("00") && shukeiKbn.equals("SIBU")*/
             , CAMPMST65.HONBU_CD, CAMPMST65.HONBU_NAME
             , CAMPMST65.JIGYOU_CD, CAMPMST65.JIGYOU_NAME
             , CAMPMST65.SLAREA_CD, CAMPMST65.SLAREA_NAME
/*END*/
             , CAMPMST65.SIBU_CD, CAMPMST65.SIBU_NAME
      /*IF menuTotaledKbn == "TANPIN" || menuTotaledKbn == "SHUYAKU" */
             , CAMPMST65.MENU_CD
      /*END*/
            )
/*END*/
      )
) BD03
/*IF menuTotaledKbn != "TOTAL" */
LEFT JOIN PC10SMNU PC10 ON (BD03.MENU_CD = PC10.MENU_CD)
/*END*/
WHERE BD03.COMPANY_CD = BD13.COMPANY_CD
/*IF companyCd.equals("00") && shukeiKbn.equals("SIBU") && menuTotaledKbn == "TOTAL"*/
AND   (BD03.HONBU_CD = BD13.HONBU_CD OR (BD03.HONBU_CD IS NULL AND BD13.HONBU_CD IS NULL))
AND   (BD03.JIGYOU_CD = BD13.JIGYOU_CD OR (BD03.JIGYOU_CD IS NULL AND BD13.JIGYOU_CD IS NULL))
AND   (BD03.SLAREA_CD = BD13.SLAREA_CD OR (BD03.SLAREA_CD IS NULL AND BD13.SLAREA_CD IS NULL))
/*END*/
AND   (BD03.SIBU_CD = BD13.SIBU_CD OR (BD03.SIBU_CD IS NULL AND BD13.SIBU_CD IS NULL))

ORDER BY COMPANY_CD
/*IF companyCd.equals("00") && shukeiKbn.equals("SIBU")*/
,        HONBU_CD 
,        JIGYOU_CD 
,        SLAREA_CD 
,        SIBU_CD 
,        ROW_TYPE
--ELSE
,        SIBU_CD
,        ROW_TYPE
/*END*/

/*IF menuTotaledKbn != "TOTAL" */
,        MENU_CD
,        MENU_NAME_KJ
/*END*/
