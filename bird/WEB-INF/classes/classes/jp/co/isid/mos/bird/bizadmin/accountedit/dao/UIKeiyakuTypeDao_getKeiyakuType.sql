select 
  min(br59.keiyaku_type) as keiyaku_type

from 
  br59ktrl br59,
  br04usrl br04 

where 
  br59.role_cd = br04.role_cd and
  br04.user_id = /*userId*/'99990002'