select 
   sub_cate_id,
   count(*) as SUB_CATE_COUNT
from
   bt04frmm
where  
         cate_id = /*cateId*/
  /*IF regDate != null*/
     and reg_date like /*regDate*/
  /*END*/
     and sakujo_flg <> '1'
     and pub_org = (select bumon_cd from br01user where user_id = /*userId*/'99990001')
group by sub_cate_id 
having count(*) > 0