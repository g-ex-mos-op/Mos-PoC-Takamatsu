SELECT
    COUNT(DISTINCT bm01tenm.mise_cd) AS TOTAL_TENPO_COUNT
FROM
bm01tenm
/*IF shukeiKbn.equals("AREA_DAI_CD")*/
    JOIN bm10gsib ON (bm01tenm.company_cd = bm10gsib.company_cd AND bm01tenm.area_dai = bm10gsib.sibu_cd AND bm10gsib.area_dai_flg = '1')
-- ELSE
    JOIN bm10gsib ON (bm01tenm.company_cd = bm10gsib.company_cd AND bm01tenm.sibu_cd = bm10gsib.sibu_cd)
/*END*/
JOIN bt73taku ON (bm01tenm.company_cd = bt73taku.company_cd AND bm01tenm.mise_cd = bt73taku.mise_cd)
JOIN bn01dten ON (bt73taku.company_cd = bn01dten.company_cd AND bt73taku.mise_cd = bn01dten.mise_cd AND bt73taku.eigyo_dt = bn01dten.eigyo_ym)
WHERE
    bm01tenm.company_cd = /*companyCd*/'00'
/*IF taishoKikan.equals("KIBETU")*/
    AND (bt73taku.eigyo_dt BETWEEN /*kikanFrom*/ AND /*kikanTo*/)
-- ELSE
    AND bt73taku.eigyo_dt =/*kikanFrom*/'200609'
/*END*/
    AND bt73taku.takuhai_kbn <> '00'
    AND bt73taku.open_kbn = 1 
    AND bt73taku.oldm_flg='0'
/*IF !taishoTenpo.equals("ALL")*/
    /*IF taishoTenpo.equals("FC")*/
        AND bm01tenm.mise_kbn LIKE '_1_'
    -- ELSE
        AND bm01tenm.mise_kbn LIKE '_2_'
    /*END*/
/*END*/
/*IF limitFlg == true*/
    AND bm01tenm.sibu_cd IN (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
    )
/*END*/
/*IF !tenpoShu.equals("ALL")*/
    /*IF tenpoShu.equals("2")*/
        AND (bn01dten.kbn1 = '1' OR bn01dten.kbn1 = '2')
    -- ELSE
        AND bn01dten.kbn1 = /*tenpoShu*/'1'
    /*END*/
/*END*/
GROUP BY bm01tenm.company_cd