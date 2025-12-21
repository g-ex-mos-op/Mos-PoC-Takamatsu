select
	rtrim(BT27.COMPANY_CD)    as COMPANY_CD,
	rtrim(BT27.MISE_CD)       as MISE_CD,
    rtrim(BM01.MISE_NAME_KJ)  as MISE_NAME_KJ,
    rtrim(BM01.SIBU_CD)       as SIBU_CD,
    rtrim(BM10.SIBU_NAME)     as SIBU_NAME,
    rtrim(BC23.BLOCK_NAME)    as BLOCK_NAME,
	rtrim(URL)                as URL
from
	BT27MURL BT27,
	(SELECT CNT_DATE FROM BR33CDAY WHERE DAY_KBN = '02') BR33,
    BM01TENM BM01	
	    left join BM10GSIB BM10 on (BM01.COMPANY_CD = BM10.COMPANY_CD and BM01.SIBU_CD = BM10.SIBU_CD) 
		left join BC23BLCK BC23 on (BM01.BLOCK_CD = BC23.BLOCK_CD)
where
	BT27.COMPANY_CD = BM01.COMPANY_CD
and BT27.MISE_CD    = BM01.MISE_CD
and BT27.COMPANY_CD = /*companyCd*/'00'
/*IF sibuCd != null */AND BM01.SIBU_CD = /*sibuCd*/'081'/*END*/
/*IF closeFlg != null */
and BM01.CLOSE_DT >= BR33.CNT_DATE
/*END*/
order by 
    BM01.SIBU_CD,
    BM01.BLOCK_CD,
    BT27.MISE_CD