SELECT
    bt65sady.eigyo_dt,
    SUM(DECIMAL(bt65sady.uriage,13)) AS URIAGE,
    SUM(DECIMAL(COALESCE(bd65sadm.uriage1, 0),13)) AS URIAGE1,
    SUM(DECIMAL(COALESCE(bd65sadm.uriage2, 0),13)) AS URIAGE2,
    SUM(bt65sady.nebiki) AS NEBIKI,
    SUM(bt65sady.tax) AS TAX,
    SUM(COALESCE(bd65sadm.tax1, 0)) AS TAX1,
    SUM(COALESCE(bd65sadm.tax2, 0)) AS TAX2,
    SUM(bt65sady.tieket_kin_1 + bt65sady.tieket_kin_2 + bt65sady.tieket_kin_3 + bt65sady.tieket_kin_4
    + bt65sady.tieket_kin_5 + bt65sady.tieket_kin_6 + bt65sady.tieket_kin_7 + bt65sady.tieket_kin_8
    + bt65sady.tieket_kin_9 + bt65sady.tieket_kin_10 + bt65sady.tieket_kin_11 + bt65sady.tieket_kin_12
    + bt65sady.tieket_kin_13 + bt65sady.tieket_kin_14 + bt65sady.tieket_kin_15) AS GC_HAN_KIN,
    SUM(bt65sady.kaikei_ken_4 + bt65sady.kaikei_ken_5 + bt65sady.kaikei_ken_6 + bt65sady.kaikei_ken_7
    + bt65sady.kaikei_ken_8 + bt65sady.kaikei_ken_9 + bt65sady.kaikei_ken_10 + bt65sady.kaikei_ken_11) AS URIKAKE_KEN, 
    SUM(bt65sady.kaikei_kin_4 + bt65sady.kaikei_kin_5 + bt65sady.kaikei_kin_6 + bt65sady.kaikei_kin_7
    + bt65sady.kaikei_kin_8 + bt65sady.kaikei_kin_9 + bt65sady.kaikei_kin_10 + bt65sady.kaikei_kin_11) AS URIKAKE_KIN,
    SUM(bt65sady.kaikei_kin_3) AS GC_URI_KIN,
    SUM(bt65sady.kaikei_kin_2) AS INV_KIN,
    SUM(bt65sady.genkin_kin) AS GENKIN,
    SUM(bt65sady.aridaka_jitu) AS ARIDAKA_JITU,
    SUM(bt65sady.kajou) AS KAJOU,
    SUM(bt65sady.fusoku) AS FUSOKU,
    SUM(bt65sady.kyakusu) AS KYAKUSU,
    SUM(bt65sady.can_ken) AS CAN_KEN,
    SUM(bt65sady.can_kin) AS CAN_KIN,
    SUM(bt65sady.allcan_ken) AS ALLCAN_KEN,
    SUM(bt65sady.allcan_kin) AS ALLCAN_KIN,
    SUM(bt65sady.kaikei_ken_4) AS KAIKEI_KEN_4,
    SUM(bt65sady.kaikei_kin_4) AS KAIKEI_KIN_4,
    SUM(bt65sady.kaikei_ken_5) AS KAIKEI_KEN_5,
    SUM(bt65sady.kaikei_kin_5) AS KAIKEI_KIN_5,
    SUM(bt65sady.kaikei_ken_6) AS KAIKEI_KEN_6,
    SUM(bt65sady.kaikei_kin_6) AS KAIKEI_KIN_6,
    SUM(bt65sady.kaikei_ken_7) AS KAIKEI_KEN_7,
    SUM(bt65sady.kaikei_kin_7) AS KAIKEI_KIN_7,
    SUM(bt65sady.kaikei_ken_8) AS KAIKEI_KEN_8,
    SUM(bt65sady.kaikei_kin_8) AS KAIKEI_KIN_8,
    SUM(bt65sady.kaikei_ken_9) AS KAIKEI_KEN_9,
    SUM(bt65sady.kaikei_kin_9) AS KAIKEI_KIN_9,
    SUM(bt65sady.kaikei_ken_10) AS KAIKEI_KEN_10,
    SUM(bt65sady.kaikei_kin_10) AS KAIKEI_KIN_10,
    SUM(bt65sady.kaikei_ken_11) AS KAIKEI_KEN_11,
    SUM(bt65sady.kaikei_kin_11) AS KAIKEI_KIN_11,
    SUM(bt65sady.tieket_ken_1) AS TIEKET_KEN_1,
    SUM(bt65sady.tieket_kin_1) AS TIEKET_KIN_1,
    SUM(bt65sady.tieket_ken_2) AS TIEKET_KEN_2,
    SUM(bt65sady.tieket_kin_2) AS TIEKET_KIN_2,
    SUM(bt65sady.tieket_ken_3) AS TIEKET_KEN_3,
    SUM(bt65sady.tieket_kin_3) AS TIEKET_KIN_3,
    SUM(bt65sady.tieket_ken_4) AS TIEKET_KEN_4,
    SUM(bt65sady.tieket_kin_4) AS TIEKET_KIN_4,
    SUM(bt65sady.tieket_ken_5) AS TIEKET_KEN_5,
    SUM(bt65sady.tieket_kin_5) AS TIEKET_KIN_5,
    SUM(bt65sady.tieket_ken_6) AS TIEKET_KEN_6,
    SUM(bt65sady.tieket_kin_6) AS TIEKET_KIN_6,
    SUM(bt65sady.tieket_ken_7) AS TIEKET_KEN_7,
    SUM(bt65sady.tieket_kin_7) AS TIEKET_KIN_7,
    SUM(bt65sady.tieket_ken_8) AS TIEKET_KEN_8,
    SUM(bt65sady.tieket_kin_8) AS TIEKET_KIN_8,
    SUM(bt65sady.tieket_ken_9) AS TIEKET_KEN_9,
    SUM(bt65sady.tieket_kin_9) AS TIEKET_KIN_9,
    SUM(bt65sady.tieket_ken_10) AS TIEKET_KEN_10,
    SUM(bt65sady.tieket_kin_10) AS TIEKET_KIN_10,
    SUM(bt65sady.tieket_ken_11) AS TIEKET_KEN_11,
    SUM(bt65sady.tieket_kin_11) AS TIEKET_KIN_11,
    SUM(bt65sady.tieket_ken_12) AS TIEKET_KEN_12,
    SUM(bt65sady.tieket_kin_12) AS TIEKET_KIN_12,
    SUM(bt65sady.tieket_ken_13) AS TIEKET_KEN_13,
    SUM(bt65sady.tieket_kin_13) AS TIEKET_KIN_13,
    SUM(bt65sady.tieket_ken_14) AS TIEKET_KEN_14,
    SUM(bt65sady.tieket_kin_14) AS TIEKET_KIN_14,
    SUM(bt65sady.tieket_ken_15) AS TIEKET_KEN_15,
    SUM(bt65sady.tieket_kin_15) AS TIEKET_KIN_15
FROM
bt65sady
LEFT OUTER JOIN bd65sadm
ON  bt65sady.company_cd = bd65sadm.company_cd
AND bt65sady.eigyo_dt = bd65sadm.eigyo_dt
AND bt65sady.mise_cd = bd65sadm.mise_cd
WHERE
bt65sady.company_cd=/*companyCd*/'00'
AND bt65sady.eigyo_dt BETWEEN /*kikanFrom*/'20060601' AND /*kikanTo*/'20060630'
/*IF userType.equals("01") && limitKbn == true*/
AND bt65sady.mise_cd=
(SELECT bm01tenm.mise_cd FROM bm51svsb
    JOIN bm01tenm ON (bm51svsb.company_cd=bm01tenm.company_cd AND bm51svsb.sibu_cd=bm01tenm.sibu_cd)
WHERE bm51svsb.company_cd=/*companyCd*/'00'
AND bm51svsb.sv_cd=/*userId*/'00000001'
AND bm01tenm.mise_cd=/*miseCd*/'00014')
/*END*/
/*IF userType.equals("02") */
AND   bt65sady.mise_cd IN (
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
AND bt65sady.mise_cd=/*miseCd*/'00014'
/*END*/
GROUP BY ROLLUP (bt65sady.eigyo_dt)
ORDER BY bt65sady.eigyo_dt