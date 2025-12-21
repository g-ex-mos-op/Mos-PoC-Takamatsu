select
    BR18.ENTRY_CD,
    BR18.ENTRY_YEAR,
    BR18.ENTRY_KAI,
    BR18.USERTYPE_CD,
    BR18.DAY_KBN,
    rtrim(BR18.FROM_DT) as FROM_DT,
    rtrim(BR18.TO_DT) as TO_DT,
    BR18.FIRST_USER,
    BR18.FIRST_PGM,
    BR18.FIRST_TMSP,
    BR18.LAST_USER,
    BR18.LAST_PGM,
    BR18.LAST_TMSP
from
    BR18ENTD as BR18
where
    BR18.ENTRY_CD = /*entryCd*/'10' and
    BR18.ENTRY_YEAR = /*entryYear*/'2006' and
    BR18.ENTRY_KAI = /*entryKai*/'002' and
    BR18.USERTYPE_CD = /*usertypeCd*/'99' and
    BR18.DAY_KBN = /*dayKbn*/'01'
