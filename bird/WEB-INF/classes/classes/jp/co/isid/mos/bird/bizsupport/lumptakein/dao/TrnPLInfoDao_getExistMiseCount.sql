select
    count(*)
from
    BM01TENM as BM01
where
    BM01.COMPANY_CD = '00' and
    BM01.ONER_CD = /*onerCd*/'36005' and
    BM01.MISE_CD = /*miseCd*/'00018'
