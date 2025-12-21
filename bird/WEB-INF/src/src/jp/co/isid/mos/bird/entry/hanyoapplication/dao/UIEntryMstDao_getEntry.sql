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
    decimal(case when STAFF.staffSu is null then 0 else staff.staffSu end) as STAFFSU,
    decimal(case when ONER.onerSu is null then 0 else ONER.onerSu end) as ONERSU,
    decimal(case when ONER_STAFF.onerStaffSu is null then 0 else ONER_STAFF.onerStaffSu end) as ONER_STAFF,
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
 	           where CONCAT(ENTRY_CD, CONCAT(ENTRY_YEAR, ENTRY_KAI)) IN /*joinCharacterList*/('052006044', '052006045', '052006046', '052006047') 
               group by entry_cd, entry_year, entry_Kai
 	           ) STAFF 
      on (br17.entry_cd = STAFF.entry_cd and br17.entry_year = STAFF.entry_year and  br17.entry_Kai = STAFF.entry_kai)
    left join (select entry_cd, entry_year, entry_Kai, count(distinct oner_cd) as onerSu 
               from bt22enkn 
               where CONCAT(ENTRY_CD, CONCAT(ENTRY_YEAR, ENTRY_KAI)) IN /*joinCharacterList*/('052006044', '052006045', '052006046', '052006047') 
               group by entry_cd, entry_year, entry_Kai
               ) ONER 
      on (br17.entry_cd = ONER.entry_cd and br17.entry_year = ONER.entry_year and  br17.entry_Kai = ONER.entry_kai)
    left join (select entry_cd, entry_year, entry_kai, count(distinct staff_id) as onerStaffSu 
               from bt22enkn 
               where CONCAT(ENTRY_CD, CONCAT(ENTRY_YEAR, ENTRY_KAI)) IN /*joinCharacterList*/('052006044', '052006045', '052006046', '052006047') 
               and   ONER_CD = /*onerCd*/'36478' 
               group by entry_cd, entry_year, entry_Kai 
               ) ONER_STAFF
      on (br17.entry_cd = ONER_STAFF.entry_cd and br17.entry_year = ONER_STAFF.entry_year and  br17.entry_Kai = ONER_STAFF.entry_kai)
WHERE 
    CONCAT(br17.ENTRY_CD, CONCAT(br17.ENTRY_YEAR, br17.ENTRY_KAI)) IN /*joinCharacterList*/('052006045', '052006046', '052006047') 
and br17.sakujo_flg <> '1' 
ORDER BY br17.ENTRY_CD, br17.ENTRY_YEAR, br17.ENTRY_KAI