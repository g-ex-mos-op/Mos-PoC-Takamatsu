select          bt44.company_cd
,               bt44.mise_cd
,               sum(bt44.yosan) as yosan
from            bt44msjy as bt44
where           bt44.company_cd = /*companyCd*/'00'

/*IF limitFlg == true*/
and   bt44.sibu_cd in (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD      = /*userId*/'9999000a'
         GROUP BY BM51.SIBU_CD
    )
/*END*/

/*IF areaDaiFlg.equals("SV_CD")*/
AND   bt44.MISE_CD IN (
	     SELECT   BM50.MISE_CD
	     FROM     BM50TANM AS BM50
	     WHERE    BM50.COMPANY_CD = /*companyCd*/'00'
	     AND      BM50.SV_CD      = /*svCd*/'00000921'
	)                         
/*END*/

/*IF taishoKikan.equals("KIBETU")*/ 
and             bt44.yosan_dt between /*kikanFrom*/'200605' and /*kikanTo*/'200606'
--ELSE
AND   bt44.yosan_dt = /*kikanFrom*/'200705'
/*END*/
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
and             bt44.area_dai = /*sibuCd*/'011'
-- ELSE
and             bt44.sibu_cd = /*sibuCd*/'011'
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

group by        bt44.company_cd,
                bt44.mise_cd