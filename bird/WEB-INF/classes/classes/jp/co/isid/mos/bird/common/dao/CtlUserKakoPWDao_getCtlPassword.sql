select 
  ir52.user_id,
  ir52.pswd_updt_dt,
  ir52.user_pswd,
  ir52.last_pswd,
  ir52.last_user,
  ir52.last_pgm,
  ir52.last_tmsp,
  ir52.seq_no 
from 
  ir52pass ir52 
where 
  ir52.user_id = /*userId*/'99990001' 
order by 
  pswd_updt_dt desc