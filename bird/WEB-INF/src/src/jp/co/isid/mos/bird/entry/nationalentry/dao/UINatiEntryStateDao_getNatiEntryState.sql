select
    BT20.ENTRY_FLG,
    BT20.LAST_TMSP
from
    BT20ENON as BT20
where
    BT20.ENTRY_CD = /*entryCd*/'20' and
    BT20.ENTRY_YEAR = /*entryYear*/'2006' and
    BT20.ENTRY_KAI = /*entryKai*/'20'  and
    BT20.COMPANY_CD = /*companyCd*/'00' and
	BT20.ONER_CD = /*onerCd*/'36478'