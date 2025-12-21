select count(*)
from
   bt04frmm bt04 
   left join vbr01user_bmon vbr01 on bt04.pub_user = vbr01.user_id,
   bm02ifct bm02,
   bm17sbct bm17
where  bt04.cate_id = /*cateId*/
   and bt04.sub_cate_id = /*subCateId*/
   /*IF regDate != null*/
     and bt04.reg_date like /*regDate*/
   -- ELSE and bt04.reg_date between /*kikanFrom*/'20050401' and /*kikanTo*/'20080331'
   /*END*/
   and bm02.info_shu = '04'
   and bt04.cate_id = bm02.cate_id
   and bm17.info_shu = '04'
   and bm02.info_shu = bm17.info_shu
   and bm02.cate_id = bm17.cate_id
   and bt04.sub_cate_id = bm17.sub_cate_id
   and bt04.sakujo_flg <> '1'
   and bt04.r_company_cd = (select r_company_cd from bm03uscp where user_id = /*userId*/'99990001' and zokusei_kbn = '1')
   and bt04.pub_org  = (select bumon_cd from br01user where user_id = /*userId*/'99990001')