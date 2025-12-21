delete from br05actr
where  concat(menu_id, sub_menu_id) in (select    concat(menu_id, sub_menu_id)
                                        from      br02bmnu
                                        where     menu_id = /*menuId*/
                                       )
and    role_cd in (/*firstRoleId*/,/*secondRoleId*/,/*thirdRoleId*/,/*forthRoleId*/,/*fifthRoleId*/)