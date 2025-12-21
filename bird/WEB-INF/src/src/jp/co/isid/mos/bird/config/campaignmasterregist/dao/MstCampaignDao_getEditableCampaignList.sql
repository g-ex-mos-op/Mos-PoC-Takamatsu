select 
  bm60.camp_id, 
  bm60.company_cd,
  camp_title, 
  camp_from, 
  camp_to, 
  disp_from, 
  disp_to, 
  target_kbn
from 
  bm60cpdt bm60
where 
  /*kizyunDt*/'20080330' < camp_to
order by CAMP_FROM DESC
,        CAMP_ID DESC