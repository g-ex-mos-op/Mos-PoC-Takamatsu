SELECT    MISE_MST.COMPANY_CD
,         MISE_MST.SIBU_CD
,         MISE_MST.SIBU_NAME
,         MISE_MST.BLOCK_CD
,         MISE_MST.BLOCK_NAME
,         MISE_MST.MISE_CD
,         MISE_MST.MISE_NAME_KJ
,         DATA.KBN1
/*IF taishoKikan.equals("DAY1")*/
,         DATA.TENKO_KBN
,         DATA.TENKO_KBN_ZEN
/*END*/
,         DATA.URIAGE
,         DATA.URIAGE_ZEN
,         DATA.NEBIKI
,         DATA.URIAGE - DATA.NEBIKI AS URIAGE_AFTER_NEBIKI
,         DATA.URIAGE_ZEN - DATA.URIAGE_ZEN_AFTER_NEBIKI AS NEBIKI_ZEN
,         DATA.URIAGE_ZEN_AFTER_NEBIKI 
,         DATA.YOSAN
,         DATA.KYAKUSU
,         DATA.KYAKUSU_ZEN
,         DATA.OPEN_KBN
,         DATA.OPEN_KBN_ZEN
,         DATA.EIGYO_DAYS
,         DATA.EIGYO_DAYS_ZEN
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
/*IF userType == "02" */
    AND    BM01.ONER_CD IN (
                SELECT ONER_CD
                FROM BM06UONR
                WHERE COMPANY_CD = /*companyCd*/'00'
                AND   USER_ID = /*userId*/'99990002'
                )
/*END*/
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
  AND       BM01.AREA_DAI= BM10.SIBU_CD
-- ELSE
  AND       BM01.SIBU_CD = BM10.SIBU_CD
/*END*/

/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
  AND       BM10.AREA_DAI_FLG = '1'
/*END*/
/*IF taishoJoken.equals("SLAREA")*/
  AND       BM10.slarea_CD = /*hyojiTaisho*/'10001'
/*END*/
/*IF taishoJoken.equals("JIGYOU")*/
  AND       BM10.jigyou_CD = /*hyojiTaisho*/'11000'
/*END*/
/*IF taishoJoken.equals("HONBU")*/
  AND       BM10.HONBU_CD = /*hyojiTaisho*/'10000'
/*END*/

/*IF taishoJoken.equals("SIBU")*/
  AND       BM10.SIBU_CD = /*hyojiTaisho*/'022'
/*END*/

/*IF taishoJoken.equals("SEGMENT")*/
	AND       BM01.GYOTAI_KBN IN (
	                SELECT GYOTAI_KBN
	                FROM   BM09GTSG
	                WHERE  SEGMENT_TYPE =/*hyojiTaisho*/'M'
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

/*IF areaDaiFlg.equals("SV_CD")*/
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
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.KBN1
	             ELSE YOSAN45.TENPO_SHU END) AS KBN1
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.URIAGE ELSE 0 END) AS URIAGE
	,      (CASE WHEN YOSAN45.MISE_CD IS NOT NULL THEN YOSAN45.YOSAN
	             ELSE 0 END) AS YOSAN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.URIAGE_ZEN ELSE 0 END) AS URIAGE_ZEN
/*IF taishoKikan.equals("DAY1")*/
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.TENKO_KBN ELSE 0 END) AS TENKO_KBN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.TENKO_KBN_ZEN ELSE 0 END) AS TENKO_KBN_ZEN
/*END*/
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.KYAKUSU ELSE 0 END) AS KYAKUSU
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.KYAKUSU_ZEN ELSE 0 END) AS KYAKUSU_ZEN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.OPEN_KBN ELSE 0 END) AS OPEN_KBN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.OPEN_KBN_ZEN ELSE 0 END) AS OPEN_KBN_ZEN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.EIGYO_DAYS ELSE 0 END) AS EIGYO_DAYS
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN URIAGE60.EIGYO_DAYS_ZEN ELSE 0 END) AS EIGYO_DAYS_ZEN
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN COALESCE(BT65.NEBIKI,0) ELSE 0 END) AS NEBIKI
	,      (CASE WHEN URIAGE60.MISE_CD IS NOT NULL THEN COALESCE(BT59.URIAGE_ZEN_AFTER_NEBIKI,0) ELSE 0 END) AS URIAGE_ZEN_AFTER_NEBIKI
	FROM   (
		SELECT BT45.COMPANY_CD
		,      BT45.MISE_CD
		,      MIN(BT45.TENPO_SHU) AS TENPO_SHU
		,      SUM(BT45.YOSAN) AS YOSAN
		FROM  BT45DSJY AS BT45
		WHERE BT45.COMPANY_CD = /*companyCd*/'00'
		AND   BT45.YOSAN <> 0
	/*IF !taishoTenpo.equals("ALL")*/
   	    /*IF taishoTenpo.equals("FC")*/
		AND       BT45.FC_RC = '1'
       -- ELSE
		AND       BT45.FC_RC = '2'
       /*END*/
	/*END*/
	/*IF taishoJoken.equals("SIBU")*/
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
  AND       BT45.AREA_DAI= /*hyojiTaisho*/'022'
-- ELSE
  AND       BT45.SIBU_CD = /*hyojiTaisho*/'022'
/*END*/
	/*END*/
	/*IF taishoKikan.equals("DAY1")*/
		AND   BT45.YOSAN_DT = /*kikanFrom*/'20060405'
	/*END*/

	/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") */
		AND   BT45.YOSAN_DT between /*kikanFrom*/'20060401' AND /*kikanTo*/'20060430'
	/*END*/

	/*IF limitFlg == true*/
		AND   BT45.SIBU_CD in (
		         SELECT   BM51.SIBU_CD
		         FROM     BM51SVSB BM51
		         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
		         AND    BM51.SV_CD      = /*userId*/'9999000a'
		         GROUP BY BM51.SIBU_CD
		    )
	/*END*/

	/*IF areaDaiFlg.equals("SV_CD")*/
	    AND   BT45.MISE_CD IN (
	             SELECT   BM50.MISE_CD
	             FROM     BM50TANM AS BM50
	             WHERE    BM50.COMPANY_CD = /*companyCd*/'00'
	             AND      BM50.SV_CD      = /*svCd*/'00000921'
	        )
    /*END*/

	/*IF !tenpoShu.equals("ALL")*/
	   /*IF tenpoShu.equals("2")*/
		AND   (BT45.TENPO_SHU = '1' or BT45.TENPO_SHU = '2')
	   -- ELSE
		AND   BT45.TENPO_SHU = /*tenpoShu*/'1'
	   /*END*/
	/*END*/
		GROUP BY BT45.COMPANY_CD
		,      BT45.MISE_CD

	/*END*/
	) YOSAN45
	full outer join (
		select  BT60.COMPANY_CD
		,       BT60.MISE_CD
		,       MAX(BN01.KBN1) AS KBN1
		,       MAX(BT60.TENKO_KBN) AS TENKO_KBN
		,       SUM(BT60.URIAGE) AS URIAGE
	/*IF userType.equals("01") */
		,       SUM(BT60.URI_YOSAN) AS YOSAN
	--ELSE
		,       SUM(BT60.ONER_YOSAN) AS YOSAN
	/*END*/
		,       SUM(BT60.KYAKUSU) AS KYAKUSU
		,       MAX(BT60.OPEN_KBN) AS OPEN_KBN
		,       SUM(BT60.EIGYO_DAYS) AS EIGYO_DAYS
	/*IF dataShu.equals("HOSEI") || dataShu.equals("DOGETU")*/
		,       MAX(BT60.TENKO_KBN_ZEN_DOGETU)  AS TENKO_KBN_ZEN
		,       SUM(BT60.URIAGE_ZEN_DOGETU)     AS URIAGE_ZEN
		,       SUM(BT60.KYAKUSU_ZEN_DOGETU)    AS KYAKUSU_ZEN
		,       MAX(BT60.OPEN_KBN_ZEN_DOGETU)   AS OPEN_KBN_ZEN
		,       SUM(BT60.EIGYO_DAYS_ZEN_DOGETU) AS EIGYO_DAYS_ZEN
	/*END*/
	/*IF dataShu.equals("DOYO")*/
		,       MAX(BT60.TENKO_KBN_ZEN_DOYO)  AS TENKO_KBN_ZEN
		,       SUM(BT60.URIAGE_ZEN_DOYO)     AS URIAGE_ZEN
		,       SUM(BT60.KYAKUSU_ZEN_DOYO)    AS KYAKUSU_ZEN
		,       MAX(BT60.OPEN_KBN_ZEN_DOYO)   AS OPEN_KBN_ZEN
		,       SUM(BT60.EIGYO_DAYS_ZEN_DOYO) AS EIGYO_DAYS_ZEN
	/*END*/
	/*IF dataShu.equals("DOJITU")*/
		,       MAX(BT60.TENKO_KBN_ZEN_DOJITU)  AS TENKO_KBN_ZEN
		,       SUM(BT60.URIAGE_ZEN_DOJITU)     AS URIAGE_ZEN
		,       SUM(BT60.KYAKUSU_ZEN_DOJITU)    AS KYAKUSU_ZEN
		,       MAX(BT60.OPEN_KBN_ZEN_DOJITU)   AS OPEN_KBN_ZEN
		,       SUM(BT60.EIGYO_DAYS_ZEN_DOJITU) AS EIGYO_DAYS_ZEN
	/*END*/
		FROM    BT60ZNIP AS BT60
		,       BN01DTEN AS BN01
		WHERE   BT60.COMPANY_CD = /*companyCd*/'00'
		AND     BT60.COMPANY_CD = BN01.COMPANY_CD
		AND     BT60.MISE_CD    = BN01.MISE_CD
		AND     BT60.OLDM_FLG   = '0'
	/*IF taishoKikan.equals("DAY1")*/
		AND     BT60.EIGYO_DT   = /*kikanFrom*/'20060704'
	/*END*/
	/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") */
		AND     BT60.EIGYO_DT between /*kikanFrom*/'20060401' AND /*kikanTo*/'20060430'
	/*END*/
		AND     BT60.EIGYO_DT   = BN01.EIGYO_DT

	/*IF tenpoShu.equals("ALL")*/
		AND     (BT60.OPEN_KBN = 1 or BT60.OPEN_KBN_ZEN_DOGETU = 1)
	-- ELSE
		AND     BT60.OPEN_KBN = 1
	/*END*/

	/*IF dataShu.equals("HOSEI")*/
		AND     (BT60.URIAGE > 0 AND BT60.URIAGE_ZEN_DOGETU > 0)
	/*END*/
	/*IF !tenpoShu.equals("ALL")*/
	    /*IF tenpoShu.equals("2")*/
		AND     (BN01.KBN1 = '1' or BN01.KBN1 = '2')
	    -- ELSE
		AND     BN01.KBN1 = /*tenpoShu*/'1'
	    /*END*/
	/*END*/
		GROUP BY BT60.COMPANY_CD
		,        BT60.MISE_CD
	) AS URIAGE60
	ON (        URIAGE60.COMPANY_CD = YOSAN45.COMPANY_CD
			AND URIAGE60.MISE_CD    = YOSAN45.MISE_CD
	   )
	left outer join (
		SELECT
		  BT65SADY.COMPANY_CD,
		  BT65SADY.MISE_CD,
		  SUM(COALESCE(BT65SADY.NEBIKI,0)) AS NEBIKI
		FROM
		  BT65SADY
	/*IF dataShu.equals("HOSEI") */
		  INNER JOIN (
			SELECT
				EIGYO_DT,
				MISE_CD
			FROM
			 	BT60ZNIP
			WHERE
				URIAGE > 0  AND
				URIAGE_ZEN_DOGETU > 0 AND
				COMPANY_CD = /*companyCd*/'00'
                /*IF taishoKikan.equals("DAY1")*/
                    AND   EIGYO_DT = /*kikanFrom*/'20060405'
                /*END*/
                /*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") */
                    AND   EIGYO_DT between /*kikanFrom*/'20060401' AND /*kikanTo*/'20060430'
                /*END*/
		  ) BT60_HOSEI ON (BT60_HOSEI.EIGYO_DT = BT65SADY.EIGYO_DT) AND (BT60_HOSEI.MISE_CD = BT65SADY.MISE_CD)
	/*END*/
		WHERE
		  BT65SADY.COMPANY_CD = /*companyCd*/'00'
		/*IF taishoKikan.equals("DAY1")*/
			AND   BT65SADY.EIGYO_DT = /*kikanFrom*/'20060405'
		/*END*/
		/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") */
			AND   BT65SADY.EIGYO_DT between /*kikanFrom*/'20060401' AND /*kikanTo*/'20060430'
		/*END*/
		GROUP BY
		  BT65SADY.COMPANY_CD,
		  BT65SADY.MISE_CD
		) AS BT65
	ON (
			(
				BT65.COMPANY_CD = YOSAN45.COMPANY_CD
			AND BT65.MISE_CD    = YOSAN45.MISE_CD
			)
			OR
			(   BT65.COMPANY_CD = URIAGE60.COMPANY_CD
			AND BT65.MISE_CD    = URIAGE60.MISE_CD
			)
	   )
	left outer join (
		SELECT
		  BT59ANZJ.COMPANY_CD,
		  BT59ANZJ.MISE_CD,
/*IF dataShu.equals("DOGETU")*/
		  SUM(COALESCE(BT59ANZJ.URI_ZEN_AFT_NBK_DOGETU,0)) AS URIAGE_ZEN_AFTER_NEBIKI
/*END*/
/*IF dataShu.equals("DOJITU")*/
		  SUM(COALESCE(BT59ANZJ.URI_ZEN_AFT_NBK_DOJITU,0)) AS URIAGE_ZEN_AFTER_NEBIKI
/*END*/
/*IF dataShu.equals("DOYO")*/
		  SUM(COALESCE(BT59ANZJ.URI_ZEN_AFT_NBK_DOYO,0)) AS URIAGE_ZEN_AFTER_NEBIKI
/*END*/
/*IF dataShu.equals("HOSEI") */
		  SUM(COALESCE(BT59ANZJ.URI_ZEN_AFT_NBK_HOSEI,0)) AS URIAGE_ZEN_AFTER_NEBIKI
/*END*/
		FROM
		  BT59ANZJ
		WHERE
		  BT59ANZJ.COMPANY_CD = /*companyCd*/'00'
		/*IF taishoKikan.equals("DAY1")*/
			AND   BT59ANZJ.EIGYO_DT = /*kikanFrom*/'20060405'
		/*END*/
		/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") */
			AND   BT59ANZJ.EIGYO_DT between /*kikanFrom*/'20060401' AND /*kikanTo*/'20060430'
		/*END*/
		GROUP BY
		  BT59ANZJ.COMPANY_CD,
		  BT59ANZJ.MISE_CD
		) AS BT59
	ON (
			(
				BT59.COMPANY_CD = YOSAN45.COMPANY_CD
			AND BT59.MISE_CD    = YOSAN45.MISE_CD
			)
			OR
			(   BT59.COMPANY_CD = URIAGE60.COMPANY_CD
			AND BT59.MISE_CD    = URIAGE60.MISE_CD
			)
	   )
) DATA
WHERE DATA.COMPANY_CD = MISE_MST.COMPANY_CD
AND   DATA.MISE_CD    = MISE_MST.MISE_CD
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
order by  MISE_MST.COMPANY_CD
,         MISE_MST.MISE_CD
-- ELSE
order by  MISE_MST.COMPANY_CD
,         MISE_MST.SIBU_CD
,         MISE_MST.BLOCK_CD
,         MISE_MST.MISE_CD
/*END*/
