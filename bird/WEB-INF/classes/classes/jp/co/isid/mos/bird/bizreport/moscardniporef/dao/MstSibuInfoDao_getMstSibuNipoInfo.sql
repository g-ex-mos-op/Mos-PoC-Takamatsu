select          bm10.company_cd
,               bm10.honbu_cd
,               bm10.honbu_name
,               bm10.jigyou_cd
,               bm10.jigyou_name
,               bm10.slarea_cd
,               bm10.slarea_name
,               bm10.sibu_cd
,               bm10.sibu_name
,               (case when bd35.sibu_cd is null
                      then (case when bt45.sibu_cd is null then 'NOTDISP' 
                                 when bt45.yosan = 0      then 'NOTDISP'
                                 else 'DISPONLY' end)
                      else 'DISP'
                 end) as disp_kbn
from            bm10gsib as bm10
left join (
       select bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
       ,      bm01.area_dai as sibu_cd
-- ELSE
       ,      bm01.sibu_cd
/*END*/
       FROM  bm01tenm as bm01
       ,     (SELECT bd35.company_cd  , bd35.mise_cd 
              from   bd35znmc as bd35
              ,      bn01dten as bn01
/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH")*/ 
              where  bd35.eigyo_dt between /*kikanFrom*/'20060101' and /*kikanTo*/'20060201'
--ELSE
              where  bd35.eigyo_dt = /*kikanFrom*/'20060101'
/*END*/
              and    bd35.company_cd  = /*companyCd*/'00'
              and    bd35.oldm_flg  = '0'
              and    bd35.honbu_kbn = '0'
/*IF tenShu.equals("ALL")*/
              and    (bd35.open_kbn = 1 or bd35.dogetu_zen_open_kbn = 1) 
-- ELSE
              and    bd35.open_kbn = 1
/*END*/
/*IF !tenShu.equals("ALL")*/
    /*IF tenShu.equals("2")*/
              and    (bn01.kbn1 = '1' or bn01.kbn1 = '2')
    -- ELSE
              and    bn01.kbn1 = /*dataShuKbn*/'1'
    /*END*/
/*END*/
              and             bd35.eigyo_dt = bn01.eigyo_dt
              and             bd35.company_cd = bn01.company_cd
              and             bd35.mise_cd = bn01.mise_cd
              group by        bd35.company_cd, bd35.mise_cd) MISE35
          WHERE MISE35.COMPANY_CD = BM01.COMPANY_CD
          AND   MISE35.MISE_CD = BM01.MISE_CD
/*IF !taishoTenpo.equals("ALL")*/
       /*IF taishoTenpo.equals("FC")*/
                and             bm01.mise_kbn like '_1_'
       -- ELSE
                and             bm01.mise_kbn like '_2_'
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
          GROUP BY          bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
                ,               bm01.area_dai
-- ELSE
                ,               bm01.sibu_cd
/*END*/
                ) as bd35
on              (bm10.company_cd = bd35.company_cd and bm10.sibu_cd = bd35.sibu_cd)
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
/*IF taishoKikan.equals("DAYS")*/ 
                and             bt45.yosan_dt between /*kikanFrom*/'20060101' and /*kikanTo*/'20060201'
--ELSE
                and             bt45.yosan_dt = /*kikanFrom*/'20060101' 
/*END*/
/*IF !tenShu.equals("ALL")*/
    /*IF tenShu.equals("2")*/
                and             (bt45.tenpo_shu = '1' or bt45.tenpo_shu = '2')
    -- ELSE
                and             bt45.tenpo_shu = /*dataShuKbn*/'1'
    /*END*/
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
