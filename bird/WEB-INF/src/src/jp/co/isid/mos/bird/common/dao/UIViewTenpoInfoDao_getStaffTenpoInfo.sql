SELECT
    bm01.company_cd,
/*IF totalCd.equals("MISE")*/
    bm01.mise_cd as data_cd,
/*END*/
/*IF totalCd.equals("AREA")*/
    bm01.area_dai as data_cd,
/*END*/
    bm01.gyotai_kbn
FROM
    bm01tenm as bm01,
    bm07uten as bm07
WHERE
    bm01.mise_cd = bm07.mise_cd AND
    bm01.company_cd = bm07.company_cd AND
    bm07.user_id = /*userId*/'99990003'
GROUP BY
    bm01.company_cd,
/*IF totalCd.equals("MISE")*/
    bm01.mise_cd,
/*END*/
/*IF totalCd.equals("AREA")*/
    bm01.area_dai,
/*END*/
    bm01.gyotai_kbn