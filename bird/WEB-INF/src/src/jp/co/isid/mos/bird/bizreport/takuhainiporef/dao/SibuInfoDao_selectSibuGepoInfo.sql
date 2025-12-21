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
    RTRIM(takuinfo.taku_detail_cd) AS TAKU_DETAIL_CD,
   	SUM(TAISHO_TENPO_CNT) AS TAISHO_TENPO_CNT,   	
   	SUM(takuinfo.tenpo_count) AS TENPO_COUNT,   	
    SUM(takuinfo.uriage) AS URIAGE,
    SUM(takuinfo.uriage_zen) AS URIAGE_ZEN,
    SUM(takuinfo.uriage_taku) AS URIAGE_TAKU,
    SUM(takuinfo.uriage_taku_zen) AS URIAGE_TAKU_ZEN,
    SUM(takuinfo.kyakusu) AS KYAKUSU,
    SUM(takuinfo.kyakusu_zen) AS KYAKUSU_ZEN, 
    SUM(takuinfo.kyakusu_taku) AS KYAKUSU_TAKU,     
    SUM(takuinfo.kyakusu_taku_zen) AS KYAKUSU_TAKU_ZEN
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
    COUNT(DISTINCT (CASE WHEN BT73.OPEN_KBN=1 THEN bm01tenm.mise_cd ELSE NULL END)) AS TAISHO_TENPO_CNT,
    COUNT(DISTINCT (CASE WHEN BT73.takuhai_kbn = '00' OR BT73.uriage_DOGETU_taku > 0 THEN bm01tenm.mise_cd ELSE NULL END)) AS TENPO_COUNT,     
/*IF dispType.equals("DETAIL") */
    BT73.takuhai_kbn AS TAKU_DETAIL_CD,
--ELSE
    BM47.TAKU_CD AS TAKU_DETAIL_CD,
/*END*/
/*IF dataShu.equals("DOGETU")*/
    SUM(BT73.eigyo_days_dogetu_taku) AS EIGYO_DAYS_TAKU,
    SUM(BT73.uriage_dogetu) AS URIAGE, 
    SUM(BT73.uriage_dogetu_taku) AS URIAGE_TAKU, 
    SUM(BT73.kyakusu_dogetu) AS KYAKUSU, 
    SUM(BT73.kyakusu_dogetu_taku) AS KYAKUSU_TAKU, 
    SUM(BT73.uriage_zen_dogetu) AS URIAGE_ZEN, 
    SUM(BT73.uriage_zen_dogetu_taku) AS URIAGE_TAKU_ZEN, 
    SUM(BT73.kyakusu_zen_dogetu) AS KYAKUSU_ZEN, 
    SUM(BT73.kyakusu_zen_dogetu_taku) AS KYAKUSU_TAKU_ZEN
/*END*/
/*IF dataShu.equals("DOJITU")*/
    SUM(BT73.eigyo_days_dojitu_taku) AS EIGYO_DAYS_TAKU,
    SUM(BT73.uriage_dojitu) AS URIAGE, 
    SUM(BT73.uriage_dojitu_taku) AS URIAGE_TAKU, 
    SUM(BT73.kyakusu_dojitu) AS KYAKUSU, 
    SUM(BT73.kyakusu_dojitu_taku) AS KYAKUSU_TAKU, 
    SUM(BT73.uriage_zen_dojitu) AS URIAGE_ZEN, 
    SUM(BT73.uriage_zen_dojitu_taku) AS URIAGE_TAKU_ZEN, 
    SUM(BT73.kyakusu_zen_dojitu) AS KYAKUSU_ZEN,
    SUM(BT73.kyakusu_zen_dojitu_taku) AS KYAKUSU_TAKU_ZEN
/*END*/
/*IF dataShu.equals("DOYO")*/
    SUM(BT73.eigyo_days_doyo_taku) AS EIGYO_DAYS_TAKU,
    SUM(BT73.uriage_doyo) AS URIAGE, 
    SUM(BT73.uriage_doyo_taku) AS URIAGE_TAKU, 
    SUM(BT73.kyakusu_doyo) AS KYAKUSU, 
    SUM(BT73.kyakusu_doyo_taku) AS KYAKUSU_TAKU, 
    SUM(BT73.uriage_zen_doyo) AS URIAGE_ZEN, 
    SUM(BT73.uriage_zen_doyo_taku) AS URIAGE_TAKU_ZEN, 
    SUM(BT73.kyakusu_zen_doyo) AS KYAKUSU_ZEN,
    SUM(BT73.kyakusu_zen_doyo_taku) AS KYAKUSU_TAKU_ZEN
/*END*/
/*IF dataShu.equals("HOSEI")*/
    SUM(BT73.eigyo_days_dogetu_taku) AS EIGYO_DAYS_TAKU
,   SUM(BT73.uriage_dogetu) AS URIAGE
,   SUM(BT73.uriage_dogetu_taku) AS URIAGE_TAKU
,   SUM(BT73.kyakusu_dogetu) AS KYAKUSU
,   SUM(BT73.kyakusu_dogetu_taku) AS KYAKUSU_TAKU
,   SUM(BT73.uriage_zen_dogetu) AS URIAGE_ZEN
,   SUM(BT73.uriage_zen_dogetu_taku) AS URIAGE_TAKU_ZEN
,   SUM(BT73.kyakusu_zen_dogetu) AS KYAKUSU_ZEN
,   SUM(BT73.kyakusu_zen_dogetu_taku) AS KYAKUSU_TAKU_ZEN
,   SUM(BT73.EIGYO_DAYS_HOSEI_TAKU) AS EIGYO_DAYS_TAKU_NET  
,   COUNT(DISTINCT (CASE WHEN  BT73.URIAGE_HOSEI_TAKU > 0
                AND BT73.URIAGE_ZEN_HOSEI_TAKU > 0 
          THEN BT73.MISE_CD ELSE NULL END)) AS TENPO_COUNT_NET     
,   SUM(BT73.URIAGE_HOSEI) AS URIAGE_NET
,   SUM(BT73.URIAGE_HOSEI_TAKU) AS URIAGE_TAKU_NET
,   SUM(BT73.KYAKUSU_HOSEI) AS KYAKUSU_NET
,   SUM(BT73.KYAKUSU_HOSEI_TAKU) AS KYAKUSU_TAKU_NET
,   SUM(BT73.URIAGE_ZEN_HOSEI) AS URIAGE_ZEN_NET
,   SUM(BT73.URIAGE_ZEN_HOSEI_TAKU) AS URIAGE_TAKU_ZEN_NET
,   SUM(BT73.KYAKUSU_ZEN_HOSEI) AS KYAKUSU_ZEN_NET
,   SUM(BT73.KYAKUSU_ZEN_HOSEI_TAKU) AS KYAKUSU_TAKU_ZEN_NET/*END*/
FROM
   	bm01tenm
   	JOIN bt73taku BT73 ON (bm01tenm.company_cd = BT73.company_cd AND bm01tenm.mise_cd = BT73.mise_cd)
   	JOIN (
   	    SELECT bn01dten.company_cd, bn01dten.mise_cd, bn01dten.eigyo_ym
   	    FROM bn01dten
        /*IF !tenpoShu.equals("ALL")*/
            /*IF tenpoShu.equals("2")*/
		        WHERE (bn01dten.kbn1 = '1' OR bn01dten.kbn1 = '2')
            -- ELSE
                WHERE bn01dten.kbn1 = /*tenpoShu*/
            /*END*/
        /*END*/
        GROUP BY bn01dten.company_cd, bn01dten.mise_cd, bn01dten.eigyo_ym
   	) bn01dten ON (BT73.company_cd = bn01dten.company_cd AND BT73.mise_cd = bn01dten.mise_cd AND BT73.eigyo_dt = bn01dten.eigyo_ym)
,   bm47tdms BM47
WHERE
    bm01tenm.company_cd = /*companyCd*/'00'
/*IF taishoKikan.equals("KIBETU")*/
    AND (BT73.eigyo_dt BETWEEN /*kikanFrom*/ AND /*kikanTo*/) 
-- ELSE
    AND BT73.eigyo_dt = /*kikanFrom*/
/*END*/
/*IF tenpoShu.equals("ALL")*/
    AND (BT73.open_kbn = 1 OR BT73.open_kbn_zen_dogetu = 1) 
-- ELSE
    AND BT73.open_kbn = 1
/*END*/
    AND BT73.oldm_flg='0'
/*IF !taishoTenpo.equals("ALL")*/
    /*IF taishoTenpo.equals("FC")*/
        AND bm01tenm.mise_kbn LIKE '_1_'
    -- ELSE
        AND bm01tenm.mise_kbn LIKE '_2_'
    /*END*/
/*END*/
/*IF limitFlg == true*/
    AND bm01tenm.sibu_cd IN (
         SELECT BM51.SIBU_CD
         FROM   BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
    )
/*END*/
/*IF !sibuCdList.isEmpty()*/
    /*IF shukeiKbn.equals("AREA_DAI_CD")*/
        AND bm01tenm.area_dai IN /*sibuCdList*/('020')
    -- ELSE
        AND bm01tenm.sibu_cd IN /*sibuCdList*/('020')
    /*END*/
/*END*/
    AND BT73.takuhai_kbn = BM47.TAKU_DETAIL_CD
    GROUP BY
/*IF shukeiKbn.equals("AREA_DAI_CD")*/
        bm01tenm.company_cd, bm01tenm.area_dai
-- ELSE
        bm01tenm.company_cd, bm01tenm.sibu_cd
/*END*/
/*IF dispType.equals("DETAIL") */
,     BT73.takuhai_kbn
-- ELSE
,     BM47.TAKU_CD
/*END*/
) takuinfo JOIN bm10gsib ON (takuinfo.company_cd = bm10gsib.company_cd AND takuinfo.sibu_cd = bm10gsib.sibu_cd
    /*IF shukeiKbn.equals("AREA_DAI_CD")*/
        AND bm10gsib.area_dai_flg = '1'
    /*END*/
)
GROUP BY ROLLUP(
takuinfo.taku_detail_cd,
/*IF shukeiKbn.equals("AREA_DAI_CD") == false*/
/*IF companyCd.equals("00")*/
(bm10gsib.company_cd, bm10gsib.honbu_cd, bm10gsib.honbu_name),
(bm10gsib.jigyou_cd, bm10gsib.jigyou_name),
(bm10gsib.slarea_cd, bm10gsib.slarea_name),
/*END*/
/*END*/
(bm10gsib.sibu_cd, bm10gsib.sibu_name))
ORDER BY
/*IF shukeiKbn.equals("AREA_DAI_CD") == false*/
/*IF companyCd.equals("00")*/
bm10gsib.company_cd, bm10gsib.honbu_cd, bm10gsib.jigyou_cd, bm10gsib.slarea_cd,
/*END*/
/*END*/
bm10gsib.sibu_cd, takuinfo.taku_detail_cd