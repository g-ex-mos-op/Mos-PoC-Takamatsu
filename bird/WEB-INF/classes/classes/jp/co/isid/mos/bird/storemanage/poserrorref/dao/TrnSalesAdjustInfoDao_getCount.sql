select
	count(*)
    
from
    (select distinct COMPANY_CD, MISE_CD, cast(/*to*/'200703' as CHAR(6)) as SEISAN_YM from BT79SKNR
    union
    select distinct COMPANY_CD, MISE_CD, cast(/*from*/'200702' as CHAR(6)) as SEISAN_YM from BT79SKNR) as TARGET
    left outer join BT79SKNR as BT79 on BT79.COMPANY_CD = TARGET.COMPANY_CD and BT79.MISE_CD = TARGET.MISE_CD and BT79.SEISAN_YM = TARGET.SEISAN_YM
    inner join PC01TENM as PC01 on PC01.COMPANY_CD = TARGET.COMPANY_CD and PC01.MISE_CD = TARGET.MISE_CD

where
    TARGET.COMPANY_CD = /*companyCd*/'00'
    /*IF userTypeCd == '02' */
    and PC01.ONER_CD = /*onerCd*/'37004'
    /*END*/
    /*IF userTypeCd == '03' */
    and TARGET.MISE_CD = /*miseCd*/'04602'
    /*END*/
    and (PC01.MISE_KBN like '_1_' or PC01.MISE_KBN like '_2_')
    and  PC01.P4_OPEN_DT  <=  concat(/*to*/'200703', '31')
    and  PC01.P4_OPEN_DT  <> ''
    and  PC01.CLOSE_DT    >= /*from*/'200702' 
    and  PC01.P4_SHUHAI_FLG = '1'