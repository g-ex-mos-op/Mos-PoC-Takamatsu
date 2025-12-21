select    count(distinct MISE_MST.MISE_CD) AS COUNT
/*IF userTypeCd.equals("01") */
,         MISE_MST.COMPANY_CD
,         RTRIM(MISE_MST.SIBU_CD) AS SIBU_CD
,         RTRIM(MISE_MST.SIBU_NAME) AS SIBU_NAME
,         MISE_MST.BLOCK_CD
,         MISE_MST.BLOCK_NAME
/*END*/
,         MISE_MST.MISE_CD
,         MISE_MST.MISE_NAME_KJ
,         DATA.TENPO_SHUBETU
,         DATA.MOSG_KBN
,         RTRIM(BM44.YAGO_DETAIL_NAME) AS YAGO_DTL_NAME
,         DATA.TENKO_KBN
,         SUM(DATA.URIAGE) AS URIAGE
,         SUM(DATA.URIAGE_ZEN) AS URIAGE_ZEN
,         SUM(DATA.YOSAN) AS URI_YOSAN
,         DATA.TENKO_KBN_ZEN
,         SUM(DATA.KYAKUSU) AS KYAKUSU
,         SUM(DATA.KYAKUSU_ZEN) AS KYAKUSU_ZEN
,         SUM(DATA.OPEN_KBN) AS OPEN_KBN
,         SUM(DATA.OPEN_KBN_ZEN) AS OPEN_KBN_ZEN
,         SUM(DATA.EIGYO_DAYS) AS EIGYO_DAYS
,         SUM(DATA.EIGYO_DAYS_ZEN) AS EIGYO_DAYS_ZEN
,         SUM(TENPO_COUNT_NET) AS TENPO_COUNT_NET   	
,         SUM(DATA.URIAGE_NET) AS ZENNEN_HI_TAISYO_URIAGE
,         SUM(DATA.KYAKUSU_NET) AS ZENNEN_HI_TAISYO_KYAKUSU
,         SUM(DATA.URIAGE_ZEN_NET) AS ZENNEN_HI_TAISYO_MAE_URIAGE
,         SUM(DATA.KYAKUSU_ZEN_NET) AS ZENNEN_HI_TAISYO_MAE_KYAKUSU
FROM (
	SELECT    BM01.COMPANY_CD
	,         BM10.SIBU_CD
	,         BM10.SIBU_NAME
	,         (CASE WHEN length(ltrim(rtrim(BM01.BLOCK_CD))) = 0 then '001' else BM01.BLOCK_CD END) AS BLOCK_CD
	,         (CASE WHEN length(ltrim(rtrim(BM01.BLOCK_CD))) = 0 then '' else BC23.BLOCK_NAME END) AS BLOCK_NAME
	,         BM01.MISE_CD
	,         BM01.MISE_NAME_KJ
	FROM BM01TENM AS BM01
	LEFT JOIN BC23BLCK AS BC23
	          ON (BM01.BLOCK_CD = BC23.BLOCK_CD)
	,    BM10GSIB AS BM10
	WHERE  BM01.COMPANY_CD = /*companyCd*/'00'
	AND    BM01.COMPANY_CD = BM10.COMPANY_CD
/*IF userTypeCd == "02" */
    AND    BM01.ONER_CD IN (
                SELECT ONER_CD 
                FROM BM06UONR
                WHERE COMPANY_CD = /*companyCd*/'00'
                AND   USER_ID = /*userId*/'99990002'
                )
/*END*/
/*IF shukeiKbn.equals("AREA_DAI_CD")*/
    AND    BM01.AREA_DAI= BM10.SIBU_CD
-- ELSE
    AND    BM01.SIBU_CD = BM10.SIBU_CD
/*END*/

/*IF shukeiKbn.equals("AREA_DAI_CD")*/
    AND    BM10.AREA_DAI_FLG = '1'
/*END*/
/*IF taishoJoken.equals("SLAREA")*/
    AND    BM10.slarea_CD = /*hyojiTaishoCd*/'10001'
/*END*/
/*IF taishoJoken.equals("JIGYOU")*/
    AND    BM10.jigyou_CD = /*hyojiTaishoCd*/'11000'
/*END*/
/*IF taishoJoken.equals("HONBU")*/
    AND    BM10.HONBU_CD = /*hyojiTaishoCd*/'10000'
/*END*/
/*IF taishoJoken.equals("SIBU")*/
    AND    BM10.SIBU_CD = /*hyojiTaishoCd*/'022'
/*END*/

/*IF taishoJoken.equals("SEGMENT")*/
	AND    BM01.GYOTAI_KBN IN (
	                SELECT GYOTAI_KBN
	                FROM   BM09GTSG
	                WHERE  SEGMENT_TYPE =/*hyojiTaishoCd*/'M'
                              )
/*END*/
/*IF limitFlg == true*/
	AND       BM01.SIBU_CD IN (
	                SELECT   BM51.SIBU_CD
	                FROM     BM51SVSB AS BM51
	                WHERE    BM51.COMPANY_CD = /*companyCd*/'00'
	                AND      BM51.SV_CD      = /*userId*/'00000921'
	                GROUP BY BM51.SIBU_CD
	                          )
/*END*/

/*IF shukeiKbn.equals("SV_CD")*/
	AND       BM01.MISE_CD IN (
	                SELECT   BM50.MISE_CD
	                FROM     BM50TANM AS BM50
	                WHERE    BM50.COMPANY_CD = /*companyCd*/'00'
	                AND      BM50.SV_CD      = /*svCd*/'00000921'
	                          )                         
/*END*/

/*IF !taishoTenpo.equals("ALL")*/
       /*IF taishoTenpo.equals("FC")*/
	AND       BM01.MISE_KBN like '_1_'
       -- ELSE
	AND       BM01.MISE_KBN like '_2_'
       /*END*/
/*END*/
) AS MISE_MST
,    (
	SELECT (CASE WHEN URIAGE60.COMPANY_CD IS NOT NULL THEN URIAGE60.COMPANY_CD 
	             ELSE YOSAN45.COMPANY_CD END) AS COMPANY_CD
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.MISE_CD 
	             ELSE YOSAN45.MISE_CD END) AS MISE_CD
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.TENPO_SHUBETU ELSE YOSAN45.TENPO_SHUBETU END) AS TENPO_SHUBETU
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.MOSG_KBN ELSE YOSAN45.MOSG_KBN END) AS MOSG_KBN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.TENKO_KBN ELSE 0 END) AS TENKO_KBN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.URIAGE ELSE 0 END) AS URIAGE
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.YOSAN 
	             ELSE 0 END) AS YOSAN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.URIAGE_ZEN ELSE 0 END) AS URIAGE_ZEN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.TENKO_KBN_ZEN ELSE 0 END) AS TENKO_KBN_ZEN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.KYAKUSU ELSE 0 END) AS KYAKUSU
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.KYAKUSU_ZEN ELSE 0 END) AS KYAKUSU_ZEN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.OPEN_KBN ELSE 0 END) AS OPEN_KBN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.OPEN_KBN_ZEN ELSE 0 END) AS OPEN_KBN_ZEN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.EIGYO_DAYS ELSE 0 END) AS EIGYO_DAYS
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.EIGYO_DAYS_ZEN ELSE 0 END) AS EIGYO_DAYS_ZEN
,          (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN OPEN_KBN_ZEN_NET ELSE 0 END) AS TENPO_COUNT_NET   	
,          (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE_NET ELSE 0 END) AS URIAGE_NET
,          (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN KYAKUSU_NET ELSE 0 END) AS KYAKUSU_NET
,          (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE_ZEN_NET ELSE 0 END) AS URIAGE_ZEN_NET
,          (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN KYAKUSU_ZEN_NET ELSE 0 END) AS KYAKUSU_ZEN_NET
,          (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN EIGYO_DAYS_ZEN_NET ELSE 0 END) AS EIGYO_DAYS_ZEN_NET
	FROM   (
		SELECT BT45.COMPANY_CD
		,      BT45.MISE_CD
		,      BN01.KBN1 AS TENPO_SHUBETU
		,      BN01.KBN16 AS MOSG_KBN
		,      SUM(BT45.YOSAN) AS YOSAN
		FROM  BT45DSJY AS BT45
        ,     BN01DTEN AS BN01
		WHERE BT45.COMPANY_CD = /*companyCd*/'00'
        AND   BN01.COMPANY_CD   = BT45.COMPANY_CD
		AND   BT45.YOSAN <> 0
		AND   BT45.MISE_CD = BN01.MISE_CD
	/*IF !taishoTenpo.equals("ALL")*/
   	    /*IF taishoTenpo.equals("FC")*/
		AND       BT45.FC_RC = '1'
       -- ELSE
		AND       BT45.FC_RC = '2'
       /*END*/
	/*END*/
    /*IF taishoJoken.equals("SIBU")*/
	    /*IF shukeiKbn.equals("AREA_DAI_CD") */
	    AND    BT45.AREA_DAI = /*hyojiTaishoCd*/'075'
	    --ELSE
	    AND    BT45.SIBU_CD = /*hyojiTaishoCd*/'075'
	    /*END*/
    /*END*/
	/*IF taishoKikan.equals("DAY1")*/ 
		AND   BT45.YOSAN_DT = /*kikanFrom*/'20060405'
	/*END*/

	/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") */ 
		AND   BT45.YOSAN_DT between /*kikanFrom*/'20060401' AND /*kikanTo*/'20060430'
	/*END*/
        AND     BT45.YOSAN_DT   = BN01.EIGYO_DT
	/*IF limitFlg == true*/
		AND   BT45.SIBU_CD in (
		         SELECT   BM51.SIBU_CD
		         FROM     BM51SVSB BM51
		         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
		         AND    BM51.SV_CD      = /*userId*/'9999000a'
		         GROUP BY BM51.SIBU_CD
		    )
	/*END*/
	                                       
    /*IF shukeiKbn.equals("SV_CD") */
	    AND   BT45.MISE_CD IN (
	             SELECT   BM50.MISE_CD
	             FROM     BM50TANM AS BM50
	             WHERE    BM50.COMPANY_CD = /*companyCd*/'00'
	             AND      BM50.SV_CD      = /*svCd*/'00000921'
	        )                         
    /*END*/

	/*IF !tenpoShubetu.equals("ALL")*/
	   /*IF tenpoShubetu.equals("2")*/
		AND   (BT45.TENPO_SHU = '1' or BT45.TENPO_SHU = '2')
	   -- ELSE
		AND   BT45.TENPO_SHU = /*tenpoShubetu*/'1'
	   /*END*/
	/*END*/
        AND   BN01.KBN1 = BT45.TENPO_SHU
/*IF yagoFlg == true*/
        AND   BN01.KBN16 IN /*yagoList*/('02','03')
/*END*/

		GROUP BY BT45.COMPANY_CD
		,        BT45.MISE_CD
        ,        BN01.KBN1 
		,        BN01.KBN16
	/*END*/
	) YOSAN45
	full outer join (
		select  BT60.COMPANY_CD
		,       BT60.MISE_CD
	    ,       BN01.KBN1 AS TENPO_SHUBETU
	    ,       BN01.KBN16 AS MOSG_KBN
		,       MAX(BT60.TENKO_KBN) AS TENKO_KBN
		,       SUM(BT60.URIAGE) AS URIAGE
	/*IF userTypeCd.equals("01") */
		,       SUM(BT60.URI_YOSAN) AS YOSAN
	--ELSE
		,       SUM(BT60.ONER_YOSAN) AS YOSAN
	/*END*/
		,       SUM(BT60.KYAKUSU) AS KYAKUSU
		,       MAX(BT60.OPEN_KBN) AS OPEN_KBN
		,       SUM(BT60.EIGYO_DAYS) AS EIGYO_DAYS
	/*IF zennenDataShubetu.equals("DOGETU")*/
		,       MAX(BT60.TENKO_KBN_ZEN_DOGETU)  AS TENKO_KBN_ZEN
		,       SUM(BT60.URIAGE_ZEN_DOGETU)     AS URIAGE_ZEN
		,       SUM(BT60.KYAKUSU_ZEN_DOGETU)    AS KYAKUSU_ZEN
		,       MAX(BT60.OPEN_KBN_ZEN_DOGETU)   AS OPEN_KBN_ZEN
		,       SUM(BT60.EIGYO_DAYS_ZEN_DOGETU) AS EIGYO_DAYS_ZEN
	/*END*/
	/*IF zennenDataShubetu.equals("DOYO")*/
		,       MAX(BT60.TENKO_KBN_ZEN_DOYO)  AS TENKO_KBN_ZEN
		,       SUM(BT60.URIAGE_ZEN_DOYO)     AS URIAGE_ZEN
		,       SUM(BT60.KYAKUSU_ZEN_DOYO)    AS KYAKUSU_ZEN
		,       MAX(BT60.OPEN_KBN_ZEN_DOYO)   AS OPEN_KBN_ZEN
		,       SUM(BT60.EIGYO_DAYS_ZEN_DOYO) AS EIGYO_DAYS_ZEN
	/*END*/
	/*IF zennenDataShubetu.equals("DOJITU")*/
		,       MAX(BT60.TENKO_KBN_ZEN_DOJITU)  AS TENKO_KBN_ZEN
		,       SUM(BT60.URIAGE_ZEN_DOJITU)     AS URIAGE_ZEN
		,       SUM(BT60.KYAKUSU_ZEN_DOJITU)    AS KYAKUSU_ZEN
		,       MAX(BT60.OPEN_KBN_ZEN_DOJITU)   AS OPEN_KBN_ZEN
		,       SUM(BT60.EIGYO_DAYS_ZEN_DOJITU) AS EIGYO_DAYS_ZEN
	/*END*/
	/*IF zennenDataShubetu.equals("HOSEI") */
		,       MAX(BT60.TENKO_KBN_ZEN_DOGETU)  AS TENKO_KBN_ZEN
		,       SUM(BT60.URIAGE_ZEN_DOGETU)     AS URIAGE_ZEN
		,       SUM(BT60.KYAKUSU_ZEN_DOGETU)    AS KYAKUSU_ZEN
		,       MAX(BT60.OPEN_KBN_ZEN_DOGETU)   AS OPEN_KBN_ZEN
		,       SUM(BT60.EIGYO_DAYS_ZEN_DOGETU) AS EIGYO_DAYS_ZEN		
	/*END*/
		,       SUM(CASE WHEN (BT60.OPEN_KBN_ZEN_DOGETU = 1 AND BT60.URIAGE_ZEN_DOGETU > 0)
		                 THEN BT60.URIAGE
		                 ELSE 0 END) AS URIAGE_NET
		,       SUM(CASE WHEN (BT60.OPEN_KBN_ZEN_DOGETU = 1 AND BT60.URIAGE_ZEN_DOGETU > 0)
		                 THEN BT60.KYAKUSU
		                 ELSE 0 END) AS KYAKUSU_NET
		,       SUM(CASE WHEN (BT60.OPEN_KBN_ZEN_DOGETU = 1 AND BT60.URIAGE > 0)
		                 THEN BT60.URIAGE_ZEN_DOGETU
		                 ELSE 0 END)     AS URIAGE_ZEN_NET
		,       SUM(CASE WHEN (BT60.OPEN_KBN_ZEN_DOGETU = 1 AND BT60.URIAGE > 0)
		                 THEN BT60.KYAKUSU_ZEN_DOGETU
		                 ELSE 0 END)    AS KYAKUSU_ZEN_NET
		,       MAX(CASE WHEN (BT60.OPEN_KBN_ZEN_DOGETU = 1 AND BT60.URIAGE > 0)
		                 THEN BT60.OPEN_KBN_ZEN_DOGETU
		                 ELSE 0 END)   AS OPEN_KBN_ZEN_NET
		,       SUM(CASE WHEN (BT60.OPEN_KBN_ZEN_DOGETU = 1 AND BT60.URIAGE > 0)
		                 THEN BT60.EIGYO_DAYS_ZEN_DOGETU
		                 ELSE 0 END) AS EIGYO_DAYS_ZEN_NET
		FROM    BT60ZNIP AS BT60
		,       BN01DTEN AS BN01
		WHERE   BT60.COMPANY_CD = /*companyCd*/'00'
		AND     BT60.COMPANY_CD = BN01.COMPANY_CD
		AND     BT60.MISE_CD    = BN01.MISE_CD
		AND     BT60.OLDM_FLG   <> '1'
	/*IF taishoKikan.equals("DAY1")*/ 
		AND     BT60.EIGYO_DT   = /*kikanFrom*/'20060704'
	/*END*/
	/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") */ 
		AND     BT60.EIGYO_DT between /*kikanFrom*/'20060401' AND /*kikanTo*/'20060430'
	/*END*/
		AND     BT60.EIGYO_DT   = BN01.EIGYO_DT

	/*IF tenpoShubetu.equals("ALL")*/
		AND     (BT60.OPEN_KBN = 1 or BT60.OPEN_KBN_ZEN_DOGETU = 1) 
	-- ELSE
		AND     BT60.OPEN_KBN = 1
	    /*IF tenpoShubetu.equals("2")*/
		AND     (BN01.KBN1 = '1' or BN01.KBN1 = '2')
	    -- ELSE
		AND     BN01.KBN1 = /*tenpoShubetu*/'1'
	    /*END*/
	/*END*/
	/*IF yagoFlg == true*/
	    AND BN01.KBN16 IN /*yagoList*/('02','03')
	/*END*/
		GROUP BY BT60.COMPANY_CD
		,        BT60.MISE_CD
		,        BN01.KBN1
    	,        BN01.KBN16 
	) AS URIAGE60
	ON (        URIAGE60.COMPANY_CD     = YOSAN45.COMPANY_CD
			AND URIAGE60.MISE_CD        = YOSAN45.MISE_CD
			AND URIAGE60.TENPO_SHUBETU  = YOSAN45.TENPO_SHUBETU
			AND URIAGE60.MOSG_KBN       = YOSAN45.MOSG_KBN
	   )
) DATA
, BM44YDMS BM44

WHERE DATA.COMPANY_CD     = MISE_MST.COMPANY_CD
AND   DATA.MISE_CD        = MISE_MST.MISE_CD
AND   BM44.YAGO_DETAIL_CD = DATA.MOSG_KBN 

/*IF userTypeCd.equals("01") */
GROUP BY rollup(
        /*IF shukeiKbn.equals("SV_CD") */
        (
            MISE_MST.COMPANY_CD
          , DATA.MOSG_KBN
          , BM44.YAGO_DETAIL_NAME
         )
        ,
        /*END*/
         (
            MISE_MST.COMPANY_CD
          , MISE_MST.SIBU_CD
          , MISE_MST.SIBU_NAME
          , DATA.MOSG_KBN
          , BM44.YAGO_DETAIL_NAME
         )
        ,(
            MISE_MST.BLOCK_CD
          , MISE_MST.BLOCK_NAME
         )
        ,(
            MISE_MST.MISE_CD
          , MISE_MST.MISE_NAME_KJ
          , DATA.TENPO_SHUBETU
		  , DATA.TENKO_KBN
		  , DATA.TENKO_KBN_ZEN
         )
)
/*IF shukeiKbn.equals("SIBU_CD") || shukeiKbn.equals("SV_CD")*/
order by  MISE_MST.COMPANY_CD
,         MISE_MST.SIBU_CD
,         MISE_MST.SIBU_NAME
,         MISE_MST.BLOCK_CD
,         MISE_MST.BLOCK_NAME
,         MISE_MST.MISE_CD
,         MISE_MST.MISE_NAME_KJ
,         DATA.TENPO_SHUBETU
,         DATA.MOSG_KBN
--ELSE
order by  MISE_MST.MISE_CD
,         MISE_MST.MISE_NAME_KJ
,         DATA.TENPO_SHUBETU
,         DATA.MOSG_KBN
/*END*/
--ELSE
GROUP BY rollup( 
         (
            DATA.MOSG_KBN
          , BM44.YAGO_DETAIL_NAME
         )
        ,(
            MISE_MST.MISE_CD
          , MISE_MST.MISE_NAME_KJ
          , DATA.TENPO_SHUBETU
		  , DATA.TENKO_KBN
		  , DATA.TENKO_KBN_ZEN
         )
)
order by  MISE_MST.MISE_CD
,         MISE_MST.MISE_NAME_KJ
,         DATA.TENPO_SHUBETU
,         DATA.MOSG_KBN
/*END*/