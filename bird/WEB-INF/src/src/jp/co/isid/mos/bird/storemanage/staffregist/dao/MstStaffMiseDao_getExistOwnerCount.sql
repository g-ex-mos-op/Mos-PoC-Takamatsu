select
    count(*)
from
    BM11ONER as BM11
where
    BM11.COMPANY_CD = /*companyCd*/'00' and
    BM11.ONER_CD = /*onerCd*/'36444'
