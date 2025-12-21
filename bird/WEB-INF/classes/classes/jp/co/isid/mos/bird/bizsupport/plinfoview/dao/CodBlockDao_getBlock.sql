select 
	BLOCK_CD,
	rtrim(BLOCK_NAME) as BLOCK_NAME 
from 
	BC23BLCK 
order by 
	BLOCK_CD