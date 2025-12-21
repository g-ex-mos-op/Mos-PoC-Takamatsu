SELECT
    COUNT(DISTINCT bm01tenm.mise_cd) AS TOTAL_TENPO_COUNT
FROM
    bm01tenm JOIN bt73taku ON (bm01tenm.company_cd = bt73taku.company_cd AND bm01tenm.mise_cd = bt73taku.mise_cd)
WHERE
    bm01tenm.company_cd = /*companyCd*/'00'
    AND bm01tenm.oner_cd = /*onerCd*/'36066'
    AND bt73taku.eigyo_dt = /*kikanFrom*/'200607'
    AND bt73taku.takuhai_kbn <> '00'
    AND bt73taku.open_kbn = 1
    AND bt73taku.oldm_flg='0'