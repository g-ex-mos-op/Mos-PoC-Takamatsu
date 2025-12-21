select
	rtrim(COMPANY_CD) as COMPANY_CD,
	rtrim(MISE_CD) as MISE_CD,
	KAISO_KAISU,
	rtrim(KAISO_HOSHU) as KAISO_HOSHU,
	rtrim(KAISO_STA) as KAISO_STA,
	rtrim(KAISO_END) as KAISO_END,
	KAISO_HIYO,
	rtrim(KAISO_NAIYO) as KAISO_NAIYO,
	rtrim(FIRST_USER) as FIRST_USER,
	rtrim(FIRST_PGM) as FIRST_PGM,
	FIRST_TMSP,
	rtrim(LAST_USER) as LAST_USER,
	rtrim(LAST_PGM) as LAST_PGM,
	LAST_TMSP
from
	BM18MKAI
where
	COMPANY_CD = /*companyCd*/'00'
and MISE_CD = /*miseCd*/'01776' 
order by 
	KAISO_KAISU DESC