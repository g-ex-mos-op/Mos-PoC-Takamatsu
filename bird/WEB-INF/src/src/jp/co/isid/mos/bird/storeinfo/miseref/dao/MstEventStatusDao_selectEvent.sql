select 
    rtrim(BC19.EVENT_BNRUI) as EVENT_BNRUI,
    rtrim(BC19.EVENT_BNRUI_NAME) as EVENT_BNRUI_NAME,
    rtrim(BC16.EVENT_CD) as EVENT_CD,
    rtrim(BC16.EVENT_NAME) as EVENT_NAME,
	rtrim(BM22.COMPANY_CD) as COMPANY_CD,
	rtrim(BM22.MISE_CD) as MISE_CD,
	rtrim(BM22.EVENT_CD) as EVENT_CD,
	rtrim(BC16.EVENT_NAME) as EVENT_NAME,
	rtrim(BM22.EVENT_STATUS) as EVENT_STATUS,
	rtrim(BM22.EVENT_START_DT) as EVENT_START_DT,
	rtrim(BM22.EVENT_END_DT) as EVENT_END_DT,
	rtrim(BC16.EVENT_SORT_CD) as EVENT_SORT_CD,
	rtrim(BM01.GYOTAI_KBN) as GYOTAI_KBN,
	rtrim(BM22.LAST_USER) as LAST_USER,
	rtrim(BM22.LAST_PGM) as LAST_PGM,
	BM22.LAST_TMSP as LAST_TMSP,
    rtrim(BM22.EVENT_PATTERN_CD)   as EVENT_PATTERN_CD,
    rtrim(BC24.EVENT_PATTERN_NAME) as EVENT_PATTERN_NAME,
    rtrim(BM22.HENKO_DT) as HENKO_DT,
    rtrim(BM22.NOTES) as NOTES
from 
    BM01TENM BM01,
    BC19EVBN BC19,
    BC16EVNT BC16
        left join BM22EVNT BM22 on (BC16.EVENT_CD = BM22.EVENT_CD
                                and BM22.COMPANY_CD = /*companyCd*/'00'
                                and BM22.MISE_CD = /*miseCd*/'01776') 
                                
        left join (select * from BC24EVPT, BM01TENM 
                      where BM01TENM.MISE_GRP1   = BC24EVPT.GYOTAI_KBN 
                         and BM01TENM.COMPANY_CD = /*companyCd*/'00'
                         and BM01TENM.MISE_CD    = /*miseCd*/'01776') BC24 
                  on (BC24.EVENT_CD   = BM22.EVENT_CD
                  and BC24.EVENT_PATTERN_CD = BM22.EVENT_PATTERN_CD 
                  and BM22.COMPANY_CD = /*companyCd*/'00'
                  and BM22.MISE_CD    = /*miseCd*/'01776')
where 
    BM01.COMPANY_CD = /*companyCd*/'00'
and BM01.MISE_CD = /*miseCd*/'01776'
and BC16.EVENT_BNRUI = BC19.EVENT_BNRUI
and BM01.MISE_GRP1 = BC16.GYOTAI_KBN 
order by 
	BC19.EVENT_BNRUI,
	BC16.EVENT_SORT_CD