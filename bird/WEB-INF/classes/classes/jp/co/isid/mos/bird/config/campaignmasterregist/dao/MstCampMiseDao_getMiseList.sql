select 
  bm64.camp_id,
  bm64.company_cd,
  bm64.mise_cd,
  bm01.mise_name_kj,
  bm64.first_user,
  bm64.first_pgm,
  bm64.first_tmsp,
  bm64.last_user,
  bm64.last_pgm,
  bm64.last_tmsp
from
  bm64CSMS bm64,
  bm01tenm bm01
where 
    camp_id = /*campId*/'200801'
and bm64.company_cd = bm01.company_cd
and bm64.mise_cd = bm01.mise_cd 
order by 
  bm64.mise_cd