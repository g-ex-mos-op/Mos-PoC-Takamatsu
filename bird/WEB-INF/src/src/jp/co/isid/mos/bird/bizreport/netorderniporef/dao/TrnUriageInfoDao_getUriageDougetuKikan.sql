select          bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai as sibu_cd
-- ELSE
,               bm01.sibu_cd
/*END*/
,               sum(uriage)                    as uriage
,               sum(kyakusu)                   as kyakusu
,               sum(open_kbn)                  as open_kbn
,				sum(misecnt_kbn_nsum)          as mise_kbn_nsum
,				sum(misecnt_kbn_ntake)          as mise_kbn_ntake
,				sum(misecnt_kbn_ntaku)          as mise_kbn_ntaku
,               sum(uriage_nsum)               as uriage_nsum
,               sum(kyakusu_nsum)              as kyakusu_nsum
,               sum(uriage_nsum)               as uriage_nsum
,               sum(kyakusu_nsum)              as kyakusu_nsum
,               sum(uriage_ntake)              as uriage_ntake
,               sum(kyakusu_ntake)             as kyakusu_ntake
,               sum(uriage_ntaku)              as uriage_ntaku
,               sum(kyakusu_ntaku)             as kyakusu_ntaku
,               sum(open_kbn_zen_dogetu)         as open_kbn_zen
,               sum(uriage_zen_dogetu)           as uriage_zen
,               sum(kyakusu_zen_dogetu)          as kyakusu_zen
,               sum(uriage_zen_dogetu_nsum)      as uriage_nsum_zen
,               sum(kyakusu_zen_dogetu_nsum)     as kyakusu_nsum_zen
,               sum(uriage_zen_dogetu_ntake)     as uriage_ntake_zen
,               sum(kyakusu_zen_dogetu_ntake)    as kyakusu_ntake_zen
,               sum(uriage_zen_dogetu_ntaku)     as uriage_ntaku_zen
,               sum(kyakusu_zen_dogetu_ntaku)    as kyakusu_ntaku_zen
from            bm01tenm as bm01
,               (select  bd54.company_cd
                 ,       bd54.mise_cd 
                 ,      sum(bd54.uriage)                    as uriage
                 ,      sum(bd54.kyakusu)                   as kyakusu
                 ,      max(bd54.open_kbn)                  as open_kbn
                 ,      max(bd54.misecnt_kbn_nsum)          as misecnt_kbn_nsum
                 ,      max(bd54.misecnt_kbn_ntake)         as misecnt_kbn_ntake
                 ,      max(bd54.misecnt_kbn_ntaku)         as misecnt_kbn_ntaku
                 ,      sum(bd54.uriage_nsum)               as uriage_nsum
                 ,      sum(bd54.kyakusu_nsum)              as kyakusu_nsum
                 ,      sum(bd54.uriage_ntake)              as uriage_ntake
                 ,      sum(bd54.kyakusu_ntake)             as kyakusu_ntake
                 ,      sum(bd54.uriage_ntaku)              as uriage_ntaku
                 ,      sum(bd54.kyakusu_ntaku)             as kyakusu_ntaku
                 ,      max(bd54.open_kbn_zen_dogetu)       as open_kbn_zen_dogetu
                 ,      sum(bd54.uriage_zen_dogetu)         as uriage_zen_dogetu
                 ,      sum(bd54.kyakusu_zen_dogetu)        as kyakusu_zen_dogetu
                 ,      sum(bd54.uriage_zen_dogetu_nsum)    as uriage_zen_dogetu_nsum
                 ,      sum(bd54.kyakusu_zen_dogetu_nsum)   as kyakusu_zen_dogetu_nsum
                 ,      sum(bd54.uriage_zen_dogetu_ntake)   as uriage_zen_dogetu_ntake
                 ,      sum(bd54.kyakusu_zen_dogetu_ntake)  as kyakusu_zen_dogetu_ntake
                 ,      sum(bd54.uriage_zen_dogetu_ntaku)   as uriage_zen_dogetu_ntaku
                 ,      sum(bd54.kyakusu_zen_dogetu_ntaku)  as kyakusu_zen_dogetu_ntaku
                 from    BD54NNIP as bd54
                 ,       bn01dten as bn01
                 where  bd54.mise_cd = bn01.mise_cd
                 and    bd54.eigyo_dt = bn01.eigyo_dt
                 and    bd54.company_cd = bn01.company_cd
                 and    bd54.eigyo_dt between /*kikanFrom*/'20060525' and /*kikanTo*/'20060531' 
                 and    bd54.oldm_flg = '0' 
                 
                /*IF tenShu.equals("ALL")*/
                and     (bd54.open_kbn = 1 or bd54.open_kbn_zen_dogetu = 1) 
                -- ELSE
                and     bd54.open_kbn = 1
                /*END*/

                /*IF !tenShu.equals("ALL")*/
                     /*IF tenShu.equals("2")*/
                     and             (bn01.kbn1 = '1' or bn01.kbn1 = '2')
                     -- ELSE
                     and             bn01.kbn1 = /*dataShuKbn*/'1'
                    /*END*/
                 /*END*/
                group by bd54.company_cd
                ,        bd54.mise_cd
                ) as bd54
where           bd54.company_cd = bm01.company_cd
and             bd54.mise_cd = bm01.mise_cd

/*IF limitFlg == true*/
and             bm01.sibu_cd in (select   bm51.sibu_cd
                                 from     bm51svsb as bm51
                                 where    bm51.sv_cd = /*userId*/'00000921'
                                 and      bm51.company_cd = /*companyCd*/'00'
                                 group by bm51.sibu_cd
                                 )
/*END*/
    
/*IF !taishoTenpo.equals("ALL")*/
       /*IF taishoTenpo.equals("FC")*/
and             bm01.mise_kbn like '_1_'
       -- ELSE
and             bm01.mise_kbn like '_2_'
       /*END*/
/*END*/

and             bd54.company_cd = /*companyCd*/'00'
group by        bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai
-- ELSE
,               bm01.sibu_cd
/*END*/
order by        bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai
-- ELSE
,               bm01.sibu_cd
/*END*/