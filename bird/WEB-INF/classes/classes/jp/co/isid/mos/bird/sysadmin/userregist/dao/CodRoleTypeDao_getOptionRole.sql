select distinct
       br60.role_cd
from   bc05kcom as bc05
     , br60oprl as br60
where  bc05.company_cd in /*arrCompanyCd*/('00', '60', '70')
and    br60.company_cd = bc05.company_cd
/*IF arrOption != null*/
and    br60.option_kbn in /*arrOption*/('2', '3')
/*END*/