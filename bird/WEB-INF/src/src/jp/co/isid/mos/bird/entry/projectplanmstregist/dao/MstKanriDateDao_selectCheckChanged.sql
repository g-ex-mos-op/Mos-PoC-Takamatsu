SELECT ENTRY_CD
,      ENTRY_YEAR
,      ENTRY_KAI
,      USERTYPE_CD
,      DAY_KBN
,      FROM_DT
,      TO_DT
,      FIRST_USER
,      FIRST_PGM
,      FIRST_TMSP
,      LAST_USER
,      LAST_PGM
,      LAST_TMSP
FROM   BR18ENTD
WHERE  ENTRY_CD = /*entity.entryCd*/'25'
AND    ENTRY_YEAR = /*entity.entryYear*/'2006'
AND    ENTRY_KAI = /*entity.entryKai*/'001'
AND    USERTYPE_CD = /*entity.usertypeCd*/'01'
AND    DAY_KBN = /*entity.dayKbn*/'03'
AND    LAST_TMSP = /*entity.lastTmsp*/