select 
    rtrim(BC23.BLOCK_CD) as BLOCK_CD,
	rtrim(BC23.BLOCK_NAME) as BLOCK_NAME
from
	BC23BLCK as BC23
order by 
	BC23.BLOCK_CD