select
	rtrim(COMPANY_CD) as COMPANY_CD,
	rtrim(MISE_CD) as MISE_CD,
	TO_KAISU,
	rtrim(TO_DATE) as TO_DATE,
	rtrim(OLD_ONER_CD) as OLD_ONER_CD,
	rtrim(OLD_ONER_NAME) as OLD_ONER_NAME,
	rtrim(FIRST_USER) as FIRST_USER,
	rtrim(FIRST_PGM) as FIRST_PGM,
	FIRST_TMSP,
	rtrim(LAST_USER) as LAST_USER,
	rtrim(LAST_PGM) as LAST_PGM,
	LAST_TMSP
from
	BM19TKOV
where
	COMPANY_CD = /*companyCd*/'00'
and MISE_CD = /*miseCd*/'01776' 
order by
	TO_KAISU DESC 