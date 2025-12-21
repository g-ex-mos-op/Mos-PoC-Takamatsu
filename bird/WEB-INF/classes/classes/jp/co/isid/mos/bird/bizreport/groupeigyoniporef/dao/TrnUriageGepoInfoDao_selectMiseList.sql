select    MISE_MST.COMPANY_CD
,         RTRIM(MISE_MST.SIBU_CD) AS SIBU_CD
,         RTRIM(MISE_MST.SIBU_NAME) AS SIBU_NAME
,         MISE_MST.BLOCK_CD
,         MISE_MST.BLOCK_NAME
,         MISE_MST.MISE_CD
,         MISE_MST.MISE_NAME_KJ
,         DATA.KBN1
,         DATA.URIAGE
,         DATA.URIAGE_ZEN
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
	SELECT (CASE WHEN URIAGE64.COMPANY_CD IS NOT NULL THEN URIAGE64.COMPANY_CD 
	             ELSE YOSAN44.COMPANY_CD END) AS COMPANY_CD
	,      (CASE WHEN URIAGE64.MISE_CD IS NOT NULL THEN URIAGE64.MISE_CD 
	             ELSE YOSAN44.MISE_CD END) AS MISE_CD
	,      (CASE WHEN URIAGE64.MISE_CD IS NOT NULL THEN URIAGE64.KBN1 
	             ELSE YOSAN44.TENPO_SHU END) AS KBN1
	,      (CASE WHEN URIAGE64.MISE_CD IS NOT NULL THEN URIAGE64.URIAGE ELSE 0 END) AS URIAGE
	,      (CASE WHEN YOSAN44.MISE_CD IS NOT NULL THEN YOSAN44.YOSAN 
	             ELSE 0 END) AS YOSAN
	,      (CASE WHEN URIAGE64.MISE_CD IS NOT NULL THEN URIAGE64.URIAGE_ZEN ELSE 0 END) AS URIAGE_ZEN
	,      (CASE WHEN URIAGE64.MISE_CD IS NOT NULL THEN URIAGE64.KYAKUSU ELSE 0 END) AS KYAKUSU
	,      (CASE WHEN URIAGE64.MISE_CD IS NOT NULL THEN URIAGE64.KYAKUSU_ZEN ELSE 0 END) AS KYAKUSU_ZEN
	,      (CASE WHEN URIAGE64.MISE_CD IS NOT NULL THEN URIAGE64.OPEN_KBN ELSE 0 END) AS OPEN_KBN
	,      (CASE WHEN URIAGE64.MISE_CD IS NOT NULL THEN URIAGE64.OPEN_KBN_ZEN ELSE 0 END) AS OPEN_KBN_ZEN
	,      (CASE WHEN URIAGE64.MISE_CD IS NOT NULL THEN URIAGE64.EIGYO_DAYS ELSE 0 END) AS EIGYO_DAYS
	,      (CASE WHEN URIAGE64.MISE_CD IS NOT NULL THEN URIAGE64.EIGYO_DAYS_ZEN ELSE 0 END) AS EIGYO_DAYS_ZEN
	FROM   (
		SELECT BT44.COMPANY_CD
		,      BT44.MISE_CD
		,      MIN(BT44.TENPO_SHU) AS TENPO_SHU
		,      SUM(BT44.YOSAN) AS YOSAN
	/*IF taishoKikan.equals("APPMONTH") */ 
		FROM  BT45DSJY AS BT44
	--ELSE
		FROM  BT44MSJY AS BT44
	/*END*/
		WHERE BT44.COMPANY_CD = /*companyCd*/'00'
		AND   BT44.YOSAN <> 0
	/*IF !taishoTenpo.equals("ALL")*/
   	    /*IF taishoTenpo.equals("FC")*/
		AND       BT44.FC_RC = '1'
       -- ELSE
		AND       BT44.FC_RC = '2'
       /*END*/
	/*END*/
	/*IF taishoJoken.equals("SIBU")*/
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
  AND       BT44.AREA_DAI= /*hyojiTaisho*/'022'
-- ELSE
  AND       BT44.SIBU_CD = /*hyojiTaisho*/'022'
/*END*/
	/*END*/

	/*IF taishoKikan.equals("KIBETU") */ 
		AND   BT44.YOSAN_DT between /*kikanFrom*/'200706' AND /*kikanTo*/'200706'
	--ELSE
		/*IF taishoKikan.equals("APPMONTH") */ 
		AND   SUBSTR(BT44.YOSAN_DT, 1,6) = /*kikanFrom*/'200706'
		AND   BT44.YOSAN_DT <= /*kikanTo*/'20070629'
		--ELSE
		AND   BT44.YOSAN_DT = /*kikanFrom*/'200706'
		/*END*/
	/*END*/

	/*IF limitFlg == true*/
		AND   BT44.SIBU_CD in (
		         SELECT   BM51.SIBU_CD
		         FROM     BM51SVSB BM51
		         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
		         AND    BM51.SV_CD      = /*userId*/'9999000a'
		         GROUP BY BM51.SIBU_CD
		    )
	--ELSE
		/*IF areaDaiFlg.equals("SV_CD")*/
		AND   BT44.SIBU_CD IN (
		          SELECT   distinct BM01TENM.SIBU_CD
		          FROM     BM50TANM , BM01TENM 
		          WHERE    BM50TANM.COMPANY_CD = /*companyCd*/'00'
		          AND      BM50TANM.SV_CD      = /*svCd*/'00000921'
		          AND      BM50TANM.COMPANY_CD=BM01TENM.COMPANY_CD
		          AND      BM50TANM.MISE_CD=BM01TENM.MISE_CD
		     )                      
        /*END*/ 
	/*END*/              

	/*IF !tenpoShu.equals("ALL")*/
	   /*IF tenpoShu.equals("2")*/
		AND   (BT44.TENPO_SHU = '1' or BT44.TENPO_SHU = '2')
	   -- ELSE
		AND   BT44.TENPO_SHU = /*tenpoShu*/'1'
	   /*END*/
	/*END*/
		GROUP BY BT44.COMPANY_CD
		,      BT44.MISE_CD

	/*END*/
	) YOSAN44
	full outer join (
		select  BT64.COMPANY_CD
		,       BT64.MISE_CD
		,       MIN(BN01.KBN1) AS KBN1
		,       MAX(BT64.OPEN_KBN) AS OPEN_KBN
	/*IF dataShu.equals("DOGETU")*/
		,       SUM(BT64.URIAGE_DOGETU) AS URIAGE
		,       SUM(BT64.KYAKUSU_DOGETU) AS KYAKUSU
		,       SUM(BT64.EIGYO_DAYS_DOGETU) AS EIGYO_DAYS
	/*IF userType.equals("01") */
		,       SUM(BT64.YOSAN_DOGETU) AS YOSAN
	--ELSE
		,       SUM(BT64.ONER_YOSAN_DOGETU) AS YOSAN
	/*END*/
		,       SUM(BT64.URIAGE_ZEN_DOGETU)     AS URIAGE_ZEN
		,       SUM(BT64.KYAKUSU_ZEN_DOGETU)    AS KYAKUSU_ZEN
		,       MAX(BT64.OPEN_KBN_ZEN_DOGETU)   AS OPEN_KBN_ZEN
		,       SUM(BT64.EIGYO_DAYS_ZEN_DOGETU) AS EIGYO_DAYS_ZEN
	/*END*/
	/*IF dataShu.equals("DOYO")*/
		,       SUM(BT64.URIAGE_DOYO) AS URIAGE
		,       SUM(BT64.KYAKUSU_DOYO) AS KYAKUSU
		,       SUM(BT64.EIGYO_DAYS_DOYO) AS EIGYO_DAYS
	/*IF userType.equals("01") */
		,       SUM(BT64.YOSAN_DOYO) AS YOSAN
	--ELSE
		,       SUM(BT64.ONER_YOSAN_DOYO) AS YOSAN
	/*END*/
		,       SUM(BT64.URIAGE_ZEN_DOYO)     AS URIAGE_ZEN
		,       SUM(BT64.KYAKUSU_ZEN_DOYO)    AS KYAKUSU_ZEN
		,       MAX(BT64.OPEN_KBN_ZEN_DOYO)   AS OPEN_KBN_ZEN
		,       SUM(BT64.EIGYO_DAYS_ZEN_DOYO) AS EIGYO_DAYS_ZEN
	/*END*/
	/*IF dataShu.equals("DOJITU")*/
		,       SUM(BT64.URIAGE_DOJITU) AS URIAGE
		,       SUM(BT64.KYAKUSU_DOJITU) AS KYAKUSU
		,       SUM(BT64.EIGYO_DAYS_DOJITU) AS EIGYO_DAYS
	/*IF userType.equals("01") */
		,       SUM(BT64.YOSAN_DOJITU) AS YOSAN
	--ELSE
		,       SUM(BT64.ONER_YOSAN_DOHITU) AS YOSAN
	/*END*/
		,       SUM(BT64.URIAGE_ZEN_DOJITU)     AS URIAGE_ZEN
		,       SUM(BT64.KYAKUSU_ZEN_DOJITU)    AS KYAKUSU_ZEN
		,       MAX(BT64.OPEN_KBN_ZEN_DOJITU)   AS OPEN_KBN_ZEN
		,       SUM(BT64.EIGYO_DAYS_ZEN_DOJITU) AS EIGYO_DAYS_ZEN
	/*END*/
	/*IF dataShu.equals("HOSEI") */
		,       SUM(BT64.URIAGE_HOSEI) AS URIAGE
		,       SUM(BT64.KYAKUSU_HOSEI) AS KYAKUSU
		,       SUM(BT64.EIGYO_DAYS_HOSEI) AS EIGYO_DAYS
	/*IF userType.equals("01") */
		,       SUM(BT64.YOSAN_HOSEI) AS YOSAN
	--ELSE
		,       SUM(BT64.ONER_YOSAN_HOSEI) AS YOSAN
	/*END*/
		,       SUM(BT64.URIAGE_ZEN_HOSEI)     AS URIAGE_ZEN
		,       SUM(BT64.KYAKUSU_ZEN_HOSEI)    AS KYAKUSU_ZEN
		,       MAX(BT64.OPEN_KBN_ZEN_HOSEI)   AS OPEN_KBN_ZEN
		,       SUM(BT64.EIGYO_DAYS_ZEN_HOSEI) AS EIGYO_DAYS_ZEN
	/*END*/
		FROM    BT64ZGEP AS BT64
		,       (
               SELECT EIGYO_YM
               ,      MISE_CD
               ,      KBN1
               FROM BN01DTEN 
/*IF taishoKikan.equals("KIBETU")*/ 
               WHERE  EIGYO_YM  BETWEEN /*kikanFrom*/'200705' AND /*kikanTo*/'200705'
--ELSE
               WHERE  EIGYO_YM  = /*kikanFrom*/'200705'
/*END*/
/*IF tenpoShu == "1" || tenpoShu == "3"*/
               AND   KBN1 = /*tenpoShu*/'1'
/*END*/
/*IF tenpoShu == "2" */
               AND   KBN1 IN ('1', '2')
/*END*/
               AND   COMPANY_CD = /*companyCd*/'00'
               GROUP BY EIGYO_YM
               ,        KBN1
               ,        MISE_CD
            ) BN01
		WHERE   BT64.COMPANY_CD = /*companyCd*/'00'
		AND     BT64.MISE_CD    = BN01.MISE_CD
		AND     BT64.OLDM_FLG   = '0'
	/*IF taishoKikan.equals("KIBETU") */ 
		AND     BT64.EIGYO_DT between /*kikanFrom*/'200706' AND /*kikanTo*/'200706'
	--ELSE
		AND     BT64.EIGYO_DT   = /*kikanFrom*/'200706'
	/*END*/
		AND     BT64.EIGYO_DT   = BN01.EIGYO_YM

	/*IF tenpoShu.equals("ALL")*/
		AND     (BT64.OPEN_KBN = 1 or BT64.OPEN_KBN_ZEN_DOGETU = 1) 
	-- ELSE
		AND     BT64.OPEN_KBN = 1
	/*END*/

	/*IF dataShu.equals("HOSEI")*/
		AND     (BT64.URIAGE_DOGETU > 0 AND BT64.URIAGE_ZEN_DOGETU > 0)
	/*END*/
		GROUP BY BT64.COMPANY_CD
		,        BT64.MISE_CD
	) AS URIAGE64
	ON (        URIAGE64.COMPANY_CD = YOSAN44.COMPANY_CD
			AND URIAGE64.MISE_CD    = YOSAN44.MISE_CD
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
