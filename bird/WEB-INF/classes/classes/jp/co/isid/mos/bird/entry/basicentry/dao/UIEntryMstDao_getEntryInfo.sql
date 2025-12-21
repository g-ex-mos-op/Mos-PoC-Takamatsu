SELECT 
	br17.ENTRY_CD,
	br17.ENTRY_YEAR,
	br17.ENTRY_KAI,
	rtrim(br17.ENTRY_TITLE) as ENTRY_TITLE,
	rtrim(br17.ENTRY_PLACE) as ENTRY_PLACE,
	br17.NUMBER_LIMIT,
	br17.PLACE_LIMIT,
	rtrim(br17.NOTE) as NOTE,
	br17.SPARE_FLG1,
	br17.SPARE_FLG2,
	rtrim(br17.SAKUJO_FLG) as SAKUJO_FLG,
    case when STAFF.staffSu is null then 0 else staff.staffSu end as SOMOSIKOMI_NUM,
    case when ONER.onerSu is null then 0 else ONER.onerSu end as ONERSU,
	br17.FIRST_USER,
	br17.FIRST_PGM,
	br17.FIRST_TMSP,
	br17.LAST_USER,
	br17.LAST_PGM,
	br17.LAST_TMSP
FROM
	BR17ENTL br17 
    left join (select entry_cd, entry_year, entry_Kai, count(distinct staff_id) as staffSu 
               from bt22enkn  
               where ENTRY_CD = /*entryCd*/'01'
               and   ENTRY_YEAR = /*entryYear*/'2006'
               and   ENTRY_KAI = /*entryKai*/'010' 
               group by entry_cd, entry_year, entry_Kai
              ) STAFF 
      on (br17.entry_cd = STAFF.entry_cd and br17.entry_year = STAFF.entry_year and  br17.entry_Kai = STAFF.entry_kai)
    left join (select entry_cd, entry_year, entry_Kai, count(distinct oner_cd) as onerSu 
               from bt22enkn 
               where ENTRY_CD = /*entryCd*/'01'
               and   ENTRY_YEAR = /*entryYear*/'2006'
               and   ENTRY_KAI = /*entryKai*/'010'
               group by entry_cd, entry_year, entry_Kai
              ) ONER 
      on (br17.entry_cd = ONER.entry_cd and br17.entry_year = ONER.entry_year and  br17.entry_Kai = ONER.entry_kai)
WHERE 
    br17.ENTRY_CD = /*entryCd*/'01'
and br17.ENTRY_YEAR = /*entryYear*/'2006'
and br17.ENTRY_KAI = /*entryKai*/'010' 