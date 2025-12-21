select    MISE_MST.COMPANY_CD
,         RTRIM(MISE_MST.SIBU_CD) AS SIBU_CD
,         RTRIM(MISE_MST.SIBU_NAME) AS SIBU_NAME
,         MISE_MST.BLOCK_CD
,         MISE_MST.BLOCK_NAME
,         MISE_MST.MISE_CD
,         MISE_MST.MISE_NAME_KJ
,         BM50.SV_CD
,         BM50.USERNAMEKJ
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
,         DATA.CHARGE_KIN_CANCEL
,         DATA.CHARGE_KEN_CANCEL
,         DATA.USE_KIN_CANCEL
,         DATA.USE_KEN_CANCEL
,         DATA.BONUS_V_ISSUE
,         DATA.BONUS_V_USE
,         DATA.COUPON_V_ISSUE
,         DATA.COUPON_V_USE
,         DATA.ZANDAKA
,         DATA.ISSUE_CNT
,         DATA.CHARGE_KIN
,         DATA.CHARGE_KEN
,         DATA.KESSAI_KIN
,         DATA.KESSAI_KEN
,         DATA.ZEN_ISSUE_CNT
,         DATA.ZEN_CHARGE_KIN
,         DATA.ZEN_CHARGE_KEN
,         DATA.ZEN_KESSAI_KIN
,         DATA.ZEN_KESSAI_KEN
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

/*IF taishoJoken.equals("GYOTAI")*/
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
	AND  EXISTS (
	                SELECT   BM50.MISE_CD
	                FROM     BM50TANM AS BM50
	                WHERE    BM50.COMPANY_CD = /*companyCd*/'00'
	                AND      BM50.SV_CD      = /*svCd*/'00000921'
	                AND      BM50.MISE_CD = BM01.MISE_CD
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
	SELECT (CASE WHEN URIAGE36.COMPANY_CD IS NOT NULL THEN URIAGE36.COMPANY_CD 
	             ELSE YOSAN44.COMPANY_CD END) AS COMPANY_CD
	,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.MISE_CD 
	             ELSE YOSAN44.MISE_CD END) AS MISE_CD
	,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.KBN1 
	             ELSE YOSAN44.TENPO_SHU END) AS KBN1
	,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.URIAGE ELSE 0 END) AS URIAGE
	,      (CASE WHEN YOSAN44.MISE_CD IS NOT NULL THEN YOSAN44.YOSAN 
	             ELSE 0 END) AS YOSAN
	,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.URIAGE_ZEN ELSE 0 END) AS URIAGE_ZEN
	,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.KYAKUSU ELSE 0 END) AS KYAKUSU
	,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.KYAKUSU_ZEN ELSE 0 END) AS KYAKUSU_ZEN
	,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.OPEN_KBN ELSE 0 END) AS OPEN_KBN
	,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.OPEN_KBN_ZEN ELSE 0 END) AS OPEN_KBN_ZEN
	,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.EIGYO_DAYS ELSE 0 END) AS EIGYO_DAYS
	,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.EIGYO_DAYS_ZEN ELSE 0 END) AS EIGYO_DAYS_ZEN
	,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.CHARGE_KIN_CANCEL ELSE 0 END) AS CHARGE_KIN_CANCEL	
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.CHARGE_KEN_CANCEL ELSE 0 END) AS CHARGE_KEN_CANCEL	
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.USE_KIN_CANCEL ELSE 0 END) AS USE_KIN_CANCEL	    
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.USE_KEN_CANCEL ELSE 0 END) AS USE_KEN_CANCEL	    
	,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.BONUS_V_ISSUE ELSE 0 END) AS BONUS_V_ISSUE
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.BONUS_V_USE ELSE 0 END) AS BONUS_V_USE
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.COUPON_V_ISSUE ELSE 0 END) AS COUPON_V_ISSUE	        
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.COUPON_V_USE ELSE 0 END) AS COUPON_V_USE	 
	,      (CASE WHEN MAXDT.ZANDAKA IS NULL THEN 0 ELSE MAXDT.ZANDAKA END) AS ZANDAKA
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.ISSUE_CNT ELSE 0 END) AS ISSUE_CNT
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.CHARGE_KIN ELSE 0 END) AS CHARGE_KIN	        
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.CHARGE_KEN ELSE 0 END) AS CHARGE_KEN	 
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.KESSAI_KIN ELSE 0 END) AS KESSAI_KIN	     
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.KESSAI_KEN ELSE 0 END) AS KESSAI_KEN    
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.ZEN_ISSUE_CNT ELSE 0 END) AS ZEN_ISSUE_CNT   
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.ZEN_CHARGE_KIN ELSE 0 END) AS ZEN_CHARGE_KIN       
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.ZEN_CHARGE_KEN ELSE 0 END) AS ZEN_CHARGE_KEN	     
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.ZEN_KESSAI_KIN ELSE 0 END) AS ZEN_KESSAI_KIN    
    ,      (CASE WHEN URIAGE36.MISE_CD IS NOT NULL THEN URIAGE36.ZEN_KESSAI_KEN ELSE 0 END) AS ZEN_KESSAI_KEN    
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
		select  BD36.COMPANY_CD
		,       BD36.MISE_CD
		,       MIN(BN01.KBN1) AS KBN1
		,       MAX(BD36.OPEN_KBN) AS OPEN_KBN
		,       SUM(BD36./*$dataShu*/'DOGETU'_URIAGE) AS URIAGE
		,       SUM(BD36./*$dataShu*/'DOGETU'_KYAKUSU) AS KYAKUSU
		,       SUM(BD36./*$dataShu*/'DOGETU'_EIGYO_DAYS) AS EIGYO_DAYS
	/*IF userType.equals("01") */
		,       SUM(BD36./*$dataShu*/'DOGETU'_YOSAN) AS YOSAN
	--ELSE
		,       SUM(BD36./*$dataShu*/'DOGETU'_ONER_YOSAN) AS YOSAN
	/*END*/
		,       SUM(BD36./*$dataShu*/'DOGETU'_ZEN_URIAGE)        AS URIAGE_ZEN
		,       SUM(BD36./*$dataShu*/'DOGETU'_ZEN_KYAKUSU)       AS KYAKUSU_ZEN
		,       MAX(BM45.OPEN_KBN_ZEN)             AS OPEN_KBN_ZEN
		,       SUM(BD36./*$dataShu*/'DOGETU'_ZEN_EIGYO_DAYS)    AS EIGYO_DAYS_ZEN
		,       SUM(BD36./*$dataShu*/'DOGETU'_CHARGE_KIN_CANCEL) AS CHARGE_KIN_CANCEL
		,       SUM(BD36./*$dataShu*/'DOGETU'_CHARGE_KEN_CANCEL) AS CHARGE_KEN_CANCEL
		,       SUM(BD36./*$dataShu*/'DOGETU'_USE_KIN_CANCEL)    AS USE_KIN_CANCEL
		,       SUM(BD36./*$dataShu*/'DOGETU'_USE_KEN_CANCEL)    AS USE_KEN_CANCEL
		,       SUM(BD36./*$dataShu*/'DOGETU'_BONUS_V_ISSUE)     AS BONUS_V_ISSUE
		,       SUM(BD36./*$dataShu*/'DOGETU'_BONUS_V_USE)       AS BONUS_V_USE
		,       SUM(BD36./*$dataShu*/'DOGETU'_COUPON_V_ISSUE)    AS COUPON_V_ISSUE
		,       SUM(BD36./*$dataShu*/'DOGETU'_COUPON_V_USE)      AS COUPON_V_USE
		,       SUM(BD36./*$dataShu*/'DOGETU'_ISSUE_CNT)         AS ISSUE_CNT
		,       SUM(BD36./*$dataShu*/'DOGETU'_CHARGE_KIN)        AS CHARGE_KIN
		,       SUM(BD36./*$dataShu*/'DOGETU'_CHARGE_KEN)        AS CHARGE_KEN
		,       SUM(BD36./*$dataShu*/'DOGETU'_KESSAI_KIN)        AS KESSAI_KIN
		,       SUM(BD36./*$dataShu*/'DOGETU'_KESSAI_KEN)        AS KESSAI_KEN
		,       SUM(BD36./*$dataShu*/'DOGETU'_ZEN_ISSUE_CNT)     AS ZEN_ISSUE_CNT
		,       SUM(BD36./*$dataShu*/'DOGETU'_ZEN_CHARGE_KIN)    AS ZEN_CHARGE_KIN
		,       SUM(BD36./*$dataShu*/'DOGETU'_ZEN_CHARGE_KEN)    AS ZEN_CHARGE_KEN
		,       SUM(BD36./*$dataShu*/'DOGETU'_ZEN_KESSAI_KIN)    AS ZEN_KESSAI_KIN
		,       SUM(BD36./*$dataShu*/'DOGETU'_ZEN_KESSAI_KEN)    AS ZEN_KESSAI_KEN			
		FROM    BD36ZGMC AS BD36
		,       (
	          SELECT BN01.EIGYO_YM, BN01.COMPANY_CD, BN01.MISE_CD, BN01.KBN1
	          FROM   BN01DTEN BN01
	          GROUP BY BN01.EIGYO_YM, BN01.COMPANY_CD, BN01.MISE_CD, BN01.KBN1
	        ) as BN01
		,       (
	          SELECT SUBSTR(EIGYO_DT, 1, 6) AS EIGYO_YM
	          ,      COMPANY_CD, MISE_CD, OPEN_KBN
	 /*IF dataShu.equals("HOSEI")*/ 
	          , MAX(OP_KBN_ZEN_DOGETU) AS OPEN_KBN_ZEN
	 --ELSE
	          , MAX(OP_KBN_ZEN_/*$dataShu*/'DOGETU') AS OPEN_KBN_ZEN
	 /*END*/
	          FROM   BM45ZDAY
	          GROUP BY SUBSTR(EIGYO_DT, 1, 6)
	          ,        COMPANY_CD, MISE_CD, OPEN_KBN
	        ) as BM45
		WHERE   BD36.COMPANY_CD = /*companyCd*/'00'
        AND     BM45.EIGYO_YM   = BN01.EIGYO_YM
        AND     BD36.EIGYO_DT   = BM45.EIGYO_YM
        AND     BM45.COMPANY_CD = bn01.COMPANY_CD
        AND     BD36.COMPANY_CD = BM45.COMPANY_CD
        AND     BM45.MISE_CD    = bn01.MISE_CD
        AND     BD36.MISE_CD    = BM45.MISE_CD
        AND     BD36.OPEN_KBN   = BM45.OPEN_KBN
	/*IF taishoKikan.equals("KIBETU") */ 
		AND     BD36.EIGYO_DT between /*kikanFrom*/'200706' AND /*kikanTo*/'200706'
	--ELSE
		AND     BD36.EIGYO_DT   = /*kikanFrom*/'200706'
	/*END*/
/*IF tenpoShu == "1" || tenpoShu == "3"*/
        AND   BN01.KBN1 = /*tenpoShu*/'1'
/*END*/
/*IF tenpoShu == "2" */
        AND   BN01.KBN1 IN ('1', '2')
/*END*/
		AND    BD36.OLDM_FLG   = '0'
	/*IF tenpoShu.equals("ALL")*/
		AND     (BD36.OPEN_KBN = 1 or BM45.OPEN_KBN_ZEN = 1) 
	-- ELSE
		AND     BD36.OPEN_KBN = 1
	/*END*/

	/*IF dataShu.equals("HOSEI")*/
		AND     (BD36.HOSEI_URIAGE > 0 AND BD36.HOSEI_ZEN_URIAGE > 0)
	/*END*/
		GROUP BY BD36.COMPANY_CD
		,        BD36.MISE_CD
	) AS URIAGE36
	ON (        URIAGE36.COMPANY_CD = YOSAN44.COMPANY_CD
			AND URIAGE36.MISE_CD    = YOSAN44.MISE_CD
	   )
	LEFT JOIN (
	     SELECT EIGYO_DT
	     ,      COMPANY_CD
	     ,      MISE_CD
	     ,      ZANDAKA
	     FROM (SELECT MAX(BD3.EIGYO_DT) over(order by BD3.COMPANY_CD, BD3.MISE_CD) AS MAX_DT
	           ,      BD3.EIGYO_DT
	           ,      BD3.COMPANY_CD
	           ,      BD3.MISE_CD
	           ,      BD3./*$dataShu*/'DOJITU'_ZANDAKA AS ZANDAKA
	               FROM BD36ZGMC BD3
	    /*IF taishoKikan.equals("KIBETU")*/ 
	           WHERE BD3.EIGYO_DT BETWEEN /*kikanFrom*/'200705' AND /*kikanTo*/'200705'
	    --ELSE
	           WHERE BD3.EIGYO_DT = /*kikanFrom*/'201303'
	    /*END*/
	           AND   BD3.COMPANY_CD = /*companyCd*/'00'
	           AND   BD3.OPEN_KBN =1
	           GROUP BY BD3.EIGYO_DT
	           ,      BD3.COMPANY_CD
	           ,      BD3.MISE_CD
	           ,      BD3./*$dataShu*/'DOJITU'_ZANDAKA) GETMAX
	     WHERE EIGYO_DT = MAX_DT) MAXDT
	ON (    MAXDT.COMPANY_CD = URIAGE36.COMPANY_CD
        AND MAXDT.MISE_CD    = URIAGE36.MISE_CD)
) DATA LEFT JOIN 
        (select BM50.COMPANY_CD, BM50.MISE_CD , BM50.sv_cd , RTRIM(BR01.user_name_kj) as USERNAMEKJ
         from BM50TANM as BM50, BR01USER as BR01 
         where BM50.sv_cd = BR01.USER_ID ) BM50

     on (DATA.COMPANY_CD = BM50.COMPANY_CD
     and DATA.MISE_CD = BM50.MISE_CD)

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
