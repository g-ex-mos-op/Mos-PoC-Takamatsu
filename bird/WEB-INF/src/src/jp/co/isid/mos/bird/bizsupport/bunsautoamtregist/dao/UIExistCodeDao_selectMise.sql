SELECT COMPANY_CD	
   ,   MISE_CD	                                            AS TARGET_CD
   ,   RTRIM(CHAR(REPLACE(MISE_NAME_KJ , '  ', '  '), 40))  AS TARGET_NAME_KJ 	
   ,   RTRIM(CHAR(REPLACE(MISE_NAME_KNA , '  ', '  '), 40)) AS TARGET_NAME_KNA 	
FROM   BM01TENM

WHERE COMPANY_CD = /*companyCd*/'00'	
AND   MISE_CD = /*taishoCd*/'00171'
