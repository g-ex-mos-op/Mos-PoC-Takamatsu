select 
bm01.company_cd, 
bm01.mise_cd,
bm01.mise_name_kj,
bm01.sibu_cd,
bm10.sibu_name,
bm01.block_cd,
bc23.block_name,
bm50.sv_cd,
br01.user_name_kj,
bm50.first_user,
bm50.first_pgm,
bm50.first_tmsp,
bm50.last_user,
bm50.last_pgm,
bm50.last_tmsp
from 
    bm01tenm bm01
    left join bm50tanm bm50 
      on (bm01.company_cd = bm50.company_cd and bm01.mise_cd = bm50.mise_cd)
    left join br01user br01
      on (bm50.sv_cd = br01.user_id)
    left join bm10gsib bm10
      on (bm01.company_cd = bm10.company_cd and bm01.sibu_cd = bm10.sibu_cd)
    left join bc23blck bc23
      on (bm01.block_cd = bc23.block_cd)
where 
    bm01.company_cd = /*companyCd*/'00'
/*IF sibuCd != "ALL" */
and bm01.sibu_cd = /*sibuCd*/'031'
/*END*/
and bm01.close_dt >= /*sysDate*/'20070401'
order by 
    bm01.company_cd,
    bm01.sibu_cd,
    bm01.block_cd,
    bm01.mise_cd