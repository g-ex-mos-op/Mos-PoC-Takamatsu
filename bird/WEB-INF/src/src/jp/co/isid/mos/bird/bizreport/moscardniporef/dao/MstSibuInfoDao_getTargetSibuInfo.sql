select   bm10.company_cd
,        bm10.sibu_cd
,        bm10.sibu_name
from     bm10gsib as bm10
,        (select company_cd
          ,      sibu_cd
          ,      area_dai
          from   bm01tenm
/*IF kbnCd.equals("GYOTAI")*/
          where  gyotai_kbn in (select gyotai_kbn
                                from   bm09gtsg
                                where  segment_type =/*sibuCd*/'M'
                                )
          and    company_cd = /*companyCd*/'00'
          group by 
       	          company_cd
          ,      sibu_cd
          ,      area_dai
/*END*/          
          ) as bm01

where    bm10.company_cd = bm01.company_cd
/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
and      bm01.area_dai = bm10.sibu_cd
-- ELSE
and      bm01.sibu_cd = bm10.sibu_cd
/*END*/

and      bm10.company_cd =/*companyCd*/'00'

/*IF areaDaiFlg.equals("AREA_DAI_CD")*/
and      bm10.area_dai_flg = '1'
/*END*/

/*IF kbnCd.equals("SLAREA")*/
and      bm10.slarea_cd = /*sibuCd*/'10001'
/*END*/
/*IF kbnCd.equals("JIGYOU")*/
and      bm10.jigyou_cd = /*sibuCd*/'11000'
/*END*/
/*IF kbnCd.equals("HONBU")*/
and      bm10.honbu_cd = /*sibuCd*/'10000'
/*END*/

/*IF kbnCd.equals("SIBU")*/
and      bm10.sibu_cd = /*sibuCd*/'022'
/*END*/
	
/*IF limitFlg == true*/
and      bm01.sibu_cd in (select   bm51.sibu_cd
                          from     bm51svsb as bm51
                          where    bm51.sv_cd = /*userId*/'00000921'
                          and      bm51.company_cd = /*companyCd*/'00'
                          group by bm51.sibu_cd
                          )
/*END*/

group by bm10.company_cd
,        bm10.sibu_cd
,        bm10.sibu_name
order by bm10.company_cd
,        bm10.sibu_cd