update
    BT32MLKR as BT32
set
    BT32.SUB1_RESULT = /*entity.sub1Result*/'1',
    BT32.LAST_USER = /*entity.lastUser*/'99990003',
    BT32.LAST_PGM = /*entity.lastPgm*/'BSM008',
    BT32.LAST_TMSP = /*entity.lastTmsp*/'2006-08-10 13:52:00.000000'
where
    BT32.STAFF_ID = /*entity.staffId*/'98002183' and
    BT32.TOTAL_LAST_YEAR = /*entry.totalLastYear*/'2006' and
    BT32.TOTAL_LAST_KAI = /*entry.totalLastKai*/'001'
