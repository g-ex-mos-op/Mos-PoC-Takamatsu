select
	BM01.COMPANY_CD,
	BM01.MISE_CD,
	BM01.AREA_DAI,
	rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ,
	BM01.ONER_CD,
	BM01.OPEN_DT,
	BM01.LOCATE_KBN,
	BM01.MISE_KEITAI,
	(case when BT17.URIAGEDAKA is null
	      then 0 else BT17.URIAGEDAKA end) as URIAGEDAKA,
	BM01.CLOSE_DT
		
from
	BM01TENM BM01
	left join (select *
	             from BT17PLDT
	            where PL_YM = /*zenMonthYYYYMM*/'200601'
	              and PL_TYPE = '1'
	              and ERR_FLG = '0' 
	          ) BT17
	       on (BM01.MISE_CD = BT17.MISE_CD   and
	           BM01.COMPANY_CD = BT17.COMPANY_CD)
/*IF limitFlg == true && userTypeCd.equals("01")*/
  , BM51SVSB BM51 
/*END*/

where
	BM01.MISE_CD = /*miseCd*/'01776' and
	BM01.COMPANY_CD = '00'
/*IF limitFlg == true && userTypeCd.equals("01")*/
and BM01.COMPANY_CD = BM51.COMPANY_CD 
and BM01.SIBU_CD = BM51.SIBU_CD 
and BM51.SV_CD = /*userId*/'00000921'
/*END*/
	
order by BT17.MISE_CD