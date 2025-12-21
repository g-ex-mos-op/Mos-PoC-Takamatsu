select      br05.role_cd
,           br05.menu_id
,           br05.sub_menu_id
,           br05.enable_flg
,           br05.extra_flg
from        br05actr as br05
,           br03role as br03
where       br05.role_cd = br03.role_cd
and         br03.bunrui_cd = /*bunruiCd*/
and         concat(br05.menu_id,br05.sub_menu_id) in (/*firstID*/,/*secondID*/,/*thirdID*/,/*forthID*/,/*fifthID*/)
and         br05.menu_id <> '00'
order by    br05.role_cd
,           br05.menu_id
,           br05.sub_menu_id