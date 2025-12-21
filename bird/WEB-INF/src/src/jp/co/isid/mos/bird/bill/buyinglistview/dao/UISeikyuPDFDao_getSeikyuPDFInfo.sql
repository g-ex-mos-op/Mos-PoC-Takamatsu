select
    substr(BS02.SEIKYUSHO_ID, 1, 8) as SEIKYU_DT,
    BS02.SEIKYUSHO_ID,
    BS02.URIKAKE_CD,
    TM11.ONER_NAME_KJ,
    BM33.ONER_CD,
    concat(BS02.URIKAKE_CD, BS02.SEIKYUSHO_ID) as PDF_NAME,
    HAKKO_DT,
    SEIKYU_ZEN
from
    BS02SEKR as BS02,
    BM33ONUR as BM33,
    TM11ONER as TM11
where

/*IF onerCd != null && onerCd != '' */
    BM33.ONER_CD = /*onerCd*/'36478' and
    /*END*/
    BS02.URIKAKE_CD = BM33.URIKAKE_CD and
    BM33.URIKAKE_CD = TM11.ONER_CD and
    BS02.COMPANY_CD = '00'
/*IF frDate != null && frDate != '' */
    and BS02.HAKKO_DT >= /*frDate*/'20060101'
    /*END*/
/*IF toDate != null && toDate != '' */
    and BS02.HAKKO_DT <= /*toDate*/'20220199'
    /*END*/
/*IF kinGaku != null && kinGaku != ''*/
    and BS02.SEIKYU_KON >= /*kinGaku*/'0'
     /*END*/
/*IF kinGakuTo != null && kinGakuTo != ''*/
    and BS02.SEIKYU_KON <= /*kinGakuTo*/'0'
     /*END*/
    and substr(HAKKO_DT,1,4) + 7 >= (values year(current timestamp))
    and ( NYUKIN_GAKU != 0 or KURI_ZAN != 0 or
     BS02.KAZEI_GAKU != 0 or BS02.SHO_TAX !=0 or BS02.HAND_TAX != 0 or
     BS02.HIZEI_GAKU != 0 or BS02.SEIKYU_KON != 0 or BS02.NYUKIN_RUISEKI != 0)
order by
    BS02.SEIKYUSHO_ID desc,
    BS02.URIKAKE_CD desc