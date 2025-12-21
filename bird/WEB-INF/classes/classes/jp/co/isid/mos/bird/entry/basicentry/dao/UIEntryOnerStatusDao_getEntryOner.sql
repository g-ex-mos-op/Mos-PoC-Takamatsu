select 
	ENTRY_CD, 
	ENTRY_YEAR,
	ENTRY_KAI,
	COMPANY_CD,
	ONER_CD,
	rtrim(ENTRY_FLG) as ENTRY_FLG,
	rtrim(FIRST_USER) as FIRST_USER,
	rtrim(FIRST_PGM) as FIRST_PGM,
	FIRST_TMSP,
	rtrim(LAST_USER) as LAST_USER,
	rtrim(LAST_PGM) as LAST_PGM,
	LAST_TMSP 
from 
	BT20ENON 
where 
	ENTRY_CD = /*entryCd*/'05'
and ENTRY_YEAR = /*entryYear*/'2006'
and ENTRY_KAI = /*entryKai*/'44'
and COMPANY_CD = /*companyCd*/'00'
and ONER_CD = /*onerCd*/'36478'