select    bm01.company_cd
,         bm01.mise_cd
,         bm01.mise_name_kj

/*IF kikanInfo.equals("DAY1")*/
,         bt60.tenko_kbn
/*END*/
,         sum(bt60.uriage) as uriage
,         sum(bt60.oner_yosan) as yosan
,         sum(bt60.eigyo_days) as eigyo_days
,         sum(bt60.kyakusu) as kyakusu

/*IF dataOnerInfo.equals("DOGETU") */
,         sum(bt60.uriage_zen_dogetu) as uriage_zen
,         sum(bt60.eigyo_days_zen_dogetu) as eigyo_days_zen
,         sum(bt60.kyakusu_zen_dogetu) as kyakusu_zen
    /*IF kikanInfo.equals("DAY1")*/
,         bt60.tenko_kbn_zen_dogetu as tenko_kbn_zen
    /*END*/
/*END*/

/*IF dataOnerInfo.equals("DOYO") */
,         sum(bt60.uriage_zen_doyo) as uriage_zen
,         sum(bt60.eigyo_days_zen_doyo) as eigyo_days_zen
,         sum(bt60.kyakusu_zen_doyo) as kyakusu_zen
    /*IF kikanInfo.equals("DAY1")*/
,         bt60.tenko_kbn_zen_doyo as tenko_kbn_zen
    /*END*/
/*END*/

/*IF dataOnerInfo.equals("DOJITU") */
,         sum(bt60.uriage_zen_dojitu) as uriage_zen
,         sum(bt60.eigyo_days_zen_dojitu) as eigyo_days_zen
,         sum(bt60.kyakusu_zen_dojitu) as kyakusu_zen
    /*IF kikanInfo.equals("DAY1")*/
,         bt60.tenko_kbn_zen_dojitu as tenko_kbn_zen
    /*END*/
/*END*/

from      bt60znip as bt60
,         bm01tenm as bm01
where     bt60.company_cd = bm01.company_cd
and       bt60.mise_cd = bm01.mise_cd
and       bt60.company_cd = /*companyCd*/'00'

/*IF kikanInfo.equals("DAY1")*/
and       bt60.eigyo_dt = /*kikanFrom*/'20060530'
-- ELSE
and       bt60.eigyo_dt between /*kikanFrom*/'20060530' and /*kikanTo*/'20060601'
/*END*/

and       bm01.oner_cd =/*onerCd*/'36387'
and       bt60.oldm_flg = '0'
and       bt60.open_kbn = 1
group by  bm01.company_cd
,         bm01.mise_cd
,         bm01.mise_name_kj
/*IF kikanInfo.equals("DAY1")*/
,         bt60.tenko_kbn

   /*IF dataOnerInfo.equals("DOYO") */
,         bt60.tenko_kbn_zen_doyo
   /*END*/

   /*IF dataOnerInfo.equals("DOJITU") */
,         bt60.tenko_kbn_zen_dojitu
   /*END*/
   /*IF dataOnerInfo.equals("DOGETU") */
,         bt60.tenko_kbn_zen_dogetu
   /*END*/

/*END*/
order by  mise_cd
