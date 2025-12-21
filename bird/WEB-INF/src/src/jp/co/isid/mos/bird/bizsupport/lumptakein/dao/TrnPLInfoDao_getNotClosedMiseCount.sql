select
    count(*)
from
    BM01TENM as BM01
where
    BM01.COMPANY_CD = '00' and
    BM01.MISE_CD = /*miseCd*/'01671' and
    BM01.CLOSE_DT >= /*plYm*/'200103'
