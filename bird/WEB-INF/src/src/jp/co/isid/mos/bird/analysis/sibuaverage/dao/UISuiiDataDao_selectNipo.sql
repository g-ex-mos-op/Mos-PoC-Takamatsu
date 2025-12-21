select 
  main.eigyo_dt as eigyo_dt,
  main.uriage as uriage,
  main.kyakusu as kyakusu,
  main.uriage_zen as uriage_zen,
  main.kyakusu_zen as kyakusu_zen,
  sibu.uriage as sibu_uriage,
  sibu.kyakusu as sibu_kyakusu,
  sibu.uriage_zen as sibu_uriage_zen,
  sibu.kyakusu_zen as sibu_kyakusu_zen
from (
select
  BT60.eigyo_dt as eigyo_dt,
  decimal(avg(DOUBLE(BT60.uriage)) +0.5 ,11,0) as uriage,
  decimal(avg(DOUBLE(BT60.kyakusu)) +0.5 ,11,0) as kyakusu,
  decimal(avg(DOUBLE(BT60.uriage_zen_/*$zenDataShu*/'DOYO')) +0.5 ,11,0) as uriage_zen,
  decimal(avg(DOUBLE(BT60.kyakusu_zen_/*$zenDataShu*/'DOYO')) +0.5 ,11,0) as kyakusu_zen,
1 as dummy
from 
  BT60znip BT60
/*IF userTypeCd.equals("01") && limitFlg*/
 ,bm01tenm bm01
/*END*/
where 
  BT60.company_cd = /*companyCd*/'00' and
  BT60.eigyo_dt between /*taishoKikanFrom*/'20080601' and /*taishoKikanTo*/'20080631' and
  BT60.open_kbn = 1
/*IF taishoJoken.equals("MISE")*/
  and BT60.mise_cd = /*hyojiTaisho*/'01776'
/*END*/  
/*IF userTypeCd.equals("01") && taishoJoken.equals("ONER")*/
  and BT60.mise_cd in (select mise_cd 
                   from bm01tenm 
                   where company_cd = /*companyCd*/'00' 
                     and oner_cd = /*hyojiTaisho*/'36478' 
                     and /*$shukeiKbn*/'AREA_DAI' = /*sibuCd*/'031'
                  )

/*END*/
/*IF userTypeCd.equals("02") && taishoJoken.equals("ALL") */
  and BT60.mise_cd in (select mise_cd 
                   from bm01tenm 
                   where company_cd = /*companyCd*/'00' 
                     and   oner_cd in (select oner_cd from bm06uonr 
                                       where  USER_ID = /*userId*/'99990002'   
                                       AND   COMPANY_CD = /*companyCd*/'00')
                     and /*$shukeiKbn*/'AREA_DAI' = /*sibuCd*/'031'
                  )
/*END*/
/*IF userTypeCd.equals("01") && limitFlg*/
  AND BM01.SIBU_CD IN (
      SELECT   BM51.SIBU_CD
      FROM     BM51SVSB BM51
      WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
      AND    BM51.SV_CD      = /*userId*/'99990005'
      GROUP BY BM51.SIBU_CD
  )
  and BT60.mise_cd = bm01.mise_cd
  and BT60.company_cd = bm01.company_cd
/*END*/
group by BT60.eigyo_dt
) as main

left join 
(
select
  BT60.eigyo_dt,
  decimal(avg(DOUBLE(BT60.uriage)) +0.5 ,11,0) as uriage,
  decimal(avg(DOUBLE(BT60.kyakusu)) +0.5 ,11,0) as kyakusu,
  decimal(avg(DOUBLE(BT60.uriage_zen_/*$zenDataShu*/'DOYO')) +0.5 ,11,0) as uriage_zen,
  decimal(avg(DOUBLE(BT60.kyakusu_zen_/*$zenDataShu*/'DOYO')) +0.5 ,11,0) as kyakusu_zen,
1 as dummy
from (select mise_cd , sibu_cd
          from bm01tenm BM01
          where company_cd = /*companyCd*/'00'
          and   /*$shukeiKbn*/'AREA_DAI' = /*sibuCd*/'031') BM01
,    BT60ZNIP BT60
,    BN01DTEN BN01
where BT60.company_cd = /*companyCd*/'00' 
  AND BT60.eigyo_dt between /*taishoKikanFrom*/'20080601' and /*taishoKikanTo*/'20080631'
  AND BT60.open_kbn = 1
  AND BT60.oldm_flg = '0'
  AND BN01.KBN1 = '1'
  AND BT60.COMPANY_CD = BN01.COMPANY_CD 
  AND BT60.EIGYO_DT   = BN01.EIGYO_DT 
  AND BT60.MISE_CD    = BN01.MISE_CD
  AND BT60.MISE_CD    = BM01.MISE_CD
/*IF userTypeCd.equals("01") && limitFlg*/
  AND BM01.SIBU_CD IN (
      SELECT   BM51.SIBU_CD
      FROM     BM51SVSB BM51
      WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
      AND    BM51.SV_CD      = /*userId*/'99990005'
      GROUP BY BM51.SIBU_CD
  )
/*END*/

group by BT60.eigyo_dt
) as sibu
on (sibu.eigyo_dt = main.eigyo_dt )

order by eigyo_dt
