SELECT
    bt15.info_shu,
    bt15.reg_date,
    bt15.seq,
    bt15.gyotai_kbn,
    bt15.kobetsu_shu,
    bt15.kobetsu_cd,
    bt15.first_user,
    bt15.first_pgm,
    bt15.first_tmsp,
    bt15.last_user,
    bt15.last_pgm,
    bt15.last_tmsp
FROM
    bt15iaid bt15
WHERE
    bt15.info_shu = /*infoShu*/'01' AND
    bt15.reg_date = /*regDate*/'20060126' AND
    bt15.seq = /*seq*/'0001' AND
    bt15.gyotai_kbn = /*gyotaiKbn*/'010'
ORDER BY
    bt15.kobetsu_shu,
    bt15.kobetsu_cd
