select br17.entry_cd
     , br17.entry_year
     , br17.entry_kai
     , entry_title
     , oner.from_dt as oner_from_dt
     , oner.to_dt as oner_to_dt
     , honbu.from_dt as honbu_from_dt
     , honbu.to_dt as honbu_to_dt
     , (case when entry_oner.cnt is null then 0 else entry_oner.cnt end) as apply_oner_cnt
     , (case when entry_oner.cnt is null then oner.all_oner_cnt 
             else oner.all_oner_cnt - entry_oner.cnt
        end) as no_apply_oner
     , sum(case when entry_person.cnt is null then 0 
                else entry_person.cnt
                end) as apply_cnt

from (select count(distinct oner.oner_cd) as all_oner_cnt
  	  from   bm11oner oner
	       , BM01TENM MISE
	  where  oner.company_cd   = /*companyCd*/'00'
	  and    keiyaku_sta <= /*sysDate*/'20090811'
	  and    keiyaku_end >= /*sysDate*/'20090811'
	  and    oner.oner_cd     != '39999'
	  AND    MISE.COMPANY_CD   = ONER.COMPANY_CD
	  AND    MISE.ONER_CD   = ONER.ONER_CD
	  AND    EXISTS (select from_dt
         from   br18entd br18
         where  br18.usertype_cd = '01'
         and    br18.day_kbn = '04'
         and    br18.entry_cd   = /*entryCd*/'20'
		 and    br18.entry_year = /*entryYear*/'2005'
		 and    br18.entry_kai  = /*entryKai*/'017'
         AND    MISE.CLOSE_DT  >= br18.from_dt
         )
)  as oner
  
,      (select from_dt
             , to_dt
             , entry_cd
             , entry_year
             , entry_kai
        from   br18entd
        where  usertype_cd = '02'
        and    day_kbn = '04'
        ) as oner

,       (select from_dt
              , to_dt
              , entry_cd
              , entry_year
              , entry_kai
         from   br18entd
         where  usertype_cd = '01'
         and    day_kbn = '04'
         ) as honbu
         
,      br17entl as br17

   left join  (select entry_cd
                    , entry_year
                    , entry_kai
                    , count(distinct bt49.oner_cd) as cnt
                 from bt49long as bt49
                    , (select distinct
                              mise.company_cd
                            , mise.oner_cd
                       from   bm11oner as oner
                            , BM01TENM MISE
                       where  oner.company_cd   = '00'
                       and    keiyaku_sta <= /*sysDate*/'20090811'
                       and    keiyaku_end >= /*sysDate*/'20090811'
                       and    oner.oner_cd     != '39999'
                       AND    MISE.COMPANY_CD   = ONER.COMPANY_CD
                       AND    MISE.ONER_CD   = ONER.ONER_CD
                       AND    EXISTS (select from_dt
								         from   br18entd br18
								         where  br18.usertype_cd = '01'
								         and    br18.day_kbn = '04'
								         and    br18.entry_cd   = /*entryCd*/'20'
										 and    br18.entry_year = /*entryYear*/'2005'
										 and    br18.entry_kai  = /*entryKai*/'017'
								         AND    MISE.CLOSE_DT  >= br18.from_dt
								         )

                       ) as bm11
                where bt49.company_cd = bm11.company_cd
                and   bt49.oner_cd = bm11.oner_cd         
                group by entry_cd
                       , entry_year
                       , entry_kai      
                ) as entry_oner
           on (entry_oner.entry_cd = br17.entry_cd
           and entry_oner.entry_year = br17.entry_year
           and entry_oner.entry_kai = br17.entry_kai)

   left join  (select entry_cd
                    , entry_year
                    , entry_kai
                    , bt49.company_cd
                    , bt49.oner_cd
                    , count(seq_no) as cnt
               from   bt49long as bt49
               ,    (select distinct
                            mise.company_cd
                          , mise.oner_cd
                     from   bm11oner as oner
                          , BM01TENM MISE
                     where  oner.company_cd   = '00'
                     and    keiyaku_sta <= /*sysDate*/'20090811'
                     and    keiyaku_end >= /*sysDate*/'20090811'
                     and    oner.oner_cd     != '39999'
                     AND    MISE.COMPANY_CD   = ONER.COMPANY_CD
                     AND    MISE.ONER_CD   = ONER.ONER_CD
                     AND    EXISTS (select from_dt
							         from   br18entd br18
							         where  br18.usertype_cd = '01'
							         and    br18.day_kbn = '04'
							         and    br18.entry_cd   = /*entryCd*/'20'
									 and    br18.entry_year = /*entryYear*/'2005'
									 and    br18.entry_kai  = /*entryKai*/'017'
							         AND    MISE.CLOSE_DT  >= br18.from_dt
							         )) as bm11
               where bt49.company_cd = bm11.company_cd
               and   bt49.oner_cd = bm11.oner_cd         
               group by entry_cd
                      , entry_year
                      , entry_kai
                      , bt49.company_cd
                      , bt49.oner_cd  ) as entry_person    
           on (    entry_person.entry_cd  = br17.entry_cd
               and entry_person.entry_year = br17.entry_year
               and entry_person.entry_kai = br17.entry_kai
           )

where  br17.entry_cd   = /*entryCd*/'20'
and    br17.entry_year = /*entryYear*/'2005'
and    br17.entry_kai  = /*entryKai*/'017'
and    sakujo_flg <> '1'
and    br17.entry_cd   = oner.entry_cd 
and    br17.entry_year = oner.entry_year
and    br17.entry_kai  = oner.entry_kai
and    br17.entry_cd   = honbu.entry_cd 
and    br17.entry_year = honbu.entry_year
and    br17.entry_kai  = honbu.entry_kai

group by br17.entry_cd
     , br17.entry_year
     , br17.entry_kai
     , entry_title
     , oner.from_dt
     , oner.to_dt
     , honbu.from_dt
     , honbu.to_dt
     , (case when entry_oner.cnt is null then 0 else entry_oner.cnt end)
     , (case when entry_oner.cnt is null then oner.all_oner_cnt
             else oner.all_oner_cnt - entry_oner.cnt
             end)
