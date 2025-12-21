SELECT
   EIGYO_DT,
   SUM(NEBIKI_KEN_1) AS NEBIKI_KEN_1,
   SUM(NEBIKI_KIN_1) AS NEBIKI_KIN_1,
   SUM(NEBIKI_TAX_1) AS NEBIKI_TAX_1,
   SUM(NEBIKI_KEN_2) AS NEBIKI_KEN_2,
   SUM(NEBIKI_KIN_2) AS NEBIKI_KIN_2,
   SUM(NEBIKI_TAX_2) AS NEBIKI_TAX_2,
   SUM(NEBIKI_KEN_3) AS NEBIKI_KEN_3,
   SUM(NEBIKI_KIN_3) AS NEBIKI_KIN_3,
   SUM(NEBIKI_TAX_3) AS NEBIKI_TAX_3,
   SUM(NEBIKI_KEN_4) AS NEBIKI_KEN_4,
   SUM(NEBIKI_KIN_4) AS NEBIKI_KIN_4,
   SUM(NEBIKI_TAX_4) AS NEBIKI_TAX_4,
   SUM(NEBIKI_KEN_5) AS NEBIKI_KEN_5,
   SUM(NEBIKI_KIN_5) AS NEBIKI_KIN_5,
   SUM(NEBIKI_TAX_5) AS NEBIKI_TAX_5,
   SUM(NEBIKI_KEN_6) AS NEBIKI_KEN_6,
   SUM(NEBIKI_KIN_6) AS NEBIKI_KIN_6,
   SUM(NEBIKI_TAX_6) AS NEBIKI_TAX_6,
   SUM(NEBIKI_KEN_7) AS NEBIKI_KEN_7,
   SUM(NEBIKI_KIN_7) AS NEBIKI_KIN_7,
   SUM(NEBIKI_TAX_7) AS NEBIKI_TAX_7,
   SUM(NEBIKI_KEN_8) AS NEBIKI_KEN_8,
   SUM(NEBIKI_KIN_8) AS NEBIKI_KIN_8,
   SUM(NEBIKI_TAX_8) AS NEBIKI_TAX_8,
   SUM(NEBIKI_KEN_9) AS NEBIKI_KEN_9,
   SUM(NEBIKI_KIN_9) AS NEBIKI_KIN_9,
   SUM(NEBIKI_TAX_9) AS NEBIKI_TAX_9
FROM
            BT95NBKI 
WHERE
company_cd=/*companyCd*/'00'
AND eigyo_dt BETWEEN /*kikanFrom*/'20060601' AND /*kikanTo*/'20060630'
/*IF userType.equals("01") && limitKbn == true*/
AND mise_cd=
(SELECT bm01tenm.mise_cd FROM bm51svsb
    JOIN bm01tenm ON (bm51svsb.company_cd=bm01tenm.company_cd AND bm51svsb.sibu_cd=bm01tenm.sibu_cd)
WHERE bm51svsb.company_cd=/*companyCd*/'00'
AND bm51svsb.sv_cd=/*userId*/'00000001'
AND bm01tenm.mise_cd=/*miseCd*/'00014')
/*END*/
/*IF userType.equals("02") */
AND   mise_cd IN (
	SELECT DISTINCT RTRIM(bm01tenm.mise_cd) AS MISE_CD
	FROM
	    bm01tenm
	WHERE
	    bm01tenm.company_cd = /*companyCd*/'00'
	AND bm01tenm.oner_cd = /*onerCd*/'36378'
	/*IF miseCd != null && miseCd.equals("") == false && miseCd.equals("99999") == false */
	AND bm01tenm.mise_cd = /*miseCd*/'00014'
	/*END*/
)
--ELSE
AND mise_cd=/*miseCd*/'00014'
/*END*/
GROUP BY EIGYO_DT
ORDER BY eigyo_dt