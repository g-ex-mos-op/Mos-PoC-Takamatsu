SELECT
    sibu_cd,
    sibu_name
FROM bm10gsib
WHERE 
company_cd =/*companyCd*/'00'
/*IF shukeiKbn.equals("AREA_DAI_CD")*/
    AND area_dai_flg = '1'
/*END*/
/*IF limitFlg == true*/
AND sibu_cd IN
    (SELECT
    /*IF shukeiKbn.equals("AREA_DAI_CD")*/
        distinct bm01.area_dai
    -- ELSE
        distinct bm01.sibu_cd
    /*END*/		
    FROM
        bm51svsb AS bm51,
        bm01tenm AS bm01
    WHERE
        bm01.sibu_cd = bm51.sibu_cd
    AND bm01.company_cd = bm51.company_cd
    AND bm51.sv_cd = /*userId*/'00000921'
)
/*END*/
ORDER BY sibu_cd