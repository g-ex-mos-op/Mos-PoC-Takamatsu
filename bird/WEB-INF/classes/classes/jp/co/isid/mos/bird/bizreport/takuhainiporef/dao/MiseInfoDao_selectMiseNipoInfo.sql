SELECT
    takuinfo.sibu_cd,
    takuinfo.sibu_name,
/*IF shukeiKbn.equals("AREA_DAI_CD") == false */
/*IF companyCd.equals("00")*/
    takuinfo.block_cd,
    bc23blck.block_name,
/*END*/
/*END*/
    takuinfo.mise_cd,
    takuinfo.mise_name,
    takuinfo.taku_detail_cd,
    SUM(takuinfo.eigyo_days_taku) AS EIGYO_DAYS_TAKU,
    SUM(takuinfo.uriage) AS URIAGE, 
    SUM(takuinfo.uriage_taku) AS URIAGE_TAKU, 
    SUM(takuinfo.kyakusu) AS KYAKUSU, 
    SUM(takuinfo.kyakusu_taku) AS KYAKUSU_TAKU, 
    SUM(takuinfo.uriage_zen) AS URIAGE_ZEN, 
    SUM(takuinfo.uriage_taku_zen) AS URIAGE_TAKU_ZEN, 
    SUM(takuinfo.kyakusu_zen) AS KYAKUSU_ZEN, 
    SUM(takuinfo.kyakusu_taku_zen) AS KYAKUSU_TAKU_ZEN
/*IF dataShu.equals("HOSEI")*/
,  	SUM(EIGYO_DAYS_TAKU_NET) AS EIGYO_DAYS_TAKU_NET   	
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
        bm10gsib.sibu_cd,
        bm10gsib.sibu_name,
        (CASE WHEN LENGTH(LTRIM(RTRIM(bm01tenm.block_cd))) = 0 THEN '001' ELSE bm01tenm.block_cd END) AS BLOCK_CD,
        bm01tenm.mise_cd,
        bm01tenm.mise_name_kj AS MISE_NAME,
        bn01dten.kbn17 AS TAKU_DETAIL_CD,
        (CASE WHEN uri.other_kin_1 > 0 THEN 1 ELSE 0 END) AS EIGYO_DAYS_TAKU,
        uri.uriage AS URIAGE,
        uri.kyakusu AS KYAKUSU,
        zenuri.uriage AS URIAGE_ZEN,
        zenuri.kyakusu AS KYAKUSU_ZEN,
        uri.other_kin_1 AS URIAGE_TAKU,
        uri.other_ken_1 AS KYAKUSU_TAKU,												
        zenuri.other_kin_1 AS URIAGE_TAKU_ZEN,
        zenuri.other_ken_1 AS KYAKUSU_TAKU_ZEN
/*IF dataShu.equals("HOSEI")*/
,   (CASE WHEN (bn01dten.kbn17 = '00' OR uri.other_kin_1 > 0)
                              AND bm45zday.URI_KBN = 1 
                              AND bm45zday.OP_KBN_ZEN_DOGETU = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1 
                              AND uri.other_kin_1 > 0 AND zenuri.other_kin_1 > 0
                         THEN bm45zday.OP_KBN_ZEN_DOGETU ELSE 0 END) AS EIGYO_DAYS_TAKU_NET  
,   (CASE WHEN (bn01dten.kbn17 = '00' OR uri.other_kin_1 > 0) 
                              AND bm45zday.URI_KBN = 1 
                              AND bm45zday.OP_KBN_ZEN_DOGETU = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1 
                         THEN 1 ELSE 0 END) AS TENPO_COUNT_NET     
,   (CASE WHEN (bm45zday.OP_KBN_ZEN_DOGETU = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1 )
             THEN uri.URIAGE
             ELSE 0 END) AS URIAGE_NET
,   (CASE WHEN (bm45zday.OP_KBN_ZEN_DOGETU = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1 )
               AND uri.other_kin_1 > 0 AND zenuri.other_kin_1 > 0
             THEN uri.other_kin_1
             ELSE 0 END) AS URIAGE_TAKU_NET
,   (CASE WHEN (bm45zday.OP_KBN_ZEN_DOGETU = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1 )
             THEN uri.KYAKUSU
             ELSE 0 END) AS KYAKUSU_NET
,   (CASE WHEN (bm45zday.OP_KBN_ZEN_DOGETU = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1 )
               AND uri.other_kin_1 > 0 AND zenuri.other_kin_1 > 0
             THEN uri.other_ken_1
             ELSE 0 END) AS KYAKUSU_TAKU_NET
,   (CASE WHEN (bm45zday.URI_KBN = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1)
             THEN zenuri.URIAGE
             ELSE 0 END) AS URIAGE_ZEN_NET
,   (CASE WHEN (bm45zday.URI_KBN = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1)
               AND uri.other_kin_1 > 0 AND zenuri.other_kin_1 > 0
             THEN zenuri.other_kin_1
             ELSE 0 END) AS URIAGE_TAKU_ZEN_NET
,   (CASE WHEN (bm45zday.URI_KBN = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1)
             THEN zenuri.KYAKUSU
             ELSE 0 END) AS KYAKUSU_ZEN_NET
,   (CASE WHEN (bm45zday.URI_KBN = 1 AND bm45zday.ZEN_DOGETU_URI_KBN = 1)
               AND uri.other_kin_1 > 0 AND zenuri.other_kin_1 > 0
             THEN zenuri.other_ken_1
             ELSE 0 END) AS KYAKUSU_TAKU_ZEN_NET
/*END*/
    FROM
    bm01tenm
    /*IF shukeiKbn.equals("AREA_DAI_CD")*/
        JOIN bm10gsib ON (bm01tenm.company_cd = bm10gsib.company_cd AND bm01tenm.area_dai = bm10gsib.sibu_cd AND bm10gsib.area_dai_flg = '1')
	-- ELSE
        JOIN bm10gsib ON (bm01tenm.company_cd = bm10gsib.company_cd AND bm01tenm.sibu_cd = bm10gsib.sibu_cd)
    /*END*/
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
    /*IF limitFlg == true*/
        AND bm01tenm.sibu_cd IN (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
    )
    /*END*/
    /*IF !takuKbnCdList.isEmpty()*/
        AND bn01dten.kbn17 IN /*takuKbnCdList*/('00')
    /*END*/	
    /*IF linkKbnCd.equals("SIBU")*/
        AND bm10gsib.sibu_cd = /*sibuCd*/
    /*END*/
    /*IF linkKbnCd.equals("SLAREA")*/
        AND bm10gsib.slarea_cd = /*sibuCd*/
    /*END*/
    /*IF linkKbnCd.equals("JIGYOU")*/
        AND bm10gsib.jigyou_cd = /*sibuCd*/
    /*END*/
    /*IF linkKbnCd.equals("HONBU")*/		
        AND bm10gsib.honbu_cd = /*sibuCd*/
    /*END*/
    /*IF shukeiKbn.equals("SV_CD")*/
        AND bm01tenm.MISE_CD IN (
            SELECT   BM50.MISE_CD
            FROM     BM50TANM AS BM50
            WHERE    BM50.COMPANY_CD = /*companyCd*/'00'
            AND      BM50.SV_CD      = /*svCd*/'00000921'
    )                         
    /*END*/
) takuinfo
/*IF companyCd.equals("00")*/
    JOIN bc23blck ON (takuinfo.block_cd = bc23blck.block_cd)
/*END*/	
GROUP BY ROLLUP(
takuinfo.taku_detail_cd,
(takuinfo.sibu_cd, takuinfo.sibu_name),
/*IF companyCd.equals("00")*/
(takuinfo.block_cd, bc23blck.block_name),
/*END*/
(takuinfo.mise_cd, takuinfo.mise_name))
ORDER BY
takuinfo.sibu_cd,
/*IF shukeiKbn.equals("AREA_DAI_CD") == false */
/*IF companyCd.equals("00")*/
    takuinfo.block_cd,
/*END*/
/*END*/
takuinfo.mise_cd, takuinfo.taku_detail_cd