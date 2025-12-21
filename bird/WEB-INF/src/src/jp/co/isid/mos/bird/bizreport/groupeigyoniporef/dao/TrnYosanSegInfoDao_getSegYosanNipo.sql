select bm08.segment_type
,      bm08.sort_seq
,      (case when sum(yosan) is null  then 0 else sum(yosan) end )  as yosan
,      DECIMAL(COUNT(DISTINCT yosan.MISE_CD)) AS MISE_CNT
from   bm08sgtp as bm08
left join 
(
     select   sibu.segment_type
     ,        bt45.company_cd
     ,        bt45.mise_cd
     ,        sum(yosan) as yosan
     from     bt45dsjy as bt45
     ,        ( select    segment_type
                ,         sibu_cd
                from      bm09gtsg as bm09
                ,         bm01tenm as bm01
                where     bm09.gyotai_kbn = bm01.gyotai_kbn
                and       bm09.segment_type in /*segmentList*/('M','J')
            /*IF !taishoTenpo.equals("ALL")*/
                /*IF taishoTenpo.equals("FC")*/
                and       bm01.mise_kbn like '_1_'
                -- ELSE
                and       bm01.mise_kbn like '_2_'
                /*END*/
            /*END*/

                group by  segment_type
                ,         sibu_cd
              ) as sibu
     where    bt45.sibu_cd = sibu.sibu_cd
     AND      bt45.YOSAN <> 0

/*IF taishoKikan.equals("DAY1")*/ 
     and      bt45.yosan_dt = /*kikanFrom*/'20060530'
/*END*/

/*IF taishoKikan.equals("DAYS") || taishoKikan.equals("APPMONTH")*/ 
     and      bt45.yosan_dt between /*kikanFrom*/'20060101' and /*kikanTo*/'20060201'
/*END*/
        
/*IF !tenShu.equals("ALL")*/
    /*IF tenShu.equals("2")*/
     and      (bt45.tenpo_shu = '1' or bt45.tenpo_shu = '2')
    -- ELSE
     and       bt45.tenpo_shu = /*tenShu*/'1 '
    /*END*/
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
    and          bt45.company_cd = /*companyCd*/'00'
    
     group by sibu.segment_type
     ,        bt45.company_cd
     ,        bt45.mise_cd
) as yosan
on  ( bm08.segment_type = yosan.segment_type)
where bm08.segment_type in /*segmentList*/('M','J')
group by bm08.segment_type
,        bm08.sort_seq
order by bm08.sort_seq