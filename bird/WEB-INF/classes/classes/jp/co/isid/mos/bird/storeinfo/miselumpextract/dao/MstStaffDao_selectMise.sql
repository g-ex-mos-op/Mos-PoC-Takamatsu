select
	rtrim(BM01.COMPANY_CD)      as COMPANY_CD,
	rtrim(BM01.ONER_CD)         as ONER_CD,
	rtrim(BM11.ONER_NAME_KJ)    as ONER_NAME_KJ,
	rtrim(BM01.MISE_CD)         as MISE_CD_1,
	rtrim(BM01.MISE_NAME_KJ)    as MISE_NAME_KJ,
	rtrim(BM01.SIBU_CD)         as SIBU_CD,
    rtrim(BM10.SIBU_NAME)       as SIBU_NAME
from
    BM11ONER BM11,
    BM01TENM BM01
        left join BM10GSIB BM10 on (BM01.COMPANY_CD  = BM10.COMPANY_CD and BM01.SIBU_CD  = BM10.SIBU_CD) ,
    (SELECT CNT_DATE FROM BR33CDAY WHERE DAY_KBN = '02') BR33
where
	    BM01.ONER_CD    = BM11.ONER_CD
    and BM01.COMPANY_CD = /*companyCd*/'00'
	/*IF sibuCd != null */
	    and BM01.SIBU_CD = /*sibuCd*/'081'/*END*/
	/*IF closeFlg != null */
	    and BM01.CLOSE_DT >= BR33.CNT_DATE/*END*/
order by 
    BM01.SIBU_CD,
    BM01.ONER_CD,
    BM01.MISE_CD