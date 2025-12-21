SELECT
    BT32A.*,
    case when exists(
        select 
            STAFF_ID
        from
            BT32MLKR
        where
            concat(SUB3_LAST_YEAR ,SUB3_LAST_KAI ) = (
                select
                    max(concat(ENTRY_YEAR, ENTRY_KAI))
                from
                    BR17ENTL
                where
                    ENTRY_CD = '10' and
                    concat(ENTRY_YEAR, ENTRY_KAI) < /*entryYearKai*/'2006003'
            ) and
            SUB3_RESULT = '0' and
            STAFF_ID = BT32A.STAFF_ID
    )
    then '1'
    else '0'
    end as BEFORE_FLG

FROM
    BT32MLKR BT32A
WHERE
    BT32A.STAFF_ID = /*staffId*/'' AND
concat(BT32A.TOTAL_LAST_YEAR ,BT32A.TOTAL_LAST_KAI ) = (

    SELECT max(concat(TOTAL_LAST_YEAR ,TOTAL_LAST_KAI )) as TOTAL_LAST
      FROM BT32MLKR
     WHERE cast(integer(REENTRY_BASE_YEAR) + 1 as char(4)) >= /*entryYear*/'2006'
       AND concat(TOTAL_LAST_YEAR ,TOTAL_LAST_KAI ) <> /*entryYearKai*/'2006003'
       AND STAFF_ID = /*staffId*/''
     GROUP BY staff_id

)