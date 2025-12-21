select
	rtrim(BM10.SIBU_CD) as SIBU_CD,
	rtrim(BM10.SIBU_NAME) as SIBU_NAME
from
	BM10GSIB BM10
where
    BM10.COMPANY_CD = '00'
	/*IF shukeKubu.equals("AREA_DAI_CD")*/
	AND BM10.AREA_DAI_FLG = '1'
	/*END*/
order by BM10.SIBU_CD asc
