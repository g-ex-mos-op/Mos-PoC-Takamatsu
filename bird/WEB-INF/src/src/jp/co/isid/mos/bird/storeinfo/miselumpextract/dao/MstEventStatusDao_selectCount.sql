select 
    rtrim(CNTTBL.EVENT_BNRUI) as EVENT_BNRUI ,
	MAX(CNTTBL.CNT)           as EVENT_MAX_CNT 
from 
	(select 
	    EVENT_BNRUI ,
	    GYOTAI_KBN ,
	    COUNT(EVENT_CD) as CNT 
	from BC16EVNT 
	group by GYOTAI_KBN,EVENT_BNRUI ) CNTTBL,
	
	(select 
	    distinct 
		rtrim(BM01.MISE_GRP1)   as MISE_GRP1
	from 
		(SELECT CNT_DATE FROM BR33CDAY WHERE DAY_KBN = '02') BR33,
		BM22EVNT BM22,
		BM01TENM BM01
	where
		    BM22.COMPANY_CD = BM01.COMPANY_CD
		and BM22.MISE_CD    = BM01.MISE_CD
		and BM22.COMPANY_CD = /*companyCd*/'60'
	/*IF sibuCd != null */
	    and BM01.SIBU_CD = /*sibuCd*/'008'/*END*/
	/*IF closeFlg != null */
	    and BM01.CLOSE_DT >= BR33.CNT_DATE/*END*/
	group by 
	    BM01.MISE_GRP1) GTAITBL
	
where 
    CNTTBL.GYOTAI_KBN = GTAITBL.MISE_GRP1
group by EVENT_BNRUI
order by EVENT_BNRUI
