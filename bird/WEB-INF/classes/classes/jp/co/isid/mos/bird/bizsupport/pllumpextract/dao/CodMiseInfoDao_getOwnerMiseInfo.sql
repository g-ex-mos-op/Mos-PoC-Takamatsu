select   bm01.company_cd
,        bm01.mise_cd
,        bm01.mise_name_kj
,        bm01.open_dt
from     bt17pldt as bt17
,        bm01tenm as bm01
where    bt17.company_cd = bm01.company_cd
and      bt17.mise_cd = bm01.mise_cd
and      bm01.company_cd = /*companyCd*/'00'
and      bm01.oner_cd = /*onerCd*/'36378'
and      bt17.pl_ym between /*fromDt*/'200601' and /*toDt*/'200603'
group by bm01.company_cd
,        bm01.mise_cd
,        bm01.mise_name_kj
,        bm01.open_dt
order by mise_cd