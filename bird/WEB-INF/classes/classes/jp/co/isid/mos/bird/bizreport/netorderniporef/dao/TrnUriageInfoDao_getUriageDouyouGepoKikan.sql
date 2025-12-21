select         bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai as sibu_cd
-- ELSE
,               bm01.sibu_cd
/*END*/
,               sum(uriage_doyo)                as uriage
,               sum(kyakusu_doyo)               as kyakusu
,               sum(open_kbn)                   as open_kbn
,               sum(open_kbn_zen)               as open_kbn_zen
,				sum(misecnt_doyo_nsum)          as mise_kbn_nsum
,				sum(misecnt_doyo_ntake)         as mise_kbn_ntake
,				sum(misecnt_doyo_ntaku)         as mise_kbn_ntaku
,				sum(uriage_doyo_nsum)           as uriage_nsum
,               sum(kyakusu_doyo_nsum)          as kyakusu_nsum
,               sum(uriage_doyo_ntake)          as uriage_ntake
,               sum(kyakusu_doyo_ntake)         as kyakusu_ntake
,               sum(uriage_doyo_ntaku)          as uriage_ntaku
,               sum(kyakusu_doyo_ntaku)         as kyakusu_ntaku
,               sum(uriage_zen_doyo)            as uriage_zen
,               sum(kyakusu_zen_doyo)           as kyakusu_zen
,               sum(uriage_zen_doyo_nsum)       as uriage_nsum_zen
,               sum(kyakusu_zen_doyo_nsum)      as kyakusu_nsum_zen
,               sum(uriage_zen_doyo_ntake)      as uriage_ntake_zen
,               sum(kyakusu_zen_doyo_ntake)     as kyakusu_ntake_zen
,               sum(uriage_zen_doyo_ntaku)      as uriage_ntaku_zen
,               sum(kyakusu_zen_doyo_ntaku)     as kyakusu_ntaku_zen
from            bm01tenm as bm01
,               (select   bd53.company_cd
                ,         bd53.mise_cd
                ,         max(open_kbn) as open_kbn
                ,         max(open_kbn_zen_doyo) as open_kbn_zen
                ,         max(misecnt_doyo_nsum) as misecnt_doyo_nsum
                ,         max(misecnt_doyo_ntake) as misecnt_doyo_ntake
                ,         max(misecnt_doyo_ntaku) as misecnt_doyo_ntaku
                ,         sum(uriage_doyo) as uriage_doyo
                ,         sum(kyakusu_doyo) as kyakusu_doyo
                ,         sum(uriage_doyo_nsum) as uriage_doyo_nsum
                ,         sum(kyakusu_doyo_nsum) as kyakusu_doyo_nsum
                ,         sum(uriage_doyo_ntake) as uriage_doyo_ntake
                ,         sum(kyakusu_doyo_ntake) as kyakusu_doyo_ntake
                ,         sum(uriage_doyo_ntaku) as uriage_doyo_ntaku
                ,         sum(kyakusu_doyo_ntaku) as kyakusu_doyo_ntaku
                ,         sum(uriage_zen_doyo) as uriage_zen_doyo
                ,         sum(kyakusu_zen_doyo) as kyakusu_zen_doyo
                ,         sum(uriage_zen_doyo_nsum) as uriage_zen_doyo_nsum
                ,         sum(kyakusu_zen_doyo_nsum) as kyakusu_zen_doyo_nsum
                ,         sum(uriage_zen_doyo_ntake) as uriage_zen_doyo_ntake
                ,         sum(kyakusu_zen_doyo_ntake) as kyakusu_zen_doyo_ntake
                ,         sum(uriage_zen_doyo_ntaku) as uriage_zen_doyo_ntaku
                ,         sum(kyakusu_zen_doyo_ntaku) as kyakusu_zen_doyo_ntaku
                from      BD53NGEP as bd53
                ,         (select   company_cd
                          ,         mise_cd
                          ,         eigyo_ym
                          ,         kbn1
                          from      bn01dten
                          where     eigyo_dt between concat(/*kikanFrom*/'200605','00') and concat(/*kikanTo*/'200606','99')
                          and       company_cd=/*companyCd*/'00'
                          and       kbn1 = '1'
                          group by  company_cd
                          ,         mise_cd
                          ,         eigyo_ym
                          ,         kbn1
                          ) as bn01
                where     bd53.eigyo_dt = bn01.eigyo_ym
                and       bd53.company_cd = bn01.company_cd
                and       bd53.mise_cd = bn01.mise_cd
                and       bd53.eigyo_dt between /*kikanFrom*/'200605' and /*kikanTo*/'200606'
                and       bd53.open_kbn = 1
                and       bd53.oldm_flg = '0'
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
order by        bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai
-- ELSE
,               bm01.sibu_cd
/*END*/