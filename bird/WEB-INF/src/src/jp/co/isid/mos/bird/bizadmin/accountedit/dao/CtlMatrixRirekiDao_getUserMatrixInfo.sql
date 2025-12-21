select 
  user_id, 
  matrix_key_1, 
  matrix_key_2, 
  matrix_key_3, 
  matrix_key_4, 
  matrix_key_5, 
  last_updt_dt,
  first_user,
  first_pgm,
  first_tmsp,
  last_user,
  last_pgm,
  last_tmsp 
from 
  br78mtxr
where 
  user_id = /*userId*/'99990004'
