select          br03.role_cd
,               br03.role_name
,               br03.bunrui_cd
,               bc03.bunrui_name
,               br02.menu_id
,               br02.menu_name
,               br02.sub_menu_id
,               br02.sub_menu_name
,               br02.sort_seq
from            br02bmnu as br02
,               br03role as br03
,               bc03rlbr as bc03
where           concat(br02.menu_id,br02.sub_menu_id) in (/*firstID*/,/*secondID*/,/*thirdID*/,/*forthID*/,/*fifthID*/)
and             br03.bunrui_cd = bc03.bunrui_cd
and             br03.bunrui_cd = /*bunruiCd*/
order by        bc03.sort_seq
,               role_cd
,               menu_id
,               sub_menu_id