select
  vir51user.user_Id,
  vir51user.USER_NAME_KJ,
  vir51user.USER_PSWD,
  vir51user.PSWD_UPDT_DT,
  vir51user.stop_Flg
from
  vir51user,
  ir04okur
where
  vir51user.user_id = /*userId*/
  and vir51user.stop_flg <> '1'
  and vir51user.user_id = ir04okur.user_id