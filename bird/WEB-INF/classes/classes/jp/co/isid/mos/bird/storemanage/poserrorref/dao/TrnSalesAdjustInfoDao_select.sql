select
    TARGET.COMPANY_CD,
    TARGET.MISE_CD,
    TARGET.SEISAN_YM,
    PC01.ONER_CD,
    PC01.MISE_NAME_KJ,
    PC01.OPEN_DT,    
    PC01.CLOSE_DT,
    PC01.P4_OPEN_DT,
    coalesce(BT79.SEISAN_KBN1, '') as SEISAN_KBN1,
    coalesce(BT79.SEISAN_KBN2, '') as SEISAN_KBN2,
    coalesce(BT79.SEISAN_KBN3, '') as SEISAN_KBN3,
    coalesce(BT79.SEISAN_KBN4, '') as SEISAN_KBN4,
    coalesce(BT79.SEISAN_KBN5, '') as SEISAN_KBN5,
    coalesce(BT79.SEISAN_KBN6, '') as SEISAN_KBN6,
    coalesce(BT79.SEISAN_KBN7, '') as SEISAN_KBN7,
    coalesce(BT79.SEISAN_KBN8, '') as SEISAN_KBN8,
    coalesce(BT79.SEISAN_KBN9, '') as SEISAN_KBN9,
    coalesce(BT79.SEISAN_KBN10, '') as SEISAN_KBN10,
    coalesce(BT79.SEISAN_KBN11, '') as SEISAN_KBN11,
    coalesce(BT79.SEISAN_KBN12, '') as SEISAN_KBN12,
    coalesce(BT79.SEISAN_KBN13, '') as SEISAN_KBN13,
    coalesce(BT79.SEISAN_KBN14, '') as SEISAN_KBN14,
    coalesce(BT79.SEISAN_KBN15, '') as SEISAN_KBN15,
    coalesce(BT79.SEISAN_KBN16, '') as SEISAN_KBN16,
    coalesce(BT79.SEISAN_KBN17, '') as SEISAN_KBN17,
    coalesce(BT79.SEISAN_KBN18, '') as SEISAN_KBN18,
    coalesce(BT79.SEISAN_KBN19, '') as SEISAN_KBN19,
    coalesce(BT79.SEISAN_KBN20, '') as SEISAN_KBN20,
    coalesce(BT79.SEISAN_KBN21, '') as SEISAN_KBN21,
    coalesce(BT79.SEISAN_KBN22, '') as SEISAN_KBN22,
    coalesce(BT79.SEISAN_KBN23, '') as SEISAN_KBN23,
    coalesce(BT79.SEISAN_KBN24, '') as SEISAN_KBN24,
    coalesce(BT79.SEISAN_KBN25, '') as SEISAN_KBN25,
    coalesce(BT79.SEISAN_KBN26, '') as SEISAN_KBN26,
    coalesce(BT79.SEISAN_KBN27, '') as SEISAN_KBN27,
    coalesce(BT79.SEISAN_KBN28, '') as SEISAN_KBN28,
    coalesce(BT79.SEISAN_KBN29, '') as SEISAN_KBN29,
    coalesce(BT79.SEISAN_KBN30, '') as SEISAN_KBN30,
    coalesce(BT79.SEISAN_KBN31, '') as SEISAN_KBN31
    
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

order by
    TARGET.MISE_CD,
    TARGET.SEISAN_YM