SELECT
    count(*)
FROM
    BT49LONG
WHERE
	ENTRY_CD = /*entryCd*/'00' AND
    ENTRY_YEAR = /*entryYear*/'2007' AND
    ENTRY_KAI = /*entryKai*/'001' AND
    COMPANY_CD = /*companyCd*/'00' AND
    ONER_CD = /*onerCd*/'36478'