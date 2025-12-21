select count(*)
from bm60cpdt
where disp_from <= /*targetDt*/'20080301'
and   disp_to >= /*targetDt*/'20080301'
/*IF campId != null*/
and   camp_id <> /*campId*/'200806'
/*END*/