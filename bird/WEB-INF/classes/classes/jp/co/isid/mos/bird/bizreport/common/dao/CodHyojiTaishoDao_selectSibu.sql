select distinct rtrim(BM10.SIBU_CD) as  HYOJITAISHO_CD
,      rtrim(BM10.SIBU_NAME) as  HYOJITAISHO_NAME
from   BM10GSIB BM10
/*IF userTypeCd == "02" */
,      BM06UONR BM06
,      BM01TENM BM01
/*END*/
where  BM10.COMPANY_CD = /*companyCd*/'00'
/*IF limitFlg == true && userTypeCd == '01' */
AND    BM10.SIBU_CD IN (
           SELECT   BM51.SIBU_CD
           FROM     BM51SVSB AS BM51
           WHERE    BM51.SV_CD = /*userId*/'00000921'
           GROUP BY BM51.SIBU_CD
       )
/*END*/
/*IF userTypeCd == "02" */
and    BM06.USER_ID = /*userId*/'99990002'
and    BM01.COMPANY_CD = BM10.COMPANY_CD
and    BM06.COMPANY_CD = BM01.COMPANY_CD
and    BM01.ONER_CD = BM06.ONER_CD
and    BM10.SIBU_CD = BM01.SIBU_CD
/*END*/
order by HYOJITAISHO_CD