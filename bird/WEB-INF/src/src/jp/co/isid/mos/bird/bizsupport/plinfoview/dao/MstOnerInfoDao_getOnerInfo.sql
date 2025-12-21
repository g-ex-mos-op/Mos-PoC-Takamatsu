select 
	COMPANY_CD,
	rtrim(ONER_CD) as ONER_CD,
	rtrim(ONER_KBN) as ONER_KBN,
	rtrim(ONER_NAME_KJ) as ONER_NAME_KJ
from 
	BM11ONER
where
	COMPANY_CD = /*companyCd*/'00' 
and ONER_CD = /*onerCd*/'36478'