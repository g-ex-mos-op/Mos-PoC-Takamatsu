select    count(distinct MISE_MST.MISE_CD) AS COUNT
FROM (
	SELECT    BM01.COMPANY_CD
	,         BM01.MISE_CD
	,         BM01.MISE_NAME_KJ
	FROM BM01TENM AS BM01
	LEFT JOIN BC23BLCK AS BC23
	          ON (BM01.BLOCK_CD = BC23.BLOCK_CD)
	,    BM10GSIB AS BM10
	WHERE  BM01.COMPANY_CD = /*companyCd*/'00'
	AND    BM01.COMPANY_CD = BM10.COMPANY_CD
/*IF shukeiKbn.equals("AREA_DAI_CD")*/
    AND    BM01.AREA_DAI= BM10.SIBU_CD
-- ELSE
    AND    BM01.SIBU_CD = BM10.SIBU_CD
/*END*/

/*IF shukeiKbn.equals("AREA_DAI_CD")*/
    AND    BM10.AREA_DAI_FLG = '1'
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

/*IF !taishoTenpo.equals("ALL")*/
       /*IF taishoTenpo.equals("FC")*/
	AND       BM01.MISE_KBN like '_1_'
       -- ELSE
	AND       BM01.MISE_KBN like '_2_'
       /*END*/
/*END*/
) AS MISE_MST
,    (
		select  BT60.COMPANY_CD
		,       BT60.MISE_CD
	    ,       BN01.KBN16 AS MOSG_KBN
		FROM    BT60ZNIP AS BT60
		,       BN01DTEN AS BN01
		WHERE   BT60.COMPANY_CD = /*companyCd*/'00'
		AND     BT60.COMPANY_CD = BN01.COMPANY_CD
		AND     BT60.MISE_CD    = BN01.MISE_CD
		AND     BT60.OLDM_FLG   = '0'
		AND     BT60.OPEN_KBN = 1
	/*IF taishoKikan.equals("DAY1")*/ 
		AND     BT60.EIGYO_DT   = /*kikanFrom*/'20060704'
	/*END*/
	/*IF taishoKikan.equals("DAYS") */ 
		AND     BT60.EIGYO_DT between /*kikanFrom*/'20070601' AND /*kikanTo*/'20070627'
	/*END*/
		AND     BT60.EIGYO_DT   = BN01.EIGYO_DT
	/*IF !tenShu.equals("ALL")*/
	    /*IF tenShu.equals("2")*/
		AND     (BN01.KBN1 = '1' or BN01.KBN1 = '2')
	    -- ELSE
		AND     BN01.KBN1 = /*tenShu*/'1'
	    /*END*/
	/*END*/
		GROUP BY BT60.COMPANY_CD
		,        BT60.MISE_CD
    	,        BN01.KBN16 
	) AS BT60
, BM44YDMS BM44

WHERE BT60.COMPANY_CD     = MISE_MST.COMPANY_CD
AND   BT60.MISE_CD        = MISE_MST.MISE_CD
AND   BM44.YAGO_DETAIL_CD = BT60.MOSG_KBN 