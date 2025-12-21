select     menu_id 
,          menu_name
,          sub_menu_id
,          sub_menu_name
,          sort_seq
from       br02bmnu
where      concat(menu_id,sub_menu_id) not in( 
                                               select     concat(br02.menu_id,br02.sub_menu_id) as conmenu
                                               from       br02bmnu as br02
                                               inner join br05actr as br05
                                               on         br02.menu_id = br05.menu_id
                                               and        br02.sub_menu_id = br05.sub_menu_id
                                               where      br02.menu_id != '00'
                                               group by   br02.menu_id
                                               ,          br02.sub_menu_id
                                               order by   br02.menu_id
                                               ,          br02.sub_menu_id
                                              )
and        menu_id <> '00'
order by   menu_id
,          sub_menu_id
