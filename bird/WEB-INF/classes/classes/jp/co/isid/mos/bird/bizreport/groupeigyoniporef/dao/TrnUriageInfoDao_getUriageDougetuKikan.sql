select          bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai as sibu_cd
-- ELSE
,               bm01.sibu_cd
/*END*/
,               sum(uriage)              as uriage
,               sum(kyakusu)             as kyakusu
,               sum(open_kbn)            as open_kbn
,               sum(open_kbn_zen_dogetu) as open_kbn_zen
,               sum(uriage_zen_dogetu)   as uriage_zen
,               sum(kyakusu_zen_dogetu)  as kyakusu_zen
from            bm01tenm as bm01
,               (select bt60.company_cd
                 ,      bt60.mise_cd 
                 ,      max(open_kbn) as open_kbn
                 ,      max(open_kbn_zen_dogetu) as open_kbn_zen_dogetu
                 ,      sum(uriage) as uriage
                 ,      sum(kyakusu) as kyakusu
                 ,      sum(uriage_zen_dogetu) as uriage_zen_dogetu
                 ,      sum(kyakusu_zen_dogetu) as kyakusu_zen_dogetu
                 from   bt60znip as bt60
                 ,      bn01dten as bn01
                 where  bt60.mise_cd = bn01.mise_cd
                 and    bt60.eigyo_dt = bn01.eigyo_dt
                 and    bt60.company_cd = bn01.company_cd
                 and    bt60.eigyo_dt between /*kikanFrom*/'20060525' and /*kikanTo*/'20060531' 
                 and    bt60.oldm_flg = '0' 
                 
                /*IF tenShu.equals("ALL")*/
                and     (bt60.open_kbn = 1 or bt60.open_kbn_zen_dogetu = 1) 
                -- ELSE
                and     bt60.open_kbn = 1
                /*END*/

                /*IF !tenShu.equals("ALL")*/
                     /*IF tenShu.equals("2")*/
                     and             (bn01.kbn1 = '1' or bn01.kbn1 = '2')
                     -- ELSE
                     and             bn01.kbn1 = /*dataShuKbn*/'1'
                    /*END*/
                 /*END*/
                group by bt60.company_cd
                ,        bt60.mise_cd
                ) as bt60
where           bt60.company_cd = bm01.company_cd
and             bt60.mise_cd = bm01.mise_cd

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

and             bt60.company_cd = /*companyCd*/'00'
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
