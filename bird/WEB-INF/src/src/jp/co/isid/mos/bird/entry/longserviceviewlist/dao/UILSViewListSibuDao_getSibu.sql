select
    rtrim(BM10.COMPANY_CD) as COMPANY_CD,
    rtrim(BM10.SIBU_CD) as SIBU_CD,
    rtrim(BM10.SIBU_NAME) as SIBU_NAME
from
    BM10GSIB as BM10
    /*IF limit*/
    inner join BM51SVSB as BM51 on BM51.COMPANY_CD = BM10.COMPANY_CD and BM51.SIBU_CD = BM10.SIBU_CD 
    /*END*/
where
    BM10.COMPANY_CD = /*companyCd*/'00' and
    BM10.HONBU_CD < '20000'
    /*IF limit*/
    and BM51.COMPANY_CD = /*companyCd*/'00'
    and BM51.SV_CD = /*userId*/'99990003'
    /*END*/
order by
	SIBU_CD