SELECT
    COUNT(DISTINCT bm01tenm.mise_cd) AS TOTAL_TENPO_COUNT
FROM
    bm01tenm
/*IF shukeiKbn.equals("AREA_DAI_CD")*/
    JOIN bm10gsib ON (bm01tenm.company_cd = bm10gsib.company_cd AND bm01tenm.area_dai = bm10gsib.sibu_cd AND bm10gsib.area_dai_flg = '1')
-- ELSE
    JOIN bm10gsib ON (bm01tenm.company_cd = bm10gsib.company_cd AND bm01tenm.sibu_cd = bm10gsib.sibu_cd)
/*END*/
    JOIN bm45zday ON (bm01tenm.company_cd = bm45zday.company_cd AND bm01tenm.mise_cd = bm45zday.mise_cd)

/*IF dataShu.equals("HOSEI")*/
    JOIN bt63snip ON (bm45zday.company_cd = bt63snip.company_cd AND bm45zday.mise_cd = bt63snip.mise_cd AND bm45zday.eigyo_dt = bt63snip.eigyo_dt)
/*END*/
/*IF dataShu.equals("DOGETU")*/
    /*IF tenpoShu.equals("ALL")*/
        JOIN bt63snip ON (bm45zday.company_cd = bt63snip.company_cd AND ((bm45zday.mise_cd = bt63snip.mise_cd AND bm45zday.eigyo_dt = bt63snip.eigyo_dt) or (bm45zday.zen_dogetu_mise = bt63snip.mise_cd AND bm45zday.zen_dogetu_dt = bt63snip.eigyo_dt)))
    -- ELSE
        JOIN bt63snip ON (bm45zday.company_cd = bt63snip.company_cd AND bm45zday.mise_cd = bt63snip.mise_cd AND bm45zday.eigyo_dt = bt63snip.eigyo_dt)
    /*END*/
/*END*/
/*IF dataShu.equals("DOYO")*/
    JOIN bt63snip ON (bm45zday.company_cd = bt63snip.company_cd AND bm45zday.mise_cd = bt63snip.mise_cd AND bm45zday.eigyo_dt = bt63snip.eigyo_dt)
/*END*/
    
    
JOIN (
    SELECT bn01dten.company_cd, bn01dten.mise_cd, bn01dten.eigyo_dt, bn01dten.kbn17
   	FROM bn01dten
    WHERE bn01dten.kbn17 <> '00'
   	/*IF !tenpoShu.equals("ALL")*/
        /*IF tenpoShu.equals("2")*/
            AND (bn01dten.kbn1 = '1' OR bn01dten.kbn1 = '2')
        -- ELSE
            AND bn01dten.kbn1 = /*tenpoShu*/
        /*END*/
    /*END*/
    GROUP BY bn01dten.company_cd, bn01dten.mise_cd, bn01dten.eigyo_dt, bn01dten.kbn17
) bn01dten ON (bm45zday.company_cd = bn01dten.company_cd AND bm45zday.mise_cd = bn01dten.mise_cd AND bm45zday.eigyo_dt = bn01dten.eigyo_dt)
WHERE
    bm01tenm.company_cd = /*companyCd*/'00'
/*IF taishoKikan.equals("DAY1")*/
    AND bm45zday.eigyo_dt = /*kikanFrom*/'20060601'
-- ELSE
    AND (bm45zday.eigyo_dt BETWEEN /*kikanFrom*/'20060601' AND /*kikanTo*/'20060630') 
/*END*/
    AND bm45zday.open_kbn = 1 
    AND bm45zday.oldm_flg = '0'
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
GROUP BY bm01tenm.company_cd