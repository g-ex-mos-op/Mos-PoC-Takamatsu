select 
  max(eigyo_dt) as eigyo_dt
from 
  bd19past
where 
    company_cd = /*companyCd*/'00'
and eigyo_dt > /*nendo*/'200804'