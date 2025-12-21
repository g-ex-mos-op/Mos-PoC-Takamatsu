select NO_COUNTER,
	CNT_SHOKI,
	FIRST_USER,
	FIRST_PGM,
	FIRST_TMSP,
	LAST_USER,
	LAST_PGM,
	LAST_TMSP
from 
	br37stct
where 
	no_counter = (select max(no_counter) from br37stct)