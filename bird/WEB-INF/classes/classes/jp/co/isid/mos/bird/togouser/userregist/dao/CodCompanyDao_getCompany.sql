select distinct
    BC02.R_COMPANY_CD AS COMPANY_CD
,   BC02.COMPANY_NAME
,   BC02.R_COMPANY_CD concat 'F' concat rtrim(BC02.COMPANY_NAME) as COMPANY_HYOUJI
from 
    BC02COMP BC02 
    inner join (select R_COMPANY_CD, count(*) from BC08CBMN group by R_COMPANY_CD having count(*) >= 1 ) BC08
    on BC02.R_COMPANY_CD = BC08.R_COMPANY_CD
/*IF companyCd != ""*/
where
   BC02.R_COMPANY_CD = /*companyCd*/
/*END*/
order by
    BC02.R_COMPANY_CD