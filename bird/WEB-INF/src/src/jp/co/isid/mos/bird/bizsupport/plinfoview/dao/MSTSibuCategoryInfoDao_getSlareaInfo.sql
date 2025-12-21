select distinct 
	rtrim(SLAREA_CD) as SLAREA_CD,
	rtrim(SLAREA_NAME) as SLAREA_NAME
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
	SLAREA_CD 