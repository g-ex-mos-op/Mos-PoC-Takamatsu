SELECT '00' as COMPANY_CD	
,           USER_ID as SV_CD	
,      rtrim(USER_NAME_KJ) AS SV_NAME_KJ	
,      rtrim(USER_NAME_KANA) as SV_NAME_KNA	
	
FROM   BR01USER	
	
WHERE  USER_ID = /*svCd*/'00000085'	
