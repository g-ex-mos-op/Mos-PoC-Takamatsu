select count(distinct bt60.mise_cd) as TENPO_COUNT
from 
  bt60znip bt60,
  bm65cpms bm65
where bt60.company_cd = bm65.company_cd 
and   bt60.mise_cd = bm65.mise_cd
and   bm65.camp_id = /*campId*/'200822'
and   bt60.eigyo_dt between /*fromDt*/'20080418' and /*toDt*/'20080521'
and   bt60.open_kbn = 1
and   bt60.oldm_flg <> '1'