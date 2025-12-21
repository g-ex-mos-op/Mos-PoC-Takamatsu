SELECT MH.HONBU_CD 
,      rtrim(MH.HONBU_NAME) as honbu_name
,      rtrim(MH.JIGYOU_CD) as jigyou_cd 
,      rtrim(MH.JIGYOU_NAME) as jigyou_name 
,      rtrim(MH.SLAREA_CD) as slarea_cd 
,      rtrim(MH.SLAREA_NAME) slarea_name 
,      rtrim(MH.SIBU_CD) as sibu_cd 
,      rtrim(MH.SIBU_NAME) as sibu_name 
,      rtrim(MH.MISE_CD) as mise_cd 
,      rtrim(MH.MISE_NAME_KJ) as mise_name_kj 
,      (CASE WHEN(MH.TOUGETU_ELE IS NULL) THEN 0.0 ELSE MH.TOUGETU_ELE END) AS ELECTRIC_METER 
,      (CASE WHEN(M.ELECTRIC_METER IS NULL) THEN 0.0 ELSE M.ELECTRIC_METER END) AS ELECTRIC_METER_ZENGETU 
,      MH.ELECTRIC_AMT 
,      (CASE WHEN(MH.TOUGETU_POW IS NULL) THEN 0.0 ELSE MH.TOUGETU_POW END) AS POWER_METER 
,      (CASE WHEN(M.POWER_METER IS NULL) THEN 0.0 ELSE M.POWER_METER END) AS POWER_METER_ZENGETU 
,      MH.POWER_AMT 
,      (CASE WHEN(MH.TOUGETU_GAS IS NULL) THEN 0.0 ELSE MH.TOUGETU_GAS END) AS GAS_METER 
,      (CASE WHEN(M.GAS_METER IS NULL) THEN 0.0 ELSE M.GAS_METER END) AS GAS_METER_ZENGETU 
,      MH.GAS_AMT 
,      (CASE WHEN(MH.TOUGETU_WAT IS NULL) THEN 0.0 ELSE MH.TOUGETU_WAT END) AS WATER_METER 
,      (CASE WHEN(M.WATER_METER IS NULL) THEN 0.0 ELSE M.WATER_METER END) AS WATER_METER_ZENGETU 
,      MH.WATER_AMT 
,      MH.ELECTRIC_FLG 
,      MH.POWER_FLG 
,      MH.GAS_FLG 
,      MH.WATER_FLG 
,      CASE WHEN SUBSTR(MH.OPEN_DT, 1, 6) >= /*targetYMZengetu*/'200807' THEN 'êV' ELSE '' END NEW_OPEN
FROM 
       (SELECT H.HONBU_CD 
       ,      H.HONBU_NAME 
       ,      H.JIGYOU_CD 
       ,      H.JIGYOU_NAME 
       ,      H.SLAREA_CD 
       ,      H.SLAREA_NAME 
       ,      H.SIBU_CD 
       ,      H.SIBU_NAME 
       ,      H.MISE_CD 
       ,      H.MISE_NAME_KJ 
       ,      M.ELECTRIC_METER     AS     TOUGETU_ELE 
       ,      H.ELECTRIC_AMT 
       ,      M.POWER_METER        AS     TOUGETU_POW 
       ,      H.POWER_AMT 
       ,      M.GAS_METER          AS     TOUGETU_GAS 
       ,      H.GAS_AMT 
       ,      M.WATER_METER        AS     TOUGETU_WAT 
       ,      H.WATER_AMT 
       ,      H.ELECTRIC_FLG 
       ,      H.POWER_FLG 
       ,      H.GAS_FLG 
       ,      H.WATER_FLG 
       ,      H.OPEN_DT
       FROM 
              (SELECT DISTINCT BM10.HONBU_CD    AS HONBU_CD      
              ,               BM10.HONBU_NAME   AS HONBU_NAME    
              ,               BM10.JIGYOU_CD    AS JIGYOU_CD     
              ,               BM10.JIGYOU_NAME  AS JIGYOU_NAME   
              ,               BM10.SLAREA_CD    AS SLAREA_CD     
              ,               BM10.SLAREA_NAME  AS SLAREA_NAME   
              ,               BM01.SIBU_CD      AS SIBU_CD       
              ,               BM10.SIBU_NAME    AS SIBU_NAME     
              ,               BM01.MISE_CD      AS MISE_CD       
              ,               BM01.MISE_NAME_KJ AS MISE_NAME_KJ  
              ,               BD08.ELECTRIC_AMT AS ELECTRIC_AMT  
              ,               BD08.POWER_AMT    AS POWER_AMT     
              ,               BD08.GAS_AMT      AS GAS_AMT       
              ,               BD08.WATER_AMT    AS WATER_AMT     
              ,               BM68.ELECTRIC_FLG AS ELECTRIC_FLG  
              ,               BM68.POWER_FLG    AS POWER_FLG     
              ,               BM68.GAS_FLG      AS GAS_FLG       
              ,               BM68.WATER_FLG    AS WATER_FLG
              ,               BM01.OPEN_DT      AS OPEN_DT     
              FROM            BM01TENM BM01     
              ,               BM10GSIB BM10
              ,               BD08ENGY BD08  
              ,               BM68MSET BM68
              WHERE           BD08.COMPANY_CD = /*companyCd*/'00'
              AND             BD08.COMPANY_CD = BM01.COMPANY_CD
              AND             BD08.MISE_CD    = BM01.MISE_CD       
              AND             BD08.METER_KBN  = /*meterKbn*/'0'              
              AND             BM01.COMPANY_CD = BM10.COMPANY_CD
              AND             BM01.SIBU_CD    = BM10.SIBU_CD       
              AND             BD08.METER_YM   = /*targetYMZengetu*/'200806'
              AND             BD08.COMPANY_CD = BM68.COMPANY_CD
              AND             BD08.MISE_CD    = BM68.MISE_CD
              AND             BD08.METER_KBN  = BM68.METER_KBN
        /*IF sibuCd != null  && !sibuCd.equals("ALL")*/
              AND             BM01.SIBU_CD      = /*sibuCd*/'031'
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
              AND             SUBSTR(BM01.OPEN_DT,1,6) <= /*targetYMZengetu*/'200806' 
              AND             SUBSTR(BM01.CLOSE_DT,1,6) > /*targetYMZengetu*/'200806'
              AND             (  
                                   (BD08.ELECTRIC_AMT = 0 AND BM68.ELECTRIC_FLG = '1')
                               OR  (BD08.POWER_AMT = 0 AND BM68.POWER_FLG = '1')
                               OR  (BD08.GAS_AMT = 0 AND BM68.GAS_FLG = '1')
                               OR  (BD08.WATER_AMT = 0 AND BM68.WATER_FLG = '1')  
                              )  
              )AS H 
       LEFT JOIN 
              (SELECT MISE_CD 
              ,       ELECTRIC_METER 
              ,       POWER_METER 
              ,       GAS_METER 
              ,       WATER_METER 
              FROM 
                   BD07METM AS M 
              WHERE 
                      COMPANY_CD = /*companyCd*/'00'
              AND     METER_YM = /*targetYM*/'200807'
              AND     METER_KBN = /*meterKbn*/'0'
              ) AS M 
       ON 
              H.MISE_CD = M.MISE_CD 
       ) AS MH 
       LEFT JOIN 
       (SELECT M.MISE_CD 
       ,       M.ELECTRIC_METER 
       ,       M.POWER_METER 
       ,       M.GAS_METER 
       ,       M.WATER_METER 
       FROM 
               BD07METM AS M 
       WHERE 
               COMPANY_CD = /*companyCd*/'00'
       AND     M.METER_YM = /*targetYMZengetu*/'200806'
       AND     M.METER_KBN = /*meterKbn*/'0'
       ) AS M 
       ON 
       MH.MISE_CD = M.MISE_CD 
ORDER BY 
       MH.JIGYOU_CD, MH.SLAREA_CD, MH.SIBU_CD, MH.MISE_CD  
