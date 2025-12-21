select case when CARDINFO.company_cd is null then bt44.company_cd else CARDINFO.company_cd end as company_cd,
       case when CARDINFO.sibu_cd is null then bt44.sibu_cd else CARDINFO.sibu_cd end as sibu_cd,
       case when CARDINFO.uriage is null then 0 else CARDINFO.uriage end as uriage,
       case when CARDINFO.kyakusu is null then 0 else CARDINFO.kyakusu end as kyakusu,
       case when CARDINFO.open_kbn is null then 0 else CARDINFO.open_kbn end as open_kbn,
       case when CARDINFO.open_kbn_zen is null then 0 else CARDINFO.open_kbn_zen end as open_kbn_zen,
       case when CARDINFO.zen_uriage is null then 0 else CARDINFO.zen_uriage end as uriage_zen,
       case when CARDINFO.zen_kyakusu is null then 0 else CARDINFO.zen_kyakusu end as kyakusu_zen,
       case when CARDINFO.issue_Cnt is null then 0 else CARDINFO.issue_Cnt end as issue_Cnt,
       case when CARDINFO.charge_Kin is null then 0 else CARDINFO.charge_Kin end as charge_Kin,
       case when CARDINFO.charge_Ken is null then 0 else CARDINFO.charge_Ken end as charge_Ken,
       case when CARDINFO.kessai_Kin is null then 0 else CARDINFO.kessai_Kin end as kessai_Kin,
       case when CARDINFO.kessai_Ken is null then 0 else CARDINFO.kessai_Ken end as kessai_Ken,
       case when CARDINFO.charge_kin_cancel is null then 0 else CARDINFO.charge_kin_cancel end as charge_kin_cancel,
       case when CARDINFO.charge_ken_cancel is null then 0 else CARDINFO.charge_ken_cancel end as charge_ken_cancel,
       case when CARDINFO.use_kin_cancel is null then 0 else CARDINFO.use_kin_cancel end as use_kin_cancel,
       case when CARDINFO.use_ken_cancel is null then 0 else CARDINFO.use_ken_cancel end as use_ken_cancel,
       case when CARDINFO.bonus_v_issue is null then 0 else CARDINFO.bonus_v_issue end as bonus_v_issue,
       case when CARDINFO.bonus_v_use is null then 0 else CARDINFO.bonus_v_use end as bonus_v_use,
       case when CARDINFO.coupon_v_issue is null then 0 else CARDINFO.coupon_v_issue end as coupon_v_issue,    
       case when CARDINFO.coupon_v_use is null then 0 else CARDINFO.coupon_v_use end as coupon_v_use,
       case when CARDINFO.zandaka is null then 0 else CARDINFO.zandaka end as zandaka,       
       case when CARDINFO.zen_Issue_Cnt is null then 0 else CARDINFO.zen_Issue_Cnt end as zen_Issue_Cnt,
       case when CARDINFO.zen_Charge_Kin is null then 0 else CARDINFO.zen_Charge_Kin end as zen_Charge_Kin,
       case when CARDINFO.zen_Charge_Ken is null then 0 else CARDINFO.zen_Charge_Ken end as zen_Charge_Ken,
       case when CARDINFO.zen_Kessai_Kin is null then 0 else CARDINFO.zen_Kessai_Kin end as zen_Kessai_Kin,
       case when CARDINFO.zen_Kessai_Ken is null then 0 else CARDINFO.zen_Kessai_Ken end as zen_Kessai_Ken,
       case when bt44.yosan is null then 0 else bt44.yosan end as SIBU_YOSAN,    
       case when bt44.MISE_CNT is null then 0 else bt44.MISE_CNT end as MISE_CNT
       from (SELECT bt45.COMPANY_CD , 
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
                    bt45.area_dai as sibu_cd ,
-- ELSE
                    bt45.sibu_cd ,
/*END*/
                    SUM(bt45.YOSAN) YOSAN  ,
                    DECIMAL(count(DISTINCT bt45.MISE_CD)) AS MISE_CNT
             from  bt45dsjy as bt45
                 , bm10gsib as bm10
             where bt45.company_cd = /*companyCd*/'00'
             and   bt45.YOSAN <> 0
             and   bt45.company_cd = bm10.company_cd
             /*IF areaDaiFlg.equals("AREA_DAI_CD")*/
                 and   bm10.sibu_cd = bt45.area_dai
                 and   bm10.area_dai_flg = '1'     
             -- ELSE
                 and   bm10.sibu_cd = bt45.sibu_cd
             /*END*/
                 and yosan_dt between /*yosanDtFrom*/'20060525' and /*yosanDtTo*/'20060531'    
             /*IF limitFlg == true*/
                 and bt45.sibu_cd in (
                     SELECT   BM51.SIBU_CD
                     FROM     BM51SVSB BM51
                     WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
                     AND    BM51.SV_CD      = /*userId*/'9999000a'
                     GROUP BY BM51.SIBU_CD
                     )
            /*END*/                           
            /*IF !tenShu.equals("ALL")*/
                /*IF tenShu.equals("2")*/
                    and  (tenpo_shu = '1' or tenpo_shu = '2')
                -- ELSE
                    and  tenpo_shu = /*dataShuKbn*/'1'
                /*END*/
            /*END*/

            /*IF !taishoTenpo.equals("ALL")*/
                /*IF taishoTenpo.equals("FC")*/
                    and  fc_rc = '1'
                -- ELSE
                    and  fc_rc = '2'
               /*END*/
            /*END*/             
             
             GROUP BY bt45.COMPANY_CD, 
            /*IF areaDaiFlg.equals("AREA_DAI_CD")*/
                      bt45.area_dai
            -- ELSE
                      bt45.SIBU_CD
            /*END*/       
             ) as bt44 full outer join
             (select      bm01.company_cd
                    /*IF areaDaiFlg.equals("AREA_DAI_CD")*/
                ,         bm01.area_dai as sibu_cd
                    -- ELSE
                ,         bm01.sibu_cd
                   /*END*/
                ,         sum(uriage)                as uriage
                ,         sum(kyakusu)               as kyakusu
                ,         sum(open_kbn)              as open_kbn
                ,         sum(open_kbn_zen)   as open_kbn_zen
                ,         sum(zen_uriage)     as zen_uriage
                ,         sum(zen_kyakusu)    as zen_kyakusu
                ,         sum(issue_Cnt)             as issue_Cnt
                ,         sum(charge_Kin)            as charge_Kin
                ,         sum(charge_Ken)            as charge_Ken
                ,         sum(kessai_Kin)            as kessai_Kin
                ,         sum(kessai_Ken)            as kessai_Ken
                ,         sum(charge_kin_cancel)     as charge_kin_cancel
                ,         sum(charge_ken_cancel)     as charge_ken_cancel
                ,         sum(use_kin_cancel)        as use_kin_cancel
                ,         sum(use_ken_cancel)        as use_ken_cancel
                ,         sum(bonus_v_issue)         as bonus_v_issue
                ,         sum(bonus_v_use)           as bonus_v_use
                ,         sum(coupon_v_issue)        as coupon_v_issue
                ,         sum(coupon_v_use)          as coupon_v_use
                ,         sum(ZANDAKA)         as zandaka                    
                ,         sum(zen_Issue_Cnt)  as zen_Issue_Cnt
                ,         sum(zen_Charge_Kin) as zen_Charge_Kin
                ,         sum(zen_Charge_Ken) as zen_Charge_Ken
                ,         sum(zen_Kessai_Kin) as zen_Kessai_Kin
                ,         sum(zen_Kessai_Ken) as zen_Kessai_Ken             
                from      bm01tenm as bm01
                     ,     (select bd36.company_cd
                     ,      bd36.mise_cd 
                     ,      max(bd36.open_kbn) as open_kbn
                     ,      MAX(BM45.OPEN_KBN_ZEN)as open_kbn_zen
                     ,      sum(/*$taishoJoken*/'dogetu'_uriage) as uriage
                     ,      sum(/*$taishoJoken*/'dogetu'_kyakusu) as kyakusu
                     ,      sum(/*$taishoJoken*/'dogetu'_zen_uriage)     as zen_uriage
                     ,      sum(/*$taishoJoken*/'dogetu'_zen_kyakusu)    as zen_kyakusu
                     ,      sum(/*$taishoJoken*/'dogetu'_issue_Cnt)      as issue_Cnt
                     ,      sum(/*$taishoJoken*/'dogetu'_charge_Kin)     as charge_Kin
                     ,      sum(/*$taishoJoken*/'dogetu'_charge_Ken)     as charge_Ken
                     ,      sum(/*$taishoJoken*/'dogetu'_kessai_Kin)     as kessai_Kin
                     ,      sum(/*$taishoJoken*/'dogetu'_kessai_Ken)     as kessai_Ken
                     ,      sum(/*$taishoJoken*/'dogetu'_charge_kin_cancel) as charge_kin_cancel
                     ,      sum(/*$taishoJoken*/'dogetu'_charge_ken_cancel) as charge_ken_cancel
                     ,      sum(/*$taishoJoken*/'dogetu'_use_kin_cancel) as use_kin_cancel                                
                     ,      sum(/*$taishoJoken*/'dogetu'_use_ken_cancel) as use_ken_cancel
                     ,      sum(/*$taishoJoken*/'dogetu'_bonus_v_issue) as bonus_v_issue
                     ,      sum(/*$taishoJoken*/'dogetu'_bonus_v_use) as bonus_v_use                   
                     ,      sum(/*$taishoJoken*/'dogetu'_coupon_v_issue) as coupon_v_issue
                     ,      sum(/*$taishoJoken*/'dogetu'_coupon_v_use) as coupon_v_use                     
                     ,      sum(/*$taishoJoken*/'dogetu'_Zen_Issue_Cnt)  as zen_Issue_Cnt
                     ,      sum(/*$taishoJoken*/'dogetu'_Zen_Charge_Kin) as zen_Charge_Kin
                     ,      sum(/*$taishoJoken*/'dogetu'_Zen_Charge_Ken) as zen_Charge_Ken
                     ,      sum(/*$taishoJoken*/'dogetu'_Zen_Kessai_Kin) as zen_Kessai_Kin
                     ,      sum(/*$taishoJoken*/'dogetu'_Zen_Kessai_Ken) as zen_Kessai_Ken
                     ,      CASE WHEN MAXDT.ZANDAKA IS NULL THEN 0 ELSE MAXDT.ZANDAKA END ZANDAKA
                 from   bd36zgmc as bd36
			  	LEFT JOIN (
				     SELECT MAX3.EIGYO_DT
				     ,      MAX3.COMPANY_CD
				     ,      MAX3.MISE_CD
				     ,      MAX3./*$taishoJoken*/'DOGETU'_ZANDAKA AS ZANDAKA
				     FROM (SELECT MAX(BD3.EIGYO_DT) AS EIGYO_DT
				           ,      BD3.COMPANY_CD
				           ,      BD3.MISE_CD
				           FROM BD36ZGMC BD3
				           WHERE BD3.EIGYO_DT = /*kikanFrom*/'201303'
				           AND   BD3.COMPANY_CD = /*companyCd*/'00'
				           AND   BD3.OPEN_KBN   =1
				           AND   BD3.oldm_flg   = '0'
				           GROUP BY BD3.COMPANY_CD
				           ,      BD3.MISE_CD
				           ) GETMT
				    ,    BD36ZGMC MAX3
				    WHERE MAX3.EIGYO_DT   = GETMT.EIGYO_DT
				    AND   MAX3.COMPANY_CD = GETMT.COMPANY_CD
				    AND   MAX3.MISE_CD    = GETMT.MISE_CD
				    GROUP BY MAX3.EIGYO_DT
				     ,      MAX3.COMPANY_CD
				     ,      MAX3.MISE_CD
				     ,      MAX3./*$taishoJoken*/'DOGETU'_ZANDAKA) MAXDT  
					ON (    MAXDT.COMPANY_CD = bd36.COMPANY_CD
			            AND MAXDT.MISE_CD    = bd36.MISE_CD)
				  ,       (
	                   SELECT BN01.EIGYO_YM, BN01.COMPANY_CD, BN01.MISE_CD, BN01.KBN1
	                  FROM   BN01DTEN BN01
	                  GROUP BY BN01.EIGYO_YM, BN01.COMPANY_CD, BN01.MISE_CD, BN01.KBN1
	                ) as BN01
				  ,       (
	                  SELECT SUBSTR(EIGYO_DT, 1, 6) AS EIGYO_YM
	                  ,      COMPANY_CD, MISE_CD, OPEN_KBN
	         /*IF dataShu.equals("HOSEI")*/ 
	                  , MAX(OP_KBN_ZEN_DOGETU) AS OPEN_KBN_ZEN
	         --ELSE
	                  , MAX(OP_KBN_ZEN_/*$taishoJoken*/'DOGETU') AS OPEN_KBN_ZEN
	         /*END*/
	                  FROM   BM45ZDAY
	                  GROUP BY SUBSTR(EIGYO_DT, 1, 6)
	                  ,        COMPANY_CD, MISE_CD, OPEN_KBN
	                ) as BM45
				  WHERE   BD36.COMPANY_CD = /*companyCd*/'00'
				  AND     BM45.EIGYO_YM   = BN01.EIGYO_YM
				  AND     BD36.EIGYO_DT   = BM45.EIGYO_YM
				  AND     BM45.COMPANY_CD = bn01.COMPANY_CD
				  AND     BD36.COMPANY_CD = BM45.COMPANY_CD
				  AND     BM45.MISE_CD    = bn01.MISE_CD
				  AND     BD36.MISE_CD    = BM45.MISE_CD
				  AND     BD36.OPEN_KBN   = BM45.OPEN_KBN

                 and bd36.eigyo_dt = /*kikanFrom*/
                 and    bd36.oldm_flg = '0' 
                 
                /*IF tenShu.equals("ALL")*/
                and     (bd36.open_kbn = 1 or BM45.OPEN_KBN_ZEN = 1) 
                -- ELSE
                and     bd36.open_kbn = 1
                /*END*/

                /*IF !tenShu.equals("ALL")*/
                     /*IF tenShu.equals("2")*/
                     and             (bn01.kbn1 = '1' or bn01.kbn1 = '2')
                     -- ELSE
                     and             bn01.kbn1 = /*dataShuKbn*/'1'
                    /*END*/
                /*END*/
                /*IF dataShu.equals("HOSEI")*/ 
                    and       (bd36.hosei_uriage > 0 and bd36.hosei_zen_uriage > 0)  
                /*END*/                    
                group by bd36.company_cd
                ,        bd36.mise_cd
                ,      CASE WHEN MAXDT.ZANDAKA IS NULL THEN 0 ELSE MAXDT.ZANDAKA END
                ) as bd36
where           bd36.company_cd = bm01.company_cd
and             bd36.mise_cd = bm01.mise_cd

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

and             bd36.company_cd = /*companyCd*/'00'
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
) CARDINFO

on bt44.company_cd = CARDINFO.company_cd 
and bt44.sibu_cd = CARDINFO.sibu_cd
