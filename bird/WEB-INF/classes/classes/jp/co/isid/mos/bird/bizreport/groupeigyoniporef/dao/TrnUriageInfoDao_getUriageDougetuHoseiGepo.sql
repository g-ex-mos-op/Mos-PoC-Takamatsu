select          bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai as sibu_cd
-- ELSE
,               bm01.sibu_cd
/*END*/
,               sum(uriage_hosei)        as uriage
,               sum(kyakusu_hosei)       as kyakusu
,               sum(open_kbn)            as open_kbn
,               sum(open_kbn_zen)        as open_kbn_zen
,               sum(uriage_zen_hosei)    as uriage_zen
,               sum(kyakusu_zen_hosei)   as kyakusu_zen
from            bm01tenm as bm01
,               (select   bt64.company_cd
                ,         bt64.mise_cd
                ,         max(open_kbn) as open_kbn
                ,         max(open_kbn_zen_hosei) as open_kbn_zen
                ,         sum(uriage_hosei) as uriage_hosei
                ,         sum(kyakusu_hosei) as kyakusu_hosei
                ,         sum(uriage_zen_hosei) as uriage_zen_hosei
                ,         sum(kyakusu_zen_hosei) as kyakusu_zen_hosei
                from      bt64zgep as bt64
                ,         (select   company_cd
                          ,         mise_cd
                          ,         eigyo_ym
                          ,         kbn1
                          from      bn01dten
                          where     eigyo_ym = /*kikanFrom*/'200605'
                          and       company_cd=/*companyCd*/'00'
                          and       kbn1 = '1'
                          group by  company_cd
                          ,         mise_cd
                          ,         eigyo_ym
                          ,         kbn1
                          ) as bn01
                where     bt64.company_cd = bn01.company_cd
                and       bt64.mise_cd = bn01.mise_cd
                and       bt64.eigyo_dt = bn01.eigyo_ym
                and       bt64.eigyo_dt = /*kikanFrom*/'200605'                                
                and       bt64.open_kbn = 1
                and       bt64.oldm_flg = '0'
                and       (bt64.uriage_hosei > 0 and bt64.uriage_zen_hosei > 0)                
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