select    bm01.company_cd
,         bm01.mise_cd
,         bm01.mise_name_kj

/*IF dataOnerInfo.equals("DOJITU")*/
,         sum(bt64.uriage_dojitu) as uriage
,         sum(bt64.kyakusu_dojitu) as kyakusu
,         sum(bt64.oner_yosan_dojitu) as yosan
,         sum(bt64.eigyo_days_dojitu) as eigyo_days
,         sum(bt64.uriage_zen_dojitu) as uriage_zen
,         sum(bt64.kyakusu_zen_dojitu) as kyakusu_zen
,         sum(bt64.eigyo_days_zen_dojitu) as eigyo_days_zen
/*END*/

/*IF dataOnerInfo.equals("DOGETU")*/
,         sum(bt64.uriage_dogetu) as uriage
,         sum(bt64.kyakusu_dogetu) as kyakusu
,         sum(bt64.oner_yosan_dogetu) as yosan
,         sum(bt64.eigyo_days_dogetu) as eigyo_days
,         sum(bt64.uriage_zen_dogetu) as uriage_zen
,         sum(bt64.kyakusu_zen_dogetu) as kyakusu_zen
,         sum(bt64.eigyo_days_zen_dogetu) as eigyo_days_zen
/*END*/

/*IF dataOnerInfo.equals("DOYO")*/
,         sum(bt64.uriage_doyo) as uriage
,         sum(bt64.kyakusu_doyo) as kyakusu
,         sum(bt64.oner_yosan_doyo) as yosan
,         sum(bt64.eigyo_days_doyo) as eigyo_days
,         sum(bt64.uriage_zen_doyo) as uriage_zen
,         sum(bt64.kyakusu_zen_doyo) as kyakusu_zen
,         sum(bt64.eigyo_days_zen_doyo) as eigyo_days_zen
/*END*/

from      bt64zgep as bt64
,         bm01tenm as bm01
where     bt64.company_cd = bm01.company_cd
and       bt64.mise_cd = bm01.mise_cd
and       bt64.company_cd = /*companyCd*/'00'

/*IF kikanInfo.equals("KIBETU")*/
and       bt64.eigyo_dt between /*kikanFrom*/'200605' and /*kikanTo*/'200606'
-- ELSE
and       bt64.eigyo_dt = /*kikanFrom*/'200605'
/*END*/

and       bm01.oner_cd =/*onerCd*/'36387'
and       bt64.oldm_flg = '0'
and       bt64.open_kbn = 1
group by  bm01.company_cd
,         bm01.mise_cd
,         bm01.mise_name_kj
order by  mise_cd
