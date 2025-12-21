select rtrim(BM01.MISE_CD) as  MISE_CD
,      rtrim(BM01.MISE_NAME_KJ) as  MISE_NAME_KJ
from   BM01TENM BM01
/*IF svCd != null && svCd != '' */
,      BM50TANM BM50
/*END*/
/*IF limitFlg == true && userTypeCd == '01' */
,      (
           SELECT   BM51.COMPANY_CD
           ,        BM51.SIBU_CD
           ,        BM51.SV_CD
           FROM     BM51SVSB AS BM51
           WHERE    BM51.COMPANY_CD = /*companyCd*/'00'
           AND      BM51.SV_CD = /*userId*/'00000921'
           GROUP BY BM51.COMPANY_CD
           ,        BM51.SIBU_CD
           ,        BM51.SV_CD
       ) LIMITDATA
/*END*/
where BM01.COMPANY_CD = /*companyCd*/'00'
AND   BM01.SIBU_CD = /*sibuCd*/('011')
/*IF svCd != null && svCd != '' */
AND   BM50.SV_CD = /*svCd*/'00000921'
AND   BM50.COMPANY_CD = BM01.COMPANY_CD
AND   BM50.MISE_CD    = BM01.MISE_CD
/*END*/
/*IF limitFlg == true && userTypeCd == '01' */
AND   LIMITDATA.COMPANY_CD = BM01.COMPANY_CD
AND   LIMITDATA.SIBU_CD = BM01.SIBU_CD
/*END*/

order by MISE_CD