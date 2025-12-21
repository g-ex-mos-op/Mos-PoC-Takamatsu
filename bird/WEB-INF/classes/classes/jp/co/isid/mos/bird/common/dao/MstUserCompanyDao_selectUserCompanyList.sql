select
    BM03.USER_ID,
    BM03.ZOKUSEI_KBN,
    BM03.R_COMPANY_CD as COMPANY_CD,
    rtrim(BC02.COMPANY_NAME) as COMPANY_NAME,
    BC02.SORT_SEQ
from
    BM03USCP as BM03
    inner join BC02COMP as BC02 on BC02.R_COMPANY_CD = BM03.R_COMPANY_CD and BC02.MISE_FLG = '1'
where
    BM03.USER_ID = /*userId*/'99990003'
    /*IF zokuseiKbnList != null*/
    and BM03.ZOKUSEI_KBN in /*zokuseiKbnList*/('1')
    /*END*/
order by
    BC02.SORT_SEQ
