SELECT DISTINCT BT13.reg_date
,      BT13.seq
,      BC07.sort_seq
,      BT13.shozoku_kbn
,      BC07.shozoku_name

FROM BT13iasz BT13
,    BC07shzk BC07

WHERE BT13.info_shu = '02'
AND   DATE(BT13.LAST_TMSP) = /*lastTmspDt*/'2009-10-01'
AND   BT13.shozoku_kbn = BC07.shozoku_kbn

ORDER BY
    BC07.sort_seq