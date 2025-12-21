select 
   count(*)
from
   bt03docm bt03
where  bt03.reg_date like /*regDate*/
   and bt03.cate_id = /*cateId*/
   and bt03.sub_cate_id = /*subCateId*/
   and bt03.sakujo_flg <> '1'