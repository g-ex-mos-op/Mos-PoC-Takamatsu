select
	bm01.company_cd,
	bm01.mise_cd,
	rtrim(bm01.mise_name_kj) as mise_name_kj,
	bm01.sibu_cd,
	bm10.sibu_name,
	bm01.block_cd,
	bc23.block_name,
	bm01.close_dt 
from
	bm01tenm bm01,
	bm10gsib bm10,
	bc23blck bc23
where 
	bm01.company_cd = bm10.company_cd
and bm01.sibu_cd = bm10.sibu_cd
and bm01.block_cd = bc23.block_cd
and bm01.company_cd = /*companyCd*/'00'
and bm01.mise_cd = /*miseCd*/'01776'