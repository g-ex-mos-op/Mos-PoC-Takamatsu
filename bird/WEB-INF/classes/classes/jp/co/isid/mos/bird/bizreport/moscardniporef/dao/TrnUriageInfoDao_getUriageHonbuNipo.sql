 select    bd35.company_cd 
    ,      bd35.mise_cd 
    ,      (CASE WHEN MAXDT.ZANDAKA IS NULL THEN 0 ELSE MAXDT.ZANDAKA END) AS ZANDAKA
    ,      max(open_kbn) as open_kbn
    ,      max(/*$taishoJoken*/'dogetu'_zen_open_kbn) as open_kbn_zen
    ,      case when sum(uriage) is null then 0 else sum(uriage) end as uriage
    ,      case when sum(kyakusu)  is null then 0 else sum(kyakusu)  end as kyakusu
    ,      case when sum(/*$taishoJoken*/'dogetu'_zen_uriage) is null then 0 else sum(/*$taishoJoken*/'dogetu'_zen_uriage) end as uriage_zen
    ,      case when sum(/*$taishoJoken*/'dogetu'_zen_kyakusu)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_zen_kyakusu)  end as kyakusu_zen
    ,      case when sum(issue_Cnt) is null then 0 else sum(issue_Cnt) end as issue_Cnt
    ,      case when sum(charge_Kin) is null then 0 else sum(charge_Kin)  end as charge_Kin
    ,      case when sum(charge_Ken) is null then 0 else sum(charge_Ken) end as charge_Ken
    ,      case when sum(kessai_Kin)  is null then 0 else sum(kessai_Kin)  end as kessai_Kin
    ,      case when sum(kessai_Ken) is null then 0 else sum(kessai_Ken) end as kessai_Ken
    ,      case when sum(charge_kin_cancel) is null then 0 else sum(charge_kin_cancel) end as charge_kin_cancel
    ,      case when sum(charge_ken_cancel) is null then 0 else sum(charge_ken_cancel) end as charge_ken_cancel
    ,      case when sum(kessai_Kin)  is null then 0 else sum(kessai_Kin)  end as kessai_Kin
    ,      case when sum(kessai_Ken)  is null then 0 else sum(kessai_Ken)  end as kessai_Ken
    ,      case when sum(charge_kin_cancel)  is null then 0 else sum(charge_kin_cancel)  end as charge_kin_cancel
    ,      case when sum(charge_ken_cancel)  is null then 0 else sum(charge_ken_cancel)  end as charge_ken_cancel
    ,      case when sum(use_kin_cancel)  is null then 0 else sum(use_kin_cancel)  end as use_kin_cancel
    ,      case when sum(use_ken_cancel)  is null then 0 else sum(use_ken_cancel)  end as use_ken_cancel
    ,      case when sum(bonus_v_issue)  is null then 0 else sum(bonus_v_issue)  end as bonus_v_issue
    ,      case when sum(bonus_v_use)  is null then 0 else sum(bonus_v_use)  end as bonus_v_use
    ,      case when sum(coupon_v_issue)  is null then 0 else sum(coupon_v_issue)  end as coupon_v_issue
    ,      case when sum(coupon_v_use)  is null then 0 else sum(coupon_v_use)  end as coupon_v_use
    ,      case when sum(/*$taishoJoken*/'dogetu'_Zen_Issue_Cnt)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_Zen_Issue_Cnt)  end as Zen_Issue_Cnt
    ,      case when sum(/*$taishoJoken*/'dogetu'_Zen_Charge_Kin)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_Zen_Charge_Kin)  end as Zen_Charge_Kin
    ,      case when sum(/*$taishoJoken*/'dogetu'_Zen_Charge_Ken)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_Zen_Charge_Ken)  end as Zen_Charge_Ken
    ,      case when sum(/*$taishoJoken*/'dogetu'_Zen_Kessai_Kin)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_Zen_Kessai_Kin)  end as Zen_Kessai_Kin
    ,      case when sum(/*$taishoJoken*/'dogetu'_Zen_Kessai_Ken)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_Zen_Kessai_Ken)  end as Zen_Kessai_Ken    
  from  bd35znmc as bd35
	JOIN (
	     SELECT MAX3.EIGYO_DT
	     ,      MAX3.COMPANY_CD
	     ,      MAX3.MISE_CD
	     ,      MAX3.ZANDAKA
	     FROM (SELECT MAX(BD3.EIGYO_DT) AS EIGYO_DT
	           ,      BD3.COMPANY_CD
	           ,      BD3.MISE_CD
	           FROM BD35ZNMC BD3
	/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") */
	           WHERE BD3.EIGYO_DT between /*kikanFrom*/'20060530' and /*kikanTo*/'20060601'
	--ELSE
	           WHERE BD3.EIGYO_DT = /*kikanFrom*/'201303'
	/*END*/
	           AND   BD3.COMPANY_CD = /*companyCd*/'00'
	           AND   BD3.OPEN_KBN   =1
	           AND   HONBU_KBN = '1'
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
	    ,        MAX3.ZANDAKA) MAXDT  
		ON (    MAXDT.COMPANY_CD = BD35.COMPANY_CD
            AND MAXDT.MISE_CD    = BD35.MISE_CD)
  where bd35.HONBU_KBN = '1'
  and   bd35.COMPANY_CD = /*companyCd*/'00'
/*IF taishoKikan.equals("DAY1")*/ 
  AND     bd35.EIGYO_DT   = /*kikanFrom*/'20060704'
/*END*/
/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") */ 
  AND     bd35.EIGYO_DT between /*kikanFrom*/'20060401' AND /*kikanTo*/'20060430'
/*END*/
/*IF tenShu.equals("ALL")*/
  AND     (bd35.OPEN_KBN = 1 or bd35.DOGETU_ZEN_OPEN_KBN = 1) 
-- ELSE
  AND     bd35.OPEN_KBN = 1
/*END*/
/*IF dataShu.equals("HOSEI")*/
  AND     (bd35.URIAGE > 0 AND bd35.DOGETU_ZEN_URIAGE > 0)
/*END*/
  group by bd35.company_cd, bd35.mise_cd
  ,        (CASE WHEN MAXDT.ZANDAKA IS NULL THEN 0 ELSE MAXDT.ZANDAKA END)