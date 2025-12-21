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
FROM 
    BT73TAKU BT73 
   ,(SELECT MISE_CD FROM BM01TENM 
         WHERE COMPANY_CD = /*companyCd*/'00'
  /*IF taishoJoken.equals("MISE") */
         AND   MISE_CD = /*hyojiTaisho*/'01002'
   --ELSE
         AND   ONER_CD = /*hyojiTaisho*/'36025'
  /*END*/
  ) BM01  
WHERE BT73.COMPANY_CD = /*companyCd*/'00' 
  AND BT73.EIGYO_DT  >= /*kikanStart*/'200506' 
  AND BT73.EIGYO_DT  <= /*kikanEnd*/'200605' 
  AND BT73.OLDM_FLG   = '0' 
  AND BT73.TAKUHAI_KBN <> '  ' 
  AND BT73.TAKUHAI_KBN <> '00' 
  AND BT73.OPEN_KBN  = 1 
  /*IF taishoJoken.equals("MISE") */
  AND BM01.MISE_CD = /*hyojiTaisho*/'01002'
  /*END*/
  AND BT73.MISE_CD    = BM01.MISE_CD 

GROUP BY 
    BT73.EIGYO_DT 
ORDER BY 
    BT73.EIGYO_DT 
