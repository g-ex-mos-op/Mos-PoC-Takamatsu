select
    count(*)
from
    BR17ENTL as BR17
    left outer join BR18ENTD as OPEN on BR17.ENTRY_CD = OPEN.ENTRY_CD and BR17.ENTRY_YEAR = OPEN.ENTRY_YEAR and BR17.ENTRY_KAI = OPEN.ENTRY_KAI and OPEN.USERTYPE_CD = '99' and OPEN.DAY_KBN = '01'
where
    BR17.ENTRY_CD = /*entryCd*/'10' and
    BR17.SAKUJO_FLG <> '1' and
    substr(OPEN.FROM_DT, 1, 4) >= /*fromYear*/'2005' and
    substr(OPEN.FROM_DT, 1, 4) <= /*toYear*/'2006'
