update
    BR19MLCR as BR19
set
    BR19.COURSE_CD = /*uiEntryCourse.courseCd*/'99',
    BR19.COURSE_NAME = /*uiEntryCourse.courseName*/'テストコース',
    BR19.LAST_USER = /*uiEntryCourse.lastUser*/'400',
    BR19.LAST_PGM = /*uiEntryCourse.lastPgm*/'SQL',
    BR19.LAST_TMSP = /*uiEntryCourse.lastTmsp*/'2006-06-30 19:17:00.000000'
where
    BR19.ENTRY_CD = /*uiEntryCourse.entryCd*/'01' and
    BR19.ENTRY_YEAR = /*uiEntryCourse.entryYear*/'2006' and
    BR19.ENTRY_KAI = /*uiEntryCourse.entryKai*/'030'
