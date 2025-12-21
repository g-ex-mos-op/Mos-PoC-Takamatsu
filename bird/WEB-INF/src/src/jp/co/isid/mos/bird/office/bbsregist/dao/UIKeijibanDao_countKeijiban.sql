select count(*)
from
   bt06kban bt06,
   br01user br01a,
   br01user br01b,
   bm02ifct bm02
where
   bm02.info_shu = '06'
   and bt06.sakujo_flg <> '1'
   /*IF cateid != null */
     and bt06.cate_id = /*cateId*/
   /*END*/
   /*IF cateid == null */
     and bt06.cate_id < '900'
   /*END*/
   and bt06.cate_id = bm02.cate_id
   and bt06.first_user = br01a.user_id
   and bt06.last_user = br01b.user_id