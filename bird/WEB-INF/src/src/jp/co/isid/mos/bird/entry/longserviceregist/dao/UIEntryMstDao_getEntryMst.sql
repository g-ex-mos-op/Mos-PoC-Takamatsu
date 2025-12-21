SELECT
    br17entl.entry_cd,
    br17entl.entry_year,
    br17entl.entry_kai,
    RTRIM(br17entl.entry_title) AS entry_title,
    RTRIM(br17entl.entry_place) AS entry_place,
    br17entl.number_limit,
    br17entl.place_limit,
    RTRIM(br17entl.note) AS NOTE,
    br17entl.spare_flg1,
    br17entl.spare_flg2,
    br17entl.sakujo_flg,
    br17entl.first_user,
    br17entl.first_pgm,
    br17entl.first_tmsp,
    br17entl.last_user,
    br17entl.last_pgm,
    br17entl.last_tmsp,
    br18oetry.from_dt AS ONER_ENTRY_FROM,
    br18oetry.to_dt AS ONER_ENTRY_TO,
    br18odisp.from_dt AS ONER_DISP_FROM,
    br18odisp.to_dt AS ONER_DISP_TO,
    br18hetry.from_dt AS HONBU_ENTRY_FROM,
    br18hetry.to_dt AS HONBU_ENTRY_TO,
    br18hdisp.from_dt AS HONBU_DISP_FROM,
    br18hdisp.to_dt AS HONBU_DISP_TO
FROM
    br17entl
JOIN br18entd AS br18oetry ON (br17entl.entry_cd = br18oetry.entry_cd 	
	AND br17entl.entry_year = br18oetry.entry_year AND br17entl.entry_kai = br18oetry.entry_kai AND br18oetry.usertype_cd = '02' AND br18oetry.day_kbn = '04')
JOIN br18entd AS br18odisp ON (br17entl.entry_cd = br18odisp.entry_cd 	
	AND br17entl.entry_year = br18odisp.entry_year AND br17entl.entry_kai = br18odisp.entry_kai AND br18odisp.usertype_cd = '02' AND br18odisp.day_kbn = '03')
JOIN br18entd AS br18hetry ON (br17entl.entry_cd = br18hetry.entry_cd 	
	AND br17entl.entry_year = br18hetry.entry_year AND br17entl.entry_kai = br18hetry.entry_kai AND br18hetry.usertype_cd = '01' AND br18hetry.day_kbn = '04')
JOIN br18entd AS br18hdisp ON (br17entl.entry_cd = br18hdisp.entry_cd 	
	AND br17entl.entry_year = br18hdisp.entry_year AND br17entl.entry_kai = br18hdisp.entry_kai AND br18hdisp.usertype_cd = '01' AND br18hdisp.day_kbn = '03')
WHERE
    br17entl.entry_cd=/*entryCd*/'20'
AND br17entl.entry_year>=/*entryYear*/'2006'
AND br17entl.SAKUJO_FLG <> '1'
ORDER BY
    br18oetry.to_dt DESC,
    br17entl.entry_year DESC,
    br17entl.entry_kai DESC
