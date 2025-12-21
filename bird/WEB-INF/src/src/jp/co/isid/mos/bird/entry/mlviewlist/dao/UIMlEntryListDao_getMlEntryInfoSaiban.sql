select ENTRY_CD
     , ENTRY_YEAR
     , ENTRY_KAI
     , STAFF_ID
     , EXAM_NO
     , LAST_USER
     , LAST_PGM
     , LAST_TMSP

from BT23MLEJ 

where ENTRY_CD    = /*entryCd*/'10'
   and ENTRY_YEAR = /*entryYear*/'2006'
   and ENTRY_KAI  = /*entryKai*/'001'
   
order by STAFF_ID
