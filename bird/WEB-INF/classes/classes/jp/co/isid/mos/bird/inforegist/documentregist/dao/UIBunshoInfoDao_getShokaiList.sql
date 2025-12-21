select  
       rtrim(bt03.reg_date) reg_date,
       rtrim(bt03.seq) seq,
       rtrim(bt03.cate_id) cate_id,
       rtrim(bm02.cate_name) cate_name,
       rtrim(bt03.sub_cate_id) sub_cate_id,
       rtrim(bm17.sub_cate_name) sub_cate_name,
       rtrim(bt03.title) title,
       rtrim(bt03.discription) discription,
       rtrim(bt03.pub_date_from) pub_date_from,
       rtrim(bt03.pub_date_to) pub_date_to,
       rtrim(bt03.pub_user) pub_user,
       rtrim(vbr01.user_name_kj) user_name_kj,
       rtrim(bt03.pub_org) pub_org,
       rtrim(bt03.pub_org_name) pub_org_name,
       rtrim(bt03.r_company_cd) r_company_cd,
       rtrim(bt03.file_name) file_name,
       rtrim(bt03.attach_name1) attach_name1,
       rtrim(bt03.attach_name2) attach_name2,
       rtrim(bt03.attach_name3) attach_name3,
       rtrim(bt03.attach_fl1) attach_fl1,
       rtrim(bt03.attach_fl2) attach_fl2,
       rtrim(bt03.attach_fl3) attach_fl3,
       rtrim(bt03.limit_kbn) limit_kbn,
       rtrim(bt03.sort_seq) sort_seq,
       rtrim(bt03.sakujo_flg) sakujo_flg,
       rtrim(bt03.first_user) first_user,
       rtrim(bt03.first_pgm) first_pgm,
       bt03.first_tmsp,
       rtrim(bt03.last_user) last_user,
       rtrim(bt03.last_pgm) last_pgm,
       bt03.last_tmsp
from
   bt03docm bt03
   left join vbr01user_bmon vbr01 on bt03.pub_user = vbr01.user_id,
   bm02ifct bm02,
   bm17sbct bm17
where  bt03.cate_id = /*cateId*/'001'
   and bt03.sub_cate_id = /*subCateId*/'001'
   and bm02.info_shu = '03'
   and bt03.cate_id = bm02.cate_id
   and bm17.info_shu = '03'
   and bt03.cate_id = bm17.cate_id
   and bt03.sub_cate_id = bm17.sub_cate_id
   and bt03.sakujo_flg <> '1'
order by
       rtrim(bt03.cate_id),
       rtrim(bt03.sub_cate_id),
       rtrim(bt03.sort_seq),       
       rtrim(bt03.reg_date), 
       rtrim(bt03.seq)
