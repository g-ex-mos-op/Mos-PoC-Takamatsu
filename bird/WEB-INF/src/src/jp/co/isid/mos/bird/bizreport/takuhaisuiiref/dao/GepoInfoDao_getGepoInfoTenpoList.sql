SELECT 
    BT73.EIGYO_DT as EIGYO_DT 
   ,COUNT(DISTINCT(CASE WHEN BT73.OPEN_KBN = 1 THEN BT73.MISE_CD ELSE null END)) as TENPO_CNT 
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
    --ELSE
       ,COUNT(DISTINCT(case when BT73.URIAGE_/*$zenDataShubetu*/''_TAKU>0 then BT73.MISE_CD else null end)) as TAKU_TAISHO_TENPO_CNT 
       ,sum(BT73.URIAGE_/*$zenDataShubetu*/) as URIAGE 
       ,sum(BT73.URIAGE_/*$zenDataShubetu*/''_TAKU) as URIAGE_TAKU 
       ,sum(BT73.KYAKUSU_/*$zenDataShubetu*/) as KYAKUSU 
       ,sum(BT73.KYAKUSU_/*$zenDataShubetu*/''_TAKU) as KYAKUSU_TAKU 
       
       ,sum(BT73.URIAGE_ZEN_/*$zenDataShubetu*/)       as URIAGE_ZEN 
       ,sum(BT73.URIAGE_ZEN_/*$zenDataShubetu*/''_TAKU)  as URIAGE_TAKU_ZEN 
       ,sum(BT73.KYAKUSU_ZEN_/*$zenDataShubetu*/)      as KYAKUSU_ZEN 
       ,sum(BT73.KYAKUSU_ZEN_/*$zenDataShubetu*/''_TAKU) as KYAKUSU_TAKU_ZEN 
    /*END*/
FROM 
    BT73TAKU BT73 
   ,(SELECT * FROM BM01TENM 
         WHERE COMPANY_CD = /*companyCd*/'00'
         AND    MISE_CD IN (
		    SELECT MISE_CD FROM BM07UTEN 
		    WHERE USER_ID = /*userId*/'99990003'
		    AND   COMPANY_CD = /*companyCd*/'00')) BM01 
WHERE BT73.COMPANY_CD = /*companyCd*/'00' 
  AND BT73.EIGYO_DT  BETWEEN /*kikanStart*/'200506' AND /*kikanEnd*/'200605' 
  AND BT73.OLDM_FLG   = '0' 
  AND BT73.TAKUHAI_KBN <> '  ' 
  AND BT73.TAKUHAI_KBN <> '00' 
  AND BT73.OPEN_KBN  = 1 
  AND BT73.COMPANY_CD = BM01.COMPANY_CD 
  AND BT73.MISE_CD    = BM01.MISE_CD 
GROUP BY 
    BT73.EIGYO_DT 
ORDER BY 
    BT73.EIGYO_DT 
