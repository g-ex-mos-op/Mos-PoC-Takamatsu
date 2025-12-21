select
    BR04.USER_ID,
    BR04.ROLE_CD,
    BR01.USER_NAME_KJ,
    BC04.USERTYPE_CD,
    BC04.USERTYPE_NAME,
    BC02.R_COMPANY_CD,
    BC02.COMPANY_NAME,
    BC08.BUMON_CD,
    BC08.BUMON_NAME,
    BM11.ONER_CD,
    BM11.ONER_NAME_KJ,
    BM01.MISE_CD,
    BM01.MISE_NAME_KJ
from
    BR04USRL as BR04
    inner join BR01USER as BR01 on BR01.STOP_FLG != '1' AND BR01.USER_ID = BR04.USER_ID
    inner join BC04USTP as BC04 on BC04.USERTYPE_CD = BR01.USERTYPE_CD
    inner join BM03USCP as BM03 on BM03.USER_ID = BR04.USER_ID
    inner join BC02COMP as BC02 on BC02.R_COMPANY_CD = BM03.R_COMPANY_CD

    left outer join BC08CBMN as BC08 on BC08.R_COMPANY_CD = BM03.R_COMPANY_CD and BC08.BUMON_CD = BR01.BUMON_CD
    left outer join BM06UONR as BM06 on BM06.USER_ID = BR04.USER_ID 
    left outer join BM11ONER as BM11 on BM11.ONER_CD = BM06.ONER_CD and BM11.COMPANY_CD = BM06.COMPANY_CD
    left outer join BM07UTEN as BM07 on BM07.USER_ID = BR04.USER_ID 
    left outer join BM01TENM as BM01 on BM01.MISE_CD = BM07.MISE_CD and BM01.COMPANY_CD = BM07.COMPANY_CD
where
    BR04.ROLE_CD = /*roleCd*/'101' and
    BM03.R_COMPANY_CD = (
        select
            case
            when BR01.USERTYPE_CD = '01' then
                (select
                    min(R_COMPANY_CD)
                from
                    BM03USCP
                where
                    USER_ID = BM03.USER_ID and
                    ZOKUSEI_KBN = '1'
                group by
                    USER_ID
                )
            else
                min(R_COMPANY_CD)
            end
        from
            BM03USCP
        where
            USER_ID = BM03.USER_ID
        group by
            USER_ID
    )
order by BC04.USERTYPE_CD
,        BR04.USER_ID
