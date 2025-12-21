select      menu_id
,           menu_name
from        br02bmnu
where       menu_id <> '00'
group by    menu_id
,           menu_name
order by    menu_id