select 
  ir51.user_id,
  ir51.hatsurei_dt,
  ir51.user_name_kj,
  ir51.user_pswd,
  vir51.last_pswd,
  vir51.pswd_updt_dt,
  ir51.last_user,
  ir51.last_pgm,
  ir51.last_tmsp 
from
  ir51user ir51,
  vir51user vir51 
where 
  ir51.user_id = vir51.user_id and
  ir51.hatsurei_dt = vir51.hatsurei_dt and 
  ir51.user_id = /*userId*/'99990001'