SELECT
    RTRIM(bm49tckt.tckt_cd) AS TCKT_CD,
    RTRIM(bm49tckt.tckt_name) AS TCKT_NAME
FROM
    bm49tckt
WHERE
    bm49tckt.company_cd=/*companyCd*/'00'
AND bm49tckt.mise_cd=/*miseCd*/'00200'
ORDER BY bm49tckt.tckt_cd