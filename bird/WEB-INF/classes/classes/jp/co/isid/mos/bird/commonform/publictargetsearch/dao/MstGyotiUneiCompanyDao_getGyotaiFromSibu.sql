select  distinct
        bm04.gyotai_kbn
,       bc09.gyotai_kbn_name

from 
       bm10gsib bm10, bm04gtcp bm04, bc09gtai bc09
where 
       bm10.company_cd=bm04.company_cd
and    bm04.gyotai_kbn=bc09.gyotai_kbn
and    bm10.company_cd =/*companyCd*/'00'
and    bm10.sibu_cd in /*sibuCd*/('001', '008', '005')
