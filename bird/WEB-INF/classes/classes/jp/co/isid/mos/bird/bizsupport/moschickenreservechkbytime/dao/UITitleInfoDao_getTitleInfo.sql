select
	BM39.CKANRI_NO,
	BM39.TITLE,
	BM39.TARGET_FROM,
	BM39.TARGET_TO
from
    BM39CPRD as BM39
where
    BM39.TARGET_FROM <= /*sysDate*/'20060401' and
    BM39.TARGET_TO >= /*sysDate*/'20060401'
order by
     BM39.TARGET_FROM desc, 
     BM39.CKANRI_NO desc