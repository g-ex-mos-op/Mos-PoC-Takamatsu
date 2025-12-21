select max(seq_no)
from ir52pass
where 
  user_id = /*userId*/'99990004' and 
  pswd_updt_dt = /*updateDt*/'20081117'