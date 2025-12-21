select          bm10.company_cd
,               bm10.honbu_cd
,               bm10.honbu_name
,               bm10.jigyou_cd
,               bm10.jigyou_name
,               bm10.slarea_cd
,               bm10.slarea_name
,               bm10.sibu_cd
,               bm10.sibu_name
,               (case when bt60.sibu_cd is null
                      then (case when bt45.sibu_cd is null then 'NOTDISP' 
                                 when bt45.yosan = 0      then 'NOTDISP'
                                 else 'DISPONLY' end)
                      else 'DISP'
                 end) as disp_kbn
from            bm10gsib as bm10
left join       (
                select          bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
                ,               bm01.area_dai as sibu_cd
-- ELSE
                ,               bm01.sibu_cd
/*END*/
                from            bt60znip as bt60
                ,               bm01tenm as bm01
                ,               bn01dten as bn01
                where           bt60.company_cd = bm01.company_cd
                and             bm01.company_cd = bn01.company_cd
                and             bt60.mise_cd = bm01.mise_cd
                and             bm01.mise_cd = bn01.mise_cd
                and             bt60.eigyo_dt = bn01.eigyo_dt
/*IF taishoKikan.equals("DAY1")*/ 
                and             bt60.eigyo_dt = /*kikanFrom*/'20060101'
/*END*/
/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH")*/ 
                and             bt60.eigyo_dt between /*kikanFrom*/'20060101' and /*kikanTo*/'20060201'
/*END*/
/*IF !taishoTenpo.equals("ALL")*/
       /*IF taishoTenpo.equals("FC")*/
                and             bm01.mise_kbn like '_1_'
       -- ELSE
                and             bm01.mise_kbn like '_2_'
       /*END*/
/*END*/

/*IF tenShu.equals("ALL")*/
                and             (bt60.open_kbn = 1 or bt60.open_kbn_zen_dogetu = 1) 
-- ELSE
                and             bt60.open_kbn = 1
/*END*/

/*IF !tenShu.equals("ALL")*/
    /*IF tenShu.equals("2")*/
                and             (bn01.kbn1 = '1' or bn01.kbn1 = '2')
    -- ELSE
                and             bn01.kbn1 = /*dataShuKbn*/'1'
    /*END*/
/*END*/
                and             bt60.oldm_flg = '0'
                        
/*IF limitFlg == true*/
                and             sibu_cd in (select   bm51.sibu_cd
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
                ) as bt60
on              (bm10.company_cd = bt60.company_cd and bm10.sibu_cd = bt60.sibu_cd)
left join       (
                select          bt45.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
                ,               bt45.area_dai as sibu_cd
-- ELSE
                ,               bt45.sibu_cd
/*END*/
                ,               sum(yosan) as yosan                 
                from            bt45dsjy as bt45
                where           bt45.company_cd = /*companyCd*/'00'
                
/*IF !tenShu.equals("ALL")*/
    /*IF tenShu.equals("2")*/
                and             (bt45.tenpo_shu = '1' or bt45.tenpo_shu = '2')
    -- ELSE
                and             bt45.tenpo_shu = /*dataShuKbn*/'1'
    /*END*/
/*END*/

/*IF taishoKikan.equals("DAY1")*/
                and             bt45.yosan_dt = /*kikanFrom*/'20060101' 
/*END*/
/*IF taishoKikan.equals("DAYS")*/ 
                and             bt45.yosan_dt between /*kikanFrom*/'20060101' and /*kikanTo*/'20060201'
/*END*/
/*IF !taishoTenpo.equals("ALL")*/
       /*IF taishoTenpo.equals("FC")*/
                and             bt45.fc_rc = '1'
       -- ELSE
                and             bt45.fc_rc = '2'
       /*END*/
/*END*/
                        
/*IF limitFlg == true*/
                and             sibu_cd in (select   bm51.sibu_cd
                                            from     bm51svsb as bm51
                                            where    bm51.sv_cd = /*userId*/'00000921'
                                            and      bm51.company_cd = /*companyCd*/'00'
                                            group by bm51.sibu_cd
                                            )

/*END*/
                group	 by        bt45.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
                ,               bt45.area_dai
-- ELSE
                ,               bt45.sibu_cd
/*END*/                 
                ) as bt45
on              (bm10.company_cd = bt45.company_cd and bm10.sibu_cd = bt45.sibu_cd )
where           bm10.company_cd = /*companyCd*/'00'
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
and             area_dai_flg = '1'
/*END*/
order by        bm10.company_cd
,               bm10.honbu_cd
,               bm10.jigyou_cd
,               bm10.slarea_cd
,               bm10.sibu_cd
