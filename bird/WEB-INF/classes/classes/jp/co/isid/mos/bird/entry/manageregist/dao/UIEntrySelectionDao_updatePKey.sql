UPDATE br53ents SET
	entry_year = /*entryYear*/'2006',
	entry_kai = /*entryKai*/'001',
	selection_name = /*entity.selectionName*/' ',
	last_user = /*entity.lastUser*/'99990003',
	last_pgm = /*entity.lastPgm*/'BEN020L',
	last_tmsp = /*entity.lastTmsp*/
WHERE
	entry_cd = /*entity.entryCd*/'35'
	AND entry_year = /*entity.entryYear*/'2006'
	AND entry_kai = /*entity.entryKai*/'001'
	AND selection_kbn = /*entity.selectionKbn*/'1'
	AND selection_index = /*entity.selectionIndex*/'01'
	AND last_tmsp = /*entity.lastTmsp*/'1111-11-11 11:11:11.111111'