select
    rtrim(BM10.COMPANY_CD) as COMPANY_CD
   ,rtrim(BM10.SIBU_CD)    as SIBU_CD
   ,rtrim(BM10.SIBU_NAME)  as SIBU_NAME
   ,rtrim(HONBU_CD)        as HONBU_CD
   ,rtrim(HONBU_NAME)      as HONBU_NAME
   ,rtrim(JIGYOU_CD)       as JIGYOU_CD
   ,rtrim(JIGYOU_NAME)     as JIGYOU_NAME
   ,rtrim(SLAREA_CD)       as SLAREA_CD
   ,rtrim(SLAREA_NAME)     as SLAREA_NAME
   ,rtrim(AREA_DAI_FLG)    as AREA_DAI_FLG
from
    BM10GSIB as BM10
    /*IF limit*/
    inner join BM51SVSB as BM51 on BM51.COMPANY_CD = BM10.COMPANY_CD and BM51.SIBU_CD = BM10.SIBU_CD and BM51.SV_CD =/*userId*/'99990003'
    /*END*/
where
    BM10.COMPANY_CD = /*companyCd*/'00' and
    BM10.AREA_DAI_FLG = '1' 
order by
	SIBU_CD
