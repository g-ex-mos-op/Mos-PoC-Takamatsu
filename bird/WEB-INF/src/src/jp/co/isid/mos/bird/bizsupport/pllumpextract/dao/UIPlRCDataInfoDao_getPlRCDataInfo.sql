select      bm01.company_cd
,           bm01.mise_cd
,           bm01.mise_name_kj
,           wt54.koumoku_no
,           wt54.koumoku_name
,           wt54.disp_order
,           sum(wt54.kingaku) as kingaku
from        bm01tenm as bm01
left join   wt54plrc as wt54
on          (bm01.company_cd = wt54.company_cd
and         bm01.mise_cd = wt54.mise_cd)
where       bm01.sibu_cd = /*sibuCd*/'081'
and         bm01.company_cd= /*companyCd*/'00'
and         wt54.pl_ym between /*fromDt*/'200601' and /*toDt*/'200603'
and         koumoku_zoku <> '1'
group by    bm01.company_cd
,           bm01.mise_cd
,           bm01.mise_name_kj
,           wt54.koumoku_no
,           wt54.koumoku_name
,           wt54.disp_order
order by    bm01.company_cd
,           bm01.mise_cd
,           wt54.disp_order