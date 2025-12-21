select distinct rtrim(BM10.SIBU_CD) as  SIBU_CD
,      rtrim(BM10.SIBU_NAME) as  SIBU_NAME
from   BM10GSIB BM10
,      BM01TENM BM01
,      BS04MSPS BS04 
where  BM10.COMPANY_CD = /*companyCd*/'00'
/*IF limitFlg == true && userTypeCd == '01' */
AND    BM01.SIBU_CD IN (
           SELECT   BM51.SIBU_CD
           FROM     BM51SVSB AS BM51
           WHERE    BM51.COMPANY_CD = /*companyCd*/'00'
           AND      BM51.SV_CD = /*userId*/'00000921'
           GROUP BY BM51.SIBU_CD
       )
/*END*/
and    BM01.COMPANY_CD = BM10.COMPANY_CD
/*IF searchType == 'SIBU' */
and    BM01.SIBU_CD = /*sibuCd*/'031'
/*END*/
/*IF searchType == 'ONER' */
and    BM01.SIBU_CD IN (
           SELECT SIBU_CD 
           FROM BM11ONER
           WHERE COMPANY_CD = /*companyCd*/'00'
           AND   ONER_CD = /*onerCd*/'36478'
       )
/*END*/
/*IF searchType == 'MISE' */
and    BM01.MISE_CD = /*miseCd*/'01776'
/*END*/
AND    BS04.NENDO = /*nendo*/'2006' 
AND    BS04.KAI = /*kai*/'01' 
AND    BS04.COMPANY_CD = BM01.COMPANY_CD
and    BM10.SIBU_CD = BM01.SIBU_CD
AND    BS04.MISE_CD = BM01.MISE_CD
order by SIBU_CD