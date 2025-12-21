select distinct sibu_cd
from 
  bm50tanm bm50,
  bm01tenm bm01 
where 
    bm50.company_cd = bm01.company_cd
and bm50.mise_cd = bm01.mise_cd
and bm50.sv_cd = /*svCd*/'99990003'
and bm50.company_cd = /*companyCd*/'00'