select      br11.gamen_id
,           br11.bunrui_cd
,           br11.setei_kbn
,           br11.role_cd
from        br11gmrl as br11
,           br04usrl as br04
where       br11.role_cd = br04.role_cd
and         br11.gamen_id = /*gamenId*/
and         br11.bunrui_cd = /*bunruiCd*/
and         br04.user_id = /*userId*/