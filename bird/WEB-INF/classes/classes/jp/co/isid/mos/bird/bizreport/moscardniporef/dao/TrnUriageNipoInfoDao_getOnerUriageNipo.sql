SELECT BD35.COMPANY_CD
,      BD35.MISE_CD
,      BM01.MISE_NAME_KJ
,      uriage
,      yosan
,      TENKO_KBN
,      eigyo_days
,      kyakusu
,      ISSUE_CNT
,      CHARGE_KIN
,      CHARGE_KEN
,      KESSAI_KIN
,      KESSAI_KEN
,      CHARGE_KIN_CANCEL
,      CHARGE_KEN_CANCEL
,      USE_KIN_CANCEL
,      USE_KEN_CANCEL
,      BONUS_V_ISSUE
,      BONUS_V_USE
,      COUPON_V_ISSUE
,      COUPON_V_USE
,      uriage_zen
,      tenko_kbn_zen
,      EIGYO_DAYS_ZEN
,      kyakusu_zen
,      ZEN_ISSUE_CNT
,      ZEN_CHARGE_KIN
,      ZEN_CHARGE_KEN
,      ZEN_KESSAI_KIN
,      ZEN_KESSAI_KEN
,      MAXDT.ZANDAKA

FROM (SELECT COMPANY_CD, MISE_CD, MISE_NAME_KJ 
      FROM BM01TENM WHERE COMPANY_CD = /*companyCd*/'00' AND ONER_CD =/*onerCd*/'36387') BM01
,    (select    bd35.company_cd
      ,         bd35.mise_cd
      ,         MAX(bd35.tenko_kbn) AS TENKO_KBN
      ,         sum(bd35.uriage) as uriage
      ,         sum(bd35.oner_yosan) as yosan
      ,         sum(bd35.eigyo_days) as eigyo_days
      ,         sum(bd35.kyakusu) as kyakusu
      ,         SUM(bd35.ISSUE_CNT) AS ISSUE_CNT
      ,         SUM(bd35.CHARGE_KIN) AS CHARGE_KIN
      ,         SUM(bd35.CHARGE_KEN) AS CHARGE_KEN
      ,         SUM(bd35.KESSAI_KIN) AS KESSAI_KIN
      ,         SUM(bd35.KESSAI_KEN) AS KESSAI_KEN
      ,         SUM(bd35.CHARGE_KIN_CANCEL) AS CHARGE_KIN_CANCEL
      ,         SUM(bd35.CHARGE_KEN_CANCEL) AS CHARGE_KEN_CANCEL
      ,         SUM(bd35.USE_KIN_CANCEL) AS USE_KIN_CANCEL
      ,         SUM(bd35.USE_KEN_CANCEL) AS USE_KEN_CANCEL
      ,         SUM(bd35.BONUS_V_ISSUE) AS BONUS_V_ISSUE
      ,         SUM(bd35.BONUS_V_USE) AS BONUS_V_USE
      ,         SUM(bd35.COUPON_V_ISSUE) AS COUPON_V_ISSUE
      ,         SUM(bd35.COUPON_V_USE) AS COUPON_V_USE
      ,         sum(bd35./*$dataOnerInfo*/'DOJITU'_ZEN_URIAGE) as uriage_zen
      ,         sum(bd35./*$dataOnerInfo*/'DOJITU'_ZEN_EIGYO_DAYS) as EIGYO_DAYS_ZEN
      ,         sum(bd35./*$dataOnerInfo*/'DOJITU'_ZEN_KYAKUSU) as kyakusu_zen
      ,         SUM(bd35./*$dataOnerInfo*/'DOJITU'_ZEN_ISSUE_CNT)  AS ZEN_ISSUE_CNT
      ,         SUM(bd35./*$dataOnerInfo*/'DOJITU'_ZEN_CHARGE_KIN) AS ZEN_CHARGE_KIN
      ,         SUM(bd35./*$dataOnerInfo*/'DOJITU'_ZEN_CHARGE_KEN) AS ZEN_CHARGE_KEN
      ,         SUM(bd35./*$dataOnerInfo*/'DOJITU'_ZEN_KESSAI_KIN) AS ZEN_KESSAI_KIN
      ,         SUM(bd35./*$dataOnerInfo*/'DOJITU'_ZEN_KESSAI_KEN) AS ZEN_KESSAI_KEN	
      ,         MAX(bd35./*$dataOnerInfo*/'DOJITU'_ZEN_TENKO_KBN) as tenko_kbn_zen
      from      bd35znmc as bd35

/*IF kikanInfo.equals("DAY1")*/
      where       bd35.eigyo_dt = /*kikanFrom*/'20060530'
-- ELSE
      where       bd35.eigyo_dt between /*kikanFrom*/'20060530' and /*kikanTo*/'20060601'
/*END*/
      and       bd35.company_cd = /*companyCd*/'00'
      and       bd35.oldm_flg = '0'
      and       bd35.open_kbn = 1
      and       bd35.honbu_kbn = '0'
      group by  bd35.company_cd
      ,         bd35.mise_cd
) BD35
,    (
     SELECT MAX3.EIGYO_DT
     ,      MAX3.COMPANY_CD
     ,      MAX3.MISE_CD
     ,      MAX3.ZANDAKA
     FROM (SELECT MAX(BD3.EIGYO_DT) AS EIGYO_DT
           ,      BD3.COMPANY_CD
           ,      BD3.MISE_CD
           FROM BD35ZNMC BD3
/*IF kikanInfo.equals("DAY1")*/
           WHERE BD3.EIGYO_DT = /*kikanFrom*/'201303'
--ELSE
           WHERE BD3.EIGYO_DT between /*kikanFrom*/'20060530' and /*kikanTo*/'20060601'
/*END*/
           AND   BD3.COMPANY_CD = /*companyCd*/'00'
           AND   BD3.OPEN_KBN   =1
           AND   BD3.oldm_flg   = '0'
           and   bd3.honbu_kbn = '0'
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
WHERE bd35.mise_cd = bm01.mise_cd
AND   bd35.company_cd = bm01.company_cd
AND   MAXDT.COMPANY_CD = BD35.COMPANY_CD
AND   MAXDT.MISE_CD    = BD35.MISE_CD

order by  mise_cd