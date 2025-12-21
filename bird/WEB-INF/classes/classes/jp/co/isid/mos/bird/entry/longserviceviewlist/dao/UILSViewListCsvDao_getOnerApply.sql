select distinct
	   bm11.oner_cd
     , bm11.oner_name_kj
     , seq_no
     , bm11.sibu_cd
     , bm11.sibu_name
     , bm01.mise_cd
     , bm01.mise_name_kj
     , name
     , tel
     , l_name_kj
     , f_name_kj
     , l_name_rm
     , f_name_rm
     , sex
     , birthday
     , age
     , entry_date
     , exp_kbn
     , bt49.last_user
     , br01.USER_NAME_KJ
     , bm11.keiyaku_end

from   bt49long as bt49
     , br01user as br01
     , (select mise.oner_cd
             , bm11.oner_name_kj
             , bm11.sibu_cd
             , bm10.sibu_name
             , bm11.keiyaku_end
        from   bm11oner as bm11
             , BM01TENM MISE
             , (select sibu_cd
                     , sibu_name
                     , company_cd
                from   bm10gsib
                where  company_cd = /*companyCd*/'00') as bm10
        where  bm11.company_cd = /*companyCd*/'00'
        and    bm11.keiyaku_sta <= /*sysDate*/'20090811'
	    and    bm11.keiyaku_end >= /*sysDate*/'20090811'
        and    bm11.oner_cd   != '39999'
        and    bm10.sibu_cd    = bm11.sibu_cd
        and    bm10.company_cd = bm11.company_cd
        AND    MISE.COMPANY_CD  = bm11.COMPANY_CD
	    AND    MISE.ONER_CD    = bm11.ONER_CD
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
        group by mise.oner_cd
             , bm11.oner_name_kj
             , bm11.sibu_cd
             , bm10.sibu_name
             , bm11.keiyaku_end
        ) as bm11

     , (select name
             , tel
             , company_cd
             , oner_cd
        from   bt21enoj as bt21
        where  bt21.entry_cd    = /*entryCd*/'20'
        and    bt21.entry_year  = /*entryYear*/'2009'
        and    bt21.entry_Kai   = /*entryKai*/'001'
        and    bt21.atesaki_kbn = '00') as bt21

     , br17entl as br17

      , (select mise_cd
              , mise_name_kj
              , company_cd
         from   bm01tenm as bm01
         where  bm01.company_cd = /*companyCd*/'00'
         AND    CLOSE_DT  >= /*fromEntryDt*/'20120727') as bm01

where  bt49.entry_cd   = /*entryCd*/'20'
and    bt49.entry_year = /*entryYear*/'2009'
and    bt49.entry_Kai  = /*entryKai*/'001'
and    bt49.company_cd = /*companyCd*/'00'
and    bt49.oner_cd    = bm11.oner_cd
and    bm11.oner_cd    = bt21.oner_cd
and    br01.user_id    = bt49.last_user
and    bt49.company_cd = bm01.company_cd
and    bt21.company_cd = bt49.company_cd
and    bt49.mise_cd    = bm01.mise_cd
and    br17.entry_cd   = bt49.entry_cd 
and    br17.entry_year = bt49.entry_year
and    br17.entry_kai  = bt49.entry_Kai

order by oner_cd
       , seq_no