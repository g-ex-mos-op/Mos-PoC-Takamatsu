select
    COMPANY_CD,
    MISE_CD,
    MISE_HOL_DT,
    HOL_KBN
from
    TN01MHOL
where
    COMPANY_CD = /*companyCd*/'00' and
    MISE_HOL_DT between concat(/*from*/'200702', '01') and concat(/*to*/'200703', '31')