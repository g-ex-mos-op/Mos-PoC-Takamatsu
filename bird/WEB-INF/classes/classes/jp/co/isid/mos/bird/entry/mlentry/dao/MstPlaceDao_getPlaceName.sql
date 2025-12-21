select 
     rtrim(ENTRY_PLACE_NAME) as ENTRY_PLACE_NAME
from 
     BR20MLPL
where
     ENTRY_CD = /*entryCd*/'10' 
 and ENTRY_YEAR = /*entryYear*/'2005'
 and ENTRY_KAI = /*entryKai*/'001'
 and ENTRY_PLACE_CD = /*entryPlaceCd*/'001'
 
