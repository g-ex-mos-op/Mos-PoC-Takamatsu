select    bm01.company_cd
,         bm01.mise_cd
,         bm01.mise_name_kj
,         (CASE WHEN MAXDT.ZANDAKA IS NULL THEN 0 ELSE MAXDT.ZANDAKA END) AS ZANDAKA
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_URIAGE) as uriage
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_KYAKUSU) as kyakusu
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_ONER_YOSAN) as yosan
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_EIGYO_DAYS) as eigyo_days
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_ZEN_URIAGE) as uriage_zen
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_ZEN_KYAKUSU) as kyakusu_zen
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_ZEN_EIGYO_DAYS) as EIGYO_DAYS_ZEN
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_CHARGE_KIN_CANCEL)  AS CHARGE_KIN_CANCEL
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_CHARGE_KEN_CANCEL) AS CHARGE_KEN_CANCEL
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_USE_KIN_CANCEL) AS USE_KIN_CANCEL
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_USE_KEN_CANCEL) AS USE_KEN_CANCEL
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_BONUS_V_ISSUE) AS BONUS_V_ISSUE
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_BONUS_V_USE)  AS BONUS_V_USE
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_COUPON_V_ISSUE) AS COUPON_V_ISSUE
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_COUPON_V_USE) AS COUPON_V_USE
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_ISSUE_CNT)  AS ISSUE_CNT
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_CHARGE_KIN) AS CHARGE_KIN
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_CHARGE_KEN) AS CHARGE_KEN
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_KESSAI_KIN) AS KESSAI_KIN
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_KESSAI_KEN) AS KESSAI_KEN	
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_ZEN_ISSUE_CNT)  AS ZEN_ISSUE_CNT
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_ZEN_CHARGE_KIN) AS ZEN_CHARGE_KIN
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_ZEN_CHARGE_KEN) AS ZEN_CHARGE_KEN						
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_ZEN_KESSAI_KIN) AS ZEN_KESSAI_KIN
,         sum(bd36./*$dataOnerInfo*/'DOJITU'_ZEN_KESSAI_KEN) AS ZEN_KESSAI_KEN	

from      bm01tenm as bm01
,         bd36zgmc as bd36
,         (
     SELECT MAX3.EIGYO_DT
     ,      MAX3.COMPANY_CD
     ,      MAX3.MISE_CD
     ,      MAX3./*$dataOnerInfo*/'DOJITU'_ZANDAKA AS ZANDAKA
     FROM (SELECT MAX(BD3.EIGYO_DT) AS EIGYO_DT
           ,      BD3.COMPANY_CD
           ,      BD3.MISE_CD
           FROM BD36ZGMC BD3
           WHERE BD3.EIGYO_DT   = /*kikanFrom*/'201303'
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
    ,      MAX3./*$dataOnerInfo*/'DOJITU'_ZANDAKA
) MAXDT

where     bd36.eigyo_dt = /*kikanFrom*/'200605'
and       bd36.company_cd = /*companyCd*/'00'
and       bd36.company_cd = bm01.company_cd
and       bd36.mise_cd = bm01.mise_cd
and       bm01.oner_cd =/*onerCd*/'36387'
and       bd36.oldm_flg = '0'
and       bd36.open_kbn = 1
AND       MAXDT.COMPANY_CD = BD36.COMPANY_CD
AND       MAXDT.MISE_CD    = BD36.MISE_CD
group by  bm01.company_cd
,         bm01.mise_cd
,         bm01.mise_name_kj
,         (CASE WHEN MAXDT.ZANDAKA IS NULL THEN 0 ELSE MAXDT.ZANDAKA END)
order by  mise_cd
