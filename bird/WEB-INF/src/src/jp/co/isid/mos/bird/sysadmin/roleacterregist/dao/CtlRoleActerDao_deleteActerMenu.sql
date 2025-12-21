delete from br05actr
where  role_cd in (select    role_cd
                   from      br03role
                   where     bunrui_cd = /*bunruiCd*/
                  )
and    concat(menu_id, sub_menu_id) in (/*firstID*/,/*secondID*/,/*thirdID*/,/*forthID*/,/*fifthID*/)