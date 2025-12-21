select
    BR17.ENTRY_CD,
    BR17.ENTRY_YEAR,
    BR17.ENTRY_KAI,

    rtrim(BC10.ENTRY_NAME) as ENTRY_NAME,
    rtrim(coalesce(OPEN.FROM_DT, '')) as OPEN_FROM_DT,
    rtrim(coalesce(OPEN.TO_DT, '')) as OPEN_TO_DT,
    rtrim(BR17.ENTRY_TITLE) as ENTRY_TITLE,
    ONER_APPLY.FROM_DT as ONER_APPLY_FROM_DT,
    ONER_APPLY.TO_DT as ONER_APPLY_TO_DT,

    APPLY.FROM_DT as APPLY_FROM_DT,
    APPLY.TO_DT as APPLY_TO_DT,
    BC10.ENTRY_TYPE,
    BR52.VIEW_ID
from
    BR17ENTL as BR17
    inner join BC10ENTM as BC10 on BC10.ENTRY_CD = BR17.ENTRY_CD
    inner join BR52ENLK as BR52 on BR52.ENTRY_CD = BR17.ENTRY_CD and  BR52.BUNRUI = /*bunrui*/'02'
    left outer join BR18ENTD as OPEN on
            OPEN.ENTRY_CD = BR17.ENTRY_CD and
            OPEN.ENTRY_YEAR = BR17.ENTRY_YEAR and
            OPEN.ENTRY_KAI = BR17.ENTRY_KAI and
            OPEN.USERTYPE_CD = '99' and
            OPEN.DAY_KBN = '01'
    left outer join BR18ENTD as HEAD_APPLY on
            HEAD_APPLY.ENTRY_CD = BR17.ENTRY_CD and
            HEAD_APPLY.ENTRY_YEAR = BR17.ENTRY_YEAR and
            HEAD_APPLY.ENTRY_KAI = BR17.ENTRY_KAI and
            HEAD_APPLY.USERTYPE_CD = '01' and
            HEAD_APPLY.DAY_KBN = '04'
    left outer join BR18ENTD as ONER_APPLY on
            ONER_APPLY.ENTRY_CD = BR17.ENTRY_CD and
            ONER_APPLY.ENTRY_YEAR = BR17.ENTRY_YEAR and
            ONER_APPLY.ENTRY_KAI = BR17.ENTRY_KAI and
            ONER_APPLY.USERTYPE_CD = '02' and
            ONER_APPLY.DAY_KBN = '04'
    left outer join BR18ENTD as APPLY on
            APPLY.ENTRY_CD = BR17.ENTRY_CD and
            APPLY.ENTRY_YEAR = BR17.ENTRY_YEAR and
            APPLY.ENTRY_KAI = BR17.ENTRY_KAI and
            APPLY.USERTYPE_CD = /*usertypeCd*/'01' and
            APPLY.DAY_KBN = '04'
    left outer join BR18ENTD as DISP on
            DISP.ENTRY_CD = BR17.ENTRY_CD and
            DISP.ENTRY_YEAR = BR17.ENTRY_YEAR and
            DISP.ENTRY_KAI = BR17.ENTRY_KAI and
            DISP.USERTYPE_CD = /*usertypeCd*/'01' and
            DISP.DAY_KBN = '03'
where
    BR17.ENTRY_CD in /*entryCdList*/('15', '20', '25', '35', '50') and
    BR17.SAKUJO_FLG <> '1' and
    /*sysDate*/'20061124' between DISP.FROM_DT and DISP.TO_DT
order by
    case when OPEN.FROM_DT is not null then OPEN.FROM_DT else HEAD_APPLY.FROM_DT end desc,
    BR17.ENTRY_CD,
    BR17.ENTRY_YEAR desc,
    BR17.ENTRY_KAI desc
