select     br03.role_cd
,          br03.role_name
,          br03.bunrui_cd
,          br03.description
,          br03.sort_seq
,          bc03.bunrui_name
,          bc03.sort_seq
from       br03role as br03
,          br10rolm as br10
,          bm03uscp as bm03
,          bc03rlbr as bc03
where      br03.role_cd= br10.role_cd 
and        br10.r_company_cd = bm03.r_company_cd  
and        br03.bunrui_cd = bc03.bunrui_cd 
and        bm03.user_id = /*userId*/
and        br10.usertype_cd = /*userTypeCd*/
order by   br03.bunrui_cd
,          br03.sort_seq