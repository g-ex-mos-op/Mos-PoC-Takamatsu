select count(distinct mise_cd) 
from bt37urtr 
where company_cd = '00'
  and urikake_cd=/*onerCd*/'37002'
  and urikake_ym >= /*fromDt*/'200701'
  and urikake_ym <= /*toDt*/'200712'