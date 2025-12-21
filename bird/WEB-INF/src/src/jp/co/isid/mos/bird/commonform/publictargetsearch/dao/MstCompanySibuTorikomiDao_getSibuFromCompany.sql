select 
      BM10.COMPANY_CD
,     BM10.SIBU_CD
,     rtrim(BM10.SIBU_NAME) as SIBU_NAME
,     BM10.AREA_DAI_FLG
from 
      BM10GSIB BM10
where 
      BM10.COMPANY_CD=/*companyCd*/'00'
AND   BM10.AREA_DAI_FLG <> '0'      
order by
      BM10.SIBU_CD