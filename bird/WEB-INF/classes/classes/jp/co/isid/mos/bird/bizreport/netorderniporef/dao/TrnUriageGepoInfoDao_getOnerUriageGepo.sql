select    bm01.company_cd
,         bm01.mise_cd
,         bm01.mise_name_kj
,         sum(bt53.URIAGE_/*$dataOnerInfo*/'DOJITU') as uriage
,         sum(bt53.ONER_YOSAN_/*$dataOnerInfo*/'DOJITU') as yosan
,         sum(bt53.KYAKUSU_/*$dataOnerInfo*/'DOJITU') as kyakusu
,         sum(bt53.EIGYO_DAYS_/*$dataOnerInfo*/'DOJITU') as eigyo_days
,         sum(bt53.URIAGE_ZEN_/*$dataOnerInfo*/'DOJITU') as uriage_zen
,         sum(bt53.KYAKUSU_ZEN_/*$dataOnerInfo*/'DOJITU') as kyakusu_zen
,         sum(bt53.EIGYO_DAYS_ZEN_/*$dataOnerInfo*/'DOJITU') as eigyo_days_zen
,         sum(bt53.URIAGE_/*$dataOnerInfo*/'DOJITU'_NSUM) as uriage_nsum
,         sum(bt53.KYAKUSU_/*$dataOnerInfo*/'DOJITU'_NSUM) as kyakusu_nsum
,         sum(bt53.EIGYO_DAYS_/*$dataOnerInfo*/'DOJITU'_NSUM) as eigyo_days_nsum
,         max(bt53.MISECNT_/*$dataOnerInfo*/'DOJITU'_NSUM) as misecnt_kbn_nsum
,         sum(bt53.URIAGE_ZEN_/*$dataOnerInfo*/'DOJITU'_NSUM) as uriage_nsum_zen
,         sum(bt53.KYAKUSU_ZEN_/*$dataOnerInfo*/'DOJITU'_NSUM) as kyakusu_nsum_zen
,         sum(bt53.EIGYO_DAYS_ZEN_/*$dataOnerInfo*/'DOJITU'_NSUM) as eigyo_days_nsum_zen
,         max(bt53.MISECNT_ZEN_/*$dataOnerInfo*/'DOJITU'_NSUM) as misecnt_nsum_zen
,         sum(bt53.URIAGE_/*$dataOnerInfo*/'DOJITU'_NTAKE) as uriage_ntake
,         sum(bt53.KYAKUSU_/*$dataOnerInfo*/'DOJITU'_NTAKE) as kyakusu_ntake
,         sum(bt53.EIGYO_DAYS_/*$dataOnerInfo*/'DOJITU'_NTAKE) as eigyo_days_ntake
,         max(bt53.MISECNT_/*$dataOnerInfo*/'DOJITU'_NTAKE) as misecnt_kbn_ntake
,         sum(bt53.URIAGE_ZEN_/*$dataOnerInfo*/'DOJITU'_NTAKE) as uriage_ntake_zen
,         sum(bt53.KYAKUSU_ZEN_/*$dataOnerInfo*/'DOJITU'_NTAKE) as kyakusu_ntake_zen
,         sum(bt53.EIGYO_DAYS_ZEN_/*$dataOnerInfo*/'DOJITU'_NTAKE) as eigyo_days_ntake_zen
,         max(bt53.MISECNT_ZEN_/*$dataOnerInfo*/'DOJITU'_NTAKE) as misecnt_ntake_zen
,         sum(bt53.URIAGE_/*$dataOnerInfo*/'DOJITU'_NTAKU) as uriage_ntaku
,         sum(bt53.KYAKUSU_/*$dataOnerInfo*/'DOJITU'_NTAKU) as kyakusu_ntaku
,         sum(bt53.EIGYO_DAYS_/*$dataOnerInfo*/'DOJITU'_NTAKU) as eigyo_days_ntaku
,         max(bt53.MISECNT_/*$dataOnerInfo*/'DOJITU'_NTAKU) as misecnt_kbn_ntaku
,         sum(bt53.URIAGE_ZEN_/*$dataOnerInfo*/'DOJITU'_NTAKU) as uriage_ntaku_zen
,         sum(bt53.KYAKUSU_ZEN_/*$dataOnerInfo*/'DOJITU'_NTAKU) as kyakusu_ntaku_zen
,         sum(bt53.EIGYO_DAYS_ZEN_/*$dataOnerInfo*/'DOJITU'_NTAKU) as eigyo_days_ntaku_zen
,         max(bt53.MISECNT_ZEN_/*$dataOnerInfo*/'DOJITU'_NTAKU) as misecnt_ntaku_zen
from      BD53NGEP as bt53
,         bm01tenm as bm01
where     bt53.company_cd = bm01.company_cd
and       bt53.mise_cd = bm01.mise_cd
and       bt53.company_cd = /*companyCd*/'00'

/*IF kikanInfo.equals("KIBETU")*/
and       bt53.eigyo_dt between /*kikanFrom*/'200605' and /*kikanTo*/'200606'
-- ELSE
and       bt53.eigyo_dt = /*kikanFrom*/'200605'
/*END*/

and       bm01.oner_cd =/*onerCd*/'36387'
and       bt53.oldm_flg = '0'
and       bt53.open_kbn = 1
group by  bm01.company_cd
,         bm01.mise_cd
,         bm01.mise_name_kj
order by  mise_cd
