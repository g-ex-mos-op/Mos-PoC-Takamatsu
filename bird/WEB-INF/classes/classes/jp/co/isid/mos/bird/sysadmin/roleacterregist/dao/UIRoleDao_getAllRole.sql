select      br03.role_cd
,           br03.role_name
,           br03.bunrui_cd
,           br03.sort_seq
,           bc03.bunrui_name
,           bc03.sort_seq as bunrui_sort_seq
from        br03role as br03
,           bc03rlbr as bc03
where       br03.bunrui_cd = bc03.bunrui_cd
and         br03.bunrui_cd = /*bunruiCd*/
order by    br03.bunrui_cd
,           bc03.sort_seq