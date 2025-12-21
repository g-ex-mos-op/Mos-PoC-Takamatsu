select
    BR17.ENTRY_CD,
    BR17.ENTRY_YEAR,
    BR17.ENTRY_KAI
from
    BR17ENTL as BR17
    left outer join BR18ENTD as APPLY on
            APPLY.ENTRY_CD = BR17.ENTRY_CD and
            APPLY.ENTRY_YEAR = BR17.ENTRY_YEAR and
            APPLY.ENTRY_KAI = BR17.ENTRY_KAI and
            APPLY.USERTYPE_CD = /*userTypeCd*/'02' and
            APPLY.DAY_KBN = '04'
where
    BR17.ENTRY_CD ='20' and
    BR17.SAKUJO_FLG <> '1' and
    /*sysDate*/'20080106' between APPLY.FROM_DT and APPLY.TO_DT
order by  BR17.ENTRY_YEAR desc, BR17.ENTRY_KAI desc
FETCH FIRST 1 ROWS ONLY;