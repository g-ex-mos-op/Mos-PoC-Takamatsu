select 
  'åéçáåv' as eigyo_dt,
  main.uriage as uriage,
  main.kyakusu as kyakusu,
  main.uriage_zen as uriage_zen,
  main.kyakusu_zen as kyakusu_zen,
  sibu.uriage as sibu_uriage,
  sibu.kyakusu as sibu_kyakusu,
  sibu.uriage_zen as sibu_uriage_zen,
  sibu.kyakusu_zen as sibu_kyakusu_zen
from (
select BT64.eigyo_dt
,      decimal(avg(DOUBLE(BT64.uriage)) +0.5 ,11,0) as uriage
,      decimal(avg(DOUBLE(BT64.kyakusu)) +0.5 ,11,0) as kyakusu
,      decimal(avg(DOUBLE(BT64.uriage_zen)) +0.5 ,11,0) as uriage_zen
,      decimal(avg(DOUBLE(BT64.kyakusu_zen)) +0.5 ,11,0) as kyakusu_zen
from 
  (SELECT BT64.EIGYO_DT, BT64.MISE_CD
   ,      SUM(BT64.uriage_/*$zenDataShu*/'DOYO') as uriage
   ,      SUM(BT64.kyakusu_/*$zenDataShu*/'DOYO')  as kyakusu
   ,      SUM(BT64.uriage_zen_/*$zenDataShu*/'DOYO') as uriage_zen
   ,      SUM(BT64.kyakusu_zen_/*$zenDataShu*/'DOYO') as kyakusu_zen
   FROM BT64ZGEP BT64
where BT64.company_cd = /*companyCd*/'00'
  and BT64.eigyo_dt = /*taishoYm*/'201208'
  and BT64.open_kbn = 1 
/*IF taishoJoken.equals("MISE")*/
  and BT64.mise_cd = /*hyojiTaisho*/'01776'
/*END*/
/*IF userTypeCd.equals("01")*/
/*IF taishoJoken.equals("ONER")*/
  and BT64.mise_cd in (select mise_cd 
                   from bm01tenm 
                   where company_cd = /*companyCd*/'00' 
                     and oner_cd = /*hyojiTaisho*/'36478' 
                     and /*$shukeiKbn*/'AREA_DAI' = /*sibuCd*/'031'
                  )

/*END*/
/*IF limitFlg*/
  AND EXISTS (
      SELECT BM51.SIBU_CD
      FROM   BM51SVSB BM51
      ,      BM01TENM BM01
      WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
      AND    BM51.SV_CD      = /*userId*/'99990005'
	  AND    BM51.SIBU_CD    = BM01.SIBU_CD
	  AND    BT64.mise_cd    = BM01.mise_cd
	  AND    BT64.company_cd = BM01.company_cd
      GROUP BY BM51.SIBU_CD
  )
/*END*/
/*END*/
/*IF userTypeCd.equals("02") && taishoJoken.equals("ALL") */
 and BT64.mise_cd in (select mise_cd 
                   from bm01tenm 
                   where company_cd = /*companyCd*/'00' 
                     and   oner_cd in (select oner_cd from bm06uonr 
                                       where  USER_ID = /*userId*/'99990002'   
                                       AND   COMPANY_CD = /*companyCd*/'00')
                     and /*$shukeiKbn*/'AREA_DAI' = /*sibuCd*/'031'
                  )
/*END*/
  GROUP BY BT64.EIGYO_DT, BT64.MISE_CD
) BT64
group by BT64.eigyo_dt
) as main

left join (
select BT64.eigyo_dt
,      decimal(avg(DOUBLE(BT64.uriage)) +0.5 ,11,0) as uriage
,      decimal(avg(DOUBLE(BT64.kyakusu)) +0.5 ,11,0) as kyakusu
,      decimal(avg(DOUBLE(BT64.uriage_zen)) +0.5 ,11,0) as uriage_zen
,      decimal(avg(DOUBLE(BT64.kyakusu_zen)) +0.5 ,11,0) as kyakusu_zen
FROM (select mise_cd 
          from bm01tenm BM01
          where company_cd = /*companyCd*/'00'
          and   /*$shukeiKbn*/'AREA_DAI' = /*sibuCd*/'031') BM01
,    (SELECT EIGYO_YM, MISE_CD
      FROM BN01DTEN
      WHERE COMPANY_CD = /*companyCd*/'00' 
      AND   EIGYO_YM   = /*taishoYm*/'201208'
      AND    KBN1 = '1'
      GROUP BY EIGYO_YM, MISE_CD) BN01
,    (SELECT EIGYO_DT, MISE_CD
   ,      SUM(uriage_/*$zenDataShu*/'DOYO') as uriage
   ,      SUM(kyakusu_/*$zenDataShu*/'DOYO')  as kyakusu
   ,      SUM(uriage_zen_/*$zenDataShu*/'DOYO') as uriage_zen
   ,      SUM(kyakusu_zen_/*$zenDataShu*/'DOYO') as kyakusu_zen
   FROM BT64ZGEP BT64
   where company_cd = /*companyCd*/'00' 
   AND eigyo_dt = /*taishoYm*/'201208'
   AND open_kbn = 1
   AND oldm_flg = '0'
/*IF userTypeCd.equals("01") && limitFlg*/
  AND EXISTS (
      SELECT BM51.SIBU_CD
      FROM   BM51SVSB BM51
      ,      BM01TENM BM01
      WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
      AND    BM51.SV_CD      = /*userId*/'99990005'
	  AND    BM51.SIBU_CD    = BM01.SIBU_CD
	  AND    BT64.mise_cd    = BM01.mise_cd
	  AND    BT64.company_cd = BM01.company_cd
      GROUP BY BM51.SIBU_CD
  )
/*END*/
   GROUP BY EIGYO_DT, MISE_CD
  ) BT64
  WHERE BT64.EIGYO_DT   = BN01.EIGYO_YM
  AND   BT64.MISE_CD    = BN01.MISE_CD
  AND   BT64.MISE_CD    = BM01.MISE_CD

group by BT64.eigyo_dt
) as sibu
on (sibu.eigyo_dt = main.eigyo_dt )

