select 
	rtrim(COURSE_CD) as COURSE_CD
,	rtrim(COURSE_NAME) as COURSE_NAME
from  
    BR19MLCR
where 
    ENTRY_CD   = /*entryCd*/'10'
AND ENTRY_YEAR = /*entryYear*/'2006'
AND ENTRY_KAI  = /*entryKai*/'001'
