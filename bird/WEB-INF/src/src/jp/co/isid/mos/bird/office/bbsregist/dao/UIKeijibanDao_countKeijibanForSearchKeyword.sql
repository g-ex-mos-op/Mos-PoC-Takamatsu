select count(*)
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