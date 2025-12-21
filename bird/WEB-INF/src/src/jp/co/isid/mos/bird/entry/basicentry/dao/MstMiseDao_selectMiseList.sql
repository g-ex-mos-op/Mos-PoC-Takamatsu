select
	rtrim(COMPANY_CD) as COMPANY_CD, 
	rtrim(MISE_CD) as  MISE_CD,
	rtrim(MISE_NAME_KJ) as MISE_NAME_KJ ,
	rtrim(MISE_NAME_KNA) as  MISE_NAME_KNA,
	rtrim(MISE_POST_NO) as MISE_POST_NO,
	rtrim(MISE_ADRS1) as MISE_ADRS1,
	rtrim(MISE_ADRS2) as MISE_ADRS2,
	rtrim(MISE_ADRS3) as MISE_ADRS3,
	rtrim(ONER_CD) as  ONER_CD
from
	BM01TENM
where
    COMPANY_CD = /*companyCd*/'00' 
and ONER_CD = /*onerCd*/'36478' 
and CLOSE_DT > /*sysDt*/'20060707' 
order by 
    MISE_CD