select bt49long.entry_cd
     , bt49long.entry_year
     , bt49long.entry_kai
     , bm11.sibu_cd
     , bm11.oner_cd
     , bm11.oner_name_kj
     , (case when apply_mark is null then 0 else apply_mark end) as apply_mark
     , (case when apply_cnt is null then 0 else apply_cnt end) as apply_cnt

from  (select distinct
              mise.company_cd
            , mise.oner_cd
            , oner.oner_name_kj
            , oner.sibu_cd
       from   bm11oner as oner
            , BM10GSIB BM10
            , BM01TENM MISE
       where  oner.company_cd   = /*companyCd*/'00'
       and    keiyaku_sta <= /*sysDate*/'20070125'
       and    keiyaku_end >= /*sysDate*/'20070125'
       and    oner.oner_cd     != '39999'
       AND    MISE.COMPANY_CD   = ONER.COMPANY_CD
	   AND    MISE.ONER_CD   = ONER.ONER_CD
	   AND    MISE.CLOSE_DT  >= /*fromEntryDt*/'20120727'
	   /*IF limit*/
       AND    MISE.SIBU_CD IN (
         SELECT   BM51.SIBU_CD
         FROM     BM51SVSB BM51
         WHERE  BM51.COMPANY_CD = /*companyCd*/'00'
         AND    BM51.SV_CD      = /*userId*/'00000316'
         GROUP BY BM51.SIBU_CD
       )
	   /*END*/
		) as bm11

left join (select br17.entry_cd
            , br17.entry_year
            , br17.entry_kai
	      , company_cd
            , oner_cd
            , count(oner_cd) as apply_cnt
            , count(oner_cd) as apply_mark
       from   bt49long as bt49

            , (select entry_cd
                    , entry_year
                    , entry_kai
               from   br17entl
               where  entry_cd   = /*entryCd*/'20'
               and    entry_year = /*entryYear*/'2005'
               and    entry_kai  = /*entryKai*/'013') as br17
     where br17.entry_cd = bt49.entry_cd
       and   br17.entry_year = bt49.entry_year
       and   br17.entry_kai = bt49.entry_kai
    group by bt49.company_cd
           , br17.entry_cd
           , br17.entry_year
           , br17.entry_kai
           , oner_cd) as bt49long

on (bm11.company_cd = bt49long.company_cd
and bm11.oner_cd    = bt49long.oner_cd)

/*IF taishouJokenChoice == "2"*/     
,    (SELECT DISTINCT SIBU_CD
           ,          ONER_CD 
      FROM   BM01TENM 
      
      WHERE  MISE_CD in (SELECT MISE_CD
                         FROM   BM50TANM 
                         WHERE COMPANY_CD = /*companyCd*/'00'
                         AND   SV_CD      = /*svCd*/'00000085')
      AND    COMPANY_CD = /*companyCd*/'00'
      AND    CLOSE_DT  >= /*fromEntryDt*/'20120727') as BM50
      
where  bm11.oner_cd = bm50.oner_cd
/*END*/
 
/*IF taishouJokenChoice == "1"*/
where bm11.oner_cd in (select distinct 
                              oner_cd
                       from   bm01tenm
                       where  company_cd = /*companyCd*/'00'
                       and    sibu_cd    = /*sibuChoice*/'011'
                       AND    CLOSE_DT  >= /*fromEntryDt*/'20120727')
/*END*/

group by bt49long.entry_cd
       , bt49long.entry_year
       , bt49long.entry_kai
       , bm11.sibu_cd
       , bm11.oner_cd
       , bm11.oner_name_kj
       , bt49long.apply_cnt
       , bt49long.apply_mark

order by  bm11.oner_cd