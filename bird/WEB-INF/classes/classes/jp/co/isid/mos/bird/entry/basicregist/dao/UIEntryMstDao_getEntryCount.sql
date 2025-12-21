select
    count(*)
from
    BR17ENTL as BR17
    left outer join BR18ENTD as BASIC on BR17.ENTRY_CD = BASIC.ENTRY_CD and BR17.ENTRY_YEAR = BASIC.ENTRY_YEAR and BR17.ENTRY_KAI = BASIC.ENTRY_KAI and BASIC.USERTYPE_CD = '99' and BASIC.DAY_KBN = '01'
where
    BR17.ENTRY_CD = /*entryCd*/'01' and
    BR17.SAKUJO_FLG <> '1' and
    substr(BASIC.FROM_DT, 1, 4) >= /*fromYear*/'2005' 
