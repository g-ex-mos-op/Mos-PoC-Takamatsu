select          bt44.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bt44.area_dai as sibu_cd
-- ELSE
,               bt44.sibu_cd
/*END*/
,               sum(bt44.yosan) as yosan
,               DECIMAL(COUNT(DISTINCT bt44.mise_cd)) AS MISE_CNT
from            bt44msjy as bt44
where           bt44.company_cd = /*companyCd*/'00'
AND             bt44.YOSAN <> 0
/*IF limitFlg == true*/
and             bt44.sibu_cd in (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD      = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
    )
/*END*/

/*IF taishoKikan.equals("KIBETU")*/ 
and             bt44.yosan_dt between /*kikanFrom*/'200605' and /*kikanTo*/'200606'
-- ELSE
and             bt44.yosan_dt = /*kikanFrom*/'200605'
/*END*/

/*IF !tenShu.equals("ALL")*/
   /*IF tenShu.equals("2")*/
                and             (bt44.tenpo_shu = '1' or bt44.tenpo_shu = '2')
   -- ELSE
                and             bt44.tenpo_shu = /*dataShuKbn*/'1'
   /*END*/
/*END*/

/*IF !taishoTenpo.equals("ALL")*/
   /*IF taishoTenpo.equals("FC")*/
                and             bt44.fc_rc = '1'
   -- ELSE
                and             bt44.fc_rc = '2'
   /*END*/
/*END*/

group by        bt44.company_cd

/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bt44.area_dai
-- ELSE
,               bt44.sibu_cd
/*END*/
