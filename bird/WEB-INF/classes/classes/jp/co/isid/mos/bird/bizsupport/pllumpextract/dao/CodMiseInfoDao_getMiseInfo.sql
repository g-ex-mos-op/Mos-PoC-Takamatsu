select   bm01.company_cd
,        bm01.mise_cd
,        bm01.mise_name_kj
from     wt54plrc as wt54
,        bm01tenm as bm01
where    wt54.company_cd = bm01.company_cd
and      wt54.mise_cd = bm01.mise_cd
and      bm01.company_cd = /*companyCd*/'00'
and      bm01.sibu_cd = /*sibuCd*/'081'
and      wt54.pl_ym between /*fromDt*/'200604' and /*toDt*/'200604'
group by bm01.company_cd
,        bm01.mise_cd
,        bm01.mise_name_kj
order by mise_cd
