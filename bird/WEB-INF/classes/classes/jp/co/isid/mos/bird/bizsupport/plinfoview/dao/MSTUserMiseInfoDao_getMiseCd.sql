SELECT
    rtrim(MISE_CD) as MISE_CD,
    case when CLOSE_DT > /*sysDate*/'20060507' 
    then RTRIM(CHAR(REPLACE(MISE_NAME_KJ , '  ', '  '), 40)) 
    else RTRIM(CHAR(REPLACE(MISE_NAME_KJ , '  ', '  '), 40)) CONCAT 'ÅiCLOSEÅj' 
    end as MISE_NAME_KJ 
FROM 
    BM01TENM
WHERE
	COMPANY_CD = /*companyCd*/'00'
and ONER_CD = /*onerCd*/'36378' 
ORDER BY
	MISE_CD