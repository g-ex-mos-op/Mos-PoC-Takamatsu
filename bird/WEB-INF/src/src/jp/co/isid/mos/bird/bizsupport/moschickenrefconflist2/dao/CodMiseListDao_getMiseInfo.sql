select distinct a.mise_cd, b.mise_name_kj
from bt70crsv a, bm01tenm b 
where
	ckanri_no = /*cKanriNo*/'200632' 
and a.company_cd = '00' 
and reserve_dt = /*reserveDt*/'20061224' 
and cancel_flg <> '1' 
and a.company_cd = b.company_cd and a.mise_cd = b.mise_cd 
and a.mise_cd >= /*miseCdFrom*/ 
and a.mise_cd <= /*miseCdTo*/ 
order by mise_cd