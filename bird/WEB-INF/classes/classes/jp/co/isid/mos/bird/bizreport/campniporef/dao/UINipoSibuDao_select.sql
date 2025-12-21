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
           CASE WHEN BT60.URIAGE <= 0 THEN 0.00 
                WHEN BD03.URIAGE < 0
                THEN ((DOUBLE(BD03.URIAGE)/DOUBLE(BT60.URIAGE)*100)-0.005)
                ELSE ((DOUBLE(BD03.URIAGE)/DOUBLE(BT60.URIAGE)*100)+0.005)
           END, 20, 2) AS KINGAKU_KOUSEI_HI
,      BT60.TAISHO_TENPO_CNT
,      BT60.TENPO_CNT
,      (BT60.TENKO_KBN) AS TENKO_KBN
,      (BT60.URIAGE) AS URIAGE
,      (BT60.KYAKUSU) AS KYAKUSU
,      (BT60.TENKO_KBN_ZEN) AS TENKO_KBN_ZEN
,      (BT60.URIAGE_ZEN) AS URIAGE_ZEN
,      (BT60.KYAKUSU_ZEN) AS KYAKUSU_ZEN
,      BT60.KYAKUTANKA
,      BT60.KYAKUTANKA_ZEN
,      DECIMAL(
           CASE WHEN BT60.URIAGE_ZEN <= 0 THEN 0.00 
                ELSE ((DOUBLE(BT60.URIAGE)/DOUBLE(BT60.URIAGE_ZEN)*100)+0.005)
           END, 20, 2) AS URIAGE_ZEN_HI
,      DECIMAL(
           CASE WHEN BT60.KYAKUSU_ZEN <= 0 THEN 0.00 
                ELSE ((DOUBLE(BT60.KYAKUSU)/DOUBLE(BT60.KYAKUSU_ZEN)*100)+0.005)
           END, 20, 2) AS KYAKUSU_ZEN_HI
,      DECIMAL(
           CASE WHEN BT60.KYAKUTANKA_ZEN <= 0 THEN 0.00 
                ELSE ((DOUBLE(BT60.KYAKUTANKA)/DOUBLE(BT60.KYAKUTANKA_ZEN)*100)+0.005)
           END, 20, 2) AS KYAKUTANKA_ZEN_HI
/*IF zennenDataShubetu.equals("DOGETUHOSEI") */
,      BT60.NET_URIAGE
,      BT60.NET_KYAKUSU
,      BT60.NET_URIAGE_ZEN
,      BT60.NET_KYAKUSU_ZEN
,      BT60.NET_KYAKUTANKA
,      BT60.NET_KYAKUTANKA_ZEN
,      DECIMAL(
           CASE WHEN BT60.NET_URIAGE_ZEN <= 0 THEN 0.00 
                ELSE ((DOUBLE(BT60.NET_URIAGE)/DOUBLE(BT60.NET_URIAGE_ZEN)*100)+0.005)
           END, 20, 2) AS NET_URIAGE_ZEN_HI
,      DECIMAL(
           CASE WHEN BT60.NET_KYAKUSU_ZEN <= 0 THEN 0.00 
                ELSE ((DOUBLE(BT60.NET_KYAKUSU)/DOUBLE(BT60.NET_KYAKUSU_ZEN)*100)+0.005)
           END, 20, 2) AS NET_KYAKUSU_ZEN_HI
,      DECIMAL(
           CASE WHEN BT60.NET_KYAKUTANKA_ZEN <= 0 THEN 0.00 
                ELSE ((DOUBLE(BT60.NET_KYAKUTANKA)/DOUBLE(BT60.NET_KYAKUTANKA_ZEN)*100)+0.005)
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
      ,      MAX(BT60.EIGYO_YM) AS EIGYO_YM
      ,      COUNT(DISTINCT (CASE WHEN BT60.OPEN_KBN = 1 THEN BM01.MISE_CD ELSE null END)) AS TAISHO_TENPO_CNT
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
      ,    (SELECT COMPANY_CD, SUBSTR(EIGYO_DT, 1,6) AS EIGYO_YM, MISE_CD 
	        ,      MAX(OPEN_KBN) AS OPEN_KBN
	        ,      SUM(URIAGE) AS URIAGE
	        ,      SUM(KYAKUSU) AS KYAKUSU
	        ,      SUM(EIGYO_DAYS) AS EIGYO_DAYS
	        ,      MAX(TENKO_KBN) AS TENKO_KBN
/*IF zennenDataShubetu.equals("DOGETU") */
            ,      SUM(URIAGE_ZEN_DOGETU)     AS URIAGE_ZEN
            ,      SUM(EIGYO_DAYS_ZEN_DOGETU) AS EIGYO_DAYS_ZEN
            ,      MAX(TENKO_KBN_ZEN_DOGETU)  AS TENKO_KBN_ZEN
            ,      SUM(KYAKUSU_ZEN_DOGETU)    AS KYAKUSU_ZEN
/*END*/
/*IF zennenDataShubetu.equals("DOJITU") */
            ,      SUM(URIAGE_ZEN_DOJITU)     AS URIAGE_ZEN
            ,      SUM(EIGYO_DAYS_ZEN_DOJITU) AS EIGYO_DAYS_ZEN
            ,      MAX(TENKO_KBN_ZEN_DOJITU)  AS TENKO_KBN_ZEN
            ,      SUM(KYAKUSU_ZEN_DOJITU)    AS KYAKUSU_ZEN
/*END*/
/*IF zennenDataShubetu.equals("DOYO") */
            ,      SUM(URIAGE_ZEN_DOYO)     AS URIAGE_ZEN
            ,      SUM(EIGYO_DAYS_ZEN_DOYO) AS EIGYO_DAYS_ZEN
            ,      MAX(TENKO_KBN_ZEN_DOYO)  AS TENKO_KBN_ZEN
            ,      SUM(KYAKUSU_ZEN_DOYO)    AS KYAKUSU_ZEN
/*END*/
/*IF zennenDataShubetu.equals("DOGETUHOSEI") */
            ,      SUM(URIAGE_ZEN_DOGETU)     AS URIAGE_ZEN
            ,      SUM(EIGYO_DAYS_ZEN_DOGETU) AS EIGYO_DAYS_ZEN
            ,      MAX(TENKO_KBN_ZEN_DOGETU)  AS TENKO_KBN_ZEN
            ,      SUM(KYAKUSU_ZEN_DOGETU)    AS KYAKUSU_ZEN
            ,      SUM(CASE WHEN OPEN_KBN =1 AND OPEN_KBN_ZEN_DOGETU = 1 AND URIAGE_ZEN_DOGETU > 0
                   THEN URIAGE ELSE 0 END) AS NET_URIAGE
            ,      SUM(CASE WHEN OPEN_KBN =1 AND OPEN_KBN_ZEN_DOGETU = 1 AND URIAGE_ZEN_DOGETU > 0
                   THEN KYAKUSU ELSE 0 END)    AS NET_EIGYO_DAYS
            ,      SUM(CASE WHEN OPEN_KBN =1 AND OPEN_KBN_ZEN_DOGETU = 1 AND URIAGE_ZEN_DOGETU > 0
                   THEN EIGYO_DAYS ELSE 0 END) AS NET_KYAKUSU
            ,      SUM(CASE WHEN OPEN_KBN =1 AND OPEN_KBN_ZEN_DOGETU = 1 AND URIAGE > 0
                   THEN URIAGE_ZEN_DOGETU ELSE 0 END)  AS NET_URIAGE_ZEN
            ,      SUM(CASE WHEN OPEN_KBN =1 AND OPEN_KBN_ZEN_DOGETU = 1 AND URIAGE > 0
                   THEN EIGYO_DAYS_ZEN_DOGETU ELSE 0 END) AS NET_EIGYO_DAYS_ZEN
            ,      SUM(CASE WHEN OPEN_KBN =1 AND OPEN_KBN_ZEN_DOGETU = 1 AND URIAGE > 0
                   THEN KYAKUSU_ZEN_DOGETU ELSE 0 END) AS NET_KYAKUSU_ZEN
/*END*/
	        FROM BT60ZNIP
            WHERE COMPANY_CD = /*companyCd*/'00'
/*IF taishoKikan == "DAYS" */
            AND   EIGYO_DT BETWEEN /*taishoDtFrom*/'' AND /*taishoDt*/
--ELSE
            AND   EIGYO_DT = /*taishoDtFrom*/
/*END*/
            AND   OLDM_FLG <> '1'
            AND   OPEN_KBN = 1
/*IF taishoJoken.equals("MISE") */  
            AND   MISE_CD = /*hyojiTaisho*/
/*END*/
	        GROUP BY COMPANY_CD, SUBSTR(EIGYO_DT, 1,6), MISE_CD 
           ) BT60
      ,    (SELECT COMPANY_CD, EIGYO_YM, MISE_CD 
            FROM BN01DTEN
            WHERE COMPANY_CD = /*companyCd*/'00'
/*IF taishoKikan == "DAYS" */
            AND   EIGYO_DT >= /*taishoDtFrom*/'' 
            AND   EIGYO_DT <= /*taishoDt*/
--ELSE
            AND   EIGYO_DT = /*taishoDtFrom*/
/*END*/
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
            AND   KBN1 = /*tenpoShubetu*/''
/*END*/
/*IF tenpoShubetu == "2" */
            AND   KBN1 IN ('1', '2')
/*END*/
             GROUP BY COMPANY_CD, EIGYO_YM, MISE_CD
            ) BN01
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
      AND   BN01.COMPANY_CD = BM01.COMPANY_CD
      AND   BT60.COMPANY_CD = BN01.COMPANY_CD
      AND   BM01.MISE_CD = BM65.MISE_CD
      AND   BN01.MISE_CD = BM01.MISE_CD
      AND   BT60.MISE_CD = BN01.MISE_CD
	  AND   BT60.EIGYO_YM = BN01.EIGYO_YM
      GROUP BY ROLLUP ((BM01.COMPANY_CD)
/*IF companyCd.equals("00") && shukeiKbn.equals("SIBU") && menuTotaledKbn == "TOTAL"*/
      ,      (BM01.COMPANY_CD, BM01.HONBU_CD)
      ,      (BM01.COMPANY_CD, BM01.JIGYOU_CD)
      ,      (BM01.COMPANY_CD, BM01.SLAREA_CD)
/*END*/
      ,      (BM01.COMPANY_CD, BM01.SIBU_CD)
      )
     ) BT60
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
/*IF "BD12CPRI".equals(targetTable)==false */
      ,      BN01.EIGYO_YM
/*END*/
      ,      BN01.KBN1
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
      ,    (
             SELECT COMPANY_CD, EIGYO_YM, MISE_CD , KBN1
             FROM BN01DTEN
             WHERE COMPANY_CD = /*companyCd*/'00'
/*IF taishoKikan == "DAYS" */
             AND   EIGYO_DT >= /*taishoDtFrom*/'' 
             AND   EIGYO_DT <= /*taishoDt*/
--ELSE
             AND   EIGYO_DT = /*taishoDtFrom*/
/*END*/
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
             AND   KBN1 = /*tenpoShubetu*/''
/*END*/
/*IF tenpoShubetu == "2" */
             AND   KBN1 IN ('1', '2')
/*END*/
             GROUP BY COMPANY_CD, EIGYO_YM, MISE_CD, KBN1
           ) BN01
        WHERE BM65.CAMP_ID    = /*campId*/'200806'
        AND   BM65.COMPANY_CD    = /*companyCd*/'00'
        AND   BM61.CAMP_ID    = BM65.CAMP_ID    
        AND   BM65.COMPANY_CD = BM01.COMPANY_CD
        AND   BN01.COMPANY_CD = BM65.COMPANY_CD
        AND   BM65.MISE_CD    = BM01.MISE_CD
        AND   BN01.MISE_CD    = BM65.MISE_CD
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
/*IF "BD12CPRI".equals(targetTable)==false */
        ,      BN01.EIGYO_YM
/*END*/
        ,      BN01.KBN1
/*IF menuTotaledKbn == "TOTAL" ||  menuTotaledKbn == "SHUYAKU" */
        ,      (CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BM61.MENU_CD ELSE BM62.SUM_MENU_CD END)
/*END*/
/*IF menuTotaledKbn == "TANPIN" */
        ,      BM61.MENU_CD 
/*END*/
    ) CAMPMST65
    LEFT JOIN (
        SELECT BD03.COMPANY_CD
/*IF "BD12CPRI".equals(targetTable)==false */
        ,      SUBSTR(BD03.MENU_DT, 1,6) AS MENU_YM
/*END*/
        ,      BD03.MISE_CD 
/*IF "BD12CPRI".equals(targetTable) */
		,      SUM(BD03.URIAGE) AS URIAGE
--ELSE
	/*IF taishoKikan == "DAYS" */
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
    --ELSE
       	,      SUM(CASE WHEN BD03.URIAGE IS NULL THEN 0
                        ELSE BD03.URIAGE 
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
		/*IF taishoKikan == "DAYS" */
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
	    --ELSE
       	,      SUM(CASE WHEN BD03.KAZU_KEI IS NULL THEN 0
                        ELSE BD03.KAZU_KEI
                   END) AS KAZU_KEI
	    /*END*/
	/*END*/
--ELSE
        ,      (CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.MENU_CD ELSE BM62.SUM_MENU_CD END) AS MENU_CD
	/*IF "BD12CPRI".equals(targetTable) */
        ,      SUM((CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.KAZU_KEI ELSE BD03.KAZU_KEI*BM62.CONV_VALUE END)) AS KAZU_KEI
	--ELSE
		/*IF taishoKikan == "DAYS" */
			/*IF tenpoShubetu == "1"*/
       	,      SUM(CASE WHEN MENU_DT = /*taishoDt*/'20080515' THEN (CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.KAZU_KEI_RUI_ZEN ELSE BD03.KAZU_KEI_RUI_ZEN*BM62.CONV_VALUE END)
                        ELSE ((CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.KAZU_KEI_RUI_ZEN ELSE BD03.KAZU_KEI_RUI_ZEN*BM62.CONV_VALUE END)*-1) 
                   END) AS KAZU_KEI
            --ELSE
       	,      SUM(CASE WHEN MENU_DT = /*taishoDt*/'20080515' THEN (CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.KAZU_KEI_RUI ELSE BD03.KAZU_KEI_RUI*BM62.CONV_VALUE END)
                        ELSE ((CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.KAZU_KEI_RUI ELSE BD03.KAZU_KEI_RUI*BM62.CONV_VALUE END)*-1) 
                   END) AS KAZU_KEI
            /*END*/
	    --ELSE
       	,      SUM((CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.KAZU_KEI ELSE BD03.KAZU_KEI*BM62.CONV_VALUE END)) AS KAZU_KEI
	    /*END*/
	/*END*/
/*END*/
        FROM BM61CPMN BM61
        ,    BM65CPMS BM65
/*IF "BD12CPRI".equals(targetTable) */
        ,    BD12CPRI BD03
--ELSE
        ,    BD03CPML BD03
/*END*/
/*IF menuTotaledKbn == "TOTAL" ||  menuTotaledKbn == "SHUYAKU" */
        LEFT JOIN BM62SYMM BM62
        ON (BM62.MENU_CD = BD03.MENU_CD)
/*END*/
        WHERE BM65.CAMP_ID    = /*campId*/'200806'
        AND   BD03.COMPANY_CD = /*companyCd*/'00'
/*IF taishoKikan == "DAYS" */
	/*IF "BD12CPRI".equals(targetTable)==false */
        AND  (BD03.MENU_DT =/*taishoDtFromLastDay*/'20080327' OR BD03.MENU_DT =/*taishoDt*/'20080515')
   /*END*/
--ELSE
        AND   BD03.MENU_DT = /*taishoDtFrom*/'20080515'
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
/*IF "BD12CPRI".equals(targetTable)==false */
        ,        SUBSTR(BD03.MENU_DT, 1,6) 
/*END*/
        ,        BD03.MISE_CD 
/*IF menuTotaledKbn == "TANPIN" */
        ,      BD03.MENU_CD
--ELSE
        ,      (CASE WHEN BM62.SUM_MENU_CD IS NULL THEN BD03.MENU_CD ELSE BM62.SUM_MENU_CD END)
/*END*/
    ) CAMP03
       ON (      CAMP03.COMPANY_CD = CAMPMST65.COMPANY_CD 
           AND   CAMP03.MISE_CD = CAMPMST65.MISE_CD
/*IF "BD12CPRI".equals(targetTable)==false */
           AND   CAMP03.MENU_YM = CAMPMST65.EIGYO_YM
/*END*/
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
WHERE BD03.COMPANY_CD = BT60.COMPANY_CD
/*IF companyCd.equals("00") && shukeiKbn.equals("SIBU") && menuTotaledKbn == "TOTAL"*/
AND   (BD03.HONBU_CD = BT60.HONBU_CD OR (BD03.HONBU_CD IS NULL AND BT60.HONBU_CD IS NULL))
AND   (BD03.JIGYOU_CD = BT60.JIGYOU_CD OR (BD03.JIGYOU_CD IS NULL AND BT60.JIGYOU_CD IS NULL))
AND   (BD03.SLAREA_CD = BT60.SLAREA_CD OR (BD03.SLAREA_CD IS NULL AND BT60.SLAREA_CD IS NULL))
/*END*/
AND   (BD03.SIBU_CD = BT60.SIBU_CD OR (BD03.SIBU_CD IS NULL AND BT60.SIBU_CD IS NULL))

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
