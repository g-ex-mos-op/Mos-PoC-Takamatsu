update
    BT32MLKR as BT32
set
    BT32.TOTAL_RESULT = /*entity.totalResult*/'3',
    BT32.LAST_USER = /*entity.lastUser*/'99990003',
    BT32.LAST_PGM = /*entity.lastPgm*/'BSM006'
where
    BT32.STAFF_ID = /*entity.staffId*/'98002627' and
    BT32.TOTAL_LAST_YEAR = /*entity.totalLastYear*/'2006' and
    BT32.TOTAL_LAST_KAI = /*entity.totalLastKai*/'001' and
    BT32.LAST_USER = /*entity.lastUser*/'11111111' and
    BT32.LAST_PGM = /*entity.lastPgm*/'SQL_TEST' and
    BT32.LAST_TMSP = /*entity.lastTmsp*/'1111-11-11 11:11:11.111111'
