select
    BT23.STAFF_ID,
    BT23.EXAM_NO
from
    BT23MLEJ as BT23
where
    BT23.ENTRY_CD = /*entryCd*/'10' and
    BT23.ENTRY_YEAR = /*entryYear*/'2006' and
    BT23.ENTRY_KAI = /*entryKai*/'001' and
    exists (
        select
            *
        from
            BT26UPJK
        where
            STAFF_ID = BT23.STAFF_ID and
            LICENSE_VALID_DT >= substr(/*sysDate*/'20060825', 1, 6) and
            EXPIRE_FLG <> '1'
    )
