select 
  bd20.eigyo_ym as eigyo_dt,
/*IF taishoJoken.equals("MISE")*/
  max(rtrim(bm01.mise_name_kj)) as mise_name_kj,
/*END*/
  count(distinct bd20.mise_cd) as mise_cnt,
  sum(bd20.eat_ken) as eat_ken,
  sum(bd20.eat_kin) as eat_kin,
  sum(bd20.take_ken) as take_ken,
  sum(bd20.take_kin) as take_kin,
  sum(bd20.tel_ken) as tel_ken,
  sum(bd20.tel_kin) as tel_kin,
  sum(bd20.drive_ken) as drive_ken,
  sum(bd20.drive_kin) as drive_kin,
  sum(bd20.other_ken_1) as takuhai_ken,
  sum(bd20.other_kin_1) as takuhai_kin,
  sum(bd20.other_ken_2) as gaihan_ken,
  sum(bd20.other_kin_2) as gaihan_kin,
  sum(bd20.other_ken_3) as syubetsu07_ken,
  sum(bd20.other_kin_3) as syubetsu07_kin,
  sum(bd20.other_ken_4) as syubetsu08_ken,
  sum(bd20.other_kin_4) as syubetsu08_kin,
  sum(bd20.other_ken_5) as syubetsu09_ken,
  sum(bd20.other_kin_5) as syubetsu09_kin,
  sum(bd20.other_ken_6) as syubetsu10_ken,
  sum(bd20.other_kin_6) as syubetsu10_kin,
  sum(bd20.other_ken_7) as nettake_ken,
  sum(bd20.other_kin_7) as nettake_kin,
  sum(bd20.other_ken_8) as nettakuhai_ken,
  sum(bd20.other_kin_8) as nettakuhai_kin,
  sum(bd20.other_ken_9) as syubetsu13_ken,
  sum(bd20.other_kin_9) as syubetsu13_kin,
  sum(bd20.other_ken_10) as syubetsu14_ken,
  sum(bd20.other_kin_10) as syubetsu14_kin,
  sum(bd20.other_ken_11) as syubetsu15_ken,
  sum(bd20.other_kin_11) as syubetsu15_kin,
  sum(bd20.other_ken_2+
      bd20.other_ken_3+
      bd20.other_ken_4+
      bd20.other_ken_5+
      bd20.other_ken_6+
      bd20.other_ken_7+
      bd20.other_ken_8+
      bd20.other_ken_9+
      bd20.other_ken_10+
      bd20.other_ken_11) as other_ken,
  sum(bd20.other_kin_2+
      bd20.other_kin_3+
      bd20.other_kin_4+
      bd20.other_kin_5+
      bd20.other_kin_6+
      bd20.other_kin_7+
      bd20.other_kin_8+
      bd20.other_kin_9+
      bd20.other_kin_10+
      bd20.other_kin_11) as other_kin,
/*IF zenDataShu.equals("DOYO")*/
  case when sum(bd20.eat_ken_zen_doyo) is null then 0 else sum(bd20.eat_ken_zen_doyo) end as eat_ken_zen,
  case when sum(bd20.eat_kin_zen_doyo) is null then 0 else sum(bd20.eat_kin_zen_doyo) end as eat_kin_zen,
  case when sum(bd20.take_ken_zen_doyo) is null then 0 else sum(bd20.take_ken_zen_doyo) end as take_ken_zen,
  case when sum(bd20.take_kin_zen_doyo) is null then 0 else sum(bd20.take_kin_zen_doyo) end as take_kin_zen,
  case when sum(bd20.tel_ken_zen_doyo) is null then 0 else sum(bd20.tel_ken_zen_doyo) end as tel_ken_zen,
  case when sum(bd20.tel_kin_zen_doyo) is null then 0 else sum(bd20.tel_kin_zen_doyo) end as tel_kin_zen,
  case when sum(bd20.drive_ken_zen_doyo) is null then 0 else sum(bd20.drive_ken_zen_doyo) end as drive_ken_zen,
  case when sum(bd20.drive_kin_zen_doyo) is null then 0 else sum(bd20.drive_kin_zen_doyo) end as drive_kin_zen,
  case when sum(bd20.other_ken_1_zen_doyo) is null then 0 else sum(bd20.other_ken_1_zen_doyo) end as takuhai_ken_zen,
  case when sum(bd20.other_kin_1_zen_doyo) is null then 0 else sum(bd20.other_kin_1_zen_doyo) end as takuhai_kin_zen,
  case when sum(bd20.other_ken_2_zen_doyo) is null then 0 else sum(bd20.other_ken_2_zen_doyo) end as gaihan_ken_zen,
  case when sum(bd20.other_kin_2_zen_doyo) is null then 0 else sum(bd20.other_kin_2_zen_doyo) end as gaihan_kin_zen,
  case when sum(bd20.other_ken_3_zen_doyo) is null then 0 else sum(bd20.other_ken_3_zen_doyo) end as syubetsu07_ken_zen,
  case when sum(bd20.other_kin_3_zen_doyo) is null then 0 else sum(bd20.other_kin_3_zen_doyo) end as syubetsu07_kin_zen,
  case when sum(bd20.other_ken_4_zen_doyo) is null then 0 else sum(bd20.other_ken_4_zen_doyo) end as syubetsu08_ken_zen,
  case when sum(bd20.other_kin_4_zen_doyo) is null then 0 else sum(bd20.other_kin_4_zen_doyo) end as syubetsu08_kin_zen,
  case when sum(bd20.other_ken_5_zen_doyo) is null then 0 else sum(bd20.other_ken_5_zen_doyo) end as syubetsu09_ken_zen,
  case when sum(bd20.other_kin_5_zen_doyo) is null then 0 else sum(bd20.other_kin_5_zen_doyo) end as syubetsu09_kin_zen,
  case when sum(bd20.other_ken_6_zen_doyo) is null then 0 else sum(bd20.other_ken_6_zen_doyo) end as syubetsu10_ken_zen,
  case when sum(bd20.other_kin_6_zen_doyo) is null then 0 else sum(bd20.other_kin_6_zen_doyo) end as syubetsu10_kin_zen,
  case when sum(bd20.other_ken_7_zen_doyo) is null then 0 else sum(bd20.other_ken_7_zen_doyo) end as nettake_ken_zen,
  case when sum(bd20.other_kin_7_zen_doyo) is null then 0 else sum(bd20.other_kin_7_zen_doyo) end as nettake_kin_zen,
  case when sum(bd20.other_ken_8_zen_doyo) is null then 0 else sum(bd20.other_ken_8_zen_doyo) end as nettakuhai_ken_zen,
  case when sum(bd20.other_kin_8_zen_doyo) is null then 0 else sum(bd20.other_kin_8_zen_doyo) end as nettakuhai_kin_zen,
  case when sum(bd20.other_ken_9_zen_doyo) is null then 0 else sum(bd20.other_ken_9_zen_doyo) end as syubetsu13_ken_zen,
  case when sum(bd20.other_kin_9_zen_doyo) is null then 0 else sum(bd20.other_kin_9_zen_doyo) end as syubetsu13_kin_zen,
  case when sum(bd20.other_ken_10_zen_doyo) is null then 0 else sum(bd20.other_ken_10_zen_doyo) end as syubetsu14_ken_zen,
  case when sum(bd20.other_kin_10_zen_doyo) is null then 0 else sum(bd20.other_kin_10_zen_doyo) end as syubetsu14_kin_zen,
  case when sum(bd20.other_ken_11_zen_doyo) is null then 0 else sum(bd20.other_ken_11_zen_doyo) end as syubetsu15_ken_zen,
  case when sum(bd20.other_kin_11_zen_doyo) is null then 0 else sum(bd20.other_kin_11_zen_doyo) end as syubetsu15_kin_zen,
  case when sum(bd20.other_ken_2_zen_doyo+
      bd20.other_ken_3_zen_doyo+
      bd20.other_ken_4_zen_doyo+
      bd20.other_ken_5_zen_doyo+
      bd20.other_ken_6_zen_doyo+
      bd20.other_ken_7_zen_doyo+
      bd20.other_ken_8_zen_doyo+
      bd20.other_ken_9_zen_doyo+
      bd20.other_ken_10_zen_doyo+
      bd20.other_ken_11_zen_doyo) is null then 0 
      else sum(bd20.other_ken_2_zen_doyo+
      bd20.other_ken_3_zen_doyo+
      bd20.other_ken_4_zen_doyo+
      bd20.other_ken_5_zen_doyo+
      bd20.other_ken_6_zen_doyo+
      bd20.other_ken_7_zen_doyo+
      bd20.other_ken_8_zen_doyo+
      bd20.other_ken_9_zen_doyo+
      bd20.other_ken_10_zen_doyo+
      bd20.other_ken_11_zen_doyo) end as other_ken_zen,
  case when sum(bd20.other_kin_2_zen_doyo+
      bd20.other_kin_3_zen_doyo+
      bd20.other_kin_4_zen_doyo+
      bd20.other_kin_5_zen_doyo+
      bd20.other_kin_6_zen_doyo+
      bd20.other_kin_7_zen_doyo+
      bd20.other_kin_8_zen_doyo+
      bd20.other_kin_9_zen_doyo+
      bd20.other_kin_10_zen_doyo+
      bd20.other_kin_11_zen_doyo) is null then 0 
      else sum(bd20.other_kin_2_zen_doyo+
      bd20.other_kin_3_zen_doyo+
      bd20.other_kin_4_zen_doyo+
      bd20.other_kin_5_zen_doyo+
      bd20.other_kin_6_zen_doyo+
      bd20.other_kin_7_zen_doyo+
      bd20.other_kin_8_zen_doyo+
      bd20.other_kin_9_zen_doyo+
      bd20.other_kin_10_zen_doyo+
      bd20.other_kin_11_zen_doyo) end as other_kin_zen
/*END*/
/*IF zenDataShu.equals("DOJITU")*/
  case when sum(bd20.eat_ken_zen_dojitu) is null then 0 else sum(bd20.eat_ken_zen_dojitu) end as eat_ken_zen,
  case when sum(bd20.eat_kin_zen_dojitu) is null then 0 else sum(bd20.eat_kin_zen_dojitu) end as eat_kin_zen,
  case when sum(bd20.take_ken_zen_dojitu) is null then 0 else sum(bd20.take_ken_zen_dojitu) end as take_ken_zen,
  case when sum(bd20.take_kin_zen_dojitu) is null then 0 else sum(bd20.take_kin_zen_dojitu) end as take_kin_zen,
  case when sum(bd20.tel_ken_zen_dojitu) is null then 0 else sum(bd20.tel_ken_zen_dojitu) end as tel_ken_zen,
  case when sum(bd20.tel_kin_zen_dojitu) is null then 0 else sum(bd20.tel_kin_zen_dojitu) end as tel_kin_zen,
  case when sum(bd20.drive_ken_zen_dojitu) is null then 0 else sum(bd20.drive_ken_zen_dojitu) end as drive_ken_zen,
  case when sum(bd20.drive_kin_zen_dojitu) is null then 0 else sum(bd20.drive_kin_zen_dojitu) end as drive_kin_zen,
  case when sum(bd20.other_ken_1_zen_dojitu) is null then 0 else sum(bd20.other_ken_1_zen_dojitu) end as takuhai_ken_zen,
  case when sum(bd20.other_kin_1_zen_dojitu) is null then 0 else sum(bd20.other_kin_1_zen_dojitu) end as takuhai_kin_zen,
  case when sum(bd20.other_ken_2_zen_dojitu) is null then 0 else sum(bd20.other_ken_2_zen_dojitu) end as gaihan_ken_zen,
  case when sum(bd20.other_kin_2_zen_dojitu) is null then 0 else sum(bd20.other_kin_2_zen_dojitu) end as gaihan_kin_zen,
  case when sum(bd20.other_ken_3_zen_dojitu) is null then 0 else sum(bd20.other_ken_3_zen_dojitu) end as syubetsu07_ken_zen,
  case when sum(bd20.other_kin_3_zen_dojitu) is null then 0 else sum(bd20.other_kin_3_zen_dojitu) end as syubetsu07_kin_zen,
  case when sum(bd20.other_ken_4_zen_dojitu) is null then 0 else sum(bd20.other_ken_4_zen_dojitu) end as syubetsu08_ken_zen,
  case when sum(bd20.other_kin_4_zen_dojitu) is null then 0 else sum(bd20.other_kin_4_zen_dojitu) end as syubetsu08_kin_zen,
  case when sum(bd20.other_ken_5_zen_dojitu) is null then 0 else sum(bd20.other_ken_5_zen_dojitu) end as syubetsu09_ken_zen,
  case when sum(bd20.other_kin_5_zen_dojitu) is null then 0 else sum(bd20.other_kin_5_zen_dojitu) end as syubetsu09_kin_zen,
  case when sum(bd20.other_ken_6_zen_dojitu) is null then 0 else sum(bd20.other_ken_6_zen_dojitu) end as syubetsu10_ken_zen,
  case when sum(bd20.other_kin_6_zen_dojitu) is null then 0 else sum(bd20.other_kin_6_zen_dojitu) end as syubetsu10_kin_zen,
  case when sum(bd20.other_ken_7_zen_dojitu) is null then 0 else sum(bd20.other_ken_7_zen_dojitu) end as nettake_ken_zen,
  case when sum(bd20.other_kin_7_zen_dojitu) is null then 0 else sum(bd20.other_kin_7_zen_dojitu) end as nettake_kin_zen,
  case when sum(bd20.other_ken_8_zen_dojitu) is null then 0 else sum(bd20.other_ken_8_zen_dojitu) end as nettakuhai_ken_zen,
  case when sum(bd20.other_kin_8_zen_dojitu) is null then 0 else sum(bd20.other_kin_8_zen_dojitu) end as nettakuhai_kin_zen,
  case when sum(bd20.other_ken_9_zen_dojitu) is null then 0 else sum(bd20.other_ken_9_zen_dojitu) end as syubetsu13_ken_zen,
  case when sum(bd20.other_kin_9_zen_dojitu) is null then 0 else sum(bd20.other_kin_9_zen_dojitu) end as syubetsu13_kin_zen,
  case when sum(bd20.other_ken_10_zen_dojitu) is null then 0 else sum(bd20.other_ken_10_zen_dojitu) end as syubetsu14_ken_zen,
  case when sum(bd20.other_kin_10_zen_dojitu) is null then 0 else sum(bd20.other_kin_10_zen_dojitu) end as syubetsu14_kin_zen,
  case when sum(bd20.other_ken_11_zen_dojitu) is null then 0 else sum(bd20.other_ken_11_zen_dojitu) end as syubetsu15_ken_zen,
  case when sum(bd20.other_kin_11_zen_dojitu) is null then 0 else sum(bd20.other_kin_11_zen_dojitu) end as syubetsu15_kin_zen,
  case when sum(bd20.other_ken_2_zen_dojitu+
      bd20.other_ken_3_zen_dojitu+
      bd20.other_ken_4_zen_dojitu+
      bd20.other_ken_5_zen_dojitu+
      bd20.other_ken_6_zen_dojitu+
      bd20.other_ken_7_zen_dojitu+
      bd20.other_ken_8_zen_dojitu+
      bd20.other_ken_9_zen_dojitu+
      bd20.other_ken_10_zen_dojitu+
      bd20.other_ken_11_zen_dojitu) is null then 0 
  else sum(bd20.other_ken_2_zen_dojitu+
      bd20.other_ken_3_zen_dojitu+
      bd20.other_ken_4_zen_dojitu+
      bd20.other_ken_5_zen_dojitu+
      bd20.other_ken_6_zen_dojitu+
      bd20.other_ken_7_zen_dojitu+
      bd20.other_ken_8_zen_dojitu+
      bd20.other_ken_9_zen_dojitu+
      bd20.other_ken_10_zen_dojitu+
      bd20.other_ken_11_zen_dojitu) end as other_ken_zen,
  case when sum(bd20.other_kin_2_zen_dojitu+
      bd20.other_kin_3_zen_dojitu+
      bd20.other_kin_4_zen_dojitu+
      bd20.other_kin_5_zen_dojitu+
      bd20.other_kin_6_zen_dojitu+
      bd20.other_kin_7_zen_dojitu+
      bd20.other_kin_8_zen_dojitu+
      bd20.other_kin_9_zen_dojitu+
      bd20.other_kin_10_zen_dojitu+
      bd20.other_kin_11_zen_dojitu) is null then 0 
  else sum(bd20.other_kin_2_zen_dojitu+
      bd20.other_kin_3_zen_dojitu+
      bd20.other_kin_4_zen_dojitu+
      bd20.other_kin_5_zen_dojitu+
      bd20.other_kin_6_zen_dojitu+
      bd20.other_kin_7_zen_dojitu+
      bd20.other_kin_8_zen_dojitu+
      bd20.other_kin_9_zen_dojitu+
      bd20.other_kin_10_zen_dojitu+
      bd20.other_kin_11_zen_dojitu) end as other_kin_zen
/*END*/

from 
  bd20shum bd20
/*IF !taishoJoken.equals("ALL") || (userTypeCd.equals("01") && limitFlg)*/  
  ,bm01tenm bm01
/*END*/  
  /*IF "1".equals(tenpoShu)*/
  ,(select distinct eigyo_ym,
          mise_cd
   from   bn01dten bn01
   where  eigyo_ym between /*targetDtFrom*/'200706' and /*targetDtTo*/'200806'
   and    company_cd = /*companyCd*/'00'
   and    kbn1 = '1'
  ) bn01
  /*END*/

where
    bd20.company_cd = /*companyCd*/'00'
and bd20.eigyo_ym between /*targetDtFrom*/'200706' and /*targetDtTo*/'200806'
/*IF !taishoJoken.equals("ALL") || (userTypeCd.equals("01") && limitFlg)*/  
and bd20.company_cd = bm01.company_cd 
and bd20.mise_cd = bm01.mise_cd
/*END*/
/*IF taishoJoken.equals("MISE")*/
and bd20.mise_cd = /*miseCd*/'01776'
/*END*/
/*IF taishoJoken.equals("SIBU")*/
and bm01.sibu_cd = /*sibuCd*/'031'
/*END*/
/*IF "1".equals(tenpoShu)*/
and bd20.eigyo_ym = bn01.eigyo_ym
and bd20.mise_cd = bn01.mise_cd
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
and     bd20.mise_cd in (select mise_cd 
                 from bm01tenm 
                 where company_cd = /*companyCd*/'00' 
                 and   oner_cd in (select oner_cd from bm06uonr 
                                   where  USER_ID = /*userId*/'99990002'   
                                   AND   COMPANY_CD = bm01tenm.company_cd)
                )
/*END*/
/*IF userTypeCd == "03" */
and     bd20.mise_cd in (select mise_cd 
                 from bm07uten 
                 where USER_ID = /*userId*/'99990003' and COMPANY_CD = /*companyCd*/'00'
                )
/*END*/


group by 
  (bd20.eigyo_ym)

order by
  bd20.eigyo_ym