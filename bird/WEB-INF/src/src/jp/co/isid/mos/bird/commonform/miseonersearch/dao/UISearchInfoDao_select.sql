SELECT RTRIM(CHAR(SUM(SEARCH_POINT))) BUNRUI_KBN
,      COMPANY_CD
,      COMPANY_NAME
,      MISE_CD
,      MISE_NAME_KJ
,      ONER_CD
,      ONER_NAME_KJ
FROM (
	SELECT 2 AS SEARCH_POINT
	,      BM01.COMPANY_CD
	,      BC02.COMPANY_NAME
	,      BM01.MISE_CD
	,      RTRIM(BM01.MISE_NAME_KJ) AS MISE_NAME_KJ
	,      BM01.ONER_CD
	,      RTRIM(BM11.ONER_NAME_KJ) AS ONER_NAME_KJ
	FROM BM01TENM BM01
	,    BM11ONER BM11
	,    (SELECT R_COMPANY_CD 
	      ,      COMPANY_NAME
	      FROM BC02COMP 
	      WHERE R_COMPANY_CD IN (
	      	SELECT R_COMPANY_CD 
	      	FROM BM03USCP 
	      	WHERE USER_ID = /*userId*/'99990001'
	      	AND   ZOKUSEI_KBN IN ('0', '1')
	      	GROUP BY R_COMPANY_CD 
	     	) 
	     AND   MISE_FLG ='1'
	     )BC02
/*IF userTypeCd == "01" && limitFlg == true */
	,    BM51SVSB BM51
/*END*/
/*IF userTypeCd == "02" */
    ,    BM06UONR BM06
/*END*/
/*IF userTypeCd == "03" */
    ,    BM07UTEN BM07
/*END*/
	WHERE BM01.CLOSE_DT > /*sysDate*/'20081126'
	AND   BM11.KEIYAKU_END > /*sysDate*/'20081126'
	AND (BM01.MISE_CD like /*searchWord*/
	       OR   BM01.MISE_NAME_KJ like /*searchWord*/'%“X%'
	       OR   BM01.MISE_NAME_KNA like /*searchWord*/'%“X%'
	       OR   BM01.MISE_RYAK_KJ like /*searchWord*/'%“X%'
	       OR   BM01.MISE_RYAK_KNA like /*searchWord*/'%“X%')
	AND   BM11.COMPANY_CD = BC02.R_COMPANY_CD
	AND   BM01.COMPANY_CD = BM11.COMPANY_CD
/*IF userTypeCd == "01" && limitFlg == true */
    AND   BM51.SV_CD      = /*userId*/'9999000a'
    AND   BM51.COMPANY_CD = BM01.COMPANY_CD
    AND   BM51.SIBU_CD = BM01.SIBU_CD
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
	AND   BM01.ONER_CD    = BM11.ONER_CD
	GROUP BY BM01.COMPANY_CD
	,        BC02.COMPANY_NAME
	,        BM01.MISE_CD
	,        RTRIM(BM01.MISE_NAME_KJ)
	,        BM01.ONER_CD
	,        RTRIM(BM11.ONER_NAME_KJ)
	UNION ALL
	SELECT 1 AS SEARCH_POINT
	,      BM01.COMPANY_CD
	,      BC02.COMPANY_NAME
	,      BM01.MISE_CD
	,      RTRIM(BM01.MISE_NAME_KJ) AS MISE_NAME_KJ
	,      BM01.ONER_CD
	,      RTRIM(BM11.ONER_NAME_KJ) AS ONER_NAME_KJ
	FROM BM01TENM BM01
	,    BM11ONER BM11
	,    (SELECT R_COMPANY_CD 
	      ,      COMPANY_NAME
	      FROM BC02COMP 
	      WHERE R_COMPANY_CD IN (
	      	SELECT R_COMPANY_CD 
	      	FROM BM03USCP 
	      	WHERE USER_ID = /*userId*/'99990001'
	      	AND   ZOKUSEI_KBN IN ('0', '1')
	      	GROUP BY R_COMPANY_CD 
	     	) 
	     )BC02
/*IF userTypeCd == "01" && limitFlg == true */
	,    BM51SVSB BM51
/*END*/
/*IF userTypeCd == "02" */
    ,    BM06UONR BM06
/*END*/
/*IF userTypeCd == "03" */
    ,    BM07UTEN BM07
/*END*/
	WHERE (BM11.ONER_CD like /*searchWord*/'3600%'
	       OR   BM11.ONER_NAME_KJ like /*searchWord*/'%“X%'
	       OR   BM11.ONER_NAME_KNA like /*searchWord*/'%“X%')
	AND   BM11.COMPANY_CD = BC02.R_COMPANY_CD
	AND   BM01.COMPANY_CD = BM11.COMPANY_CD
/*IF userTypeCd == "01" && limitFlg == true */
    AND   BM51.SV_CD      = /*userId*/'9999000a'
    AND   BM51.COMPANY_CD = BM01.COMPANY_CD
    AND   BM51.SIBU_CD = BM01.SIBU_CD
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
	AND   BM01.ONER_CD    = BM11.ONER_CD
	GROUP BY BM01.COMPANY_CD
	,        BC02.COMPANY_NAME
	,        BM01.MISE_CD
	,        RTRIM(BM01.MISE_NAME_KJ) 
	,        BM01.ONER_CD
	,        RTRIM(BM11.ONER_NAME_KJ) 
	
) TAISHO
GROUP BY COMPANY_CD
,        COMPANY_NAME
,        MISE_CD
,        MISE_NAME_KJ
,        ONER_CD
,        ONER_NAME_KJ
ORDER BY BUNRUI_KBN DESC
,        COMPANY_CD
,        ONER_CD
,        MISE_CD
