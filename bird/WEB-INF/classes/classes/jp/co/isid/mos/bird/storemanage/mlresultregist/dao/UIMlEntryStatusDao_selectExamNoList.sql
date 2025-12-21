select
    BT23.EXAM_NO
from
    BT23MLEJ as BT23
where
    BT23.ENTRY_CD = /*entryCd*/'10' and
    BT23.ENTRY_YEAR = /*entryYear*/'2006' and
    BT23.ENTRY_KAI = /*entryKai*/'001'
order by
    BT23.EXAM_NO
