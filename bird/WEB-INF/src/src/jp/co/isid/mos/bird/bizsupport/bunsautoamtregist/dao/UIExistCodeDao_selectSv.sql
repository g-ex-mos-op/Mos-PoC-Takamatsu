SELECT '00' as COMPANY_CD
,           USER_ID	                                     AS TARGET_CD
,      RTRIM(CHAR(REPLACE(USER_NAME_KJ, '  ', '  '), 80))  AS TARGET_NAME_KJ	
,      RTRIM(CHAR(REPLACE(USER_NAME_KANA, '  ', '  '), 80)) AS TARGET_NAME_KNA	
	
FROM   BR01USER	
	
WHERE  USER_ID = /*taishoCd*/'00000085'	
