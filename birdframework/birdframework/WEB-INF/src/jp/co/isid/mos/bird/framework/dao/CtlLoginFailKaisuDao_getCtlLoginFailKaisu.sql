select
  USER_ID,
  LOGIN_FAIL,
  LAST_USER,
  LAST_PGM,
  LAST_TMSP
from
  ir05fail
where
  user_id = /*userId*/