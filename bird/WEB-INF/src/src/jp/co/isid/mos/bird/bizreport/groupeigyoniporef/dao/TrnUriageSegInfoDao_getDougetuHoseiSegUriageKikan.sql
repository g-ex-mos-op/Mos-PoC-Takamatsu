select
    bm08.company_cd,
    bm08.segment_type,
    bm08.segment_name,
    (case when open_kbn is null then 0 else open_kbn end) as open_kbn,
    (case when open_kbn_zen is null then 0 else open_kbn_zen end) as open_kbn_zen,
    (case when uriage is null then 0 else uriage end) as uriage,
    (case when kyakusu is null then 0 else kyakusu end) as kyakusu,
    (case when uriage_zen is null then 0 else uriage_zen end) as uriage_zen,
    (case when kyakusu_zen is null then 0 else kyakusu_zen end) as kyakusu_zen
from
    bm08sgtp as bm08 left join
    (select
        bm01.company_cd,
        bm09.segment_type,
        sum(bt60.open_kbn) as open_kbn,
        sum(bt60.open_kbn_zen) as open_kbn_zen,        
        sum(bt60.uriage) as uriage,
        sum(bt60.kyakusu) as kyakusu,
        sum(bt60.uriage_zen) as uriage_zen,
        sum(bt60.kyakusu_zen) as kyakusu_zen
     from
        bm09gtsg as bm09
        join bm01tenm as bm01 on (bm09.gyotai_kbn = bm01.gyotai_kbn)
        join (select
                 bt60.company_cd,
                 bt60.mise_cd,
                 max(bt60.open_kbn) as open_kbn,
                 max(bt60.open_kbn_zen_dogetu) as open_kbn_zen,
                 sum(bt60.uriage) as uriage,
                 sum(bt60.kyakusu) as kyakusu,
                 sum(bt60.uriage_zen_dogetu) as uriage_zen,
                 sum(bt60.kyakusu_zen_dogetu) as kyakusu_zen
             from
                 bt60znip as bt60
                 join bn01dten as bn01 on (bt60.company_cd = bn01.company_cd and bt60.mise_cd = bn01.mise_cd and bt60.eigyo_dt = bn01.eigyo_dt)
             where
                 bt60.eigyo_dt between /*kikanFrom*/'20060525' and /*kikanTo*/'20060531'
                 and (bt60.uriage > 0 and bt60.uriage_zen_dogetu > 0)
                 and (bt60.open_kbn = 1 and bt60.open_kbn_zen_dogetu = 1)
                 and bt60.oldm_flg = '0'
             /*IF !tenShu.equals("ALL")*/
                 /*IF tenShu.equals("2")*/
	                 and (bn01.kbn1 = '1' or bn01.kbn1 = '2')
                 -- ELSE
                     and bn01.kbn1 = /*tenShu*/'1'
                /*END*/
             /*END*/
             group by bt60.company_cd, bt60.mise_cd
        ) as bt60 on (bt60.company_cd = bm01.company_cd and bt60.mise_cd = bm01.mise_cd)        
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
         AND    BM51.SV_CD      = /*userId*/'99990003'
         GROUP BY BM51.SIBU_CD
    )
/*END*/
group by bm01.company_cd, bm09.segment_type
) as seg on (bm08.segment_type = seg.segment_type)
where   bm08.segment_type in /*segmentList*/('M','J')
order by bm08.sort_seq