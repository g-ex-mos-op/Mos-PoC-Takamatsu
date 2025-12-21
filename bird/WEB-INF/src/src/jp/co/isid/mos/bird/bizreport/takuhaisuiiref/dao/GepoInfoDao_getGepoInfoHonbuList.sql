SELECT 
    BT73.EIGYO_DT as EIGYO_DT 
   ,COUNT(DISTINCT(CASE WHEN BT73.OPEN_KBN = 1 THEN BT73.MISE_CD ELSE null END)) as TENPO_CNT 
    /*IF zenDataShubetu.equals("DOGETU") */
       ,COUNT(DISTINCT(case when BT73.URIAGE_DOGETU_TAKU>0 then BT73.MISE_CD else null end)) as TAKU_TAISHO_TENPO_CNT 
       ,sum(BT73.URIAGE_DOGETU) as URIAGE 
       ,sum(BT73.URIAGE_DOGETU_TAKU) as URIAGE_TAKU 
       ,sum(BT73.KYAKUSU_DOGETU) as KYAKUSU 
       ,sum(BT73.KYAKUSU_DOGETU_TAKU) as KYAKUSU_TAKU 
       
       ,sum(BT73.URIAGE_ZEN_DOGETU)       as URIAGE_ZEN 
       ,sum(BT73.URIAGE_ZEN_DOGETU_TAKU)  as URIAGE_TAKU_ZEN 
       ,sum(BT73.KYAKUSU_ZEN_DOGETU)      as KYAKUSU_ZEN 
       ,sum(BT73.KYAKUSU_ZEN_DOGETU_TAKU) as KYAKUSU_TAKU_ZEN 
    /*END*/
    /*IF zenDataShubetu.equals("DOJITU") */
       ,COUNT(DISTINCT(case when BT73.URIAGE_DOJITU_TAKU>0 then BT73.MISE_CD else null end)) as TAKU_TAISHO_TENPO_CNT 
       ,sum(BT73.URIAGE_DOJITU) as URIAGE 
       ,sum(BT73.URIAGE_DOJITU_TAKU) as URIAGE_TAKU 
       ,sum(BT73.KYAKUSU_DOJITU) as KYAKUSU 
       ,sum(BT73.KYAKUSU_DOJITU_TAKU) as KYAKUSU_TAKU 
       
       ,sum(BT73.URIAGE_ZEN_DOJITU)       as URIAGE_ZEN 
       ,sum(BT73.URIAGE_ZEN_DOJITU_TAKU)  as URIAGE_TAKU_ZEN 
       ,sum(BT73.KYAKUSU_ZEN_DOJITU)      as KYAKUSU_ZEN 
       ,sum(BT73.KYAKUSU_ZEN_DOJITU_TAKU) as KYAKUSU_TAKU_ZEN 
    /*END*/
    /*IF zenDataShubetu.equals("DOYO") */
       ,COUNT(DISTINCT(case when BT73.URIAGE_DOYO_TAKU>0 then BT73.MISE_CD else null end)) as TAKU_TAISHO_TENPO_CNT 
       ,sum(BT73.URIAGE_DOYO) as URIAGE 
       ,sum(BT73.URIAGE_DOYO_TAKU) as URIAGE_TAKU 
       ,sum(BT73.KYAKUSU_DOYO) as KYAKUSU 
       ,sum(BT73.KYAKUSU_DOYO_TAKU) as KYAKUSU_TAKU 
       
       ,sum(BT73.URIAGE_ZEN_DOYO)       as URIAGE_ZEN 
       ,sum(BT73.URIAGE_ZEN_DOYO_TAKU)  as URIAGE_TAKU_ZEN 
       ,sum(BT73.KYAKUSU_ZEN_DOYO)      as KYAKUSU_ZEN 
       ,sum(BT73.KYAKUSU_ZEN_DOYO_TAKU) as KYAKUSU_TAKU_ZEN 
    /*END*/
    /*IF zenDataShubetu.equals("HOSEI") */
       ,COUNT(DISTINCT(case when BT73.URIAGE_DOGETU_TAKU>0 then BT73.MISE_CD else null end)) as TAKU_TAISHO_TENPO_CNT 
       ,sum(BT73.URIAGE_DOGETU) as URIAGE 
       ,sum(BT73.URIAGE_DOGETU_TAKU) as URIAGE_TAKU 
       ,sum(BT73.KYAKUSU_DOGETU) as KYAKUSU 
       ,sum(BT73.KYAKUSU_DOGETU_TAKU) as KYAKUSU_TAKU 

       ,sum(BT73.URIAGE_ZEN_DOGETU)       as URIAGE_ZEN 
       ,sum(BT73.URIAGE_ZEN_DOGETU_TAKU)  as URIAGE_TAKU_ZEN 
       ,sum(BT73.KYAKUSU_ZEN_DOGETU)      as KYAKUSU_ZEN 
       ,sum(BT73.KYAKUSU_ZEN_DOGETU_TAKU) as KYAKUSU_TAKU_ZEN 

       ,COUNT(DISTINCT(case when BT73.OPEN_KBN = 1 AND OPEN_KBN_ZEN = 1
                                    AND BT73.URIAGE_HOSEI>0
                                    AND BT73.URIAGE_ZEN_HOSEI>0 
                            then BT73.MISE_CD 
                            else null end)) as TENPO_CNT_HOSEI
       ,COUNT(DISTINCT(case when BT73.TAKU_GETUMATU_OPEN_KBN = 1 
                                    AND BT73.OPEN_KBN = 1 AND OPEN_KBN_ZEN = 1
                                    AND BT73.URIAGE_HOSEI_TAKU>0
                                    AND BT73.URIAGE_ZEN_HOSEI_TAKU>0 
                            then BT73.MISE_CD 
                            else null end)) as TAKU_TAISHO_TENPO_CNT_HOSEI
       ,sum(BT73.URIAGE_HOSEI)       as URIAGE_HOSEI 
       ,sum(BT73.URIAGE_HOSEI_TAKU)  as URIAGE_TAKU_HOSEI
       ,sum(BT73.KYAKUSU_HOSEI)      as KYAKUSU_HOSEI
       ,sum(BT73.KYAKUSU_HOSEI_TAKU) as KYAKUSU_TAKU_HOSEI
       ,sum(BT73.URIAGE_ZEN_HOSEI)       as URIAGE_ZEN_HOSEI 
       ,sum(BT73.URIAGE_ZEN_HOSEI_TAKU)  as URIAGE_TAKU_ZEN_HOSEI
       ,sum(BT73.KYAKUSU_ZEN_HOSEI)      as KYAKUSU_ZEN_HOSEI
       ,sum(BT73.KYAKUSU_ZEN_HOSEI_TAKU) as KYAKUSU_TAKU_ZEN_HOSEI
    /*END*/
FROM BT73TAKU BT73 
,    BM01TENM BM01 
,   ( 
     SELECT COMPANY_CD,MISE_CD,EIGYO_YM FROM BN01DTEN
     WHERE COMPANY_CD = /*companyCd*/'00' 
     AND EIGYO_YM >= /*kikanStart*/'200704' 
     AND EIGYO_YM <= /*kikanEnd*/'200803'
    /*IF tenpoShubetu.equals("1") */
     AND KBN1 = '1'
    /*END*/
    /*IF tenpoShubetu.equals("2") */
     AND (KBN1 = '1' OR KBN1 = '2') 
    /*END*/
    /*IF tenpoShubetu.equals("3") */
     AND KBN1 = '3'
    /*END*/
     GROUP BY COMPANY_CD,MISE_CD,EIGYO_YM
    ) as BN01
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
WHERE BM01.COMPANY_CD = /*companyCd*/'00' 
AND   BT73.EIGYO_DT  >= /*kikanStart*/'200506' 
AND   BT73.EIGYO_DT  <= /*kikanEnd*/'200605' 
AND   BT73.OLDM_FLG   = '0' 
AND   BT73.TAKUHAI_KBN <> '  ' 
AND   BT73.TAKUHAI_KBN <> '00' 
/*IF tenpoShubetu.equals("ALL") */
AND   (BT73.OPEN_KBN  = 1 OR BT73.OPEN_KBN_ZEN_DOGETU = 1)
-- ELSE
AND   BT73.OPEN_KBN  = 1
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
AND   BM10.SIBU_CD IN ( 
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
          ) 
/*END*/
AND   BM01.COMPANY_CD = BM10.COMPANY_CD
AND   BM01.SIBU_CD    = BM10.SIBU_CD
AND   BN01.COMPANY_CD = BM01.COMPANY_CD 
AND   BN01.MISE_CD    = BM01.MISE_CD 
AND   BT73.COMPANY_CD = BN01.COMPANY_CD 
AND   BT73.MISE_CD    = BN01.MISE_CD 
AND   BT73.EIGYO_DT   = BN01.EIGYO_YM 

GROUP BY 
    BT73.EIGYO_DT 
ORDER BY 
    BT73.EIGYO_DT 
