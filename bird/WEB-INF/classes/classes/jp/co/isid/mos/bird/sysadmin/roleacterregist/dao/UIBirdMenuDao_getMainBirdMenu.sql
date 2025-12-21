select      menu_id
,           menu_name
,           sub_menu_id
,           sub_menu_name
,           sort_seq
from        br02bmnu
where       menu_id = '00'
order by    sort_seq