select
    bm08.company_cd,
    bm08.segment_type,
    bm08.segment_name,
    (case when open_kbn is null then 0 else open_kbn end) as open_kbn,
    (case when zen_open_kbn is null then 0 else zen_open_kbn end) as open_kbn_zen,
    (case when uriage is null then 0 else uriage end) as uriage,
    (case when kyakusu is null then 0 else kyakusu end) as kyakusu,
    (case when zen_uriage is null then 0 else zen_uriage end) as uriage_zen,
    (case when zen_kyakusu is null then 0 else zen_kyakusu end) as kyakusu_zen,
    (case when issue_Cnt is null then 0 else issue_Cnt end) as issue_Cnt,
    (case when charge_Kin is null then 0 else charge_Kin end) as charge_Kin,
    (case when charge_Ken is null then 0 else charge_Ken end) as charge_Ken,
    (case when kessai_Kin is null then 0 else kessai_Kin end) as kessai_Kin,
    (case when kessai_Ken is null then 0 else kessai_Ken end) as kessai_Ken,
    (case when charge_kin_cancel is null then 0 else charge_kin_cancel end) as charge_kin_cancel,
    (case when charge_ken_cancel is null then 0 else charge_ken_cancel end) as charge_ken_cancel,
    (case when use_kin_cancel is null then 0 else use_kin_cancel end) as use_kin_cancel,
    (case when use_ken_cancel is null then 0 else use_ken_cancel end) as use_ken_cancel,
    (case when bonus_v_issue is null then 0 else bonus_v_issue end) as bonus_v_issue,
    (case when bonus_v_use is null then 0 else bonus_v_use end) as bonus_v_use,
    (case when coupon_v_issue is null then 0 else coupon_v_issue end) as coupon_v_issue,
    (case when coupon_v_use is null then 0 else coupon_v_use end) as coupon_v_use,
    (case when zandaka is null then 0 else zandaka end) as zandaka,
    (case when zen_Issue_Cnt is null then 0 else zen_Issue_Cnt end) as issue_cnt_zen,
    (case when zen_Charge_Kin is null then 0 else zen_Charge_Kin end) as charge_kin_zen,
    (case when zen_Charge_Ken is null then 0 else zen_Charge_Ken end) as charge_ken_zen,
    (case when zen_Kessai_Kin is null then 0 else zen_Kessai_Kin end) as kessai_kin_zen,
    (case when zen_Kessai_Ken is null then 0 else zen_Kessai_Ken end) as kessai_ken_zen,
    (case when yosan is null then 0 else yosan end) as sibu_yosan,    
    (case when MISE_CNT is null then 0 else MISE_CNT end) as MISE_COUNT
from  bm08sgtp as bm08 
left join (
     select   sibu.segment_type
     ,        bt45.company_cd
     ,        sum(yosan) as yosan
     ,        DECIMAL(count(DISTINCT bt45.MISE_CD)) AS MISE_CNT
     from     bt45dsjy as bt45
     ,        ( select    segment_type
                ,         sibu_cd
                from      bm09gtsg as bm09
                ,         bm01tenm as bm01
                where     bm09.gyotai_kbn = bm01.gyotai_kbn
                and       bm09.segment_type in /*segmentList*/('M', 'J')
            /*IF !taishoTenpo.equals("ALL")*/
                /*IF taishoTenpo.equals("FC")*/
                and       bm01.mise_kbn like '_1_'
                -- ELSE
                and       bm01.mise_kbn like '_2_'
                /*END*/
            /*END*/                
                group by  segment_type
                ,         sibu_cd
              ) as sibu
     where    bt45.sibu_cd = sibu.sibu_cd
     AND      bt45.YOSAN <> 0
     /*IF taishoKikan.equals("DAYS")*/
         and      bt45.yosan_dt   between /*kikanFrom*/'20060525' and /*kikanTo*/'20060531' 
     -- ELSE
         and      bt45.yosan_dt   = /*kikanFrom*/'20130109'
     /*END*/    
	 /*IF !tenShu.equals("ALL")*/
	     /*IF tenShu.equals("2")*/
	      and      (bt45.tenpo_shu = '1' or bt45.tenpo_shu = '2')
	     -- ELSE
	      and       bt45.tenpo_shu = /*tenShu*/'1 '
	     /*END*/
	 /*END*/     
     /*IF limitFlg == true*/
     and             bt45.sibu_cd in (
          SELECT   BM51.SIBU_CD
          FROM     BM51SVSB BM51
          WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
          AND    BM51.SV_CD      = /*userId*/'9999000a'
          GROUP BY BM51.SIBU_CD
     )
     /*END*/	 
     and      bt45.company_cd = /*companyCd*/'00'
     group by sibu.segment_type
     ,        bt45.company_cd
    ) as yosan on (bm08.company_cd = yosan.company_cd
and bm08.segment_type = yosan.segment_type) 
full outer join
    (select
        bm01.company_cd,
        bm09.segment_type,
        sum(open_kbn)              as open_kbn,
        sum(zen_open_kbn)          as zen_open_kbn,
        sum(uriage)                as uriage,
        sum(kyakusu)               as kyakusu,
        sum(zen_uriage)            as zen_uriage,
        sum(zen_kyakusu)           as zen_kyakusu,
        sum(issue_Cnt)             as issue_Cnt,
        sum(charge_Kin)            as charge_Kin,
        sum(charge_Ken)            as charge_Ken,
        sum(kessai_Kin)            as kessai_Kin,
        sum(kessai_Ken)            as kessai_Ken,
        sum(charge_kin_cancel)     as charge_kin_cancel,
        sum(charge_ken_cancel)     as charge_ken_cancel,
        sum(use_kin_cancel)        as use_kin_cancel,
        sum(use_ken_cancel)        as use_ken_cancel,
        sum(bonus_v_issue)         as bonus_v_issue,
        sum(bonus_v_use)           as bonus_v_use,
        sum(coupon_v_issue)        as coupon_v_issue,
        sum(coupon_v_use)          as coupon_v_use,
        sum(ZANDAKA)         as zandaka,        
        sum(Zen_Issue_Cnt)         as zen_Issue_Cnt,
        sum(Zen_Charge_Kin)        as zen_Charge_Kin,
        sum(Zen_Charge_Ken)        as zen_Charge_Ken,
        sum(Zen_Kessai_Kin)        as zen_Kessai_Kin,
        sum(Zen_Kessai_Ken)        as zen_Kessai_Ken
     from
        bm09gtsg as bm09
        join bm01tenm as bm01 on (bm09.gyotai_kbn = bm01.gyotai_kbn)
        join  
            (select
                 bd35.company_cd,
                 bd35.mise_cd,
                 max(open_kbn)              as open_kbn,
                 max(/*$taishoJoken*/'dogetu'_zen_open_kbn)   as zen_open_kbn,
                 sum(uriage)                as uriage,
                 sum(kyakusu)               as kyakusu,
                 sum(/*$taishoJoken*/'dogetu'_zen_uriage)     as zen_uriage,
                 sum(/*$taishoJoken*/'dogetu'_zen_kyakusu)    as zen_kyakusu,
                 sum(issue_Cnt)             as issue_Cnt,
                 sum(charge_Kin)            as charge_Kin,
                 sum(charge_Ken)            as charge_Ken,
                 sum(kessai_Kin)            as kessai_Kin,
                 sum(kessai_Ken)            as kessai_Ken,
                 sum(charge_kin_cancel)     as charge_kin_cancel,
                 sum(charge_ken_cancel)     as charge_ken_cancel,
                 sum(use_kin_cancel)        as use_kin_cancel,
                 sum(use_ken_cancel)        as use_ken_cancel,
                 sum(bonus_v_issue)         as bonus_v_issue,
                 sum(bonus_v_use)           as bonus_v_use,
                 sum(coupon_v_issue)        as coupon_v_issue,
                 sum(coupon_v_use)          as coupon_v_use,
                 sum(/*$taishoJoken*/'dogetu'_Zen_Issue_Cnt)  as zen_Issue_Cnt,
                 sum(/*$taishoJoken*/'dogetu'_Zen_Charge_Kin) as zen_Charge_Kin,
                 sum(/*$taishoJoken*/'dogetu'_Zen_Charge_Ken) as zen_Charge_Ken,
                 sum(/*$taishoJoken*/'dogetu'_Zen_Kessai_Kin) as zen_Kessai_Kin,
                 sum(/*$taishoJoken*/'dogetu'_Zen_Kessai_Ken) as zen_Kessai_Ken
	        ,       (CASE WHEN MAXDT.ZANDAKA IS NOT NULL THEN MAXDT.ZANDAKA ELSE 0 END) AS ZANDAKA
            from bd35znmc as bd35
			LEFT JOIN  (
			     SELECT MAX3.EIGYO_DT
			     ,      MAX3.COMPANY_CD
			     ,      MAX3.MISE_CD
			     ,      MAX3.ZANDAKA
			     FROM (SELECT MAX(BD3.EIGYO_DT) AS EIGYO_DT
			           ,      BD3.COMPANY_CD
			           ,      BD3.MISE_CD
			           FROM BD35ZNMC BD3
				/*IF taishoKikan.equals("DAYS")*/
			           WHERE BD3.EIGYO_DT between /*kikanFrom*/'20060530' and /*kikanTo*/'20060601'
				--ELSE
				       WHERE BD3.EIGYO_DT = /*kikanFrom*/'201303'
				/*END*/
				       AND   BD3.COMPANY_CD = /*companyCd*/'00'
				       AND   BD3.OPEN_KBN   =1
				       AND   BD3.oldm_flg   = '0'
				       GROUP BY BD3.COMPANY_CD
				       ,      BD3.MISE_CD
				       ) GETMT
				,    BD35ZNMC MAX3
				WHERE MAX3.EIGYO_DT   = GETMT.EIGYO_DT
				AND   MAX3.COMPANY_CD = GETMT.COMPANY_CD
				AND   MAX3.MISE_CD    = GETMT.MISE_CD
				GROUP BY MAX3.EIGYO_DT
				,        MAX3.COMPANY_CD
				,        MAX3.MISE_CD
				,        MAX3.ZANDAKA
				) MAXDT  
				ON (    MAXDT.COMPANY_CD = BD35.COMPANY_CD
		                  AND MAXDT.MISE_CD    = BD35.MISE_CD)
             , bn01dten as bn01
             where
            /*IF taishoKikan.equals("DAYS")*/
               bd35.eigyo_dt between /*kikanFrom*/'20060525' and /*kikanTo*/'20060531' 
            -- ELSE
               bd35.eigyo_dt = /*kikanFrom*/
           /*END*/
           /*IF tenShu.equals("ALL")*/
               and (bd35.open_kbn = 1 or /*$taishoJoken*/'dogetu'_zen_open_kbn = 1) 
           -- ELSE
               and bd35.open_kbn = 1
           /*END*/

           /*IF dataShu.equals("HOSEI")*/
               and    (bd35.uriage > 0 and /*$taishoJoken*/'dogetu'_zen_uriage > 0)
           /*END*/
               and bd35.oldm_flg = '0'
           /*IF !tenShu.equals("ALL")*/
               /*IF tenShu.equals("2")*/
           	    and (bn01.kbn1 = '1' or bn01.kbn1 = '2')
               -- ELSE
                and bn01.kbn1 = /*tenShu*/'1'
               /*END*/
           /*END*/
           	   and bd35.honbu_kbn = '0'
           	   and bd35.eigyo_dt = bn01.eigyo_dt
           	   and bd35.company_cd = bn01.company_cd 
           	   and bd35.mise_cd = bn01.mise_cd 
           group by bd35.company_cd, bd35.mise_cd
           , (CASE WHEN MAXDT.ZANDAKA IS NOT NULL THEN MAXDT.ZANDAKA ELSE 0 END)
           ) as bd35 on (bd35.company_cd = bm01.company_cd and bd35.mise_cd = bm01.mise_cd)
           where
           bm01.company_cd =/*companyCd*/'00'
           and bm09.segment_type in /*segmentList*/('M','J')
           /*IF !taishoTenpo.equals("ALL")*/
              /*IF taishoTenpo.equals("FC")*/
              and bm01.mise_kbn like '_1_'
              -- ELSE
              and bm01.mise_kbn like '_2_'
              /*END*/
          /*END*/
              /*IF limitFlg == true */
                  AND bm01.sibu_cd IN (
                      SELECT   BM51.SIBU_CD
                      FROM     BM51SVSB BM51
                      WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
                      AND    BM51.SV_CD      = /*userId*/'9999000a'
                      GROUP BY BM51.SIBU_CD
                  )
              /*END*/
         group by bm01.company_cd, bm09.segment_type
     ) as seg 
on (yosan.company_cd = seg.company_cd
     and yosan.segment_type = seg.segment_type )
where   bm08.segment_type in /*segmentList*/('M','J')
order by bm08.sort_seq