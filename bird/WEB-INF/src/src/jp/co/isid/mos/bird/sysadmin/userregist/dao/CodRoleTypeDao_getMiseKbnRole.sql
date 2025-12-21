select distinct
       br61.role_cd
from   br61mkrl as br61
     , (select distinct
               company_cd
             , mise_kbn
        from   bm01tenm
        where  mise_cd = /*miseCd*/'01754') as bm01
where  br61.company_cd in /*arrCompanyCd*/('00')
and    br61.company_cd = bm01.company_cd
and    br61.mise_kbn   = bm01.mise_kbn