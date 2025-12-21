SELECT ENTRY_CD
,      ENTRY_YEAR
,      ENTRY_KAI
,      RTRIM(NOTICE1) AS TOTICE1
,      RTRIM(NOTICE2) AS TOTICE2
,      RTRIM(NOTICE3) AS TOTICE3
,      FIRST_USER
,      FIRST_PGM
,      FIRST_TMSP
,      LAST_USER
,      LAST_PGM
,      LAST_TMSP
FROM   BR51ENCI
WHERE  ENTRY_CD = /*entity.entryCd*/'25'
AND    ENTRY_YEAR = /*entity.entryYear*/'2006'
AND    ENTRY_KAI = /*entity.entryKai*/'001'
AND    LAST_TMSP = /*entity.lastTmsp*/