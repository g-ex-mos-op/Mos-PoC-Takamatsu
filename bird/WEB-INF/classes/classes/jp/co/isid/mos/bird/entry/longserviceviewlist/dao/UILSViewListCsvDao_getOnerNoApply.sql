select distinct
	   bm11.oner_cd,
       bm11.oner_name_kj,
       bm11.sibu_cd,
       bm10.sibu_name,
       bm11.keiyaku_end
from   bm11oner as bm11
     , bm10gsib as bm10
     , BM01TENM MISE
where  bm11.company_cd = /*companyCd*/'00'
and    bm10.sibu_cd    = bm11.sibu_cd
and    bm10.company_cd = bm11.company_cd
and    bm11.keiyaku_sta <= /*sysDate*/'20060902'
and    bm11.keiyaku_end >= /*sysDate*/'20060902'
and    bm11.oner_cd   != '39999'
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
and    concat(bm11.company_cd, bm11.oner_cd) not in 
       (select distinct concat(company_cd, oner_cd) 
        from   bt49long
        where  entry_cd   = /*entryCd*/'20'
        and    entry_year = /*entryYear*/'2009'
        and    entry_kai   = /*entryKai*/'001')
order by bm11.oner_cd