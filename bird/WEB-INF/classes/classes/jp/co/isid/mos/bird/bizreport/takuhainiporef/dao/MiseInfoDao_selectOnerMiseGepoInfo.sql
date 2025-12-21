SELECT
    bm01tenm.mise_cd,
    bm01tenm.mise_name_kj AS MISE_NAME,
    bt73taku.takuhai_kbn AS TAKU_DETAIL_CD,
/*IF dataShu.equals("DOGETU") || dataShu.equals("HOSEI")*/
    SUM(bt73taku.eigyo_days_dogetu_taku) AS EIGYO_DAYS_TAKU,
    SUM(bt73taku.uriage_dogetu) AS URIAGE, 
    SUM(bt73taku.uriage_dogetu_taku) AS URIAGE_TAKU, 
    SUM(bt73taku.kyakusu_dogetu) AS KYAKUSU, 
    SUM(bt73taku.kyakusu_dogetu_taku) AS KYAKUSU_TAKU, 
    SUM(bt73taku.uriage_zen_dogetu) AS URIAGE_ZEN, 
    SUM(bt73taku.uriage_zen_dogetu_taku) AS URIAGE_TAKU_ZEN, 
    SUM(bt73taku.kyakusu_zen_dogetu) AS KYAKUSU_ZEN, 
    SUM(bt73taku.kyakusu_zen_dogetu_taku) AS KYAKUSU_TAKU_ZEN
/*END*/
/*IF dataShu.equals("DOJITU")*/
    SUM(bt73taku.eigyo_days_dojitu_taku) AS EIGYO_DAYS_TAKU,
    SUM(bt73taku.uriage_dojitu) AS URIAGE, 
    SUM(bt73taku.uriage_dojitu_taku) AS URIAGE_TAKU, 
    SUM(bt73taku.kyakusu_dojitu) AS KYAKUSU, 
    SUM(bt73taku.kyakusu_dojitu_taku) AS KYAKUSU_TAKU, 
    SUM(bt73taku.uriage_zen_dojitu) AS URIAGE_ZEN, 
    SUM(bt73taku.uriage_zen_dojitu_taku) AS URIAGE_TAKU_ZEN, 
    SUM(bt73taku.kyakusu_zen_dojitu) AS KYAKUSU_ZEN,
    SUM(bt73taku.kyakusu_zen_dojitu_taku) AS KYAKUSU_TAKU_ZEN
/*END*/
/*IF dataShu.equals("DOYO")*/
    SUM(bt73taku.eigyo_days_doyo_taku) AS EIGYO_DAYS_TAKU,
    SUM(bt73taku.uriage_doyo) AS URIAGE, 
    SUM(bt73taku.uriage_doyo_taku) AS URIAGE_TAKU, 
    SUM(bt73taku.kyakusu_doyo) AS KYAKUSU, 
    SUM(bt73taku.kyakusu_doyo_taku) AS KYAKUSU_TAKU, 
    SUM(bt73taku.uriage_zen_doyo) AS URIAGE_ZEN, 
    SUM(bt73taku.uriage_zen_doyo_taku) AS URIAGE_TAKU_ZEN, 
    SUM(bt73taku.kyakusu_zen_doyo) AS KYAKUSU_ZEN,
    SUM(bt73taku.kyakusu_zen_doyo_taku) AS KYAKUSU_TAKU_ZEN
/*END*/
FROM
    bm01tenm JOIN bt73taku ON (bm01tenm.company_cd = bt73taku.company_cd AND bm01tenm.mise_cd = bt73taku.mise_cd)
WHERE
    bm01tenm.company_cd = /*companyCd*/'00'
    AND bm01tenm.oner_cd = /*onerCd*/'36006'
    AND bt73taku.eigyo_dt = /*kikanFrom*/'200606'
    AND bt73taku.open_kbn = 1
    AND bt73taku.oldm_flg='0'
GROUP BY ROLLUP(bt73taku.takuhai_kbn, (bm01tenm.mise_cd, bm01tenm.mise_name_kj))
ORDER BY bm01tenm.mise_cd, bt73taku.takuhai_kbn