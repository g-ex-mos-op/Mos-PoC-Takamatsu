select 
  company_cd,
  mise_cd,
  sv_cd,
  first_user,
  first_pgm,
  first_tmsp,
  last_user,
  last_pgm,
  last_tmsp 
from bm50tanm
where company_cd = /*companyCd*/'00' 
  and mise_cd = /*miseCd*/'01776'