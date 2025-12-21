SELECT
    bt16.info_shu,
    bt16.reg_date,
    bt16.seq,
    bt16.gyotai_kbn,
    bt16.mise_cd,
    bm01.mise_name_kj,
    bt16.first_user,
    bt16.first_pgm,
    bt16.first_tmsp,
    bt16.last_user,
    bt16.last_pgm,
    bt16.last_tmsp
FROM
    bt16iast bt16,
    bm01tenm bm01
WHERE
    bt16.info_shu = /*infoShu*/'01' AND
    bt16.reg_date = /*regDate*/'20060126' AND
    bt16.seq = /*seq*/'0001' AND
    bt16.gyotai_kbn = /*gyotaiKbn*/'011' AND
    bt16.gyotai_kbn = bm01.gyotai_kbn AND
    bt16.mise_cd = bm01.mise_cd
ORDER BY
    bt16.mise_cd
