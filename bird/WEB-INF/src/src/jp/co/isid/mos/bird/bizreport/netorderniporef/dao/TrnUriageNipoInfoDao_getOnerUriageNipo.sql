select    bm01.company_cd
,         bm01.mise_cd
,         bm01.mise_name_kj

/*IF kikanInfo.equals("DAY1")*/
,         bd54.tenko_kbn
/*END*/
,         sum(bd54.uriage) as uriage
,         sum(bd54.oner_yosan) as yosan
,         sum(bd54.eigyo_days) as eigyo_days
,         sum(bd54.kyakusu) as kyakusu
,         sum(bd54.uriage_nsum) as uriage_nsum
,         sum(bd54.kyakusu_nsum) as kyakusu_nsum
,         sum(bd54.eigyo_days_nsum) as eigyo_days_nsum
,         max(bd54.misecnt_kbn_nsum) as misecnt_kbn_nsum
,         sum(bd54.uriage_ntake) as uriage_ntake
,         sum(bd54.kyakusu_ntake) as kyakusu_ntake
,         sum(bd54.eigyo_days_ntake) as eigyo_days_ntake
,         max(bd54.misecnt_kbn_ntake) as misecnt_kbn_ntake
,         sum(bd54.uriage_ntaku) as uriage_ntaku
,         sum(bd54.kyakusu_ntaku) as kyakusu_ntaku
,         sum(bd54.eigyo_days_ntaku) as eigyo_days_ntaku
,         max(bd54.misecnt_kbn_ntaku) as misecnt_kbn_ntaku
,         sum(bd54.uriage_zen_/*$dataOnerInfo*/'DOJITU') as uriage_zen
,         sum(bd54.kyakusu_zen_/*$dataOnerInfo*/'DOJITU') as kyakusu_zen
,         sum(bd54.eigyo_days_zen_/*$dataOnerInfo*/'DOJITU') as eigyo_days_zen
		  /*IF kikanInfo.equals("DAY1")*/
,         bd54.tenko_kbn_zen_/*$dataOnerInfo*/'DOJITU' as tenko_kbn_zen
          /*END*/
,         sum(bd54.uriage_zen_/*$dataOnerInfo*/'DOJITU'_nsum) as uriage_nsum_zen
,         sum(bd54.kyakusu_zen_/*$dataOnerInfo*/'DOJITU'_nsum) as kyakusu_nsum_zen
,         sum(bd54.eigyo_days_zen_/*$dataOnerInfo*/'DOJITU'_nsum) as eigyo_days_nsum_zen
,         max(bd54.misecnt_zen_/*$dataOnerInfo*/'DOJITU'_nsum) as misecnt_nsum_zen  
,         sum(bd54.uriage_zen_/*$dataOnerInfo*/'DOJITU'_ntake) as uriage_ntake_zen
,         sum(bd54.kyakusu_zen_/*$dataOnerInfo*/'DOJITU'_ntake) as kyakusu_ntake_zen
,         sum(bd54.eigyo_days_zen_/*$dataOnerInfo*/'DOJITU'_ntake) as eigyo_days_ntake_zen
,         max(bd54.misecnt_kbn_zen_/*$dataOnerInfo*/'DOJITU'_ntake) as misecnt_ntake_zen
,         sum(bd54.uriage_zen_/*$dataOnerInfo*/'DOJITU'_ntaku) as uriage_ntaku_zen
,         sum(bd54.kyakusu_zen_/*$dataOnerInfo*/'DOJITU'_ntaku) as kyakusu_ntaku_zen
,         sum(bd54.eigyo_days_zen_/*$dataOnerInfo*/'DOJITU'_ntaku) as eigyo_days_ntaku_zen
,         max(bd54.misecnt_kbn_zen_/*$dataOnerInfo*/'DOJITU'_ntaku) as misecnt_ntaku_zen 
from      BD54NNIP as bd54
,         bm01tenm as bm01
where     bd54.company_cd = bm01.company_cd
and       bd54.mise_cd = bm01.mise_cd
and       bd54.company_cd = /*companyCd*/'00'

/*IF kikanInfo.equals("DAY1")*/
and       bd54.eigyo_dt = /*kikanFrom*/'20060530'
-- ELSE
and       bd54.eigyo_dt between /*kikanFrom*/'20060530' and /*kikanTo*/'20060601'
/*END*/

and       bm01.oner_cd =/*onerCd*/'36387'
and       bd54.oldm_flg = '0'
and       bd54.open_kbn = 1
group by  bm01.company_cd
,         bm01.mise_cd
,         bm01.mise_name_kj
/*IF kikanInfo.equals("DAY1")*/
,         bd54.tenko_kbn
,         bd54.tenko_kbn_zen_/*$dataOnerInfo*/'DOJITU'
/*END*/
order by  mise_cd
