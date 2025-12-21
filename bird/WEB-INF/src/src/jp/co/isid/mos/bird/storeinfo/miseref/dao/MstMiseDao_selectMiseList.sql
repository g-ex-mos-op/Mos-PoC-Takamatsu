select
	rtrim(COMPANY_CD) as COMPANY_CD, 
	rtrim(MISE_CD) as  MISE_CD,
	rtrim(MISE_NAME_KJ) as MISE_NAME_KJ ,
	rtrim(MISE_NAME_KNA) as  MISE_NAME_KNA,
	rtrim(ONER_CD) as  ONER_CD
from
	BM01TENM
where
    COMPANY_CD = /*companyCd*/'01' 
and ONER_CD = /*onerCd*/'01776'
order by 
    MISE_CD