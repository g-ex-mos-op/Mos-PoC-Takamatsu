SELECT
    R_COMPANY_CD
   ,BUMON_CD
   ,BUMON_CD concat 'ÅF' concat rtrim(BUMON_NAME) as BUMON_NAME
FROM
    BC08CBMN BC08
    inner join
      ( select * from BR27BMUT where USERTYPE_CD = '01' ) BR27
    on
      (BR27.BUMON_CD_STF_F <= substr(BC08.BUMON_CD, 4, 5)
       and substr(BC08.BUMON_CD, 4, 5) <= BR27.BUMON_CD_STF_T)
WHERE
	R_COMPANY_CD = /*R_COMPANY_CD*/
GROUP BY
    R_COMPANY_CD
   ,BUMON_CD
   ,BUMON_NAME
ORDER BY
    R_COMPANY_CD
   ,BUMON_CD