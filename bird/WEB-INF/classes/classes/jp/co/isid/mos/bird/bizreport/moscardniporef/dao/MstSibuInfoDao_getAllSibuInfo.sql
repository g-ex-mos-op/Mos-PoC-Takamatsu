select company_cd
,      sibu_cd
,      sibu_name
from   bm10gsib
where  company_cd =/*companyCd*/'00'
/*IF limitFlg == true*/
and    sibu_cd in (select   bm51.sibu_cd
                   from     bm51svsb as bm51
                   where    bm51.sv_cd = /*userId*/'00000921'
                   and      bm51.company_cd = /*companyCd*/'00'
                   group by bm51.sibu_cd
                   )
/*END*/

/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
and    area_dai_flg = '1'
/*END*/

order by company_cd
,        sibu_cd