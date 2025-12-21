select 
  bm63.camp_id,
  bm63.company_cd,
  bm63.sibu_cd,
  bm63.area_dai_flg,
  bm63.first_user,
  bm63.first_pgm,
  bm63.first_tmsp,
  bm63.last_user,
  bm63.last_pgm,
  bm63.last_tmsp
from
  bm63cssb bm63
where 
  camp_id = /*campId*/'200801' 
order by 
  sibu_cd