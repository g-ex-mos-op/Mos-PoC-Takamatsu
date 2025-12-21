select
	distinct
    BT23.EXAM_NO
from
    BT23MLEJ as BT23
where
    BT23.ENTRY_CD = /*entryCd*/'10' and
    BT23.ENTRY_YEAR in (/*entryYear*/'2006',/*lastYear*/'2005')  and
    BT23.EXAM_NO != ''
order by
    BT23.EXAM_NO
