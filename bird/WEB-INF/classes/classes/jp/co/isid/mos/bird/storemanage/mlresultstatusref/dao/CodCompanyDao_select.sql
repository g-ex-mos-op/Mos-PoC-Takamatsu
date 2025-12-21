select BC02.r_company_cd as COMPANY_CD
,      rtrim(BC02.company_name) AS COMPANY_NAME
from   BM03USCP BM03
,      BC02COMP BC02
where  BM03.USER_ID = /*userId*/'99990003'
and    BC02.MISE_FLG = '1'
and    BC02.R_COMPANY_CD = '00'
and    BC02.R_COMPANY_CD = BM03.R_COMPANY_CD
order by    BC02.sort_seq