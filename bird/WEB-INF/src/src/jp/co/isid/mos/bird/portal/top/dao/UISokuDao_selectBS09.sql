SELECT
    CASE WHEN BS09.DT IS NULL THEN /*nendo*/'2008' ELSE BS09.DT END AS EIGYO_DT
,       CASE WHEN BS09.DT IS NULL THEN '3' 
             WHEN BS09.DT = /*appMonth*/'200812' THEN '2' 
             ELSE '1' END AS DT_TYPE
,       CASE WHEN BS09.DT IS NULL THEN '“–Šú' 
             WHEN BS09.DT = /*appMonth*/'200812' THEN '“–ŒŽ' 
             ELSE '–{“ú' END AS DT_NAME
,		BS09.SEGMENT_TYPE
,		CASE WHEN BS09.SEGMENT_TYPE = 'Z' THEN '‘SŽÐ' ELSE BM08SGTP.SEGMENT_NAME END AS SEGMENT_NAME
,		CASE WHEN BS09.SEGMENT_TYPE = 'Z' THEN '00' ELSE BM08SGTP.SORT_SEQ END AS SORT_SEQ
,		BS09.KIZON_FLG
,		BS09.TENPO_COUNT
,		BS09.ZEN_TENPO_COUNT
,		BS09.URIAGE
,		BS09.YOSAN
,		BS09.YOSANHI_MIKOMI
,		BS09.ZEN_URIAGE
,		BS09.ZEN_URIAGE_DOUJITU
,		BS09.KYAKUSU
,		BS09.ZEN_KYAKUSU
,		BS09.ZEN_KYAKUSU_DOUJITU
,      DECIMAL(
           CASE WHEN BS09.YOSAN <= 0 THEN 0.00 
                WHEN BS09.URIAGE <= 0 THEN 0.00 
                ELSE ((DOUBLE(BS09.URIAGE)/DOUBLE(BS09.YOSAN)*100)+0.005)
           END, 20, 2) AS YOSAN_HI
,      DECIMAL(
           CASE WHEN BS09.ZEN_URIAGE <= 0 THEN 0.00 
                WHEN BS09.URIAGE <= 0 THEN 0.00 
                ELSE ((DOUBLE(BS09.URIAGE)/DOUBLE(BS09.ZEN_URIAGE)*100)+0.005)
           END, 20, 2) AS URIAGE_ZEN_HI
,      DECIMAL(
           CASE WHEN BS09.ZEN_URIAGE_DOUJITU <= 0 THEN 0.00 
                WHEN BS09.URIAGE <= 0 THEN 0.00 
                ELSE ((DOUBLE(BS09.URIAGE)/DOUBLE(BS09.ZEN_URIAGE_DOUJITU)*100)+0.005)
           END, 20, 2) AS URIAGE_ZEN_HI_DOUJITU
,      DECIMAL(
           CASE WHEN BS09.ZEN_KYAKUSU <= 0 THEN 0.00 
                WHEN BS09.KYAKUSU <= 0 THEN 0.00 
                ELSE ((DOUBLE(BS09.KYAKUSU)/DOUBLE(BS09.ZEN_KYAKUSU)*100)+0.005)
           END, 20, 2) AS KYAKUSU_ZEN_HI
,      DECIMAL(
           CASE WHEN BS09.ZEN_KYAKUSU_DOUJITU <= 0 THEN 0.00 
                WHEN BS09.KYAKUSU <= 0 THEN 0.00 
                ELSE ((DOUBLE(BS09.KYAKUSU)/DOUBLE(BS09.ZEN_KYAKUSU_DOUJITU)*100)+0.005)
           END, 20, 2) AS KYAKUSU_ZEN_HI_DOUJITU
,      DECIMAL(
           CASE WHEN BS09.KYAKUSU <= 0 THEN 0.00 
                ELSE ((DOUBLE(BS09.URIAGE )/DOUBLE(BS09.KYAKUSU))+0.5)
           END, 11, 0) AS KYAKUTANKA

,      DECIMAL(
           CASE WHEN BS09.ZEN_KYAKUSU <= 0 THEN 0.00 
                ELSE ((DOUBLE(BS09.ZEN_URIAGE )/DOUBLE(BS09.ZEN_KYAKUSU))+0.5)
           END, 11, 0) AS ZEN_KYAKUTANKA

,      DECIMAL(
           CASE WHEN BS09.ZEN_KYAKUSU_DOUJITU <= 0 THEN 0.00 
                ELSE ((DOUBLE(BS09.ZEN_URIAGE_DOUJITU )/DOUBLE(BS09.ZEN_KYAKUSU_DOUJITU))+0.5)
           END, 11, 0) AS ZEN_KYAKUTANKA_DOUJITU

,      DECIMAL(
           CASE WHEN BS09.KYAKUSU <= 0 THEN 0.00 
                WHEN BS09.ZEN_KYAKUSU <= 0 THEN 0.00 
                ELSE ((DECIMAL(
                         ((DOUBLE(BS09.URIAGE )/DOUBLE(BS09.KYAKUSU))+0.5)
                       , 11,0) 
                      /DECIMAL(
                         ((DOUBLE(BS09.ZEN_URIAGE )/DOUBLE(BS09.ZEN_KYAKUSU))+0.5)
                       , 11,0)
                         *100
                      )+0.005) 
           END, 10, 2) AS KYAKUTANKA_ZEN_HI
,      DECIMAL(
           CASE WHEN BS09.KYAKUSU <= 0 THEN 0.00 
                WHEN BS09.ZEN_KYAKUSU_DOUJITU <= 0 THEN 0.00 
                ELSE ((DECIMAL(
                         ((DOUBLE(BS09.URIAGE )/DOUBLE(BS09.KYAKUSU))+0.5)
                       , 11,0) 
                      /DECIMAL(
                         ((DOUBLE(BS09.ZEN_URIAGE_DOUJITU)/DOUBLE(BS09.ZEN_KYAKUSU_DOUJITU))+0.5)
                       , 11,0)
                         *100
                      )+0.005) 
           END, 10, 2) AS KYAKUTANKA_ZEN_HI_DOUJITU
,      URIAGE_NET
,      ZEN_URIAGE_NET
,      DECIMAL(
           CASE WHEN BS09.ZEN_URIAGE_NET <= 0 THEN 0.00 
                WHEN BS09.URIAGE_NET <= 0 THEN 0.00 
                ELSE ((DOUBLE(BS09.URIAGE_NET)/DOUBLE(BS09.ZEN_URIAGE_NET)*100)+0.005)
           END, 20, 2) AS URIAGE_ZEN_HI_NET
,      KYAKUSU_NET
,      ZEN_KYAKUSU_NET
,      DECIMAL(
           CASE WHEN BS09.ZEN_KYAKUSU_NET <= 0 THEN 0.00 
                WHEN BS09.KYAKUSU_NET <= 0 THEN 0.00 
                ELSE ((DOUBLE(BS09.KYAKUSU_NET)/DOUBLE(BS09.ZEN_KYAKUSU_NET)*100)+0.005)
           END, 20, 2) AS KYAKUSU_ZEN_HI_NET
,      DECIMAL(
           CASE WHEN BS09.KYAKUSU_NET <= 0 THEN 0.00 
                ELSE ((DOUBLE(BS09.URIAGE_NET )/DOUBLE(BS09.KYAKUSU_NET))+0.5)
           END, 11, 0) AS KYAKUTANKA_NET

,      DECIMAL(
           CASE WHEN BS09.ZEN_KYAKUSU_NET <= 0 THEN 0.00 
                ELSE ((DOUBLE(BS09.ZEN_URIAGE_NET )/DOUBLE(BS09.ZEN_KYAKUSU_NET))+0.5)
           END, 11, 0) AS ZEN_KYAKUTANKA_NET
,      DECIMAL(
           CASE WHEN BS09.KYAKUSU_NET <= 0 THEN 0.00 
                WHEN BS09.ZEN_KYAKUSU_NET <= 0 THEN 0.00 
                ELSE ((DECIMAL(
                         ((DOUBLE(BS09.URIAGE_NET )/DOUBLE(BS09.KYAKUSU_NET))+0.5)
                       , 11,0) 
                      /DECIMAL(
                         ((DOUBLE(BS09.ZEN_URIAGE_NET )/DOUBLE(BS09.ZEN_KYAKUSU_NET))+0.5)
                       , 11,0)
                         *100
                      )+0.005) 
           END, 10, 2) AS KYAKUTANKA_ZEN_HI_NET

FROM (
    (SELECT DT
     ,      COMPANY_CD
     ,      SEGMENT_TYPE
     ,      KIZON_FLG
     ,      URIAGE
     ,      YOSAN
     ,      YOSANHI_MIKOMI
     ,      ZEN_URIAGE
     ,      ZEN_URIAGE_DOUJITU
     ,      KYAKUSU
     ,      ZEN_KYAKUSU
     ,      ZEN_KYAKUSU_DOUJITU
     ,      TENPO_COUNT
     ,      ZEN_TENPO_COUNT
     ,      URIAGE_NET
     ,      ZEN_URIAGE_NET
     ,      KYAKUSU_NET
     ,      ZEN_KYAKUSU_NET
     FROM BS09SOKU 
     WHERE DT = /*appDate*/'20081214') 
    UNION ALL
    (SELECT  DT
	     ,      COMPANY_CD
	     ,      SEGMENT_TYPE
	     ,      KIZON_FLG
	     ,      SUM(URIAGE) AS URIAGE
	     ,      SUM(YOSAN) AS YOSAN
	     ,      SUM(YOSANHI_MIKOMI) AS YOSANHI_MIKOMI
	     ,      SUM(ZEN_URIAGE) AS ZEN_URIAGE
	     ,      SUM(ZEN_URIAGE_DOUJITU) AS ZEN_URIAGE_DOUJITU
	     ,      SUM(KYAKUSU) AS KYAKUSU
	     ,      SUM(ZEN_KYAKUSU) AS ZEN_KYAKUSU
	     ,      SUM(ZEN_KYAKUSU_DOUJITU) AS ZEN_KYAKUSU_DOUJITU
	     ,      SUM(TENPO_COUNT) AS TENPO_COUNT
	     ,      SUM(ZEN_TENPO_COUNT) AS ZEN_TENPO_COUNT
	     ,      SUM(URIAGE_NET) AS URIAGE_NET
	     ,      SUM(ZEN_URIAGE_NET) AS ZEN_URIAGE_NET
	     ,      SUM(KYAKUSU_NET) AS KYAKUSU_NET
	     ,      SUM(ZEN_KYAKUSU_NET) AS ZEN_KYAKUSU_NET
	     FROM (SELECT SUBSTR(DT,1,6) DT
             ,      COMPANY_CD
             ,      SEGMENT_TYPE
             ,      KIZON_FLG
             ,      URIAGE
             ,      YOSAN
             ,      YOSANHI_MIKOMI
             ,      ZEN_URIAGE
             ,      ZEN_URIAGE_DOUJITU
             ,      KYAKUSU
             ,      ZEN_KYAKUSU
             ,      ZEN_KYAKUSU_DOUJITU
             ,      TENPO_COUNT
             ,      ZEN_TENPO_COUNT
             ,      URIAGE_NET
             ,      ZEN_URIAGE_NET
             ,      KYAKUSU_NET
             ,      ZEN_KYAKUSU_NET
             FROM BS09SOKU
	     WHERE DT BETWEEN /*nendoStartDate*/'20080401' AND /*appDate*/'20081214'
             ) SUB
	     GROUP BY ROLLUP (
	                (KIZON_FLG,        SEGMENT_TYPE,		 COMPANY_CD) ,(DT)
				)
	     HAVING KIZON_FLG IS NOT NULL AND (DT=/*appMonth*/'200812' OR DT IS NULL)
    ) 
    ) BS09
    LEFT JOIN BM08SGTP ON (BS09.SEGMENT_TYPE = BM08SGTP.SEGMENT_TYPE)
WHERE BS09.COMPANY_CD IN (select BC06COMM.COMPANY_CD 
                           from BC06COMM
                           where BC06COMM.R_COMPANY_CD IN /*companyCdList*/('00')) 
ORDER BY BS09.KIZON_FLG
,        SORT_SEQ 
,        DT_TYPE