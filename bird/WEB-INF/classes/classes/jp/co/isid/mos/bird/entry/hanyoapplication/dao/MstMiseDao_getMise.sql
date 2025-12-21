select 
	BM01.COMPANY_CD,
	BM01.MISE_CD,
	rtrim(BM01.mise_post_no) as MISE_POST_NO,
	rtrim(BM01.mise_name_kj) as MISE_NAME_KJ,
	rtrim(BM01.mise_name_kna) as MISE_NAME_KNA,
	rtrim(BM01.mise_adrs1) as MISE_ADRS1,
	rtrim(BM01.mise_adrs2) as MISE_ADRS2,
	rtrim(BM01.mise_adrs3) as MISE_ADRS3,
	BM01.ONER_CD,
	rtrim(BM11.ONER_NAME_KJ) as ONER_NAME_KJ,
	rtrim(BM01.r_sakujo_flg) as R_SAKUJO_FLG, 
	rtrim(BM01.mise_adrs1) concat ' ' concat rtrim(BM01.mise_adrs2) concat ' ' concat rtrim(BM01.mise_adrs3) as MISE_ADRS 
from 
	BM01TENM BM01,
	BM11ONER BM11 
where 
	BM01.COMPANY_CD = /*companyCd*/'00' 
and BM01.COMPANY_CD = BM11.COMPANY_CD 
and BM01.ONER_CD = BM11.ONER_CD 
and BM01.MISE_CD in (
		select 
			distinct MISE_CD_1 
		from 
			BM12STAF 
		where 
			COMPANY_CD = /*companyCd*/'00' 
		and ONER_CD = /*onerCd*/'36478') 
and BM01.CLOSE_DT > /*sysDt*/'20060101' 
and BM01.R_SAKUJO_FLG <> '1'