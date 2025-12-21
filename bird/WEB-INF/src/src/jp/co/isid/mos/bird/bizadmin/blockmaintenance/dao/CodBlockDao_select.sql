select rtrim(BC23.BLOCK_CD) as BLOCK_CD
,      rtrim(BC23.BLOCK_NAME) as BLOCK_NAME
,      BC23.CHANGE_FLG as CHANGE_FLG
from
	BC23BLCK BC23
/*IF changeDataOnly == true */
WHERE CHANGE_FLG <> '1'
/*END*/
order by BLOCK_CD
