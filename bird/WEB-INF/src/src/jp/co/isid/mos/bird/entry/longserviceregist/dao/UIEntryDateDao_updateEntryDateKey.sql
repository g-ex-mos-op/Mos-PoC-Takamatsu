UPDATE BR18ENTD
SET    ENTRY_YEAR = /*newEntryYear*/'2006'
,      ENTRY_KAI = /*newEntryKai*/'001'
,      FROM_DT = /*uiEntryDate.fromDt*/'20061202'
,      TO_DT = /*uiEntryDate.toDt*/'20061202'
,      LAST_USER = /*uiEntryDate.lastUser*/'99990003'
,      LAST_PGM = /*uiEntryDate.lastPgm*/'BEN012L'
,      LAST_TMSP = /*lastTmsp*/
WHERE  
	ENTRY_CD = /*uiEntryDate.entryCd*/'20'
	AND ENTRY_YEAR = /*uiEntryDate.entryYear*/'2006'
	AND	ENTRY_KAI = /*uiEntryDate.entryKai*/'001'
	AND USERTYPE_CD = /*uiEntryDate.usertypeCd*/'01'
	AND DAY_KBN = /*uiEntryDate.dayKbn*/'03'
	AND LAST_TMSP = /*uiEntryDate.lastTmsp*/'1111-11-11 11:11:11.111111'
