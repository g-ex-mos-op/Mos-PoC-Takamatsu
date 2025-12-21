select 
	BM12.STAFF_ID,
	BM12.COMPANY_CD,
	BM12.ONER_CD,
	rtrim(BM12.OLD_ONER_CD) as OLD_ONER_CD,
	rtrim(BM12.MISE_CD_1) as MISE_CD,
	rtrim(BM12.STAFF_L_NAME_KJ) as STAFF_L_NAME_KJ,
	rtrim(BM12.STAFF_F_NAME_KJ) as STAFF_F_NAME_KJ,
	rtrim(BM12.STAFF_L_NAME_KNA) as STAFF_L_NAME_KNA,
	rtrim(BM12.STAFF_F_NAME_KNA) as STAFF_F_NAME_KNA,
	rtrim(BM12.SEX) as SEX,
	rtrim(BM12.BIRTHDAY) as BIRTHDAY,
	rtrim(BM12.SITUATION_KBN) as SITUATION_KBN,
	rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ,
	rtrim(DISPFLG.FLG) as ENTRY_FLG, 
	rtrim(BM12.STAFF_L_NAME_KJ)
		concat '  ' 
		concat rtrim(BM12.STAFF_F_NAME_KJ) 
		concat 'Åi' 
		concat rtrim(BM01.MISE_NAME_KJ) 
        concat 'Åj' as STAFF_SELECT 
from
	BM12STAF BM12
	left join
	(
		select
			STAFF_ID,
			'1' as FLG 
		from
			BT22ENKN
		where
			ENTRY_CD = /*entryCd*/'05'
		and ENTRY_YEAR = /*entryYear*/'2006'
		and ENTRY_KAI = /*entryKai*/'044'
		and ONER_CD =/*onerCd*/'36478'
	) DISPFLG 
	on BM12.STAFF_ID = DISPFLG.STAFF_ID,
	BM01TENM BM01 
where 
	BM12.COMPANY_CD = BM01.COMPANY_CD
and BM12.MISE_CD_1 = BM01.MISE_CD 
and BM12.COMPANY_CD = /*companyCd*/'00' 
and BM12.ONER_CD = /*onerCd*/'36478' 
and BM12.SITUATION_KBN <> '2' 
order by
	MISE_CD, BM12.STAFF_ID