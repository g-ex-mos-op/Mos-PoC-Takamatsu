UPDATE br18entd SET
	entry_year = /*entryYear*/'2006',
	entry_kai = /*entryKai*/'001',
	from_dt = /*entity.fromDt*/'20061201',
	to_dt = /*entity.toDt*/'20061202',
	last_user = /*entity.lastUser*/'99990003',
	last_pgm = /*entity.lastPgm*/'BEN014L',
	last_tmsp = /*lastTmsp*/
WHERE
	entry_cd = /*entity.entryCd*/'15'
	AND entry_year = /*entity.entryYear*/'2006'
	AND entry_kai = /*entity.entryKai*/'001'
	AND usertype_cd = /*entity.usertypeCd*/'99'
	AND day_kbn = /*entity.dayKbn*/'01'
	AND last_tmsp = /*entity.lastTmsp*/'1111-11-11 11:11:11.111111'