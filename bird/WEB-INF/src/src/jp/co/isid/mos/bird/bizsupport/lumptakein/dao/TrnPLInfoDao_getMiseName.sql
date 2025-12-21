select
    BM01.MISE_NAME_KJ
from
    BM01TENM as BM01
where
    BM01.COMPANY_CD = '00' and
    BM01.MISE_CD = /*miseCd*/'00385'
