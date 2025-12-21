select 
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
       bt45.area_dai as sibu_cd
-- ELSE
       bt45.sibu_cd
/*END*/
    , sum(yosan) as yosan
,     DECIMAL(count(DISTINCT bt45.MISE_CD)) AS MISE_CNT
from  bt45dsjy as bt45
    , bm10gsib as bm10
where bt45.company_cd = bm10.company_cd
AND   bt45.YOSAN <> 0

/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
 and   bm10.sibu_cd = bt45.area_dai
 and   bm10.area_dai_flg = '1'     
-- ELSE
 and   bm10.sibu_cd = bt45.sibu_cd
/*END*/

and   bt45.company_cd =/*companyCd*/'00'
and   substr(bt45.mise_cd ,1,1) in /*keyList*/('A','X')

/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
 and   bt45.area_dai = /*sibuCd*/'012'
-- ELSE
 and   bt45.sibu_cd = /*sibuCd*/'012'
/*END*/     
and   bt45.mise_cd not in (select  mise_cd
                           from    bm01tenm
                           where   company_cd = /*companyCd*/'00'
                   /*IF areaDaiFlg.equals("AREA_DAI_CD")*/
                           and     area_dai = /*sibuCd*/'012'
                   -- ELSE
                           and     sibu_cd = /*sibuCd*/'012'
                   /*END*/
                     )
/*IF taishoKikan.equals("DAY1")*/ 
and   bt45.yosan_dt = /*kikanFrom*/'20060704'
/*END*/
/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH")*/
and   bt45.yosan_dt between /*kikanFrom*/'20060704' and /*kikanTo*/'20060704'
/*END*/

/*IF limitFlg == true*/
and   bt45.sibu_cd in (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD      = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
    )
/*END*/

group by 
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
  bt45.area_dai
-- ELSE
  bt45.sibu_cd
/*END*/