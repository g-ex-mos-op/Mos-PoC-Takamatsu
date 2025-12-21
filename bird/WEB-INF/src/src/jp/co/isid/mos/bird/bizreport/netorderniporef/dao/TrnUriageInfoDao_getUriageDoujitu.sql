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
,				sum(misecnt_kbn_ntake)         as mise_kbn_ntake
,				sum(misecnt_kbn_ntaku)         as mise_kbn_ntaku
,               sum(uriage_nsum)               as uriage_nsum
,               sum(kyakusu_nsum)              as kyakusu_nsum
,               sum(uriage_ntake)              as uriage_ntake
,               sum(kyakusu_ntake)             as kyakusu_ntake
,               sum(uriage_ntaku)              as uriage_ntaku
,               sum(kyakusu_ntaku)             as kyakusu_ntaku
,               sum(open_kbn_zen_dojitu)         as open_kbn_zen
,               sum(uriage_zen_dojitu)           as uriage_zen
,               sum(kyakusu_zen_dojitu)          as kyakusu_zen
,               sum(uriage_zen_dojitu_nsum)      as uriage_nsum_zen
,               sum(kyakusu_zen_dojitu_nsum)     as kyakusu_nsum_zen
,               sum(uriage_zen_dojitu_ntake)     as uriage_ntake_zen
,               sum(kyakusu_zen_dojitu_ntake)    as kyakusu_ntake_zen
,               sum(uriage_zen_dojitu_ntaku)     as uriage_ntaku_zen
,               sum(kyakusu_zen_dojitu_ntaku)    as kyakusu_ntaku_zen
from            BD54NNIP as bd54
,               bm01tenm as bm01
,               bn01dten as bn01
where           bd54.company_cd = bm01.company_cd
and             bm01.company_cd = bn01.company_cd
and             bd54.mise_cd = bm01.mise_cd
and             bm01.mise_cd = bn01.mise_cd
and             bd54.eigyo_dt = bn01.eigyo_dt
and             bd54.eigyo_dt = /*kikanFrom*/'20060101'
and             bd54.open_kbn = 1
and             bd54.company_cd = /*companyCd*/'00'
and             bn01.kbn1 = '1'

/*IF !taishoTenpo.equals("ALL")*/
       /*IF taishoTenpo.equals("FC")*/
        and             bm01.mise_kbn like '_1_'
       -- ELSE
        and             bm01.mise_kbn like '_2_'
       /*END*/
/*END*/

/*IF limitFlg == true*/
and             bm01.sibu_cd in (select   bm01.sibu_cd
                                 from     bm51svsb as bm51
                                 ,        bm01tenm as bm01
                                 where    bm01.sibu_cd = bm51.sibu_cd
                                 and      bm51.sv_cd = /*userId*/'00000921'
                                 and      bm51.company_cd = /*companyCd*/'00'
                                 group by bm01.sibu_cd
                                 )
/*END*/
and             bd54.oldm_flg = '0'
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