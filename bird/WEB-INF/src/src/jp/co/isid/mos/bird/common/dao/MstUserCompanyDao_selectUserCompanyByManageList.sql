select distinct
    BM03.USER_ID,
    BM03.ZOKUSEI_KBN,
    BC05.COMPANY_CD,
    rtrim(BC05.COMPANY_NAME) as COMPANY_NAME,
    BC05.SORT_SEQ
from
    BM03USCP as BM03
    inner join BC06COMM as BC06 on BC06.R_COMPANY_CD = BM03.R_COMPANY_CD
    inner join BC05KCOM as BC05 on BC05.COMPANY_CD = BC06.COMPANY_CD
where
    BM03.USER_ID = /*userId*/'99990003'
    /*IF zokuseiKbnList != null*/
    and BM03.ZOKUSEI_KBN in /*zokuseiKbnList*/('1')
    /*END*/
order by
    BC05.SORT_SEQ
