select
    BM06.ONER_CD
from
    BM06UONR as BM06
where
    BM06.COMPANY_CD = '00' and
    BM06.USER_ID = /*userId*/'99990003'
order by
    BM06.ONER_CD
