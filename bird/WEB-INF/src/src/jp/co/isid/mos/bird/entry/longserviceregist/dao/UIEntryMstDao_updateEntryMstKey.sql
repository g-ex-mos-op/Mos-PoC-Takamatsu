UPDATE br17entl
SET    ENTRY_YEAR = /*newEntryYear*/'2006'
,      ENTRY_KAI = /*newEntryKai*/'001'
,      ENTRY_TITLE = /*uiEntryMst.entryTitle*/
,      LAST_USER = /*uiEntryMst.lastUser*/'99990003'
,      LAST_PGM = /*uiEntryMst.lastPgm*/'BEN017L04'
,      LAST_TMSP = /*lastTmsp*/
WHERE 
	ENTRY_CD = /*uiEntryMst.entryCd*/'20'
	AND ENTRY_YEAR = /*uiEntryMst.entryYear*/'2006'
	AND ENTRY_KAI = /*uiEntryMst.entryKai*/'001'
	AND LAST_TMSP = /*uiEntryMst.lastTmsp*/'1111-11-11 11:11:11.111111'
