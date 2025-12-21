select
	rtrim(HDC_DT) as HDC_DT,
	rtrim(HDC_NAIYOU) as HDC_NAIYOU
from
	BM27HDCR BM27
where
	COMPANY_CD = /*companyCd*/'01'
and MISE_CD = /*miseCd*/'01776'
order by 
	HDC_DT