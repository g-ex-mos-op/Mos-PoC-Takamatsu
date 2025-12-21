SELECT
    bt14.info_shu,
    bt14.reg_date,
    bt14.seq,
    bt14.gyotai_kbn,
    bc09.gyotai_kbn_name,
    bt14.kobetsu_flg,
    bt14.mise_flg,
    bt14.first_user,
    bt14.first_pgm,
    bt14.first_tmsp,
    bt14.last_user,
    bt14.last_pgm,
    bt14.last_tmsp
FROM
    bt14iagt bt14,
    bc09gtai bc09
WHERE bt14.info_shu = /*infoShu*/'01'
AND   bt14.reg_date = /*regDate*/'20060126'
/*IF seq != null */
AND   bt14.seq = /*seq*/'0001'
/*END*/
AND   bt14.gyotai_kbn = bc09.gyotai_kbn
ORDER BY
    bt14.gyotai_kbn
