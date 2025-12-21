SELECT DISTINCT ONER.ONER_CD 
,	  ONER.ONER_NAME_KJ 
,	  ZENCNT.CNT AS ZEN_CNT 
,	  SIBUCNT.CNT AS SIBU_CNT 
,	  SVCNT.CNT AS SV_CNT 
,	  MISECNT.CNT AS MISE_CNT 
	
FROM (
	SELECT DISTINCT BS04.ONER_CD
	,      BM11.ONER_NAME_KJ 
	FROM BS04MSPS BS04 
	,    BM11ONER BM11
	,    BM01TENM BM01
	WHERE BS04.NENDO = /*nendo*/''     
	AND   BS04.KAI = /*kai*/''     
	AND   BS04.COMPANY_CD = /*companyCd*/'' 
	AND   BM01.MISE_CD IN 
          (	
            select rtrim(BM01.MISE_CD) as  MISE_CD
            from   BM01TENM BM01
            /*IF svCd != null && svCd != '' */
            ,      BM50TANM BM50
            /*END*/
            /*IF limitFlg == true && userTypeCd == '01' */
            ,      (
                    SELECT   BM51.COMPANY_CD
                    ,        BM51.SIBU_CD
                    ,        BM51.SV_CD
                    FROM     BM51SVSB AS BM51
                    WHERE    BM51.COMPANY_CD = /*companyCd*/'00'
                    AND      BM51.SV_CD = /*userId*/'00000921'
                    GROUP BY BM51.COMPANY_CD
                    ,        BM51.SIBU_CD
                    ,        BM51.SV_CD
                   ) LIMITDATA
            /*END*/
            where BM01.COMPANY_CD = /*companyCd*/'00'
            AND   BM01.SIBU_CD = /*sibuCd*/('011')
            /*IF svCd != null && svCd != '' */
            AND   BM50.SV_CD = /*svCd*/'00000921'
            AND   BM50.COMPANY_CD = BM01.COMPANY_CD
            AND   BM50.MISE_CD    = BM01.MISE_CD
            /*END*/
            /*IF limitFlg == true && userTypeCd == '01' */
            AND   LIMITDATA.COMPANY_CD = BM01.COMPANY_CD
            AND   LIMITDATA.SIBU_CD = BM01.SIBU_CD
            /*END*/
            )
	AND   BS04.HYOUKA_KBN = '1'
    AND   BM11.COMPANY_CD = BS04.COMPANY_CD
	AND   BM01.COMPANY_CD = BM11.COMPANY_CD     
	AND   BS04.MISE_CD = BM01.MISE_CD    
	AND   BM11.ONER_CD = BS04.ONER_CD     
) ONER 
	
,   (
	SELECT COUNT(DISTINCT BS04.MISE_CD) AS CNT 
	FROM BS04MSPS BS04 
	WHERE BS04.NENDO = /*nendo*/''     
	AND   BS04.KAI = /*kai*/''     
	AND   BS04.COMPANY_CD = /*companyCd*/'' 
) ZENCNT  
,   (
	SELECT COUNT(DISTINCT BS04.MISE_CD) AS CNT 
	FROM BS04MSPS BS04 
	,    BM01TENM BM01
	WHERE BS04.NENDO = /*nendo*/''     
	AND   BS04.KAI = /*kai*/''     
	AND   BS04.COMPANY_CD = /*companyCd*/'' 
	AND   BM01.SIBU_CD IN /*sibuCd*/('')       
	AND   BS04.HYOUKA_KBN = '1'
	AND   BM01.COMPANY_CD = BS04.COMPANY_CD     
	AND   BS04.MISE_CD = BM01.MISE_CD    
) SIBUCNT  
,   (
	SELECT COUNT(DISTINCT BS04.MISE_CD) AS CNT 
	FROM BS04MSPS BS04 
	,    BM01TENM BM01
	WHERE BS04.NENDO = /*nendo*/''     
	AND   BS04.KAI = /*kai*/''     
	AND   BS04.COMPANY_CD = /*companyCd*/'' 
	AND   BM01.MISE_CD IN 
          (	
            select rtrim(BM01.MISE_CD) as  MISE_CD
            from   BM01TENM BM01
            /*IF svCd != null && svCd != '' */
            ,      BM50TANM BM50
            /*END*/
            /*IF limitFlg == true && userTypeCd == '01' */
            ,      (
                    SELECT   BM51.COMPANY_CD
                    ,        BM51.SIBU_CD
                    ,        BM51.SV_CD
                    FROM     BM51SVSB AS BM51
                    WHERE    BM51.COMPANY_CD = /*companyCd*/'00'
                    AND      BM51.SV_CD = /*userId*/'00000921'
                    GROUP BY BM51.COMPANY_CD
                    ,        BM51.SIBU_CD
                    ,        BM51.SV_CD
                   ) LIMITDATA
            /*END*/
            where BM01.COMPANY_CD = /*companyCd*/'00'
            AND   BM01.SIBU_CD = /*sibuCd*/('011')
            /*IF svCd != null && svCd != '' */
            AND   BM50.SV_CD = /*svCd*/'00000921'
            AND   BM50.COMPANY_CD = BM01.COMPANY_CD
            AND   BM50.MISE_CD    = BM01.MISE_CD
            /*END*/
            /*IF limitFlg == true && userTypeCd == '01' */
            AND   LIMITDATA.COMPANY_CD = BM01.COMPANY_CD
            AND   LIMITDATA.SIBU_CD = BM01.SIBU_CD
            /*END*/
            )
	AND   BM01.SIBU_CD IN /*sibuCd*/('')       
	AND   BS04.HYOUKA_KBN = '1'
	AND   BM01.COMPANY_CD = BS04.COMPANY_CD     
	AND   BS04.MISE_CD = BM01.MISE_CD    
) SVCNT  
,   (
	SELECT BS04.ONER_CD 
	, COUNT(DISTINCT BS04.MISE_CD) AS CNT 
	FROM BS04MSPS BS04 
	,    BM01TENM BM01
	WHERE BS04.NENDO = /*nendo*/''     
	AND   BS04.KAI = /*kai*/''     
	AND   BS04.COMPANY_CD = /*companyCd*/'' 
	AND   BM01.MISE_CD IN 
          (	
            select rtrim(BM01.MISE_CD) as  MISE_CD
            from   BM01TENM BM01
            /*IF svCd != null && svCd != '' */
            ,      BM50TANM BM50
            /*END*/
            /*IF limitFlg == true && userTypeCd == '01' */
            ,      (
                    SELECT   BM51.COMPANY_CD
                    ,        BM51.SIBU_CD
                    ,        BM51.SV_CD
                    FROM     BM51SVSB AS BM51
                    WHERE    BM51.COMPANY_CD = /*companyCd*/'00'
                    AND      BM51.SV_CD = /*userId*/'00000921'
                    GROUP BY BM51.COMPANY_CD
                    ,        BM51.SIBU_CD
                    ,        BM51.SV_CD
                   ) LIMITDATA
            /*END*/
            where BM01.COMPANY_CD = /*companyCd*/'00'
            AND   BM01.SIBU_CD = /*sibuCd*/('011')
            /*IF svCd != null && svCd != '' */
            AND   BM50.SV_CD = /*svCd*/'00000921'
            AND   BM50.COMPANY_CD = BM01.COMPANY_CD
            AND   BM50.MISE_CD    = BM01.MISE_CD
            /*END*/
            /*IF limitFlg == true && userTypeCd == '01' */
            AND   LIMITDATA.COMPANY_CD = BM01.COMPANY_CD
            AND   LIMITDATA.SIBU_CD = BM01.SIBU_CD
            /*END*/
            )

	AND   BM01.SIBU_CD IN /*sibuCd*/('')        
	AND   BS04.HYOUKA_KBN = '1'
	AND   BM01.COMPANY_CD = BS04.COMPANY_CD     
	AND   BS04.MISE_CD = BM01.MISE_CD    
	GROUP BY BS04.ONER_CD
) MISECNT  
	
WHERE   MISECNT.ONER_CD = ONER.ONER_CD 
	
ORDER BY ONER.ONER_CD 