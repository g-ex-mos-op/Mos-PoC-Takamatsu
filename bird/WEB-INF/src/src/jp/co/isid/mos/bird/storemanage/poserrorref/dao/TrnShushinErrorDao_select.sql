select
    BT75.SHU_DT,
    BT75.SHU_KBN,
    BT75.COMPANY_CD,
    BC05.COMPANY_NAME,
    BM01.ONER_CD,
    BT75.MISE_CD,
    BM01.MISE_NAME_KJ,
    BM01.CLOSE_DT,    
    BT75.TEKIYOU,
    BT75.HOL_KBN
from
    BT75PERT as BT75
    inner join BM01TENM as BM01 on BT75.COMPANY_CD = BM01.COMPANY_CD and
                                   BT75.MISE_CD = BM01.MISE_CD
    inner join BC05KCOM as BC05 on BT75.COMPANY_CD = BC05.COMPANY_CD
where
    BT75.COMPANY_CD = /*companyCd*/'00' and
    BT75.SHU_DT = /*shuDt*/'20070118'
    /*IF userTypeCd == '02' */
    and BM01.ONER_CD = /*onerCd*/'37004' 
    /*END*/
    /*IF userTypeCd == '03' */
    and BT75.MISE_CD = /*miseCd*/'04602' 
    /*END*/
order by
	BT75.SHU_KBN,
    BT75.MISE_CD