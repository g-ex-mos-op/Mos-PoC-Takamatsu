select decimal(max(ENTRY_KAI)) 
from BR17ENTL
where 
    ENTRY_CD = /*entryCd*/'10' 
and ENTRY_YEAR = /*entryYear*/'2006'