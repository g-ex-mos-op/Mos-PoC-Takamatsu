select
	rtrim(BM11.COMPANY_CD) as COMPANY_CD,
	rtrim(BM11.ONER_CD) as ONER_CD,
	rtrim(BM11.ONER_NAME_KJ) as ONER_NAME_KJ,
	rtrim(BM11.ONER_NAME_KNA) as ONER_NAME_KNA,
	rtrim(BM14.ONER_SUB_KJ) as ONER_SUB_KJ, 
	rtrim(BM11.SEIKYU_POST_NO) as SEIKYU_POST_NO,
	rtrim(BM11.SEIKYU_ADRS1) as SEIKYU_ADRS1,
	rtrim(BM11.SEIKYU_ADRS2) as SEIKYU_ADRS2,
	rtrim(BM11.SEIKYU_ADRS3) as SEIKYU_ADRS3,
	rtrim(BM11.SEIKYU_NAME) as SEIKYU_NAME ,
	rtrim(BM11.ONER_TEL) as ONER_TEL
from
	BM11ONER BM11 
	left join 
	(select COMPANY_CD, ONER_CD, ONER_SUB_KJ 
	from    BM14DAIH
	where   COMPANY_CD = /*companyCd*/'00' 
	and     ONER_CD = /*onerCd*/'36478'
	and     ONER_M_DATE = (select max(ONER_M_DATE) 
				     from BM14DAIH 
				     where COMPANY_CD = /*companyCd*/'00' 
				     and   ONER_CD = /*onerCd*/'36478')
	) BM14 
	on (BM11.COMPANY_CD = BM14.COMPANY_CD and BM11.ONER_CD = BM14.ONER_CD) 
where
    BM11.COMPANY_CD = /*companyCd*/'00' 
and BM11.ONER_CD = /*onerCd*/'36478'