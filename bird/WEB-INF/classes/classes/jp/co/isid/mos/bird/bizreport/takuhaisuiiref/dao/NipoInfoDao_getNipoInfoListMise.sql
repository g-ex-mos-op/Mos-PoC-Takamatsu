    SELECT BT63.EIGYO_DT
       ,sum(BT63.URIAGE)        as URIAGE
       ,sum(BT63.KYAKUSU)       as KYAKUSU
       ,sum(BT63.OTHER_KIN_1)   as URIAGE_TAKU
       ,sum(BT63.OTHER_KEN_1)   as KYAKUSU_TAKU 
       ,sum(coalesce(BT63ZEN.URIAGE,0))   as URIAGE_ZEN
       ,sum(coalesce(BT63ZEN.KYAKUSU,0))  as KYAKUSU_ZEN
       ,sum(coalesce(BT63ZEN.OTHER_KIN_1,0)) as URIAGE_TAKU_ZEN
       ,sum(coalesce(BT63ZEN.OTHER_KEN_1,0)) as KYAKUSU_TAKU_ZEN 
       ,COUNT(DISTINCT (case when BT63.OTHER_KIN_1 >0 then BT63.MISE_CD else null end)) as HAN_TENPO_CNT
    FROM BM45ZDAY BM45 
    LEFT JOIN BT63SNIP BT63ZEN
	        ON (BT63ZEN.COMPANY_CD = BM45.COMPANY_CD
            AND BT63ZEN.MISE_CD    = BM45.ZEN_/*$zenDataShubetu*/''_MISE
            AND BT63ZEN.EIGYO_DT   = BM45.ZEN_/*$zenDataShubetu*/''_DT)
       ,BT63SNIP BT63 
       ,BN01DTEN BN01 
       ,(SELECT * FROM BM01TENM 
         WHERE COMPANY_CD = /*companyCd*/'00'
         AND    MISE_CD IN (
		    SELECT MISE_CD FROM BM07UTEN 
		    WHERE USER_ID = /*userId*/'99990003'
		    AND   COMPANY_CD = /*companyCd*/'00')) BM01 
    WHERE 
          BT63.COMPANY_CD = BM45.COMPANY_CD 
      AND BT63.MISE_CD    = BM45.MISE_CD 
      AND BT63.EIGYO_DT   = BM45.EIGYO_DT 
      AND BM45.OLDM_FLG   = '0'
      AND BM45.OPEN_KBN   = 1 
      AND BM45.COMPANY_CD = BN01.COMPANY_CD
      AND BM45.MISE_CD    = BN01.MISE_CD
      AND BM45.EIGYO_DT   = BN01.EIGYO_DT
      AND BN01.KBN17     <> '  '
      AND BN01.KBN17     <> '00' 
      AND BT63.COMPANY_CD = BM01.COMPANY_CD
      AND BT63.MISE_CD    = BM01.MISE_CD 
      AND BT63.COMPANY_CD = /*companyCd*/'00'
      AND BM45.EIGYO_DT BETWEEN /*startYmd*/'20050501'  AND /*endYmd*/'20050531'
GROUP BY
    BT63.EIGYO_DT
ORDER BY
    BT63.EIGYO_DT