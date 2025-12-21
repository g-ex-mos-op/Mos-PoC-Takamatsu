SELECT
    bm01tenm.mise_cd,
    bm01tenm.mise_name_kj AS MISE_NAME,
    takunipo.taku_detail_cd,
    SUM(takunipo.eigyo_days_taku) AS EIGYO_DAYS_TAKU,
    SUM(takunipo.uriage) AS URIAGE, 
    SUM(takunipo.uriage_taku) AS URIAGE_TAKU, 
    SUM(takunipo.kyakusu) AS KYAKUSU, 
    SUM(takunipo.kyakusu_taku) AS KYAKUSU_TAKU, 
    SUM(takunipo.uriage_zen) AS URIAGE_ZEN, 
    SUM(takunipo.uriage_taku_zen) AS URIAGE_TAKU_ZEN, 
    SUM(takunipo.kyakusu_zen) AS KYAKUSU_ZEN, 
    SUM(takunipo.kyakusu_taku_zen) AS KYAKUSU_TAKU_ZEN
FROM bm01tenm JOIN
(SELECT
    bm45zday.company_cd,
    bm45zday.eigyo_dt,
    bm45zday.mise_cd,
    bn01dten.kbn17 AS TAKU_DETAIL_CD,
    (CASE WHEN uri.other_kin_1 > 0 THEN 1 ELSE 0 END) AS EIGYO_DAYS_TAKU,
    uri.uriage,												
    uri.kyakusu,
    uri.other_kin_1 AS URIAGE_TAKU,
    uri.other_ken_1 AS KYAKUSU_TAKU,
    zenuri.uriage AS URIAGE_ZEN,										
    zenuri.kyakusu AS KYAKUSU_ZEN,
    zenuri.other_kin_1 AS URIAGE_TAKU_ZEN,
    zenuri.other_ken_1 AS KYAKUSU_TAKU_ZEN											
FROM
    (SELECT
        bm45zday.company_cd,							
        bm45zday.eigyo_dt,
        bm45zday.mise_cd,
    /*IF dataShu.equals("DOJITU")*/
        bm45zday.zen_dojitu_mise AS ZEN_MISE_CD,
        bm45zday.zen_dojitu_dt AS ZEN_EIGYO_DT
    /*END*/
    /*IF dataShu.equals("DOYO")*/
        bm45zday.zen_doyo_mise AS ZEN_MISE_CD,
        bm45zday.zen_doyo_dt AS ZEN_EIGYO_DT
      /*END*/
    /*IF dataShu.equals("DOGETU")*/
        bm45zday.zen_dogetu_mise AS ZEN_MISE_CD,
        bm45zday.zen_dogetu_dt AS ZEN_EIGYO_DT
    /*END*/
    FROM bm45zday
    WHERE
        /*IF taishoKikan.equals("DAY1")*/
            bm45zday.eigyo_dt = /*kikanFrom*/
        -- ELSE
            (bm45zday.eigyo_dt between /*kikanFrom*/ AND /*kikanTo*/) 
        /*END*/
        AND bm45zday.open_kbn = 1
        AND bm45zday.oldm_flg = '0'
    ) bm45zday
    JOIN bt63snip AS uri ON (bm45zday.company_cd = uri.company_cd AND bm45zday.eigyo_dt = uri.eigyo_dt AND bm45zday.mise_cd = uri.mise_cd)
    LEFT OUTER JOIN bt63snip AS zenuri ON (bm45zday.company_cd = zenuri.company_cd AND bm45zday.zen_eigyo_dt = zenuri.eigyo_dt AND bm45zday.zen_mise_cd = zenuri.mise_cd)
    JOIN bn01dten ON (bm45zday.eigyo_dt = bn01dten.eigyo_dt AND bm45zday.company_cd = bn01dten.company_cd AND bm45zday.mise_cd = bn01dten.mise_cd)
) takunipo ON (bm01tenm.company_cd = takunipo.company_cd AND bm01tenm.mise_cd = takunipo.mise_cd)
WHERE
    bm01tenm.company_cd = /*companyCd*/'00'
    AND bm01tenm.oner_cd = /*onerCd*/'36006'
GROUP BY ROLLUP(takunipo.taku_detail_cd, (bm01tenm.mise_cd, bm01tenm.mise_name_kj))
ORDER BY bm01tenm.mise_cd, takunipo.taku_detail_cd