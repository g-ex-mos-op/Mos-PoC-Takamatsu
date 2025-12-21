 select    bd36.company_cd 
    ,      bd36.mise_cd 
    ,      (CASE WHEN MAXDT.ZANDAKA IS NULL THEN 0 ELSE MAXDT.ZANDAKA END) AS ZANDAKA
    ,      max(open_kbn) as open_kbn    
    ,      max(open_kbn_zen) as open_kbn_zen
    ,      case when sum(/*$taishoJoken*/'dogetu'_uriage) is null then 0 else sum(/*$taishoJoken*/'dogetu'_uriage) end as uriage
    ,      case when sum(/*$taishoJoken*/'dogetu'_kyakusu)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_kyakusu)  end as kyakusu
    ,      case when sum(/*$taishoJoken*/'dogetu'_zen_uriage) is null then 0 else sum(/*$taishoJoken*/'dogetu'_zen_uriage) end as uriage_zen
    ,      case when sum(/*$taishoJoken*/'dogetu'_zen_kyakusu)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_zen_kyakusu)  end as kyakusu_zen
    ,      case when sum(/*$taishoJoken*/'dogetu'_issue_Cnt) is null then 0 else sum(/*$taishoJoken*/'dogetu'_issue_Cnt) end as issue_Cnt
    ,      case when sum(/*$taishoJoken*/'dogetu'_charge_Kin) is null then 0 else sum(/*$taishoJoken*/'dogetu'_charge_Kin)  end as charge_Kin
    ,      case when sum(/*$taishoJoken*/'dogetu'_charge_Ken) is null then 0 else sum(/*$taishoJoken*/'dogetu'_charge_Ken) end as charge_Ken
    ,      case when sum(/*$taishoJoken*/'dogetu'_kessai_Kin)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_kessai_Kin)  end as kessai_Kin
    ,      case when sum(/*$taishoJoken*/'dogetu'_kessai_Ken) is null then 0 else sum(/*$taishoJoken*/'dogetu'_kessai_Ken) end as kessai_Ken
    ,      case when sum(/*$taishoJoken*/'dogetu'_charge_kin_cancel) is null then 0 else sum(/*$taishoJoken*/'dogetu'_charge_kin_cancel) end as charge_kin_cancel
    ,      case when sum(/*$taishoJoken*/'dogetu'_charge_ken_cancel) is null then 0 else sum(/*$taishoJoken*/'dogetu'_charge_ken_cancel) end as charge_ken_cancel
    ,      case when sum(/*$taishoJoken*/'dogetu'_kessai_Kin)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_kessai_Kin)  end as kessai_Kin
    ,      case when sum(/*$taishoJoken*/'dogetu'_kessai_Ken)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_kessai_Ken)  end as kessai_Ken
    ,      case when sum(/*$taishoJoken*/'dogetu'_charge_kin_cancel)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_charge_kin_cancel)  end as charge_kin_cancel
    ,      case when sum(/*$taishoJoken*/'dogetu'_charge_ken_cancel)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_charge_ken_cancel)  end as charge_ken_cancel
    ,      case when sum(/*$taishoJoken*/'dogetu'_use_kin_cancel)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_use_kin_cancel)  end as use_kin_cancel
    ,      case when sum(/*$taishoJoken*/'dogetu'_use_ken_cancel)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_use_ken_cancel)  end as use_ken_cancel
    ,      case when sum(/*$taishoJoken*/'dogetu'_bonus_v_issue)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_bonus_v_issue)  end as bonus_v_issue
    ,      case when sum(/*$taishoJoken*/'dogetu'_bonus_v_use)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_bonus_v_use)  end as bonus_v_use
    ,      case when sum(/*$taishoJoken*/'dogetu'_coupon_v_issue)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_coupon_v_issue)  end as coupon_v_issue
    ,      case when sum(/*$taishoJoken*/'dogetu'_coupon_v_use)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_coupon_v_use)  end as coupon_v_use
    ,      case when sum(/*$taishoJoken*/'dogetu'_Zen_Issue_Cnt)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_Zen_Issue_Cnt)  end as Zen_Issue_Cnt
    ,      case when sum(/*$taishoJoken*/'dogetu'_Zen_Charge_Kin)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_Zen_Charge_Kin)  end as Zen_Charge_Kin
    ,      case when sum(/*$taishoJoken*/'dogetu'_Zen_Charge_Ken)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_Zen_Charge_Ken)  end as Zen_Charge_Ken
    ,      case when sum(/*$taishoJoken*/'dogetu'_Zen_Kessai_Kin)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_Zen_Kessai_Kin)  end as Zen_Kessai_Kin
    ,      case when sum(/*$taishoJoken*/'dogetu'_Zen_Kessai_Ken)  is null then 0 else sum(/*$taishoJoken*/'dogetu'_Zen_Kessai_Ken)  end as Zen_Kessai_Ken
  from  bd36zgmc as bd36
 	JOIN (
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
	           AND   HONBU_KBN = '1'
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
  where bd36.HONBU_KBN = '1'
  and   bd36.COMPANY_CD = /*companyCd*/'00'
/*IF taishoKikan.equals("KIBETU")*/
  AND (bd36.eigyo_dt BETWEEN /*kikanFrom*/ AND /*kikanTo*/) 
-- ELSE
  AND bd36.eigyo_dt = /*kikanFrom*/
/*END*/
/*IF tenShu.equals("ALL")*/
  and     (bd36.open_kbn = 1 or bd36.open_kbn_zen = 1) 
-- ELSE
  and     bd36.open_kbn = 1
/*END*/
/*IF dataShu.equals("HOSEI")*/
  and       (bd36.hosei_uriage > 0 and bd36.hosei_zen_uriage > 0) 
/*END*/
  group by bd36.company_cd, bd36.mise_cd
  ,        (CASE WHEN MAXDT.ZANDAKA IS NULL THEN 0 ELSE MAXDT.ZANDAKA END)