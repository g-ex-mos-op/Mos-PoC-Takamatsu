select 
  count(*)
from 
  bs06niph
where
  company_cd = /*companyCd*/'00' and
  eigyo_dt = /*eigyoDt*/'200710'