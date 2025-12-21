select 
	rtrim(BM10.COMPANY_CD) as COMPANY_CD,
	rtrim(BM10.SIBU_CD) as SIBU_CD,
	rtrim(BM10.SIBU_NAME) as SIBU_NAME,
	rtrim(BM10.HONBU_CD) as HONBU_CD,
	rtrim(BM10.HONBU_NAME) as HONBU_NAME,
	rtrim(BM10.JIGYOU_CD) as JIGYOU_CD,
	rtrim(BM10.JIGYOU_NAME) as JIGYOU_NAME,
	rtrim(BM10.SLAREA_CD) as SLAREA_CD,
	rtrim(BM10.SLAREA_NAME) as SLAREA_NAME 
from 
	BM10GSIB BM10 
/*IF isLimit == true*/
  , BM51SVSB BM51 
/*END*/
where 
    BM10.COMPANY_CD = /*companyCd*/'00' 
and BM10.HONBU_CD < '20000' 
/*IF isLimit == true*/
and BM10.COMPANY_CD = BM51.COMPANY_CD 
and BM10.SIBU_CD = BM51.SIBU_CD 
and BM51.SV_CD = /*userId*/'00000921'
/*END*/ 
order by 
	SIBU_CD