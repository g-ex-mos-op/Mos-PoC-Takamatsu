select    count(distinct MISE_MST.MISE_CD) AS COUNT
,         MISE_MST.COMPANY_CD
/*IF companyCd.equals("00") && shukeiKbn.equals("AREA_DAI_CD") == false*/
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
,         SUM(DATA.URIAGE) AS URIAGE
,         SUM(DATA.URIAGE_ZEN) AS URIAGE_ZEN
,         SUM(DATA.YOSAN) AS URI_YOSAN
,         SUM(DATA.KYAKUSU) AS KYAKUSU
,         SUM(DATA.KYAKUSU_ZEN) AS KYAKUSU_ZEN
,         SUM(DATA.OPEN_KBN) AS OPEN_KBN
,         SUM(DATA.OPEN_KBN_ZEN) AS OPEN_KBN_ZEN
,         SUM(DATA.EIGYO_DAYS) AS EIGYO_DAYS
,         SUM(DATA.EIGYO_DAYS_ZEN) AS EIGYO_DAYS_ZEN
,         SUM(DATA.TENPO_COUNT_NET) AS TENPO_COUNT_NET   	
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
	SELECT (CASE WHEN URIAGE74.COMPANY_CD IS NOT NULL THEN URIAGE74.COMPANY_CD 
	             ELSE YOSAN45.COMPANY_CD END) AS COMPANY_CD
	,      (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE74.MISE_CD 
	             ELSE YOSAN45.MISE_CD END) AS MISE_CD
	,      (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE74.TENPO_SHUBETU ELSE YOSAN45.TENPO_SHUBETU END) AS TENPO_SHUBETU
	,      (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE74.MOSG_KBN ELSE YOSAN45.MOSG_KBN END) AS MOSG_KBN
	,      (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE74.URIAGE ELSE 0 END) AS URIAGE
	,      (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE74.YOSAN ELSE 0 END) AS YOSAN
	,      (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE74.URIAGE_ZEN ELSE 0 END) AS URIAGE_ZEN
	,      (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE74.KYAKUSU ELSE 0 END) AS KYAKUSU
	,      (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE74.KYAKUSU_ZEN ELSE 0 END) AS KYAKUSU_ZEN
	,      (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE74.OPEN_KBN ELSE 0 END) AS OPEN_KBN
	,      (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE74.OPEN_KBN_ZEN ELSE 0 END) AS OPEN_KBN_ZEN
	,      (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE74.EIGYO_DAYS ELSE 0 END) AS EIGYO_DAYS
	,      (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE74.EIGYO_DAYS_ZEN ELSE 0 END) AS EIGYO_DAYS_ZEN
	
,          (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN OPEN_KBN_ZEN_NET ELSE 0 END) AS TENPO_COUNT_NET   	
,          (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE_NET ELSE 0 END) AS URIAGE_NET
,          (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN KYAKUSU_NET ELSE 0 END) AS KYAKUSU_NET
,          (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN URIAGE_ZEN_NET ELSE 0 END) AS URIAGE_ZEN_NET
,          (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN KYAKUSU_ZEN_NET ELSE 0 END) AS KYAKUSU_ZEN_NET
,          (CASE WHEN URIAGE74.MISE_CD IS NOT NULL THEN EIGYO_DAYS_ZEN_NET ELSE 0 END) AS EIGYO_DAYS_ZEN_NET
	FROM   (
		SELECT YTBL4.COMPANY_CD
		,      YTBL4.MISE_CD
		,      MIN(BN01.KBN1) AS TENPO_SHUBETU
		,      BN01.KBN16 AS MOSG_KBN
		,      SUM(YTBL4.YOSAN) AS YOSAN
/*IF taishoKikan.equals("APPMONTH")*/
		FROM  BT45DSJY AS YTBL4
--ELSE		
		FROM  BT44MSJY AS YTBL4
/*END*/
		,       (
               SELECT EIGYO_YM
               ,      MISE_CD
               ,      KBN1
               ,      KBN16
               FROM BN01DTEN 
               WHERE COMPANY_CD = /*companyCd*/'00'
	/*IF taishoKikan.equals("APPMONTH")*/
			   AND   EIGYO_YM = /*kikanFrom*/'200706'
	/*END*/
	/*IF taishoKikan.equals("MONTH")*/
			   AND   EIGYO_YM = /*kikanFrom*/'200706'
	/*END*/
	/*IF taishoKikan.equals("KIBETU")*/
			   AND   (EIGYO_YM between /*kikanFrom*/'200706' AND /*kikanTo*/'200706') 
	/*END*/
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
               AND   KBN1 = /*tenpoShubetu*/'1'
/*END*/
/*IF tenpoShubetu == "2" */
               AND   KBN1 IN ('1', '2')
/*END*/
               GROUP BY EIGYO_YM
               ,        MISE_CD
               ,        KBN1
               ,        KBN16
            ) BN01
		WHERE YTBL4.COMPANY_CD = /*companyCd*/'00'
		AND   YTBL4.YOSAN <> 0
		AND   YTBL4.MISE_CD = BN01.MISE_CD
	/*IF !taishoTenpo.equals("ALL")*/
   	    /*IF taishoTenpo.equals("FC")*/
		AND       YTBL4.FC_RC = '1'
       -- ELSE
		AND       YTBL4.FC_RC = '2'
       /*END*/
	/*END*/
/*IF taishoKikan.equals("APPMONTH")*/
		AND   SUBSTR(YTBL4.YOSAN_DT, 1, 6) = /*kikanFrom*/'200706'
		AND   YTBL4.YOSAN_DT <= /*kikanTo*/'20070629'
        AND   SUBSTR(YTBL4.YOSAN_DT, 1, 6)   = BN01.EIGYO_YM
--ELSE		
	/*IF taishoKikan.equals("MONTH")*/
		AND   YTBL4.YOSAN_DT = /*kikanFrom*/'200706'
	/*END*/
	/*IF taishoKikan.equals("KIBETU")*/
		AND   YTBL4.YOSAN_DT between /*kikanFrom*/'200706' AND /*kikanTo*/'200706'
	/*END*/
        AND   YTBL4.YOSAN_DT   = BN01.EIGYO_YM
/*END*/

/*IF limitFlg == true*/
		AND   YTBL4.SIBU_CD in (
		         SELECT   BM51.SIBU_CD
		         FROM     BM51SVSB BM51
		         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
		         AND    BM51.SV_CD      = /*userId*/'9999000a'
		         GROUP BY BM51.SIBU_CD
		    )
--ELSE
	/*IF shukeiKbn.equals("SV_CD")*/
		AND   YTBL4.SIBU_CD IN (
		          SELECT   distinct BM01TENM.SIBU_CD
		          FROM     BM50TANM , BM01TENM 
		          WHERE    BM50TANM.COMPANY_CD = /*companyCd*/'00'
		          AND      BM50TANM.SV_CD      = /*svCd*/'00000921'
		          AND      BM50TANM.COMPANY_CD=BM01TENM.COMPANY_CD
		          AND      BM50TANM.MISE_CD=BM01TENM.MISE_CD
		     )                      
    /*END*/
/*END*/

/*IF taishoJoken.equals("SIBU")*/
	/*IF shukeiKbn.equals("AREA_DAI_CD") */
	    AND    YTBL4.AREA_DAI = /*hyojiTaishoCd*/'075'
	--ELSE
	    AND    YTBL4.SIBU_CD = /*hyojiTaishoCd*/'075'
	/*END*/
/*END*/
	                                       

	/*IF !tenpoShubetu.equals("ALL")*/
	   /*IF tenpoShubetu.equals("2")*/
		AND   (YTBL4.TENPO_SHU = '1' or YTBL4.TENPO_SHU = '2')
	   -- ELSE
		AND   YTBL4.TENPO_SHU = /*tenpoShubetu*/'1'
	   /*END*/
	/*END*/
        AND   BN01.KBN1 = YTBL4.TENPO_SHU
/*IF yagoFlg == true*/
        AND   BN01.KBN16 IN /*yagoList*/('02','03')
/*END*/

		GROUP BY YTBL4.COMPANY_CD
		,        YTBL4.MISE_CD
		,        BN01.KBN16
	/*END*/
	) YOSAN45
	full outer join (
		select  BT74.COMPANY_CD
		,       BT74.MISE_CD
	    ,       BN01.KBN1 AS TENPO_SHUBETU
	    ,       BN01.KBN16 AS MOSG_KBN
		,       MAX(BT74.OPEN_KBN) AS OPEN_KBN
	/*IF zennenDataShubetu.equals("DOGETU")*/
		,       SUM(BT74.URIAGE_DOGETU) AS URIAGE
	/*IF userTypeCd.equals("01") */
		,       SUM(BT74.YOSAN_DOGETU) AS YOSAN
	--ELSE
		,       SUM(BT74.ONER_YOSAN_DOGETU) AS YOSAN
	/*END*/
		,       SUM(BT74.KYAKUSU_DOGETU) AS KYAKUSU
		,       SUM(BT74.EIGYO_DAYS_DOGETU) AS EIGYO_DAYS
		,       SUM(BT74.URIAGE_ZEN_DOGETU)     AS URIAGE_ZEN
		,       SUM(BT74.KYAKUSU_ZEN_DOGETU)    AS KYAKUSU_ZEN
		,       MAX(BT74.OPEN_KBN_ZEN_DOGETU)   AS OPEN_KBN_ZEN
		,       SUM(BT74.EIGYO_DAYS_ZEN_DOGETU) AS EIGYO_DAYS_ZEN
	/*END*/
	/*IF zennenDataShubetu.equals("DOYO")*/
		,       SUM(BT74.URIAGE_DOYO) AS URIAGE
	/*IF userTypeCd.equals("01") */
		,       SUM(BT74.YOSAN_DOYO) AS YOSAN
	--ELSE
		,       SUM(BT74.ONER_YOSAN_DOYO) AS YOSAN
	/*END*/
		,       SUM(BT74.KYAKUSU_DOYO) AS KYAKUSU
		,       SUM(BT74.EIGYO_DAYS_DOYO) AS EIGYO_DAYS
		,       SUM(BT74.URIAGE_ZEN_DOYO)     AS URIAGE_ZEN
		,       SUM(BT74.KYAKUSU_ZEN_DOYO)    AS KYAKUSU_ZEN
		,       MAX(BT74.OPEN_KBN_ZEN_DOYO)   AS OPEN_KBN_ZEN
		,       SUM(BT74.EIGYO_DAYS_ZEN_DOYO) AS EIGYO_DAYS_ZEN
	/*END*/
	/*IF zennenDataShubetu.equals("DOJITU")*/
		,       SUM(BT74.URIAGE_DOJITU) AS URIAGE
	/*IF userTypeCd.equals("01") */
		,       SUM(BT74.YOSAN_DOJITUU) AS YOSAN
	--ELSE
		,       SUM(BT74.ONER_YOSAN_DOJITU) AS YOSAN
	/*END*/
		,       SUM(BT74.KYAKUSU_DOJITU) AS KYAKUSU
		,       SUM(BT74.EIGYO_DAYS_DOJITU) AS EIGYO_DAYS
		,       SUM(BT74.URIAGE_ZEN_DOJITU)     AS URIAGE_ZEN
		,       SUM(BT74.KYAKUSU_ZEN_DOJITU)    AS KYAKUSU_ZEN
		,       MAX(BT74.OPEN_KBN_ZEN_DOJITU)   AS OPEN_KBN_ZEN
		,       SUM(BT74.EIGYO_DAYS_ZEN_DOJITU) AS EIGYO_DAYS_ZEN
	/*END*/
	/*IF zennenDataShubetu.equals("HOSEI") */
		,       SUM(BT74.URIAGE_DOGETU) AS URIAGE
	/*IF userTypeCd.equals("01") */
		,       SUM(BT74.YOSAN_DOGETU) AS YOSAN
	--ELSE
		,       SUM(BT74.ONER_YOSAN_DOGETU) AS YOSAN
	/*END*/
		,       SUM(BT74.KYAKUSU_DOGETU) AS KYAKUSU
		,       SUM(BT74.EIGYO_DAYS_DOGETU) AS EIGYO_DAYS
		,       SUM(BT74.URIAGE_ZEN_DOGETU)     AS URIAGE_ZEN
		,       SUM(BT74.KYAKUSU_ZEN_DOGETU)    AS KYAKUSU_ZEN
		,       MAX(BT74.OPEN_KBN_ZEN_DOGETU)   AS OPEN_KBN_ZEN
		,       SUM(BT74.EIGYO_DAYS_ZEN_DOGETU) AS EIGYO_DAYS_ZEN
	/*END*/
		,       SUM(BT74.URIAGE_HOSEI) AS URIAGE_NET
		,       SUM(BT74.KYAKUSU_HOSEI) AS KYAKUSU_NET
		,       SUM(BT74.URIAGE_ZEN_HOSEI) AS URIAGE_ZEN_NET
		,       SUM(BT74.KYAKUSU_ZEN_HOSEI)    AS KYAKUSU_ZEN_NET
		,       MAX(BT74.OPEN_KBN_ZEN_HOSEI)   AS OPEN_KBN_ZEN_NET
		,       SUM(BT74.EIGYO_DAYS_ZEN_HOSEI) AS EIGYO_DAYS_ZEN_NET
		FROM    BT74YAGO AS BT74
		,       (
               SELECT EIGYO_YM
               ,      MISE_CD
               ,      KBN1
               ,      KBN16
               FROM BN01DTEN 
	/*IF taishoKikan.equals("APPMONTH")*/
			   WHERE   EIGYO_YM = /*kikanFrom*/'200706'
	/*END*/
	/*IF taishoKikan.equals("MONTH")*/
			   WHERE   EIGYO_YM = /*kikanFrom*/'200706'
	/*END*/
	/*IF taishoKikan.equals("KIBETU")*/
			   WHERE   (EIGYO_YM between /*kikanFrom*/'200706' AND /*kikanTo*/'200706') 
	/*END*/
/*IF tenpoShubetu == "1" || tenpoShubetu == "3"*/
               AND   KBN1 = /*tenpoShubetu*/'1'
/*END*/
/*IF tenpoShubetu == "2" */
               AND   KBN1 IN ('1', '2')
/*END*/
               AND   COMPANY_CD = /*companyCd*/'00'
               GROUP BY EIGYO_YM
               ,        MISE_CD
               ,        KBN1
               ,        KBN16
            ) BN01

		WHERE   BT74.COMPANY_CD = /*companyCd*/'00'
		AND     BT74.MISE_CD    = BN01.MISE_CD
		AND     BT74.OLDM_FLG   <> '1'
	/*IF taishoKikan.equals("APPMONTH")*/
		AND     BT74.EIGYO_DT = /*kikanFrom*/'200706'
	/*END*/
	/*IF taishoKikan.equals("MONTH")*/
		AND     BT74.EIGYO_DT = /*kikanFrom*/'200706'
	/*END*/
	/*IF taishoKikan.equals("KIBETU")*/
		AND     (BT74.EIGYO_DT between /*kikanFrom*/'200706' AND /*kikanTo*/'200706') 
	/*END*/
		AND     BT74.EIGYO_DT   = BN01.EIGYO_YM

	/*IF tenpoShubetu.equals("ALL")*/
		AND     (BT74.OPEN_KBN = 1 or BT74.OPEN_KBN_ZEN_DOGETU = 1) 
	-- ELSE
		AND     BT74.OPEN_KBN = 1
	/*END*/

	/*IF yagoFlg == true*/
	    AND BN01.KBN16 IN /*yagoList*/('02','03')
	/*END*/
	    AND     BT74.YAGO_DETAIL_CD = BN01.KBN16
		GROUP BY BT74.COMPANY_CD
		,        BT74.MISE_CD
		,        BN01.KBN1
    	,        BN01.KBN16 
	) AS URIAGE74
	ON (        URIAGE74.COMPANY_CD     = YOSAN45.COMPANY_CD
			AND URIAGE74.MISE_CD        = YOSAN45.MISE_CD
			AND URIAGE74.TENPO_SHUBETU  = YOSAN45.TENPO_SHUBETU
			AND URIAGE74.MOSG_KBN       = YOSAN45.MOSG_KBN
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
         )
)
order by  MISE_MST.MISE_CD
,         MISE_MST.MISE_NAME_KJ
,         DATA.TENPO_SHUBETU
,         DATA.MOSG_KBN
/*END*/