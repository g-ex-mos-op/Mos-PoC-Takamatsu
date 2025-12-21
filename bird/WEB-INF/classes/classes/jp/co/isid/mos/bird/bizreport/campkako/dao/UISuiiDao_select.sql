select 
       BN01.EIGYO_DT AS EIGYO_DT
/*IF taishoJoken.equals("MISE") */
,      MAX(BM01.MISE_NAME_KJ) AS MISE_NAME_KJ
,      MAX(CASE WHEN BN01.TENKO_KBN IS NULL THEN 0 ELSE BN01.TENKO_KBN END) AS TENKO_KBN
,      MAX(CASE WHEN BN01.TENKO_KBN_ZEN IS NULL THEN 0 ELSE BN01.TENKO_KBN_ZEN END)  AS TENKO_KBN_ZEN
/*END*/
,      COUNT(DISTINCT (CASE WHEN BN01.OPEN_KBN = 1 THEN BM01.MISE_CD ELSE null END)) TAISHO_TENPO_CNT
,	   SUM(CASE WHEN BD04.KAZU_KEI IS NULL THEN 0 ELSE BD04.KAZU_KEI END) AS KAZU_KEI
,	   SUM(CASE WHEN BD04.MENU_URIAGE IS NULL THEN 0 ELSE BD04.MENU_URIAGE END) AS MENU_URIAGE
,      SUM(CASE WHEN BN01.URIAGE IS NULL THEN 0 ELSE BN01.URIAGE END) AS URIAGE
,      SUM(CASE WHEN BN01.KYAKUSU IS NULL THEN 0 ELSE BN01.KYAKUSU END)  AS KYAKUSU
,      SUM(CASE WHEN BN01.URIAGE_ZEN IS NULL THEN 0 ELSE BN01.URIAGE_ZEN END)   AS URIAGE_ZEN
,      SUM(CASE WHEN BN01.KYAKUSU_ZEN IS NULL THEN 0 ELSE BN01.KYAKUSU_ZEN END)  AS KYAKUSU_ZEN
FROM 
( 
      SELECT SUBBM01.COMPANY_CD
      ,      SUBBM01.SIBU_CD 
      ,      SUBBM01.MISE_CD
      ,      SUBBM01.ONER_CD
      ,      SUBBM01.MISE_NAME_KJ
      FROM BM01TENM SUBBM01
      ,    BM10GSIB BM10
      WHERE SUBBM01.COMPANY_CD = /*companyCd*/'00'
/*IF taishoJoken.equals("JIGYOU") */  
      AND   BM10.JIGYOU_CD = /*hyojiTaisho*/'11000'
/*END*/

/*IF taishoJoken.equals("SIBU") */  
      AND   SUBBM01.SIBU_CD = /*hyojiTaisho*/'031'
/*IF blockCd != null */
      AND   SUBBM01.BLOCK_CD = /*blockCd*/'001'
/*END*/
/*END*/
/*IF taishoJoken.equals("SLAREA") */  
      AND   BM10.SLAREA_CD = /*hyojiTaisho*/'10001'
/*END*/

/*IF taishoJoken.equals("MISE") */  
      AND   SUBBM01.MISE_CD = /*hyojiTaisho*/'02001'
/*END*/
      AND    BM10.COMPANY_CD  = SUBBM01.COMPANY_CD
      AND    BM10.SIBU_CD     = SUBBM01.SIBU_CD
/*IF limitFlg*/
      AND    BM10.SIBU_CD IN (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD      = /*userId*/'99990001'
         GROUP BY BM51.SIBU_CD
      )
/*END*/
      GROUP BY SUBBM01.COMPANY_CD
      ,        SUBBM01.SIBU_CD 
      ,        SUBBM01.MISE_CD
      ,        SUBBM01.ONER_CD
      ,        SUBBM01.MISE_NAME_KJ
) BM01
,(
       SELECT DISTINCT
                BN01.EIGYO_DT
         ,      BN01.MISE_CD
         ,      BT60.OPEN_KBN
         ,      BT60.URIAGE
         ,      BT60.KYAKUSU
         ,      BT60.TENKO_KBN
         ,      BT60.URIAGE_ZEN_DOYO     AS URIAGE_ZEN
         ,      BT60.TENKO_KBN_ZEN_DOYO  AS TENKO_KBN_ZEN
         ,      BT60.KYAKUSU_ZEN_DOYO    AS KYAKUSU_ZEN
       FROM BN01DTEN BN01
       ,    BT60ZNIP BT60 
       ,    BM65CPMS BM65
       WHERE  BN01.EIGYO_DT BETWEEN /*kikanFrom*/'20071201' AND /*kikanTo*/'20071231'
       AND   BN01.COMPANY_CD = /*companyCd*/'00'
/*IF taishoJoken.equals("MISE") */  
       AND   BN01.MISE_CD = /*hyojiTaisho*/'02001'
/*END*/
       AND   BN01.COMPANY_CD = BT60.COMPANY_CD
       AND   BN01.EIGYO_DT   = BT60.EIGYO_DT
       AND   BN01.MISE_CD    = BT60.MISE_CD
       AND   BT60.OLDM_FLG <> '1'
       AND   OPEN_KBN = 1
       AND   BM65.CAMP_ID = /*campId*/'200832'
       AND   BM65.COMPANY_CD = BN01.COMPANY_CD
       AND   BM65.MISE_CD = BN01.MISE_CD
) BN01
LEFT OUTER JOIN 
(
         SELECT BM61.CAMP_ID
         ,		BD04.MENU_DT 
         ,      BD04.MISE_CD
         ,      SUM(BD04.URIAGE) AS MENU_URIAGE
/*IF totaledKbn == "TANPIN" */
         ,      SUM(BD04.KAZU_KEI) AS KAZU_KEI
/*END*/
/*IF (totaledKbn == "SHUYAKU" || totaledKbn == "TOTAL")*/
		 ,      SUM(BD04.KAZU_KEI * (case when bm69.conv_value is null then 1 else bm69.conv_value end)) AS KAZU_KEI
/*END*/
         FROM BD04CPST BD04
         ,    BM65CPMS BM65
         ,    BM61CPMN BM61
/*IF totaledKbn.equals("SHUYAKU") || totaledKbn.equals("TOTAL")*/
		 left outer join bm69symp bm69 on (bm61.camp_id = bm69.camp_id and bm61.menu_cd = bm69.menu_cd)
/*END*/
         WHERE BD04.CAMP_ID = /*campId*/'200832'
         AND   BD04.COMPANY_CD = /*companyCd*/'00'
         AND   BD04.MENU_DT >=/*kikanFrom*/'20071201' 
         AND   BD04.MENU_DT <=/*kikanTo*/'20071231'
/*IF taishoJoken.equals("MISE") */  
         AND   BD04.MISE_CD = /*hyojiTaisho*/'02001'
/*END*/
/*IF totaledKbn.equals("TANPIN") */
		 AND   BD04.MENU_CD = /*menuCd*/'220001'
/*END*/
         AND   BM61.CAMP_ID = BD04.CAMP_ID
         AND   BM61.MENU_CD = BD04.MENU_CD
         AND   BD04.MISE_CD = BM65.MISE_CD
         AND   BM65.CAMP_ID = BM61.CAMP_ID
         AND   BM65.COMPANY_CD = BD04.COMPANY_CD
/*IF totaledKbn.equals("SHUYAKU")*/
		 AND   (case when bm69.sum_menu_cd is null then bm61.menu_cd else bm69.sum_menu_cd end) = /*menuCd*/'220001'
/*END*/
         GROUP BY BM61.CAMP_ID
         ,        BD04.MENU_DT
         ,        BD04.MISE_CD
) BD04 ON (BN01.EIGYO_DT = BD04.MENU_DT AND BN01.MISE_CD = BD04.MISE_CD)
WHERE BN01.MISE_CD  = BM01.MISE_CD
GROUP BY ROLLUP (BN01.EIGYO_DT)
ORDER BY BN01.EIGYO_DT