select     br03.role_cd
,          br03.role_name
,          br03.bunrui_cd
,          br03.description
,          br03.sort_seq
,          bc03.bunrui_name
,          bc03.sort_seq 
,          br04.bat_flg
from       br03role as br03
,          br04usrl as br04
,          bc03rlbr as bc03
where      br03.role_cd= br04.role_cd  
and        br03.bunrui_cd = bc03.bunrui_cd
and        br04.user_id = /*userId*/
order by   br03.bunrui_cd
,          br03.sort_seq