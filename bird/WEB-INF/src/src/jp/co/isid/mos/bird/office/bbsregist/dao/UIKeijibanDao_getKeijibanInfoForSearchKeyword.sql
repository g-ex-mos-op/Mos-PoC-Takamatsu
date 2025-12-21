select sub.* 
from 
(
select row_number() over(order by bt06.reg_date desc, bt06.seq desc) as row_num,
       rtrim(bt06.reg_date) reg_date,
       rtrim(bt06.seq) seq,
       rtrim(bt06.cate_id) cate_id,
       rtrim(bm02.cate_name) cate_name,
       rtrim(bt06.title) title,
       rtrim(bt06.message) message,
       rtrim(bt06.attach_name1) attach_name1,
       rtrim(bt06.attach_name2) attach_name2,
       rtrim(bt06.attach_name3) attach_name3,
       rtrim(bt06.attach_fl1) attach_fl1,
       rtrim(bt06.attach_fl2) attach_fl2,
       rtrim(bt06.attach_fl3) attach_fl3,
       rtrim(bt06.sakujo_flg) sakujo_flg,
       rtrim(bt06.first_user) first_user,
       rtrim(br01a.user_name_kj) first_user_name_kj,
       rtrim(bt06.first_pgm) first_pgm,
       bt06.first_tmsp,
       rtrim(bt06.last_user) last_user,
       rtrim(br01b.user_name_kj) last_user_name_kj,
       rtrim(bt06.last_pgm) last_pgm,
       bt06.last_tmsp
from
   bt06kban bt06,
   br01user br01a,
   br01user br01b,
   bm02ifct bm02
where
   bm02.info_shu = '06'
   and bt06.sakujo_flg <> '1'
   /*IF keyWord != null && titleFlg == "1" && msgFlg == "0"*/
     and bt06.title like /*keyWord*/  
   /*END*/
   /*IF keyWord != null && titleFlg == "0" && msgFlg == "1"*/
     and bt06.message like /*keyWord*/
   /*END*/
   /*IF keyWord != null && titleFlg == "1" && msgFlg == "1"*/
     and ( bt06.message like /*keyWord*/ or bt06.title like /*keyWord*/ )
   /*END*/
   and bt06.cate_id = bm02.cate_id
   and bt06.first_user = br01a.user_id
   and bt06.last_user = br01b.user_id
) as sub
where row_num between /*from*/ and /*to*/
