select          bt45.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bt45.area_dai as sibu_cd
-- ELSE
,               bt45.sibu_cd
/*END*/
,               sum(bt45.yosan) as yosan
,               DECIMAL(count(DISTINCT bt45.MISE_CD)) AS MISE_CNT
from            bt45dsjy as bt45
where           bt45.company_cd = /*companyCd*/'00'
AND             bt45.YOSAN <> 0
/*IF taishoKikan.equals("DAY1")*/ 
and             bt45.yosan_dt = /*kikanFrom*/'20060405'
/*END*/

/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH") */ 
and             bt45.yosan_dt between /*kikanFrom*/'20060401' and /*kikanTo*/'20060430'
/*END*/

/*IF limitFlg == true*/
and             bt45.sibu_cd in (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD      = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
    )
/*END*/
                                       

/*IF !tenShu.equals("ALL")*/
   /*IF tenShu.equals("2")*/
                and             (bt45.tenpo_shu = '1' or bt45.tenpo_shu = '2')
   -- ELSE
                and             bt45.tenpo_shu = /*dataShuKbn*/'1'
   /*END*/
/*END*/

/*IF !taishoTenpo.equals("ALL")*/
   /*IF taishoTenpo.equals("FC")*/
                and             bt45.fc_rc = '1'
   -- ELSE
                and             bt45.fc_rc = '2'
   /*END*/
/*END*/

group by        bt45.company_cd

/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
,               bt45.area_dai
-- ELSE
,               bt45.sibu_cd
/*END*/