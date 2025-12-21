select rtrim(BM10.SIBU_CD) as SIBU_CD
,      rtrim(BM10.SIBU_NAME) as SIBU_NAME
from   BM10GSIB BM10
where  BM10.COMPANY_CD = /*companyCd*/'00'