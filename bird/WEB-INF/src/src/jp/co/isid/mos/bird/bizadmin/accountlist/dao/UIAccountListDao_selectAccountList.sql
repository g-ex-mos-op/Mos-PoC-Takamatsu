select distinct
  userinfo.user_id,
  userinfo.user_name_kj,
  userinfo.user_name_kana,
  userinfo.userType_cd,
  userinfo.mail_add,
  userinfo.stop_flg,
  userinfo.applied_dt,
  case when userinfo.keiyaku_type = '03' then '1' 
       when userinfo.keiyaku_type = '04' then '9' 
       when userinfo.maxflg1 is null then '0' 
       else userinfo.maxflg1 end as maxflg1,
  case when userinfo.keiyaku_type = '03' then '0' 
       when userinfo.keiyaku_type = '04' then '9' 
       when userinfo.maxflg2 is null then '0' 
       else userinfo.maxflg2 end as maxflg2,
  case when userinfo.keiyaku_type = '03' then '0' 
       when userinfo.keiyaku_type = '04' then '9' 
       when userinfo.maxflg3 is null then '0' 
       else userinfo.maxflg3 end as maxflg3,
  userinfo.maxflg4,
  userinfo.maxflg5,
  userinfo.keiyaku_type,
  bc26.keiyaku_type_nm,
  userinfo.close_dt,
  userinfo.close_flg
 
from
(
select 
  user_list.company_cd,
  user_list.user_id,
  user_list.user_name_kj,
  user_list.user_name_kana,
  user_list.userType_cd,
  user_list.mail_add, 
  user_list.stop_flg, 
  user_list.applied_dt,
  user_option.maxflg1,
  user_option.maxflg2,
  user_option.maxflg3,
  user_option.maxflg4,
  user_option.maxflg5,
  min(br59.keiyaku_type) as keiyaku_type,
  close_dt,
  close_flg 
from
(
(
select 
  bm06.company_cd,
  bm06.user_id, 
  br01.user_Name_Kj, 
  br01.user_Name_Kana,
  br01.userType_cd,
  br01.mail_add, 
  br01.stop_flg,
  br01.applied_dt, 
  '99991231' as close_dt,
  '0' as close_flg 
from 
  bm06uonr bm06, 
  br01user br01 
where 
  bm06.user_id = br01.user_id and 
  concat(bm06.company_cd, bm06.oner_cd) in (select concat(company_cd, oner_cd) from bm06uonr where user_id = /*userId*/'99990004') and
  br01.sekyu_flg = '1'
) 
union all 
(
select 
  bm07.company_cd,
  bm07.user_id, 
  br01.user_Name_Kj, 
  br01.user_Name_Kana,
  br01.userType_cd,
  br01.mail_add, 
  br01.stop_flg, 
  br01.applied_dt, 
  bm01.close_dt,
  case when bm01.close_dt < /*sysDt*/'20090224'  then '1' else '0' end as close_flg
from 
  bm07uten bm07, 
  br01user br01, 
  bm01tenm bm01
where 
  bm07.user_id = br01.user_id and
  concat(bm07.company_cd, bm07.mise_cd) in (
    SELECT distinct
      concat(bm01.company_cd, bm01.mise_cd)
    from 
      bm01tenm bm01
    where 
      concat(bm01.company_cd, bm01.oner_cd) in (select concat(company_cd, oner_cd) from bm06uonr where user_id = /*userId*/'99990004')
  ) and
  bm07.company_cd = bm01.company_cd and 
  bm07.mise_cd = bm01.mise_cd and
  br01.sekyu_flg = '1' and
  bm01.close_dt > /*closeKizyunDt*/'20080226'
) 
) as user_list
left join 
(
select 
  br04.user_id,
  max(br44.option_flg1) as maxflg1, 
  max(br44.option_flg2) as maxflg2, 
  max(br44.option_flg3) as maxflg3, 
  max(br44.option_flg4) as maxflg4, 
  max(br44.option_flg5) as maxflg5   
from br04usrl br04,br44rlop br44 
where br04.role_cd = br44.role_cd 
group by br04.user_id 
) as user_option on (user_list.user_id = user_option.user_id),
br04usrl br04,
br59ktrl br59

where 
  user_list.user_id = br04.user_id and
  user_list.company_cd = br59.company_cd and 
  br04.role_cd = br59.role_cd

group by
  user_list.company_cd,
  user_list.user_id,
  user_list.user_name_kj,
  user_list.user_name_kana,
  user_list.userType_cd,
  user_list.mail_add, 
  user_list.stop_flg, 
  user_list.applied_dt,
  user_option.maxflg1,
  user_option.maxflg2,
  user_option.maxflg3,
  user_option.maxflg4,
  user_option.maxflg5,
  user_list.close_dt,
  user_list.close_flg
) as userinfo,
bc26kytp bc26

where 
  userinfo.keiyaku_type = bc26.keiyaku_type

order by 
  userinfo.close_flg,
  userinfo.keiyaku_type, 
  userinfo.user_id
