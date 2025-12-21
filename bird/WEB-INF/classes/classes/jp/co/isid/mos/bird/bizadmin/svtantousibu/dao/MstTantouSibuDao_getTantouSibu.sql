select
	company_cd,
	sv_cd,
	sibu_cd,
	first_user,
	first_pgm,
	first_tmsp,
	last_user,
	last_pgm,
	last_tmsp 
from
	bm51svsb 
where 
	company_cd = /*companyCd*/'00' 
and sv_cd = /*userId*/'99990001' 
order by 
	sibu_cd