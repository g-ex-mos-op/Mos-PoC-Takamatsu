select          bm10.company_cd
,               bm10.honbu_cd
,               bm10.honbu_name
,               bm10.jigyou_cd
,               bm10.jigyou_name
,               bm10.slarea_cd
,               bm10.slarea_name
,               bm10.sibu_cd
,               bm10.sibu_name
,               (case when bd36.sibu_cd is null
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
                from    bd36zgmc as bd36
                ,       bm01tenm as bm01
				,       (
			          SELECT BN01.EIGYO_YM, BN01.COMPANY_CD, BN01.MISE_CD, BN01.KBN1
			          FROM   BN01DTEN BN01
			          GROUP BY BN01.EIGYO_YM, BN01.COMPANY_CD, BN01.MISE_CD, BN01.KBN1
			        ) as BN01
				,       (
			          SELECT SUBSTR(EIGYO_DT, 1, 6) AS EIGYO_YM
			          ,      COMPANY_CD, MISE_CD, OPEN_KBN
			          , MAX(OP_KBN_ZEN_DOGETU) AS OPEN_KBN_ZEN
			          FROM   BM45ZDAY
			          GROUP BY SUBSTR(EIGYO_DT, 1, 6)
			          ,        COMPANY_CD, MISE_CD, OPEN_KBN
			        ) as BM45
               /*IF taishoKikan.equals("KIBETU")*/ 
                WHERE  bd36.eigyo_dt between /*kikanFrom*/'200601' and /*kikanTo*/'200602'
               -- ELSE
                WHERE  bd36.eigyo_dt = /*kikanFrom*/'200601'
               /*END*/
                AND    BM01.COMPANY_CD = /*companyCd*/'00'
                and    BM45.EIGYO_YM   = BN01.EIGYO_YM
                and    bd36.eigyo_dt   = BM45.EIGYO_YM
                and    bn01.COMPANY_CD = bm01.COMPANY_CD
                and    BM45.COMPANY_CD = bn01.COMPANY_CD
                and    bd36.COMPANY_CD = BM45.COMPANY_CD
                and    bn01.MISE_CD    = bm01.MISE_CD
                and    BM45.MISE_CD    = bn01.MISE_CD
                and    bd36.MISE_CD    = BM45.MISE_CD
                AND    bd36.OPEN_KBN   = BM45.OPEN_KBN

/*IF !taishoTenpo.equals("ALL")*/
       /*IF taishoTenpo.equals("FC")*/
                and             bm01.mise_kbn like '_1_'
       -- ELSE
                and             bm01.mise_kbn like '_2_'
       /*END*/
/*END*/

/*IF tenShu.equals("ALL")*/
                and             (bd36.open_kbn = 1 or BM45.OPEN_KBN_ZEN = 1) 
-- ELSE
                and             bd36.open_kbn = 1
/*END*/

/*IF !tenShu.equals("ALL")*/
    /*IF tenShu.equals("2")*/
                and             (bn01.kbn1 = '1' or bn01.kbn1 = '2')
    -- ELSE
                and             bn01.kbn1 = /*dataShuKbn*/'1'
    /*END*/
/*END*/
                and             bd36.oldm_flg = '0'

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

                ) as bd36
on              (bm10.company_cd = bd36.company_cd and bm10.sibu_cd = bd36.sibu_cd)
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
