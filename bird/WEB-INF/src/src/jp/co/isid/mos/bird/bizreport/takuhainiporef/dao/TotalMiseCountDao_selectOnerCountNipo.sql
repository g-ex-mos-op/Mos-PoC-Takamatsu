SELECT
    COUNT(DISTINCT bm01tenm.mise_cd) AS TOTAL_TENPO_COUNT
FROM
    bm01tenm
    JOIN bm45zday ON (bm01tenm.company_cd = bm45zday.company_cd AND bm01tenm.mise_cd = bm45zday.mise_cd)
    JOIN bt63snip ON (bm45zday.company_cd = bt63snip.company_cd AND bm45zday.mise_cd = bt63snip.mise_cd AND bm45zday.eigyo_dt = bt63snip.eigyo_dt)
    JOIN bn01dten ON (bm45zday.company_cd = bn01dten.company_cd AND bm45zday.mise_cd = bn01dten.mise_cd AND bm45zday.eigyo_dt = bn01dten.eigyo_dt)
WHERE
    bm01tenm.company_cd = /*companyCd*/'00'
    AND bm01tenm.oner_cd = /*onerCd*/'36066'
/*IF taishoKikan.equals("DAY1")*/
    AND bm45zday.eigyo_dt = /*kikanFrom*/'20060601'
-- ELSE
    AND (bm45zday.eigyo_dt BETWEEN /*kikanFrom*/'20060601' AND /*kikanTo*/'20060630') 
/*END*/
    AND bm45zday.open_kbn = 1
    AND bm45zday.oldm_flg = '0'
    AND bn01dten.kbn17 <> '00'