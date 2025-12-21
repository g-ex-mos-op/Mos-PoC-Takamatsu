select distinct BM10.COMPANY_CD
,      rtrim(BM10.SIBU_CD) as  SIBU_CD
,      rtrim(BM10.SIBU_NAME) as  SIBU_NAME
from   BM10GSIB BM10
,      BM01TENM BM01
where  BM10.COMPANY_CD = /*companyCd*/'00'
/*IF limitFlg == true*/
AND    BM01.SIBU_CD IN (
           SELECT   BM51.SIBU_CD
           FROM     BM51SVSB AS BM51
           WHERE    BM51.SV_CD = /*userId*/'00000921'
           GROUP BY BM51.SIBU_CD
       )
/*END*/
and    BM01.COMPANY_CD = BM10.COMPANY_CD
and    BM10.SIBU_CD = BM01.SIBU_CD
order by SIBU_CD