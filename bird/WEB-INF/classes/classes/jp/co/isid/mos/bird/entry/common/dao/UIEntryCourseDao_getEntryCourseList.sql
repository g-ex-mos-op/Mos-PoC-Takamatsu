select
    BR19.ENTRY_CD,
    BR19.ENTRY_YEAR,
    BR19.ENTRY_KAI,
    BR19.COURSE_CD,
    rtrim(BR19.COURSE_NAME) as COURSE_NAME,
    BR19.FIRST_USER,
    BR19.FIRST_PGM,
    BR19.FIRST_TMSP,
    BR19.LAST_USER,
    BR19.LAST_PGM,
    BR19.LAST_TMSP
from
    BR19MLCR as BR19
where
    BR19.ENTRY_CD = /*entryCd*/'10' and
    BR19.ENTRY_YEAR = /*entryYear*/'2006' and
    BR19.ENTRY_KAI = /*entryKai*/'004'
order by
    BR19.COURSE_CD
