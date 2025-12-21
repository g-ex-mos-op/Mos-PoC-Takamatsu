select distinct
       br59.role_cd
from   br59ktrl as br59
     , bc05kcom as bc05
where  bc05.company_cd in /*arrCompanyCd*/('00','60','70')
and    bc05.company_cd = br59.company_cd
and    keiyaku_type    = /*keiyakuChoice*/'01'