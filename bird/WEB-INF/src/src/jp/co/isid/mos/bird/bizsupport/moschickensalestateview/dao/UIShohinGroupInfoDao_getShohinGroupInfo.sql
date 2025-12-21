select
	BM40.MENU_GROUP,
	BM40.MENU_GROUP_NM
from
    BM40CMGP as BM40
where
    BM40.CKANRI_NO = /*ckanriNo*/'200607'
order by
     BM40.CKANRI_NO