select 
  rtrim(COMPANY_CD) AS COMPANY_CD
, rtrim(SIBU_CD) AS SIBU_CD
, rtrim(SIBU_NAME) AS SIBU_NAME
, rtrim(HONBU_CD) AS HONBU_CD
, rtrim(HONBU_NAME) AS HONBU_NAME
, rtrim(JIGYOU_CD) AS JIGYOU_CD
, rtrim(JIGYOU_NAME) AS JIGYOU_NAME
, rtrim(SLAREA_CD) AS SLAREA_CD
, rtrim(SLAREA_NAME) AS SLAREA_NAME
, rtrim(AREA_DAI_FLG) AS AREA_DAI_FLG
from
  BM57NSIB
  /*IF companyCd != null*/  
	where
	  	COMPANY_CD = /*companyCd*/
  /*END*/
order by
  COMPANY_CD, SIBU_CD