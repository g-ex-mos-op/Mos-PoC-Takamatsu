SELECT
    bt13.info_shu,
    bt13.reg_date,
    bt13.seq,
    bc07.sort_seq,
    bt13.shozoku_kbn,
    bc07.shozoku_name,
    bt13.first_user,
    bt13.first_pgm,
    bt13.first_tmsp,
    bt13.last_user,
    bt13.last_pgm,
    bt13.last_tmsp
FROM
    bt13iasz bt13,
    bc07shzk bc07
WHERE
    bt13.info_shu = /*infoShu*/'01' AND
    bt13.reg_date = /*regDate*/'20060126'
/*IF seq != null */
AND   bt13.seq = /*seq*/'0001'
/*END*/
AND   bt13.shozoku_kbn = bc07.shozoku_kbn
ORDER BY
    bc07.sort_seq
