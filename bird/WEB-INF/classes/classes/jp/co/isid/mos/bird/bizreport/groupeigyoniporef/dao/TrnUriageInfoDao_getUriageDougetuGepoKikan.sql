select         bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai as sibu_cd
-- ELSE
,               bm01.sibu_cd
/*END*/
,               sum(uriage_dogetu)       as uriage
,               sum(kyakusu_dogetu)      as kyakusu
,               sum(open_kbn)            as open_kbn
,               sum(open_kbn_zen)        as open_kbn_zen
,               sum(uriage_zen_dogetu)   as uriage_zen
,               sum(kyakusu_zen_dogetu)  as kyakusu_zen
from            bm01tenm as bm01
,               (select   bt64.company_cd
                ,         bt64.mise_cd
                ,         max(open_kbn) as open_kbn
                ,         max(open_kbn_zen_dogetu) as open_kbn_zen
                ,         sum(uriage_dogetu) as uriage_dogetu
                ,         sum(kyakusu_dogetu) as kyakusu_dogetu
                ,         sum(uriage_zen_dogetu) as uriage_zen_dogetu
                ,         sum(kyakusu_zen_dogetu) as kyakusu_zen_dogetu
                from      bt64zgep as bt64
                ,         (select   company_cd
                          ,         mise_cd
                          ,         eigyo_ym
                          ,         kbn1
                          from      bn01dten
                          where     eigyo_dt between concat(/*kikanFrom*/'200605','00') and concat(/*kikanTo*/'20060699','99')
                          and       company_cd=/*companyCd*/'00'

                         /*IF !tenShu.equals("ALL")*/
                             /*IF tenShu.equals("2")*/
                          and       (kbn1 = '1' or kbn1 = '2')
                            -- ELSE
                          and       kbn1 = /*dataShuKbn*/'1'
                            /*END*/
                         /*END*/

                          group by  company_cd
                          ,         mise_cd
                          ,         eigyo_ym
                          ,         kbn1
                          ) as bn01
                where     bt64.company_cd = bn01.company_cd
                and       bt64.mise_cd = bn01.mise_cd
                and       bt64.eigyo_dt = bn01.eigyo_ym
                and       bt64.oldm_flg = '0'
                and       bt64.eigyo_dt between /*kikanFrom*/'200605' and /*kikanTo*/'200606'

                /*IF tenShu.equals("ALL")*/
                and       (bt64.open_kbn = 1 or bt64.open_kbn_zen_dogetu = 1)
                -- ELSE
                and       bt64.open_kbn = 1
                /*END*/
                group by  bt64.company_cd
                ,         bt64.mise_cd
               
                ) as bt64
where           bt64.company_cd = bm01.company_cd
and             bt64.mise_cd = bm01.mise_cd

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
    
and             bt64.company_cd = /*companyCd*/'00'    
group by        bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai
-- ELSE
,               bm01.sibu_cd
/*END*/
