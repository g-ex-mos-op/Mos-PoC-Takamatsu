select
    BM01.MISE_CD,
    rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ
from
    BM01TENM as BM01
where
    BM01.COMPANY_CD = '00' and
    BM01.ONER_CD = /*onerCd*/'36005' and
    substr(BM01.CLOSE_DT, 1, 6) >= /*plYm*/'200601'
order by
    BM01.MISE_CD
