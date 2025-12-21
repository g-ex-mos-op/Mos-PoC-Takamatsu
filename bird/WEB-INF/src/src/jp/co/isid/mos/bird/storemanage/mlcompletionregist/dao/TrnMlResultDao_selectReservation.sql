select
    BT32.STAFF_ID,
    BT32.TOTAL_LAST_YEAR,
    BT32.TOTAL_LAST_KAI,
    BT32.TOTAL_RESULT,
    BT32.LAST_USER,
    BT32.LAST_PGM,
    BT32.LAST_TMSP
from
    BT32MLKR as BT32
where
    BT32.STAFF_ID = /*staffId*/'98002627' and
    concat(BT32.TOTAL_LAST_YEAR, BT32.TOTAL_LAST_KAI) = (
        select
            max(concat(TOTAL_LAST_YEAR, TOTAL_LAST_KAI))
        from
            BT32MLKR
        where
            STAFF_ID = BT32.STAFF_ID and
            BT32.TOTAL_LAST_YEAR >= /*entryYearBefore*/'2005' and
            not (BT32.TOTAL_LAST_YEAR = /*entryYearBefore*/'2005' and BT32.TOTAL_LAST_KAI = '001') and
            BT32.TOTAL_RESULT = '1' and
            BT32.SUB1_RESULT in ('1', '2') and
            BT32.SUB2_RESULT in ('1', '2') and
            BT32.SUB3_RESULT in ('1', '2')
    )
