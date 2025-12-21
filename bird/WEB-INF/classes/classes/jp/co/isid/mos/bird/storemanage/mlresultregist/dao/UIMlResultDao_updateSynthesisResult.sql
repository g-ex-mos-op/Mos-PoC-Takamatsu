update
    BT32MLKR as BT32
set
    BT32.TOTAL_RESULT = /*entity.totalResult*/'0',
    BT32.LAST_USER = /*entity.lastUser*/'99990003',
    BT32.LAST_PGM = /*entity.lastPgm*/'BSM008',
    BT32.LAST_TMSP = /*entity.lastTmsp*/'2006-07-20 20:25:00.000000'
where
    BT32.TOTAL_LAST_YEAR = /*entity.totalLastYear*/'2006' and
    BT32.TOTAL_LAST_KAI = /*entity.totalLastKai*/'01' and
    BT32.STAFF_ID = /*entity.staffId*/'12345678'
