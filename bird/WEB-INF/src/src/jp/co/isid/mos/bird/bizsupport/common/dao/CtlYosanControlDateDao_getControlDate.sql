select 
  company_cd,
  nendo,
  shori_kbn,
  state_flg,
  rtrim(shori_dt) as shori_dt,
  last_pgm,
  last_user,
  last_tmsp 
from 
  br62ycdt 
where
  company_cd = /*companyCd*/'00' and
  nendo = /*nendo*/'2007' and
  shori_kbn = /*shoriKbn*/'01'