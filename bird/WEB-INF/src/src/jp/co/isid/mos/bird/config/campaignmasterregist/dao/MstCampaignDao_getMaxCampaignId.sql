select 
  max(camp_id)
from 
  bm60cpdt bm60
where
     company_cd = /*companyCd*/'00'
and camp_id like /*year*/'2008%'