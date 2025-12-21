select      role_cd
,           menu_id
,           sub_menu_id
,           enable_flg
,           extra_flg 
from        br05actr
where       role_cd in (/*firstRoleId*/,/*secondRoleId*/,/*thirdRoleId*/,/*forthRoleId*/,/*fifthRoleId*/)
and         menu_id = /*menuId*/
order by    role_cd
,           menu_id
,           sub_menu_id
