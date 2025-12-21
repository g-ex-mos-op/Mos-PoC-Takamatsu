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
WHERE  ENTRY_CD = /*entryCd*/'25'
AND    ENTRY_YEAR = /*entryYear*/'2006'
AND    ENTRY_KAI = /*entryKai*/'001'
AND    ((USERTYPE_CD ='99' AND DAY_KBN= '01')
            OR (USERTYPE_CD ='01' AND DAY_KBN= '04')
                 OR (USERTYPE_CD ='01' AND DAY_KBN= '03')
                      OR (USERTYPE_CD ='02' AND DAY_KBN= '04')
                           OR (USERTYPE_CD ='02' AND DAY_KBN= '03'))