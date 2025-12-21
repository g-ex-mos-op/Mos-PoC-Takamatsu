select  distinct bm11.oner_cd
,                bm11.oner_name_kj
,                bm11.kessan_m
from    bm51svsb bm51
,       bm01tenm as bm01
,       bm11oner as bm11
where   bm51.sibu_cd = bm01.sibu_cd
and     bm51.company_cd = bm01.company_cd
and     bm01.company_cd = bm11.company_cd
and     bm01.oner_cd = bm11.oner_cd
and     bm51.company_cd = /*companyCd*/'00'
and     bm51.sv_cd =  /*userId*/'00000921'
order by oner_cd
