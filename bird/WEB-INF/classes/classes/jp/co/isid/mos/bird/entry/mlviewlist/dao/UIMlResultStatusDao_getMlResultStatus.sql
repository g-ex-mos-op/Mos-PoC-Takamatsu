select 
	STAFF_ID,
	TOTAL_LAST_YEAR,
	TOTAL_LAST_KAI,
	EXAM_NO,
	LAST_USER,
	LAST_PGM,
	LAST_TMSP 
from
	BT32MLKR 
where 
	TOTAL_LAST_YEAR = /*totalLastYear*/'2006'
and TOTAL_LAST_KAI = /*totalLastKai*/'001' 

order by
	STAFF_ID