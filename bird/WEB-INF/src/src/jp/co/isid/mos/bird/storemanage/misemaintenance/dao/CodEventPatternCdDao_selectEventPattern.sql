select
	rtrim(EVENT_CD) as EVENT_CD ,
	rtrim(GYOTAI_KBN) as GYOTAI_KBN ,
	rtrim(EVENT_PATTERN_CD) as EVENT_PATTERN_CD ,
	rtrim(EVENT_PATTERN_NAME) as EVENT_PATTERN_NAME 
from
	BC24EVPT BC24
where
    EVENT_CD   = /*eventCd*/'04'
    and GYOTAI_KBN = /*gyotaiKbn*/'010'
order by 
	EVENT_CD,EVENT_PATTERN_CD