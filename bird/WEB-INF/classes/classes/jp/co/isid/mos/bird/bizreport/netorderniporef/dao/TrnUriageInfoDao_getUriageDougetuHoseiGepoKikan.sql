select         bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai as sibu_cd
-- ELSE
,               bm01.sibu_cd
/*END*/
,               sum(uriage_hosei)       as uriage
,               sum(kyakusu_hosei)      as kyakusu
,               sum(open_kbn)            as open_kbn
,               sum(open_kbn_zen)        as open_kbn_zen
,               sum(uriage_hosei_nsum)   as uriage_nsum
,               sum(kyakusu_hosei_nsum)   as kyakusu_nsum
,               sum(uriage_hosei_ntake)   as uriage_ntake
,               sum(kyakusu_hosei_ntake)   as kyakusu_ntake
,               sum(uriage_hosei_ntaku)   as uriage_ntaku
,               sum(kyakusu_hosei_ntaku)   as kyakusu_ntaku
,               sum(uriage_zen_hosei)    as uriage_zen
,               sum(kyakusu_zen_hosei)   as kyakusu_zen
,               sum(uriage_zen_hosei_nsum)    as uriage_nsum_zen
,               sum(kyakusu_zen_hosei_nsum)   as kyakusu_nsum_zen
,               sum(uriage_zen_hosei_ntake)   as uriage_ntake_zen
,               sum(kyakusu_zen_hosei_ntake)  as kyakusu_ntake_zen
,               sum(uriage_zen_hosei_ntaku)   as uriage_ntaku_zen
,               sum(kyakusu_zen_hosei_ntaku)  as kyakusu_ntaku_zen
from            bm01tenm as bm01
,               (select   bd53.company_cd
                ,         bd53.mise_cd
                ,         max(open_kbn) as open_kbn
                ,         max(open_kbn_zen) as open_kbn_zen
                ,         sum(uriage_hosei) as uriage_hosei
                ,         sum(kyakusu_hosei) as kyakusu_hosei
                ,         sum(uriage_hosei_nsum) as uriage_hosei_nsum
                ,         sum(kyakusu_hosei_nsum) as kyakusu_hosei_nsum
                ,         sum(uriage_hosei_ntake) as uriage_hosei_ntake
                ,         sum(kyakusu_hosei_ntake) as kyakusu_hosei_ntake
                ,         sum(uriage_hosei_ntaku) as uriage_hosei_ntaku
                ,         sum(kyakusu_hosei_ntaku) as kyakusu_hosei_ntaku
                ,         sum(uriage_zen_hosei) as uriage_zen_hosei
                ,         sum(kyakusu_zen_hosei) as kyakusu_zen_hosei
                ,         sum(uriage_zen_hosei_nsum) as uriage_zen_hosei_nsum
                ,         sum(kyakusu_zen_hosei_nsum) as kyakusu_zen_hosei_nsum
                ,         sum(uriage_zen_hosei_ntake) as uriage_zen_hosei_ntake
                ,         sum(kyakusu_zen_hosei_ntake) as kyakusu_zen_hosei_ntake
                ,         sum(uriage_zen_hosei_ntaku) as uriage_zen_hosei_ntaku
                ,         sum(kyakusu_zen_hosei_ntaku) as kyakusu_zen_hosei_ntaku
                from      BD53NGEP as bd53
                ,         (select   company_cd
                          ,         mise_cd
                          ,         eigyo_ym
                          ,         kbn1
                          from      bn01dten
                          where     eigyo_dt between concat(/*kikanFrom*/'200605','00') and concat(/*kikanTo*/'20060699','99')
                          and       company_cd=/*companyCd*/'00'
                          and       kbn1 = '1'
                          group by  company_cd
                          ,         mise_cd
                          ,         eigyo_ym
                          ,         kbn1
                          ) as bn01
                where     bd53.company_cd = bn01.company_cd
                and       bd53.mise_cd = bn01.mise_cd
                and       bd53.eigyo_dt = bn01.eigyo_ym
                and       bd53.oldm_flg = '0'
                and       bd53.eigyo_dt between /*kikanFrom*/'200605' and /*kikanTo*/'200606'
                and       (bd53.uriage_hosei > 0 and bd53.uriage_zen_hosei > 0)
                and       bd53.open_kbn = 1
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

and             bd53.company_cd = /*companyCd*/'00'
group by        bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bm01.area_dai
-- ELSE
,               bm01.sibu_cd
/*END*/
