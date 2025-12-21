select          br03.role_cd
,               br03.role_name
,               br02.menu_id
,               br02.sub_menu_id
,               br02.sub_menu_name
from            br02bmnu as br02
,               br03role as br03
where           br03.role_cd in (/*firstRoleId*/,/*secondRoleId*/,/*thirdRoleId*/,/*forthRoleId*/,/*fifthRoleId*/)
and             br02.menu_id = /*menuId*/
and             br02.menu_id <> '00'
order by        br02.sub_menu_id
