select sub.* 
from 
(
select row_number() over(order by bm02.SORT_KEY,bm17.SORT_KEY, rtrim(bt04.sort_seq), rtrim(bt04.reg_date) desc , rtrim(bt04.seq) desc) as row_num,
       rtrim(bt04.reg_date) reg_date,
       rtrim(bt04.seq) seq,
       rtrim(bt04.cate_id) cate_id,
       rtrim(bm02.cate_name) cate_name,
       rtrim(bt04.sub_cate_id) sub_cate_id,
       rtrim(bm17.sub_cate_name) sub_cate_name,
       rtrim(bt04.title) title,
       rtrim(bt04.discription) discription,
       rtrim(bt04.pub_date_from) pub_date_from,
       rtrim(bt04.pub_date_to) pub_date_to,
       rtrim(bt04.pub_user) pub_user,
       rtrim(vbr01.user_name_kj) user_name_kj,
       rtrim(bt04.pub_org) pub_org,
       rtrim(bt04.pub_org_name) pub_org_name,
       rtrim(bt04.file_name) file_name,
       rtrim(bt04.attach_name1) attach_name1,
       rtrim(bt04.attach_name2) attach_name2,
       rtrim(bt04.attach_name3) attach_name3,
       rtrim(bt04.attach_fl1) attach_fl1,
       rtrim(bt04.attach_fl2) attach_fl2,
       rtrim(bt04.attach_fl3) attach_fl3,
       rtrim(bt04.limit_kbn) limit_kbn,
       rtrim(bt04.sort_seq) sort_seq,
       rtrim(bt04.sakujo_flg) sakujo_flg,
       rtrim(bc08.bumon_name) bumon_name,       
       bm02.SORT_KEY as CATE_SORT_KEY,
       bm17.SORT_KEY as SUB_CATE_SORT_KEY,
       rtrim(bt04.first_user) first_user,
       rtrim(bt04.first_pgm) first_pgm,
       bt04.first_tmsp,
       rtrim(bt04.last_user) last_user,
       rtrim(bt04.last_pgm) last_pgm,
       bt04.last_tmsp
from
   bt04frmm bt04 
   left join vbr01user_bmon vbr01 on bt04.pub_user = vbr01.user_id
   left outer join bc08cbmn bc08 on bc08.bumon_cd = bt04.pub_org,
   bm02ifct bm02,
   bm17sbct bm17
where  bt04.cate_id = /*cateId*/
   and bt04.sub_cate_id = /*subCateId*/
   /*IF regDate != null*/
	   and bt04.reg_date like /*regDate*/
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
   
) as sub
where row_num between /*from*/ and /*to*/