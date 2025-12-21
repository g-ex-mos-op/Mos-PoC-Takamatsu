select          bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai as sibu_cd
-- ELSE
,               bm01.sibu_cd
/*END*/
,               sum(uriage_dogetu)            as uriage
,               sum(kyakusu_dogetu)           as kyakusu
,               sum(open_kbn)                 as open_kbn
,               sum(open_kbn_zen)             as open_kbn_zen
,               sum(misecnt_dogetu_nsum)      as mise_kbn_nsum
,               sum(misecnt_dogetu_ntake)     as mise_kbn_ntake
,               sum(misecnt_dogetu_ntaku)     as mise_kbn_ntaku
,               sum(uriage_dogetu_nsum)       as uriage_nsum
,               sum(kyakusu_dogetu_nsum)      as kyakusu_nsum
,               sum(uriage_dogetu_ntake)      as uriage_ntake
,               sum(kyakusu_dogetu_ntake)     as kyakusu_ntake
,               sum(uriage_dogetu_ntaku)      as uriage_ntaku
,               sum(kyakusu_dogetu_ntaku)     as kyakusu_ntaku
,               sum(uriage_zen_dogetu)        as uriage_zen
,               sum(kyakusu_zen_dogetu)       as kyakusu_zen
,               sum(uriage_zen_dogetu_nsum)   as uriage_nsum_zen
,               sum(kyakusu_zen_dogetu_nsum)  as kyakusu_nsum_zen
,               sum(uriage_zen_dogetu_ntake)  as uriage_ntake_zen
,               sum(kyakusu_zen_dogetu_ntake) as kyakusu_ntake_zen
,               sum(uriage_zen_dogetu_ntaku)  as uriage_ntaku_zen
,               sum(kyakusu_zen_dogetu_ntaku) as kyakusu_ntaku_zen
from            bm01tenm as bm01
,               (select   bd53.company_cd
                ,         bd53.mise_cd
                ,         max(open_kbn) as open_kbn
                ,         max(open_kbn_zen_dogetu) as open_kbn_zen
                ,         max(misecnt_dogetu_nsum) as misecnt_dogetu_nsum
                ,         max(misecnt_dogetu_ntake) as misecnt_dogetu_ntake
                ,         max(misecnt_dogetu_ntaku) as misecnt_dogetu_ntaku
                ,         sum(uriage_dogetu) as uriage_dogetu
                ,         sum(kyakusu_dogetu) as kyakusu_dogetu
                ,         sum(uriage_dogetu_nsum) as uriage_dogetu_nsum
                ,         sum(kyakusu_dogetu_nsum) as kyakusu_dogetu_nsum
                ,         sum(uriage_dogetu_ntake) as uriage_dogetu_ntake
                ,         sum(kyakusu_dogetu_ntake) as kyakusu_dogetu_ntake
                ,         sum(uriage_dogetu_ntaku) as uriage_dogetu_ntaku
                ,         sum(kyakusu_dogetu_ntaku) as kyakusu_dogetu_ntaku
                ,         sum(uriage_zen_dogetu) as uriage_zen_dogetu
                ,         sum(kyakusu_zen_dogetu) as kyakusu_zen_dogetu
                ,         sum(uriage_zen_dogetu_nsum) as uriage_zen_dogetu_nsum
                ,         sum(kyakusu_zen_dogetu_nsum) as kyakusu_zen_dogetu_nsum
                ,         sum(uriage_zen_dogetu_ntake) as uriage_zen_dogetu_ntake
                ,         sum(kyakusu_zen_dogetu_ntake) as kyakusu_zen_dogetu_ntake
                ,         sum(uriage_zen_dogetu_ntaku) as uriage_zen_dogetu_ntaku
                ,         sum(kyakusu_zen_dogetu_ntaku) as kyakusu_zen_dogetu_ntaku
                from      BD53NGEP as bd53
                ,         (select   company_cd
                          ,         mise_cd
                          ,         eigyo_ym
                          ,         kbn1
                          from      bn01dten
                          where     eigyo_ym = /*kikanFrom*/'200605'
                          and       company_cd=/*companyCd*/'00'

                          /*IF !tenShu.equals("ALL")*/
                             /*IF tenShu.equals("2")*/
                          and       (kbn1 = '1' or kbn1 = '2')
                             -- ELSE
                          and        kbn1 = /*dataShuKbn*/'1'
                             /*END*/
                          /*END*/

                          group by  company_cd
                          ,         mise_cd
                          ,         eigyo_ym
                          ,         kbn1
                          ) as bn01

                where     bd53.company_cd = bn01.company_cd
                and       bd53.mise_cd = bn01.mise_cd
                and       bd53.eigyo_dt = bn01.eigyo_ym
                and       bd53.eigyo_dt = /*kikanFrom*/'200605'
                and       bd53.oldm_flg = '0'
                
                /*IF tenShu.equals("ALL")*/
                    and       (bd53.open_kbn = 1 or bd53.open_kbn_zen_dogetu = 1)
                -- ELSE
                    and       bd53.open_kbn = 1
                /*END*/

                group by  bd53.company_cd
                ,         bd53.mise_cd
               
                ) as bd53
where           bd53.company_cd = bm01.company_cd
and             bd53.mise_cd = bm01.mise_cd

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

group by        bm01.company_cd

/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai
-- ELSE
,               bm01.sibu_cd
/*END*/