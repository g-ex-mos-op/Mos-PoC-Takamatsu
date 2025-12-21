select 
	bt30.entry_cd,
	bt30.entry_year,
	bt30.entry_kai,
	bt30.staff_id,
	bt30.course_status,
	bt30.comple_course_dt,
	bt30.comple_point,
	bt30.comple_course_cd,
	bt30.comple_course_name 
from bt30enkj bt30, 
    (select entry_cd, entry_year, max(entry_kai) as entry_kai, staff_id 
     from bt30enkj
     where entry_cd   = /*entryCd*/'30'
     and   entry_year = /*entryYear*/'2006'
     and   staff_id   = /*staffId*/'98005334'
     group by entry_cd,entry_year,staff_id) bt30max
where 
    bt30.entry_cd   = bt30max.entry_cd
and bt30.entry_year = bt30max.entry_year
and bt30.entry_kai  = bt30max.entry_kai
and bt30.staff_id   = /*staffId*/'98005334'