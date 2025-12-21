SELECT ENTRY_CD
,           ENTRY_YEAR
,           ENTRY_KAI
,           RTRIM(ENTRY_TITLE) AS ENTRY_TITLE
,           RTRIM(ENTRY_PLACE) AS ENTRY_PLACE
,           NUMBER_LIMIT
,           PLACE_LIMIT
,           RTRIM(NOTE) AS NOTE
,           SPARE_FLG1
,           SPARE_FLG2
,           SAKUJO_FLG
,           FIRST_USER
,           FIRST_PGM
,           FIRST_TMSP
,           LAST_USER
,           LAST_PGM
,           LAST_TMSP
FROM    BR17ENTL
WHERE   ENTRY_CD = /*entryCd*/'25'
AND      ENTRY_YEAR = /*entryYear*/'2006'
AND      ENTRY_KAI = /*entryKai*/'001'