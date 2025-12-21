select          bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai as sibu_cd
-- ELSE
,               bm01.sibu_cd
/*END*/
,               sum(uriage)              as uriage
,               sum(kyakusu)             as kyakusu
,               sum(open_kbn)            as open_kbn
,               sum(open_kbn_zen_dojitu)   as open_kbn_zen
,               sum(uriage_zen_dojitu)     as uriage_zen
,               sum(kyakusu_zen_dojitu)    as kyakusu_zen
from            bm01tenm as bm01
,               (select  bt60.company_cd
                 ,       bt60.mise_cd
                 ,       max(open_kbn) as open_kbn
                 ,       max(open_kbn_zen_dojitu) as open_kbn_zen_dojitu
                 ,       sum(uriage) as uriage
                 ,       sum(kyakusu) as kyakusu
                 ,       sum(uriage_zen_dojitu) as uriage_zen_dojitu
                 ,       sum(kyakusu_zen_dojitu) as kyakusu_zen_dojitu
                 from    bt60znip as bt60
                 ,       bn01dten as bn01
                 where   bt60.eigyo_dt = bn01.eigyo_dt
                 and     bt60.company_cd = bn01.company_cd
                 and     bt60.mise_cd = bn01.mise_cd
                 and     bt60.eigyo_dt between /*kikanFrom*/'20060525' and /*kikanTo*/'20060531'
                 and     bt60.company_cd = /*companyCd*/'00'
                 and     bt60.open_kbn = 1
                 and     bn01.kbn1 = '1'
                 and     bt60.oldm_flg = '0'
                group by bt60.company_cd
                ,        bt60.mise_cd
                ) as bt60

where           bt60.company_cd = bm01.company_cd
and             bt60.mise_cd = bm01.mise_cd

/*IF !taishoTenpo.equals("ALL")*/
       /*IF taishoTenpo.equals("FC")*/
and             bm01.mise_kbn like '_1_'
       -- ELSE
and             bm01.mise_kbn like '_2_'
       /*END*/
/*END*/

/*IF limitFlg == true*/
and             bm01.sibu_cd in (select   bm51.sibu_cd
                                 from     bm51svsb as bm51
                                 where    bm51.sv_cd = /*userId*/'00000921'
                                 and      bm51.company_cd = /*companyCd*/'00'
                                 group by bm51.sibu_cd
                                 )
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