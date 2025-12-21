select
    BT43.KARI_CD as MISE_CD
from
    BT43KTKT as BT43
where
    BT43.NENDO = /*nendo*/'2006' and
    BT43.COMPANY_CD = /*companyCd*/'00'
order by
    BT43.KARI_CD
