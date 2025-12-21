SELECT
/*IF shukeiKbn.equals("AREA_DAI_CD") == false*/
/*IF companyCd.equals("00")*/
    bm10gsib.honbu_cd,
    RTRIM(bm10gsib.honbu_name) AS HONBU_NAME,
    bm10gsib.jigyou_cd,
    RTRIM(bm10gsib.jigyou_name) AS JIGYOU_NAME,
    bm10gsib.slarea_cd,
    RTRIM(bm10gsib.slarea_name) AS SLAREA_NAME,
/*END*/
/*END*/
    RTRIM(bm10gsib.sibu_cd) AS SIBU_CD,
    RTRIM(bm10gsib.sibu_name) AS SIBU_NAME,  
    takunipo.taku_detail_cd,
    SUM(TAISHO_TENPO_CNT) AS TAISHO_TENPO_CNT,  	
    SUM(takunipo.tenpo_count) AS TENPO_COUNT,  	
    SUM(takunipo.uriage) AS URIAGE,
    SUM(takunipo.uriage_zen) AS URIAGE_ZEN,
    SUM(takunipo.uriage_taku) AS URIAGE_TAKU,
    SUM(takunipo.uriage_taku_zen) AS URIAGE_TAKU_ZEN,
    SUM(takunipo.kyakusu) AS KYAKUSU,
    SUM(takunipo.kyakusu_zen) AS KYAKUSU_ZEN, 
    SUM(takunipo.kyakusu_taku) AS KYAKUSU_TAKU,     
    SUM(takunipo.kyakusu_taku_zen) AS KYAKUSU_TAKU_ZEN 
/*IF dataShu.equals("HOSEI")*/
,  	SUM(TENPO_COUNT_NET) AS TENPO_COUNT_NET   	
,   SUM(URIAGE_NET) AS URIAGE_NET
,   SUM(URIAGE_TAKU_NET) AS URIAGE_TAKU_NET 
,   SUM(KYAKUSU_NET) AS KYAKUSU_NET
,   SUM(KYAKUSU_TAKU_NET) AS KYAKUSU_TAKU_NET
,   SUM(URIAGE_ZEN_NET) AS URIAGE_ZEN_NET
,   SUM(URIAGE_TAKU_ZEN_NET) AS URIAGE_TAKU_ZEN_NET
,   SUM(KYAKUSU_ZEN_NET) AS KYAKUSU_ZEN_NET
,   SUM(KYAKUSU_TAKU_ZEN_NET) AS KYAKUSU_TAKU_ZEN_NET
/*END*/
FROM
(SELECT 												
    bm01tenm.company_cd,
/*IF shukeiKbn.equals("AREA_DAI_CD")*/
    bm01tenm.area_dai as SIBU_CD,
-- ELSE
    bm01tenm.sibu_cd,
/*END*/
/*IF dispType.equals("DETAIL") */
    bn01dten.kbn17 AS TAKU_DETAIL_CD,
--ELSE
    BM47.TAKU_CD AS TAKU_DETAIL_CD,
/*END*/
    COUNT(DISTINCT (CASE WHEN bm45zday.OPEN_KBN=1 THEN bm01tenm.mise_cd ELSE NULL END)) AS TAISHO_TENPO_CNT,
    COUNT(DISTINCT (CASE WHEN bn01dten.kbn17 = '00' OR uri.other_kin_1 > 0 THEN bm01tenm.mise_cd ELSE NULL END)) AS TENPO_COUNT,         
    SUM(uri.uriage) AS URIAGE,
    SUM(uri.kyakusu) AS KYAKUSU,
    SUM(uri.other_kin_1) AS URIAGE_TAKU,
    SUM(uri.other_ken_1) AS KYAKUSU_TAKU,												
    SUM(zenuri.uriage) AS URIAGE_ZEN,
    SUM(zenuri.kyakusu) AS KYAKUSU_ZEN,
    SUM(zenuri.other_kin_1) AS URIAGE_TAKU_ZEN,
    SUM(zenuri.other_ken_1) AS KYAKUSU_TAKU_ZEN							
/*IF dataShu.equals("HOSEI")*/
,   COUNT(DISTINCT (CASE WHEN (bn01dten.kbn17 = '00' OR uri.other_kin_1 > 0) 
                              AND bm45zday.URI_KBN = 1 
                              AND bm45zday.OP_KBN_ZEN_DOGETU = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1 
                              AND uri.other_kin_1 > 0 AND zenuri.other_kin_1 > 0
                         THEN bm01tenm.mise_cd ELSE NULL END)) AS TENPO_COUNT_NET     
,   SUM(CASE WHEN (bm45zday.OP_KBN_ZEN_DOGETU = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1 )
             THEN uri.URIAGE
             ELSE 0 END) AS URIAGE_NET
,   SUM(CASE WHEN (bm45zday.OP_KBN_ZEN_DOGETU = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1 )
               AND uri.other_kin_1 > 0 AND zenuri.other_kin_1 > 0
             THEN uri.other_kin_1
             ELSE 0 END) AS URIAGE_TAKU_NET
,   SUM(CASE WHEN (bm45zday.OP_KBN_ZEN_DOGETU = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1 )
             THEN uri.KYAKUSU
             ELSE 0 END) AS KYAKUSU_NET
,   SUM(CASE WHEN (bm45zday.OP_KBN_ZEN_DOGETU = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1 )
               AND uri.other_kin_1 > 0 AND zenuri.other_kin_1 > 0
             THEN uri.other_ken_1
             ELSE 0 END) AS KYAKUSU_TAKU_NET
,   SUM(CASE WHEN (bm45zday.URI_KBN = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1)
             THEN zenuri.URIAGE
             ELSE 0 END) AS URIAGE_ZEN_NET
,   SUM(CASE WHEN (bm45zday.URI_KBN = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1)
               AND uri.other_kin_1 > 0 AND zenuri.other_kin_1 > 0
             THEN zenuri.other_kin_1
             ELSE 0 END) AS URIAGE_TAKU_ZEN_NET
,   SUM(CASE WHEN (bm45zday.URI_KBN = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1)
             THEN zenuri.KYAKUSU
             ELSE 0 END) AS KYAKUSU_ZEN_NET
,   SUM(CASE WHEN (bm45zday.URI_KBN = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1)
               AND uri.other_kin_1 > 0 AND zenuri.other_kin_1 > 0
             THEN zenuri.other_ken_1
             ELSE 0 END) AS KYAKUSU_TAKU_ZEN_NET
/*END*/
FROM
bm01tenm
JOIN bm45zday ON (bm01tenm.company_cd = bm45zday.company_cd AND bm01tenm.mise_cd = bm45zday.mise_cd)
left JOIN bt63snip AS uri ON (bm45zday.company_cd = uri.company_cd AND bm45zday.mise_cd = uri.mise_cd AND bm45zday.eigyo_dt = uri.eigyo_dt)
LEFT OUTER JOIN bt63snip AS zenuri ON
/*IF dataShu.equals("DOYO")*/
    (bm45zday.company_cd = zenuri.company_cd AND bm45zday.zen_doyo_mise = zenuri.mise_cd AND bm45zday.zen_doyo_dt = zenuri.eigyo_dt)
-- ELSE
    (bm45zday.company_cd = zenuri.company_cd AND bm45zday.zen_dogetu_mise = zenuri.mise_cd AND bm45zday.zen_dogetu_dt = zenuri.eigyo_dt)
/*END*/
JOIN (
    SELECT bn01dten.company_cd, bn01dten.mise_cd, bn01dten.eigyo_dt, bn01dten.kbn17
    FROM bn01dten
    /*IF !tenpoShu.equals("ALL")*/
    	/*IF tenpoShu.equals("2")*/
            WHERE (bn01dten.kbn1 = '1' OR bn01dten.kbn1 = '2')
        -- ELSE
            WHERE bn01dten.kbn1 = /*tenpoShu*/
        /*END*/
    /*END*/
    GROUP BY bn01dten.company_cd, bn01dten.mise_cd, bn01dten.eigyo_dt, bn01dten.kbn17
) bn01dten ON (bm45zday.company_cd = bn01dten.company_cd AND bm45zday.mise_cd = bn01dten.mise_cd AND bm45zday.eigyo_dt = bn01dten.eigyo_dt)
,   bm47tdms BM47
WHERE
    bm01tenm.company_cd = /*companyCd*/'00'
/*IF taishoKikan.equals("DAY1")*/
    AND bm45zday.eigyo_dt = /*kikanFrom*/'20060601'
-- ELSE
    AND (bm45zday.eigyo_dt BETWEEN /*kikanFrom*/'20060601' AND /*kikanTo*/'20060630') 
/*END*/	
/*IF tenpoShu.equals("ALL")*/
    AND (bm45zday.open_kbn = 1 OR bm45zday.op_kbn_zen_dogetu = 1)
-- ELSE
    AND bm45zday.open_kbn = 1
/*END*/
    AND bm45zday.oldm_flg = '0'
/*IF !taishoTenpo.equals("ALL")*/
    /*IF taishoTenpo.equals("FC")*/
        AND bm01tenm.mise_kbn LIKE '_1_'
    -- ELSE
        AND bm01tenm.mise_kbn LIKE '_2_'
    /*END*/
/*END*/
/*IF !sibuCdList.isEmpty()*/
    /*IF shukeiKbn.equals("AREA_DAI_CD")*/
        AND bm01tenm.area_dai IN /*sibuCdList*/('020')
    -- ELSE
        AND bm01tenm.sibu_cd IN /*sibuCdList*/('020')
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
    AND bn01dten.kbn17 = BM47.TAKU_DETAIL_CD
GROUP BY
/*IF shukeiKbn.equals("AREA_DAI_CD")*/
    bm01tenm.company_cd, bm01tenm.area_dai
-- ELSE
    bm01tenm.company_cd, bm01tenm.sibu_cd
/*END*/
/*IF dispType.equals("DETAIL") */
, bn01dten.kbn17
--ELSE
, BM47.TAKU_CD
/*END*/
) takunipo JOIN bm10gsib ON (takunipo.company_cd = bm10gsib.company_cd AND takunipo.sibu_cd = bm10gsib.sibu_cd
    /*IF shukeiKbn.equals("AREA_DAI_CD")*/
        AND bm10gsib.area_dai_flg = '1'
    /*END*/
)
GROUP BY ROLLUP (
takunipo.taku_detail_cd,
/*IF shukeiKbn.equals("AREA_DAI_CD") == false*/
/*IF companyCd.equals("00")*/
(bm10gsib.honbu_cd, bm10gsib.honbu_name),
(bm10gsib.jigyou_cd, bm10gsib.jigyou_name),
(bm10gsib.slarea_cd, bm10gsib.slarea_name),
/*END*/
/*END*/
(bm10gsib.sibu_cd, bm10gsib.sibu_name))
ORDER BY
/*IF shukeiKbn.equals("AREA_DAI_CD") == false*/
/*IF companyCd.equals("00")*/
bm10gsib.honbu_cd, bm10gsib.jigyou_cd, bm10gsib.slarea_cd,
/*END*/
/*END*/
bm10gsib.sibu_cd, takunipo.taku_detail_cd