select count(distinct mise_cd)
from bt37urtr
where company_cd = '00'
/*IF onerCd != null && onerCd != '' */
  and urikake_cd=/*onerCd*/'37002'
      /*END*/
  /*IF fromDt != null && fromDt != '' */
  and urikake_ym >= /*fromDt*/'200701'
        /*END*/
  /*IF toDt != null && toDt != '' */
  and urikake_ym <= /*toDt*/'200712'
        /*END*/