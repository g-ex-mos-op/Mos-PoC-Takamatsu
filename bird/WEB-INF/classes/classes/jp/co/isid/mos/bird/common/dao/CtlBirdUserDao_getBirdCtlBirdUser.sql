select 
  br01.user_id,
  br01.user_name_kj,
  br01.user_pswd,
  br01.pswd_updt_dt,
  br01.last_pswd,
  br01.usertype_cd,
  br01.last_user,
  br01.last_pgm,
  br01.last_tmsp
from 
  br01user br01
where 
  br01.user_id = /*userId*/'99990003'