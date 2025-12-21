select
    distinct(rtrim(BM01.SIBU_CD)) as SIBU_CD
   ,rtrim(BM10.SIBU_NAME)  as SIBU_NAME
from
    BM01TENM as BM01, 
    BM10GSIB as BM10
    /*IF limit*/
    inner join BM51SVSB as BM51 on BM51.COMPANY_CD = BM10.COMPANY_CD and BM51.SIBU_CD = BM10.SIBU_CD and BM51.SV_CD =/*userId*/'99990003'
    /*END*/
where
    BM01.COMPANY_CD = /*companyCd*/'00' and
    BM01.COMPANY_CD = BM10.COMPANY_CD and
    BM01.SIBU_CD = BM10.SIBU_CD
order by
	SIBU_CD