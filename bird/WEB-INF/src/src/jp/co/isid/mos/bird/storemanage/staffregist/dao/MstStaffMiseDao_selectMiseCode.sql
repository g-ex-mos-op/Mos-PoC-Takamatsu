select
    BM01.MISE_CD,
    rtrim(BM01.MISE_NAME_KJ) as MISE_NAME_KJ,
    case when BM01.CLOSE_DT <= /*sysDate*/'20060525' then
        'true'
    else 
        'false'
    end as CLOSE_FLAG
from
    BM01TENM as BM01
where
    BM01.COMPANY_CD = /*companyCd*/'00' and
    BM01.ONER_CD = /*onerCd*/'36444'
order by
    BM01.MISE_CD
