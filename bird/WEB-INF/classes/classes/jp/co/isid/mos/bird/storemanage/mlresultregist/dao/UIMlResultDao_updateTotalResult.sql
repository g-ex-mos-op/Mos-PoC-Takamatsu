update
    BT32MLKR as BT32
set
    BT32.TOTAL_RESULT = /*entity.totalResult*/'0',
    BT32.SUB2_RESULT = /*entity.sub2Result*/'1',
    BT32.LAST_USER = /*entity.lastUser*/'99990003',
    BT32.LAST_PGM = /*entity.lastPgm*/'BSM008',
    BT32.LAST_TMSP = /*entity.lastTmsp*/'2006-07-20 20:25:00.000000'
where
    BT32.TOTAL_LAST_YEAR = /*entry.totalLastYear*/'2006' and
    BT32.TOTAL_LAST_KAI = /*entry.totalLastKai*/'001' and
    BT32.EXAM_NO = /*entity.examNo*/'02466'
