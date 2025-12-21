select
	BM06.USER_ID,
	BM06.COMPANY_CD,
	BM06.ONER_CD

from
	BM06UONR BM06
	
where
	BM06.USER_ID    = /*userId*/'99990003' and
	BM06.COMPANY_CD = /*companyCd*/'00'