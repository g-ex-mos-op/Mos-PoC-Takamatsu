select          bm10.company_cd
,               bm10.honbu_cd
,               bm10.honbu_name
,               bm10.jigyou_cd
,               bm10.jigyou_name
,               bm10.slarea_cd
,               bm10.slarea_name
,               bm10.sibu_cd
,               bm10.sibu_name
,               (case when bt53.sibu_cd is null
                      then (case when bt44.sibu_cd is null then 'NOTDISP' 
                                 when bt44.yosan = 0      then 'NOTDISP'
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
                from            BD53NGEP as bt53
                ,               bm01tenm as bm01
                ,               (select   company_cd
                                 ,         mise_cd
                                 ,         eigyo_ym
                                 ,         kbn1
                                 from      bn01dten    
                                 where 
                                 /*IF taishoKikan.equals("KIBETU")*/ 
                                           substr(eigyo_dt,1,6) between /*kikanFrom*/'200601' and /*kikanTo*/'200602'
                                 -- ELSE
                                           eigyo_ym = /*kikanFrom*/'200601'
                                 /*END*/
                                 and       company_cd=/*companyCd*/'00'
                                 group by  company_cd
                                 ,         mise_cd
                                 ,         eigyo_ym
                                 ,         kbn1
                                ) as bn01
                where           bt53.company_cd = bm01.company_cd
                and             bm01.company_cd = bn01.company_cd
                and             bt53.mise_cd = bm01.mise_cd
                and             bm01.mise_cd = bn01.mise_cd
                and             bt53.eigyo_dt = bn01.eigyo_ym

/*IF !taishoTenpo.equals("ALL")*/
       /*IF taishoTenpo.equals("FC")*/
                and             bm01.mise_kbn like '_1_'
       -- ELSE
                and             bm01.mise_kbn like '_2_'
       /*END*/
/*END*/

/*IF tenShu.equals("ALL")*/
                and             (bt53.open_kbn = 1 or bt53.open_kbn_zen = 1) 
-- ELSE
                and             bt53.open_kbn = 1
/*END*/

/*IF !tenShu.equals("ALL")*/
    /*IF tenShu.equals("2")*/
                and             (bn01.kbn1 = '1' or bn01.kbn1 = '2')
    -- ELSE
                and             bn01.kbn1 = /*dataShuKbn*/'1'
    /*END*/
/*END*/
                and             bt53.oldm_flg = '0'

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

                ) as bt53
on              (bm10.company_cd = bt53.company_cd and bm10.sibu_cd = bt53.sibu_cd)
left join       (
                select          bt44.company_cd

/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
                ,               bt44.area_dai as sibu_cd
-- ELSE
                ,               bt44.sibu_cd
/*END*/
                ,               sum(yosan) as yosan                 
                from            bt44msjy as bt44
                where           

               /*IF taishoKikan.equals("KIBETU")*/ 
                                bt44.yosan_dt between /*kikanFrom*/'200601' and /*kikanTo*/'200602'
               -- ELSE
                                bt44.yosan_dt = /*kikanFrom*/'200601'
               /*END*/
                and             bt44.company_cd = /*companyCd*/'00'
/*IF !tenShu.equals("ALL")*/
    /*IF tenShu.equals("2")*/
                and             (bt44.tenpo_shu = '1' or bt44.tenpo_shu = '2')
    -- ELSE
                and             bt44.tenpo_shu = /*dataShuKbn*/'1'
    /*END*/
/*END*/
/*IF !taishoTenpo.equals("ALL")*/
       /*IF taishoTenpo.equals("FC")*/
                and             bt44.fc_rc = '1'
       -- ELSE
                and             bt44.fc_rc = '2'
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

                group by        bt44.company_cd

/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
                ,               bt44.area_dai
-- ELSE
                ,               bt44.sibu_cd
/*END*/

                ) as bt44
on              (bm10.company_cd = bt44.company_cd and bm10.sibu_cd = bt44.sibu_cd)
where           bm10.company_cd = /*companyCd*/'00'

/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
and             area_dai_flg = '1'
/*END*/
order by        bm10.company_cd
,               bm10.honbu_cd
,               bm10.jigyou_cd
,               bm10.slarea_cd
,               bm10.sibu_cd
