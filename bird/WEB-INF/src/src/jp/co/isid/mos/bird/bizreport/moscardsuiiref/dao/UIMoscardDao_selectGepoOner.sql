  SELECT SUB.EIGYO_DT
  ,      SUM(ZANDAKA) AS ZANDAKA
  ,      SUM(URIAGE) AS URIAGE
  ,      SUM(YOSAN) AS YOSAN
  ,      SUM(EIGYO_DAYS) AS EIGYO_DAYS
  ,      SUM(URIAGE_ZEN) AS URIAGE_ZEN
  ,      SUM(EIGYO_DAYS_ZEN) AS EIGYO_DAYS_ZEN
  ,      SUM(KYAKUSU) AS KYAKUSU
  ,      SUM(KYAKUSU_ZEN) AS KYAKUSU_ZEN
  ,      SUM(CHARGE_KIN_CANCEL) AS CHARGE_KIN_CANCEL
  ,      SUM(CHARGE_KEN_CANCEL) AS CHARGE_KEN_CANCEL
  ,      SUM(USE_KIN_CANCEL) AS USE_KIN_CANCEL
  ,      SUM(USE_KEN_CANCEL) AS USE_KEN_CANCEL
  ,      SUM(BONUS_V_ISSUE) AS BONUS_V_ISSUE
  ,      SUM(BONUS_V_USE) AS BONUS_V_USE
  ,      SUM(COUPON_V_ISSUE) AS COUPON_V_ISSUE
  ,      SUM(COUPON_V_USE) AS COUPON_V_USE
  ,      SUM(ISSUE_CNT) AS ISSUE_CNT
  ,      SUM(CHARGE_KIN) AS CHARGE_KIN
  ,      SUM(CHARGE_KEN) AS CHARGE_KEN
  ,      SUM(KESSAI_KIN) AS KESSAI_KIN
  ,      SUM(KESSAI_KEN) AS KESSAI_KEN	
  ,      SUM(ZEN_ISSUE_CNT) AS ZEN_ISSUE_CNT
  ,      SUM(ZEN_CHARGE_KIN) AS ZEN_CHARGE_KIN
  ,      SUM(ZEN_CHARGE_KEN) AS ZEN_CHARGE_KEN
  ,      SUM(ZEN_KESSAI_KIN) AS ZEN_KESSAI_KIN
  ,      SUM(ZEN_KESSAI_KEN) AS ZEN_KESSAI_KEN	
      FROM ( 
SELECT BD36.EIGYO_DT
,      BD36.MISE_CD
,      BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZANDAKA AS ZANDAKA
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_URIAGE) AS URIAGE
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_ONER_YOSAN) AS YOSAN
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_EIGYO_DAYS) AS EIGYO_DAYS
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_URIAGE) AS URIAGE_ZEN
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_EIGYO_DAYS) AS EIGYO_DAYS_ZEN
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_KYAKUSU) AS KYAKUSU
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_KYAKUSU) AS KYAKUSU_ZEN
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_CHARGE_KIN_CANCEL)  AS CHARGE_KIN_CANCEL
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_CHARGE_KEN_CANCEL) AS CHARGE_KEN_CANCEL
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_USE_KIN_CANCEL) AS USE_KIN_CANCEL
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_USE_KEN_CANCEL) AS USE_KEN_CANCEL
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_BONUS_V_ISSUE) AS BONUS_V_ISSUE
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_BONUS_V_USE)  AS BONUS_V_USE
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_COUPON_V_ISSUE) AS COUPON_V_ISSUE
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_COUPON_V_USE) AS COUPON_V_USE
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_ISSUE_CNT)  AS ISSUE_CNT
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_CHARGE_KIN) AS CHARGE_KIN
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_CHARGE_KEN) AS CHARGE_KEN
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_KESSAI_KIN) AS KESSAI_KIN
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_KESSAI_KEN) AS KESSAI_KEN	
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_ISSUE_CNT)  AS ZEN_ISSUE_CNT
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_CHARGE_KIN) AS ZEN_CHARGE_KIN
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_CHARGE_KEN) AS ZEN_CHARGE_KEN						
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_KESSAI_KIN) AS ZEN_KESSAI_KIN
,      SUM(BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZEN_KESSAI_KEN) AS ZEN_KESSAI_KEN	
FROM   BD36ZGMC BD36
WHERE  BD36.EIGYO_DT BETWEEN /*paramsDto.kikanSiteiFrom*/'201203' AND /*paramsDto.kikanSiteiTo*/'201304'
AND    BD36.COMPANY_CD = /*paramsDto.companyCd*/'00'
AND    OPEN_KBN = 1
AND    OLDM_FLG = '0'
AND    EXISTS (SELECT * FROM BM01TENM BM01
/*IF "MISE".equals(paramsDto.taishoJoken) */  
               WHERE BM01.MISE_CD = /*paramsDto.hyojiTaisho*/'00108'
--ELSE
               WHERE BM01.ONER_CD = /*paramsDto.hyojiTaisho*/'37006'
/*END*/
               AND   BM01.COMPANY_CD = BD36.COMPANY_CD
               AND   BM01.MISE_CD    = BD36.MISE_CD)
      GROUP BY BD36.EIGYO_DT
      ,      BD36.MISE_CD
      ,      BD36./*$paramsDto.zennenDataShubetu*/'DOGETU'_ZANDAKA
) SUB
GROUP BY SUB.EIGYO_DT
ORDER BY SUB.EIGYO_DT