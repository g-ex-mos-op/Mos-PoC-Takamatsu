select 
  case when bt63.eigyo_dt is null
       then 'åéçáåv' else bt63.eigyo_dt end as eigyo_dt,
/*IF taishoJoken.equals("MISE")*/
  max(rtrim(bm01.mise_name_kj)) as mise_name_kj,
/*END*/
  count(distinct bt63.mise_cd) as mise_cnt,
  sum(bt63.eat_ken) as eat_ken,
  sum(bt63.eat_kin) as eat_kin,
  sum(bt63.take_ken) as take_ken,
  sum(bt63.take_kin) as take_kin,
  sum(bt63.tel_ken) as tel_ken,
  sum(bt63.tel_kin) as tel_kin,
  sum(bt63.drive_ken) as drive_ken,
  sum(bt63.drive_kin) as drive_kin,
  sum(bt63.other_ken_1) as takuhai_ken,
  sum(bt63.other_kin_1) as takuhai_kin,
  sum(bt63.other_ken_2) as gaihan_ken,
  sum(bt63.other_kin_2) as gaihan_kin,
  sum(bt63.other_ken_3) as syubetsu07_ken,
  sum(bt63.other_kin_3) as syubetsu07_kin,
  sum(bt63.other_ken_4) as syubetsu08_ken,
  sum(bt63.other_kin_4) as syubetsu08_kin,
  sum(bt63.other_ken_5) as syubetsu09_ken,
  sum(bt63.other_kin_5) as syubetsu09_kin,
  sum(bt63.other_ken_6) as syubetsu10_ken,
  sum(bt63.other_kin_6) as syubetsu10_kin,
  sum(bt63.other_ken_7) as nettake_ken,
  sum(bt63.other_kin_7) as nettake_kin,
  sum(bt63.other_ken_8) as nettakuhai_ken,
  sum(bt63.other_kin_8) as nettakuhai_kin,
  sum(bt63.other_ken_9) as syubetsu13_ken,
  sum(bt63.other_kin_9) as syubetsu13_kin,
  sum(bt63.other_ken_10) as syubetsu14_ken,
  sum(bt63.other_kin_10) as syubetsu14_kin,
  sum(bt63.other_ken_11) as syubetsu15_ken,
  sum(bt63.other_kin_11) as syubetsu15_kin,
  sum(bt63.other_ken_2+
      bt63.other_ken_3+
      bt63.other_ken_4+
      bt63.other_ken_5+
      bt63.other_ken_6+
      bt63.other_ken_7+
      bt63.other_ken_8+
      bt63.other_ken_9+
      bt63.other_ken_10+
      bt63.other_ken_11) as other_ken,
  sum(bt63.other_kin_2+
      bt63.other_kin_3+
      bt63.other_kin_4+
      bt63.other_kin_5+
      bt63.other_kin_6+
      bt63.other_kin_7+
      bt63.other_kin_8+
      bt63.other_kin_9+
      bt63.other_kin_10+
      bt63.other_kin_11) as other_kin,
  case when sum(bt63zen.eat_ken) is null then 0 else sum(bt63zen.eat_ken) end as eat_ken_zen,
  case when sum(bt63zen.eat_kin) is null then 0 else sum(bt63zen.eat_kin) end as eat_kin_zen,
  case when sum(bt63zen.take_ken) is null then 0 else sum(bt63zen.take_ken) end as take_ken_zen,
  case when sum(bt63zen.take_kin) is null then 0 else sum(bt63zen.take_kin) end as take_kin_zen,
  case when sum(bt63zen.tel_ken) is null then 0 else sum(bt63zen.tel_ken) end as tel_ken_zen,
  case when sum(bt63zen.tel_kin) is null then 0 else sum(bt63zen.tel_kin) end as tel_kin_zen,
  case when sum(bt63zen.drive_ken) is null then 0 else sum(bt63zen.drive_ken) end as drive_ken_zen,
  case when sum(bt63zen.drive_kin) is null then 0 else sum(bt63zen.drive_kin) end as drive_kin_zen,
  case when sum(bt63zen.other_ken_1) is null then 0 else sum(bt63zen.other_ken_1) end as takuhai_ken_zen,
  case when sum(bt63zen.other_kin_1) is null then 0 else sum(bt63zen.other_kin_1) end as takuhai_kin_zen,
  case when sum(bt63zen.other_ken_2) is null then 0 else sum(bt63zen.other_ken_2) end as gaihan_ken_zen,
  case when sum(bt63zen.other_kin_2) is null then 0 else sum(bt63zen.other_kin_2) end as gaihan_kin_zen,
  case when sum(bt63zen.other_ken_3) is null then 0 else sum(bt63zen.other_ken_3) end as syubetsu07_ken_zen,
  case when sum(bt63zen.other_kin_3) is null then 0 else sum(bt63zen.other_kin_3) end as syubetsu07_kin_zen,
  case when sum(bt63zen.other_ken_4) is null then 0 else sum(bt63zen.other_ken_4) end as syubetsu08_ken_zen,
  case when sum(bt63zen.other_kin_4) is null then 0 else sum(bt63zen.other_kin_4) end as syubetsu08_kin_zen,
  case when sum(bt63zen.other_ken_5) is null then 0 else sum(bt63zen.other_ken_5) end as syubetsu09_ken_zen,
  case when sum(bt63zen.other_kin_5) is null then 0 else sum(bt63zen.other_kin_5) end as syubetsu09_kin_zen,
  case when sum(bt63zen.other_ken_6) is null then 0 else sum(bt63zen.other_ken_6) end as syubetsu10_ken_zen,
  case when sum(bt63zen.other_kin_6) is null then 0 else sum(bt63zen.other_kin_6) end as syubetsu10_kin_zen,
  case when sum(bt63zen.other_ken_7) is null then 0 else sum(bt63zen.other_ken_7) end as nettake_ken_zen,
  case when sum(bt63zen.other_kin_7) is null then 0 else sum(bt63zen.other_kin_7) end as nettake_kin_zen,
  case when sum(bt63zen.other_ken_8) is null then 0 else sum(bt63zen.other_ken_8) end as nettakuhai_ken_zen,
  case when sum(bt63zen.other_kin_8) is null then 0 else sum(bt63zen.other_kin_8) end as nettakuhai_kin_zen,
  case when sum(bt63zen.other_ken_9) is null then 0 else sum(bt63zen.other_ken_9) end as syubetsu13_ken_zen,
  case when sum(bt63zen.other_kin_9) is null then 0 else sum(bt63zen.other_kin_9) end as syubetsu13_kin_zen,
  case when sum(bt63zen.other_ken_10) is null then 0 else sum(bt63zen.other_ken_10) end as syubetsu14_ken_zen,
  case when sum(bt63zen.other_kin_10) is null then 0 else sum(bt63zen.other_kin_10) end as syubetsu14_kin_zen,
  case when sum(bt63zen.other_ken_11) is null then 0 else sum(bt63zen.other_ken_11) end as syubetsu15_ken_zen,
  case when sum(bt63zen.other_kin_11) is null then 0 else sum(bt63zen.other_kin_11) end as syubetsu15_kin_zen,
  case when sum(bt63zen.other_ken_2+
      bt63zen.other_ken_3+
      bt63zen.other_ken_4+
      bt63zen.other_ken_5+
      bt63zen.other_ken_6+
      bt63zen.other_ken_7+
      bt63zen.other_ken_8+
      bt63zen.other_ken_9+
      bt63zen.other_ken_10+
      bt63zen.other_ken_11) is null then 0 
  else sum(bt63zen.other_ken_2+
      bt63zen.other_ken_3+
      bt63zen.other_ken_4+
      bt63zen.other_ken_5+
      bt63zen.other_ken_6+
      bt63zen.other_ken_7+
      bt63zen.other_ken_8+
      bt63zen.other_ken_9+
      bt63zen.other_ken_10+
      bt63zen.other_ken_11) end as other_ken_zen,
  case when sum(bt63zen.other_kin_2+
      bt63zen.other_kin_3+
      bt63zen.other_kin_4+
      bt63zen.other_kin_5+
      bt63zen.other_kin_6+
      bt63zen.other_kin_7+
      bt63zen.other_kin_8+
      bt63zen.other_kin_9+
      bt63zen.other_kin_10+
      bt63zen.other_kin_11) is null then 0 
  else sum(bt63zen.other_kin_2+
      bt63zen.other_kin_3+
      bt63zen.other_kin_4+
      bt63zen.other_kin_5+
      bt63zen.other_kin_6+
      bt63zen.other_kin_7+
      bt63zen.other_kin_8+
      bt63zen.other_kin_9+
      bt63zen.other_kin_10+
      bt63zen.other_kin_11) end as other_kin_zen

from 
  bt63snip bt63,
  bm01tenm bm01,
  bm45zday bm45
  left join bt63snip bt63zen 
        on (bm45.company_cd = bt63zen.company_cd and
        /*IF zenDataShu.equals("DOYO")*/
            bm45.zen_doyo_mise = bt63zen.mise_cd and
            bm45.zen_doyo_dt = bt63zen.eigyo_dt and 
            bm45.op_kbn_zen_doyo = 1
        /*END*/
        /*IF zenDataShu.equals("DOJITU")*/
            bm45.zen_dojitu_mise = bt63zen.mise_cd and
            bm45.zen_dojitu_dt = bt63zen.eigyo_dt and
            bm45.op_kbn_zen_dojitu = 1
        /*END*/
        )
  /*IF "1".equals(tenpoShu)*/
  ,bn01dten bn01
  /*END*/

where
    bt63.company_cd = bm01.company_cd 
and bt63.mise_cd = bm01.mise_cd
and bt63.eigyo_dt = bm45.eigyo_dt
and bt63.company_cd = bm45.company_cd
and bt63.mise_cd = bm45.mise_cd
and bt63.company_cd = /*companyCd*/'00'
and bt63.eigyo_dt between /*targetDtFrom*/'20080601' and /*targetDtTo*/'20080631'
/*IF taishoJoken.equals("MISE")*/
and bt63.mise_cd = /*miseCd*/'01776'
/*END*/
/*IF taishoJoken.equals("SIBU")*/
and bm01.sibu_cd = /*sibuCd*/'031'
/*END*/
and bm45.open_kbn = 1
and bm45.oldm_flg = '0'
/*IF "1".equals(tenpoShu)*/
and bt63.company_cd = bn01.company_cd
and bt63.eigyo_dt = bn01.eigyo_dt
and bt63.mise_cd = bn01.mise_cd
and bn01.kbn1 = '1'
/*END*/
/*IF userTypeCd.equals("01") && limitFlg*/
AND    BM01.SIBU_CD IN (
   SELECT   BM51.SIBU_CD
   FROM     BM51SVSB BM51
   WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
   AND    BM51.SV_CD      = /*userId*/'99990005'
   GROUP BY BM51.SIBU_CD
)
/*END*/
/*IF userTypeCd == "02" */
and     bt63.mise_cd in (select mise_cd 
                 from bm01tenm 
                 where company_cd = /*companyCd*/'00' 
                 and   oner_cd in (select oner_cd from bm06uonr 
                                   where  USER_ID = /*userId*/'99990002'   
                                   AND   COMPANY_CD = bm01tenm.company_cd)
                )
/*END*/
/*IF userTypeCd == "03" */
and     bt63.mise_cd in (select mise_cd 
                 from bm07uten 
                 where USER_ID = /*userId*/'99990003' and COMPANY_CD = /*companyCd*/'00'
                )
/*END*/


group by rollup
  (bt63.eigyo_dt)

order by
  bt63.eigyo_dt