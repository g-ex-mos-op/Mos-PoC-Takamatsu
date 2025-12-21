select 
     ENTRY_PLACE_CD,
     ENTRY_PLACE_NAME
from 
     BR20MLPL
where
     ENTRY_CD = /*entryCd*/'10' 
 and ENTRY_YEAR = /*entryYear*/'2005'
 and ENTRY_KAI = /*entryKai*/'001'
 and ENTRY_PLACE_CD is not null
 and ENTRY_PLACE_NAME is not null
 and rtrim(ENTRY_PLACE_NAME) <> ''
order by
	ENTRY_PLACE_CD
