select
    coalesce(BR46.ENTRY_YEAR, /*entryYear*/'2006') as ENTRY_YEAR,
    coalesce(BR46.ENTRY_KAI, /*entryKai*/'001') as ENTRY_KAI,
    coalesce(BR46.STAFF_ID, /*staffId*/'98000420') as STAFF_ID,
    BM29.KOUMOKU_CD,
    BR46.KOUMOKU_RESULT,
    BM28.CATEGORY_CD,
    rtrim(BM29.KOUMOKU_NAME) as KOUMOKU_NAME,
    BM29.KOUMOKU_CONTENTS,
    BR46.FIRST_USER,
    BR46.FIRST_PGM,
    BR46.FIRST_TMSP,
    BR46.LAST_USER,
    BR46.LAST_PGM,
    BR46.LAST_TMSP,
    case when BR46.STAFF_ID is null then '1' else '0' end as INSERT_FLG
from
    BM29ABDK as BM29
    inner join BM28ABCA as BM28 on BM28.CATEGORY_CD = BM29.CATEGORY_CD
    left outer join BR46ABDR as BR46 on BR46.KOUMOKU_CD = BM29.KOUMOKU_CD and BR46.ENTRY_YEAR = /*entryYear*/'2006' and BR46.ENTRY_KAI = /*entryKai*/'001' and BR46.STAFF_ID = /*staffId*/'98000420'
order by
    BM28.SORT_SEQ,
    BM29.SORT_SEQ
