SELECT
    COMPANY_CD,
    MISE_CD,
    (case when CLOSE_DT > /*targetDt*/'200701' then '0' else '1' end) as CLOSE_FLG
FROM
    BM01TENM
WHERE
    COMPANY_CD = /*companyCd*/'00'
    and KAIYAKU_DT < /*targetDt*/'200701'
    and MISE_KBN like '_1_'
    and MISE_CD in /*aryMise*/('00018')
