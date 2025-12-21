SELECT
    MISE_CD,
    CLOSE_DT,
    P4_TEL_NO
FROM
    PC01TENM
WHERE
    COMPANY_CD = /*companyCd*/'00' and
    MISE_CD in /*listMise*/('00005')
ORDER BY
    MISE_CD
