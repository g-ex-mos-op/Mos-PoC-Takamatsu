select 
  distinct 
    bm17.info_shu, 
    bm17.cate_id, 
    bm17.sub_cate_id, 
    bm17.sub_cate_name, 
    bm02.cate_name, 
    bm17.sort_key
from 
  bm17sbct bm17, 
  bm02ifct bm02 
where  
       bm17.info_shu = /*infoShu*/'04' 
  and  bm17.cate_id = /*cateId*/'005' 
  and  bm17.info_shu = bm02.info_shu
  and  bm17.cate_id = bm02.cate_id
order by sort_key, cate_id, sub_cate_id