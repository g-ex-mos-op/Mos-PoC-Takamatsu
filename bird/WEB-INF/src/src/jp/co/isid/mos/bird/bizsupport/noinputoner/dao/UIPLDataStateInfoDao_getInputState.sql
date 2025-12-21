select 
    PL_TYPE,
    PL_YM,
    COMPANY_CD,
    ONER_CD,
    ONER_NAME_KJ,
    MISE_CD,
    MISE_NAME_KJ, 
    ERR_FLG 
from (
    
    select 
    	rtrim(BT17.PL_TYPE) as PL_TYPE,
    	rtrim(BT17.PL_YM) as PL_YM,
    	rtrim(BT17.COMPANY_CD) as COMPANY_CD,
    	rtrim(BT17.ONER_CD) as ONER_CD,
    	rtrim(BM11.ONER_NAME_KJ) as ONER_NAME_KJ,
    	rtrim(BT17.MISE_CD) as MISE_CD,
    	rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ, 
    	rtrim(BT17.ERR_FLG) as ERR_FLG 
    from 
    	BT17PLDT BT17,
    	BM01TENM BM01,
    	BM11ONER BM11 
    where 
        (BT17.PL_TYPE = '1' and 
         BT17.MISE_CD = BM01.MISE_CD and
         BT17.COMPANY_CD = BM01.COMPANY_CD
         /*IF sibuCd != null && sibuCd != '' */and BM01.SIBU_CD = /*sibuCd*/'051'/*END*/)
    and BT17.ONER_CD = BM11.ONER_CD 
    and BT17.COMPANY_CD = /*companyCd*/'00' 
    and BT17.COMPANY_CD = BM01.COMPANY_CD 
    and BT17.COMPANY_CD = BM11.COMPANY_CD 
    and BT17.PL_YM between /*nengetuFrom*/'200601' and /*nengetuTo*/'200603' 
    and BM01.CLOSE_DT >= /*nengetuFrom*/'200603' 
    /*IF sibuCd != null && sibuCd != '' */and BM01.SIBU_CD =  /*sibuCd*/'051'/*END*/
    /*IF !closeMiseFlg */and BM01.CLOSE_DT > /*nengetuTo*/'200603'
    and BT17.ONER_CD in (select ONER_CD from BM11ONER where KEIYAKU_END > /*nengetuTo*/'200603')
    /*END*/
    
    union all
    
    select 
    	rtrim(BT17.PL_TYPE) as PL_TYPE,
    	rtrim(BT17.PL_YM) as PL_YM,
    	rtrim(BT17.COMPANY_CD) as COMPANY_CD,
    	rtrim(BT17.ONER_CD) as ONER_CD,
    	rtrim(BM11.ONER_NAME_KJ) as ONER_NAME_KJ,
    	rtrim(BT17.MISE_CD) as MISE_CD,
    	rtrim(BM11.ONER_NAME_KJ) as MISE_NAME_KJ, 
    	rtrim(BT17.ERR_FLG) as ERR_FLG 
    from 
    	BT17PLDT BT17,
    	BM11ONER BM11,
           (select distinct company_cd,oner_cd
              from bm01tenm bm01
             where BM01.CLOSE_DT >= /*nengetuFrom*/'200603' 
               /*IF sibuCd != null && sibuCd != '' */and BM01.SIBU_CD =  /*sibuCd*/'051'/*END*/
               /*IF !closeMiseFlg */ and BM01.CLOSE_DT > /*nengetuTo*/'200603'/*END*/) mise
    where 
        BT17.PL_TYPE = '0' 
    and BT17.MISE_CD = BM11.ONER_CD 
    and BT17.ONER_CD = BM11.ONER_CD 
    and BT17.COMPANY_CD = /*companyCd*/'00' 
    and BT17.COMPANY_CD = mise.COMPANY_CD 
    and BT17.COMPANY_CD = BM11.COMPANY_CD 
    and bt17.oner_cd = mise.oner_cd 
    and BT17.PL_YM between /*nengetuFrom*/'200601' and /*nengetuTo*/'200603' 
    /*IF !closeMiseFlg */ and BM11.KEIYAKU_END > /*nengetuTo*/'200603'/*END*/

) PLDATA

order by 
    PLDATA.ONER_CD,
    PLDATA.PL_TYPE,
    PLDATA.MISE_CD,
    PLDATA.PL_YM