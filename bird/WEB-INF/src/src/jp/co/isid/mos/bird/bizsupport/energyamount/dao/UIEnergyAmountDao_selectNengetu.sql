SELECT R.JIGYOU_CD 
,      rtrim(R.JIGYOU_NAME) as JIGYOU_NAME
,      R.SLAREA_CD 
,      rtrim(R.SLAREA_NAME) as SLAREA_NAME
,      rtrim(R.SIBU_CD) as SIBU_CD 
,      rtrim(R.SIBU_NAME) as SIBU_NAME 
,      R.BLOCK_CD 
,      rtrim(R.BLOCK_NAME) as BLOCK_NAME 
,      R.MISE_CD 
,      rtrim(R.MISE_NAME_KJ) as MISE_NAME_KJ 
,      R.URIAGE 
,      R.ZEN_URIAGE
,      R.EIGYO_DAYS 
,      R.ZEN_EIGYO_DAYS 
,      (CASE WHEN (W.TOUGETU_ELE IS NULL) THEN 0.0 ELSE W.TOUGETU_ELE END) AS ELECTRIC_METER_YOKUGETU 
,      (CASE WHEN (B.ZENGETU_ELE IS NULL) THEN 0.0 ELSE B.ZENGETU_ELE END) AS ELECTRIC_METER 
,      R.TOUNEN_ELE_AMT AS ELECTRIC_AMT
,      R.ZENNEN_ELE_AMT AS ELECTRIC_AMT_ZENNEN
,      R.ELEC AS ELECTRIC_AMT_URIAGEHI 
,      R.ELEC_ZENNEN AS ELECTRIC_AMT_URIAGEHI_ZENNEN 
,      (CASE WHEN (W.TOUGETU_POW IS NULL) THEN 0.0 ELSE W.TOUGETU_POW END) AS POWER_METER_YOKUGETU 
,      (CASE WHEN (B.ZENGETU_POW IS NULL) THEN 0.0 ELSE B.ZENGETU_POW END) AS POWER_METER 
,      R.TOUNEN_POW_AMT AS POWER_AMT 
,      R.ZENNEN_POW_AMT AS POWER_AMT_ZENNEN 
,      R.POWER AS POWER_AMT_URIAGEHI 
,      R.POWER_ZENNEN AS POWER_AMT_URIAGEHI_ZENNEN 
,      (CASE WHEN (W.TOUGETU_GAS IS NULL) THEN 0.0 ELSE W.TOUGETU_GAS END) AS GAS_METER_YOKUGETU 
,      (CASE WHEN (B.ZENGETU_GAS IS NULL) THEN 0.0 ELSE B.ZENGETU_GAS END) AS GAS_METER 
,      R.TOUNEN_GAS_AMT AS GAS_AMT 
,      R.ZENNEN_GAS_AMT AS GAS_AMT_ZENNEN 
,      R.GAS AS GAS_AMT_URIAGEHI 
,      R.GAS_ZENNEN AS GAS_AMT_URIAGEHI_ZENNEN 
,      (CASE WHEN (W.TOUGETU_WAT IS NULL) THEN 0.0 ELSE W.TOUGETU_WAT END) AS WATER_METER_YOKUGETU 
,      (CASE WHEN (B.ZENGETU_WAT IS NULL) THEN 0.0 ELSE B.ZENGETU_WAT END) AS WATER_METER 
,      R.TOUNEN_WAT_AMT AS WATER_AMT 
,      R.ZENNEN_WAT_AMT AS WATER_AMT_ZENNEN
,      R.WATER AS WATER_AMT_URIAGEHI
,      R.WATER_ZENNEN AS WATER_AMT_URIAGEHI_ZENNEN
,      CASE WHEN SUBSTR(R.OPEN_DT, 1, 6) >= /*targetYM*/'200807' THEN 'V' ELSE '' END NEW_OPEN
,      R.DATA_MT_DT 
,      BM68.ELECTRIC_FLG
,      BM68.POWER_FLG
,      BM68.GAS_FLG
,      BM68.WATER_FLG
FROM 
       (SELECT DISTINCT BD08.COMPANY_CD
       ,               BD08.JIGYOU_CD         
       ,               BD08.JIGYOU_NAME       
       ,               BD08.SLAREA_CD         
       ,               BD08.SLAREA_NAME       
       ,               BD08.SIBU_CD           
       ,               BD08.SIBU_NAME         
       ,               BD08.BLOCK_CD          
       ,               BD08.BLOCK_NAME        
       ,               BD08.MISE_CD           
       ,               BD08.MISE_NAME_KJ 
       ,               BD08.OPEN_DT     
       ,               CASE WHEN BT64.URIAGE_DOGETU IS NULL THEN 0 ELSE BT64.URIAGE_DOGETU END AS URIAGE
       ,               CASE WHEN BT64.URIAGE_ZEN_DOGETU IS NULL THEN 0 ELSE BT64.URIAGE_ZEN_DOGETU END AS ZEN_URIAGE 
       ,               CASE WHEN BT64.EIGYO_DAYS_DOGETU IS NULL THEN 0 ELSE BT64.EIGYO_DAYS_DOGETU END AS EIGYO_DAYS
       ,               CASE WHEN BT64.EIGYO_DAYS_ZEN_DOGETU IS NULL THEN 0 ELSE BT64.EIGYO_DAYS_ZEN_DOGETU END AS ZEN_EIGYO_DAYS 
       ,               BD08.ELECTRIC_AMT     AS TOUNEN_ELE_AMT 
       ,               CASE WHEN ZEN_BD08.ELECTRIC_AMT IS NULL THEN 0.0 ELSE ZEN_BD08.ELECTRIC_AMT END AS ZENNEN_ELE_AMT 
       ,               BD08.POWER_AMT        AS TOUNEN_POW_AMT 
       ,               CASE WHEN ZEN_BD08.POWER_AMT IS NULL THEN 0.0 ELSE ZEN_BD08.POWER_AMT END AS ZENNEN_POW_AMT 
       ,               BD08.GAS_AMT          AS TOUNEN_GAS_AMT 
       ,               CASE WHEN ZEN_BD08.GAS_AMT IS NULL THEN 0.0 ELSE ZEN_BD08.GAS_AMT END     AS ZENNEN_GAS_AMT 
       ,               BD08.WATER_AMT        AS TOUNEN_WAT_AMT 
       ,               CASE WHEN ZEN_BD08.WATER_AMT IS NULL THEN 0.0 ELSE ZEN_BD08.WATER_AMT END AS ZENNEN_WAT_AMT 
       ,               (CASE WHEN (BD08.ELECTRIC_AMT = 0 OR BT64.URIAGE_DOGETU =0 OR BT64.URIAGE_DOGETU IS NULL) 
                             THEN 0 
                             ELSE DECIMAL(((DOUBLE(BD08.ELECTRIC_AMT)) / (DOUBLE(((DOUBLE(BT64.URIAGE_DOGETU))/1000000))))+0.005, 16, 2) END) AS ELEC  
       ,               (CASE WHEN (ZEN_BD08.ELECTRIC_AMT = 0 OR ZEN_BD08.ELECTRIC_AMT IS NULL OR BT64.URIAGE_ZEN_DOGETU =0 OR BT64.URIAGE_ZEN_DOGETU IS NULL) 
                             THEN 0 
                             ELSE DECIMAL(((DOUBLE(ZEN_BD08.ELECTRIC_AMT)) / (DOUBLE(((DOUBLE(BT64.URIAGE_ZEN_DOGETU))/1000000))))+0.005, 16, 2) END) AS ELEC_ZENNEN  
       ,               DECIMAL(0.00) AS ELEC_ZENNENHI
       ,               (CASE WHEN (BD08.POWER_AMT = 0 OR BT64.URIAGE_DOGETU =0 OR BT64.URIAGE_DOGETU IS NULL) 
                             THEN 0 
                             ELSE DECIMAL(((DOUBLE(BD08.POWER_AMT)) / (DOUBLE(((DOUBLE(BT64.URIAGE_DOGETU))/1000000))))+0.005, 16, 2) END) AS POWER  
       ,               (CASE WHEN (ZEN_BD08.POWER_AMT = 0 OR ZEN_BD08.POWER_AMT IS NULL OR BT64.URIAGE_ZEN_DOGETU =0 OR BT64.URIAGE_DOGETU IS NULL) 
                             THEN 0 
                             ELSE DECIMAL(((DOUBLE(ZEN_BD08.POWER_AMT)) / (DOUBLE(((DOUBLE(BT64.URIAGE_ZEN_DOGETU))/1000000))))+0.005, 16, 2) END) AS POWER_ZENNEN  
       ,               DECIMAL(0.00) AS POWER_ZENNENHI  
       ,               (CASE WHEN (BD08.GAS_AMT = 0 OR BT64.URIAGE_DOGETU =0 OR BT64.URIAGE_DOGETU IS NULL)           
                             THEN 0 
                             ELSE DECIMAL(((DOUBLE(BD08.GAS_AMT)) / (DOUBLE(((DOUBLE(BT64.URIAGE_DOGETU))/1000000))))+0.005, 16, 2) END) AS GAS  
       ,               (CASE WHEN (ZEN_BD08.GAS_AMT = 0 OR ZEN_BD08.GAS_AMT IS NULL OR BT64.URIAGE_ZEN_DOGETU =0 OR BT64.URIAGE_ZEN_DOGETU IS NULL) 
                             THEN 0 
                             ELSE DECIMAL(((DOUBLE(ZEN_BD08.GAS_AMT)) / (DOUBLE(((DOUBLE(BT64.URIAGE_ZEN_DOGETU))/1000000))))+0.005, 16, 2) END) AS GAS_ZENNEN  
       ,               DECIMAL(0.00) AS GAS_ZENNENHI  
       ,               (CASE WHEN (BD08.WATER_AMT = 0 OR BT64.URIAGE_DOGETU =0 OR BT64.URIAGE_DOGETU IS NULL)           
                             THEN 0 
                             ELSE DECIMAL(((DOUBLE(BD08.WATER_AMT)) / (DOUBLE(((DOUBLE(BT64.URIAGE_DOGETU))/1000000))))+0.005, 16, 2) END) AS WATER  
       ,               (CASE WHEN (ZEN_BD08.WATER_AMT = 0 OR ZEN_BD08.WATER_AMT IS NULL OR BT64.URIAGE_ZEN_DOGETU =0 OR BT64.URIAGE_ZEN_DOGETU IS NULL) 
                             THEN 0 
                             ELSE DECIMAL(((DOUBLE(ZEN_BD08.WATER_AMT)) / (DOUBLE(((DOUBLE(BT64.URIAGE_ZEN_DOGETU))/1000000))))+0.005, 16, 2) END) AS WATER_ZENNEN  
       ,               DECIMAL(0.00) AS WATER_ZENNENHI
       ,               BD08.DATA_MT_DT  
       ,               BD08.METER_KBN       
       FROM            (SELECT BD08.COMPANY_CD
                        ,      BD08.ELECTRIC_AMT              
                        ,      BD08.POWER_AMT                 
                        ,      BD08.GAS_AMT                   
                        ,      BD08.WATER_AMT                 
                        ,      BM01.MISE_CD                   
                        ,      BM01.MISE_NAME_KJ              
                        ,      BD08.METER_YM                  
                        ,      BM10.JIGYOU_CD    AS JIGYOU_CD    
                        ,      BM10.JIGYOU_NAME  AS JIGYOU_NAME  
                        ,      BM10.SLAREA_CD AS SLAREA_CD    
                        ,      BM10.SLAREA_NAME  AS SLAREA_NAME  
                        ,      BM01.SIBU_CD   AS SIBU_CD      
                        ,      BM10.SIBU_NAME    AS SIBU_NAME    
                        ,      BM01.BLOCK_CD  AS BLOCK_CD     
                        ,      BC23.BLOCK_NAME   AS BLOCK_NAME  
                        ,      BM01.OPEN_DT 
                        ,      BD08.DATA_MT_DT
                        ,      BD08.METER_KBN 
                        FROM   BD08ENGY BD08
                        ,      BM01TENM BM01
                        ,      BC23BLCK BC23
                        ,      BM10GSIB BM10
                        WHERE  BD08.COMPANY_CD = /*companyCd*/'00'
                        AND    BM01.COMPANY_CD = BD08.COMPANY_CD
                        AND    BM01.MISE_CD = BD08.MISE_CD    
                        AND    BD08.METER_YM   = /*targetYM*/'200807'
                        AND    substr(BM01.CLOSE_DT, 1, 6)  > /*targetYM*/'200807'
                        AND    substr(BM01.OPEN_DT, 1, 6)   <= /*targetYM*/'200807'     
                        AND    BD08.METER_KBN = /*meterKbn*/'0'
                        AND    BM01.COMPANY_CD = BM10.COMPANY_CD 
                        AND    BM01.SIBU_CD = BM10.SIBU_CD
                /*IF userTypeCd == "01" */
	                /*IF sibuCd != null && !sibuCd.equals("ALL")*/
                        AND    BM01.SIBU_CD      = /*sibuCd*/'031'   
                    /*END*/
                    /*IF sibuCd.equals("ALL") && limitFlg == true */
						AND    BM01.SIBU_CD IN (
						         SELECT   BM51.SIBU_CD
						         FROM     BM51SVSB BM51
						         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
						         AND    BM51.SV_CD      = /*userId*/'99990001'
						         GROUP BY BM51.SIBU_CD
								)                    
	                /*END*/
	            /*END*/
                        AND    BM01.BLOCK_CD     = BC23.BLOCK_CD    
                /*IF userTypeCd == "02" */
                        and  bm01.mise_cd in (
                                  select mise_cd 
                                  from bm01tenm 
                                  where company_cd = /*companyCd*/'00' 
                                  and   oner_cd in (select oner_cd from bm06uonr 
                                                    where  USER_ID = /*userId*/'99990002'   
                                                     AND   COMPANY_CD = bm01tenm.company_cd)
                             )
                /*END*/
                /*IF userTypeCd == "03" */
                        and  bm01.mise_cd in (
                                  select mise_cd 
                                  from bm07uten 
                                  where USER_ID = /*userId*/'99990003' and COMPANY_CD = /*companyCd*/'00'
                             )
                /*END*/
                        ) BD08  
       LEFT JOIN (select eigyo_dt, company_cd, mise_cd, sum(uriage_dogetu) as uriage_dogetu, sum(eigyo_days_dogetu) as eigyo_days_dogetu, sum(uriage_zen_dogetu) as uriage_zen_dogetu, sum(eigyo_days_zen_dogetu) as eigyo_days_zen_dogetu from BT64ZGEP where company_cd = /*companyCd*/'00' and eigyo_dt = /*targetYM*/'200807' group by eigyo_dt, company_cd, mise_cd) BT64 ON (BD08.METER_YM = BT64.EIGYO_DT AND BD08.MISE_CD=BT64.MISE_CD)  
       LEFT JOIN (SELECT * FROM BD08ENGY WHERE company_cd = /*companyCd*/'00' and METER_YM  = /*zenTargetYM*/'200707' AND   METER_KBN = /*meterKbn*/'0' ) ZEN_BD08 ON (BD08.COMPANY_CD = ZEN_BD08.COMPANY_CD AND BD08.MISE_CD=ZEN_BD08.MISE_CD)  
       ) AS R 
LEFT JOIN 
       (SELECT MISE_CD 
       ,      ELECTRIC_METER AS TOUGETU_ELE 
       ,      POWER_METER    AS TOUGETU_POW 
       ,      GAS_METER      AS TOUGETU_GAS 
       ,      WATER_METER    AS TOUGETU_WAT 
       FROM   BD07METM 
       WHERE  COMPANY_CD = /*companyCd*/'00'
       AND    METER_YM = /*targetYMplus1*/'200808'
       AND    METER_KBN = /*meterKbn*/'0'
       ) AS W 
ON     (R.MISE_CD = W.MISE_CD) 
LEFT JOIN 
       (SELECT MISE_CD 
       ,      ELECTRIC_METER AS ZENGETU_ELE 
       ,      POWER_METER    AS ZENGETU_POW 
       ,      GAS_METER      AS ZENGETU_GAS 
       ,      WATER_METER    AS ZENGETU_WAT 
       FROM   BD07METM 
       WHERE  COMPANY_CD = /*companyCd*/'00'
       AND    METER_YM = /*targetYM*/'200807'
       AND    METER_KBN = /*meterKbn*/'0'
       ) AS B 
ON     (R.MISE_CD = B.MISE_CD) 
,  BM68MSET BM68 
WHERE
      R.COMPANY_CD = BM68.COMPANY_CD 
AND   R.MISE_CD = BM68.MISE_CD 
AND   R.METER_KBN = BM68.METER_KBN 

ORDER BY 
/*IF userTypeCd == "01" */
  R.JIGYOU_CD, R.SLAREA_CD, R.SIBU_CD, R.BLOCK_CD, B.MISE_CD  
/*END*/
/*IF userTypeCd != "01" */
  B.MISE_CD
/*END*/