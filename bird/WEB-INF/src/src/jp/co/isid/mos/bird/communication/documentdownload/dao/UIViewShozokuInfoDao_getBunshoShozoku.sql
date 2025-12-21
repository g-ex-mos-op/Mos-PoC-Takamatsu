SELECT
    bc07.shozoku_kbn,
    bc07.shozoku_name
FROM
    bc07shzk as bc07,
    bt13iasz as bt13
WHERE
    bc07.shozoku_kbn = bt13.shozoku_kbn AND
    bt13.info_shu = /*infoShu*/'03' AND
    bt13.reg_date = /*regDate*/'20070701' AND
    bt13.seq = /*seq*/'0000'
ORDER BY
    bc07.sort_seq