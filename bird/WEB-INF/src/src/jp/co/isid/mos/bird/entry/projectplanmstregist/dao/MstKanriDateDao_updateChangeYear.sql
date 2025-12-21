UPDATE BR18ENTD
SET    ENTRY_YEAR = /*entity.entryYear*/'2006'
,      ENTRY_KAI = /*entity.entryKai*/'001'
,      FROM_DT = /*entity.fromDt*/'20061202'
,      TO_DT = /*entity.toDt*/'20061202'
,      LAST_USER = /*entity.lastUser*/'99990003'
,      LAST_PGM = /*entity.lastPgm*/'BEN012L'
,      LAST_TMSP = /*entity.lastTmsp*/
WHERE   ENTRY_CD = /*entity.entryCd*/'25'
AND     ENTRY_YEAR = /*lastYear*/'2006'
AND     ENTRY_KAI = /*lastKai*/'001'
AND     USERTYPE_CD = /*entity.usertypeCd*/'01'
AND     DAY_KBN = /*entity.dayKbn*/'03'
