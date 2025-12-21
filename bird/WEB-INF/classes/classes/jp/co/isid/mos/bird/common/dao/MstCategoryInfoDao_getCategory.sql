select distinct cate_id, cate_name, sort_key 
from bm02ifct
where  info_shu = /*infoShu*/'04'
order by sort_key, cate_id