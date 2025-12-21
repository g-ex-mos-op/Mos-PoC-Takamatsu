select bm08.company_cd,
       bm08.segment_type,
       bm08.segment_name,
    (case when open_kbn is null then 0 else open_kbn end) as open_kbn,
    (case when open_kbn_zen is null then 0 else open_kbn_zen end) as open_kbn_zen,
    (case when dogetu_uriage is null then 0 else dogetu_uriage end) as uriage,
    (case when dogetu_kyakusu is null then 0 else dogetu_kyakusu end) as kyakusu,
    (case when dogetu_zen_uriage is null then 0 else dogetu_zen_uriage end) as uriage_zen,
    (case when dogetu_zen_kyakusu is null then 0 else dogetu_zen_kyakusu end) as kyakusu_zen,
    (case when dogetu_issue_cnt is null then 0 else dogetu_issue_cnt end) as issue_cnt,
    (case when dogetu_charge_kin is null then 0 else dogetu_charge_kin end) as charge_kin,
    (case when dogetu_charge_ken is null then 0 else dogetu_charge_ken end) as charge_ken,
    (case when dogetu_kessai_kin is null then 0 else dogetu_kessai_kin end) as kessai_kin,
    (case when dogetu_kessai_ken is null then 0 else dogetu_kessai_ken end) as kessai_ken,
    (case when charge_kin_cancel is null then 0 else charge_kin_cancel end) as charge_kin_cancel,
    (case when charge_ken_cancel is null then 0 else charge_ken_cancel end) as charge_ken_cancel,
    (case when use_kin_cancel is null then 0 else use_kin_cancel end) as use_kin_cancel,
    (case when use_ken_cancel is null then 0 else use_ken_cancel end) as use_ken_cancel,
    (case when bonus_v_issue is null then 0 else bonus_v_issue end) as bonus_v_issue,
    (case when bonus_v_use is null then 0 else bonus_v_use end) as bonus_v_use,
    (case when coupon_v_issue is null then 0 else coupon_v_issue end) as coupon_v_issue,    
    (case when coupon_v_use is null then 0 else coupon_v_use end) as coupon_v_use,
    (case when zandaka is null then 0 else zandaka end) as zandaka,  
    (case when dogetu_zen_issue_cnt is null then 0 else dogetu_zen_issue_cnt end) as issue_cnt_zen,
    (case when dogetu_zen_charge_kin is null then 0 else dogetu_zen_charge_kin end) as charge_kin_zen,
    (case when dogetu_zen_charge_ken is null then 0 else dogetu_zen_charge_ken end) as charge_ken_zen,
    (case when dogetu_zen_kessai_kin is null then 0 else dogetu_zen_kessai_kin end) as kessai_kin_zen,
    (case when dogetu_zen_kessai_ken is null then 0 else dogetu_zen_kessai_ken end) as kessai_ken_zen,
    (case when sibu_yosan is null then 0 else sibu_yosan end) as sibu_yosan,
    (case when mise_count is null then 0 else mise_count end) as mise_count 
from bm08sgtp as bm08 
LEFT JOIN (
     select   sibu.segment_type
     ,        bt45.company_cd
     ,        sum(yosan) as sibu_yosan
     ,        DECIMAL(count(DISTINCT bt45.MISE_CD)) AS mise_count
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

     and      bt45.yosan_dt   between /*yosanDtFrom*/'20060525' and /*yosanDtTo*/'20060531' 
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
    ) as yosan  on (bm08.company_cd = yosan.company_cd
and bm08.segment_type = yosan.segment_type) 
    full outer join
    (select
        bm01.company_cd,
        bm09.segment_type,
        sum(bd36.open_kbn) as open_kbn,
        sum(bd36.open_kbn_zen) as open_kbn_zen,
        sum(bd36.dogetu_uriage) as dogetu_uriage,
        sum(bd36.dogetu_kyakusu) as dogetu_kyakusu,
        sum(bd36.dogetu_zen_uriage) as dogetu_zen_uriage,
        sum(bd36.dogetu_zen_kyakusu) as dogetu_zen_kyakusu,
        sum(bd36.dogetu_issue_cnt) as dogetu_issue_cnt,
        sum(bd36.dogetu_charge_kin) as dogetu_charge_kin,
        sum(bd36.dogetu_charge_ken) as dogetu_charge_ken,
        sum(bd36.dogetu_kessai_kin) as dogetu_kessai_kin,
        sum(bd36.dogetu_kessai_ken) as dogetu_kessai_ken,
        sum(bd36.charge_kin_cancel) as charge_kin_cancel,        
        sum(bd36.charge_ken_cancel) as charge_ken_cancel,
        sum(bd36.use_kin_cancel) as use_kin_cancel,
        sum(bd36.use_ken_cancel) as use_ken_cancel,
        sum(bd36.bonus_v_issue) as bonus_v_issue,
        sum(bd36.bonus_v_use) as bonus_v_use,
        sum(bd36.coupon_v_issue) as coupon_v_issue,
        sum(bd36.coupon_v_use) as coupon_v_use,
        sum(ZANDAKA) as zandaka,                                                    
        sum(bd36.dogetu_zen_issue_cnt) as dogetu_zen_issue_cnt,
        sum(bd36.dogetu_zen_charge_kin) as dogetu_zen_charge_kin,
        sum(bd36.dogetu_zen_charge_ken) as dogetu_zen_charge_ken,
        sum(bd36.dogetu_zen_kessai_kin) as dogetu_zen_kessai_kin,
        sum(bd36.dogetu_zen_kessai_ken) as dogetu_zen_kessai_ken
     from
        bm09gtsg as bm09
        join bm01tenm as bm01 on (bm09.gyotai_kbn = bm01.gyotai_kbn)
        join  
            (select
                 bd36.company_cd,
                 bd36.mise_cd,
                 max(bd36.open_kbn) as open_kbn,
                 MAX(BM45.OPEN_KBN_ZEN)as open_kbn_zen,
                 sum(bd36./*$taishoJoken*/'dogetu'_uriage) as dogetu_uriage,
                 sum(bd36./*$taishoJoken*/'dogetu'_kyakusu) as dogetu_kyakusu,
                 sum(bd36./*$taishoJoken*/'dogetu'_zen_uriage) as dogetu_zen_uriage,
                 sum(bd36./*$taishoJoken*/'dogetu'_zen_kyakusu) as dogetu_zen_kyakusu,
                 sum(bd36./*$taishoJoken*/'dogetu'_issue_cnt) as dogetu_issue_cnt,
                 sum(bd36./*$taishoJoken*/'dogetu'_charge_kin) as dogetu_charge_kin,
                 sum(bd36./*$taishoJoken*/'dogetu'_charge_ken) as dogetu_charge_ken,
                 sum(bd36./*$taishoJoken*/'dogetu'_kessai_kin) as dogetu_kessai_kin,
                 sum(bd36./*$taishoJoken*/'dogetu'_kessai_ken) as dogetu_kessai_ken,
                 sum(bd36./*$taishoJoken*/'dogetu'_charge_kin_cancel) as charge_kin_cancel,
                 sum(bd36./*$taishoJoken*/'dogetu'_charge_ken_cancel) as charge_ken_cancel,
                 sum(bd36./*$taishoJoken*/'dogetu'_use_kin_cancel) as use_kin_cancel,                                  
                 sum(bd36./*$taishoJoken*/'dogetu'_use_ken_cancel) as use_ken_cancel,
                 sum(bd36./*$taishoJoken*/'dogetu'_bonus_v_issue) as bonus_v_issue,
                 sum(bd36./*$taishoJoken*/'dogetu'_bonus_v_use) as bonus_v_use,                         
                 sum(bd36./*$taishoJoken*/'dogetu'_coupon_v_issue) as coupon_v_issue,
                 sum(bd36./*$taishoJoken*/'dogetu'_coupon_v_use) as coupon_v_use,            
                 sum(bd36./*$taishoJoken*/'dogetu'_zen_issue_cnt) as dogetu_zen_issue_cnt,
                 sum(bd36./*$taishoJoken*/'dogetu'_zen_charge_kin) as dogetu_zen_charge_kin,
                 sum(bd36./*$taishoJoken*/'dogetu'_zen_charge_ken) as dogetu_zen_charge_ken,
                 sum(bd36./*$taishoJoken*/'dogetu'_zen_kessai_kin) as dogetu_zen_kessai_kin,
                 sum(bd36./*$taishoJoken*/'dogetu'_zen_kessai_ken) as dogetu_zen_kessai_ken                 
	    ,       (CASE WHEN MAXDT.ZANDAKA IS NOT NULL THEN MAXDT.ZANDAKA ELSE 0 END) AS ZANDAKA
             from
                 bd36zgmc as bd36
			 	LEFT JOIN (
				     SELECT MAX3.EIGYO_DT
				     ,      MAX3.COMPANY_CD
				     ,      MAX3.MISE_CD
				     ,      MAX3./*$taishoJoken*/'DOGETU'_ZANDAKA AS ZANDAKA
				     FROM (SELECT MAX(BD3.EIGYO_DT) AS EIGYO_DT
				           ,      BD3.COMPANY_CD
				           ,      BD3.MISE_CD
				           FROM BD36ZGMC BD3
				/*IF taishoKikan.equals("KIBETU") */
				           WHERE BD3.EIGYO_DT between /*kikanFrom*/'201212' and /*kikanTo*/'201303'
				--ELSE
				           WHERE BD3.EIGYO_DT = /*kikanFrom*/'201303'
				/*END*/
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
				    ,        MAX3.COMPANY_CD
				    ,        MAX3.MISE_CD
				    ,        MAX3./*$taishoJoken*/'DOGETU'_ZANDAKA) MAXDT  
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
             /*IF taishoKikan.equals("KIBETU")*/         
				  AND     (bd36.eigyo_dt BETWEEN /*kikanFrom*/ AND /*kikanTo*/) 
             -- ELSE                 
				  AND     bd36.eigyo_dt = /*kikanFrom*/'200605'
             /*END*/    
             /*IF tenShu.equals("ALL")*/
                 and (bd36.open_kbn = 1 or BM45.OPEN_KBN_ZEN = 1) 
             -- ELSE
                 and bd36.open_kbn = 1
             /*END*/
             /*IF dataShu.equals("HOSEI")*/ 
                 and       (bd36.hosei_uriage > 0 and bd36.hosei_zen_uriage > 0)  
             /*END*/      
         /*IF !tenShu.equals("ALL")*/
                  /*IF tenShu.equals("2")*/
                 AND (BN01.kbn1 = '1' or BN01.kbn1 = '2')
                  -- ELSE
                 AND  BN01.kbn1 = /*tenShu*/'1'
             /*END*/
         /*END*/
             and bd36.oldm_flg = '0'
             and bd36.eigyo_dt = bn01.eigyo_ym
             and bd36.company_cd = bn01.company_cd 
             and bd36.mise_cd = bn01.mise_cd 
             group by bd36.company_cd, bd36.mise_cd
	         ,       (CASE WHEN MAXDT.ZANDAKA IS NOT NULL THEN MAXDT.ZANDAKA ELSE 0 END)
        ) as bd36 on (bd36.company_cd = bm01.company_cd and bd36.mise_cd = bm01.mise_cd)
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
    and yosan.segment_type = seg.segment_type)
where bm08.segment_type in /*segmentList*/('M','J')
order by bm08.sort_seq