select 
    VBT32.REENTRY_FLG,
    coalesce(BT28.ENTRY_YEAR, VBT32.TOTAL_LAST_YEAR) as ENTRY_YEAR,
    coalesce(BT28.ENTRY_KAI, VBT32.TOTAL_LAST_KAI) as ENTRY_KAI,
    coalesce(BT28.STAFF_ID, VBT32.STAFF_ID) as STAFF_ID,
    rtrim(BT28.ASSESSER) as ASSESSER,
    coalesce(BT28.SUB1_CHK1_BYEAR, VBT32.SUB1_BASE_YEAR) as SUB1_CHK1_BYEAR,
    coalesce(BT28.SUB1_CHK1_LYEAR, VBT32.SUB1_LAST_YEAR) as SUB1_CHK1_LYEAR,
    coalesce(BT28.SUB1_CHK1_LKAI, VBT32.SUB1_LAST_KAI) as SUB1_CHK1_LKAI,
    coalesce(BT28.SUB1_CHK1_LAST_RSLT, '') as SUB1_CHK1_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT28MLNC
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB1_CHK1_RESULT = '1'
        )
    then '2'
    else coalesce(BT28.SUB1_CHK1_RESULT, '')
    end as SUB1_CHK1_RESULT,
    coalesce(BT28.SUB1_CHK2_BYEAR, VBT32.SUB1_BASE_YEAR) as SUB1_CHK2_BYEAR,
    coalesce(BT28.SUB1_CHK2_LYEAR, VBT32.SUB1_LAST_YEAR) as SUB1_CHK2_LYEAR,
    coalesce(BT28.SUB1_CHK2_LKAI, VBT32.SUB1_LAST_KAI) as SUB1_CHK2_LKAI,
    coalesce(BT28.SUB1_CHK2_LAST_RSLT, '') as SUB1_CHK2_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT28MLNC
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB1_CHK2_RESULT = '1'
        )
    then '2'
    else coalesce(BT28.SUB1_CHK2_RESULT, '')
    end as SUB1_CHK2_RESULT,
    coalesce(BT28.SUB1_CHK3_BYEAR, VBT32.SUB1_BASE_YEAR) as SUB1_CHK3_BYEAR,
    coalesce(BT28.SUB1_CHK3_LYEAR, VBT32.SUB1_LAST_YEAR) as SUB1_CHK3_LYEAR,
    coalesce(BT28.SUB1_CHK3_LKAI, VBT32.SUB1_LAST_KAI) as SUB1_CHK3_LKAI,
    coalesce(BT28.SUB1_CHK3_LAST_RSLT, '') as SUB1_CHK3_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT28MLNC
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB1_CHK3_RESULT = '1'
        )
    then '2'
    else coalesce(BT28.SUB1_CHK3_RESULT, '')
    end as SUB1_CHK3_RESULT,
    coalesce(BT28.SUB1_CHK4_BYEAR, VBT32.SUB1_BASE_YEAR) as SUB1_CHK4_BYEAR,
    coalesce(BT28.SUB1_CHK4_LYEAR, VBT32.SUB1_LAST_YEAR) as SUB1_CHK4_LYEAR,
    coalesce(BT28.SUB1_CHK4_LKAI, VBT32.SUB1_LAST_KAI) as SUB1_CHK4_LKAI,
    coalesce(BT28.SUB1_CHK4_LAST_RSLT, '') as SUB1_CHK4_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT28MLNC
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB1_CHK4_RESULT = '1'
        )
    then '2'
    else coalesce(BT28.SUB1_CHK4_RESULT, '')
    end as SUB1_CHK4_RESULT,
    coalesce(BT28.SUB1_CHK5_BYEAR, VBT32.SUB1_BASE_YEAR) as SUB1_CHK5_BYEAR,
    coalesce(BT28.SUB1_CHK5_LYEAR, VBT32.SUB1_LAST_YEAR) as SUB1_CHK5_LYEAR,
    coalesce(BT28.SUB1_CHK5_LKAI, VBT32.SUB1_LAST_KAI) as SUB1_CHK5_LKAI,
    coalesce(BT28.SUB1_CHK5_LAST_RSLT, '') as SUB1_CHK5_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT28MLNC
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB1_CHK5_RESULT = '1'
        )
    then '2'
    else coalesce(BT28.SUB1_CHK5_RESULT, '')
    end as SUB1_CHK5_RESULT,
    coalesce(BT28.SUB1_CHK6_BYEAR, VBT32.SUB1_BASE_YEAR) as SUB1_CHK6_BYEAR,
    coalesce(BT28.SUB1_CHK6_LYEAR, VBT32.SUB1_LAST_YEAR) as SUB1_CHK6_LYEAR,
    coalesce(BT28.SUB1_CHK6_LKAI, VBT32.SUB1_LAST_KAI) as SUB1_CHK6_LKAI,
    coalesce(BT28.SUB1_CHK6_LAST_RSLT, '') as SUB1_CHK6_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT28MLNC
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB1_CHK6_RESULT = '1'
        )
    then '2'
    else coalesce(BT28.SUB1_CHK6_RESULT, '')
    end as SUB1_CHK6_RESULT,
    coalesce(BT28.SUB1_CHK7_BYEAR, VBT32.SUB1_BASE_YEAR) as SUB1_CHK7_BYEAR,
    coalesce(BT28.SUB1_CHK7_LYEAR, VBT32.SUB1_LAST_YEAR) as SUB1_CHK7_LYEAR,
    coalesce(BT28.SUB1_CHK7_LKAI, VBT32.SUB1_LAST_KAI) as SUB1_CHK7_LKAI,
    coalesce(BT28.SUB1_CHK7_LAST_RSLT, '') as SUB1_CHK7_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT28MLNC
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB1_CHK7_RESULT = '1'
        )
    then '2'
    else coalesce(BT28.SUB1_CHK7_RESULT, '')
    end as SUB1_CHK7_RESULT,
    coalesce(BT28.SUB1_CHK8_BYEAR, VBT32.SUB1_BASE_YEAR) as SUB1_CHK8_BYEAR,
    coalesce(BT28.SUB1_CHK8_LYEAR, VBT32.SUB1_LAST_YEAR) as SUB1_CHK8_LYEAR,
    coalesce(BT28.SUB1_CHK8_LKAI, VBT32.SUB1_LAST_KAI) as SUB1_CHK8_LKAI,
    coalesce(BT28.SUB1_CHK8_LAST_RSLT, '') as SUB1_CHK8_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT28MLNC
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB1_CHK8_RESULT = '1'
        )
    then '2'
    else coalesce(BT28.SUB1_CHK8_RESULT, '')
    end as SUB1_CHK8_RESULT,
    coalesce(BT28.SUB1_CHK9_BYEAR, VBT32.SUB1_BASE_YEAR) as SUB1_CHK9_BYEAR,
    coalesce(BT28.SUB1_CHK9_LYEAR, VBT32.SUB1_LAST_YEAR) as SUB1_CHK9_LYEAR,
    coalesce(BT28.SUB1_CHK9_LKAI, VBT32.SUB1_LAST_KAI) as SUB1_CHK9_LKAI,
    coalesce(BT28.SUB1_CHK9_LAST_RSLT, '') as SUB1_CHK9_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT28MLNC
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB1_CHK9_RESULT = '1'
        )
    then '2'
    else coalesce(BT28.SUB1_CHK9_RESULT, '')
    end as SUB1_CHK9_RESULT,
    coalesce(BT28.SUB1_CHK10_BYEAR, VBT32.SUB1_BASE_YEAR) as SUB1_CHK10_BYEAR,
    coalesce(BT28.SUB1_CHK10_LYEAR, VBT32.SUB1_LAST_YEAR) as SUB1_CHK10_LYEAR,
    coalesce(BT28.SUB1_CHK10_LKAI, VBT32.SUB1_LAST_KAI) as SUB1_CHK10_LKAI,
    coalesce(BT28.SUB1_CHK10_LAST_RSLT, '') as SUB1_CHK10_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT28MLNC
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB1_CHK10_RESULT = '1'
        )
    then '2'
    else coalesce(BT28.SUB1_CHK10_RESULT, '')
    end as SUB1_CHK10_RESULT,
    BT28.FIRST_USER,
    BT28.FIRST_PGM,
    BT28.FIRST_TMSP,
    BT28.LAST_USER,
    BT28.LAST_PGM,
    BT28.LAST_TMSP,
    case when BT28.STAFF_ID is null then '1' else '0' end as INSERT_FLG
from
    VBT32MLKR as VBT32
    left outer join BT28MLNC as BT28 on BT28.ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and BT28.ENTRY_KAI = VBT32.TOTAL_LAST_KAI and BT28.STAFF_ID = VBT32.STAFF_ID 
where
    VBT32.TOTAL_LAST_YEAR = /*entryYear*/'2006' and
    VBT32.TOTAL_LAST_KAI = /*entryKai*/'001' and
    VBT32.STAFF_ID = /*staffId*/'98002183'
