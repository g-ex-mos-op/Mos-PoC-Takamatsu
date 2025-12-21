select 
  max(br70.scdl_id) as scdl_id
from 
  br70scdl br70
where 
  br70.taisho_cd = /*taishoCd*/'02' and 
  br70.company_cd = /*companyCd*/'00' and 
  br70.scdl_date = /*taishoDt*/'20081208'
