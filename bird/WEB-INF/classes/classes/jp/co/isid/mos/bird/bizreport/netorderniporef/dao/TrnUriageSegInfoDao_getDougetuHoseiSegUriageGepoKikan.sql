select
    bm08.company_cd,
    bm08.segment_type,
    bm08.segment_name,
    (case when open_kbn is null then 0 else open_kbn end) as open_kbn,
    (case when open_kbn_zen is null then 0 else open_kbn_zen end) as open_kbn_zen,
    (case when uriage is null then 0 else uriage end) as uriage,
    (case when kyakusu is null then 0 else kyakusu end) as kyakusu,
    (case when uriage_zen is null then 0 else uriage_zen end) as uriage_zen,
    (case when kyakusu_zen is null then 0 else kyakusu_zen end) as kyakusu_zen,
    (case when uriage_nsum is null then 0 else uriage_nsum end) as uriage_nsum,
    (case when kyakusu_nsum is null then 0 else kyakusu_nsum end) as kyakusu_nsum,
    (case when uriage_nsum_zen is null then 0 else uriage_nsum_zen end) as uriage_nsum_zen,
    (case when kyakusu_nsum_zen is null then 0 else kyakusu_nsum_zen end) as kyakusu_nsum_zen,
    (case when uriage_ntake is null then 0 else uriage_ntake end) as uriage_ntake,
    (case when kyakusu_ntake is null then 0 else kyakusu_ntake end) as kyakusu_ntake,
    (case when uriage_ntake_zen is null then 0 else uriage_ntake_zen end) as uriage_ntake_zen,
    (case when kyakusu_ntake_zen is null then 0 else kyakusu_ntake_zen end) as kyakusu_ntake_zen,
    (case when uriage_ntaku is null then 0 else uriage_ntaku end) as uriage_ntaku,
    (case when kyakusu_ntaku is null then 0 else kyakusu_ntaku end) as kyakusu_ntaku,
    (case when uriage_ntaku_zen is null then 0 else uriage_ntaku_zen end) as uriage_ntaku_zen,
    (case when kyakusu_ntaku_zen is null then 0 else kyakusu_ntaku_zen end) as kyakusu_ntaku_zen
from
    bm08sgtp as bm08 left join
    (select
        bm01.company_cd,
        bm09.segment_type,
        sum(bd53.open_kbn) as open_kbn,
        sum(bd53.open_kbn_zen) as open_kbn_zen,
        sum(bd53.uriage) as uriage,
        sum(bd53.kyakusu) as kyakusu,
        sum(bd53.uriage_zen) as uriage_zen,
        sum(bd53.kyakusu_zen) as kyakusu_zen,
        sum(bd53.uriage_nsum) as uriage_nsum,
        sum(bd53.kyakusu_nsum) as kyakusu_nsum,
        sum(bd53.uriage_nsum_zen) as uriage_nsum_zen,
        sum(bd53.kyakusu_nsum_zen) as kyakusu_nsum_zen,
        sum(bd53.uriage_ntake) as uriage_ntake,
        sum(bd53.kyakusu_ntake) as kyakusu_ntake,
        sum(bd53.uriage_ntake_zen) as uriage_ntake_zen,
        sum(bd53.kyakusu_ntake_zen) as kyakusu_ntake_zen,
        sum(bd53.uriage_ntaku) as uriage_ntaku,
        sum(bd53.kyakusu_ntaku) as kyakusu_ntaku,
        sum(bd53.uriage_ntaku_zen) as uriage_ntaku_zen,
        sum(bd53.kyakusu_ntaku_zen) as kyakusu_ntaku_zen
     from
        bm09gtsg as bm09
        join bm01tenm as bm01 on (bm09.gyotai_kbn = bm01.gyotai_kbn)
        join   
            (select
                 bd53.company_cd,
                 bd53.mise_cd,
                 max(bd53.open_kbn) as open_kbn,
                 max(bd53.open_kbn_zen_hosei) as open_kbn_zen,
                 sum(bd53.uriage_hosei) as uriage,
                 sum(bd53.kyakusu_hosei) as kyakusu,
                 sum(bd53.uriage_zen_hosei) as uriage_zen,
                 sum(bd53.kyakusu_zen_hosei) as kyakusu_zen,
                 sum(bd53.uriage_hosei_nsum) as uriage_nsum,
                 sum(bd53.kyakusu_hosei_nsum) as kyakusu_nsum,
                 sum(bd53.uriage_zen_hosei_nsum) as uriage_nsum_zen,
                 sum(bd53.kyakusu_zen_hosei_nsum) as kyakusu_nsum_zen,
                 sum(bd53.uriage_hosei_ntake) as uriage_ntake,
                 sum(bd53.kyakusu_hosei_ntake) as kyakusu_ntake,
                 sum(bd53.uriage_zen_hosei_ntake) as uriage_ntake_zen,
                 sum(bd53.kyakusu_zen_hosei_ntake) as kyakusu_ntake_zen,
                 sum(bd53.uriage_hosei_ntaku) as uriage_ntaku,
                 sum(bd53.kyakusu_hosei_ntaku) as kyakusu_ntaku,
                 sum(bd53.uriage_zen_hosei_ntaku) as uriage_ntaku_zen,
                 sum(bd53.kyakusu_zen_hosei_ntaku) as kyakusu_ntaku_zen
             from
                 BD53NGEP as bd53
                 join
                 (select company_cd, mise_cd, eigyo_ym
                  from bn01dten
                  /*IF !tenShu.equals("ALL")*/
                      /*IF tenShu.equals("2")*/
	                      where (kbn1 = '1' or kbn1 = '2')
	                  -- ELSE
                          where  kbn1 = /*tenShu*/'1'
	                  /*END*/
	              /*END*/
                  group by company_cd, mise_cd, eigyo_ym
                 ) as bn01 on (bd53.company_cd = bn01.company_cd and bd53.mise_cd = bn01.mise_cd and bd53.eigyo_dt = bn01.eigyo_ym)
             where
                 bd53.eigyo_dt between /*kikanFrom*/'200605' and /*kikanTo*/'200606'
                 and (bd53.uriage_hosei > 0 and bd53.uriage_zen_hosei > 0)
                 and (bd53.open_kbn = 1 and bd53.open_kbn_zen_hosei = 1)
                 and bd53.oldm_flg = '0'
             group by bd53.company_cd, bd53.mise_cd
        ) as bd53 on (bd53.company_cd = bm01.company_cd and bd53.mise_cd = bm01.mise_cd)
    where
        bm01.company_cd =/*companyCd*/'00'
    and bm09.segment_type in /*segmentList*/('M','J')
/*IF !taishoTenpo.equals("ALL")*/
    /*IF taishoTenpo.equals("FC")*/
        and bm01.mise_kbn like '_1_'
    -- ELSE
        and bm01.mise_kbn like '_2_'
    /*END*/
/*END*/
/*IF limitFlg == true */
    AND bm01.sibu_cd IN (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD      = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
    )
/*END*/
group by bm01.company_cd, bm09.segment_type
) as seg on (bm08.segment_type = seg.segment_type)
where   bm08.segment_type in /*segmentList*/('M','J')
order by bm08.sort_seq