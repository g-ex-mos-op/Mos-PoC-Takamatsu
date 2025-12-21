select
    coalesce(BT29.ENTRY_YEAR, VBT32.TOTAL_LAST_YEAR) as ENTRY_YEAR,
    coalesce(BT29.ENTRY_KAI, VBT32.TOTAL_LAST_KAI) as ENTRY_KAI,
    coalesce(BT29.STAFF_ID, VBT32.STAFF_ID) as STAFF_ID,
    rtrim(BM12.STAFF_L_NAME_KJ) as STAFF_L_NAME_KJ,
    rtrim(BM12.STAFF_F_NAME_KJ) as STAFF_F_NAME_KJ,
    MISE.ONER_CD,
    MISE.MISE_CD,
    rtrim(MISE.ONER_NAME_KJ) as ONER_NAME_KJ,
    rtrim(MISE.MISE_NAME_KJ) as MISE_NAME_KJ,
    MISE.AREA_DAI,
    rtrim(MISE.SIBU_NAME) as SIBU_NAME, 
    rtrim(BT29.ASSESSER) as ASSESSER,
    coalesce(BT29.SUB2_CHK1_BYEAR, VBT32.SUB2_BASE_YEAR) as SUB2_CHK1_BYEAR,
    coalesce(BT29.SUB2_CHK1_LYEAR, VBT32.SUB2_LAST_YEAR) as SUB2_CHK1_LYEAR,
    coalesce(BT29.SUB2_CHK1_LKAI, VBT32.SUB2_LAST_KAI) as SUB2_CHK1_LKAI,
    coalesce(BT29.SUB2_CHK1_LAST_RSLT, '') as SUB2_CHK1_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT29MLMN
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB2_CHK1_RESULT = '1'
        )
    then '2'
    else coalesce(BT29.SUB2_CHK1_RESULT, '')
    end as SUB2_CHK1_RESULT,
    coalesce(BT29.SUB2_CHK2_BYEAR, VBT32.SUB2_BASE_YEAR) as SUB2_CHK2_BYEAR,
    coalesce(BT29.SUB2_CHK2_LYEAR, VBT32.SUB2_LAST_YEAR) as SUB2_CHK2_LYEAR,
    coalesce(BT29.SUB2_CHK2_LKAI, VBT32.SUB2_LAST_KAI) as SUB2_CHK2_LKAI,
    coalesce(BT29.SUB2_CHK2_LAST_RSLT, '') as SUB2_CHK2_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT29MLMN
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB2_CHK2_RESULT = '1'
        )
    then '2'
    else coalesce(BT29.SUB2_CHK2_RESULT, '')
    end as SUB2_CHK2_RESULT,
    coalesce(BT29.SUB2_CHK3_BYEAR, VBT32.SUB2_BASE_YEAR) as SUB2_CHK3_BYEAR,
    coalesce(BT29.SUB2_CHK3_LYEAR, VBT32.SUB2_LAST_YEAR) as SUB2_CHK3_LYEAR,
    coalesce(BT29.SUB2_CHK3_LKAI, VBT32.SUB2_LAST_KAI) as SUB2_CHK3_LKAI,
    coalesce(BT29.SUB2_CHK3_LAST_RSLT, '') as SUB2_CHK3_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT29MLMN
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB2_CHK3_RESULT = '1'
        )
    then '2'
    else coalesce(BT29.SUB2_CHK3_RESULT, '')
    end as SUB2_CHK3_RESULT,
    coalesce(BT29.SUB2_CHK4_BYEAR, VBT32.SUB2_BASE_YEAR) as SUB2_CHK4_BYEAR,
    coalesce(BT29.SUB2_CHK4_LYEAR, VBT32.SUB2_LAST_YEAR) as SUB2_CHK4_LYEAR,
    coalesce(BT29.SUB2_CHK4_LKAI, VBT32.SUB2_LAST_KAI) as SUB2_CHK4_LKAI,
    coalesce(BT29.SUB2_CHK4_LAST_RSLT, '') as SUB2_CHK4_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT29MLMN
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB2_CHK4_RESULT = '1'
        )
    then '2'
    else coalesce(BT29.SUB2_CHK4_RESULT, '')
    end as SUB2_CHK4_RESULT,
    coalesce(BT29.SUB2_CHK5_BYEAR, VBT32.SUB2_BASE_YEAR) as SUB2_CHK5_BYEAR,
    coalesce(BT29.SUB2_CHK5_LYEAR, VBT32.SUB2_LAST_YEAR) as SUB2_CHK5_LYEAR,
    coalesce(BT29.SUB2_CHK5_LKAI, VBT32.SUB2_LAST_KAI) as SUB2_CHK5_LKAI,
    coalesce(BT29.SUB2_CHK5_LAST_RSLT, '') as SUB2_CHK5_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT29MLMN
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB2_CHK5_RESULT = '1'
        )
    then '2'
    else coalesce(BT29.SUB2_CHK5_RESULT, '')
    end as SUB2_CHK5_RESULT,
    coalesce(BT29.SUB2_CHK6_BYEAR, VBT32.SUB2_BASE_YEAR) as SUB2_CHK6_BYEAR,
    coalesce(BT29.SUB2_CHK6_LYEAR, VBT32.SUB2_LAST_YEAR) as SUB2_CHK6_LYEAR,
    coalesce(BT29.SUB2_CHK6_LKAI, VBT32.SUB2_LAST_KAI) as SUB2_CHK6_LKAI,
    coalesce(BT29.SUB2_CHK6_LAST_RSLT, '') as SUB2_CHK6_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT29MLMN
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB2_CHK6_RESULT = '1'
        )
    then '2'
    else coalesce(BT29.SUB2_CHK6_RESULT, '')
    end as SUB2_CHK6_RESULT,
    coalesce(BT29.SUB2_CHK7_BYEAR, VBT32.SUB2_BASE_YEAR) as SUB2_CHK7_BYEAR,
    coalesce(BT29.SUB2_CHK7_LYEAR, VBT32.SUB2_LAST_YEAR) as SUB2_CHK7_LYEAR,
    coalesce(BT29.SUB2_CHK7_LKAI, VBT32.SUB2_LAST_KAI) as SUB2_CHK7_LKAI,
    coalesce(BT29.SUB2_CHK7_LAST_RSLT, '') as SUB2_CHK7_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT29MLMN
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB2_CHK7_RESULT = '1'
        )
    then '2'
    else coalesce(BT29.SUB2_CHK7_RESULT, '')
    end as SUB2_CHK7_RESULT,
    coalesce(BT29.SUB2_CHK8_BYEAR, VBT32.SUB2_BASE_YEAR) as SUB2_CHK8_BYEAR,
    coalesce(BT29.SUB2_CHK8_LYEAR, VBT32.SUB2_LAST_YEAR) as SUB2_CHK8_LYEAR,
    coalesce(BT29.SUB2_CHK8_LKAI, VBT32.SUB2_LAST_KAI) as SUB2_CHK8_LKAI,
    coalesce(BT29.SUB2_CHK8_LAST_RSLT, '') as SUB2_CHK8_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT29MLMN
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB2_CHK8_RESULT = '1'
        )
    then '2'
    else coalesce(BT29.SUB2_CHK8_RESULT, '')
    end as SUB2_CHK8_RESULT,
    coalesce(BT29.SUB2_CHK9_BYEAR, VBT32.SUB2_BASE_YEAR) as SUB2_CHK9_BYEAR,
    coalesce(BT29.SUB2_CHK9_LYEAR, VBT32.SUB2_LAST_YEAR) as SUB2_CHK9_LYEAR,
    coalesce(BT29.SUB2_CHK9_LKAI, VBT32.SUB2_LAST_KAI) as SUB2_CHK9_LKAI,
    coalesce(BT29.SUB2_CHK9_LAST_RSLT, '') as SUB2_CHK9_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT29MLMN
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB2_CHK9_RESULT = '1'
        )
    then '2'
    else coalesce(BT29.SUB2_CHK9_RESULT, '')
    end as SUB2_CHK9_RESULT,
    coalesce(BT29.SUB2_CHK10_BYEAR, VBT32.SUB2_BASE_YEAR) as SUB2_CHK10_BYEAR,
    coalesce(BT29.SUB2_CHK10_LYEAR, VBT32.SUB2_LAST_YEAR) as SUB2_CHK10_LYEAR,
    coalesce(BT29.SUB2_CHK10_LKAI, VBT32.SUB2_LAST_KAI) as SUB2_CHK10_LKAI,
    coalesce(BT29.SUB2_CHK10_LAST_RSLT, '') as SUB2_CHK10_LAST_RSLT,
    case when exists (
        select
            *
        from
            BT29MLMN
        where
            STAFF_ID = VBT32.STAFF_ID and
            decimal(ENTRY_YEAR) >= decimal(VBT32.REENTRY_BASE_YEAR) and
            decimal(ENTRY_YEAR) <= decimal(VBT32.REENTRY_BASE_YEAR) + 1 and
            not (ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and ENTRY_KAI = VBT32.TOTAL_LAST_KAI) and
            SUB2_CHK10_RESULT = '1'
        )
    then '2'
    else coalesce(BT29.SUB2_CHK10_RESULT, '')
    end as SUB2_CHK10_RESULT,
    BT29.FIRST_USER,
    BT29.FIRST_PGM,
    BT29.FIRST_TMSP,
    BT29.LAST_USER,
    BT29.LAST_PGM,
    BT29.LAST_TMSP,
    VBT32.EXAM_NO,
    VBT32.SUB3_RESULT,
    rtrim(BR47.INTERVIEW_DT) as INTERVIEW_DT,
    case when BT29.STAFF_ID is null then '1' else '0' end as INSERT_FLG,
    BT23.interview_chk
    
from
    VBT32MLKR as VBT32
    left outer join BT23MLEJ as BT23 on BT23.ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and BT23.ENTRY_KAI = VBT32.TOTAL_LAST_KAI and BT23.STAFF_ID = VBT32.STAFF_ID 
    left outer join BT29MLMN as BT29 on BT29.ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and BT29.ENTRY_KAI = VBT32.TOTAL_LAST_KAI and BT29.STAFF_ID = VBT32.STAFF_ID 
    left outer join BR47ITVD as BR47 on BR47.ENTRY_YEAR = VBT32.TOTAL_LAST_YEAR and BR47.ENTRY_KAI = VBT32.TOTAL_LAST_KAI and BR47.STAFF_ID = VBT32.STAFF_ID
    inner join BM12STAF as BM12 on BM12.STAFF_ID = VBT32.STAFF_ID ,
(select
    BM01.COMPANY_CD   ,
    BM01.MISE_CD      ,
    BM01.MISE_NAME_KJ ,
    BM11.ONER_CD      ,
    BM11.ONER_NAME_KJ ,
    BM10.SIBU_NAME,
    BM01.AREA_DAI
 from
    BM01TENM as BM01 ,
    BM11ONER as BM11 ,
    BM10GSIB as BM10
 where
    BM01.COMPANY_CD = BM11.COMPANY_CD and
    BM01.ONER_CD = BM11.ONER_CD       and
    BM01.COMPANY_CD = BM10.COMPANY_CD and
    BM01.AREA_DAI = BM10.SIBU_CD         ) MISE

where
    VBT32.TOTAL_LAST_YEAR = /*entryYear*/'2006' and
    VBT32.TOTAL_LAST_KAI = /*entryKai*/'001' and
    BM12.COMPANY_CD = /*companyCd*/'00' and
    BM12.MISE_CD_1 = MISE.MISE_CD
    
    /*IF condType == "1"*/ 
    and MISE.AREA_DAI = /*sibuCd*/'031' 
    /*END*/
    /*IF condType == "2"*/ 
    and MISE.ONER_CD = /*onerCd*/'36478' 
    /*END*/
    /*IF condType == "3"*/ 
    and MISE.MISE_CD = /*miseCd*/'01776' 
    /*END*/
order by
    VBT32.EXAM_NO
