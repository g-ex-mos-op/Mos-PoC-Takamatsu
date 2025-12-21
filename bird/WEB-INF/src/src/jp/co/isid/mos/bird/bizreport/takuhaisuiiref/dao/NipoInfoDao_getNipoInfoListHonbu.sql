SELECT EIGYO_DT 
,      sum(URIAGE)       as URIAGE
,      sum(URIAGE_ZEN)   as URIAGE_ZEN
,      sum(URIAGE_TAKU)  as URIAGE_TAKU
,      sum(URIAGE_TAKU_ZEN) as URIAGE_TAKU_ZEN
,      sum(KYAKUSU)     as KYAKUSU
,      sum(KYAKUSU_ZEN) as KYAKUSU_ZEN
,      sum(KENSU_TAKU)  as KYAKUSU_TAKU 
,      sum(KENSU_TAKU_ZEN) as KYAKUSU_TAKU_ZEN 
,      COUNT(DISTINCT (case when URIAGE_TAKU >0 then MISE_CD else null end)) as HAN_TENPO_CNT
/*IF zenDataShubetu.equals("HOSEI") */
,      COUNT(DISTINCT(case when OPEN_KBN = 1 AND OPEN_KBN_ZEN = 1
                                AND URI_KBN>0
                                AND URI_KBN_ZEN>0 
                        then MISE_CD 
                        else null end)) as TENPO_CNT_HOSEI

,      COUNT(DISTINCT(case when OPEN_KBN = 1 AND OPEN_KBN_ZEN = 1
                                AND URI_KBN>0
                                AND URI_KBN_ZEN>0 
                                AND URIAGE_TAKU>0
                                AND URIAGE_TAKU_ZEN>0
                        then MISE_CD 
                        else null end)) as HAN_TENPO_CNT_HOSEI

,      sum(case when OPEN_KBN = 1 AND OPEN_KBN_ZEN = 1
                      AND URI_KBN>0 AND URI_KBN_ZEN>0 
        THEN URIAGE
        ELSE 0 END)       as URIAGE_HOSEI

,      sum(case when OPEN_KBN = 1 AND OPEN_KBN_ZEN = 1
                      AND URI_KBN>0 AND URI_KBN_ZEN>0 
                      AND URIAGE_TAKU>0 AND URIAGE_TAKU_ZEN>0 
        THEN URIAGE_TAKU
        ELSE 0 END)  as URIAGE_TAKU_HOSEI

,      sum(case when OPEN_KBN = 1 AND OPEN_KBN_ZEN = 1
                      AND URI_KBN>0 AND URI_KBN_ZEN>0 
        THEN KYAKUSU
        ELSE 0 END)       as KYAKUSU_HOSEI

,      sum(case when OPEN_KBN = 1 AND OPEN_KBN_ZEN = 1
                      AND URI_KBN>0 AND URI_KBN_ZEN>0 
                      AND URIAGE_TAKU>0 AND URIAGE_TAKU_ZEN>0 
        THEN KENSU_TAKU
        ELSE 0 END)  as KYAKUSU_TAKU_HOSEI

,      sum(case when OPEN_KBN = 1 AND OPEN_KBN_ZEN = 1
                      AND URI_KBN>0 AND URI_KBN_ZEN>0 
        THEN URIAGE_ZEN
        ELSE 0 END)       as URIAGE_ZEN_HOSEI

,      sum(case when OPEN_KBN = 1 AND OPEN_KBN_ZEN = 1
                      AND URI_KBN>0 AND URI_KBN_ZEN>0 
                      AND URIAGE_TAKU>0 AND URIAGE_TAKU_ZEN>0 
        THEN URIAGE_TAKU_ZEN
        ELSE 0 END)  as URIAGE_TAKU_ZEN_HOSEI

,      sum(case when OPEN_KBN = 1 AND OPEN_KBN_ZEN = 1
                      AND URI_KBN>0 AND URI_KBN_ZEN>0 
        THEN KYAKUSU_ZEN
        ELSE 0 END)       as KYAKUSU_ZEN_HOSEI

,      sum(case when OPEN_KBN = 1 AND OPEN_KBN_ZEN = 1
                      AND URI_KBN>0 AND URI_KBN_ZEN>0 
                      AND URIAGE_TAKU>0 AND URIAGE_TAKU_ZEN>0 
        THEN KENSU_TAKU_ZEN
        ELSE 0 END)  as KYAKUSU_TAKU_ZEN_HOSEI
,      MAX(1) AS HOSEI_FLG
/*END*/
FROM (
    SELECT BM45.COMPANY_CD
    ,      BM45.EIGYO_DT
    ,      BM45.MISE_CD
    ,      coalesce(TOU.URIAGE, 0)  as URIAGE
    ,      coalesce(TOU.KYAKUSU, 0) as KYAKUSU 
    ,      coalesce(TOU.OTHER_KIN_1, 0) as URIAGE_TAKU 
    ,      coalesce(TOU.OTHER_KEN_1, 0) as KENSU_TAKU 
    ,      coalesce(ZEN.URIAGE, 0)  as URIAGE_ZEN 
    ,      coalesce(ZEN.KYAKUSU, 0) as KYAKUSU_ZEN 
    ,      coalesce(ZEN.OTHER_KIN_1, 0) as URIAGE_TAKU_ZEN
    ,      coalesce(ZEN.OTHER_KEN_1, 0) as KENSU_TAKU_ZEN
    ,      BM01.GYOTAI_KBN
    ,      BM01.SIBU_CD
    ,      BM01.BLOCK_CD
    ,      BM45.OPEN_KBN
    ,      BM45.OP_KBN_ZEN_DOGETU AS OPEN_KBN_ZEN
    ,      BM45.URI_KBN
    ,      BM45.ZEN_DOGETU_URI_KBN AS URI_KBN_ZEN
    ,      (CASE WHEN BN01.KBN17='00' THEN 0 ELSE 1 END) AS TAKU_GETUMATU_OPEN_KBN
    FROM BM01TENM BM01
/*IF taishoJoken.equals("SEGMENT") */
,    (SELECT GYOTAI_KBN FROM BM09GTSG  WHERE SEGMENT_TYPE = /*hyojiTaisho*/'M') BM09 
/*END*/
,    (SELECT COMPANY_CD , SIBU_CD FROM BM10GSIB 
      WHERE COMPANY_CD = /*companyCd*/'00'
/*IF taishoJoken.equals("JIGYOU")*/
      AND JIGYOU_CD = /*hyojiTaisho*/'00'
/*END*/
/*IF taishoJoken.equals("SLAREA")*/
      AND SLAREA_CD = /*hyojiTaisho*/'00'
/*END*/
/*IF taishoJoken.equals("SIBU")*/
      AND SIBU_CD = /*hyojiTaisho*/'00'
/*END*/
     ) BM10 
    ,    BN01DTEN BN01
    ,    BM45ZDAY BM45
    LEFT JOIN BT63SNIP TOU ON (TOU.COMPANY_CD = BM45.COMPANY_CD 
        AND   BM45.MISE_CD    = TOU.MISE_CD
        AND   BM45.EIGYO_DT   = TOU.EIGYO_DT
        ) 
    LEFT JOIN BT63SNIP ZEN ON (ZEN.COMPANY_CD = BM45.COMPANY_CD 
      /*IF zenDataShubetu.equals("DOJITU") */
        AND   BM45.ZEN_DOJITU_MISE = ZEN.MISE_CD
        AND   BM45.ZEN_DOJITU_DT   = ZEN.EIGYO_DT
      /*END*/
      /*IF zenDataShubetu.equals("DOYO") */
        AND   BM45.ZEN_DOYO_MISE = ZEN.MISE_CD
        AND   BM45.ZEN_DOYO_DT   = ZEN.EIGYO_DT
      /*END*/
      /*IF zenDataShubetu.equals("DOGETU") */
        AND   BM45.ZEN_DOGETU_MISE = ZEN.MISE_CD
        AND   BM45.ZEN_DOGETU_DT   = ZEN.EIGYO_DT
      /*END*/
      /*IF zenDataShubetu.equals("HOSEI") */
        AND   BM45.ZEN_DOGETU_MISE = ZEN.MISE_CD
        AND   BM45.ZEN_DOGETU_DT   = ZEN.EIGYO_DT
      /*END*/
        ) 

	WHERE BM01.COMPANY_CD = /*companyCd*/'00'
	AND   BN01.EIGYO_DT  >= /*kikanStart*/'20060601'
	AND   BN01.EIGYO_DT  <= /*kikanEnd*/'20060631'
	AND   BN01.KBN17 <> '  '
	AND   BN01.KBN17 <> '00' 
	AND   BM45.OLDM_FLG = '0'
	/*IF tenpoShubetu.equals("ALL") */
	AND   (BM45.OPEN_KBN  = 1 OR BM45.OP_KBN_ZEN_DOGETU = 1)
	-- ELSE
	AND   BM45.OPEN_KBN  = 1
	/*END*/
	
	/*IF tenpoShubetu.equals("1") */
	AND   BN01.KBN1  = '1'
	/*END*/
	/*IF tenpoShubetu.equals("2") */
	AND   (BN01.KBN1 = '1' OR BN01.KBN1 = '2')
	/*END*/
	/*IF tenpoShubetu.equals("3") */
	AND   BN01.KBN1  = '3'
	/*END*/
	/*IF taishoJoken.equals("SEGMENT") */
	AND   BM01.GYOTAI_KBN = BM09.GYOTAI_KBN
	/*END*/
	/*IF taishoJoken.equals("SIBU") && blockCd != null */
	AND   BM01.BLOCK_CD = /*blockCd*/'00'
	/*END*/
	/*IF taishoJoken.equals("MISE") */
	AND   BM01.MISE_CD = /*hyojiTaisho*/'01002'
	/*END*/
	
	/*IF limitFlg==true */
	AND   BM01.SIBU_CD IN ( 
	         SELECT   BM51.SIBU_CD
	         FROM     BM51SVSB BM51
	         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
	         AND    BM51.SV_CD = /*userId*/'9999000a'
	         GROUP BY BM51.SIBU_CD
	      ) 
	/*END*/
	AND   BM01.COMPANY_CD = BM10.COMPANY_CD
	AND   BM01.SIBU_CD = BM10.SIBU_CD
	AND   BM01.COMPANY_CD = BM45.COMPANY_CD
	AND   BM01.MISE_CD    = BM45.MISE_CD
	AND   BM45.COMPANY_CD = BN01.COMPANY_CD
	AND   BM45.MISE_CD    = BN01.MISE_CD
	AND   BM45.EIGYO_DT   = BN01.EIGYO_DT
) SUB
GROUP BY
    EIGYO_DT
ORDER BY
    EIGYO_DT
