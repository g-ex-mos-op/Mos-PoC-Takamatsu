select count(*)
from
   bt03docm bt03 
   left join vbr01user_bmon vbr01 on bt03.pub_user = vbr01.user_id,
   bm02ifct bm02,
   bm17sbct bm17
where  bt03.cate_id = /*cateId*/'001'
   and bt03.sub_cate_id = /*subCateId*/'001'
   /*IF regDate != null*/
	   and bt03.reg_date like /*regDate*/'20070801'
   -- ELSE and bt03.reg_date between /*kikanFrom*/'20050401' and /*kikanTo*/'20080331'
   /*END*/
   and bt03.r_company_cd = /*rCompanyCd*/'00'
   and bt03.pub_org = /*bumonCd*/'00000001'
   and bm02.info_shu = '03'
   and bt03.cate_id = bm02.cate_id
   and bm17.info_shu = '03'
   and bm02.info_shu = bm17.info_shu
   and bm02.cate_id = bm17.cate_id
   and bt03.sub_cate_id = bm17.sub_cate_id
   and bt03.sakujo_flg <> '1'