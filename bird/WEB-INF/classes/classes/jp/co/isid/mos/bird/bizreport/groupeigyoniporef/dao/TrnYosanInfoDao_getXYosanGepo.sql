  select 
      /*IF areaDaiFlg.equals("AREA_DAI_CD")*/
         bt44.area_dai as sibu_cd
      -- ELSE
         bt44.sibu_cd
      /*END*/
       , sum(yosan) as yosan
,               DECIMAL(COUNT(DISTINCT bt44.mise_cd)) AS MISE_CNT
    from bt44msjy as bt44
       , bm10gsib as bm10
   where bt44.company_cd = bm10.company_cd
AND             bt44.YOSAN <> 0

   /*IF areaDaiFlg.equals("AREA_DAI_CD")*/
     and bm10.sibu_cd = bt44.area_dai
     and bm10.area_dai_flg = '1'
   -- ELSE
     and bm10.sibu_cd = bt44.sibu_cd
   /*END*/
   
     and bt44.company_cd =/*companyCd*/'00'
     and substr(bt44.mise_cd ,1,1) in /*keyList*/('A','X')

   /*IF areaDaiFlg.equals("AREA_DAI_CD")*/
     and bt44.area_dai = /*sibuCd*/'012'
   -- ELSE
     and bt44.sibu_cd = /*sibuCd*/'012'   
   /*END*/
     and bt44.mise_cd not in (select  mise_cd
                              from    bm01tenm
                              where   company_cd = /*companyCd*/'00'
                          /*IF areaDaiFlg.equals("AREA_DAI_CD")*/
                              and     area_dai = /*sibuCd*/'012'
                          -- ELSE
                              and     sibu_cd = /*sibuCd*/'012'
                          /*END*/
                             )

/*IF taishoKikan.equals("KIBETU")*/ 
     and bt44.yosan_dt between /*kikanFrom*/'200605' and /*kikanTo*/'200606'
-- ELSE
     and bt44.yosan_dt = /*kikanFrom*/'200605'
/*END*/

/*IF limitFlg == true*/     
and    bt44.sibu_cd in (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD      = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
    )
/*END*/

group by 
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
          bt44.area_dai
-- ELSE
          bt44.sibu_cd
/*END*/
