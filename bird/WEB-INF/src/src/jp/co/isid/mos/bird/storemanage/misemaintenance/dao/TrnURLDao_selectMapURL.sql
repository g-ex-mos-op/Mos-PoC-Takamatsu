select
	rtrim(COMPANY_CD) as COMPANY_CD,
	rtrim(MISE_CD) as MISE_CD,
	rtrim(URL) as URL,
	rtrim(LAST_USER) as LAST_USER,
	rtrim(LAST_PGM) as LAST_PGM,
	LAST_TMSP as LAST_TMSP 
from
	BT27MURL  
where
	COMPANY_CD = /*companyCd*/'01'
and MISE_CD = /*miseCd*/'01776'