select
	rtrim(INSENTIVE_CD) as INSENTIVE_CD,
	rtrim(INSENTIVE_NAME) as INSENTIVE_NAME
from
	BC17INSE BC17
where
	COMPANY_CD = /*companyCd*/'01'
order by 
	INSENTIVE_CD