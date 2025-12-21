select
    BR53.SELECTION_KBN, 
    BR53.SELECTION_INDEX,
    RTRIM(BR53.SELECTION_NAME) AS SELECTION_NAME
from
    BR53ENTS as BR53
where
    BR53.ENTRY_CD = /*entryCd*/'15' and
    BR53.ENTRY_YEAR = /*entryYear*/'2006' and
    BR53.ENTRY_KAI = /*entryKai*/'004' and
    BR53.SELECTION_KBN = /*selectionKbn*/'1'
order by     
    BR53.SELECTION_KBN, BR53.SELECTION_INDEX