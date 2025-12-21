select 
  camp_id,
  company_cd,
  rtrim(camp_title) as camp_title,
  camp_from,
  camp_to,
  disp_from,
  disp_to,
  yobi_from,
  yobi_to,
  target_kbn,
  first_user,
  first_pgm,
  first_tmsp,
  last_user,
  last_pgm,
  last_tmsp,
  camp_from as CAMP_FROM_EDIT_BEF 
from 
  bm60cpdt 
where
  camp_id = /*campId*/'200806'