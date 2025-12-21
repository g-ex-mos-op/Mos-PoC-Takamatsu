select 
   sub_cate_id,
   count(*) as SUB_CATE_COUNT
from
   bt03docm
where  
     cate_id = /*cateId*/
/*IF regDate != null*/
 and reg_date like /*regDate*/
/*END*/
 and pub_org = /*bumonCd*/'00014051'
 and sakujo_flg <> '1'
group by sub_cate_id 
having count(*) > 0