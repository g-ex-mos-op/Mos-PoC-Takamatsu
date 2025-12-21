select   distinct bm10.sibu_cd
,                 bm10.sibu_name
from     bm01tenm as bm01
,        bm10gsib as bm10
where    bm01.sibu_cd = bm10.sibu_cd
and      bm01.company_cd = bm10.company_cd
and      bm10.company_cd = /*companyCd*/'00'
and      mise_kbn like '_2_'
order by bm10.sibu_cd